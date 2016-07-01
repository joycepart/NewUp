package com.qluxstory.qingshe.me.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.qluxstory.qingshe.me.MeUiGoto;
import com.qluxstory.qingshe.me.entity.MyIncomeEntity;
import com.qluxstory.qingshe.me.entity.MyIncomeResult;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的收入主页面
 */
public class MyIncomeActivity extends BaseTitleActivity {
    @Bind(R.id.myincome_withdrawals)
    TextView mWithdrawals;
    @Bind(R.id.myincome_accumulate)
    TextView mAccumulate;
    @Bind(R.id.income_Btn)
    Button mIncomeBtn;


    private String strPhoneNum;
    /**
     * 可提现余额
     */
    private String mCashaMountMoney;
    /**
     * 累计收入
     */
    private String mAccumuLatedMoney;





    @Override
    public void initView() {
        setTitleText("我的账户");
        setEnsureText("交易明细");
        strPhoneNum = AppContext.get("mobileNum","");

    }

    @Override
    public void initData() {
        MyIncome();//我的收入请求
    }

    private void MyIncome() {
        BaseDTO bdto = new BaseDTO();
        String time = TimeUtils.getSignTime();
        bdto.setMembermob(strPhoneNum);
        bdto.setSign(time+AppConfig.SIGN_1);
        bdto.setTimestamp(time);
        CommonApiClient.myIncome(this, bdto, new CallBack<MyIncomeResult>() {
            @Override
            public void onSuccess(MyIncomeResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.d("我的收入请求成功");
                    if(null == result.getData()){
                        return;
                    }else {
                        myIncomeResult(result.getData());
                    }

                }

            }
        });
    }

    private void myIncomeResult(List<MyIncomeEntity> data) {
        MyIncomeEntity entity = data.get(0);
        mCashaMountMoney = entity.getCashamountmoney();
        mAccumuLatedMoney = entity.getAccumulatedmoney();
        mWithdrawals.setText(mCashaMountMoney);
        mAccumulate.setText(mAccumuLatedMoney);
    }


    @OnClick({ R.id.income_Btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.income_Btn:
                if(mCashaMountMoney==null){
                    DialogUtils.showPrompt(this,"提示","暂无可提现余额","确定");
                }else if(mCashaMountMoney.equals("0.00")||mCashaMountMoney.equals("0")){
                        DialogUtils.showPrompt(this,"提示","暂无可提现余额","确定");
                }
                else{
                    Bundle b = new Bundle();
                    b.putString("mCashaMountMoney",mCashaMountMoney);
                    MeUiGoto.withd(this,b);//申请提现
                }
                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
            case R.id.base_titlebar_ensure:
                UIHelper.showFragment(this, SimplePage.TRANSACTION_DETAIL);//交易明细
                break;

        }
    }

    @Override
    protected int getContentResId() {
        return R.layout.activity_me_myincome;
    }


}
