package com.qluxstory.qingshe.issue.entity;

import java.util.List;

/**
 * Created by lenovo on 2016/6/5.
 */
public class IssueProduct  {
    public List<IssueProduct> getList() {
        return list;
    }

    public void setList(List<IssueProduct> list) {
        this.list = list;
    }

    List<IssueProduct> list;

    /*
                     夺宝商品编号
                          */
    private String mSnaCode;
    /*
                     批次编号
                          */
    private String mBatCode;
    /*
                     夺宝期数
                          */
    private String mSnaTerm;
    /*
                    商品标题
                          */
    private String mSnaTitle;
    /*
                    开奖所需人次
                          */
    private String mTotalCount;
    /*
                     商品备注
                          */
    private String mSnaRemark;
    /*
                    已参与人次
                          */
    private String mSnaOut;
    /*
                             开始时间
                                   */
    private String mSeginDate;
    /*
                           封面图片地址
                                 */
    private String mPicUrl;
    /*
                               订单号
                                     */
    private String mRecCode;
    /*
                                   价格
                                         */
    private String mBalance;

    public String getmBalance() {
        return mBalance;
    }

    public void setmBalance(String mBalance) {
        this.mBalance = mBalance;
    }



    public String getmRecCode() {
        return mRecCode;
    }

    public void setmRecCode(String mRecCode) {
        this.mRecCode = mRecCode;
    }



    public String getmPicUrl() {
        return mPicUrl;
    }

    public void setmPicUrl(String mPicUrl) {
        this.mPicUrl = mPicUrl;
    }




    public String getmBatCode() {
        return mBatCode;
    }

    public void setmBatCode(String mBatCode) {
        this.mBatCode = mBatCode;
    }

    public String getmSeginDate() {
        return mSeginDate;
    }

    public void setmSeginDate(String mSeginDate) {
        this.mSeginDate = mSeginDate;
    }

    public String getmSnaCode() {
        return mSnaCode;
    }

    public void setmSnaCode(String mSnaCode) {
        this.mSnaCode = mSnaCode;
    }

    public String getmSnaOut() {
        return mSnaOut;
    }

    public void setmSnaOut(String mSnaOut) {
        this.mSnaOut = mSnaOut;
    }

    public String getmSnaRemark() {
        return mSnaRemark;
    }

    public void setmSnaRemark(String mSnaRemark) {
        this.mSnaRemark = mSnaRemark;
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

    public String getmTotalCount() {
        return mTotalCount;
    }

    public void setmTotalCount(String mTotalCount) {
        this.mTotalCount = mTotalCount;
    }






}
