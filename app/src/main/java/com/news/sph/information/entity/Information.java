package com.news.sph.information.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 消息列表
 */
public class Information implements Serializable {
    public int[] getmImgPic() {
        return mImgPic;
    }

    public void setmImgPic(int[] mImgPic) {
        this.mImgPic = mImgPic;
    }

    /**
     * 消息图片
     */
    private int[] mImgPic;
    public String[] getmStr1() {
        return mStr1;
    }

    public void setmStr1(String[] mStr1) {
        this.mStr1 = mStr1;
    }

    /**
     * 消息标题
     */
    private String[] mStr1;

    public String[] getmStr2() {
        return mStr2;
    }

    public void setmStr2(String[] mStr2) {
        this.mStr2 = mStr2;
    }

    /**
     * 消息内容
     */
    private String[] mStr2;
}
