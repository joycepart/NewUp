package com.qluxstory.qingshe.me.entity;

import com.google.gson.annotations.SerializedName;

/**

 */
public class LoginEntity {
    /***
     * 用户手机号码
     */
    private String membermobile;
    /**
     * 用户名
     */
    private String membername;
    /**
     * 头像路径
     */
    private String memberHeadimg;
    @SerializedName("RongyunToken")
    private String token;

    public String getMembermobile() {
        return membermobile;
    }

    public void setMembermobile(String membermobile) {
        this.membermobile = membermobile;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public String getMemberHeadimg() {
        return memberHeadimg;
    }

    public void setMemberHeadimg(String memberHeadimg) {
        this.memberHeadimg = memberHeadimg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
