package com.qluxstory.qingshe.me.entity;

import com.qluxstory.qingshe.common.entity.BaseEntity;

/**
 * Created by lenovo on 2016/5/26.
 */
public class RecordIndianaEntity extends BaseEntity {
    /*
                    参与人手机号
                         */
    private String rec_phone;
    /*
                    参与时间
                         */
    private String rec_participate_date ;
    /*
                      参与次数
                             */
    private String rec_participate_count;

    public String getRec_participate_count() {
        return rec_participate_count;
    }

    public void setRec_participate_count(String rec_participate_count) {
        this.rec_participate_count = rec_participate_count;
    }

    public String getRec_participate_date() {
        return rec_participate_date;
    }

    public void setRec_participate_date(String rec_participate_date) {
        this.rec_participate_date = rec_participate_date;
    }

    public String getRec_phone() {
        return rec_phone;
    }

    public void setRec_phone(String rec_phone) {
        this.rec_phone = rec_phone;
    }


}
