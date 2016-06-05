package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/6/3.
 */
public class ExchangeVoucherResult extends BaseEntity {
    List<ExchangeVoucherEntity> data;
    public List<ExchangeVoucherEntity> getData() {
        return data;
    }

    public void setData(List<ExchangeVoucherEntity> data) {
        this.data = data;
    }

}
