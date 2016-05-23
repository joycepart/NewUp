package com.news.sph.demo;

import com.news.sph.common.entity.BaseEntity;

import java.util.List;

/**
 * Created by bixinwei on 16/4/27.
 */
public class DemoEntity extends BaseEntity {
    private List<String>  data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
