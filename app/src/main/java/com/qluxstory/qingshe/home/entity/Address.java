package com.qluxstory.qingshe.home.entity;

/**
 * Created by lenovo on 2016/5/31.
 */
public class Address {
    /*省*/
    private String dis_province;
    /*市*/
    private String dis_city;
    /*区*/
    private String dis_area;
    /*门店名称*/
    private String sto_name;
    /*门店电话*/
    private String sto_phone;

    public String getDis_area() {
        return dis_area;
    }

    public void setDis_area(String dis_area) {
        this.dis_area = dis_area;
    }

    public String getDis_city() {
        return dis_city;
    }

    public void setDis_city(String dis_city) {
        this.dis_city = dis_city;
    }

    public String getDis_province() {
        return dis_province;
    }

    public void setDis_province(String dis_province) {
        this.dis_province = dis_province;
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


}
