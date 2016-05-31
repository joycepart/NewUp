package com.qluxstory.qingshe.issue.dto;

import com.qluxstory.qingshe.common.dto.BaseDTO;

/**
 * Created by lenovo on 2016/5/26.
 */
public class SettlementDTO extends BaseDTO {
    private String type ;
    private String userPhone ;
    private String infor_phone;
    private String bat_code;
    private String sna_code;
    private String balance;
    private String sna_total_count;
    private String rec_code;
    private String term   ;
    private String rec_participate_count;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBat_code() {
        return bat_code;
    }

    public void setBat_code(String bat_code) {
        this.bat_code = bat_code;
    }

    public String getInfor_phone() {
        return infor_phone;
    }

    public void setInfor_phone(String infor_phone) {
        this.infor_phone = infor_phone;
    }

    public String getRec_code() {
        return rec_code;
    }

    public void setRec_code(String rec_code) {
        this.rec_code = rec_code;
    }

    public String getRec_participate_count() {
        return rec_participate_count;
    }

    public void setRec_participate_count(String rec_participate_count) {
        this.rec_participate_count = rec_participate_count;
    }

    public String getSna_code() {
        return sna_code;
    }

    public void setSna_code(String sna_code) {
        this.sna_code = sna_code;
    }

    public String getSna_total_count() {
        return sna_total_count;
    }

    public void setSna_total_count(String sna_total_count) {
        this.sna_total_count = sna_total_count;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }


}
