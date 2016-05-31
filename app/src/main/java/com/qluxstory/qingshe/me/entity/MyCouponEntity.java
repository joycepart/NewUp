package com.qluxstory.qingshe.me.entity;

import com.google.gson.annotations.SerializedName;
import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/20.
 */
public class MyCouponEntity extends BaseEntity{
    /**
     * 优惠券兑换码
     */
    @SerializedName("CouponRedeemCode")
    private String couponRedeemCode;
    /**
     * 满减【满金额】
     */
    @SerializedName("CouponMoney")
    private String couponmoney;
    /**
     * 满减【减金额】、直减金额、免费金额
     */
    @SerializedName("CouponMoneyEqual")
    private String CouponMoneyEqual;
    /**
     * 属性 服务券，优惠券
     */
    @SerializedName("CouponRangeOfUse")
    private String couponRangeOfUse;
    /**
     * 优惠券种类 打折、满减、免费、直减
     */
    @SerializedName("CouponType")
    private String couponType;
    /**
     * 打折数(7折、7.5折) 种类是打折时使用
     */
    @SerializedName("DiscountNumber")
    private String DiscountNumber;
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

    /**
     * 是否注册券
     */
    @SerializedName("CouponRegister")
    private String CouponRegister;
    /**
     * 优惠券名称
     */
    @SerializedName("CouponRedeemName")
    private String CouponRedeemName;
    /**
     * H5显示路径(只有服务券有url)
     */
    @SerializedName("H5Url")
    private String H5Url;

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

    public String getCouponMoneyEqual() {
        return CouponMoneyEqual;
    }

    public void setCouponMoneyEqual(String couponMoneyEqual) {
        this.CouponMoneyEqual = couponMoneyEqual;
    }

    public String getCouponRangeOfUse() {
        return couponRangeOfUse;
    }

    public void setCouponRangeOfUse(String couponRangeOfUse) {
        this.couponRangeOfUse = couponRangeOfUse;
    }

    public String getmCouponRedeemCode() {
        return couponRedeemCode;
    }

    public void setmCouponRedeemCode(String couponRedeemCode) {
        this.couponRedeemCode = couponRedeemCode;
    }

    public String getCouponRedeemName() {
        return CouponRedeemName;
    }

    public void setCouponRedeemName(String couponRedeemName) {
        this.CouponRedeemName = couponRedeemName;
    }

    public String getCouponRegister() {
        return CouponRegister;
    }

    public void setCouponRegister(String couponRegister) {
        this.CouponRegister = couponRegister;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getDiscountNumber() {
        return DiscountNumber;
    }

    public void setDiscountNumber(String discountNumber) {
        this.DiscountNumber = discountNumber;
    }

    public String getH5Url() {
        return H5Url;
    }

    public void setH5Url(String h5Url) {
        this.H5Url = h5Url;
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
