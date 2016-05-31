package com.qluxstory.qingshe.me.entity;

import com.google.gson.annotations.SerializedName;
import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/20.
 */
public class CuringOrderListEntity  extends BaseEntity {
    /*
              订单编号
                */
    private String OrderNum;

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
    @SerializedName("app_show_pic")
    private String com_show_pic;
    /*
           数量
            */
    private String ComCount;
    /*
          状态
            */
    private String OrderState;
    /*
          下单时间(yyyy-MM-dd HH:mm:ss)
            */
    private String OrderSingleTime;
    /*
               是否过期 【状态=0时显示已过期,不等于0，当没有这个状态】(1过期0未过期)
                */
    private String isovertime;

    public String getComCount() {
        return ComCount;
    }

    public void setComCount(String comCount) {
        this.ComCount = comCount;
    }

    public String getComName() {
        return ComName;
    }

    public void setComName(String comName) {
        this.ComName = comName;
    }

    public String getComOnlyCode() {
        return ComOnlyCode;
    }

    public void setComOnlyCode(String comOnlyCode) {
        this.ComOnlyCode = comOnlyCode;
    }

    public String getComPrice() {
        return ComPrice;
    }

    public void setComPrice(String comPrice) {
        this.ComPrice = comPrice;
    }

    public String getCom_show_pic() {
        return com_show_pic;
    }

    public void setCom_show_pic(String com_show_pic) {
        this.com_show_pic = com_show_pic;
    }

    public String getIsovertime() {
        return isovertime;
    }

    public void setIsovertime(String isovertime) {
        this.isovertime = isovertime;
    }

    public String getOrderMoney() {
        return OrderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.OrderMoney = orderMoney;
    }

    public String getOrderNum() {
        return OrderNum;
    }

    public void setOrderNum(String orderNum) {
        this.OrderNum = orderNum;
    }

    public String getOrderSingleTime() {
        return OrderSingleTime;
    }

    public void setOrderSingleTime(String orderSingleTime) {
        this.OrderSingleTime = orderSingleTime;
    }

    public String getOrderState() {
        return OrderState;
    }

    public void setOrderState(String orderState) {
        this.OrderState = orderState;
    }



}
