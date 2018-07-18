package com.globalmate.data.entity.builder;

/**
 * @author XingJiajun
 * @Date 2018/7/17 10:55
 * @Description
 */
public interface IBuilder<T> {
    IBuilder build();
    T get();
}
