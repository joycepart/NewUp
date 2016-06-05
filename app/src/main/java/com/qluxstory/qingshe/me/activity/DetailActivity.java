package com.qluxstory.qingshe.me.activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.me.entity.TransactionEntity;

import butterknife.Bind;

/**
 * 明细详情主页面
 */
public class DetailActivity extends BaseTitleActivity {
    @Bind(R.id.details_tit)
    TextView mTit;
    @Bind(R.id.details_balance)
    TextView mBalance;
    @Bind(R.id.details_buy)
    TextView mBuy;
    @Bind(R.id.details_coupon)
    TextView mCoupon;
    @Bind(R.id.details_toa)
    TextView mToa;
    @Bind(R.id.details_num)
    TextView mNum;
    @Bind(R.id.details_de)
    TextView mDe;
    @Bind(R.id.details_data)
    TextView mData;
    @Bind(R.id.details_name)
    TextView mName;
    @Bind(R.id.details_rel_name)
    RelativeLayout mRelName;
    @Bind(R.id.details_rel_coupon)
    RelativeLayout mRelCoupon;
    @Bind(R.id.details_rel_money)
    RelativeLayout mRelMoney;
    TransactionEntity entity;

    @Override
    protected int getContentResId() {
        return R.layout.activity_details;
    }

    @Override
    public void initView() {
        setTitleText("明细详情");
        Intent intent = getIntent();
        if(intent!=null){
            entity = (TransactionEntity) intent.getBundleExtra("bundle").getSerializable("entity");
            if(entity.getComName().equals("提现")){
                mRelName.setVisibility(View.GONE);
                mRelCoupon.setVisibility(View.GONE);
                mRelMoney.setVisibility(View.GONE);
                mTit.setText("转出金额");
                mBuy.setText("提现");

            }else {
                mRelName.setVisibility(View.VISIBLE);
                mRelCoupon.setVisibility(View.VISIBLE);
                mRelMoney.setVisibility(View.VISIBLE);
                mTit.setText("消费金额");
                mBuy.setText("购买消费");
                mName.setText(entity.getComName());
                mCoupon.setText(entity.getComCommission());
                mToa.setText(entity.getComIncomeAmount());
            }
            mBalance.setText(entity.getComIncomeAmount());
            mDe.setText(entity.getComDetailProParty());
            mNum.setText(entity.getComDataSources());
            mData.setText(entity.getComTradingData()+" "+entity.getComTradingTime());
        }

    }

    @Override
    public void initData() {

    }

}
