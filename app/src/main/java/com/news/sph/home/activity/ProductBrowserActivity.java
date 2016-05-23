package com.news.sph.home.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import com.news.sph.R;
import com.news.sph.common.base.BaseTitleActivity;
import com.news.sph.home.utils.HomeUiGoto;
import com.news.sph.widget.ProgressWebView;
import com.news.sph.widget.SlidingTabLayout;

import butterknife.Bind;

/**
 * Created by lenovo on 2016/5/18.
 */
public class ProductBrowserActivity extends BaseTitleActivity {
    @Bind(R.id.browser_product_webview)
    ProgressWebView mWebView;
    @Bind(R.id.prod_btm_kf)
    LinearLayout mPrldLin;
    @Bind(R.id.place_btn)
    Button mPlace;
    protected String strUrl;
    protected String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent mIntent = getIntent();
        if (mIntent != null) {
            strUrl = mIntent.getStringExtra("url");
            title = mIntent.getStringExtra("title");
        }
        super.onCreate(savedInstanceState);
        if (!TextUtils.isEmpty(title)) {
            setTitleText(title);
        }
    }

    @Override
    public void initView() {
        mPrldLin.setOnClickListener(this);
        mPlace.setOnClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prod_btm_kf:
                break;
            case R.id.place_btn:
                HomeUiGoto.placeOrder(this);
                break;
            default:
                break;
        }
        super.onClick(v);
    }

    @Override
    public void initData() {

        mWebView.loadUrl(strUrl);



    }

    @Override
    protected int getContentResId() {
        return R.layout.browser_product;
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
    }

}
