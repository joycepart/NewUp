package com.news.sph.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.sph.AppConfig;
import com.news.sph.AppContext;
import com.news.sph.R;
import com.news.sph.common.base.BaseActivity;
import com.news.sph.common.dto.BaseDTO;
import com.news.sph.common.entity.BaseEntity;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.home.dto.LoginDTO;
import com.news.sph.home.entity.LoginEntity;
import com.news.sph.home.entity.LoginResult;
import com.news.sph.me.entity.User;
import com.news.sph.me.utils.MeUiGoto;
import com.news.sph.ui.BrowserActivity;
import com.news.sph.utils.LogUtils;

import java.util.List;

import butterknife.Bind;

/**
 * 登录主页面
 */
public class LoginActivity extends BaseActivity {
    @Bind(R.id.img_btn_close)
    ImageView mImgClose;
    @Bind(R.id.login_et_num)
    EditText mLoginNum;
    @Bind(R.id.login_codeBtn)
    Button mLoginCode;
    @Bind(R.id.setpwd_cb_agreement)
    CheckBox setpwd_cb_agreement;
    @Bind(R.id.login_pwdEt)
    EditText mLoginPwdEt;
    @Bind(R.id.login_loginBtn)
    Button mLoginLogin;
    @Bind(R.id.login_readProtocalBtn)
    TextView mLoginRead;
    Boolean flag = false;//判断是否登录

    private String strPhoneNum;
    private String strPwd;
    private String mUrlAgreement;
    private String mAgreementTitle;
    User mUser = AppContext.getInstance().getUser();


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        mImgClose.setOnClickListener(this);
        mLoginNum.setOnClickListener(this);
        mLoginCode.setOnClickListener(this);
        setpwd_cb_agreement.setOnCheckedChangeListener(onCheckedChangeListener);
        mLoginPwdEt.setOnClickListener(this);
        mLoginLogin.setOnClickListener(this);
        mLoginRead.setOnClickListener(this);
    }

    private final CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                mLoginLogin.setEnabled(true);
            } else {
                mLoginLogin.setEnabled(false);
            }
        }
    };



    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        strPhoneNum = mLoginNum.getText().toString();
        strPwd = mLoginPwdEt.getText().toString();
        switch (v.getId()) {
            case R.id.login_codeBtn:
                ObtainCode();
                break;

            case R.id.img_btn_close:
                LoginActivity.this.finish();
                break;

            case R.id.login_loginBtn:
                Login();
                break;

            case R.id.login_readProtocalBtn:
                mUrlAgreement = AppConfig.URL_AGREEMENT;
                mAgreementTitle= "用户注册协议";
                MeUiGoto.registration(this,mUrlAgreement,mAgreementTitle);//用户注册协议
                break;

            default:
                break;
           
        }
        super.onClick(v);
    }
//获取验证码
    private void ObtainCode() {
        BaseDTO bdto=new BaseDTO();
        bdto.setMembermob(strPhoneNum);
        bdto.setSign(AppConfig.SIGN_1);
        CommonApiClient.getVerify(this, bdto, new CallBack<BaseEntity>() {
            @Override
            public void onSuccess(BaseEntity result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.d("获取验证码请求成功");
                }
            }
        });
    }
//登录
    private void Login() {

        LoginDTO ldto=new LoginDTO();
        ldto.setMemberverifycode(strPwd);
        ldto.setMembermob(strPhoneNum);
        ldto.setSign(AppConfig.SIGN_1);
        CommonApiClient.login(this, ldto, new CallBack<LoginResult>() {
            @Override
            public void onSuccess(LoginResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("登录成功");
                    getLoginResult(result.getData());
                    LoginActivity.this.finish();
                }

            }
        });
    }

    private void getLoginResult(List<LoginEntity> data) {
        AppContext.getInstance().getUser().setUserMobile(data.get(0).getMembermobile());
        AppContext.getInstance().getUser().setUserName(data.get(0).getMembername());
        AppContext.getInstance().getUser().setPictruePath(data.get(0).getMemberHeadimg());
        LogUtils.e("setUserMobile : "+data.get(0).getMembermobile());
        LogUtils.e("setUserName: "+data.get(0).getMembername());
        LogUtils.e("setPictruePath: "+data.get(0).getMemberHeadimg());
        AppContext.getInstance().getUser().setFlag(true);
    }


}
