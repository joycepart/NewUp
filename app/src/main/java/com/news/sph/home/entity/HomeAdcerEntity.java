package com.news.sph.home.entity;

import com.news.sph.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/20.
 */
public class HomeAdcerEntity extends BaseEntity{
    /* 编号，主键*/
    private String mID  ;
    /* 广告图片*/
    private String mSpecPic;
    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmSpecPic() {
        return mSpecPic;
    }

    public void setmSpecPic(String mSpecPic) {
        this.mSpecPic = mSpecPic;
    }


}
