package com.news.sph.home.entity;

/**
 * Created by lenovo on 2016/5/20.
 */
public class HomeAdcerEntity {
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

    /*
                        编号，主键
                           */
    private String ID  ;

    /*
                 广告图片
                    */
    private String spec_pic;
}
