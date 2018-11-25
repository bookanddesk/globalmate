package com.globalmate.service.match.auto;

import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.NeedCommon;
import com.globalmate.data.entity.SysMatchNeed;
import com.globalmate.data.entity.User;
import com.globalmate.data.entity.builder.SysMatchNeedBuilder;
import com.globalmate.data.entity.vo.NeedAggEntity;
import com.globalmate.service.location.LocationService;
import com.globalmate.service.match.MatchService;
import com.globalmate.service.need.NeedService;
import com.globalmate.service.need.NeedTypeEnum;
import com.globalmate.service.user.UserService;
import com.globalmate.uitl.CollectionUtils;
import com.globalmate.uitl.DateUtil;
import com.globalmate.uitl.GMConstant;
import com.globalmate.uitl.StringUtils;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author XingJiajun
 * @Date 2018/7/16 15:50
 * @Description
 */
@Component
public class UserMatchStrategy extends MatchStrategy {

    @Autowired
    private UserService userService;
    @Autowired
    private NeedService needService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private LocationService locationService;

    @Override
    public void match() {
    }

    @Override
    public List<SysMatchNeed> match(List<Need> needs) {
        List<User> users = userService.listAllUsers();
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
//        List<SysMatchNeed> sysMatchNeeds = matching(needs, users);
        List<SysMatchNeed> sysMatchNeeds = commonMatching(needs, users);
//        List<SysMatchNeed> sysMatchNeeds = allMatching(needs, users);
        return sysMatchNeeds;
    }


    /**
     * 根据用户Tag匹配需求，先过滤位置，再匹配需求描述
     * @param needs
     * @param users
     * @return
     */
    private List<SysMatchNeed> matching(List<Need> needs, List<User> users) {
        List<SysMatchNeed> sysMatchNeeds = Lists.newArrayList();

        for (Need need : needs) {

            if (need == null) {
                continue;
            }

            String[] keyWords = needService.getKeyWords(need);
            if (!ArrayUtils.isNotEmpty(keyWords)) {
                continue;
            }

            //只有国家信息时不匹配
            if (keyWords.length == 1) {
                continue;
            }

            for (User user : users) {
                if (user == null || StringUtils.equalsIgnoreCase(user.getId(), need.getUserId())) {
                    continue;
                }
                //过滤位置
                if (!filterUser(keyWords[0], user)) {
                    continue;
                }

                //标签匹配
                if (!matchTags(keyWords[1], user)) {
                    continue;
                }

                //匹配已存在
                if (isMatchExist(need.getId(), user.getId())) {
                    continue;
                }

                SysMatchNeed matchNeed = new SysMatchNeedBuilder().build().need(need).user(user).get();
                matchNeed.setMatchInfo(StringUtils.join_(keyWords[1], user.getHelpAvailable()));
                sysMatchNeeds.add(matchNeed);
            }
        }


        return sysMatchNeeds;
    }

    /**
     * 通用需求匹配，国家，城市，标签
     * @param needs
     * @param users
     * @return
     */
    private List<SysMatchNeed> commonMatching(List<Need> needs, List<User> users) {
        List<SysMatchNeed> sysMatchNeeds = Lists.newArrayList();

        for (Need need : needs) {

            if (need == null) {
                continue;
            }

            if (timeOutCheck(need)) {
                needService.closeNeed(need.getId());
                continue;
            }

            for (User user : users) {
                if (user == null ||
                        StringUtils.isBlank(user.getOpenid()) ||
                        StringUtils.isBlank(user.getCountry()) ||
                        StringUtils.isBlank(user.getCity()) ||
                        StringUtils.equalsIgnoreCase(user.getId(), need.getUserId())) {
                    continue;
                }
                //过滤位置
                if (!locationMatch(need, user)) {
                    continue;
                }

                //标签匹配
                if (!matchTags(NeedTypeEnum.valueOf(need.getType()), user)) {
                    continue;
                }

                //匹配已存在
                if (isMatchExist(need.getId(), user.getId())) {
                    continue;
                }

                SysMatchNeed matchNeed = new SysMatchNeedBuilder().build().need(need).user(user).get();
                matchNeed.setMatchInfo(StringUtils.join_(need.getType(), user.getHelpAvailable()));
                sysMatchNeeds.add(matchNeed);
            }
        }

        return sysMatchNeeds;
    }

