package com.globalmate.service.common;

/**
 * @author XingJiajun
 * @Date 2018/7/12 10:07
 * @Description
 */
public interface ICreateService<T, U> {

    T create(U u);

}
