package com.qluxstory.qingshe.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.qluxstory.qingshe.common.base.SimpleActivity;
import com.qluxstory.qingshe.common.base.SimplePage;

public class UIHelper {
    public static final int SEND_REQUEST = 0x100;//寄送地址之选择地址
    public static void showRorSendFragment(Activity act, SimplePage page,Bundle args) {
        Intent intent = new Intent(act, SimpleActivity.class);
        intent.putExtra(SimpleActivity.BUNDLE_KEY_PAGE, page.getValue());
        intent.putExtra(SimpleActivity.BUNDLE_KEY_ARGS, args);
        act.startActivityForResult(intent,SEND_REQUEST);
    }

    public static final int STORE_REQUEST = 0x102;//门店地址
    public static void showRorStoreFragment(Activity act, SimplePage page,Bundle args) {
        Intent intent = new Intent(act, SimpleActivity.class);
        intent.putExtra(SimpleActivity.BUNDLE_KEY_PAGE, page.getValue());
        intent.putExtra(SimpleActivity.BUNDLE_KEY_ARGS, args);
        act.startActivityForResult(intent,SEND_REQUEST);
    }

    public static final int SELECT_REQUEST = 0x1001;//收货地址之选择地址
    public static void showRorSelectFragment(Activity act, SimplePage page) {
        Intent intent = new Intent(act, SimpleActivity.class);
        intent.putExtra(SimpleActivity.BUNDLE_KEY_PAGE, page.getValue());
        act.startActivityForResult(intent,SELECT_REQUEST);
    }

    public static final int COUPON_REQUEST = 0x110;//代金劵
    public static void showRorCouponFragment(Activity act, SimplePage page,Bundle args) {
        Intent intent = new Intent(act, SimpleActivity.class);
        intent.putExtra(SimpleActivity.BUNDLE_KEY_PAGE, page.getValue());
        intent.putExtra(SimpleActivity.BUNDLE_KEY_ARGS, args);
        act.startActivityForResult(intent,COUPON_REQUEST);
    }

    public static void showFragment(Context context, SimplePage page) {
        Intent intent = new Intent(context, SimpleActivity.class);
        intent.putExtra(SimpleActivity.BUNDLE_KEY_PAGE, page.getValue());
        context.startActivity(intent);
    }

    public static void showFragment(Context context, SimplePage page,
                                    Bundle args) {
        Intent intent = new Intent(context, SimpleActivity.class);
        intent.putExtra(SimpleActivity.BUNDLE_KEY_PAGE, page.getValue());
        intent.putExtra(SimpleActivity.BUNDLE_KEY_ARGS, args);
        context.startActivity(intent);
    }

}
