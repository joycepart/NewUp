package com.news.sph.home.entity;

import com.news.sph.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/20.
 */
public class HomeRecommendEntity extends BaseEntity{
    /* 夺宝商品编号 */
    private String mSnaCode  ;
    /*夺宝批次编号*/
    private String mBatCode  ;
    /*夺宝商品备注*/
    private String mSnaRemark  ;
    /*夺宝期数*/
    private String mSnaTerm   ;
    /*夺宝商品标题*/
    private String mSnaTitle  ;
    /*开奖所需人次*/
    private String mSnaTotalCount  ;
    /*已售出人次 */
    private String mSnaSellOut  ;
    /*封面图片地址*/
    private String mPicUrl  ;
    /* 推荐*/
    private String mIsRecommend   ;

    public String getmBatCode() {
        return mBatCode;
    }

    public void setmBatCode(String mBatCode) {
        this.mBatCode = mBatCode;
    }

    public String getmIsRecommend() {
        return mIsRecommend;
    }

    public void setmIsRecommend(String mIsRecommend) {
        this.mIsRecommend = mIsRecommend;
    }

    public String getmPicUrl() {
        return mPicUrl;
    }

    public void setmPicUrl(String mPicUrl) {
        this.mPicUrl = mPicUrl;
    }

    public String getmSnaCode() {
        return mSnaCode;
    }

    public void setmSnaCode(String mSnaCode) {
        this.mSnaCode = mSnaCode;
    }

    public String getmSnaRemark() {
        return mSnaRemark;
    }

    public void setmSnaRemark(String mSnaRemark) {
        this.mSnaRemark = mSnaRemark;
    }

    public String getmSnaSellOut() {
        return mSnaSellOut;
    }

    public void setmSnaSellOut(String mSnaSellOut) {
        this.mSnaSellOut = mSnaSellOut;
    }

    public String getmSnaTerm() {
        return mSnaTerm;
    }

    public void setmSnaTerm(String mSnaTerm) {
        this.mSnaTerm = mSnaTerm;
    }

    public String getmSnaTitle() {
        return mSnaTitle;
    }

    public void setmSnaTitle(String mSnaTitle) {
        this.mSnaTitle = mSnaTitle;
    }

    public String getmSnaTotalCount() {
        return mSnaTotalCount;
    }

    public void setmSnaTotalCount(String mSnaTotalCount) {
        this.mSnaTotalCount = mSnaTotalCount;
    }



}
