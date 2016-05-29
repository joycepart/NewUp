package com.news.sph.issue.entity;

import com.news.sph.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/27.
 */
public class CalculationEntity extends BaseEntity {
    /*
                    时间
                              */
    private String calc_date;
    /*
                   当前时间(int型)
                              */
    private int calc_number;
    /*
                    当前时间所属人
                              */
    private String calc_people;
    /*
                    50个时间和
                              */
    private String sna_calc;
    /*
                       批次号
                                 */
    private String bat_code;

    public String getBat_code() {
        return bat_code;
    }

    public void setBat_code(String bat_code) {
        this.bat_code = bat_code;
    }

    public String getCalc_date() {
        return calc_date;
    }

    public void setCalc_date(String calc_date) {
        this.calc_date = calc_date;
    }

    public int getCalc_number() {
        return calc_number;
    }

    public void setCalc_number(int calc_number) {
        this.calc_number = calc_number;
    }

    public String getCalc_people() {
        return calc_people;
    }

    public void setCalc_people(String calc_people) {
        this.calc_people = calc_people;
    }

    public String getSna_calc() {
        return sna_calc;
    }

    public void setSna_calc(String sna_calc) {
        this.sna_calc = sna_calc;
    }


}
