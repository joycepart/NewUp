package com.news.sph.me.dto;

import com.news.sph.common.dto.BaseDTO;

/**
 * Created by lenovo on 2016/5/18.
 */
public class WithdrawalsDTO extends BaseDTO {
    /**
     * 提现金额
     */
    private String presentmoney;
    /**
     * 支付宝、银行卡、微信等账号
     */
    private String accountnumber;
    /**
     * 类型 支付宝、银行卡、微信等
     */
    private String accounttype;
    /**
     * 预留手机号码 银行需要【没有值时传’’】
     */
    private String reservemobile;
    /**
     * 持卡人姓名 银行需要【没有值时传’’】
     */
    private String reservename;

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public String getPresentmoney() {
        return presentmoney;
    }

    public void setPresentmoney(String presentmoney) {
        this.presentmoney = presentmoney;
    }

    public String getReservemobile() {
        return reservemobile;
    }

    public void setReservemobile(String reservemobile) {
        this.reservemobile = reservemobile;
    }

    public String getReservename() {
        return reservename;
    }

    public void setReservename(String reservename) {
        this.reservename = reservename;
    }



}
