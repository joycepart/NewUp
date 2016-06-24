package com.qluxstory.qingshe.me.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseActivity;
import com.qluxstory.qingshe.common.entity.BaseEntity;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.DialogUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.SecurityUtils;
import com.qluxstory.qingshe.common.utils.TimeCountDown;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.me.MeUiGoto;
import com.qluxstory.qingshe.me.dto.FristDTO;
import com.qluxstory.qingshe.me.dto.LoginDTO;
import com.qluxstory.qingshe.me.dto.ObtainDTO;
import com.qluxstory.qingshe.me.entity.LoginEntity;
import com.qluxstory.qingshe.me.entity.LoginResult;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 登录主页面
 */
public class LoginActivity extends BaseActivity {

    @Bind(R.id.img_btn_close)
    ImageView mImgBtnClose;
    @Bind(R.id.lg_imgbtn)
    ImageView mLgImgbtn;
    @Bind(R.id.login_et_num)
    EditText mLoginEtNum;
    @Bind(R.id.login_codeBtn)
    TextView mLoginCodeBtn;
    @Bind(R.id.login_pwdEt)
    EditText mLoginPwdEt;
    @Bind(R.id.setpwd_cb_agreement)
    CheckBox mSetpwdCbAgreement;
    @Bind(R.id.login_readProtocalBtn)
    TextView mLoginReadProtocalBtn;
    @Bind(R.id.login_loginBtn)
    Button mLoginLoginBtn;
    TimeCountDown mTcd;
    private String strPhoneNum;
    private String strPwd;
    private String mUrlAgreement;
    private String mAgreementTitle;
    String mCity,mProvince,mDistrict;
    boolean bool = false;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        mCity = AppContext.get("mCity","");
        mProvince = AppContext.get("mProvince","");
        mDistrict = AppContext.get("mDistrict","");
        mSetpwdCbAgreement.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    private final CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                mLoginLoginBtn.setEnabled(true);
            } else {
                mLoginLoginBtn.setEnabled(false);
            }
        }
    };


    @Override
    public void initData() {
        mTcd = new TimeCountDown(mLoginCodeBtn, 60000, 1000, "重新获取", "s");

    }


    //获取验证码
    private void ObtainCode() {
        mTcd.start();
        ObtainDTO bdto = new ObtainDTO();
        bdto.setMembermob(strPhoneNum);
        String time = TimeUtils.getSignTime();
        bdto.setSign(time+AppConfig.SIGN_1);
        bdto.setTimestamp(time);
        bdto.setRegisterFrom(AppConfig.RegisterFrom);
        CommonApiClient.getVerify(this, bdto, new CallBack<BaseEntity>() {
            @Override
            public void onSuccess(BaseEntity result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.d("获取验证码请求成功");
                }
            }
        });
    }

    //登录
    private void Login() {
        LoginDTO ldto = new LoginDTO();
        ldto.setMemberverifycode(strPwd);
        ldto.setMembermob(strPhoneNum);
        String time = TimeUtils.getSignTime();
        LogUtils.e("setSign----",""+time+AppConfig.SIGN_1);
        ldto.setSign(time+AppConfig.SIGN_1);
        ldto.setRegisterFrom(AppConfig.RegisterFrom);
        ldto.setTimestamp(time);
        CommonApiClient.login(this, ldto, new CallBack<LoginResult>() {
            @Override
            public void onSuccess(LoginResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("登录成功");
                    getLoginResult(result.getData());
                    setResult(1001);
                    finish();

                }

            }
        });
    }

    private void getLoginResult(List<LoginEntity> data) {
        LoginEntity entity=data.get(0);
        AppContext.set("mobileNum",entity.getMembermobile());
        AppContext.set("isLogin",true);
        AppContext.set("mUserName",entity.getMembername());
        AppContext.set("mPictruePath",entity.getMemberHeadimg());
        AppContext.set("mRongyunToken",entity.getToken());
        bool = AppContext.get("frist",false);
        if(bool){
            return;
        }else {
            if(TextUtils.isEmpty(mCity)){
                return;
            }else {
                reqFirst();//第一次定位
            }

        }
    }

    private void reqFirst() {
        FristDTO ldto = new FristDTO();
        ldto.setMembermob(strPhoneNum);
        ldto.setFirstarea(mDistrict);
        ldto.setFirstcity(mCity);
        ldto.setFirstprovice(mProvince);
        String time = TimeUtils.getSignTime();
        ldto.setSign(time+AppConfig.SIGN_1);
        ldto.setTimestamp(time);
        CommonApiClient.frist(this, ldto, new CallBack<LoginResult>() {
            @Override
            public void onSuccess(LoginResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("第一次定位成功");
                    AppContext.get("frist",true);

                }

            }
        });
    }


    @OnClick({R.id.img_btn_close, R.id.lg_imgbtn, R.id.login_codeBtn, R.id.login_readProtocalBtn, R.id.login_loginBtn})
    public void onClick(View view) {
        strPhoneNum = mLoginEtNum.getText().toString();
        strPwd = mLoginPwdEt.getText().toString();
        switch (view.getId()) {
            case R.id.img_btn_close:
                LoginActivity.this.finish();
                break;
            case R.id.lg_imgbtn:
                break;
            case R.id.login_codeBtn:
                if(TextUtils.isEmpty(mLoginEtNum.getText().toString())||mLoginEtNum.getText().toString().length()<11){
                    DialogUtils.showPrompt(this,"提示","请输入正确的手机号","知道了");
                }else {
                    String time = TimeUtils.getSignTime();
                    String sing = time+AppConfig.SIGN_1;
                    String str = SecurityUtils.md5(sing);
                    LogUtils.e("setSign----",""+time);
                    LogUtils.e("sing----",""+sing);
                    LogUtils.e("str----",""+str);
                    ObtainCode();
                }

                break;
            case R.id.login_readProtocalBtn:
                mUrlAgreement = AppConfig.URL_AGREEMENT;
                mAgreementTitle = "用户注册协议";
                MeUiGoto.registration(this, mUrlAgreement, mAgreementTitle);//用户注册协议
                break;
            case R.id.login_loginBtn:
                Login();
                break;
        }
    }
}
