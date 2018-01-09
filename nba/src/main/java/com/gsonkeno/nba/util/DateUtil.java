package com.gsonkeno.nba.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gaosong on 2018-01-08
 */
public class DateUtil {

    public static String getNowDay(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
