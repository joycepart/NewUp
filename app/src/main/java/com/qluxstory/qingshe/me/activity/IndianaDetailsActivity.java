package com.qluxstory.qingshe.me.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.MainActivity;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.base.SimplePage;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.DialogUtils;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.common.utils.UIHelper;
import com.qluxstory.qingshe.home.entity.Consignee;
import com.qluxstory.qingshe.issue.IssueUiGoto;
import com.qluxstory.qingshe.issue.dto.ToAnnounceDTO;
import com.qluxstory.qingshe.issue.entity.IssueProduct;
import com.qluxstory.qingshe.issue.entity.ToAnnounceEntity;
import com.qluxstory.qingshe.issue.entity.ToAnnounceResult;
import com.qluxstory.qingshe.me.adapter.GridDetailsAdapter;
import com.qluxstory.qingshe.me.dto.ConfirmDTO;
import com.qluxstory.qingshe.me.dto.NumDTO;
import com.qluxstory.qingshe.me.dto.RecordIndianaDTO;
import com.qluxstory.qingshe.me.dto.UpDTO;
import com.qluxstory.qingshe.me.dto.UpInformationDTO;
import com.qluxstory.qingshe.me.entity.NumEntity;
import com.qluxstory.qingshe.me.entity.NumResult;
import com.qluxstory.qingshe.me.entity.RecordIndianaEntity;
import com.qluxstory.qingshe.me.entity.RecordIndianaResult;
import com.qluxstory.qingshe.me.entity.RecordsEntity;
import com.qluxstory.qingshe.me.entity.UpDataEntity;
import com.qluxstory.qingshe.me.entity.UpDataResult;

import java.util.List;

import butterknife.Bind;

/**
 * 夺宝记录之夺宝详情主页面
 */
public class IndianaDetailsActivity extends BaseTitleActivity {
    @Bind(R.id.det_inf_num)
    TextView mInfNum;
    @Bind(R.id.det_num_time)
    TextView mIumTime;
    @Bind(R.id.det_data_user)
    TextView mDataUser;
    @Bind(R.id.det_data_nm)
    TextView mDataNm;
    @Bind(R.id.win_tv)
    TextView mWinTv;
    @Bind(R.id.indiana_win_plo_tv)
    TextView mWinPloTv;
    @Bind(R.id.win_num_time)
    TextView mWinNumTime;
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
    @Bind(R.id.win_rel)
    RelativeLayout mWinRel;
    @Bind(R.id.money)
    TextView mMoney;
    @Bind(R.id.inf_wining)
    TextView mInfWining;
    @Bind(R.id.tv_time)
    TextView mTvTime;
    @Bind(R.id.det_pay)
    TextView mDetPay;
    @Bind(R.id.in_btn)
    Button mInBtn;
    @Bind(R.id.win_btn)
    Button mWinBtn;
    @Bind(R.id.win_lin)
    LinearLayout mWinLin;
    @Bind(R.id.det_lin)
    LinearLayout mLinDetInf;
    @Bind(R.id.indiana_tz)
    LinearLayout mIndianaText;
    @Bind(R.id.indiana_ann)
    LinearLayout mIndianaAnn;
    @Bind(R.id.iandiana_pay)
    LinearLayout mPay;
    @Bind(R.id.indiana_win_plo)
    LinearLayout mWin;
    @Bind(R.id.order_code)
    LinearLayout mOrderCode;
    private String mCode,mTitle,mPic,mTerm,mBalance,mSnaCode,mBnt,mRecCode,mTotal,mCount;
    Consignee consignee;
    IssueProduct issueProduct;
    PopupWindow popGridWindow;

    @Override
    protected int getContentResId() {
        return R.layout.activity_indiana_details;
    }

