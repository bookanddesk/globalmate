package com.globalmate.service.match.auto;

import com.globalmate.data.entity.Need;
import com.globalmate.data.entity.SysMatchNeed;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author XingJiajun
 * @Date 2018/7/16 15:53
 * @Description
 */
@Component
public class MatchContext {
    MatchStrategy matchStrategy;
    public MatchContext(@Qualifier("userMatchStrategy") MatchStrategy matchStrategy) {
        this.matchStrategy = matchStrategy;
    }

    public void match() {
        matchStrategy.match();
    }
    public List<SysMatchNeed> match(List<Need> needs) {
        return matchStrategy.match(needs);
    }

    public List<SysMatchNeed> matchAll(List<Need> needs) {
        return matchStrategy.matchAll(needs);
    }

}
