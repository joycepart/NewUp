package com.qluxstory.qingshe.home.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by lenovo on 2016/7/5.
 */
public class ProductCommentResult extends BaseEntity {
    List<ProductCommentEntity> data;

    public List<ProductCommentEntity> getData() {
        return data;
    }

    public void setData(List<ProductCommentEntity> data) {
        this.data = data;
    }
}
