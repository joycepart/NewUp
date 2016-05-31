package com.qluxstory.qingshe.home.entity;

/**
 * Created by lenovo on 2016/5/30.
 */
public class Consignee {
    /*收货人*/
    private String conname;
    /*手机号码*/
    private String delivmobile;
    /*省市区*/
    private String provincity;
    /*详细地址*/
    private String addredetail;

    public String getAddredetail() {
        return addredetail;
    }

    public void setAddredetail(String addredetail) {
        this.addredetail = addredetail;
    }

    public String getConname() {
        return conname;
    }

    public void setConname(String conname) {
        this.conname = conname;
    }

    public String getDelivmobile() {
        return delivmobile;
    }

    public void setDelivmobile(String delivmobile) {
        this.delivmobile = delivmobile;
    }

    public String getProvincity() {
        return provincity;
    }

    public void setProvincity(String provincity) {
        this.provincity = provincity;
    }


}