    private List<SysMatchNeed> allMatching(List<Need> needs, List<User> users) {
        List<SysMatchNeed> sysMatchNeeds = Lists.newArrayList();

        for (Need need : needs) {

            if (need == null) {
                continue;
            }

            if (timeOutCheck(need)) {
                needService.closeNeed(need.getId());
                continue;
            }

            for (User user : users) {
                if (user == null ||
                        StringUtils.isBlank(user.getOpenid()) ||
                        StringUtils.equalsIgnoreCase(user.getId(), need.getUserId())) {
                    continue;
                }

                //匹配已存在
                if (isMatchExist(need.getId(), user.getId())) {
                    continue;
                }

                SysMatchNeed matchNeed = new SysMatchNeedBuilder().build().need(need).user(user).get();
                matchNeed.setMatchInfo(StringUtils.join_(need.getType(), user.getHelpAvailable()));
                sysMatchNeeds.add(matchNeed);
            }
        }

        return sysMatchNeeds;
    }

    /**
     * 位置过滤
     * @param location
     * @return
     */
    private boolean filterUser(String location, User user) {
        if (StringUtils.isEmpty(location)) {
            return false;
        }
        boolean filter = false;
        if (user.getCountry() != null) {
            filter = matchSuccess(location, user.getCountry());
        }

        if (!filter && user.getCity() != null) {
            filter = matchSuccess(location, user.getCity());
        }

        return filter;
    }


    private boolean matchTags(String needTag, User user) {
        boolean match = false;
        if (user != null && StringUtils.isNoneBlank(user.getHelpAvailable())) {
            Iterator<String> split = Splitter.on("、").omitEmptyStrings().split(user.getHelpAvailable()).iterator();
            do {
                if (matchSuccess(needTag, split.next())) {
                    match = true;
                    break;
                }
            } while (split.hasNext());
        }
        return match;
    }

    private boolean matchTags(NeedTypeEnum needTypeEnum, User user) {
        boolean match = false;
        if (user != null && StringUtils.isNoneBlank(user.getHelpAvailable())) {
            Iterator<String> split = Splitter.on("、").omitEmptyStrings().split(user.getHelpAvailable()).iterator();
            do {
                if (needTypeEnum.equals(NeedTypeEnum.convert(split.next()))) {
                    match = true;
                    break;
                }
            } while (split.hasNext());
        }
        return match;
    }

    private boolean isMatchExist(String needId, String userId) {
        SysMatchNeed matchNeed = new SysMatchNeed();
        matchNeed.setNeedId(needId);
        matchNeed.setProviderId(userId);
        return CollectionUtils.isNotEmpty( matchService.queryLike(matchNeed));
    }

    private boolean locationMatch(Need need, User targetUser) {
        if (StringUtils.isBlank(need.getWhere())) {
            return false;
        }
        String[] split = need.getWhere().split(GMConstant.UNDERLINE);
        if (split.length < 2) {
            return false;
        }
        String country = split[0], city = split[1];
//        if(!locationMatch(country, targetUser.getCountry())) {
////            return false;
////        }
////        return locationMatch(city, targetUser.getCity());
        if (!locationService.countryEquals(country, targetUser.getCountry())) {
            return false;
        }
        return locationService.cityEquals(city, targetUser.getCity());
    }

    private boolean locationMatch(String location, String targetLocation) {
        return StringUtils.equalsIgnoreCase(location, targetLocation);
    }

    private boolean timeOutCheck (Need need) {
        NeedAggEntity needAgg = needService.getNeedAgg(need.getId());
        if (needAgg.getConceretNeed() == null) {
            return true;
        }
        if (needAgg.getConceretNeed() instanceof NeedCommon) {
            NeedCommon common = (NeedCommon) needAgg.getConceretNeed();
            if (common.getEndTime() == null) {
                return false;
            }
            return Date.from(Instant.now()).after(common.getEndTime());
        }
        return false;
    }


    public List<SysMatchNeed> matchAll(String needId) {
        if (StringUtils.isEmpty(needId)) {
            return null;
        }

        Need need = needService.getNeed(needId);
        if (need == null) {
            return null;
        }

        List<SysMatchNeed> sysMatchNeeds = allMatching(Lists.newArrayList(need), userService.listAllUsers());
        return sysMatchNeeds;
    }

}
