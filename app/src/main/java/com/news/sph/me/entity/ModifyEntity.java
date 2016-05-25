package com.news.sph.me.entity;

import com.news.sph.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/19.
 */
public class ModifyEntity extends BaseEntity{
    /*新用户名
             */
    private String Membername;
    public String getMembername() {
        return Membername;
    }

    public void setMembername(String membername) {
        Membername = membername;
    }


}
