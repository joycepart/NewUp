package com.qluxstory.qingshe.me.dto;

import com.qluxstory.qingshe.common.dto.BaseDTO;

/**
 * Created by lenovo on 2016/6/8.
 */
public class UpInformationDTO extends BaseDTO {
    /*订单号
           */
    private String rec_code;
    /*登录人手机号
           */
    private String rec_phone;
    /*送货地址 省市区
           */
    private String con_privince;
    /*送货地址详情
           */
    private String con_address;
    /*收货人电话
           */
    private String con_phone;
    /*收货人姓名
               */
    private String con_name;


    public String getCon_address() {
        return con_address;
    }

    public void setCon_address(String con_address) {
        this.con_address = con_address;
    }

    public String getCon_name() {
        return con_name;
    }

    public void setCon_name(String con_name) {
        this.con_name = con_name;
    }

    public String getCon_phone() {
        return con_phone;
    }

    public void setCon_phone(String con_phone) {
        this.con_phone = con_phone;
    }

    public String getCon_privince() {
        return con_privince;
    }

    public void setCon_privince(String con_privince) {
        this.con_privince = con_privince;
    }

    public String getRec_code() {
        return rec_code;
    }

    public void setRec_code(String rec_code) {
        this.rec_code = rec_code;
    }

    public String getRec_phone() {
        return rec_phone;
    }

    public void setRec_phone(String rec_phone) {
        this.rec_phone = rec_phone;
    }


}
