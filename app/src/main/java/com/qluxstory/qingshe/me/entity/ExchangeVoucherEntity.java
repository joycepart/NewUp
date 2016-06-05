package com.qluxstory.qingshe.me.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lenovo on 2016/6/3.
 */
public class ExchangeVoucherEntity {
    /**
     * 优惠券兑换码
     */
    @SerializedName("CouponRedeemCode")
    private String couponRedeemCode;
    /**
     * 优惠劵金额
     */
    @SerializedName("CouponMoney")
    private String couponmoney;
    /**
     * 使用范围
     */
    @SerializedName("CouponRangeOfUse")
    private String couponRangeOfUse;
    /**
     * 优惠券种类 打折、满减、免费、直减
     */
    @SerializedName("CouponType")
    private String couponType;
    /**
     * 过期时间
     */
    @SerializedName("CouponExpirationTime")
    private String couponExpirationTime;
    /**
     * 是否使用  0未使用/1已使用
     */
    @SerializedName("IsToUse")
    private String istouse;
    /**
     * 是否过期‘’DataOn’’未过期/’DataOff’提示已过期
     */
    @SerializedName("Isuser")
    private String isoverdue;


    public String getCouponExpirationTime() {
        return couponExpirationTime;
    }

    public void setCouponExpirationTime(String couponExpirationTime) {
        this.couponExpirationTime = couponExpirationTime;
    }

    public String getCouponmoney() {
        return couponmoney;
    }

    public void setCouponmoney(String couponmoney) {
        this.couponmoney = couponmoney;
    }

    public String getCouponRangeOfUse() {
        return couponRangeOfUse;
    }

    public void setCouponRangeOfUse(String couponRangeOfUse) {
        this.couponRangeOfUse = couponRangeOfUse;
    }

    public String getCouponRedeemCode() {
        return couponRedeemCode;
    }

    public void setCouponRedeemCode(String couponRedeemCode) {
        this.couponRedeemCode = couponRedeemCode;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getIsoverdue() {
        return isoverdue;
    }

    public void setIsoverdue(String isoverdue) {
        this.isoverdue = isoverdue;
    }

    public String getIstouse() {
        return istouse;
    }

    public void setIstouse(String istouse) {
        this.istouse = istouse;
    }


}
