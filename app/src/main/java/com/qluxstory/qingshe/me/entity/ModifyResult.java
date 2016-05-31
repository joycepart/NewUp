package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/19.
 */
public class ModifyResult extends BaseEntity {
    List<ModifyEntity> data;

    public List<ModifyEntity> getData() {
        return data;
    }

    public void setData(List<ModifyEntity> data) {
        this.data = data;
    }
}
