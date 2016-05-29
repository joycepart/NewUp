package com.news.sph.issue;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.news.sph.common.base.BrowserActivity;
import com.news.sph.issue.activity.SettlementActivity;

/**
 * Created by lenovo on 2016/5/22.
 */
public class IssueUiGoto {
    /**
     * 跳转到结算页
     *
     * @param context
     */
    public static void settlement(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SettlementActivity.class);
        intent.putExtra("bundle",bundle);
        context.startActivity(intent);
    }

    /**
     * 跳转到图文详情页
     * @param context
     * @param mUrl
     * @param mTitle
     */
    public static void graphicDetails(Context context, String mUrl, String mTitle){
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra("url", mUrl);
        intent.putExtra("title", mTitle);
        context.startActivity(intent);
    }
    /**
     * 跳转到夺宝广告页（h5加载）
     *
     * @param context
     */
    public static void special(Context context, String mSpecSrc){
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra("url", mSpecSrc);
        context.startActivity(intent);
    }

    /**
     * 跳转到夺宝用户服务协议页（h5加载）
     *
     * @param context
     */
    public static void service(Context context, String mSpecSrc){
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra("url", mSpecSrc);
        context.startActivity(intent);
    }
}
