package com.qluxstory.qingshe.me.entity;

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

    /**
     * 用户ID
     */
    @SerializedName("user_id")
    private int mUserId;
    /**
     * 用户手机号
     */
    @SerializedName("user_mobile")
    private String mUserMobile;
    /**
     * 用户名
     */
    @SerializedName("user_name")
    private String mUserName;

    /**
     * 头像
     */
    @SerializedName("picture_path")
    private String mPictruePath;
    /**
     * 判断是否登录；
     */
    private Boolean flag = false;

    public String getmPictruePath() {
        return mPictruePath;
    }

    public void setmPictruePath(String mPictruePath) {
        this.mPictruePath = mPictruePath;
    }

    public int getmUserId() {
        return mUserId;
    }

    public void setmUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public String getmUserMobile() {
        return mUserMobile;
    }

    public void setmUserMobile(String mUserMobile) {
        this.mUserMobile = mUserMobile;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }



}
