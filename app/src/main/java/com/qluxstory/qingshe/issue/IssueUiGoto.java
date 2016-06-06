package com.qluxstory.qingshe.issue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.qluxstory.qingshe.MainActivity;
import com.qluxstory.qingshe.common.base.BrowserActivity;
import com.qluxstory.qingshe.common.widget.PopupProductDetails;
import com.qluxstory.qingshe.issue.activity.PaymentSuccessActivity;
import com.qluxstory.qingshe.issue.activity.SettlementActivity;

/**
 * Created by lenovo on 2016/5/22.
 */
public class IssueUiGoto {
    /**
     * 跳转到popup滑窗页
     *
     * @param act
     */
    public static final int POPUP_REQUEST = 0101;
    public static void popup(Activity act) {
        Intent intent = new Intent(act, PopupProductDetails.class);
        act.startActivityForResult(intent,POPUP_REQUEST);
    }

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
     * 跳转到首页
     *
     * @param context
     */

    public static void home(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("tag",0);
        context.startActivity(intent);
    }
    /**
     * 跳转到支付结果页
     *
     * @param context
     */

    public static void payment(Context context) {
        Intent intent = new Intent(context, PaymentSuccessActivity.class);
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
