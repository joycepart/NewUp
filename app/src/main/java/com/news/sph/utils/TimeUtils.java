package com.news.sph.utils;

/**
 * 随机获取时间戳
 */
public class TimeUtils {

    public static String getTime(){
        long t=System.currentTimeMillis();
        String st=(t+"").substring(0,11);
        return st;
    }
}
