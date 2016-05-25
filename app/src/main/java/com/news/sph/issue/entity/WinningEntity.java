package com.news.sph.issue.entity;

import com.news.sph.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/20.
 */
public class WinningEntity extends BaseEntity{
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
                   期数
                       */
    private String sna_term;
    /*
                        幸运用户
                           */
    private String sna_lucky_people;

    public String getBat_code() {
        return bat_code;
    }

    public void setBat_code(String bat_code) {
        this.bat_code = bat_code;
    }

    public String getSna_code() {
        return sna_code;
    }

    public void setSna_code(String sna_code) {
        this.sna_code = sna_code;
    }

    public String getSna_lucky_people() {
        return sna_lucky_people;
    }

    public void setSna_lucky_people(String sna_lucky_people) {
        this.sna_lucky_people = sna_lucky_people;
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


}
