package com.news.sph.information.entity;

import com.news.sph.common.entity.BaseEntity;
import com.news.sph.me.entity.CuringOrderListEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/20.
 */
public class InformationResult extends BaseEntity {
    public List<InformationEntity> getData() {
        return data;
    }

    public void setData(List<InformationEntity> data) {
        this.data = data;
    }

    List<InformationEntity> data;
}
