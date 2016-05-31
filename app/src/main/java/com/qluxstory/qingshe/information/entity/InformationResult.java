package com.qluxstory.qingshe.information.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/20.
 */
public class InformationResult extends BaseEntity {
    List<InformationEntity> data;

    public List<InformationEntity> getData() {
        return data;
    }

    public void setData(List<InformationEntity> data) {
        this.data = data;
    }

}
