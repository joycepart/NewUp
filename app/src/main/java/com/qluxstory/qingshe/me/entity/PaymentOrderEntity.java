package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/6/7.
 */
public class PaymentOrderEntity extends BaseEntity {

    /*
              手机号码
                */
    private String OrderManName;
    /*
              下单时间
                */
    private String OrderSingleTime;
    /*
              收货地址编号
                */
    private String ConsigneeCode;
    /*
              收货人
                */
    private String ConsigneeName;
    /*
              收货人手机
                */
    private String DeliveredMobile;
    /*
              省市区
                */
    private String ProvincialCity;
    /*
            订单类型 养护、闲置
                */
    private String OrderType;
    /*
              支付方式
                */
    private String ApplyType;
    /*
              预约时间"
                */
    private String TimeToAppointmen;
    /*
              使用的优惠券编号
                */
    private String UserCouponCode;
    /*
              商品编号
                */
    private String ComOnlyCode;
    /*
              商品名称
                */
    private String ComName;
    /*
              商品价格
                */
    private String ComPrice;
    /*
             总金额 【页面显示的最后的金额】
                */
    private String OrderMoney;
    /*
              下单个数
                */
    private String ComCount;
    /*
              商品成色
                */
    private String com_necklace;
    /*
              优惠券抵用金额
                */
    private String CouponPrice;

    /*
                 会员卡优惠
                   */
    private String MemberIDCoupon;
    /*
                 上门、快递服务费
                   */
    private String ServiceMoney;
    /*
                优惠券名称
                   */
    private String CouponRedeemName;
    /*
                     优惠券编号
                       */
    private String CouponRedeemCode;
    /*
满减【满金额】、抵用券抵用额
                           */
    private String CouponMoney;
    /*
满减【减金额】、直减金额、免费金额、购买服务优惠、抵用券付款额
                           */
    private String CouponMoneyEqual;
    /*
          打折数
                               */
    private String DiscountNumber;
    /*
          商品/服务小图
                               */
    private String app_show_pic;
    /*
          详细地址
                               */
    private String AddressInDetail;
    /*
          收货方式
                               */
    private String ConsigneeType;
    /*
          首单金额
                               */
    private String shoudanMoney;
    /*
         服务名称 专业 维护/清洗/修复
                               */
    private String ServerName;
    /*
          会员首次下单的包图片
                               */
    private String ServerKHImg;
    /*
          客服中心地址
                               */
    private String dis_cityAddress;
    /*
         客服中心名称
                              */
    private String sto_name;
    /*
         客服中心手机
                              */
    private String sto_phone;
    /*
            客服中心地址编号
                                 */
    private String ServerYJCode;
    private String CustomerNote;
    private String IntegralNum;
    private String IntegralMoney;
    /*
                   订单编号
                     */
    private String OldOrderNum;
    public String getOldOrderNum() {
        return OldOrderNum;
    }

    public void setOldOrderNum(String oldOrderNum) {
        OldOrderNum = oldOrderNum;
    }



    public String getCustomerNote() {
        return CustomerNote;
    }

    public void setCustomerNote(String customerNote) {
        CustomerNote = customerNote;
    }

    public String getIntegralNum() {
        return IntegralNum;
    }

    public void setIntegralNum(String integralNum) {
        IntegralNum = integralNum;
    }

    public String getIntegralMoney() {
        return IntegralMoney;
    }

    public void setIntegralMoney(String integralMoney) {
        IntegralMoney = integralMoney;
    }



    public String getAddressInDetail() {
        return AddressInDetail;
    }

    public void setAddressInDetail(String addressInDetail) {
        AddressInDetail = addressInDetail;
    }

    public String getApp_show_pic() {
        return app_show_pic;
    }

    public void setApp_show_pic(String app_show_pic) {
        this.app_show_pic = app_show_pic;
    }

    public String getApplyType() {
        return ApplyType;
    }

    public void setApplyType(String applyType) {
        ApplyType = applyType;
    }

    public String getCom_necklace() {
        return com_necklace;
    }

    public void setCom_necklace(String com_necklace) {
        this.com_necklace = com_necklace;
    }

    public String getComCount() {
        return ComCount;
    }

    public void setComCount(String comCount) {
        ComCount = comCount;
    }

    public String getComName() {
        return ComName;
    }

    public void setComName(String comName) {
        ComName = comName;
    }

    public String getComOnlyCode() {
        return ComOnlyCode;
    }

    public void setComOnlyCode(String comOnlyCode) {
        ComOnlyCode = comOnlyCode;
    }

