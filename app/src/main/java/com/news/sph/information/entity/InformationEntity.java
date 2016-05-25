package com.news.sph.information.entity;

import com.news.sph.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * Created by lenovo on 2016/5/20.
 */
public class InformationEntity extends  BaseEntity{
    /* 编号 */
    private String mNewsCode;
    /* 新闻大标题 */
    private String mNewsBigTitle;
    /* 新闻小标题 */
    private String mNewsSmallTitle;
    /*新闻图片地址*/
    private String mNewsPicUrl;
    /*新闻html地址*/
    private String mNewsHtmlUrl;
    /*添加时间*/
    private String mNewsAddTime;

    public String getmNewsAddTime() {
        return mNewsAddTime;
    }

    public void setmNewsAddTime(String mNewsAddTime) {
        this.mNewsAddTime = mNewsAddTime;
    }

    public String getmNewsBigTitle() {
        return mNewsBigTitle;
    }

    public void setmNewsBigTitle(String mNewsBigTitle) {
        this.mNewsBigTitle = mNewsBigTitle;
    }

    public String getmNewsCode() {
        return mNewsCode;
    }

    public void setmNewsCode(String mNewsCode) {
        this.mNewsCode = mNewsCode;
    }

    public String getmNewsHtmlUrl() {
        return mNewsHtmlUrl;
    }

    public void setmNewsHtmlUrl(String mNewsHtmlUrl) {
        this.mNewsHtmlUrl = mNewsHtmlUrl;
    }

    public String getmNewsPicUrl() {
        return mNewsPicUrl;
    }

    public void setmNewsPicUrl(String mNewsPicUrl) {
        this.mNewsPicUrl = mNewsPicUrl;
    }

    public String getmNewsSmallTitle() {
        return mNewsSmallTitle;
    }

    public void setmNewsSmallTitle(String mNewsSmallTitle) {
        this.mNewsSmallTitle = mNewsSmallTitle;
    }


}
