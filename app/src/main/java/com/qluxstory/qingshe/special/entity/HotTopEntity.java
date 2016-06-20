package com.qluxstory.qingshe.special.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/20.
 */
public class HotTopEntity extends BaseEntity {
    /* 专题图片 */
    private String spec_pic;
    /*名字*/
    private String spec_name;
    /*专题编号*/
    private String ID;

    public String getSpec_name() {
        return spec_name;
    }

    public void setSpec_name(String spec_name) {
        this.spec_name = spec_name;
    }

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