    public String getComPrice() {
        return ComPrice;
    }

    public void setComPrice(String comPrice) {
        ComPrice = comPrice;
    }

    public String getConsigneeCode() {
        return ConsigneeCode;
    }

    public void setConsigneeCode(String consigneeCode) {
        ConsigneeCode = consigneeCode;
    }

    public String getConsigneeName() {
        return ConsigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        ConsigneeName = consigneeName;
    }

    public String getConsigneeType() {
        return ConsigneeType;
    }

    public void setConsigneeType(String consigneeType) {
        ConsigneeType = consigneeType;
    }

    public String getCouponMoney() {
        return CouponMoney;
    }

    public void setCouponMoney(String couponMoney) {
        CouponMoney = couponMoney;
    }

    public String getCouponMoneyEqual() {
        return CouponMoneyEqual;
    }

    public void setCouponMoneyEqual(String couponMoneyEqual) {
        CouponMoneyEqual = couponMoneyEqual;
    }

    public String getCouponPrice() {
        return CouponPrice;
    }

    public void setCouponPrice(String couponPrice) {
        CouponPrice = couponPrice;
    }

    public String getCouponRedeemCode() {
        return CouponRedeemCode;
    }

    public void setCouponRedeemCode(String couponRedeemCode) {
        CouponRedeemCode = couponRedeemCode;
    }

    public String getCouponRedeemName() {
        return CouponRedeemName;
    }

    public void setCouponRedeemName(String couponRedeemName) {
        CouponRedeemName = couponRedeemName;
    }

    public String getDeliveredMobile() {
        return DeliveredMobile;
    }

    public void setDeliveredMobile(String deliveredMobile) {
        DeliveredMobile = deliveredMobile;
    }

    public String getDis_cityAddress() {
        return dis_cityAddress;
    }

    public void setDis_cityAddress(String dis_cityAddress) {
        this.dis_cityAddress = dis_cityAddress;
    }

    public String getDiscountNumber() {
        return DiscountNumber;
    }

    public void setDiscountNumber(String discountNumber) {
        DiscountNumber = discountNumber;
    }

    public String getMemberIDCoupon() {
        return MemberIDCoupon;
    }

    public void setMemberIDCoupon(String memberIDCoupon) {
        MemberIDCoupon = memberIDCoupon;
    }


    public String getOrderManName() {
        return OrderManName;
    }

    public void setOrderManName(String orderManName) {
        OrderManName = orderManName;
    }

    public String getOrderMoney() {
        return OrderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        OrderMoney = orderMoney;
    }

    public String getOrderSingleTime() {
        return OrderSingleTime;
    }

    public void setOrderSingleTime(String orderSingleTime) {
        OrderSingleTime = orderSingleTime;
    }

    public String getOrderType() {
        return OrderType;
    }

    public void setOrderType(String orderType) {
        OrderType = orderType;
    }

    public String getProvincialCity() {
        return ProvincialCity;
    }

    public void setProvincialCity(String provincialCity) {
        ProvincialCity = provincialCity;
    }

    public String getServerKHImg() {
        return ServerKHImg;
    }

    public void setServerKHImg(String serverKHImg) {
        ServerKHImg = serverKHImg;
    }

    public String getServerName() {
        return ServerName;
    }

    public void setServerName(String serverName) {
        ServerName = serverName;
    }

    public String getServerYJCode() {
        return ServerYJCode;
    }

    public void setServerYJCode(String serverYJCode) {
        ServerYJCode = serverYJCode;
    }

    public String getServiceMoney() {
        return ServiceMoney;
    }

    public void setServiceMoney(String serviceMoney) {
        ServiceMoney = serviceMoney;
    }

    public String getShoudanMoney() {
        return shoudanMoney;
    }

    public void setShoudanMoney(String shoudanMoney) {
        this.shoudanMoney = shoudanMoney;
    }

    public String getSto_name() {
        return sto_name;
    }

    public void setSto_name(String sto_name) {
        this.sto_name = sto_name;
    }

    public String getSto_phone() {
        return sto_phone;
    }

    public void setSto_phone(String sto_phone) {
        this.sto_phone = sto_phone;
    }

    public String getTimeToAppointmen() {
        return TimeToAppointmen;
    }

    public void setTimeToAppointmen(String timeToAppointmen) {
        TimeToAppointmen = timeToAppointmen;
    }

    public String getUserCouponCode() {
        return UserCouponCode;
    }

    public void setUserCouponCode(String userCouponCode) {
        UserCouponCode = userCouponCode;
    }




}
