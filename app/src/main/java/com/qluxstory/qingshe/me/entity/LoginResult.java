package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**

 */
public class LoginResult extends BaseEntity {
    List<LoginEntity> data;

    public List<LoginEntity> getData() {
        return data;
    }

    public void setData(List<LoginEntity> data) {
        this.data = data;
    }
}
