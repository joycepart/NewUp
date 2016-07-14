package com.qluxstory.qingshe.home.dto;

import com.qluxstory.qingshe.common.dto.BaseDTO;

/**
 * Created by lenovo on 2016/7/5.
 */
public class ProductCommentDTO extends BaseDTO {
    private String code ;
    private String com_sell_type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCom_sell_type() {
        return com_sell_type;
    }

    public void setCom_sell_type(String com_sell_type) {
        this.com_sell_type = com_sell_type;
    }


}
