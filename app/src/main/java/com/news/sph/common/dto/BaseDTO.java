package com.news.sph.common.dto;

import com.google.gson.annotations.SerializedName;
import com.news.sph.common.utils.SecurityUtils;
import com.news.sph.common.utils.TimeUtils;

import java.io.Serializable;

/**
 * DTO的基类
 */
public class BaseDTO implements Serializable {
    private String membermob;
    private String timestamp;
    private String sign;
    @SerializedName("page_size")
    private int pageSize;
    @SerializedName("page_index")
    private int pageIndex;

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
        String time = TimeUtils.getTime();
        setTimestamp(time);
        this.sign = SecurityUtils.md5(time + sign);
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
