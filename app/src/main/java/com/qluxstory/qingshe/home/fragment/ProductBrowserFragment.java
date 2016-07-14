package com.qluxstory.qingshe.home.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseFragment;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.widget.ProgressWebView;
import com.qluxstory.qingshe.common.widget.SlidingTabLayout;
import com.qluxstory.qingshe.curing.adapter.CuringTabListAdapter;
import com.umeng.socialize.media.UMImage;

import java.util.List;

import butterknife.Bind;

/**
 * Created by lenovo on 2016/7/4.
 */
public class ProductBrowserFragment extends BaseFragment {
    @Bind(R.id.browser_product_webview)
    ProgressWebView mWebView;
    private static final String TYPE = "type";
    private int type;
    private String mUrl;

    public static ProductBrowserFragment newInstance(int type, String url) {
        ProductBrowserFragment fragment = new ProductBrowserFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);//传递Type
        bundle.putString("url",url);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    protected void retry() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_productbrowser;
    }

    @Override
    public void initView(View view) {
        Bundle b  = getArguments();
        LogUtils.e("b----",""+b);
        if (b != null) {
            mUrl = b.getString("url");
            LogUtils.e("mUrl----",""+mUrl);
            mWebView = (ProgressWebView) getActivity().findViewById(R.id.browser_product_webview);
            mWebView.setWebViewClient(new MyWebViewClient());
            mWebView.loadUrl(mUrl);

        }

    }

    @Override
    public void initData() {

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
