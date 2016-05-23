package com.news.sph.me.dto;

import com.news.sph.common.dto.BaseDTO;

/**
 * Created by lenovo on 2016/5/20.
 */
public class CuringOrderListDTO extends BaseDTO {
    public String getAppreqtype() {
        return appreqtype;
    }

    public void setAppreqtype(String appreqtype) {
        this.appreqtype = appreqtype;
    }

    /*
            请求状态(不填是请求全部)
             */
    private String appreqtype;
}
