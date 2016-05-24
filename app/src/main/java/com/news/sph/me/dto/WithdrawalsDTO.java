package com.news.sph.me.dto;

import com.news.sph.common.dto.BaseDTO;

/**
 * Created by lenovo on 2016/5/18.
 */
public class WithdrawalsDTO extends BaseDTO {
    /**
     * 提现金额
     */
    private String mPresentmoney;
    /**
     * 支付宝、银行卡、微信等账号
     */
    private String mAccountnumber;
    /**
     * 类型 支付宝、银行卡、微信等
     */
    private String mAccounttype;
    /**
     * 预留手机号码 银行需要【没有值时传’’】
     */
    private String mReservemobile;
    /**
     * 持卡人姓名 银行需要【没有值时传’’】
     */
    private String mReservename;

    public String getmAccountnumber() {
        return mAccountnumber;
    }

    public void setmAccountnumber(String mAccountnumber) {
        this.mAccountnumber = mAccountnumber;
    }

    public String getmAccounttype() {
        return mAccounttype;
    }

    public void setmAccounttype(String mAccounttype) {
        this.mAccounttype = mAccounttype;
    }

    public String getmPresentmoney() {
        return mPresentmoney;
    }

    public void setmPresentmoney(String mPresentmoney) {
        this.mPresentmoney = mPresentmoney;
    }

    public String getmReservemobile() {
        return mReservemobile;
    }

    public void setmReservemobile(String mReservemobile) {
        this.mReservemobile = mReservemobile;
    }

    public String getmReservename() {
        return mReservename;
    }

    public void setmReservename(String mReservename) {
        this.mReservename = mReservename;
    }



}
