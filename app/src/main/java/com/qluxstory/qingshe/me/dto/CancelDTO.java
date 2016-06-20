package com.qluxstory.qingshe.me.dto;

import com.qluxstory.qingshe.common.dto.BaseDTO;

/**
 * Created by lenovo on 2016/6/17.
 */
public class CancelDTO extends BaseDTO {
    private String OrderNum;
    public String getOrderNum() {
        return OrderNum;
    }

    public void setOrderNum(String orderNum) {
        OrderNum = orderNum;
    }


}
