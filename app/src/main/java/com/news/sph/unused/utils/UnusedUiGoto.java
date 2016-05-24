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
     * @param mSpecName
     * @param mSpecSrc
     */

    public static void special(Context context, String mSpecName, String mSpecSrc){
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra("url", mSpecSrc);
        intent.putExtra("title", mSpecName);
        context.startActivity(intent);
    }
}
