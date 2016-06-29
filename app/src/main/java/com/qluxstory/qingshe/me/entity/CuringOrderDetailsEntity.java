package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/30.
 */
public class CuringOrderDetailsEntity  extends BaseEntity {
    /*
              订单编号
                */
    private String OrderNum;
    /*
              状态
                */
    private String OrderState;
    /*
              商品编号
                */
    private String ComOnlyCode;
    /*
              商品名称
                */
    private String ComName;
    /*
              单价
                */
    private String ComPrice;
    /*
              总金额
                */
    private String OrderMoney;
    /*
              封面图片
                */
    private String com_cover_pic;
    /*
             数量
                */
    private String ComCount;
    /*
              下单时间
                */
    private String OrderSingleTime;
    /*
              会员卡优惠
                */
    private String MemberIDCoupon;
    /*
              优惠券
                */
    private String CouponPrice;
    /*
              真实金额
                */
    private String TrueMoney;
    /*
              收货方式
                */
    private String ConsigneeType;
    /*
              收货地址编号
                */
    private String ConsigneeCode;
    /*
              收货人姓名
                */
    private String ConsigneeName;
    /*
              收货人手机
                */
    private String DeliveredMobile;
    /*
              省市
                */
    private String ProvincialCity;
    /*
              详细地址
                */
    private String AddressInDetail;

    /*
                 商品成色
                   */
    private String com_necklace;
    /*
                 服务点
                   */
    private String sto_name;
    /*
                 寄送地址
                   */
    private String dis_cityAddress;
    /*
                     寄送电话
                       */
    private String sto_phone;
    /*

                           */
    private String isovertime;
    /*

                           */
    private String app_show_pic;
    /*

                               */
    private String ServerKHImg;
    /*

                                   */
    private String IntegralNum;
    /*

                                  */
    private String ExpressDeliverName;
    /*

                                     */
    private String ExpressDeliverCode;

    public String getExpressDeliverName() {
        return ExpressDeliverName;
    }

    public void setExpressDeliverName(String expressDeliverName) {
        ExpressDeliverName = expressDeliverName;
    }

    public String getExpressDeliverCode() {
        return ExpressDeliverCode;
    }

    public void setExpressDeliverCode(String expressDeliverCode) {
        ExpressDeliverCode = expressDeliverCode;
    }



    public String getIntegralNum() {
        return IntegralNum;
    }

    public void setIntegralNum(String integralNum) {
        IntegralNum = integralNum;
    }



    public String getApp_show_pic() {
        return app_show_pic;
    }

    public void setApp_show_pic(String app_show_pic) {
        this.app_show_pic = app_show_pic;
    }

    public String getServerKHImg() {
        return ServerKHImg;
    }

    public void setServerKHImg(String serverKHImg) {
        ServerKHImg = serverKHImg;
    }



    public String getIsovertime() {
        return isovertime;
    }

    public void setIsovertime(String isovertime) {
        this.isovertime = isovertime;
    }



    public String getDis_cityAddress() {
        return dis_cityAddress;
    }

    public void setDis_cityAddress(String dis_cityAddress) {
        this.dis_cityAddress = dis_cityAddress;
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



    public String getAddressInDetail() {
        return AddressInDetail;
    }

    public void setAddressInDetail(String addressInDetail) {
        AddressInDetail = addressInDetail;
    }

    public String getCom_cover_pic() {
        return com_cover_pic;
    }

    public void setCom_cover_pic(String com_cover_pic) {
        this.com_cover_pic = com_cover_pic;
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

    public String getCouponPrice() {
        return CouponPrice;
    }

    public void setCouponPrice(String couponPrice) {
        CouponPrice = couponPrice;
    }

    public String getDeliveredMobile() {
        return DeliveredMobile;
    }

    public void setDeliveredMobile(String deliveredMobile) {
        DeliveredMobile = deliveredMobile;
    }

    public String getMemberIDCoupon() {
        return MemberIDCoupon;
    }

    public void setMemberIDCoupon(String memberIDCoupon) {
        MemberIDCoupon = memberIDCoupon;
    }

    public String getOrderMoney() {
        return OrderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        OrderMoney = orderMoney;
    }

    public String getOrderNum() {
        return OrderNum;
    }

    public void setOrderNum(String orderNum) {
        OrderNum = orderNum;
    }

    public String getOrderSingleTime() {
        return OrderSingleTime;
    }

    public void setOrderSingleTime(String orderSingleTime) {
        OrderSingleTime = orderSingleTime;
    }

    public String getOrderState() {
        return OrderState;
    }

    public void setOrderState(String orderState) {
        OrderState = orderState;
    }

    public String getProvincialCity() {
        return ProvincialCity;
    }

    public void setProvincialCity(String provincialCity) {
        ProvincialCity = provincialCity;
    }

    public String getTrueMoney() {
        return TrueMoney;
    }

    public void setTrueMoney(String trueMoney) {
        TrueMoney = trueMoney;
    }


}

