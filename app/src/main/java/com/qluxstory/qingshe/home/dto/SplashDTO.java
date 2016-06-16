package com.qluxstory.qingshe.home.dto;

import com.qluxstory.qingshe.common.dto.BaseDTO;

/**
 * Created by lenovo on 2016/6/14.
 */
public class SplashDTO extends BaseDTO {
    private String devicetype;
    private String pwidth;
    private String pheight;

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public String getPwidth() {
        return pwidth;
    }

    public void setPwidth(String pwidth) {
        this.pwidth = pwidth;
    }

    public String getPheight() {
        return pheight;
    }

    public void setPheight(String pheight) {
        this.pheight = pheight;
    }


}
