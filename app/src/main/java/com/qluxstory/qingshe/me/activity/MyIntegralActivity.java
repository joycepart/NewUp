package com.qluxstory.qingshe.me.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.base.SimplePage;
import com.qluxstory.qingshe.common.dto.BaseDTO;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.DialogUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.common.utils.UIHelper;
import com.qluxstory.qingshe.me.dto.ObtainIntegralDTO;
import com.qluxstory.qingshe.me.entity.IntegralResult;
import com.qluxstory.qingshe.me.entity.ObtainIntegralResult;
import com.qluxstory.qingshe.me.entity.SignEntity;
import com.qluxstory.qingshe.me.entity.SignResult;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by lenovo on 2016/6/24.
 */
public class MyIntegralActivity extends BaseTitleActivity {
    @Bind(R.id.integral_tv)
    TextView mIntegral;
    @Bind(R.id.tv_one)
    Button mOne;
    @Bind(R.id.tv_two)
    Button mTwo;
    @Bind(R.id.tv_three)
    Button mThree;
    @Bind(R.id.tv_four)
    Button mFour;
    @Bind(R.id.tv_five)
    Button mFive;
    @Bind(R.id.tv_six)
    Button mSix;
    @Bind(R.id.tv_server)
    Button mServer;
    @Bind(R.id.tv_rule)
    TextView mTule;
    @Bind(R.id.animationIV)
    ImageView animationIV;
//    @Bind(R.id.buttonA)
//    Button buttonA;
    private String mNum,mRule,mDtatNum,mIsSign,mObtian;
    int obtian;
    private AnimationDrawable animationDrawable;
    //Alpha动画 - 渐变透明度
    private Animation alphaAnimation = null;
    TranslateAnimation animation;


    @Override
    protected int getContentResId() {
        return R.layout.activity_integral;
    }

