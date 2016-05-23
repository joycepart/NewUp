package com.news.sph.unused.utils;

import android.content.Context;
import android.content.Intent;

import com.news.sph.ui.BrowserActivity;

/**
 * Created by lenovo on 2016/5/17.
 */
public class UnusedUiGoto {
    /**
     * 跳转到专题/广告详情页（加载h5页面）
     *
     * @param context
     * @param mSpecSrc
     */

    public static void special(Context context, String mSpecSrc){
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra("url", mSpecSrc);
//        intent.putExtra("title", mSpecPic);
        context.startActivity(intent);
    }
}
