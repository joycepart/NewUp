package com.news.sph.me.entity;

import com.news.sph.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/20.
 */
public class MyCouponEntity extends BaseEntity{
    /**
     * 优惠券兑换码
     */
    private String mCouponRedeemCode;
    /**
     * 满减【满金额】
     */
    private String mCouponmoney;
    /**
     * 满减【减金额】、直减金额、免费金额
     */
    private String mCouponMoneyEqual;
    /**
     * 属性 服务券，优惠券
     */
    private String mCouponRangeOfUse;
    /**
     * 优惠券种类 打折、满减、免费、直减
     */
    private String mCouponType;
    /**
     * 打折数(7折、7.5折) 种类是打折时使用
     */
    private String mDiscountNumber;
    /**
     * 过期时间
     */
    private String mCouponExpirationTime;
    /**
     * 是否使用  0未使用/1已使用
     */
    private String mIstouse;
    /**
     * 是否过期‘’DataOn’’未过期/’DataOff’提示已过期
     */
    private String mIsoverdue;

    /**
     * 是否注册券
     */
    private String mCouponRegister;
    /**
     * 优惠券名称
     */
    private String mCouponRedeemName;
    /**
     * H5显示路径(只有服务券有url)
     */
    private String mH5Url;

    public String getmCouponExpirationTime() {
        return mCouponExpirationTime;
    }

    public void setmCouponExpirationTime(String mCouponExpirationTime) {
        this.mCouponExpirationTime = mCouponExpirationTime;
    }

    public String getmCouponmoney() {
        return mCouponmoney;
    }

    public void setmCouponmoney(String mCouponmoney) {
        this.mCouponmoney = mCouponmoney;
    }

    public String getmCouponMoneyEqual() {
        return mCouponMoneyEqual;
    }

    public void setmCouponMoneyEqual(String mCouponMoneyEqual) {
        this.mCouponMoneyEqual = mCouponMoneyEqual;
    }

    public String getmCouponRangeOfUse() {
        return mCouponRangeOfUse;
    }

    public void setmCouponRangeOfUse(String mCouponRangeOfUse) {
        this.mCouponRangeOfUse = mCouponRangeOfUse;
    }

    public String getmCouponRedeemCode() {
        return mCouponRedeemCode;
    }

    public void setmCouponRedeemCode(String mCouponRedeemCode) {
        this.mCouponRedeemCode = mCouponRedeemCode;
    }

    public String getmCouponRedeemName() {
        return mCouponRedeemName;
    }

    public void setmCouponRedeemName(String mCouponRedeemName) {
        this.mCouponRedeemName = mCouponRedeemName;
    }

    public String getmCouponRegister() {
        return mCouponRegister;
    }

    public void setmCouponRegister(String mCouponRegister) {
        this.mCouponRegister = mCouponRegister;
    }

    public String getmCouponType() {
        return mCouponType;
    }

    public void setmCouponType(String mCouponType) {
        this.mCouponType = mCouponType;
    }

    public String getmDiscountNumber() {
        return mDiscountNumber;
    }

    public void setmDiscountNumber(String mDiscountNumber) {
        this.mDiscountNumber = mDiscountNumber;
    }

    public String getmH5Url() {
        return mH5Url;
    }

    public void setmH5Url(String mH5Url) {
        this.mH5Url = mH5Url;
    }

    public String getmIsoverdue() {
        return mIsoverdue;
    }

    public void setmIsoverdue(String mIsoverdue) {
        this.mIsoverdue = mIsoverdue;
    }

    public String getmIstouse() {
        return mIstouse;
    }

    public void setmIstouse(String mIstouse) {
        this.mIstouse = mIstouse;
    }



}
