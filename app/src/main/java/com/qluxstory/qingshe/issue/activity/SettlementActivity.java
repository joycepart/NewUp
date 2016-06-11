package com.qluxstory.qingshe.issue.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.dto.BaseDTO;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.home.entity.BalanceResult;
import com.qluxstory.qingshe.issue.IssueUiGoto;
import com.qluxstory.qingshe.issue.dto.SettlementDTO;
import com.qluxstory.qingshe.issue.entity.IssueProduct;
import com.qluxstory.qingshe.issue.entity.SettlementResult;

import butterknife.Bind;

/**
 * 结算主页面
 */
public class SettlementActivity extends BaseTitleActivity {
    @Bind(R.id.set_ed)
    TextView mInBtn;
    @Bind(R.id.settlement_balance)
    TextView mSetBalance;
    @Bind(R.id.set_cb_wx)
    CheckBox mSetWx;
    @Bind(R.id.set_cb_zhi)
    CheckBox mSetZhi;
    @Bind(R.id.set_cb_hui)
    CheckBox mSetHui;
    @Bind(R.id.set_cb_agree)
    CheckBox mSetAgree;
    @Bind(R.id.set_tv)
    TextView mSetTv;
    @Bind(R.id.settle_title)
    TextView mTitle;
    @Bind(R.id.settle_term)
    TextView mTerm;
    @Bind(R.id.settle_img)
    ImageView mImg;
    @Bind(R.id.place_tv_nm)
    TextView mPlaceNm;
    @Bind(R.id.set_pay_Btn)
    Button mPayBtn;
    @Bind(R.id.palce_pay_wx)
    LinearLayout mPayWx;
    @Bind(R.id.palce_pay_alipay)
    LinearLayout mPayAlipay;
    @Bind(R.id.palce_pay_balance)
    LinearLayout mPayBalance;
    String mCaTerm;
    String mCaTitle;
    String mBalance;
    String mPic;
    String mTotalVount;
    String mParticipate;
    String mRecCode;
    String mBatCode;
    String mSnaCode  ;
    IssueProduct issueProduct;

    @Override
    protected int getContentResId() {
        return R.layout.activity_settlement;
    }

    @Override
    public void initView() {
        setTitleText("结算");
        issueProduct = AppContext.getInstance().getIssueProduct();
        mCaTerm = issueProduct.getmSnaTerm();
        mCaTitle = issueProduct.getmSnaTitle();
        mPic = issueProduct.getmPicUrl();
        mTotalVount = issueProduct.getmTotalCount();
        mParticipate = issueProduct.getmSnaOut();
        mBatCode = issueProduct.getmBatCode();
        mSnaCode = issueProduct.getmSnaCode();
        if(!TextUtils.isEmpty(issueProduct.getmRecCode())){
            mRecCode = issueProduct.getmRecCode();
        }else {
            mRecCode = "";
        }

        mTitle.setText(mCaTitle);
        mTerm.setText("第"+mCaTerm+"期");
        mPlaceNm.setText(mBalance);
        ImageLoaderUtils.displayImage(mPic,mImg);
        Intent intent = getIntent();
        if(intent!=null){
            mBalance = intent.getBundleExtra("bundle").getString("mBalance");
            mPlaceNm.setText(mBalance);
        }


        mInBtn.setText(AppContext.get("mobileNum",""));

        mPayBtn.setOnClickListener(this);
        mPayWx.setOnClickListener(this);
        mPayAlipay.setOnClickListener(this);
        mPayBalance.setOnClickListener(this);
        mSetTv.setOnClickListener(this);
        mSetAgree.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    private final CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                mPayBtn.setEnabled(true);
            } else {
                mPayBtn.setEnabled(false);
            }
        }
    };

    @Override
    public void initData() {
        reqBalance();//会员余额
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.set_tv:
                IssueUiGoto.special(this,AppConfig.Server_Html+"");
                break;
            case R.id.set_pay_Btn:
                settlement();//下单并开奖
                break;
            case R.id.palce_pay_wx:
                mSetWx.setChecked(true);
                mSetZhi.setChecked(false);
                mSetHui.setChecked(false);
                break;
            case R.id.palce_pay_alipay:
                mSetWx.setChecked(false);
                mSetZhi.setChecked(true);
                mSetHui.setChecked(false);
                break;
            case R.id.palce_pay_balance:
                mSetWx.setChecked(false);
                mSetZhi.setChecked(false);
                mSetHui.setChecked(true);
                break;

        }
        super.onClick(v);
    }

    private void reqBalance() {
        BaseDTO dto = new BaseDTO();
        dto.setSign(AppConfig.SIGN_1);
        dto.setTimestamp(TimeUtils.getSignTime());
        dto.setMembermob(AppContext.get("mobileNum", ""));
        CommonApiClient.balance(this, dto, new CallBack<BalanceResult>() {
            @Override
            public void onSuccess(BalanceResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("会员余额成功");
                    mSetBalance.setText(result.getData().get(0).getCashAmountMoney());

                }

            }
        });
    }

    private void settlement() {
        SettlementDTO gdto=new SettlementDTO();
        if(mSetWx.isChecked()){
            gdto.setType("2");
        }

        if(mSetZhi.isChecked()){
            gdto.setType("1");
        }
        if(mSetHui.isChecked()){
            gdto.setType("3");
        }

        gdto.setUserPhone(AppContext.get("mobileNum",""));
        gdto.setInfor_phone(AppContext.get("mobileNum",""));
        gdto.setBat_code(mBatCode);
        gdto.setSna_code(mSnaCode);
        gdto.setRec_participate_count(mParticipate);
        gdto.setBalance(mBalance);
        gdto.setSna_total_count(mTotalVount);
        gdto.setTerm(mCaTerm);
        gdto.setRec_code(mRecCode);
        gdto.setSign(AppConfig.SIGN_1);
        gdto.setTime(TimeUtils.getSignTime());
        CommonApiClient.settlement(this, gdto, new CallBack<SettlementResult>() {
            @Override
            public void onSuccess(SettlementResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("结算成功");
                }
                IssueUiGoto.payment(SettlementActivity.this);//支付结果页
            }
        });

    }
}
