package com.news.sph.unused.entity;

import com.news.sph.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/20.
 */
public class HotTopEntity extends BaseEntity {
    /* 专题图片 */
    private String mSpecPic;
    /*点击专题要跳转的url*/
    private String mSpecSrc;

    public String getmSpecPic() {
        return mSpecPic;
    }

    public void setmSpecPic(String mSpecPic) {
        this.mSpecPic = mSpecPic;
    }

    public String getmSpecSrc() {
        return mSpecSrc;
    }

    public void setmSpecSrc(String mSpecSrc) {
        this.mSpecSrc = mSpecSrc;
    }


}
