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
import com.qluxstory.qingshe.common.base.BaseFragment;
import com.qluxstory.qingshe.common.base.SimplePage;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.UIHelper;
import com.qluxstory.qingshe.me.MeUiGoto;
import com.qluxstory.qingshe.me.entity.User;

import butterknife.Bind;
import butterknife.OnClick;


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
    @Bind(R.id.user_ll_return)
    LinearLayout mUserLlReturn;
    @Bind(R.id.user_ll_service)
    LinearLayout mUserLlService;
    @Bind(R.id.user_ll_ipone)
    LinearLayout mUserLlIpone;
    @Bind(R.id.user_ll_qq)
    LinearLayout mUserLlQq;
    @Bind(R.id.user_ll_inf)
    LinearLayout mUserLlInf;
    @Bind(R.id.user_btn_close)
    Button mUserBtnClose;
    @Bind(R.id.me_ll_close)
    LinearLayout mMeLlClose;
    private String mUrlUs;
    private String mUsTitle;
    private String mUrlReturn;
    private String mReturnTitle;
    private String mUrlQq;
    private String mQqTitle;
    private String mUrlInformation;
    private String mInformationTitle;
    private String userName;
    private String pictruePath;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_me;
    }

    @Override
    public void retry() {

    }

    @Override
    public void initView(View view) {
        User user =AppContext.getInstance().getUser();
        if(user!=null && user.getFlag()==true){
            mLoginLl.setVisibility(View.GONE);
            mLoingSuc.setVisibility(View.VISIBLE);
            mMeLlClose.setVisibility(View.VISIBLE);
            userName = user.getmUserName();
            pictruePath = user.getmPictruePath();
            mLoingTvName.setText(userName);
            ImageLoaderUtils.displayImage(pictruePath,mLoginImgPic);

        }else {
            mLoginLl.setVisibility(View.VISIBLE);
            mLoingSuc.setVisibility(View.GONE);
            mMeLlClose.setVisibility(View.GONE);
        }
    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.user_btn, R.id.loing_suc, R.id.user_ll_curing, R.id.user_ll_indiana, R.id.user_ll_coupon, R.id.user_ll_income, R.id.user_ll_about, R.id.user_ll_return, R.id.user_ll_service, R.id.user_ll_ipone, R.id.user_ll_qq,R.id.user_ll_inf, R.id.user_btn_close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_btn:
                MeUiGoto.login(getActivity());//登录
                break;
            case R.id.loing_suc:
                MeUiGoto.userInformation(getActivity());//用户信息
                break;
            case R.id.user_ll_curing:
                MeUiGoto.maintenanceOrder(getActivity());//养护订单
                break;
            case R.id.user_ll_indiana:
                UIHelper.showFragment(getActivity(), SimplePage.INDIANA_RECORDS);//夺宝记录
                break;
            case R.id.user_ll_coupon:
                UIHelper.showFragment(getActivity(), SimplePage.MY_COUPON);//我的优惠劵
                break;
            case R.id.user_ll_income:
                MeUiGoto.myIncome(getActivity());//我的收入
                break;
            case R.id.user_ll_about:
                mUrlUs = AppConfig.URL_ABOUT_US;
                mUsTitle = "关于我们 - 倾奢介绍";
                MeUiGoto.aboutUs(getActivity(), mUrlUs, mUsTitle);//关于我们
                break;
            case R.id.user_ll_return:
                mUrlReturn = AppConfig.URL_ABOUT_RETURN;
                mReturnTitle = "关于退货 - 倾奢";
                MeUiGoto.rturn(getActivity(), mUrlReturn, mReturnTitle);//关于退货
                break;
            case R.id.user_ll_service:
                break;
            case R.id.user_ll_ipone:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                        + getString(R.string.common_service_phone)));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent); //电话客服
                break;
            case R.id.user_ll_qq:
                mUrlQq = AppConfig.URL_QQ;
                mQqTitle = "官方QQ群 - 倾奢";
                MeUiGoto.qq(getActivity(), mUrlQq, mQqTitle);//官方QQ群
                break;
            case R.id.user_ll_inf:
                mUrlInformation = AppConfig.URL_INFORMATION;
                mInformationTitle = "版本信息";
                MeUiGoto.sinformation(getActivity(), mUrlQq, mQqTitle);//版本信息
                break;
            case R.id.user_btn_close:
                mLoginLl.setVisibility(View.VISIBLE);
                mLoingSuc.setVisibility(View.GONE);
                mMeLlClose.setVisibility(View.GONE);
                AppContext.set("isLogin",false);
                AppContext.getInstance().getUser().setFlag(false);
                break;
        }
    }

    public void updata(){
        mLoingTvName.setText(AppContext.getInstance().getUser().getmUserName());
    }

}
