package com.globalmate.uitl;

import com.google.common.base.Joiner;

/**
 * @author XingJiajun
 * @Date 2018/7/12 14:52
 * @Description
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static <E> String join_ (E... els) {
        return Joiner.on("_").join(els);
    }

}
