package com.qluxstory.qingshe.issue.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/26.
 */
public class ToAnnounceEntity extends BaseEntity {
    /*
                     批次号
                          */
    private String bat_code;
    /*
                     夺宝商品号
                          */
    private String sna_code;
    /*
                     夺宝开始时间
                          */
    private String sna_begin_date;
    /*
                    夺宝揭晓时间
                          */
    private String sna_end_date;
    /*
                     夺宝期数
                          */
    private String sna_term;
    /*
                     参与人手机号
                          */
    private String sna_lucky_people;
    /*
                     随机数
                          */
    private String sna_lucky_num;
    /*
                        参与人次
                              */
    private String sna_participate_count;

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

    public String getSna_end_date() {
        return sna_end_date;
    }

    public void setSna_end_date(String sna_end_date) {
        this.sna_end_date = sna_end_date;
    }

    public String getSna_lucky_num() {
        return sna_lucky_num;
    }

    public void setSna_lucky_num(String sna_lucky_num) {
        this.sna_lucky_num = sna_lucky_num;
    }

    public String getSna_lucky_people() {
        return sna_lucky_people;
    }

    public void setSna_lucky_people(String sna_lucky_people) {
        this.sna_lucky_people = sna_lucky_people;
    }

    public String getSna_participate_count() {
        return sna_participate_count;
    }

    public void setSna_participate_count(String sna_participate_count) {
        this.sna_participate_count = sna_participate_count;
    }

    public String getSna_term() {
        return sna_term;
    }

    public void setSna_term(String sna_term) {
        this.sna_term = sna_term;
    }


}
