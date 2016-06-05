package com.qluxstory.qingshe.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.issue.IssueUiGoto;
import com.qluxstory.qingshe.me.dto.NumDTO;
import com.qluxstory.qingshe.me.dto.RecordIndianaDTO;
import com.qluxstory.qingshe.me.entity.NumResult;
import com.qluxstory.qingshe.me.entity.RecordIndianaEntity;
import com.qluxstory.qingshe.me.entity.RecordIndianaResult;
import com.qluxstory.qingshe.me.entity.RecordsEntity;

import butterknife.Bind;

/**
 * 夺宝记录之夺宝详情主页面
 */
public class IndianaDetailsActivity extends BaseTitleActivity {
    @Bind(R.id.indiana_code)
    TextView mIndianaCode;
    @Bind(R.id.records_title)
    TextView mRecordsTitle;
    @Bind(R.id.records_term)
    TextView mRecordsTerm;
    @Bind(R.id.det_inf)
    TextView mDetInf;
    @Bind(R.id.det_num)
    TextView mDetNum;
    @Bind(R.id.det_data)
    TextView mDetData;
    @Bind(R.id.records_statu)
    TextView mRecordsStatu;
    @Bind(R.id.det_money)
    TextView mDetMoney;
    @Bind(R.id.records_img)
    ImageView mRecordsImg;
    @Bind(R.id.indiana_rl_look)
    RelativeLayout mLook;
    @Bind(R.id.money)
    TextView mMoney;
    @Bind(R.id.tv_time)
    TextView mTvTime;
    @Bind(R.id.det_pay)
    TextView mDetPay;
    @Bind(R.id.in_btn)
    Button mInBtn;
    @Bind(R.id.det_lin)
    LinearLayout mDetLin;
    private String mCode,mTitle,mPic,mTerm,mBalance;

    @Override
    protected int getContentResId() {
        return R.layout.activity_indiana_details;
    }

    @Override
    public void initView() {
        setTitleText("夺宝详情");
        mInBtn.setText("继续夺宝");
        Intent mIntent = getIntent();
        if(mIntent!=null){
            RecordsEntity entity = (RecordsEntity) mIntent.getBundleExtra("itemBean").getSerializable("entity");
            mCode = entity.getBat_code();
            mTitle = entity.getSna_title();
            mPic = entity.getPic_url();
            mTerm = entity.getRec_term();
            mBalance = entity.getRec_pay_balance();
            mIndianaCode.setText(mCode);
            mRecordsTitle.setText(mTitle);
            mRecordsTerm.setText("第"+mTerm+"期");
            if(entity.getRec_state()=="0"){
                mRecordsStatu.setText("未付款");
                mInBtn.setText("去支付");
                mDetLin.setVisibility(View.GONE);

            }else if(entity.getRec_state()=="1"){
                mRecordsStatu.setText("已付款");
                mInBtn.setText("继续夺宝");
                mDetLin.setVisibility(View.VISIBLE);
            }
            else if(entity.getRec_state()=="2"){
                mRecordsStatu.setText("已中奖");
                mInBtn.setText("去支付");
                mDetLin.setVisibility(View.GONE);
            }
            else if(entity.getRec_state()=="3"){
                mRecordsStatu.setText("未抢中");
                mInBtn.setText("去支付");
                mDetLin.setVisibility(View.GONE);
            }
            else if(entity.getRec_state()=="4"){
                mRecordsStatu.setText("派奖中");
                mInBtn.setText("去支付");
                mDetLin.setVisibility(View.GONE);
            }
            else if(entity.getRec_state()=="5"){
                mRecordsStatu.setText("已完结");
                mInBtn.setText("去支付");
                mDetLin.setVisibility(View.GONE);
            }


            if(entity.getRec_pay_type().equals("1")){
                mDetPay.setText("支付宝");
            }else if(entity.getRec_pay_type().equals("2")){
                mDetPay.setText("微信");
            }else {
                mDetPay.setText("账户余额");
            }
            mDetMoney.setText(mBalance);
            mMoney.setText(mBalance);
            mTvTime.setText(entity.getRec_participate_date());
            ImageLoaderUtils.displayImage(mPic,mRecordsImg);
        }

        mLook.setOnClickListener(this);
        mInBtn.setOnClickListener(this);

    }

    @Override
    public void initData() {
        reqIndianaDetails();//夺宝详情

    }

    private void reqIndianaDetails() {
        RecordIndianaDTO dto=new RecordIndianaDTO();
        dto.setBat_code(mCode);
        dto.setPageSize(PAGE_SIZE);
        dto.setPageIndex(mCurrentPage);
        CommonApiClient.recordsDetails(this, dto, new CallBack<RecordIndianaResult>() {
            @Override
            public void onSuccess(RecordIndianaResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("夺宝详情成功");
                    RecordIndianaEntity record = result.getData().get(0);
                    mDetInf.setText(record.getRec_phone());
                    mDetNum.setText(record.getRec_participate_count());
                    mDetData.setText(record.getRec_participate_date());
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.indiana_rl_look:
                reqNum();//参与信息
                break;

            case R.id.in_btn:
                if(mInBtn.getText().toString().equals("去支付")){
                    Bundle b = new Bundle();
                    b.putString("mBalance",mBalance);
                    b.putString("mTitle",mTitle);
                    b.putString("mPic",mPic);
                    b.putString("mTerm",mTerm);
                    IssueUiGoto.settlement(this,b);

                }else {

                }
                break;

        }
        super.onClick(v);
    }

    private void reqNum() {
        NumDTO dto=new NumDTO();
        dto.setUserPhone(AppContext.get("mobileNum",""));
        dto.setRec_code(mCode);
        dto.setTime(TimeUtils.getSignTime());
        dto.setSign(AppConfig.SIGN_1);
        CommonApiClient.num(this, dto, new CallBack<NumResult>() {
            @Override
            public void onSuccess(NumResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("参与信息成功");

                }

            }
        });
    }
}
