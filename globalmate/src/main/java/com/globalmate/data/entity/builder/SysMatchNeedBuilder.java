package com.globalmate.data.entity.builder;

import com.globalmate.common.context.SpringContextUtils;
import com.globalmate.data.entity.*;
import com.globalmate.service.user.UserService;

/**
 * @author XingJiajun
 * @Date 2018/7/17 11:02
 * @Description
 */
public class SysMatchNeedBuilder extends BaseEntityBuilder<SysMatchNeed> {

    private SysMatchNeed sysMatchNeed;

    @Override
    protected SysMatchNeed bulid(String className) {
        sysMatchNeed = super.bulid(className);
        if (sysMatchNeed == null) {
            sysMatchNeed = new SysMatchNeed();
            sysMatchNeed.setId(id());
        }
        return sysMatchNeed;
    }

    @Override
    public SysMatchNeedBuilder build() {
        sysMatchNeed = bulid(SysMatchNeed.class.getName());
        sysMatchNeed.setMatchCreateTime(date());
        sysMatchNeed.setMatchModifyTime(date());
        return this;
    }

    @Override
    public SysMatchNeed get() {
        return sysMatchNeed;
    }

    public SysMatchNeedBuilder need(Need need) {
        if (need != null) {
            sysMatchNeed.setNeedId(need.getId());
            sysMatchNeed.setuNeedId(need.getUserId());
            sysMatchNeed.setuNeedName(SpringContextUtils.getBean(UserService.class).getName(need.getUserId()));
        }
        return this;
    }

    public SysMatchNeedBuilder user(User user) {
        if (user != null) {
            sysMatchNeed.setProviderId(user.getId());
            sysMatchNeed.setProviderName(user.getNikename());
        }
        return this;
    }

    public SysMatchNeedBuilder provide(TProvide provide) {
        if (provide != null) {
            sysMatchNeed.setProvideId(provide.getId());
            sysMatchNeed.setProviderId(provide.getuId());
            sysMatchNeed.setProviderName(provide.getuName());
        }
        return this;
    }



}
