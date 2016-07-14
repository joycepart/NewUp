package com.qluxstory.qingshe.common.utils;



import java.text.SimpleDateFormat;
import java.util.Date;
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

    // 并用分割符把时间分成时间数组
    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14-16-09-00"）
     *
     * @param time
     * @return
     */
    public static String times(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }

    // 时间的小时时间
    public static String timesHour(String time) {
        String times = times(time).substring(11, 13);
        return times;

    }

    // 时间的分时间
    public static String timesMin(String time) {
        String times = times(time).substring(14, 16);
        return times;

    }



}
