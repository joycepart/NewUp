package com.qluxstory.qingshe.common.base;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
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
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.widget.ProgressWebView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;


/**
 * 公用浏览器

 */
public class BrowserActivity extends BaseTitleActivity {
    private TextView mBaseEnsure;
    protected ProgressWebView mWebView;
    protected String strUrl;
    protected String title;
    private ImageView weixin,friend,weibo;
    private TextView text;
    PopupWindow popWindow;
    String[] mPermissionList = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS};

    UMImage image = new UMImage(BrowserActivity.this, "http://www.umeng.com/images/pic/social/integrated_3.png");
    String mUrl = AppConfig.BASE_URL+"//JTh5/fenxiang.html?action=001&type=0/1&serveroid=2016052513592768928";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.requestPermissions(BrowserActivity.this,mPermissionList, 100);
        Intent mIntent = getIntent();
        if (mIntent != null) {
            strUrl = mIntent.getStringExtra("url");
            title = mIntent.getStringExtra("title");
            LogUtils.e("strUrl------------",strUrl);

        }
        super.onCreate(savedInstanceState);
        if (!TextUtils.isEmpty(title)) {
            setTitleText(title);
        }
    }

    @Override
    public void initView() {
        mBaseEnsure = (TextView) findViewById(R.id.base_titlebar_ensure);
        mBaseEnsure.setOnClickListener(this);
        mWebView = (ProgressWebView) findViewById(R.id.browser_webview);
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setOnReceivedTitleListener(new ProgressWebView.onReceivedTitleListener() {

            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (TextUtils.isEmpty(title)) {
                } else {
                }
            }
        });
    }

    @Override
    public void initData() {
        mWebView.loadUrl(strUrl);
    }

    @Override
    protected int getContentResId() {
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

    private void showPopShare() {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.from(this).inflate(R.layout.pop_share, null);
        popWindow = new PopupWindow(view, WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,true);

        // 需要设置一下此参数，点击外边可消失
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击窗口外边窗口消失
        popWindow.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        popWindow.setFocusable(true);
        backgroundAlpha(0.7f);
        //防止虚拟软键盘被弹出菜单遮住
        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


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
            LogUtils.e("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
            popWindow.dismiss();
            LogUtils.e("List_noteTypeActivity:", "dismiss");
        }

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.base_titlebar_ensure:
                showPopShare();
//                final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
//                        {
//                                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.SINA
//                        };
//                new ShareAction(this).setDisplayList( displaylist )
//                        .withText( "呵呵" )
//                        .withTitle("title")
//                        .withTargetUrl("http://www.baidu.com")
//                        .withMedia( image )
//                        .setListenerList(umShareListener)
//                        .open();
//                baseGoEnsure();
                break;
            case R.id.share_weixin:
                new ShareAction(this).setPlatform(SHARE_MEDIA.WEIXIN).setCallback(umShareListener)
                        .withText("hello umeng")
                        .withMedia(image)
                        .withTargetUrl(mUrl)
                        .share();
                break;
            case R.id.share_friend:
                new ShareAction(this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).setCallback(umShareListener)
                        .withText("this is description")
                        .withMedia(image)
                        .withTargetUrl(mUrl)
                        .share();
                break;
            case R.id.share_weibo:
                new ShareAction(this).setPlatform(SHARE_MEDIA.SINA).setCallback(umShareListener)
                        .withText("Umeng Share")
                        .withTitle("this is title")
                        .withMedia(image)
                        .share();;
                break;
            case R.id.pop_share_text:
                LogUtils.e("pop_share_text:", "backgroundAlpha");
                backgroundAlpha(1f);
                popWindow.dismiss();
                LogUtils.e("pop_share_text:", "dismiss");
                break;

        }
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            LogUtils.d("plat","platform"+platform);
            if(platform.name().equals("WEIXIN_FAVORITE")){
                Toast.makeText(BrowserActivity.this,platform + " 收藏成功啦",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(BrowserActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(BrowserActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(BrowserActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
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
