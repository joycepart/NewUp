package com.qluxstory.qingshe.home.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/6/14.
 */
public class SplashEntity extends BaseEntity {
    private String PicturesUrl;
    private String ServerCode;
    private String ServerName;

    public String getPicturesUrl() {
        return PicturesUrl;
    }

    public void setPicturesUrl(String picturesUrl) {
        PicturesUrl = picturesUrl;
    }

    public String getServerCode() {
        return ServerCode;
    }

    public void setServerCode(String serverCode) {
        ServerCode = serverCode;
    }

    public String getServerName() {
        return ServerName;
    }

    public void setServerName(String serverName) {
        ServerName = serverName;
    }


}
