package com.news.sph.me.dto;

import com.news.sph.common.dto.BaseDTO;

/**
 * Created by lenovo on 2016/5/20.
 */
public class CuringOrderListDTO extends BaseDTO {
    /*请求状态(不填是请求全部)
                 */
    private String mAppreqtype;
    public String getmAppreqtype() {
        return mAppreqtype;
    }

    public void setmAppreqtype(String mAppreqtype) {
        this.mAppreqtype = mAppreqtype;
    }

}
