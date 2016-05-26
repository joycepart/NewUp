package com.news.sph.issue.entity;

import com.news.sph.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/26.
 */
public class PicEntity extends BaseEntity {
    /*
                     图片编号
                          */
    private  String sna_pic_code;
    /*
                         图片地址
                              */
    private  String sna_pic_url;

    public String getSna_pic_code() {
        return sna_pic_code;
    }

    public void setSna_pic_code(String sna_pic_code) {
        this.sna_pic_code = sna_pic_code;
    }

    public String getSna_pic_url() {
        return sna_pic_url;
    }

    public void setSna_pic_url(String sna_pic_url) {
        this.sna_pic_url = sna_pic_url;
    }


}
