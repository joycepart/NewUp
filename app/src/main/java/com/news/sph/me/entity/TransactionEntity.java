package com.news.sph.me.entity;

import com.news.sph.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/20.
 */
public class TransactionEntity extends BaseEntity{
    /*
               交易ID，转到详情
                */
    private String mID;
    /*
           商品名称
            */
    private String mComName;
    /*
           商品金额
            */
    private String mComPrice;
    /*
           佣金
            */
    private String mComCommission;
    /*
           收益金额
            */
    private String mComIncomeAmount;
    /*
          交易完成时间(HH:mm:ss)
            */
    private String mComTradingTime;
    /*
          交易完成时间(yyyy-MM-dd)
            */
    private String mComTradingData;
    /*
           盈利[+]（1）,消费[-](0)
            */
    private String mComEarOrCon;
    /*
         数据来源（轻奢生活、京东、天猫…）
            */
    private String mComDataSources;
    /*
          明细处理方(轻奢生活、京东、天猫…)
            */
    private String mComDetailProParty;
    /*
           新用户名
            */
    private String mCouponMoney;
    /*
           代金券金额
            */
    private String mTransactionType;
    /*
              上门服务费
                */
    private String mServiceMoney;

    public String getmComCommission() {
        return mComCommission;
    }

    public void setmComCommission(String mComCommission) {
        this.mComCommission = mComCommission;
    }

    public String getmComDataSources() {
        return mComDataSources;
    }

    public void setmComDataSources(String mComDataSources) {
        this.mComDataSources = mComDataSources;
    }

    public String getmComDetailProParty() {
        return mComDetailProParty;
    }

    public void setmComDetailProParty(String mComDetailProParty) {
        this.mComDetailProParty = mComDetailProParty;
    }

    public String getmComEarOrCon() {
        return mComEarOrCon;
    }

    public void setmComEarOrCon(String mComEarOrCon) {
        this.mComEarOrCon = mComEarOrCon;
    }

    public String getmComIncomeAmount() {
        return mComIncomeAmount;
    }

    public void setmComIncomeAmount(String mComIncomeAmount) {
        this.mComIncomeAmount = mComIncomeAmount;
    }

    public String getmComName() {
        return mComName;
    }

    public void setmComName(String mComName) {
        this.mComName = mComName;
    }

    public String getmComPrice() {
        return mComPrice;
    }

    public void setmComPrice(String mComPrice) {
        this.mComPrice = mComPrice;
    }

    public String getmComTradingData() {
        return mComTradingData;
    }

    public void setmComTradingData(String mComTradingData) {
        this.mComTradingData = mComTradingData;
    }

    public String getmComTradingTime() {
        return mComTradingTime;
    }

    public void setmComTradingTime(String mComTradingTime) {
        this.mComTradingTime = mComTradingTime;
    }

    public String getmCouponMoney() {
        return mCouponMoney;
    }

    public void setmCouponMoney(String mCouponMoney) {
        this.mCouponMoney = mCouponMoney;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmServiceMoney() {
        return mServiceMoney;
    }

    public void setmServiceMoney(String mServiceMoney) {
        this.mServiceMoney = mServiceMoney;
    }

    public String getmTransactionType() {
        return mTransactionType;
    }

    public void setmTransactionType(String mTransactionType) {
        this.mTransactionType = mTransactionType;
    }


}
