package com.qluxstory.qingshe.issue.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/27.
 */
public class CalculationResult extends BaseEntity {
    List<CalculationEntity> data;
    public List<CalculationEntity> getData() {
        return data;
    }

    public void setData(List<CalculationEntity> data) {
        this.data = data;
    }
}
