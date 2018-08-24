package com.globalmate.service.convert;

import com.globalmate.uitl.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author XingJiajun
 * @Date 2018/8/23 21:14
 * @Description
 */
public class DateConvert implements Converter<String, Date> {

    private String format;

    @Override
    public Date convert(String s) {
        if (StringUtils.isBlank(s)) {
            return null;
        }
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    public void setFormat(String format) {
        this.format = format;
    }
}
