package com.liang.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liang
 * @create 2020/2/24 17:45
 */
public class DateUtils {
    //日期装换成字符串
    public static String date2String(Date date,String patt){
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String format = sdf.format(date);
        return format;
    }
    //字符串转换成日期
    public static Date string2Date(Date date,String patt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date parse = sdf.parse(patt);
        return parse;
    }
}
