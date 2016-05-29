package com.news.sph.me.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.news.sph.AppConfig;
import com.news.sph.AppContext;
import com.news.sph.R;
import com.news.sph.common.base.BaseTitleActivity;
import com.news.sph.common.base.SimplePage;
import com.news.sph.common.dto.BaseDTO;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.common.utils.LogUtils;
import com.news.sph.common.utils.UIHelper;
import com.news.sph.me.MeUiGoto;
import com.news.sph.me.entity.MyIncomeEntity;
import com.news.sph.me.entity.MyIncomeResult;
import com.news.sph.me.entity.User;

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
    TextView mBaseEnsure,mBaseBack;

    private String strPhoneNum;
    /**
     * 可提现余额
     */
    private String mCashaMountMoney;
    /**
     * 累计收入
     */
    private String mAccumuLatedMoney;
    Boolean flag = false;//判断是否登录，false为没有登录


    @Override
    public void initView() {
        setTitleText("我的收入");
        mBaseEnsure = (TextView) findViewById(R.id.base_titlebar_ensure);
        setEnsureText("交易明细");
        User user =AppContext.getInstance().getUser();
        if(user!=null && user.getFlag()==true){
            MeUiGoto.login(this);//登录
        }else {
            strPhoneNum = AppContext.getInstance().getUser().getmUserMobile();
        }

    }

    @Override
    public void initData() {
        MyIncome();//我的收入请求
    }

    private void MyIncome() {
        BaseDTO bdto = new BaseDTO();
        bdto.setMembermob(strPhoneNum);
        bdto.setSign(AppConfig.SIGN_1);
        CommonApiClient.myIncome(this, bdto, new CallBack<MyIncomeResult>() {
            @Override
            public void onSuccess(MyIncomeResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.d("我的收入请求成功");
                    myIncomeResult(result.getData());
                }

            }
        });
    }

    private void myIncomeResult(List<MyIncomeEntity> data) {
        mCashaMountMoney = data.get(0).getCashamountmoney();
        mAccumuLatedMoney = data.get(0).getAccumulatedmoney();
        mWithdrawals.setText(mCashaMountMoney);
        mAccumulate.setText(mAccumuLatedMoney);
    }


    @OnClick({ R.id.income_Btn,R.id.base_titlebar_ensure,R.id.base_titlebar_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.income_Btn:
//                if(mCashaMountMoney.isEmpty()){
//
//                }else {
//
//                }
                MeUiGoto.withd(this);//申请提现
                break;
            case R.id.base_titlebar_ensure:
                UIHelper.showFragment(this, SimplePage.TRANSACTION_DETAIL);//交易明细
                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
        }
    }

    @Override
    protected int getContentResId() {
        return R.layout.activity_me_myincome;
    }
}
