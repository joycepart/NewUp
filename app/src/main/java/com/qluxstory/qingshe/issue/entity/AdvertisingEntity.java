package com.qluxstory.qingshe.issue.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/20.
 */
public class AdvertisingEntity extends BaseEntity{
    /* 编号，主键
                            */
    private String ID;

    /*  广告图片
                    */
    private String spec_pic;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSpec_pic() {
        return spec_pic;
    }

    public void setSpec_pic(String spec_pic) {
        this.spec_pic = spec_pic;
    }




}
