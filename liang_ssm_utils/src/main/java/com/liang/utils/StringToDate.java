package com.liang.utils;




import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liang
 * @create 2020/2/18 13:22
 */
public class StringToDate implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        if (s == null){
            throw new RuntimeException("请你传入参数");
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return df.parse(s);
        } catch (Exception e) {
            throw new RuntimeException("数据转换类型异常");
        }
    }
}
