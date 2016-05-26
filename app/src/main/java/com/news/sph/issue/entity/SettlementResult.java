package com.news.sph.issue.entity;

import com.news.sph.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/26.
 */
public class SettlementResult extends BaseEntity {
    List<SettlementEntity> data;
    public List<SettlementEntity> getData() {
        return data;
    }

    public void setData(List<SettlementEntity> data) {
        this.data = data;
    }


}
