package com.qluxstory.qingshe.home.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseApplication;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.widget.ProgressWebView;
import com.qluxstory.qingshe.home.HomeUiGoto;
import com.qluxstory.qingshe.home.entity.ProductDetails;
import com.qluxstory.qingshe.me.MeUiGoto;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import butterknife.Bind;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

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
    private TextView mBaseEnsure;
    PopupWindow popWindow;
    private ImageView weixin,friend,weibo;
    private TextView text;
    private View mView;
    String[] mPermissionList = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS};

    UMImage image = new UMImage(ProductBrowserActivity.this, "http://www.umeng.com/images/pic/social/integrated_3.png");
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
        mBaseEnsure = (TextView) findViewById(R.id.base_titlebar_ensure);
        mBaseEnsure.setOnClickListener(this);
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
                service();
                break;
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
                        .withTargetUrl("http://www.umeng.com")
                        .share();
                break;
            case R.id.share_friend:
                new ShareAction(this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).setCallback(umShareListener)
                        .withText("this is description")
                        .withMedia(image)
                        .withTargetUrl("http://www.baidu.com")
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
                popWindow.dismiss();
                break;
//            case R.id.pap_share_view:
//                popWindow.dismiss();
//                break;
            case R.id.place_btn:
                Boolean bool= AppContext.get("isLogin",false);
                HomeUiGoto.placeOrder(this);//提交订单
                if(!bool){
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

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            LogUtils.d("plat","platform"+platform);
            if(platform.name().equals("WEIXIN_FAVORITE")){
                Toast.makeText(ProductBrowserActivity.this,platform + " 收藏成功啦",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(ProductBrowserActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ProductBrowserActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ProductBrowserActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    private void service() {
        if (this.getApplicationInfo().packageName
                .equals(BaseApplication.getCurProcessName(this.getApplicationContext()))) {
                        /*IMKit SDK调用第二步, 建立与服务器的连接*/
            RongIM.connect(AppContext.get("mRongyunToken", ""), new RongIMClient.ConnectCallback() {
                /*  *
                  *
                  Token 错误
                  ，
                  在线上环境下主要是因为 Token
                  已经过期，
                  您需要向 App
                  Server 重新请求一个新的
                  Token*/
                @Override
                public void onTokenIncorrect() {
                    LogUtils.e("", "--onTokenIncorrect");
                }

                /**
                 *连接融云成功
                 *
                 @param
                 userid 当前
                 token*/
                @Override
                public void onSuccess(String userid) {
                    LogUtils.e("", "--onSuccess" + userid);
                    /**
                     *启动客服聊天界面。
                     *
                     *@param context 应用上下文。
                     *@param conversationType 开启会话类型。
                     *@param targetId 客服 Id。
                     *@param title 客服标题。*/
                    RongIM.getInstance().startConversation(ProductBrowserActivity.this,
                            io.rong.imlib.model.Conversation.ConversationType.APP_PUBLIC_SERVICE,
                            "25wehl3uwcuuw", "客服");
                }

                /*  *
                  *连接融云失败
                  @param
                  errorCode 错误码
                  可到官网 查看错误码对应的注释*/
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    LogUtils.e("", "--onError" + errorCode);
                }
            });
        }
        MeUiGoto.serviceRoyun(this);//客服



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
        showPopShare();
    }

    private void showPopShare() {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.from(this).inflate(R.layout.pop_share, null);
//        popMenus = new Popup(view,300,300,true);
        popWindow = new PopupWindow(view, WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,true);
        // 需要设置一下此参数，点击外边可消失
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击窗口外边窗口消失
        popWindow.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        popWindow.setFocusable(true);
        //防止虚拟软键盘被弹出菜单遮住
        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        mView = (View) view
//                .findViewById(R.id.pap_share_view);
        weixin = (ImageView) view
                .findViewById(R.id.share_weixin);
        friend = (ImageView) view
                .findViewById(R.id.share_friend);
        weibo = (ImageView) view
                .findViewById(R.id.share_weibo);
        text = (TextView) view
                .findViewById(R.id.pop_share_text);
        mView.setOnClickListener(this);
        weixin.setOnClickListener(this);
        friend.setOnClickListener(this);
        weibo.setOnClickListener(this);
        View parent = getWindow().getDecorView();//高度为手机实际的像素高度
        popWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);

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
