package com.news.sph.common.bean;

/**
 */
public class ViewFlowBean {
    private String imgUrl; //图片下载地址
    private String bitmap; //图片bitmap
    private String link; //跳转url

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getBitmap() {
        return bitmap;
    }

    public void setBitmap(String bitmap) {
        this.bitmap = bitmap;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}