    @Override
    public void initView() {
        setTitleText("我的积分");
        setEnsureText("积分明细");
        mIntegral.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
//                anim();

            }

        });

    }


    @Override
    public void initData() {
        reqPersonal();//个人积分
        reqSign();//签到天数
    }



    @OnClick({R.id.tv_one,R.id.tv_two, R.id.tv_three, R.id.tv_four, R.id.tv_five, R.id.tv_six, R.id.tv_server, R.id.tv_rule})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_one:
                reqObtainIntegral();//签到获取积分
                    LogUtils.e("mObtian---",""+mObtian);
                    anim();
                    mOne.setBackgroundResource(R.drawable.red);
                    mOne.setEnabled(false);
                    mOne.setText("已签到");
                    mOne.setTextColor(getResources().getColor(R.color.color_f0));

                break;
            case R.id.tv_two:
                if(mIsSign.equals("0")){
                    reqObtainIntegral();//签到获取积分
                    anim();
                    mTwo.setBackgroundResource(R.drawable.red);
                    mTwo.setEnabled(false);
                    mTwo.setText("已签到");
                    mTwo.setTextColor(getResources().getColor(R.color.color_f0));

                }else {
                    DialogUtils.showPrompt(this,"提示","您今天已经签到了，明天再来吧!","知道了");
                }
                break;
            case R.id.tv_three:
                if(mIsSign.equals("0")) {
                    reqObtainIntegral();//签到获取积分
                    anim();
                    mThree.setBackgroundResource(R.drawable.red);
                    mThree.setEnabled(false);
                    mThree.setText("已签到");
                    mThree.setTextColor(getResources().getColor(R.color.color_f0));


                }else {
                    DialogUtils.showPrompt(this,"提示","您今天已经签到了，明天再来吧!","知道了");
                }
                    break;
            case R.id.tv_four:
                if(mIsSign.equals("0")) {
                    reqObtainIntegral();//签到获取积分
                    anim();
                    mFour.setBackgroundResource(R.drawable.red);
                    mFour.setEnabled(false);
                    mFour.setText("已签到");
                    mFour.setTextColor(getResources().getColor(R.color.color_f0));


                }else {
                    DialogUtils.showPrompt(this,"提示","您今天已经签到了，明天再来吧!","知道了");
                }
                break;
            case R.id.tv_five:
                if(mIsSign.equals("0")) {
                    reqObtainIntegral();//签到获取积分
                    anim();
                    mFive.setBackgroundResource(R.drawable.red);
                    mFive.setEnabled(false);
                    mFive.setText("已签到");
                    mFive.setTextColor(getResources().getColor(R.color.color_f0));


                }else {
                    DialogUtils.showPrompt(this,"提示","您今天已经签到了，明天再来吧!","知道了");
                }
                break;
            case R.id.tv_six:
                if(mIsSign.equals("0")) {
                    reqObtainIntegral();//签到获取积分
                    anim();
                    mSix.setBackgroundResource(R.drawable.red);
                    mSix.setEnabled(false);
                    mSix.setText("已签到");
                    mSix.setTextColor(getResources().getColor(R.color.color_f0));


                }else {
                    DialogUtils.showPrompt(this,"提示","您今天已经签到了，明天再来吧!","知道了");
                }
                break;
            case R.id.tv_server:
                reqObtainIntegral();//签到获取积分
                anim();
                    mServer.setBackgroundResource(R.drawable.red);
                    mServer.setEnabled(false);
                    mServer.setText("已签到");
                    mServer.setTextColor(getResources().getColor(R.color.color_f0));
                break;
            case R.id.tv_rule:
                DialogUtils.showPrompt(this,"积分规则",mRule,"知道了");
                break;
            case R.id.base_titlebar_ensure:
                UIHelper.showFragment(this, SimplePage.INTERARAL);//积分明细
                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;

        }
    }

    private void anim() {
        animationIV.setVisibility(View.VISIBLE);

        AnimationSet set = new AnimationSet(false);

        // 渐变动画
        AlphaAnimation aa = new AlphaAnimation(1, 0.3f);// 由完全透明到不透明
        //帧动画
        animationIV.setImageResource(R.drawable.gold);
        animationDrawable = (AnimationDrawable) animationIV.getDrawable();
        //位移动画
        animation = new TranslateAnimation(0,0,-10,-450);
        animation.setDuration(1600);//设置动画持续时间
        animation.setFillAfter(true);// 动画播放完之后，停留在当前状态
        animation.setInterpolator(new LinearInterpolator());
        // 添加到动画集
        set.addAnimation(aa);
        set.addAnimation(animation);
        animationDrawable.start();
        animationIV.startAnimation(set);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animationIV.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void reqObtainIntegral() {
        ObtainIntegralDTO bdto = new ObtainIntegralDTO();
        String time = TimeUtils.getSignTime();
        if(mOne.isEnabled()){
            mDtatNum = "1";
        }
        else if(mTwo.isEnabled()){
            mDtatNum = "2";
        }
        else if(mThree.isEnabled()){
            mDtatNum = "3";
        }
        else if(mFour.isEnabled()){
            mDtatNum = "4";
        }
        else if(mFive.isEnabled()){
            mDtatNum = "5";
        }
        else if(mSix.isEnabled()){
            mDtatNum = "6";
        }
        else if(mServer.isEnabled()){
            mDtatNum = "7";
        }
        bdto.setDatanum(mDtatNum);
        bdto.setMembermob(AppContext.get("mobileNum",""));
        bdto.setSign(time+ AppConfig.SIGN_1);
        bdto.setTimestamp(time);
        CommonApiClient.obtainIntegral(this, bdto, new CallBack<ObtainIntegralResult>() {
            @Override
            public void onSuccess(ObtainIntegralResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.d("签到获取积分成功");
                    if(null==result.getData()){
                        return;
                    }else {
                        mObtian = result.getData().get(0).getIntegralNum();
                        obtian = Integer.parseInt(mObtian);
                        String mInt = mIntegral.getText().toString();
                        int mi = Integer.parseInt(mInt);
                        String num = String.valueOf(obtian+mi);
                        AppContext.set("mIntegral",num);
                        LogUtils.e("num------",""+num);
                        mIntegral.setText(num);
                    }


                }

            }
        });
    }

    private void reqSign() {
        BaseDTO bdto = new BaseDTO();
        String time = TimeUtils.getSignTime();
        bdto.setMembermob(AppContext.get("mobileNum",""));
        bdto.setSign(time+ AppConfig.SIGN_1);
        bdto.setTimestamp(time);
        CommonApiClient.sign(this, bdto, new CallBack<SignResult>() {
            @Override
            public void onSuccess(SignResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.d("签到天数成功");
                    if(null==result.getData()){
                        return;
                    }else {
                        bindSign(result.getData());
                    }

                }

            }
        });
    }

    private void bindSign(List<SignEntity> data) {
        mDtatNum = data.get(0).getDataNum();
        mIsSign = data.get(0).getIsSignIn();
        if(mDtatNum.equals("0")){
            mOne.setEnabled(true);
            mTwo.setEnabled(false);
            mThree.setEnabled(false);
            mFour.setEnabled(false);
            mFive.setEnabled(false);
            mSix.setEnabled(false);
            mServer.setEnabled(false);
            mOne.setText("第一天");
            mOne.setTextColor(getResources().getColor(R.color.color_ff));
            mOne.setBackgroundResource(R.drawable.gray);
            mTwo.setText("第二天");
            mTwo.setTextColor(getResources().getColor(R.color.color_ff));
            mTwo.setBackgroundResource(R.drawable.gray);
            mThree.setText("第三天");
            mThree.setTextColor(getResources().getColor(R.color.color_ff));
            mThree.setBackgroundResource(R.drawable.gray);
            mFour.setText("第四天");
            mFour.setTextColor(getResources().getColor(R.color.color_ff));
            mFour.setBackgroundResource(R.drawable.gray);
            mFive.setText("第五天");
            mFive.setTextColor(getResources().getColor(R.color.color_ff));
            mFive.setBackgroundResource(R.drawable.gray);
            mSix.setText("第六天");
            mSix.setTextColor(getResources().getColor(R.color.color_ff));
            mSix.setBackgroundResource(R.drawable.gray);
            mServer.setText("第七天");
            mServer.setTextColor(getResources().getColor(R.color.color_ff));
            mServer.setBackgroundResource(R.drawable.gray);
        }
        else if(mDtatNum.equals("1")){
            mOne.setEnabled(false);
            mTwo.setEnabled(true);
            mThree.setEnabled(false);
            mFour.setEnabled(false);
            mFive.setEnabled(false);
            mSix.setEnabled(false);
            mServer.setEnabled(false);
            mOne.setBackgroundResource(R.drawable.red);
            mOne.setText("已签到");
            mOne.setTextColor(getResources().getColor(R.color.color_f0));
            mTwo.setText("第二天");
            mTwo.setTextColor(getResources().getColor(R.color.color_ff));
            mThree.setText("第三天");
            mThree.setTextColor(getResources().getColor(R.color.color_ff));
            mFour.setText("第四天");
            mFour.setTextColor(getResources().getColor(R.color.color_ff));
            mFive.setText("第五天");
            mFive.setTextColor(getResources().getColor(R.color.color_ff));
            mSix.setText("第六天");
            mSix.setTextColor(getResources().getColor(R.color.color_ff));
            mServer.setText("第七天");
            mServer.setTextColor(getResources().getColor(R.color.color_ff));
        }
        else if(mDtatNum.equals("2")){
            mOne.setEnabled(false);
            mTwo.setEnabled(false);
            mThree.setEnabled(true);
            mFour.setEnabled(false);
            mFive.setEnabled(false);
            mSix.setEnabled(false);
            mServer.setEnabled(false);
            mOne.setBackgroundResource(R.drawable.red);
            mTwo.setBackgroundResource(R.drawable.red);
            mOne.setText("已签到");
            mOne.setTextColor(getResources().getColor(R.color.color_f0));
            mTwo.setText("已签到");
            mTwo.setTextColor(getResources().getColor(R.color.color_f0));
            mThree.setText("第三天");
            mThree.setTextColor(getResources().getColor(R.color.color_ff));
            mFour.setText("第四天");
            mFour.setTextColor(getResources().getColor(R.color.color_ff));
            mFive.setText("第五天");
            mFive.setTextColor(getResources().getColor(R.color.color_ff));
            mSix.setText("第六天");
            mSix.setTextColor(getResources().getColor(R.color.color_ff));
            mServer.setText("第七天");
            mServer.setTextColor(getResources().getColor(R.color.color_ff));
        }
        else if(mDtatNum.equals("3")){
            mOne.setEnabled(false);
            mTwo.setEnabled(false);
            mThree.setEnabled(false);
            mFour.setEnabled(true);
            mFive.setEnabled(false);
            mSix.setEnabled(false);
            mServer.setEnabled(false);
            mOne.setBackgroundResource(R.drawable.red);
            mTwo.setBackgroundResource(R.drawable.red);
            mThree.setBackgroundResource(R.drawable.red);
            mOne.setText("已签到");
            mOne.setTextColor(getResources().getColor(R.color.color_f0));
            mTwo.setText("已签到");
            mTwo.setTextColor(getResources().getColor(R.color.color_f0));
            mThree.setText("已签到");
            mThree.setTextColor(getResources().getColor(R.color.color_f0));
            mFour.setText("第四天");
            mFour.setTextColor(getResources().getColor(R.color.color_ff));
            mFive.setText("第五天");
            mFive.setTextColor(getResources().getColor(R.color.color_ff));
            mSix.setText("第六天");
            mSix.setTextColor(getResources().getColor(R.color.color_ff));
            mServer.setText("第七天");
            mServer.setTextColor(getResources().getColor(R.color.color_ff));
        }
        else if(mDtatNum.equals("4")){
            mOne.setEnabled(false);
            mTwo.setEnabled(false);
            mThree.setEnabled(false);
            mFour.setEnabled(false);
            mFive.setEnabled(true);
            mSix.setEnabled(false);
            mServer.setEnabled(false);
            mOne.setBackgroundResource(R.drawable.red);
            mTwo.setBackgroundResource(R.drawable.red);
            mThree.setBackgroundResource(R.drawable.red);
            mFour.setBackgroundResource(R.drawable.red);
            mOne.setText("已签到");
            mOne.setTextColor(getResources().getColor(R.color.color_f0));
            mTwo.setText("已签到");
            mTwo.setTextColor(getResources().getColor(R.color.color_f0));
            mThree.setText("已签到");
            mThree.setTextColor(getResources().getColor(R.color.color_f0));
            mFour.setText("已签到");
            mFour.setTextColor(getResources().getColor(R.color.color_f0));
            mFive.setText("第五天");
            mFive.setTextColor(getResources().getColor(R.color.color_ff));
            mSix.setText("第六天");
            mSix.setTextColor(getResources().getColor(R.color.color_ff));
            mServer.setText("第七天");
            mServer.setTextColor(getResources().getColor(R.color.color_ff));
        }
        else if(mDtatNum.equals("5")){
            mOne.setEnabled(false);
            mTwo.setEnabled(false);
            mThree.setEnabled(false);
            mFour.setEnabled(false);
            mFive.setEnabled(false);
            mSix.setEnabled(true);
            mServer.setEnabled(false);
            mOne.setBackgroundResource(R.drawable.red);
            mTwo.setBackgroundResource(R.drawable.red);
            mThree.setBackgroundResource(R.drawable.red);
            mFour.setBackgroundResource(R.drawable.red);
            mFive.setBackgroundResource(R.drawable.red);
            mOne.setText("已签到");
            mOne.setTextColor(getResources().getColor(R.color.color_f0));
            mTwo.setText("已签到");
            mTwo.setTextColor(getResources().getColor(R.color.color_f0));
            mThree.setText("已签到");
            mThree.setTextColor(getResources().getColor(R.color.color_f0));
            mFour.setText("已签到");
            mFour.setTextColor(getResources().getColor(R.color.color_f0));
            mFive.setText("已签到");
            mFive.setTextColor(getResources().getColor(R.color.color_ff));
            mSix.setText("第六天");
            mSix.setTextColor(getResources().getColor(R.color.color_ff));
            mServer.setText("第七天");
            mServer.setTextColor(getResources().getColor(R.color.color_ff));
        }
        else if(mDtatNum.equals("6")){
            mOne.setEnabled(false);
            mTwo.setEnabled(false);
            mThree.setEnabled(false);
            mFour.setEnabled(false);
            mFive.setEnabled(false);
            mSix.setEnabled(false);
            mServer.setEnabled(true);
            mOne.setBackgroundResource(R.drawable.red);
            mTwo.setBackgroundResource(R.drawable.red);
            mThree.setBackgroundResource(R.drawable.red);
            mFour.setBackgroundResource(R.drawable.red);
            mFive.setBackgroundResource(R.drawable.red);
            mSix.setBackgroundResource(R.drawable.red);
            mOne.setText("已签到");
            mOne.setTextColor(getResources().getColor(R.color.color_f0));
            mTwo.setText("已签到");
            mTwo.setTextColor(getResources().getColor(R.color.color_f0));
            mThree.setText("已签到");
            mThree.setTextColor(getResources().getColor(R.color.color_f0));
            mFour.setText("已签到");
            mFour.setTextColor(getResources().getColor(R.color.color_f0));
            mFive.setText("已签到");
            mFive.setTextColor(getResources().getColor(R.color.color_f0));
            mSix.setText("已签到");
            mSix.setTextColor(getResources().getColor(R.color.color_f0));
            mServer.setText("第七天");
            mServer.setTextColor(getResources().getColor(R.color.color_ff));
        }
        else if(mDtatNum.equals("7")){
            mOne.setEnabled(false);
            mTwo.setEnabled(false);
            mThree.setEnabled(false);
            mFour.setEnabled(false);
            mFive.setEnabled(false);
            mSix.setEnabled(false);
            mServer.setEnabled(false);
            mOne.setBackgroundResource(R.drawable.red);
            mTwo.setBackgroundResource(R.drawable.red);
            mThree.setBackgroundResource(R.drawable.red);
            mFour.setBackgroundResource(R.drawable.red);
            mFive.setBackgroundResource(R.drawable.red);
            mSix.setBackgroundResource(R.drawable.red);
            mServer.setBackgroundResource(R.drawable.red);
            mOne.setText("已签到");
            mOne.setTextColor(getResources().getColor(R.color.color_f0));
            mTwo.setText("已签到");
            mTwo.setTextColor(getResources().getColor(R.color.color_f0));
            mThree.setText("已签到");
            mThree.setTextColor(getResources().getColor(R.color.color_f0));
            mFour.setText("已签到");
            mFour.setTextColor(getResources().getColor(R.color.color_f0));
            mFive.setText("已签到");
            mFive.setTextColor(getResources().getColor(R.color.color_f0));
            mSix.setText("已签到");
            mSix.setTextColor(getResources().getColor(R.color.color_f0));
            mServer.setText("已签到");
            mServer.setTextColor(getResources().getColor(R.color.color_f0));
        }


    }


    private void reqPersonal() {
        BaseDTO bdto = new BaseDTO();
        String time = TimeUtils.getSignTime();
        bdto.setMembermob(AppContext.get("mobileNum",""));
        bdto.setSign(time+ AppConfig.SIGN_1);
        bdto.setTimestamp(time);
        CommonApiClient.personal(this, bdto, new CallBack<IntegralResult>() {
            @Override
            public void onSuccess(IntegralResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.d("个人积分成功");
                    mNum = result.getData().get(0).getPointsIntegral();
                    mRule = result.getData().get(0).getIntegralState();
                    AppContext.set("mIntegral",mNum);
                    bindIntegral();

                }

            }
        });
    }

    private void bindIntegral() {
        mIntegral.setText(mNum);
    }
}
