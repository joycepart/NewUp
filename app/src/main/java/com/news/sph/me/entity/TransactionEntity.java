package com.news.sph.me.entity;

/**
 * Created by lenovo on 2016/5/20.
 */
public class TransactionEntity {
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComPrice() {
        return comPrice;
    }

    public void setComPrice(String comPrice) {
        this.comPrice = comPrice;
    }

    public String getComCommission() {
        return comCommission;
    }

    public void setComCommission(String comCommission) {
        this.comCommission = comCommission;
    }

    public String getComIncomeAmount() {
        return comIncomeAmount;
    }

    public void setComIncomeAmount(String comIncomeAmount) {
        this.comIncomeAmount = comIncomeAmount;
    }

    public String getComTradingTime() {
        return comTradingTime;
    }

    public void setComTradingTime(String comTradingTime) {
        this.comTradingTime = comTradingTime;
    }

    public String getComTradingData() {
        return comTradingData;
    }

    public void setComTradingData(String comTradingData) {
        this.comTradingData = comTradingData;
    }

    public String getComEarOrCon() {
        return comEarOrCon;
    }

    public void setComEarOrCon(String comEarOrCon) {
        this.comEarOrCon = comEarOrCon;
    }

    public String getComDataSources() {
        return comDataSources;
    }

    public void setComDataSources(String comDataSources) {
        this.comDataSources = comDataSources;
    }

    public String getComDetailProParty() {
        return comDetailProParty;
    }

    public void setComDetailProParty(String comDetailProParty) {
        this.comDetailProParty = comDetailProParty;
    }

    public String getCouponMoney() {
        return CouponMoney;
    }

    public void setCouponMoney(String couponMoney) {
        CouponMoney = couponMoney;
    }

    public String getTransactionType() {
        return TransactionType;
    }

    public void setTransactionType(String transactionType) {
        TransactionType = transactionType;
    }

    public String getServiceMoney() {
        return ServiceMoney;
    }

    public void setServiceMoney(String serviceMoney) {
        ServiceMoney = serviceMoney;
    }

    /*
               交易ID，转到详情
                */
    private String ID;
    /*
           商品名称
            */
    private String comName;
    /*
           商品金额
            */
    private String comPrice;
    /*
           佣金
            */
    private String comCommission;
    /*
           收益金额
            */
    private String comIncomeAmount;
    /*
          交易完成时间(HH:mm:ss)
            */
    private String comTradingTime;
    /*
          交易完成时间(yyyy-MM-dd)
            */
    private String comTradingData;
    /*
           盈利[+]（1）,消费[-](0)
            */
    private String comEarOrCon;
    /*
         数据来源（轻奢生活、京东、天猫…）
            */
    private String comDataSources;
    /*
          明细处理方(轻奢生活、京东、天猫…)
            */
    private String comDetailProParty;
    /*
           新用户名
            */
    private String CouponMoney;
    /*
           代金券金额
            */
    private String TransactionType;
    /*
          上门服务费
            */
    private String ServiceMoney;
}
