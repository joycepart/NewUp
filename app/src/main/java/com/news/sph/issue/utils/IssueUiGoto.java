package com.news.sph.issue.utils;

import android.content.Context;
import android.content.Intent;

import com.news.sph.issue.activity.IndianaProductDetailActivity;

/**
 * Created by lenovo on 2016/5/22.
 */
public class IssueUiGoto {
    /**
     * 跳转到商品详情页
     *
     * @param context
     */
    public static void productDetail(Context context) {
        Intent intent = new Intent(context, IndianaProductDetailActivity.class);
        context.startActivity(intent);
    }
}
