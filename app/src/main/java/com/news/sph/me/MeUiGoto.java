package com.news.sph.me;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.news.sph.MainActivity;
import com.news.sph.common.base.BrowserActivity;
import com.news.sph.me.activity.CuringOrderActivity;
import com.news.sph.me.activity.DetailActivity;
import com.news.sph.me.activity.IndianaDetailsActivity;
import com.news.sph.me.activity.LoginActivity;
import com.news.sph.me.activity.ModifyUserActivity;
import com.news.sph.me.activity.MyIncomeActivity;
import com.news.sph.me.activity.UserInformationActivity;
import com.news.sph.me.activity.WithdrawalsActivity;

/**
 * Created by lenovo on 2016/5/17.
 */


public class MeUiGoto {
    /**
     * 跳转到登录页
     * @param context
     */
    public static final int LOFIN_REQUEST = 0x100;
    public static void login(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);

        ((MainActivity)context).startActivityForResult(intent, LOFIN_REQUEST);
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
     */

    public static void indianaRecords(Context context){
        Intent intent = new Intent(context, IndianaDetailsActivity.class);
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
     */

    public static void details(Context context){
        Intent intent = new Intent(context,DetailActivity.class);
        context.startActivity(intent);
    }
    /**
     * 跳转到用户信息页
     * @param context
     */
    public static final int USERINFORMATION_REQUEST = 0x101;
    public static void userInformation(Context context){
        Intent intent = new Intent(context,UserInformationActivity.class);
        ((MainActivity)context).startActivityForResult(intent,USERINFORMATION_REQUEST);
    }
    /**
     * 跳转到修改用户名页
     * @param context
     */
    public static final int MODIFYUSER_REQUEST = 0x101;
    public static void modifyUser(Context context){
        Intent intent = new Intent(context,ModifyUserActivity.class);
        ((UserInformationActivity)context).startActivityForResult(intent,MODIFYUSER_REQUEST);
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
