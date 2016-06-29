package com.qluxstory.qingshe.me.dto;

import com.qluxstory.qingshe.common.dto.BaseDTO;

/**
 * Created by lenovo on 2016/6/28.
 */
public class KdDTO extends BaseDTO{
    private String orderNum;
    private String expdeliname;
    private String expdelicode;
    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getExpdeliname() {
        return expdeliname;
    }

    public void setExpdeliname(String expdeliname) {
        this.expdeliname = expdeliname;
    }

    public String getExpdelicode() {
        return expdelicode;
    }

    public void setExpdelicode(String expdelicode) {
        this.expdelicode = expdelicode;
    }


}
