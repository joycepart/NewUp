package com.qluxstory.qingshe.me.dto;

import com.qluxstory.qingshe.common.dto.BaseDTO;

/**
 * Created by lenovo on 2016/5/20.
 */
public class CuringOrderListDTO extends BaseDTO {
    /*请求状态(不填是请求全部)
                                */
    private String appreqtype;

    public String getAppreqtype() {
        return appreqtype;
    }

    public void setAppreqtype(String appreqtype) {
        this.appreqtype = appreqtype;
    }






}
