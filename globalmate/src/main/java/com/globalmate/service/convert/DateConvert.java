package com.globalmate.service.convert;

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
