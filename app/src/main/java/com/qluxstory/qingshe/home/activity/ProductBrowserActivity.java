package com.qluxstory.qingshe.home.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.home.adapter.ProductBrowserTabListAdapter;
import com.qluxstory.qingshe.me.adapter.CuringOrderTabListAdapter;
import com.qluxstory.qingshe.common.base.BaseApplication;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TextViewUtils;
import com.qluxstory.qingshe.common.widget.ProgressWebView;
import com.qluxstory.qingshe.common.widget.SlidingTabLayout;
import com.qluxstory.qingshe.home.HomeUiGoto;
import com.qluxstory.qingshe.home.entity.ProductDetails;
import com.qluxstory.qingshe.home.fragment.ProductBrowserFragment;
import com.qluxstory.qingshe.home.fragment.ProductCommentFragment;
import com.qluxstory.qingshe.me.MeUiGoto;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * 专业养护之商品详情
 */
public class ProductBrowserActivity extends BaseTitleActivity {
    @Bind(R.id.pro_btn_kf)
    LinearLayout mProBottom;
    @Bind(R.id.place_btn)
    Button mPlace;
    @Bind(R.id.placeorder_tv)
    TextView mTv;
    ProductDetails mProductDetails;
    private TextView mBaseEnsure;
    PopupWindow popWindow;
    private ImageView weixin,friend,weibo;
    private TextView text;
    private View mView;
    private String mName,mPic,mPrice,mUrl,mTargetUrl,mDescription,mCode;
    String[] mPermissionList = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS};
    UMImage image;
    boolean bool;
    @Bind(R.id.pb_tab)
    SlidingTabLayout mPbTab;
    @Bind(R.id.pb_content)
    ViewPager mPbContent;
    private ProductBrowserTabListAdapter mAdapter;
    private List<Fragment> fragmentList;
    public static final int TAB_A = 1;
    public static final int TAB_B = 2;


    @Override
    protected int getContentResId() {
        LogUtils.e("getContentResId---", "getContentResId");
        return R.layout.browser_product;
    }


    @Override
    public void initView() {
        //ActivityCompat.requestPermissions(ProductBrowserActivity.this,mPermissionList, 100);
        bool= AppContext.get("isLogin",false);
        LogUtils.e("initView---","initView");
        setTitleText("商品详情");
        mProductDetails = AppContext.getInstance().getProductDetails();
        mBaseEnsure = (TextView) findViewById(R.id.base_titlebar_ensure);
        mBaseEnsure.setVisibility(View.VISIBLE);
        //初始化分享按钮图片大小
        TextViewUtils.setTextViewIcon(this, mBaseEnsure, R.drawable.detaile_share,
                R.dimen.common_titlebar_icon_width,
                R.dimen.common_titlebar_icon_height, TextViewUtils.DRAWABLE_RIGHT);

        mProBottom.setOnClickListener(this);
        mPlace.setOnClickListener(this);



        Intent mIntent = getIntent();
        if (mIntent != null) {
            mPic = mIntent.getStringExtra("pic");
            mCode = mIntent.getStringExtra("code");
            mName = mIntent.getStringExtra("name");
            mPrice = mIntent.getStringExtra("price");
            mUrl = mIntent.getStringExtra("mUrl");
            mDescription =  mIntent.getStringExtra("description");
            LogUtils.e("mPrice----",mPrice);
            image = new UMImage(ProductBrowserActivity.this, AppConfig.BASE_URL+mPic);
            mTargetUrl = AppConfig.BASE_URL+"/JTh5/fenxiang.html?action=001&type=0/1&serveroid="+mCode;
            LogUtils.e("mTargetUrl---",""+mTargetUrl);
            mTv.setText(mPrice);

        }


    }

    @Override
    public void initData() {
        LogUtils.e("initData---", "initData");
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(ProductBrowserFragment.newInstance(TAB_A,mUrl));
        fragmentList.add(ProductCommentFragment.newInstance(TAB_B,mCode));
        String titles[] = getResources().getStringArray(R.array.product_order_tab);
        mAdapter = new ProductBrowserTabListAdapter(getSupportFragmentManager(),this,titles,fragmentList);
        mPbContent.setAdapter(mAdapter);
        mPbContent.setOffscreenPageLimit(fragmentList.size());
        mPbTab.setSelectedIndicatorColors(getResources().getColor(R.color.color_33));
        mPbTab.setDistributeEvenly(true);
        mPbTab.setViewPager(mPbContent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pro_btn_kf:
                if(bool){
                    service();
                }else {
                    MeUiGoto.login(this);//登录
                }
                break;
            case R.id.base_titlebar_ensure:
                baseGoEnsure();
                break;
            case R.id.share_weixin:
                new ShareAction(this).setPlatform(SHARE_MEDIA.WEIXIN).setCallback(umShareListener)
                        .withText(mDescription)
                        .withTitle(mName)
                        .withMedia(image)
                        .withTargetUrl(mTargetUrl)
                        .share();
                break;
            case R.id.share_friend:
                new ShareAction(this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).setCallback(umShareListener)
                        .withText(mDescription)
                        .withTitle(mName)
                        .withMedia(image)
                        .withTargetUrl(mTargetUrl)
                        .share();
                break;
            case R.id.share_weibo:
                new ShareAction(this).setPlatform(SHARE_MEDIA.SINA).setCallback(umShareListener)
                        .withText(mName)
                        .withTitle(mName)
                        .withMedia(image)
                        .share();
                break;
            case R.id.pop_share_text:
                backgroundAlpha(1f);
                popWindow.dismiss();
                break;

            case R.id.place_btn:

                if(bool){
                    HomeUiGoto.placeOrder(this);//提交订单
                }else {
                    MeUiGoto.login(this);//登录
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
        if (getApplicationInfo().packageName
                .equals(BaseApplication.getCurProcessName(getApplicationContext()))) {
                        /*IMKit SDK调用第二步, 建立与服务器的连接*/
            LogUtils.e("mRongyunToken", "" + AppContext.get("mRongyunToken", ""));
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
                 * 连接融云成功
                 *
                 * @param userid 当前
                 *               token
                 */
                @Override
                public void onSuccess(String userid) {
                    LogUtils.e("--onSuccess", "--onSuccess" + userid);
                    /**
                     *启动客服聊天界面。
                     *
                     *@param context 应用上下文。
                     *@param conversationType 开启会话类型。
                     *@param targetId 客服 Id。
                     *@param title 客服标题。*/
//                    RongIM.getInstance().startConversation(ProductBrowserActivity.this,
//                            io.rong.imlib.model.Conversation.ConversationType.APP_PUBLIC_SERVICE,
//                            "KEFU146286268172386", "客服");//开发环境

                    RongIM.getInstance().startConversation(ProductBrowserActivity.this,
                            io.rong.imlib.model.Conversation.ConversationType.APP_PUBLIC_SERVICE,
                            "KEFU146578665455786", "客服");//生产环境
                }

                /*  *
                  *连接融云失败
                  @param
                  errorCode 错误码
                  可到官网 查看错误码对应的注释*/
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    LogUtils.e("--onError", "--onError" + errorCode);
                }
            });
        }
    }



    @Override
    protected void baseGoBack() {
        finish();
//        if (mWebView.canGoBack()) {
//            mWebView.goBack();// 返回前一个页面
//        } else {
//            finish();
//        }
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
            LogUtils.e("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
            popWindow.dismiss();
            LogUtils.e("List_noteTypeActivity:", "dismiss");
        }

    }
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

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
//            mWebView.goBack();// 返回前一个页面
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        LogUtils.e("result","onActivityResult");
    }

}
