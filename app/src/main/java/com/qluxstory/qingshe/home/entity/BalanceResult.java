package com.qluxstory.qingshe.home.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/30.
 */
public class BalanceResult extends BaseEntity {
    List<BalanceEntity> data;

    public List<BalanceEntity> getData() {
        return data;
    }

    public void setData(List<BalanceEntity> data) {
        this.data = data;
    }
}
