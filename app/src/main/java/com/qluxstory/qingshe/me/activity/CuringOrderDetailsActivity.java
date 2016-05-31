package com.qluxstory.qingshe.me.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.me.dto.CuringOrderDetailsDTO;
import com.qluxstory.qingshe.me.entity.CuringOrderDetailsEntity;
import com.qluxstory.qingshe.me.entity.CuringOrderDetailsResult;
import com.qluxstory.qingshe.me.entity.CuringOrderListEntity;

import java.util.List;

import butterknife.Bind;

/**
 * 养护订单详情
 */
public class CuringOrderDetailsActivity extends BaseTitleActivity {
    @Bind(R.id.details_tit_img)
    ImageView mDetailsTitImg;
    @Bind(R.id.details_tv_num)
    TextView mDetailsTvNum;
    @Bind(R.id.details_tv_data)
    TextView mDetailsTvData;
    @Bind(R.id.details_tv_money)
    TextView mDetailsTvMoney;
    @Bind(R.id.details_tv_coupon)
    TextView mDetailsTvCoupon;
    @Bind(R.id.details_tv_for)
    TextView mDetailsTvFor;
    @Bind(R.id.details_tv_statu)
    TextView mDetailsTvStatu;
    @Bind(R.id.details_tv_get)
    TextView mDetailsTvGet;
    @Bind(R.id.details_tv_address)
    TextView mDetailsTvAddress;
    @Bind(R.id.details_tv_send)
    TextView mDetailsTvSend;
    @Bind(R.id.details_curing_img)
    ImageView mDetailsCuringImg;
    @Bind(R.id.details_curing_tv1)
    TextView mDetailsCuringTv1;
    @Bind(R.id.details_curing_tv3)
    TextView mDetailsCuringTv3;
    @Bind(R.id.details_curing_tv2)
    TextView mDetailsCuringTv2;
    @Bind(R.id.details_ipone)
    LinearLayout mDetailsIpone;
    @Bind(R.id.details_on)
    LinearLayout mDetailsOn;
    CuringOrderListEntity entity;

    @Override
    protected int getContentResId() {
        return R.layout.activity_curing_order_details;
    }

    @Override
    public void initView() {
        setTitleText("养护订单详情");
        Intent intent  =getIntent();
        if(intent!=null){
            entity= (CuringOrderListEntity)intent.getSerializableExtra("b");
        }else{
            return;
        }
        mDetailsIpone.setOnClickListener(this);
        mDetailsOn.setOnClickListener(this);

    }

    @Override
    public void initData() {
        reqCuringOrderDetails();//养护订单详情

    }

    private void reqCuringOrderDetails() {
        CuringOrderDetailsDTO cdto = new CuringOrderDetailsDTO();
        cdto.setMembermob(AppContext.get("mobileNum", ""));
        cdto.setOrderNum(entity.getOrderNum());
        cdto.setSign(AppConfig.SIGN_1);
        CommonApiClient.curingOrderDetails(this, cdto, new CallBack<CuringOrderDetailsResult>() {
            @Override
            public void onSuccess(CuringOrderDetailsResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.d("养护订单成功");
                    bindResult(result.getData());
                }

            }
        });
    }

    private void bindResult(List<CuringOrderDetailsEntity> data) {
        CuringOrderDetailsEntity curingOrderDetails = data.get(0);
        mDetailsTvNum.setText(curingOrderDetails.getOrderNum());
        mDetailsTvData.setText(curingOrderDetails.getOrderSingleTime());
        mDetailsTvMoney.setText(curingOrderDetails.getOrderMoney());
        mDetailsTvCoupon.setText(curingOrderDetails.getCouponPrice());
        mDetailsTvFor.setText(curingOrderDetails.getTrueMoney());
        mDetailsTvStatu.setText(curingOrderDetails.getOrderState());
        mDetailsTvGet.setText(curingOrderDetails.getConsigneeType());
        mDetailsTvAddress.setText(curingOrderDetails.getConsigneeName()+curingOrderDetails.getDeliveredMobile()
                +curingOrderDetails.getProvincialCity()+curingOrderDetails.getAddressInDetail());
        mDetailsCuringTv1.setText(curingOrderDetails.getComName());
//        mDetailsTvSend
        mDetailsCuringTv2.setText(curingOrderDetails.getOrderMoney());
        mDetailsCuringTv3.setText(curingOrderDetails.getComCount());
        ImageLoaderUtils.displayImage(curingOrderDetails.getCom_cover_pic(),mDetailsCuringImg);
        ImageLoaderUtils.displayImage(curingOrderDetails.getCom_cover_pic(),mDetailsTitImg);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.details_ipone:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                        + getString(R.string.common_service_phone)));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent); //电话客服
                break;
            case R.id.details_on:
                break;

        }
    }
}
