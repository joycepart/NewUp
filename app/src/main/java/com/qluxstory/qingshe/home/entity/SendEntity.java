package com.qluxstory.qingshe.home.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/30.
 */
public class SendEntity extends BaseEntity {
    /*配送编号*/
    private String dis_only_code;
    /*配送类型*/
    private String dis_type;
    /*省*/
    private String dis_province;
    /*市*/
    private String dis_city;
    /*区*/
    private String dis_area;
    /*详细地址*/
    private String dis_address;
    /*服务费*/
    private String dis_server_money;
    /*门店名称*/
    private String sto_name;
    /*门店电话*/
    private String sto_phone;

    public String getDis_address() {
        return dis_address;
    }

    public void setDis_address(String dis_address) {
        this.dis_address = dis_address;
    }

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

    public String getDis_only_code() {
        return dis_only_code;
    }

    public void setDis_only_code(String dis_only_code) {
        this.dis_only_code = dis_only_code;
    }

    public String getDis_province() {
        return dis_province;
    }

    public void setDis_province(String dis_province) {
        this.dis_province = dis_province;
    }

    public String getDis_server_money() {
        return dis_server_money;
    }

    public void setDis_server_money(String dis_server_money) {
        this.dis_server_money = dis_server_money;
    }

    public String getDis_type() {
        return dis_type;
    }

    public void setDis_type(String dis_type) {
        this.dis_type = dis_type;
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
