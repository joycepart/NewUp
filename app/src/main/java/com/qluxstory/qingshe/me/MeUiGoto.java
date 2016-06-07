package com.qluxstory.qingshe.me;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.qluxstory.qingshe.common.base.BrowserActivity;
import com.qluxstory.qingshe.common.widget.SelectPicPopup;
import com.qluxstory.qingshe.me.activity.CuringOrderActivity;
import com.qluxstory.qingshe.me.activity.CuringOrderDetailsActivity;
import com.qluxstory.qingshe.me.activity.DetailActivity;
import com.qluxstory.qingshe.me.activity.IndianaDetailsActivity;
import com.qluxstory.qingshe.me.activity.LoginActivity;
import com.qluxstory.qingshe.me.activity.ModifyUserActivity;
import com.qluxstory.qingshe.me.activity.MyIncomeActivity;
import com.qluxstory.qingshe.me.activity.PaymentOrderActivity;
import com.qluxstory.qingshe.me.activity.UserInformationActivity;
import com.qluxstory.qingshe.me.activity.WithdrawalsActivity;

/**
 * Created by lenovo on 2016/5/17.
 */


public class MeUiGoto {
    /**
     * 跳转到登录页
     * @param context
     */
    public static final int LOFIN_REQUEST = 0x1100;
    public static void login(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivityForResult(intent, LOFIN_REQUEST);
    }


    /**
     * 跳转到养护订单页
     * @param context
     */

    public static void maintenanceOrder(Context context){
        Intent intent = new Intent(context, CuringOrderActivity.class);
//        Intent intent = new Intent(context, SimpleActivity.class);
        context.startActivity(intent);
    }


    /**
     * 跳转到我的收入页
     * @param context
     */

    public static void myIncome(Context context){
        Intent intent = new Intent(context, MyIncomeActivity.class);
        context.startActivity(intent);
    }

    /**
     * 夺宝记录之夺宝详情
     * @param context
     * @param b
     */

    public static void indianaRecords(Context context, Bundle b){
        Intent intent = new Intent(context, IndianaDetailsActivity.class);
        intent.putExtra("itemBean",b);
        context.startActivity(intent);
    }


    /**
     * 跳转到申请提现页
     * @param context
     */

    public static void withd(Context context){
        Intent intent = new Intent(context,WithdrawalsActivity.class);
        context.startActivity(intent);
    }
    /**
     * 跳转到明细详情页
     * @param context
     * @param bundle
     */

    public static void details(Context context, Bundle bundle){
        Intent intent = new Intent(context,DetailActivity.class);
        intent.putExtra("bundle",bundle);
        context.startActivity(intent);
    }

    /**
     * 跳转到养护订单详情页
     * @param context
     * @param b
     */

    public static void curingOrderdetails(Context context, Bundle b){
        Intent intent = new Intent(context,CuringOrderDetailsActivity.class);
        intent.putExtra("bundle",b);
        context.startActivity(intent);
    }

    /**
     * 跳转到支付订单页
     * @param context
     * @param b
     */

    public static void payment(Context context, Bundle b){
        Intent intent = new Intent(context,PaymentOrderActivity.class);
        intent.putExtra("bundle",b);
        context.startActivity(intent);
    }


    /**
     * 跳转到用户信息页
     * @param context
     */
    public static final int USERINFORMATION_REQUEST = 0x201;
    public static void userInformation(Activity act){
        Intent intent = new Intent(act,UserInformationActivity.class);
        act.startActivityForResult(intent,USERINFORMATION_REQUEST);
    }
    /**
     * 跳转到修改用户名页
     * @param context
     */
    public static final int MODIFYUSER_REQUEST = 0x101;
    public static void modifyUser(Activity act){
        Intent intent = new Intent(act,ModifyUserActivity.class);
        act.startActivityForResult(intent,MODIFYUSER_REQUEST);
    }

    /**
     * 跳转到调用系统拍照和相册页
     * @param context
     */
    public static final int SELECT_REQUEST = 0x111;
    public static void selectPic(Activity act){
        Intent intent = new Intent(act,SelectPicPopup.class);
        act.startActivityForResult(intent,SELECT_REQUEST);
    }

    /**
     * 跳转到关于详情页（加载h5页面）
     *
     * @param context
     * @param mUrlUs
     * @param mUsTitle
     */

    public static void aboutUs(Context context, String mUrlUs, String mUsTitle){
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra("url", mUrlUs);
        intent.putExtra("title", mUsTitle);
        context.startActivity(intent);
    }
    /**
     * 跳转到退货详情页（加载h5页面）
     *
     * @param context
     * @param mUrlReturn
     * @param mReturnTitle
     */

    public static void rturn(Context context, String mUrlReturn, String mReturnTitle){
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra("url", mUrlReturn);
        intent.putExtra("title", mReturnTitle);
        context.startActivity(intent);
    }
    /**
     * 跳转到用户注册协议页（加载h5页面）
     *
     * @param context
     * @param mUrlAgreement
     * @param mAgreementTitle
     */

    public static void registration(Context context, String mUrlAgreement, String mAgreementTitle){
        Intent intent = new Intent(context,BrowserActivity.class);
        Bundle sb = new Bundle();
        sb.putString("url", mUrlAgreement);
        sb.putString("title", mAgreementTitle);
        context.startActivity(intent);
    }

    /**
     * 跳转官方QQ群详情页（加载h5页面）
     *
     * @param context
     * @param mUrlQq
     * @param mQqTitle
     */

    public static void qq(Context context, String mUrlQq, String mQqTitle){
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra("url", mUrlQq);
        intent.putExtra("title", mQqTitle);
        context.startActivity(intent);
    }
    /**
     * 跳转到版本详情页（加载h5页面）
     *
     * @param context
     * @param mUrlInformation
     * @param mInformationTitle
     */

    public static void sinformation(Context context, String mUrlInformation, String mInformationTitle){
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra("url", mUrlInformation);
        intent.putExtra("title", mInformationTitle);
        context.startActivity(intent);
    }
}
