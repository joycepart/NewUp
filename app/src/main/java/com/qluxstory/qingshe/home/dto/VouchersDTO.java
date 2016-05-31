package com.qluxstory.qingshe.home.dto;

import com.qluxstory.qingshe.common.dto.BaseDTO;

/**
 * Created by lenovo on 2016/5/30.
 */
public class VouchersDTO extends BaseDTO {
    private String vertype;
    private String comsort;
    private String combrand;
    private String comserviceonlycode;
    private String comallmoney;

    public String getComallmoney() {
        return comallmoney;
    }

    public void setComallmoney(String comallmoney) {
        this.comallmoney = comallmoney;
    }

    public String getCombrand() {
        return combrand;
    }

    public void setCombrand(String combrand) {
        this.combrand = combrand;
    }

    public String getComserviceonlycode() {
        return comserviceonlycode;
    }

    public void setComserviceonlycode(String comserviceonlycode) {
        this.comserviceonlycode = comserviceonlycode;
    }

    public String getComsort() {
        return comsort;
    }

    public void setComsort(String comsort) {
        this.comsort = comsort;
    }

    public String getVertype() {
        return vertype;
    }

    public void setVertype(String vertype) {
        this.vertype = vertype;
    }


}
