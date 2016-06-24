package com.qluxstory.qingshe.home.entity;

/**
 * Created by lenovo on 2016/5/30.
 */
public class Consignee {
    /*收货地址编号*/
    private String ConsigneeCode;
    /*收货人*/
    private String ConsigneeName;
    /*手机号码*/
    private String DeliveredMobile;
    /*省市区*/
    private String ProvincialCity;
    /*详细地址*/
    private String AddressInDetail;
    /*item*/
    private int item;
    private String province;
    private String area;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }



    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }




    public String getAddressInDetail() {
        return AddressInDetail;
    }

    public void setAddressInDetail(String addressInDetail) {
        AddressInDetail = addressInDetail;
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

    public String getDeliveredMobile() {
        return DeliveredMobile;
    }

    public void setDeliveredMobile(String deliveredMobile) {
        DeliveredMobile = deliveredMobile;
    }

    public String getProvincialCity() {
        return ProvincialCity;
    }

    public void setProvincialCity(String provincialCity) {
        ProvincialCity = provincialCity;
    }



}
