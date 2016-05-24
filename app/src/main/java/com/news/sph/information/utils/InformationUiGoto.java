package com.news.sph.information.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.news.sph.common.base.BrowserActivity;


/**
 * Created by lenovo on 2016/5/17.
 */
public class InformationUiGoto {
    /**
     * 跳转到新闻详情页（加载h5页面）
     *
     * @param context
     * @param mNewsBigTitle
     * @param mUrl
     */
    public static void newsDetail(Context context, String mNewsBigTitle, String mUrl) {
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra("url", mUrl);
        intent.putExtra("title", mNewsBigTitle);
        context.startActivity(intent);
    }


}
