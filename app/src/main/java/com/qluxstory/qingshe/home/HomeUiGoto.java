package com.qluxstory.qingshe.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.qluxstory.qingshe.common.base.BrowserActivity;
import com.qluxstory.qingshe.home.activity.AddAddressActivity;
import com.qluxstory.qingshe.home.activity.CuringActivity;
import com.qluxstory.qingshe.home.activity.PlaceOrderActivity;
import com.qluxstory.qingshe.home.activity.ProductBrowserActivity;

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
     * @param mCode
     * @param mPrice
     * @param mName
     * @param mPic
     */

    public static void placeOrder(Context context, String mPrice, String mName, String mPic, String mCode){
        Intent intent = new Intent(context, PlaceOrderActivity.class);
        intent.putExtra("mPrice", mPrice);
        intent.putExtra("mName", mName);
        intent.putExtra("mPic", mPic);
        intent.putExtra("mCode", mCode);
        context.startActivity(intent);
    }

    /**
     * 跳转到新增地址页
     * @param act
     */
    public static final int NEWADD_REQUEST = 0001;
    public static void addAddress(Activity act){
        Intent intent = new Intent(act, AddAddressActivity.class);
        act.startActivityForResult(intent,NEWADD_REQUEST);
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
     * 跳转到专业养护之商品详情页（加载h5页面）
     * @param context
     * @param mCode
     * @param mName
     * @param mPic
     * @param mUrl
     * @param mPrice
     */

    public static void curingProductDetails(Context context, String mUrl, String mPrice, String mName, String mPic, String mCode){
        Intent intent = new Intent(context, ProductBrowserActivity.class);
        intent.putExtra("mUrl", mUrl);
        intent.putExtra("mPrice", mPrice);
        intent.putExtra("mName", mName);
        intent.putExtra("mPic", mPic);
        intent.putExtra("mCode", mCode);
        context.startActivity(intent);
    }
    /**
     * 跳转到商品详情详情页（加载h5页面）
     *
     * @param context
     * @param mUrlSpecial
     * @param mSpecialTitle
     */

    public static void productDetails(Context context, String mUrlSpecial, String mSpecialTitle){
        Intent intent = new Intent(context, ProductBrowserActivity.class);
        intent.putExtra("url", mUrlSpecial);
        intent.putExtra("title", mSpecialTitle);
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
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
