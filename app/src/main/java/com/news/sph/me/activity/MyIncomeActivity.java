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
import com.news.sph.me.utils.MeUiGoto;
import com.news.sph.utils.LogUtils;

import java.util.List;

import butterknife.Bind;

/**
 * 我的收入主页面
 */
public class MyIncomeActivity extends BaseActivity {

    @Bind(R.id.top_myincome_img)
    ImageView mTopMyincomeImg;
    @Bind(R.id.top_myincome_tv_detailed)
    TextView mTopMyincomeTv;
    @Bind(R.id.income_Btn)
    Button mIncomeBtn;
    private String strPhoneNum;
    @Bind(R.id.myincome_withdrawals)
    TextView mWithdrawlas;
    @Bind(R.id.myincome_accumulate)
    TextView mAccumulate;
    /**
     * 可提现余额
     */
    private String mCashaMountMoney;
    /**
     * 累计收入
     */
    private String mAccumuLatedMoney;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_me_myincome;
    }



    @Override
    public void initView() {
        strPhoneNum = AppContext.getInstance().getUser().getUserMobile();
        MyIncome();
        mTopMyincomeImg.setOnClickListener(this);
        mTopMyincomeTv.setOnClickListener(this);
        mIncomeBtn.setOnClickListener(this);


    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_myincome_img:
                this.finish();
                break;
            case R.id.top_myincome_tv_detailed:
                MeUiGoto.transactionDetail(this);//交易明细
                break;
            case R.id.income_Btn:
                Withdrawals();
                break;
            default:
                break;
        }
        super.onClick(v);
    }

    private void Withdrawals() {
        WithdrawalsDTO mDto=new WithdrawalsDTO();
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
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.d("提现请求成功");
                }

            }
        });
    }

    private void MyIncome() {
        BaseDTO bdto=new BaseDTO();

        bdto.setMembermob(strPhoneNum);
        bdto.setSign(AppConfig.SIGN_1);
        CommonApiClient.myIncome(this, bdto, new CallBack<MyIncomeResult>() {
            @Override
            public void onSuccess(MyIncomeResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.d("我的收入请求成功");
                    myIncomeResult(result.getData());
                }


            }
        });
    }

    private void myIncomeResult(List<MyIncomeEntity> data) {
        mCashaMountMoney = data.get(0).getCashamountmoney();
        mAccumuLatedMoney = data.get(0).getAccumulatedmoney();
        mWithdrawlas.setText(mCashaMountMoney);
        mAccumulate.setText(mAccumuLatedMoney);
    }
}
