package com.qluxstory.qingshe.home.dto;

import com.qluxstory.qingshe.common.dto.BaseDTO;

/**
 * Created by lenovo on 2016/5/30.
 */
public class AddAddressDTO extends BaseDTO {
    private String conname;
    private String delivmobile;
    private String provincity;
    private String addredetail;

    public String getAddredetail() {
        return addredetail;
    }

    public void setAddredetail(String addredetail) {
        this.addredetail = addredetail;
    }

    public String getConname() {
        return conname;
    }

    public void setConname(String conname) {
        this.conname = conname;
    }

    public String getDelivmobile() {
        return delivmobile;
    }

    public void setDelivmobile(String delivmobile) {
        this.delivmobile = delivmobile;
    }

    public String getProvincity() {
        return provincity;
    }

    public void setProvincity(String provincity) {
        this.provincity = provincity;
    }


}
