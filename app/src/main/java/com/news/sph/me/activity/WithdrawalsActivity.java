package com.news.sph.me.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.news.sph.AppConfig;
import com.news.sph.AppContext;
import com.news.sph.R;
import com.news.sph.common.base.BaseTitleActivity;
import com.news.sph.common.entity.BaseEntity;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.common.utils.LogUtils;
import com.news.sph.me.dto.WithdrawalsDTO;

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
        strPhoneNum = AppContext.getInstance().getUser().getmUserMobile();

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.withd_md, R.id.pay_Btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.withd_md:
                break;
            case R.id.pay_Btn:
                mMon = mWithdMon.getText().toString();
                mUs = mWithdMon.getText().toString();
                mNm = mWithdMon.getText().toString();
                mIpone = mWithdMon.getText().toString();
                Withdrawals();
                break;
        }
    }

    private void Withdrawals() {
        WithdrawalsDTO mDto = new WithdrawalsDTO();
        mDto.setMembermob(strPhoneNum);
        mDto.setSign(AppConfig.SIGN_1);
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
                }

            }
        });
    }
}
