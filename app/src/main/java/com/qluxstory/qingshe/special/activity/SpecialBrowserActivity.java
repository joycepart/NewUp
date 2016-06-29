package com.qluxstory.qingshe.special.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TextViewUtils;
import com.qluxstory.qingshe.common.widget.ProgressWebView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

/**
 * 热门专题
 */
public class SpecialBrowserActivity extends BaseTitleActivity {
    private TextView mBaseEnsure;
    protected ProgressWebView mWebView;
    protected String strUrl;
    protected String title;
    protected String id;
    private ImageView weixin,friend,weibo;
    private TextView text;
    PopupWindow popWindow;
    String[] mPermissionList = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS};

    UMImage image ;
//    UMImage image = new UMImage(SpecialBrowserActivity.this, "http://www.umeng.com/images/pic/social/integrated_3.png");
    String mTargetUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtils.e("onCreate---1","onCreate1");
//        ActivityCompat.requestPermissions(SpecialBrowserActivity.this,mPermissionList, 100);

        image = new UMImage(SpecialBrowserActivity.this, R.drawable.logo);
        Intent mIntent = getIntent();
        if (mIntent != null) {
            strUrl = mIntent.getStringExtra("url");
            title = mIntent.getStringExtra("title");
            id = mIntent.getStringExtra("id");
            LogUtils.e("strUrl------------",""+strUrl);
            LogUtils.e("title------------",""+title);
            mTargetUrl =  AppConfig.BASE_URL+"/JTh5/Zhuanti.html?specialid="+id;

        }
        super.onCreate(savedInstanceState);
        if (!TextUtils.isEmpty(title)) {
            setTitleText(title);
        }
        LogUtils.e("onCreate---2","onCreate2");

    }

    @Override
    public void initView() {
        LogUtils.e("initView---","initView");
        mWebView = (ProgressWebView) findViewById(R.id.browser_webview);
        mWebView.setWebViewClient(new MyWebViewClient());


        mBaseEnsure = (TextView) findViewById(R.id.base_titlebar_ensure);
        //初始化分享按钮图片大小
        TextViewUtils.setTextViewIcon(this, mBaseEnsure, R.drawable.detaile_share,
                R.dimen.common_titlebar_icon_width,
                R.dimen.common_titlebar_icon_height, TextViewUtils.DRAWABLE_RIGHT);
        mBaseEnsure.setOnClickListener(this);

    }

    @Override
    public void initData() {
        LogUtils.e("initData---","initData");
        mWebView.loadUrl(strUrl);
    }

    @Override
    protected int getContentResId() {
        LogUtils.e("getContentResId---","getContentResId");
        return R.layout.browser;
    }

    @Override
    protected void baseGoBack() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();// 返回前一个页面
        } else {
            finish();
        }
    }

    @Override
    protected void baseGoEnsure() {
        showPopShare();
    }
    View vw;
    private void showPopShare() {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.from(this).inflate(R.layout.pop_share_test, null);
        popWindow = new PopupWindow(view, WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,true);

        // 需要设置一下此参数，点击外边可消失
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击窗口外边窗口消失
        popWindow.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        popWindow.setFocusable(true);
        //防止虚拟软键盘被弹出菜单遮住
        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        backgroundAlpha(0.7f);

        weixin = (ImageView) view
                .findViewById(R.id.share_weixin);
        friend = (ImageView) view
                .findViewById(R.id.share_friend);
        weibo = (ImageView) view
                .findViewById(R.id.share_weibo);
        text = (TextView) view
                .findViewById(R.id.pop_share_text);
        weixin.setOnClickListener(this);
        friend.setOnClickListener(this);
        weibo.setOnClickListener(this);
        text.setOnClickListener(this);

        View parent = getWindow().getDecorView();//高度为手机实际的像素高度
        popWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        //添加pop窗口关闭事件
        popWindow.setOnDismissListener(new poponDismissListener());

    }
    public class poponDismissListener implements PopupWindow.OnDismissListener{

        @Override
        public void onDismiss() {
            LogUtils.e("List_noteTypeActivity:", "我是关闭事件1");
            popWindow.dismiss();
            backgroundAlpha(1f);
            popWindow.dismiss();
            LogUtils.e("List_noteTypeActivity:", "我是关闭事件2");
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        popWindow.dismiss();
        return super.onTouchEvent(event);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.base_titlebar_ensure:
                showPopShare();
                break;
            case R.id.share_weixin:
                new ShareAction(this).setPlatform(SHARE_MEDIA.WEIXIN).setCallback(umShareListener)
                        .withText(title)
                        .withTitle(title)
                        .withMedia(image)
                        .withTargetUrl(mTargetUrl)
                        .share();
                break;
            case R.id.share_friend:
                new ShareAction(this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).setCallback(umShareListener)
                        .withText(title)
                        .withTitle(title)
                        .withMedia(image)
                        .withTargetUrl(mTargetUrl)
                        .share();
                break;
            case R.id.share_weibo:
                new ShareAction(this).setPlatform(SHARE_MEDIA.SINA).setCallback(umShareListener)
                        .withText(title)
                        .withTitle(title)
                        .withMedia(image)
                        .share();;
                break;
            case R.id.pop_share_text:
                LogUtils.e("pop_share_text:", "我是关闭事件1");
                popWindow.dismiss();
                backgroundAlpha(1f);
                LogUtils.e("pop_share_text:", "我是关闭事件2");
                break;

        }
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            LogUtils.e("onResult---","platform"+platform);
            if(platform.name().equals("WEIXIN_FAVORITE")){
                Toast.makeText(SpecialBrowserActivity.this,platform + " 收藏成功啦",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(SpecialBrowserActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            LogUtils.e("onError----","platform"+platform);
            Toast.makeText(SpecialBrowserActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(SpecialBrowserActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    class MyWebViewClient extends WebViewClient {
        // 重写shouldOverrideUrlLoading方法，使点击链接后不使用其他的浏览器打开。
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("tel:")) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            } else {
                view.loadUrl(url);
            }
            // 如果不需要其他对点击链接事件的处理返回true，否则返回false
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        LogUtils.e("result","onActivityResult");
    }

}
