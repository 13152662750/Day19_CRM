package com.fjw.util;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {

    SimpleDateFormat[] sdFormats = new SimpleDateFormat[]{
            new SimpleDateFormat("yyyy-MM-dd"),
            new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"),
            new SimpleDateFormat("yyyy/MM/dd"),
            new SimpleDateFormat("dd/MM/yyyy"),
            new SimpleDateFormat("yyyy/MM/dd hh:mm:ss"),
            new SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
    };

    @Override
    public Date convert(String str) {
        Date date = null;
        for (int i = 0; i < sdFormats.length; i++) {
            try {
                date = sdFormats[i].parse(str);
                break;
            } catch (Exception e) {
                continue;
            }
        }
        return date;
    }

}
