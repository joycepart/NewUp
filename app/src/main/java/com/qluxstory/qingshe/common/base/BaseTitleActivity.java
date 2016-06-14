package com.qluxstory.qingshe.common.base;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TextViewUtils;
import com.qluxstory.qingshe.common.utils.ToastUtils;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * 带有标题的的基类
 */
public abstract class BaseTitleActivity extends BaseActivity {
    private TextView mBaseTitle, mBaseEnsure, mBaseBack;
    private View mTitleLayout;
    protected int mCurrentPage = 1;
    protected final static int PAGE_SIZE = 6;
    private ImageView weixin,friend,weibo;
    private TextView text;
    private View mView;
    PopupWindow popWindow;


    protected void onAfterSetContentLayout() {
        LinearLayout llContent = (LinearLayout) findViewById(R.id.base_titlebar_content);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(getContentResId(), null);
        llContent.addView(v, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        baseInitView();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.base_titlebar_activity;
    }

    public View getTitleLayout() {
        return mTitleLayout;
    }

    private void baseInitView() {

        mTitleLayout = findViewById(R.id.base_titlebar_layout);
        mBaseTitle = (TextView) findViewById(R.id.base_titlebar_text);
        mBaseEnsure = (TextView) findViewById(R.id.base_titlebar_ensure);
        mBaseEnsure.setOnClickListener(this);
        mBaseBack = (TextView) findViewById(R.id.base_titlebar_back);
        mBaseBack.setOnClickListener(this);

        // 初始化返回按钮图片大小
        TextViewUtils.setTextViewIcon(this, mBaseBack, R.drawable.back,
                R.dimen.common_titlebar_icon_width,
                R.dimen.common_titlebar_icon_height, TextViewUtils.DRAWABLE_LEFT);

        // 初始化分享按钮图片大小
        TextViewUtils.setTextViewIcon(this, mBaseEnsure, R.drawable.detaile_share,
                R.dimen.common_titlebar_icon_width,
                R.dimen.common_titlebar_icon_height, TextViewUtils.DRAWABLE_RIGHT);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
            case R.id.base_titlebar_ensure:
                baseGoEnsure();
                break;
//            case R.id.base_titlebar_ensure:
//                showPopShare();
////                final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
////                        {
////                                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.SINA
////                        };
////                new ShareAction(this).setDisplayList( displaylist )
////                        .withText( "呵呵" )
////                        .withTitle("title")
////                        .withTargetUrl("http://www.baidu.com")
////                        .withMedia( image )
////                        .setListenerList(umShareListener)
////                        .open();
////                baseGoEnsure();
//                break;
//            case R.id.share_weixin:
//                new ShareAction(this).setPlatform(SHARE_MEDIA.WEIXIN).setCallback(umShareListener)
//                        .withText("hello umeng")
//                        .withMedia(image)
//                        .withTargetUrl("http://www.umeng.com")
//                        .share();
//                break;
//            case R.id.share_friend:
//                new ShareAction(this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).setCallback(umShareListener)
//                        .withText("this is description")
//                        .withMedia(image)
//                        .withTargetUrl("http://www.baidu.com")
//                        .share();
//                break;
//            case R.id.share_weibo:
//                new ShareAction(this).setPlatform(SHARE_MEDIA.SINA).setCallback(umShareListener)
//                        .withText("Umeng Share")
//                        .withTitle("this is title")
//                        .withMedia(image)
//                        .share();;
//                break;
//            case R.id.pop_share_text:
//                popWindow.dismiss();
//                break;
//            case R.id.pap_share_view:
//                popWindow.dismiss();
//                break;
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
                ToastUtils.showShort(BaseTitleActivity.this," 收藏成功啦");
            }else{
                ToastUtils.showShort(BaseTitleActivity.this," 分享成功啦");
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtils.showShort(BaseTitleActivity.this," 分享失败啦");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtils.showShort(BaseTitleActivity.this," 分享取消了");
        }
    };

    /**
     * 设置标题文字
     *
     * @param title
     */
    public void setTitleText(String title) {
        mBaseTitle.setText(title);
    }

    /**
     * 设置标题文字
     *
     * @param resid
     */
    public void setTitleText(int resid) {
        mBaseTitle.setText(resid);
    }

    /**
     * 获取标题控件
     *
     * @return
     */
    public TextView getTitleTextView() {
        return mBaseTitle;
    }

    /**
     * 设置右侧文字
     *
     * @param text
     */
    public void setEnsureText(String text) {
        mBaseEnsure.setText(text);
    }

    /**
     * 设置右侧点击按钮图片
     *
     * @param resId
     */
    public void setEnsureDrawable(int resId, int where) {
        TextViewUtils.setTextViewIcon(this, mBaseEnsure, resId,
                R.dimen.common_titlebar_right_icon_width,
                R.dimen.common_titlebar_right_icon_height, where);
    }

    public void setEnsureText(int resid) {
        mBaseEnsure.setText(resid);
    }

    public TextView getEnsureView() {
        return mBaseEnsure;
    }

    public void setEnsureEnable(boolean flag) {
        mBaseEnsure.setClickable(flag);
        mBaseEnsure.setEnabled(flag);
    }

    /**
     * 左侧的按钮事件
     */
    protected void baseGoBack() {
        finish();
    }

    /**
     * 右侧的按钮事件
     */
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
        mView = (View) view
                .findViewById(R.id.pap_share_view);
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

    /**
     * 布局文件
     *
     * @return
     */
    protected abstract int getContentResId();
}