    @Override
    public void initView() {
        setTitleText("夺宝详情");
        issueProduct = AppContext.getInstance().getIssueProduct();
        consignee = AppContext.getInstance().getConsignee();
        Intent mIntent = getIntent();
        if(mIntent!=null){
            RecordsEntity entity = (RecordsEntity) mIntent.getBundleExtra("itemBean").getSerializable("entity");
            mCode = entity.getRec_code();
            mTitle = entity.getSna_title();
            mPic = entity.getPic_url();
            mTerm = entity.getRec_term();
            mBalance = entity.getRec_pay_balance();
            mSnaCode = entity.getSna_code();
            mBnt = entity.getBat_code();
            mRecCode = entity.getRec_code();
            mIndianaCode.setText(mCode);
            mRecordsTitle.setText(mTitle);
            mRecordsTerm.setText("第"+mTerm+"期");
            LogUtils.e("entity.getRec_state()",""+entity.getRec_state());

            if(entity.getRec_state().equals("0")){
                LogUtils.e("entity.getRec_state()",""+entity.getRec_state());
                mRecordsStatu.setText("未付款");
                mInBtn.setText("去支付");
                mIndianaText.setVisibility(View.GONE);
                mLinDetInf.setVisibility(View.GONE);
                mIndianaAnn.setVisibility(View.GONE);
                mPay.setVisibility(View.VISIBLE);
                mWin.setVisibility(View.GONE);
                mInBtn.setVisibility(View.VISIBLE);

            }else if(entity.getRec_state().equals("1")){
                LogUtils.e("entity.getRec_state()1",""+entity.getRec_state());
                mRecordsStatu.setText("已付款");
                mInBtn.setText("继续夺宝");
                mLinDetInf.setVisibility(View.VISIBLE);
                mIndianaText.setVisibility(View.GONE);
                mIndianaAnn.setVisibility(View.GONE);
                mPay.setVisibility(View.VISIBLE);
                mWin.setVisibility(View.GONE);
                mInBtn.setVisibility(View.VISIBLE);
            }
            else if(entity.getRec_state().equals("2")){
                LogUtils.e("entity.getRec_state()2",""+entity.getRec_state());
                mRecordsStatu.setText("已中奖");
                mInBtn.setText("继续夺宝");
                mIndianaText.setVisibility(View.GONE);
                mLinDetInf.setVisibility(View.VISIBLE);
                mIndianaAnn.setVisibility(View.VISIBLE);
                mInfWining.setVisibility(View.VISIBLE);
                mPay.setVisibility(View.VISIBLE);
                mWin.setVisibility(View.VISIBLE);
                mInBtn.setVisibility(View.GONE);
                reqAnn();
            }
            else if(entity.getRec_state().equals("3")){
                LogUtils.e("entity.getRec_state()3",""+entity.getRec_state());
                mRecordsStatu.setText("未抢中");
                mInBtn.setText("继续夺宝");
                mPay.setVisibility(View.VISIBLE);
                mIndianaText.setVisibility(View.GONE);
                mLinDetInf.setVisibility(View.VISIBLE);
                mIndianaAnn.setVisibility(View.VISIBLE);
                mWin.setVisibility(View.GONE);
                mInBtn.setVisibility(View.VISIBLE);
                reqAnn();
            }
            else if(entity.getRec_state().equals("4")){
                LogUtils.e("entity.getRec_state()4",""+entity.getRec_state());
                mRecordsStatu.setText("派奖中");
                mInBtn.setText("继续夺宝");
                mIndianaText.setVisibility(View.GONE);
                mLinDetInf.setVisibility(View.VISIBLE);
                mIndianaAnn.setVisibility(View.VISIBLE);
                mInfWining.setVisibility(View.VISIBLE);
                mPay.setVisibility(View.VISIBLE);
                mWin.setVisibility(View.VISIBLE);
                mInBtn.setVisibility(View.GONE);
                mWinBtn.setText("确认收货");
                reqAnn();
                reqUpdate();
            }
            else if(entity.getRec_state().equals("5")){
                LogUtils.e("entity.getRec_state()5",""+entity.getRec_state());
                mRecordsStatu.setText("已完结");
                mPay.setVisibility(View.GONE);
                mIndianaText.setVisibility(View.GONE);
                mLinDetInf.setVisibility(View.VISIBLE);
                mIndianaAnn.setVisibility(View.VISIBLE);
                mWin.setVisibility(View.VISIBLE);
                mInBtn.setVisibility(View.GONE);
                mWinBtn.setText("暂未发货");
                reqAnn();
                reqUpdate();
            }else if(entity.getRec_state().equals("6")){
                mRecordsStatu.setText("已取消");
                mInBtn.setText("继续夺宝");
                mPay.setVisibility(View.GONE);
                mLinDetInf.setVisibility(View.GONE);
                mIndianaAnn.setVisibility(View.VISIBLE);
                mIndianaText.setVisibility(View.VISIBLE);
                mWin.setVisibility(View.GONE);
                mInBtn.setVisibility(View.VISIBLE);
                reqAnn();
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
        mWinTv.setOnClickListener(this);
        mWinBtn.setOnClickListener(this);

    }

    private void reqUpdate() {
        UpDTO gdto=new UpDTO();
        gdto.setRec_code(mRecCode);
        gdto.setUserPhone(AppContext.get("mobileNum",""));
        CommonApiClient.upData(this, gdto, new CallBack<UpDataResult>() {
            @Override
            public void onSuccess(UpDataResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("更新收货人信息成功");
                    if(null == result.getData()){
                        return;
                    }else {
                        bindUpdate(result.getData());
                    }

                }

            }
        });
    }

    private void bindUpdate(List<UpDataEntity> data) {
        UpDataEntity entity = data.get(0);
        mWinRel.setVisibility(View.GONE);
        mWinLin.setVisibility(View.VISIBLE);
        mWinPloTv.setText(entity.getCon_name()+" "+entity.getCon_phone());
        mWinNumTime.setText(entity.getCon_privince()+" "+entity.getCon_address());
        mDataUser.setText(entity.getOrder_name());
        mDataNm.setText(entity.getOrder_code());

    }

    @Override
    public void initData() {
        reqIndianaDetails();//夺宝详情

    }

    private void reqAnn() {
        ToAnnounceDTO gdto=new ToAnnounceDTO();
        gdto.setPageSize(PAGE_SIZE);
        gdto.setPageIndex(mCurrentPage);
        gdto.setSna_code(mSnaCode);
        CommonApiClient.announce(this, gdto, new CallBack<ToAnnounceResult>() {
            @Override
            public void onSuccess(ToAnnounceResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("往期揭晓成功");
                    if(null == result.getData()){
                        return;
                    }else {
                        bindResult(result.getData());
                    }

                }

            }
        });

    }

