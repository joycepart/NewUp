package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/6/8.
 */
public class UpDataEntity extends BaseEntity {
    /*
         订单状态 4/派奖中，5/已完结
                */

    private String rec_state;
    /*
         送货地址 省市区
                */

    private String con_privince;
    /*
        送货详细地址
                */

    private String con_address;
    /*
        送货人手机号
                */

    private String con_phone;
    /*
         送货人姓名
                */

    private String con_name;
    /*
         快递单号
                */

    private String order_code;
    /*
        快递公司名称
                */

    private String order_name;
    /*
         快递发出时间
                */

    private String order_begin_date;
    /*
         快递结束时间
                */
    private String order_end_date;

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

    public String getOrder_begin_date() {
        return order_begin_date;
    }

    public void setOrder_begin_date(String order_begin_date) {
        this.order_begin_date = order_begin_date;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public String getOrder_end_date() {
        return order_end_date;
    }

    public void setOrder_end_date(String order_end_date) {
        this.order_end_date = order_end_date;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getRec_state() {
        return rec_state;
    }

    public void setRec_state(String rec_state) {
        this.rec_state = rec_state;
    }


}
