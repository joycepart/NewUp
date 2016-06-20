package com.qluxstory.qingshe.me.fragment;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BasePullFragment;
import com.qluxstory.qingshe.common.dto.BaseDTO;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.DialogUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.common.widget.EmptyLayout;
import com.qluxstory.qingshe.common.widget.FullyLinearLayoutManager;
import com.qluxstory.qingshe.me.dto.ExchangeVoucherDTO;
import com.qluxstory.qingshe.me.entity.ExchangeVoucherResult;
import com.qluxstory.qingshe.me.entity.MyCouponEntity;
import com.qluxstory.qingshe.me.entity.MyCouponResult;

import butterknife.Bind;

/**
 *我的优惠劵 fragment
 */
public class MyCouponFragment extends BasePullFragment {
    @Bind(R.id.mycoupon_list)
    RecyclerView mMycouponList;
    @Bind(R.id.mycoupon_et)
    EditText mMouponEt;
    @Bind(R.id.mycoupon_tv)
    TextView mCouponTv;
    BaseSimpleRecyclerAdapter mMycouponListAdapter;
    private String strPhoneNum;
    private String mMoupom;

    @Override
    public void initView(View view) {
        super.initView(view);
        strPhoneNum = AppContext.get("mobileNum","");
        mCouponTv.setOnClickListener(this);
        mMycouponList.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        mMycouponListAdapter=new BaseSimpleRecyclerAdapter<MyCouponEntity>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_novice_securities;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, MyCouponEntity myCouponEntity, int position) {
                if(myCouponEntity.getCouponType().equals("1001")){
                    holder.setText(R.id.novice_tv,myCouponEntity.getDiscountNumber()+"折");
                }else if(myCouponEntity.getCouponType().equals("1002")){
                    holder.setText(R.id.novice_tv,"满"+myCouponEntity.getCouponmoney()+"减"+myCouponEntity.getCouponMoneyEqual());
                }
                else if(myCouponEntity.getCouponType().equals("1003")){
                    holder.setText(R.id.novice_tv,"免费");
                }
                else if(myCouponEntity.getCouponType().equals("1004")){
                    holder.setText(R.id.novice_tv,"直减"+myCouponEntity.getCouponMoneyEqual());
                }
                else if(myCouponEntity.getCouponType().equals("1005")){
                    holder.setText(R.id.novice_tv,myCouponEntity.getCouponMoneyEqual()+"抵用"+myCouponEntity.getCouponmoney());
                }
                if(myCouponEntity.getCouponRangeOfUse().equals("2001")){
                    holder.setText(R.id.coupon_vt,"优惠劵");
                }else if(myCouponEntity.getCouponRangeOfUse().equals("2002")){
                    holder.setText(R.id.coupon_vt,"服务劵");
                }



                holder.setText(R.id.item_tv,myCouponEntity.getCouponRedeemName());
                holder.setText(R.id.tv_time,myCouponEntity.getCouponExpirationTime());
            }


        };
        mMycouponList.setAdapter(mMycouponListAdapter);

    }
    @Override
    public void initData() {
        reqCoupon();//优惠劵

    }



    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.mycoupon_tv:
                mMoupom = mMouponEt.getText().toString();
                if(TextUtils.isEmpty(mMoupom)){
                    DialogUtils.showPrompt(getActivity(),"优惠劵兑换码为空!","确定");
                }else {
                    exchangeVoucher();//兑换优惠劵
                }

                break;
        }
    }

    protected void reqCoupon() {
        BaseDTO bdto=new BaseDTO();
        bdto.setMembermob(strPhoneNum);
        bdto.setSign(AppConfig.SIGN_1);
        bdto.setTimestamp(TimeUtils.getSignTime());
        CommonApiClient.getCoupon(this, bdto, new CallBack<MyCouponResult>() {
            @Override
            public void onSuccess(MyCouponResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.d("兑换优惠劵成功");
                    mErrorLayout.setErrorMessage("暂无优惠劵",mErrorLayout.FLAG_NODATA);
                    mErrorLayout.setErrorImag(R.drawable.siaieless1,mErrorLayout.FLAG_NODATA);
                    if(result.getData().get(0).getCouponExpirationTime()==null){
                        mErrorLayout.setErrorType(EmptyLayout.NODATA);
                    }else {
                        mMycouponListAdapter.removeAll();
                        mMycouponListAdapter.append(result.getData());
                    }

                }
            }
        });

    }
    private void exchangeVoucher() {
        ExchangeVoucherDTO bdto=new ExchangeVoucherDTO();
        bdto.setCouponredeemcode(mMoupom);
        bdto.setMembermob(strPhoneNum);
        bdto.setSign(AppConfig.SIGN_1);
        bdto.setTimestamp(TimeUtils.getSignTime());
        CommonApiClient.exchangeVoucher(this, bdto, new CallBack<ExchangeVoucherResult>() {
            @Override
            public void onSuccess(ExchangeVoucherResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.d("优惠劵请求成功");
                    mErrorLayout.setErrorMessage("暂无优惠劵",mErrorLayout.FLAG_NODATA);
                    mErrorLayout.setErrorImag(R.drawable.siaieless1,mErrorLayout.FLAG_NODATA);
                    if(result.getData().get(0).getCouponExpirationTime()==null){
//                        mErrorLayout.setErrorType(EmptyLayout.NODATA);
                    }else {
                        mMycouponListAdapter.removeAll();
                        mMycouponListAdapter.append(result.getData());
                    }

                }
            }
        });
    }




    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_mycoupon;
    }
}
