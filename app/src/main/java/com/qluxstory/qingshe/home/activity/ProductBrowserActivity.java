package com.qluxstory.qingshe.home.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.widget.ProgressWebView;
import com.qluxstory.qingshe.home.HomeUiGoto;
import com.qluxstory.qingshe.home.entity.ProductDetails;
import com.qluxstory.qingshe.me.MeUiGoto;

import butterknife.Bind;

/**
 * 专业养护之商品详情之加载h5页面
 */
public class ProductBrowserActivity extends BaseTitleActivity {
    @Bind(R.id.browser_product_webview)
    ProgressWebView mWebView;
    @Bind(R.id.pro_bottom_kf)
    LinearLayout mProBottom;
    @Bind(R.id.place_btn)
    Button mPlace;
    @Bind(R.id.placeorder_tv)
    TextView mTv;
    protected String mStrUrl;
    ProductDetails mProductDetails;
    @Override
    protected int getContentResId() {
        return R.layout.browser_product;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView() {
        mProductDetails = AppContext.getInstance().getProductDetails();
        setTitleText("商品详情");
        Intent intent = getIntent();
        if(intent!=null){
            mStrUrl = intent.getStringExtra("mUrl");
        }
        mTv.setText(mProductDetails.getSellPrice());
        mProBottom.setOnClickListener(this);
        mPlace.setOnClickListener(this);
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setOnReceivedTitleListener(new ProgressWebView.onReceivedTitleListener() {

            @Override
            public void onReceivedTitle(WebView view, String title) {
//                if (TextUtils.isEmpty(title)) {
//                } else {
//                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pro_bottom_kf:
                break;
            case R.id.place_btn:
                Boolean bool= AppContext.get("isLogin",true);
                if(bool!=true){
                    MeUiGoto.login(this);//登录
                }else {
                    HomeUiGoto.placeOrder(this);//提交订单
                }
                break;
            default:
                break;
        }
        super.onClick(v);
    }

    @Override
    public void initData() {
        mWebView.loadUrl(mStrUrl);
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
