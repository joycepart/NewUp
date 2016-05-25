package com.news.sph.home.entity;

import com.google.gson.annotations.SerializedName;
import com.news.sph.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/20.
 */
public class HomeAdcerEntity extends BaseEntity{
    /* 编号，主键*/
    @SerializedName("ID")
    private String id;
    /* 广告图片*/
    @SerializedName("spec_pic")
    private String specPic;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpecPic() {
        return specPic;
    }

    public void setSpecPic(String specPic) {
        this.specPic = specPic;
    }


}
