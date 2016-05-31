package com.qluxstory.qingshe.home.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/30.
 */
public class AddAddressResult extends BaseEntity {
    List<AddAddressEntity> data;

    public List<AddAddressEntity> getData() {
        return data;
    }

    public void setData(List<AddAddressEntity> data) {
        this.data = data;
    }
}
