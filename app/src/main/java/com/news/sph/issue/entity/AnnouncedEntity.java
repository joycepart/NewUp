package com.news.sph.issue.entity;

import com.news.sph.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/27.
 */
public class AnnouncedEntity extends BaseEntity {
    /*
                     批次号
                               */
    private String bat_code;
    /*
                     夺宝商品号
                               */
    private String sna_code;
    /*
                     期数
                               */
    private String sna_term;
    /*
                     批次状态
                               */
    private String bat_state;
    /*
                     开始时间
                               */
    private String sna_begin_date;
    /*
                     揭晓时间
                               */
    private String sna_end_date;
    /*
                     幸运用户手机号
                               */
    private String sna_lucky_people;
    /*
                     幸运号码
                               */
    private String sna_lucky_num;
    /*
                     参与次数
                               */
    private String sna_participate_count;
    /*
                     50个时间之和
                               */
    private String sna_calc;
    /*
                     头像
                               */
    private String headImg;
    /*
                         参与时间
                                   */
    private String participate_date;

    public String getBat_code() {
        return bat_code;
    }

    public void setBat_code(String bat_code) {
        this.bat_code = bat_code;
    }

    public String getBat_state() {
        return bat_state;
    }

    public void setBat_state(String bat_state) {
        this.bat_state = bat_state;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getParticipate_date() {
        return participate_date;
    }

    public void setParticipate_date(String participate_date) {
        this.participate_date = participate_date;
    }

    public String getSna_begin_date() {
        return sna_begin_date;
    }

    public void setSna_begin_date(String sna_begin_date) {
        this.sna_begin_date = sna_begin_date;
    }

    public String getSna_calc() {
        return sna_calc;
    }

    public void setSna_calc(String sna_calc) {
        this.sna_calc = sna_calc;
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
