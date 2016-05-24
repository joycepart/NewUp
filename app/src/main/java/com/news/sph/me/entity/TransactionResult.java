package com.news.sph.me.entity;

import com.news.sph.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/20.
 */
public class TransactionResult extends BaseEntity {
    List<TransactionEntity> data;
    public List<TransactionEntity> getData() {
        return data;
    }

    public void setData(List<TransactionEntity> data) {
        this.data = data;
    }


}
