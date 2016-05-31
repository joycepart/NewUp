package com.qluxstory.qingshe.information.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/20.
 */
public class InformationEntity extends  BaseEntity{
    /* 编号 */
    private String news_code;
    /* 新闻大标题 */
    private String news_big_title;
    /* 新闻小标题 */
    private String news_small_title;
    /*新闻图片地址*/
    private String news_pic_url;
    /*新闻html地址*/
    private String news_html_url;
    /*添加时间*/
    private String news_add_time;

    public String getNews_add_time() {
        return news_add_time;
    }

    public void setNews_add_time(String news_add_time) {
        this.news_add_time = news_add_time;
    }

    public String getNews_big_title() {
        return news_big_title;
    }

    public void setNews_big_title(String news_big_title) {
        this.news_big_title = news_big_title;
    }

    public String getNews_code() {
        return news_code;
    }

    public void setNews_code(String news_code) {
        this.news_code = news_code;
    }

    public String getNews_html_url() {
        return news_html_url;
    }

    public void setNews_html_url(String news_html_url) {
        this.news_html_url = news_html_url;
    }

    public String getNews_pic_url() {
        return news_pic_url;
    }

    public void setNews_pic_url(String news_pic_url) {
        this.news_pic_url = news_pic_url;
    }

    public String getNews_small_title() {
        return news_small_title;
    }

    public void setNews_small_title(String news_small_title) {
        this.news_small_title = news_small_title;
    }


}
