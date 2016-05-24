package com.news.sph.unused.utils;

import android.content.Context;
import android.content.Intent;

import com.news.sph.common.base.BrowserActivity;


/**
 * Created by lenovo on 2016/5/17.
 */
public class UnusedUiGoto {
    /**
     * 跳转到专题/广告详情页（加载h5页面）
     *  @param context
     * @param mUrl
     * @param mName
     */

    public static void special(Context context, String mUrl, String mName){
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra("url", mUrl);
        intent.putExtra("title", mName);
        context.startActivity(intent);
    }
}
