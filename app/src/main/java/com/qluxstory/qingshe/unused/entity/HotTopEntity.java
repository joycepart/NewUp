package com.qluxstory.qingshe.unused.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/20.
 */
public class HotTopEntity extends BaseEntity {
    /* 专题图片 */
    private String spec_pic;
    /*点击专题要跳转的url*/
    private String spec_src;

    public String getSpec_pic() {
        return spec_pic;
    }

    public void setSpec_pic(String spec_pic) {
        this.spec_pic = spec_pic;
    }

    public String getSpec_src() {
        return spec_src;
    }

    public void setSpec_src(String spec_src) {
        this.spec_src = spec_src;
    }






}
