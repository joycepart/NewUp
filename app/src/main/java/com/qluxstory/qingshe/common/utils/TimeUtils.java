package com.qluxstory.qingshe.common.utils;



import java.util.Random;

/**
 * 随机获取时间戳
 */
public class TimeUtils {

    public static String getSignTime(){
        long t=System.currentTimeMillis();
        String st=(t+"").substring(0,11).trim();
        return st;
    }

    //timeStamp时间戳
    public static String genTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    //nonceStr随机数
    public static String genNonceStr() {
        Random random = new Random();
        return SecurityUtils.MD5(String.valueOf(random.nextInt(10000)));
    }





}
