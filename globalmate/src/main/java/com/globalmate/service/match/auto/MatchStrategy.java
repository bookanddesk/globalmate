package com.globalmate.service.match.auto;

import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.SysMatchNeed;
import com.globalmate.uitl.StringUtils;

import java.util.List;

/**
 * @author XingJiajun
 * @Date 2018/7/16 15:49
 * @Description
 */
public abstract class MatchStrategy {
    Float MIN_MATCH_RATIO = 0.50f;

    public abstract void match();
    public abstract List<SysMatchNeed> match(List<Need> needs);
    public abstract List<SysMatchNeed> matchAll(List<Need> needs);

    protected boolean matchSuccess(String str, String target) {
        if (StringUtils.isBlank(str) || StringUtils.isBlank(target)) {
            return false;
        }
        if (StringUtils.equalsIgnoreCase(str, target)) {
            return true;
        }
        return StringUtils.matchRatio(str, target) > MIN_MATCH_RATIO;
    }
}
