package com.globalmate.service.match.auto;

import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.SysMatchNeed;
import com.globalmate.data.entity.User;
import com.globalmate.service.match.MatchService;
import com.globalmate.service.need.NeedService;
import com.globalmate.service.user.UserService;
import com.globalmate.uitl.CollectionUtils;
import com.globalmate.uitl.StringUtils;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Override
    public void match() {

    }

    @Override
    public List<SysMatchNeed> match(List<Need> needs) {
        List<User> users = userService.listUsers();
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        List<SysMatchNeed> sysMatchNeeds = matching(needs, users);
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
                if (matchTags(keyWords[1], user)) {
                    SysMatchNeed matchNeed = matchService.create(user, need);
                    matchNeed.setMatchInfo(StringUtils.join_(keyWords[1], user.getHelpAvailable()));
                    sysMatchNeeds.add(matchNeed);
                }
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
        if (StringUtils.isEmpty(location) || user == null) {
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
            Iterator<String> split = Splitter.on(",").omitEmptyStrings().split(user.getHelpAvailable()).iterator();
            do {
                if (matchSuccess(needTag, split.next())) {
                    match = true;
                    break;
                }
            } while (split.hasNext());
        }
        return match;
    }


}
