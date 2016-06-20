package com.qluxstory.qingshe.special;

import android.content.Context;
import android.content.Intent;

import com.qluxstory.qingshe.special.activity.SpecialBrowserActivity;


/**
 * Created by lenovo on 2016/5/17.
 */
public class UnusedUiGoto {
    /**
     * 跳转到专题页（加载h5页面）  有分享功能
     * @param context
     * @param mUrl
     * @param mName
     * @param id
     */

    public static void special(Context context, String mUrl, String mName, String id){
        Intent intent = new Intent(context, SpecialBrowserActivity.class);
        intent.putExtra("url", mUrl);
        intent.putExtra("title", mName);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }
}