    private void bindResult(List<ToAnnounceEntity> data) {
        ToAnnounceEntity entity = data.get(0);
        mInfNum.setText(entity.getSna_lucky_num());
        mIumTime.setText(entity.getSna_begin_date());
        mDataUser.setText(entity.getSna_lucky_people());
        mDataNm.setText(entity.getSna_participate_count());
    }


    private void reqIndianaDetails() {
        RecordIndianaDTO dto=new RecordIndianaDTO();
        dto.setBat_code(mBnt);
        dto.setPageSize(PAGE_SIZE);
        dto.setPageIndex(mCurrentPage);
        CommonApiClient.recordsDetails(this, dto, new CallBack<RecordIndianaResult>() {
            @Override
            public void onSuccess(RecordIndianaResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("夺宝详情成功");
                    if(null == result.getData()){
                        return;
                    }else {
                        RecordIndianaEntity record = result.getData().get(0);
                        mDetInf.setText(record.getRec_phone());
                        mDetNum.setText(record.getRec_participate_count()+"次");
                        mDetData.setText(record.getRec_participate_date());
                    }

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
            case R.id.win_btn:
                if(TextUtils.isEmpty(mWinPloTv.getText().toString())||TextUtils.isEmpty(mWinNumTime.getText().toString())){
                    DialogUtils.showPrompt(this, "请选择收货地址", "知道了");
                }else if(mRecordsStatu.getText().toString().equals("已中奖")){
                    reqUpInfomation();//更新中奖人信息
                }
                else if(mRecordsStatu.getText().toString().equals("派奖中")){
                    reqConfirm();//确认收货地址
                }
                break;
            case R.id.win_tv:
                UIHelper.showRorSelectFragment(this, SimplePage.SELECT_ADDRESS);//收货地址
                break;
            case R.id.grid_tv:
                backgroundAlpha(1f);
                popGridWindow.dismiss();
                break;

            case R.id.in_btn:
                if(mInBtn.getText().toString().equals("去支付")){
                    Bundle b = new Bundle();
                    issueProduct.setmPicUrl(mPic);
                    issueProduct.setmSnaTerm(mTerm);
                    issueProduct.setmSnaTitle(mTitle);
                    issueProduct.setmSnaCode(mSnaCode);
                    issueProduct.setmBatCode(mBnt);
                    issueProduct.setmRecCode(mRecCode);
                    issueProduct.setmBalance(mBalance);
                    LogUtils.e("mRecCode-----",""+mRecCode);
                    IssueUiGoto.settlement(this,b);//结算

                }else {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("tag",2);
                    startActivity(intent);

                }
                break;

        }
        super.onClick(v);
    }

    private void reqUpInfomation() {
        UpInformationDTO dto=new UpInformationDTO();
        String time = TimeUtils.getSignTime();
        dto.setTime(time);
        dto.setSign(time+AppConfig.SIGN_1);
        dto.setRec_phone(AppContext.get("mobileNum",""));
        dto.setRec_code(mCode);
        dto.setCon_address(consignee.getAddressInDetail());
        dto.setCon_name(consignee.getConsigneeName());
        dto.setCon_phone(consignee.getDeliveredMobile());
        dto.setCon_privince(consignee.getProvincialCity());
        CommonApiClient.upInformation(this, dto, new CallBack<NumResult>() {
            @Override
            public void onSuccess(NumResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("更新中奖人信息成功");
                    mWinBtn.setText("等待发货");
                    mWinBtn.setEnabled(false);

                }

            }
        });
    }

    private void reqConfirm() {
        ConfirmDTO dto=new ConfirmDTO();
        String time = TimeUtils.getSignTime();
        dto.setTime(time);
        dto.setSign(time+AppConfig.SIGN_1);
        dto.setRec_phone(AppContext.get("mobileNum",""));
        dto.setRec_code(mCode);
        CommonApiClient.comfirm(this, dto, new CallBack<NumResult>() {
            @Override
            public void onSuccess(NumResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("确认收货成功");
                    mWinBtn.setText("订单已完成");
                    mRecordsStatu.setText("已完结");

                }

            }
        });
    }

