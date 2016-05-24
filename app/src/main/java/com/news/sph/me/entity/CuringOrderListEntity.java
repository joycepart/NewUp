package com.news.sph.me.entity;

/**
 * Created by lenovo on 2016/5/20.
 */
public class CuringOrderListEntity {
    /*
              订单编号
                */
    private String mOrderNum;

    /*
           商品编号
            */
    private String mComOnlyCode;
    /*
          商品名称
            */
    private String mComName;
    /*
          单价
            */
    private String mComPrice;
    /*
           总金额
            */
    private String mOrderMoney;
    /*
           封面图片
            */
    private String mComShowPic;
    /*
           数量
            */
    private String mComCount;
    /*
          状态
            */
    private String mOrderState;
    /*
          下单时间(yyyy-MM-dd HH:mm:ss)
            */
    private String mOrderSingleTime;
    /*
               是否过期 【状态=0时显示已过期,不等于0，当没有这个状态】(1过期0未过期)
                */
    private String mIsovertime;

    public String getmComCount() {
        return mComCount;
    }

    public void setmComCount(String mComCount) {
        this.mComCount = mComCount;
    }

    public String getmComName() {
        return mComName;
    }

    public void setmComName(String mComName) {
        this.mComName = mComName;
    }

    public String getmComOnlyCode() {
        return mComOnlyCode;
    }

    public void setmComOnlyCode(String mComOnlyCode) {
        this.mComOnlyCode = mComOnlyCode;
    }

    public String getmComPrice() {
        return mComPrice;
    }

    public void setmComPrice(String mComPrice) {
        this.mComPrice = mComPrice;
    }

    public String getmComShowPic() {
        return mComShowPic;
    }

    public void setmComShowPic(String mComShowPic) {
        this.mComShowPic = mComShowPic;
    }

    public String getmIsovertime() {
        return mIsovertime;
    }

    public void setmIsovertime(String mIsovertime) {
        this.mIsovertime = mIsovertime;
    }

    public String getmOrderMoney() {
        return mOrderMoney;
    }

    public void setmOrderMoney(String mOrderMoney) {
        this.mOrderMoney = mOrderMoney;
    }

    public String getmOrderNum() {
        return mOrderNum;
    }

    public void setmOrderNum(String mOrderNum) {
        this.mOrderNum = mOrderNum;
    }

    public String getmOrderSingleTime() {
        return mOrderSingleTime;
    }

    public void setmOrderSingleTime(String mOrderSingleTime) {
        this.mOrderSingleTime = mOrderSingleTime;
    }

    public String getmOrderState() {
        return mOrderState;
    }

    public void setmOrderState(String mOrderState) {
        this.mOrderState = mOrderState;
    }



}
