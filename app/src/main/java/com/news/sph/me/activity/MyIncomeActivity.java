package com.news.sph.me.activity;

import android.view.View;
import android.widget.Button;
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
import com.news.sph.me.dto.WithdrawalsDTO;
import com.news.sph.me.entity.MyIncomeEntity;
import com.news.sph.me.entity.MyIncomeResult;
import com.news.sph.me.MeUiGoto;
import com.news.sph.common.utils.LogUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的收入主页面
 */
public class MyIncomeActivity extends BaseActivity {

    @Bind(R.id.top_myincome_img)
    ImageView mImg;
    @Bind(R.id.top_myincome_tv_detailed)
    TextView mDetailed;
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
    Boolean flag = false;//判断是否登录，false为没有登录


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_me_myincome;
    }

    @Override
    public void initView() {

        if(flag=AppContext.getInstance().getUser().getFlag()){
            MeUiGoto.login(this);//登录
        }
        strPhoneNum = AppContext.getInstance().getUser().getmUserMobile();
    }

    @Override
    public void initData() {
        MyIncome();//我的收入请求
    }

    private void Withdrawals() {
        WithdrawalsDTO mDto = new WithdrawalsDTO();
        mDto.setMembermob(strPhoneNum);
        mDto.setSign(AppConfig.SIGN_1);
        mDto.setAccountnumber("");
        mDto.setAccounttype("");
        mDto.setPresentmoney("");
        mDto.setReservemobile("");
        mDto.setReservename("");
        CommonApiClient.IncomeWith(this, mDto, new CallBack<BaseEntity>() {
            @Override
            public void onSuccess(BaseEntity result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.d("提现请求成功");
                }

            }
        });
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
//                    myIncomeResult(result.getData());
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


    @OnClick({R.id.top_myincome_img, R.id.top_myincome_tv_detailed, R.id.income_Btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_myincome_img:
                this.finish();
                break;
            case R.id.top_myincome_tv_detailed:
                MeUiGoto.transactionDetail(this);//交易明细
                break;
            case R.id.income_Btn:
                Withdrawals();
                break;
        }
    }
}
