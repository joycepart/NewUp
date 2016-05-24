package com.news.sph.issue.entity;

/**
 * Created by lenovo on 2016/5/20.
 */
public class WinningEntity {
    /*
                     批次号
                          */
    private String mBatCode  ;
    /*
                    夺宝商品号
                       */
    private String mSnaCode  ;
    /*
                  标题
                       */
    private String mSnaTitle  ;
    /*
                   期数
                       */
    private String mSnaTerm  ;
    /*
                        幸运用户
                           */
    private String mSnaLuckyPeople  ;

    public String getmBatCode() {
        return mBatCode;
    }

    public void setmBatCode(String mBatCode) {
        this.mBatCode = mBatCode;
    }

    public String getmSnaCode() {
        return mSnaCode;
    }

    public void setmSnaCode(String mSnaCode) {
        this.mSnaCode = mSnaCode;
    }

    public String getmSnaLuckyPeople() {
        return mSnaLuckyPeople;
    }

    public void setmSnaLuckyPeople(String mSnaLuckyPeople) {
        this.mSnaLuckyPeople = mSnaLuckyPeople;
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


}
