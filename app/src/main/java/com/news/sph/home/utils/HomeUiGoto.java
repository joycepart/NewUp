package com.news.sph.home.utils;

import android.content.Context;
import android.content.Intent;

import com.news.sph.home.activity.AddAddressActivity;
import com.news.sph.home.activity.CuringActivity;
import com.news.sph.home.activity.PlaceOrderActivity;
import com.news.sph.home.activity.SelectAddressActivity;
import com.news.sph.me.activity.VouchersActivity;
import com.news.sph.ui.BrowserActivity;

/**
 * Created by lenovo on 2016/5/17.
 */
public class HomeUiGoto {
    /**
     * 跳转到专业养护页
     * @param context
     */

    public static void curing(Context context){
        Intent intent = new Intent(context, CuringActivity.class);
        context.startActivity(intent);
    }
    /**
     * 跳转到提交订单页
     * @param context
     */

    public static void placeOrder(Context context){
        Intent intent = new Intent(context, PlaceOrderActivity.class);
        context.startActivity(intent);
    }
    /**
     * 跳转到选择地址页
     * @param context
     */

    public static void receiptAddress(Context context){
        Intent intent = new Intent(context, SelectAddressActivity.class);
        context.startActivity(intent);
    }
    /**
     * 跳转到新增地址页
     * @param context
     */

    public static void addAddress(Context context){
        Intent intent = new Intent(context, AddAddressActivity.class);
        context.startActivity(intent);
    }
    /**
     * 跳转到代金劵页
     * @param context
     */

    public static void vouchers(Context context){
        Intent intent = new Intent(context, VouchersActivity.class);
        context.startActivity(intent);
    }
    /**
     * 跳转到专题/广告详情页（加载h5页面）
     *
     * @param context
     * @param mSpecSrc
     */

    public static void special(Context context, String mSpecSrc){
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra("url", mSpecSrc);
        context.startActivity(intent);
    }
    /**
     * 跳转到商品详情详情页（加载h5页面）
     *
     * @param context
     * @param mUrlFocus
     * @param mFocusTitle
     */

    public static void productDetails(Context context, String mUrlFocus, String mFocusTitle){
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra("url", mUrlFocus);
        intent.putExtra("title", mFocusTitle);
        context.startActivity(intent);
    }
    /**
     * 跳转到线下详情页（加载h5页面）
     *
     * @param context
     * @param mUrlOffline
     * @param mOfflineTitle
     */

    public static void OfflineStore(Context context, String mUrlOffline, String mOfflineTitle){
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra("url", mUrlOffline);
        intent.putExtra("title", mOfflineTitle);
        context.startActivity(intent);
    }
    /**
     * 跳转到交易帮助详情页（加载h5页面）
     *
     * @param context
     * @param mUrlHelp
     * @param mHelpTitle
     */

    public static void help(Context context, String mUrlHelp, String mHelpTitle){
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra("url", mUrlHelp);
        intent.putExtra("title", mHelpTitle);
        context.startActivity(intent);
    }
}
