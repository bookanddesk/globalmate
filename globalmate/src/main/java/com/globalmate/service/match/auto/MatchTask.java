package com.globalmate.service.match.auto;

import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.SysMatchNeed;
import com.globalmate.data.entity.TProvide;
import com.globalmate.service.match.MatchService;
import com.globalmate.service.need.NeedService;
import com.globalmate.service.provider.ProvideService;
import com.globalmate.service.user.UserService;
import com.globalmate.uitl.CollectionUtils;
import com.globalmate.uitl.StringUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author XingJiajun
 * @Date 2018/7/12 15:54
 * @Description
 */
@Component
public class MatchTask {

    @Autowired
    private MatchService matchService;
    @Autowired
    private NeedService needService;
    @Autowired
    private ProvideService provideService;
    @Autowired
    private UserService userService;

    private static final Float MIN_MATCH_RATIO = 0.50f;


//    @Scheduled(cron = "0 0/30 * * * ?")
    public void doMatch() {

        List<Need> needs = needService.listUnHandled(null);
        if (CollectionUtils.isEmpty(needs)) {
            return;
        }


        List<TProvide> provides = provideService.getProvide(null);
        if (CollectionUtils.isEmpty(provides)) {
            return;
        }

        List<SysMatchNeed> matchNeeds = matching(needs, provides);
        if (CollectionUtils.isNotEmpty(matchNeeds)) {
            matchService.addMatchNeeds(matchNeeds);
        }

        //发送消息


    }

    /**
     * 根据帮助匹配需求，先过滤位置，再匹配需求描述
     * @param needs
     * @param provides
     * @return
     */
    private List<SysMatchNeed> matching(List<Need> needs, List<TProvide> provides) {
        List<SysMatchNeed> sysMatchNeeds = Lists.newArrayList();

//        Map<String, TreeMap<Float, String>> matchMap = Maps.newConcurrentMap();
        for (Need need : needs) {

            String[] keyWords = needService.getKeyWords(need);
            if (!ArrayUtils.isNotEmpty(keyWords)) {
                continue;
            }

            //只有国家信息时不匹配
            if (keyWords.length == 1) {
                continue;
            }

            for (TProvide provide : provides) {
                //过滤位置
                if (!filterProvide(keyWords[0], provide)) {
                    continue;
                }

                //描述匹配
                float matchRatio = StringUtils.matchRatio(keyWords[1], provide.getpFeature());
                if (matchRatio < MIN_MATCH_RATIO) {
                    continue;
                }

                SysMatchNeed matchNeed = matchService.create(provide);
                matchNeed.setNeedId(need.getId());
                matchNeed.setuNeedId(need.getUserId());
                matchNeed.setuNeedName(userService.getName(need.getUserId()));
                matchNeed.setMatchInfo(String.valueOf(matchRatio));
                sysMatchNeeds.add(matchNeed);

                //其他关键字匹配
//                for (int i = 2; i < keyWords.length; i++) {
//                    matchRatio += StringUtils.matchRatio(keyWords[i], provide.getpFeature());
//                }
//
//                if (matchMap.get(need.getId()) == null) {
//                    matchMap.put(need.getId(), new TreeMap<>());
//                }
//                matchMap.get(need.getId()).put(matchRatio, provide.getId());

            }
        }

        //TODO 按照匹配率选择前10条
//        for (Iterator<Map.Entry<String, TreeMap<Float, String>>> iterator = matchMap.entrySet().iterator();
//             iterator.hasNext();) {
//            Map.Entry<String, TreeMap<Float, String>> next = iterator.next();
//            TreeMap<Float, String> mathcedProvides = next.getValue();
//            if (mathcedProvides != null && mathcedProvides.size() > 10) {
//
//            }
//
//        }

        return sysMatchNeeds;
    }

    /**
     * 位置过滤
     * @param location
     * @param provide
     * @return
     */
    private boolean filterProvide(String location, TProvide provide) {
        if (StringUtils.isEmpty(location) || provide == null) {
            return true;
        }
        boolean filter = false;
        if (provide.getpLocation() != null) {
            filter = StringUtils.endsWithIgnoreCase(location, provide.getpLocation());
        }

        if (!filter && provide.getpResident1() != null) {
            filter = matchSuccess(location, provide.getpResident1());
        }

        if (!filter && provide.getpResident2() != null) {
            filter = matchSuccess(location, provide.getpResident2());
        }

        return filter;
    }

    /**
     * 根据用户注册信息匹配
     * @param keyWords
     * @return
     */
    private List<SysMatchNeed> matching(String[] keyWords) {
        return null;
    }


    /**
     * @param str
     * @param target
     * @return
     */
    private boolean matchSuccess(String str, String target) {
        return StringUtils.matchRatio(str, target) > MIN_MATCH_RATIO;
    }


}
