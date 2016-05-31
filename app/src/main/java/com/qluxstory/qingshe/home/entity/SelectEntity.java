package com.qluxstory.qingshe.home.entity;

import com.google.gson.annotations.SerializedName;
import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/30.
 */
public class SelectEntity extends BaseEntity {
    /*用户手机号码*/
    private String membermobile;
    /*用户收货地址编号*/
    @SerializedName("ConsigneeCode")
    private String ConCode;
    /*收货人*/
    @SerializedName("ConsigneeName")
    private String ConName;
    /*手机号码*/
    @SerializedName("DeliveredMobile")
    private String DelivMobile;
    /*省市区*/
    @SerializedName("ProvincialCity")
    private String ProvinCity;
    /*详细地址*/
    @SerializedName("AddressInDetail")
    private String AddreDetail;

    public String getAddreDetail() {
        return AddreDetail;
    }

    public void setAddreDetail(String addreDetail) {
        AddreDetail = addreDetail;
    }

    public String getConCode() {
        return ConCode;
    }

    public void setConCode(String conCode) {
        ConCode = conCode;
    }

    public String getConName() {
        return ConName;
    }

    public void setConName(String conName) {
        ConName = conName;
    }

    public String getDelivMobile() {
        return DelivMobile;
    }

    public void setDelivMobile(String delivMobile) {
        DelivMobile = delivMobile;
    }

    public String getMembermobile() {
        return membermobile;
    }

    public void setMembermobile(String membermobile) {
        this.membermobile = membermobile;
    }

    public String getProvinCity() {
        return ProvinCity;
    }

    public void setProvinCity(String provinCity) {
        ProvinCity = provinCity;
    }


}
