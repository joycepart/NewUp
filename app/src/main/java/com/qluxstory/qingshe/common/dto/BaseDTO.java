package com.qluxstory.qingshe.common.dto;

import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.common.utils.SecurityUtils;

import java.io.Serializable;

/**
 * DTO的基类
 */
public class BaseDTO implements Serializable {
    private String membermob;
    private String timestamp;
    private String time ;
    private String sign;
    private int pageSize ;
    private int pageIndex;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMembermob() {
        return membermob;
    }

    public void setMembermob(String membermob) {
        this.membermob = membermob;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
//        String time = TimeUtils.getSignTime();
        this.sign = SecurityUtils.md5(sign);
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
