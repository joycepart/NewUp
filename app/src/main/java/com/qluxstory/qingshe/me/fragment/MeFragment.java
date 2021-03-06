package com.qluxstory.qingshe.me.fragment;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseApplication;
import com.qluxstory.qingshe.common.base.BaseFragment;
import com.qluxstory.qingshe.common.base.SimplePage;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.UIHelper;
import com.qluxstory.qingshe.me.MeUiGoto;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;


public class MeFragment extends BaseFragment {

    @Bind(R.id.user_btn)
    Button mUserBtn;
    @Bind(R.id.login_ll)
    LinearLayout mLoginLl;
    @Bind(R.id.loing_suc)
    RelativeLayout mLoingSuc;
    @Bind(R.id.login_tv_name)
    TextView mLoingTvName;
    @Bind(R.id.login_img_pic)
    ImageView mLoginImgPic;
    @Bind(R.id.user_ll_curing)
    LinearLayout mUserLlCuring;
    @Bind(R.id.user_ll_indiana)
    LinearLayout mUserLlIndiana;
    @Bind(R.id.user_ll_coupon)
    LinearLayout mUserLlCoupon;
    @Bind(R.id.user_ll_income)
    LinearLayout mUserLlIncome;
    @Bind(R.id.user_ll_about)
    LinearLayout mUserLlAbout;
    @Bind(R.id.user_ll_service)
    LinearLayout mUserLlService;
    @Bind(R.id.user_ll_ipone)
    LinearLayout mUserLlIpone;
    @Bind(R.id.user_ll_inf)
    LinearLayout mUserLlInf;
    @Bind(R.id.user_btn_close)
    Button mUserBtnClose;
    @Bind(R.id.me_ll_close)
    LinearLayout mMeLlClose;
    @Bind(R.id.user_ll_integral)
    LinearLayout mIntegral;
    private String mUrlUs;
    private String mUsTitle;
    private String mUrlQq;
    private String mQqTitle;
    private String mUrlInformation;
    private String mInformationTitle;
    private String userName;
    private String pictruePath;
    boolean bool;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_me;
    }

    @Override
    public void retry() {

    }

    @Override
    public void initView(View view) {
        bool = AppContext.get("isLogin",false);
        if(bool){
            mLoginLl.setVisibility(View.GONE);
            mLoingSuc.setVisibility(View.VISIBLE);
            mMeLlClose.setVisibility(View.VISIBLE);
            userName = AppContext.get("mUserName","");
            pictruePath = AppContext.get("mPictruePath","");
            mLoingTvName.setText(userName);
            ImageLoaderUtils.displayAvatarImage(pictruePath,
                    mLoginImgPic);

        }else {
            mLoginLl.setVisibility(View.VISIBLE);
            mLoingSuc.setVisibility(View.GONE);
            mMeLlClose.setVisibility(View.GONE);

        }

    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.user_btn,R.id.user_ll_integral, R.id.loing_suc, R.id.user_ll_curing, R.id.user_ll_indiana, R.id.user_ll_coupon, R.id.user_ll_income, R.id.user_ll_about, R.id.user_ll_service, R.id.user_ll_ipone,R.id.user_ll_inf, R.id.user_btn_close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_btn:
                MeUiGoto.login(getActivity());//登录
                break;
            case R.id.loing_suc:
                MeUiGoto.userInformation(getActivity());//用户信息
                break;
            case R.id.user_ll_curing:
                if(bool){
                    MeUiGoto.maintenanceOrder(getActivity());//养护订单
                }else {
                    MeUiGoto.login(getActivity());//登录
                }

                break;
            case R.id.user_ll_indiana:
                if(bool){
                    UIHelper.showFragment(getActivity(), SimplePage.INDIANA_RECORDS);//夺宝记录
                }else {
                    MeUiGoto.login(getActivity());//登录
                }

                break;
            case R.id.user_ll_coupon:
                if(bool){
                    UIHelper.showFragment(getActivity(), SimplePage.MY_COUPON);//我的优惠劵
                }else {
                    MeUiGoto.login(getActivity());//登录
                }

                break;
            case R.id.user_ll_income:
                if(bool){
                    MeUiGoto.myIncome(getActivity());//我的收入
                }else {
                    MeUiGoto.login(getActivity());//登录
                }

                break;

            case R.id.user_ll_integral:
                if(bool){
                    MeUiGoto.myIntegral(getActivity());//我的积分
                }else {
                    MeUiGoto.login(getActivity());//登录
                }
                break;
            case R.id.user_ll_about:
                mUrlUs = AppConfig.URL_ABOUT_US;
                mUsTitle = "关于我们";
                MeUiGoto.aboutUs(getActivity(), mUrlUs, mUsTitle);//关于我们
                break;
            case R.id.user_ll_service:
                if(bool){
                    service();
                }else {
                    MeUiGoto.login(getActivity());//登录
                }

                break;
            case R.id.user_ll_ipone:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                        + getString(R.string.common_service_phone)));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent); //电话客服
                break;

            case R.id.user_btn_close:
                mLoginLl.setVisibility(View.VISIBLE);
                mLoingSuc.setVisibility(View.GONE);
                mMeLlClose.setVisibility(View.GONE);
                AppContext.set("mobileNum","");
                AppContext.set("isLogin",false);
                AppContext.set("alias","");
                AppContext.set("mRongyunToken", "");
                break;
        }
    }

    private void service() {
        if (getActivity().getApplicationInfo().packageName
                .equals(BaseApplication.getCurProcessName(getActivity().getApplicationContext()))) {
                        /*IMKit SDK调用第二步, 建立与服务器的连接*/
            LogUtils.e("mRongyunToken",""+AppContext.get("mRongyunToken", ""));
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

                    RongIM.getInstance().startConversation(getActivity(),
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


}
