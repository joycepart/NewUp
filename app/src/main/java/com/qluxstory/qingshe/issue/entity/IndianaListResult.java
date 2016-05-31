package com.qluxstory.qingshe.issue.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/5/20.
 */
public class IndianaListResult extends BaseEntity{
    List<IndianaListEntity> data;
    public List<IndianaListEntity> getData() {
        return data;
    }

    public void setData(List<IndianaListEntity> data) {
        this.data = data;
    }


}
