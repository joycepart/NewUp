package com.qluxstory.qingshe.home.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/26.
 */
public class TranEntity extends BaseEntity {
    /*夺宝商品编号*/
    private String sna_code;
    /*批次编号*/
    private String bat_code;
    /*夺宝期数*/
    private String sna_term;
    /*商品标题*/
    private String sna_title;
    /*开奖所需人次*/
    private String sna_total_count;
    /*商品备注*/
    private String sna_remark;
    /*已参与人次*/
    private String sna_sell_out;
    /*开始时间*/
    private String sna_begin_date;

    public String getBat_code() {
        return bat_code;
    }

    public void setBat_code(String bat_code) {
        this.bat_code = bat_code;
    }

    public String getSna_begin_date() {
        return sna_begin_date;
    }

    public void setSna_begin_date(String sna_begin_date) {
        this.sna_begin_date = sna_begin_date;
    }

    public String getSna_code() {
        return sna_code;
    }

    public void setSna_code(String sna_code) {
        this.sna_code = sna_code;
    }

    public String getSna_remark() {
        return sna_remark;
    }

    public void setSna_remark(String sna_remark) {
        this.sna_remark = sna_remark;
    }

    public String getSna_sell_out() {
        return sna_sell_out;
    }

    public void setSna_sell_out(String sna_sell_out) {
        this.sna_sell_out = sna_sell_out;
    }

    public String getSna_term() {
        return sna_term;
    }

    public void setSna_term(String sna_term) {
        this.sna_term = sna_term;
    }

    public String getSna_title() {
        return sna_title;
    }

    public void setSna_title(String sna_title) {
        this.sna_title = sna_title;
    }

    public String getSna_total_count() {
        return sna_total_count;
    }

    public void setSna_total_count(String sna_total_count) {
        this.sna_total_count = sna_total_count;
    }



}
