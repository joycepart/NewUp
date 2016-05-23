package com.news.sph.me.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 用户信息
        */
public class User {
    /**
     * 完善资料
     */
    public static final int FS_COMPLETED = 1;
    /**
     * 没有完善资料
     */
    public static final int FS_NO_COMPLETED = 0;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    /**
     * 用户ID
     */
    @SerializedName("user_id")
    private int userId;
    /**
     * 用户手机号
     */
    @SerializedName("user_mobile")
    private String userMobile;
    /**
     * 用户名
     */
    @SerializedName("user_name")
    private String userName;

    public String getPictruePath() {
        return pictruePath;
    }

    public void setPictruePath(String pictruePath) {
        this.pictruePath = pictruePath;
    }

    /**
     * 头像
     */
    @SerializedName("picture_path")
    private String pictruePath;

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    /**
     * 判断是否登录；
     */
    private Boolean flag = false;

}
