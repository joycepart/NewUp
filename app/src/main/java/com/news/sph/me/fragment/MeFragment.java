package com.news.sph.me.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.news.sph.AppConfig;
import com.news.sph.AppContext;
import com.news.sph.R;
import com.news.sph.common.base.BaseFragment;
import com.news.sph.me.entity.User;
import com.news.sph.me.utils.MeUiGoto;
import com.news.sph.ui.BrowserActivity;
import com.news.sph.utils.LogUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;


public class MeFragment extends BaseFragment{

    Button mUserBtn,mUserBtnClose,mClose;
    RelativeLayout mLoginUc;
    TextView mLoginTvName;
    ImageView mLoginImgPic;
    LinearLayout mUserCuring,mUserIndiana,mUserCoupon,mUserIncome,mUserAbout,
            mUserReturn,mUserService,mUserIpone,mUserQq,mUserInf,mLoginLl,mMeClose;
    private String mUrlUs;
    private String mUsTitle;
    private String mUrlReturn;
    private String mReturnTitle;
    private String mUrlQq;
    private String mQqTitle;
    private String mUrlInformation;
    private String mInformationTitle;
    Boolean flag;//判断是否登录
    private String userName;
    private String pictruePath;
    private String pictrueUrl;
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_me;
    }

    @Override
    public void initView(View view) {

        mLoginLl = (LinearLayout) view.findViewById(R.id.login_ll);
        mUserBtn = (Button) view.findViewById(R.id.user_btn);

        mLoginUc = (RelativeLayout) view.findViewById(R.id.loing_suc);

        mMeClose = (LinearLayout) view.findViewById(R.id.me_ll_close);
        mClose = (Button) view.findViewById(R.id.user_btn_close);

        mUserCuring = (LinearLayout) view.findViewById(R.id.user_ll_curing);
        mUserIndiana = (LinearLayout) view.findViewById(R.id.user_ll_indiana);
        mUserCoupon = (LinearLayout) view.findViewById(R.id.user_ll_coupon);
        mUserIncome = (LinearLayout) view.findViewById(R.id.user_ll_income);
        mUserAbout = (LinearLayout) view.findViewById(R.id.user_ll_about);
        mUserReturn = (LinearLayout) view.findViewById(R.id.user_ll_return);
        mUserService = (LinearLayout) view.findViewById(R.id.user_ll_service);
        mUserIpone = (LinearLayout) view.findViewById(R.id.user_ll_ipone);
        mUserQq = (LinearLayout) view.findViewById(R.id.user_ll_qq);
        mUserInf = (LinearLayout) view.findViewById(R.id.user_ll_inf);

        flag = AppContext.getInstance().getUser().getFlag();
        LogUtils.e("flag: "+flag);
        userName = AppContext.getInstance().getUser().getUserName();
        LogUtils.e("userName: "+userName);
        pictruePath = AppContext.getInstance().getUser().getPictruePath();
        pictrueUrl = AppConfig.BASE_URL+pictruePath;

        if(!flag){
            LogUtils.e("if flag: "+"true");
            mLoginLl.setVisibility(View.GONE);
            mLoginUc.setVisibility(View.VISIBLE);
            mMeClose.setVisibility(View.VISIBLE);

            mLoginImgPic = (ImageView) view.findViewById(R.id.login_img_pic);
            mLoginTvName = (TextView) view.findViewById(R.id.login_tv_name);
            mLoginTvName.setText(userName);
            LoadImage();

            mLoginUc.setOnClickListener(this);
            mClose.setOnClickListener(this);


        }else {
            LogUtils.e("else flag: "+"false");
            mLoginUc.setVisibility(View.GONE);
            mLoginLl.setVisibility(View.VISIBLE);
            mMeClose.setVisibility(View.GONE);

            mUserBtn.setOnClickListener(this);

        }



        mUserCuring.setOnClickListener(this);
        mUserIndiana.setOnClickListener(this);
        mUserCoupon.setOnClickListener(this);
        mUserIncome.setOnClickListener(this);
        mUserAbout.setOnClickListener(this);
        mUserReturn.setOnClickListener(this);
        mUserService.setOnClickListener(this);
        mUserIpone.setOnClickListener(this);
        mUserQq.setOnClickListener(this);
        mUserInf.setOnClickListener(this);


    }

    private void LoadImage() {
        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoader.getInstance().displayImage(pictrueUrl, mLoginImgPic, options);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.user_btn:
                MeUiGoto.login(getActivity());//登录
                break;
            case R.id.user_ll_curing:
                MeUiGoto.maintenanceOrder(getActivity());//养护订单
                break;
            case R.id.user_ll_indiana:
//                MeUiGoto.maintenanceOrder(getActivity());//夺宝记录
                break;
            case R.id.user_ll_coupon:
                MeUiGoto.myCoupon(getActivity());//优惠劵
                break;
            case R.id.user_ll_income:
                MeUiGoto.myIncome(getActivity());//我的收入
                break;
            case R.id.user_ll_about:
                mUrlUs = AppConfig.URL_ABOUT_US;
                mUsTitle= "关于我们 - 倾奢介绍";
                MeUiGoto.aboutUs(getActivity(),mUrlUs,mUsTitle);//关于我们
                break;
            case R.id.user_ll_return:
                Intent intent6 = new Intent(getActivity(),BrowserActivity.class);
                mUrlReturn = AppConfig.URL_ABOUT_RETURN;
                mReturnTitle= "关于退货 - 倾奢";
                MeUiGoto.rturn(getActivity(),mUrlReturn,mReturnTitle);//关于退货
                break;
            case R.id.user_ll_service:
                MeUiGoto.myIncome(getActivity());//超级客服在线
                break;
            case R.id.user_ll_ipone:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                        + getString(R.string.common_service_phone)));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent); //电话客服
                break;
            case R.id.user_ll_qq:
                mUrlQq = AppConfig.URL_QQ;
                mQqTitle= "官方QQ群 - 倾奢";
                MeUiGoto.qq(getActivity(),mUrlQq,mQqTitle);//官方QQ群
                break;
            case R.id.user_ll_inf:
                mUrlInformation = AppConfig.URL_INFORMATION;
                mInformationTitle= "版本信息 - 倾奢";
                MeUiGoto.sinformation(getActivity(),mUrlInformation,mInformationTitle);//版本信息
                break;
            case R.id.loing_suc:
                MeUiGoto.userInformation(getActivity());//用户信息
                break;
            case R.id.user_btn_close:
                mLoginUc.setVisibility(View.GONE);
                mLoginLl.setVisibility(View.VISIBLE);
                mMeClose.setVisibility(View.GONE);
                flag = false;
                break;
            default:
                break;

        }

    }

    @Override
    public void initData() {

    }
}
