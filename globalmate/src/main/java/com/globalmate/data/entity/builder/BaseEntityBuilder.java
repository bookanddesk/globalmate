package com.globalmate.data.entity.builder;

import com.globalmate.data.entity.BaseEntity;
import com.globalmate.uitl.IdGenerator;

import java.time.Instant;
import java.util.Date;

/**
 * @author XingJiajun
 * @Date 2018/7/17 10:57
 * @Description
 */
public abstract class BaseEntityBuilder<U extends BaseEntity> implements IBuilder<BaseEntity> {

    protected String id() {
        return IdGenerator.generateId();
    }

    protected Date date() {
        return Date.from(Instant.now());
    }

    protected U bulid(String className) {
        U u = null;
        try {
            u = (U) Class.forName(className).newInstance();
            u.setId(id());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return u;
    }

}