    private void reqNum() {
        NumDTO dto=new NumDTO();
        String time = TimeUtils.getSignTime();
        dto.setUserPhone(AppContext.get("mobileNum",""));
        dto.setRec_code(mCode);
        dto.setTime(time);
        dto.setSign(time+AppConfig.SIGN_1);
        CommonApiClient.num(this, dto, new CallBack<NumResult>() {
            @Override
            public void onSuccess(NumResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("参与信息成功");
                    showPopNm(result.getData());
                }

            }
        });
    }

    private void showPopNm(List<NumEntity> mNumEntity) {
        LogUtils.e("showPopNm---","showPopNm");
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.from(this).inflate(R.layout.activity_grid_num, null);
        popGridWindow = new PopupWindow(view, WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,true);

        backgroundAlpha(0.7f);
        // 需要设置一下此参数，点击外边可消失
        popGridWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击窗口外边窗口消失
        popGridWindow.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        popGridWindow.setFocusable(false);
        TextView girdTv= (TextView) view.findViewById(R.id.grid_tv);
        girdTv.setOnClickListener(this);
        GridView girdView= (GridView) view.findViewById(R.id.grid_num);
        girdView.setAdapter(new GridDetailsAdapter(this, mNumEntity));
        View parent = getWindow().getDecorView();//高度为手机实际的像素高度
        popGridWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
        //添加pop窗口关闭事件
        popGridWindow.setOnDismissListener(new poponDismissListener());
    }

    public class poponDismissListener implements PopupWindow.OnDismissListener{

        @Override
        public void onDismiss() {
            LogUtils.e("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
            popGridWindow.dismiss();
        }

    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case UIHelper.SELECT_REQUEST:
                if(!TextUtils.isEmpty(consignee.getDeliveredMobile())) {
                    mWinRel.setVisibility(View.GONE);
                    mWinLin.setVisibility(View.VISIBLE);
                    mWinPloTv.setText(consignee.getConsigneeName() + consignee.getDeliveredMobile());
                    mWinNumTime.setText(consignee.getProvincialCity() + consignee.getAddressInDetail());
                    break;
                }else {
                    mWinLin.setVisibility(View.GONE);
                    mWinRel.setVisibility(View.VISIBLE);
                }

        }

    }
}
