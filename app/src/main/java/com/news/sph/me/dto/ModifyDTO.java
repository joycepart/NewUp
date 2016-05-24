package com.news.sph.me.dto;

import com.news.sph.common.dto.BaseDTO;

/**
 * Created by lenovo on 2016/5/19.
 */
public class ModifyDTO extends BaseDTO {
    /*新用户名
       */
    private String membername;
    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }


}
