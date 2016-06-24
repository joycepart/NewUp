package com.qluxstory.qingshe.common.base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.widget.ProgressWebView;


/**
 * 公用浏览器

 */
public class BrowserActivity extends BaseTitleActivity {
    protected ProgressWebView mWebView;
    protected String strUrl;
    protected String title;
    protected String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        LogUtils.e("onCreate---","onCreate");
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
        LogUtils.e("initView---","initView");
        mWebView = (ProgressWebView) findViewById(R.id.browser_webview);
        mWebView.setWebViewClient(new MyWebViewClient());


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

}
