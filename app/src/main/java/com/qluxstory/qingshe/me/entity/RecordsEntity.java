package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/26.
 */
public class RecordsEntity extends BaseEntity {
    /*
              批次号
               */
    private String bat_code;
    /*
              夺宝商品号
               */
    private String sna_code;
    /*
              标题
               */
    private String sna_title;
    /*
             电话
               */
    private String rec_phone;
    /*
             备用电话
               */
    private String rec_info_phone;
    /*
              参与次数
               */
    private String rec_participate_count;
    /*
              参与时间
               */
    private String rec_participate_date;
    /*
             订单状态 状态 0/未付款 1/已付款 2/已中奖 3/未抢中 4/派奖中 5/已完结
               */
    private String rec_state;
    /*
              支付类型
               */
    private String rec_pay_type;
    /*
              支付金额
               */
    private String rec_pay_balance;
    /*
              期数
               */
    private String rec_term;
    /*
             订单号
               */
    private String rec_code;
    /*
                 图片地址
                   */
    private String pic_url ;
    /*
                     参与人数
                       */
    private String sna_total_count ;

    public String getSna_total_count() {
        return sna_total_count;
    }

    public void setSna_total_count(String sna_total_count) {
        this.sna_total_count = sna_total_count;
    }



    public String getBat_code() {
        return bat_code;
    }

    public void setBat_code(String bat_code) {
        this.bat_code = bat_code;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getRec_code() {
        return rec_code;
    }

    public void setRec_code(String rec_code) {
        this.rec_code = rec_code;
    }

    public String getRec_info_phone() {
        return rec_info_phone;
    }

    public void setRec_info_phone(String rec_info_phone) {
        this.rec_info_phone = rec_info_phone;
    }

    public String getRec_participate_count() {
        return rec_participate_count;
    }

    public void setRec_participate_count(String rec_participate_count) {
        this.rec_participate_count = rec_participate_count;
    }

    public String getRec_participate_date() {
        return rec_participate_date;
    }

    public void setRec_participate_date(String rec_participate_date) {
        this.rec_participate_date = rec_participate_date;
    }

    public String getRec_pay_balance() {
        return rec_pay_balance;
    }

    public void setRec_pay_balance(String rec_pay_balance) {
        this.rec_pay_balance = rec_pay_balance;
    }

    public String getRec_pay_type() {
        return rec_pay_type;
    }

    public void setRec_pay_type(String rec_pay_type) {
        this.rec_pay_type = rec_pay_type;
    }

    public String getRec_phone() {
        return rec_phone;
    }

    public void setRec_phone(String rec_phone) {
        this.rec_phone = rec_phone;
    }

    public String getRec_state() {
        return rec_state;
    }

    public void setRec_state(String rec_state) {
        this.rec_state = rec_state;
    }

    public String getRec_term() {
        return rec_term;
    }

    public void setRec_term(String rec_term) {
        this.rec_term = rec_term;
    }

    public String getSna_code() {
        return sna_code;
    }

    public void setSna_code(String sna_code) {
        this.sna_code = sna_code;
    }

    public String getSna_title() {
        return sna_title;
    }

    public void setSna_title(String sna_title) {
        this.sna_title = sna_title;
    }



}
