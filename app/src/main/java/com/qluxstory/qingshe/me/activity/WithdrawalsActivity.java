package com.qluxstory.qingshe.me.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.MainActivity;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.entity.BaseEntity;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.DialogUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.common.widget.PopuoWithdrawals;
import com.qluxstory.qingshe.me.dto.WithdrawalsDTO;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 申请提现主页面
 */
public class WithdrawalsActivity extends BaseTitleActivity {
    @Bind(R.id.withd_md)
    LinearLayout mWithdMd;
    @Bind(R.id.withd_lin_bank)
    LinearLayout mLinBank;
    @Bind(R.id.withd_mon)
    EditText mWithdMon;
    @Bind(R.id.withd_us)
    EditText mWithdUs;
    @Bind(R.id.withd_nm)
    EditText mWithdNm;
    @Bind(R.id.withd_ipone)
    EditText mWithdIpone;
    @Bind(R.id.withd_bank)
    EditText mWithdBank;
    @Bind(R.id.withd_tv_md)
    TextView mWithdTv;
    @Bind(R.id.pay_Btn)
    Button mWBtn;
    private String strPhoneNum,mMon,mUs,mNm,mIpone,mWTv;
    PopuoWithdrawals popMenus;

    @Override
    protected int getContentResId() {
        return R.layout.activity_withdrawals;
    }

    @Override
    public void initView() {
        setTitleText("提现");
        mWTv = mWithdTv.getText().toString();
        if(mWTv.equals("支付宝")){
            mLinBank.setVisibility(View.GONE);
        }else {
            mLinBank.setVisibility(View.VISIBLE);
        }
        strPhoneNum = AppContext.get("mobileNum","");

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.withd_md, R.id.pay_Btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.withd_md:
                showPopWith();
                break;
            case R.id.pay_Btn:
                mMon = mWithdMon.getText().toString();
                mUs = mWithdMon.getText().toString();
                mNm = mWithdMon.getText().toString();
                mIpone = mWithdMon.getText().toString();
                if(TextUtils.isEmpty(mMon)){
                    DialogUtils.showPrompt(this,"请填写正确的提现金额!","确定");
                }
//                if(mMon<"100"){
//                    DialogUtils.showPrompt(this,"请填写正确的提现金额","确定");
//                }

                else if(TextUtils.isEmpty(mUs)){
                    DialogUtils.showPrompt(this,"请填写账户！","确定");
                }
                else if(TextUtils.isEmpty(mNm)){
                    DialogUtils.showPrompt(this,"请填写姓名！","确定");
                }
                else if(TextUtils.isEmpty(mIpone)&&mIpone.length()<11){
                    DialogUtils.showPrompt(this,"请填写手机号！","确定");
                }
               else {
                    Withdrawals();
                }

                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
        }
    }

    private void showPopWith() {
        popMenus = new PopuoWithdrawals(this,itemsOnClick);

        popMenus.showAtLocation(this.findViewById(R.id.with_tv_t),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener(){

        public void onClick(View v) {
            popMenus.dismiss();
            switch (v.getId()) {
                case R.id.fre_btn:

                    break;

                default:
                    break;
            }


        }

    };

    private void Withdrawals() {
        WithdrawalsDTO mDto = new WithdrawalsDTO();
        mDto.setMembermob(strPhoneNum);
        mDto.setSign(AppConfig.SIGN_1);
        mDto.setTimestamp(TimeUtils.getSignTime());
        mDto.setAccounttype(mWTv);
        mDto.setAccountnumber(mUs);
        mDto.setPresentmoney(mMon);
        mDto.setReservemobile(mIpone);
        mDto.setReservename(mNm);
        CommonApiClient.IncomeWith(this, mDto, new CallBack<BaseEntity>() {
            @Override
            public void onSuccess(BaseEntity result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.d("提现请求成功");
                    Intent intent = new Intent(WithdrawalsActivity.this, MainActivity.class);
                    intent.putExtra("tag",4);
                    startActivity(intent);

                }

            }
        });
    }
}
