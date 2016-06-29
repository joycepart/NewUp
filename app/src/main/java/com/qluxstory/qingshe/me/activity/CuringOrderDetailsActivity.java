package com.qluxstory.qingshe.me.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseApplication;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.me.MeUiGoto;
import com.qluxstory.qingshe.me.dto.CuringOrderDetailsDTO;
import com.qluxstory.qingshe.me.dto.KdDTO;
import com.qluxstory.qingshe.me.entity.CuringOrderDetailsEntity;
import com.qluxstory.qingshe.me.entity.CuringOrderDetailsResult;
import com.qluxstory.qingshe.me.entity.CuringOrderListEntity;

import java.util.List;

import butterknife.Bind;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

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
    @Bind(R.id.details_tv_pri)
    TextView mTtvPri;
    @Bind(R.id.details_tv_details)
    TextView mTvDetails;
    @Bind(R.id.details_tv_city)
    TextView mTvCity;
    @Bind(R.id.order_details_tv)
    TextView mDetailsTv;
    @Bind(R.id.order_details_mode)
    TextView mOrderMode;
    @Bind(R.id.order_details_send)
    TextView mOrderSend;
    @Bind(R.id.details_tv_integral)
    TextView mIntegral;
    @Bind(R.id.ipone_kf)
    LinearLayout mDetailsIpone;
    @Bind(R.id.ipone_zx)
    LinearLayout mDetailsOn;
    @Bind(R.id.order_lin)
    LinearLayout mOrderLin;
    @Bind(R.id.item_kd)
    LinearLayout mKd;
    @Bind(R.id.kd_lin1)
    LinearLayout mLin1;
    @Bind(R.id.kd_lin2)
    LinearLayout mLin2;
    @Bind(R.id.kd_et1)
    EditText mEt1;
    @Bind(R.id.kd_et2)
    EditText mEt2;
    @Bind(R.id.kd_tv1)
    TextView mKdTv1;
    @Bind(R.id.kd_tv2)
    TextView mKdTv2;
    CuringOrderListEntity entity;
    CuringOrderDetailsEntity curingOrderDetails;
    private String mOrdNum;

    @Override
    protected int getContentResId() {
        return R.layout.activity_curing_order_details;
    }

    @Override
    public void initView() {
        setTitleText("养护订单详情");
        Intent intent = getIntent();
        if(intent!=null){
            entity = (CuringOrderListEntity) intent.getBundleExtra("bundle").getSerializable("entitiy");
            LogUtils.e("entity----",entity.getOrderNum());
        }
        mDetailsIpone.setOnClickListener(this);
        mDetailsOn.setOnClickListener(this);
        mDetailsTv.setOnClickListener(this);

    }

    @Override
    public void initData() {
        reqCuringOrderDetails();//养护订单详情

    }

    private void reqCuringOrderDetails() {
        CuringOrderDetailsDTO cdto = new CuringOrderDetailsDTO();
        String time = TimeUtils.getSignTime();
        cdto.setMembermob(AppContext.get("mobileNum", ""));
        cdto.setOrderNum(entity.getOrderNum());
        cdto.setSign(time+AppConfig.SIGN_1);
        cdto.setTimestamp(time);
        CommonApiClient.curingOrderDetails(this, cdto, new CallBack<CuringOrderDetailsResult>() {
            @Override
            public void onSuccess(CuringOrderDetailsResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.d("养护订单详情成功");
                    bindResult(result.getData());
                }

            }
        });
    }

    private void bindResult(List<CuringOrderDetailsEntity> data) {
        curingOrderDetails = data.get(0);
        mOrdNum = curingOrderDetails.getOrderNum();
        LogUtils.e("bindResult----",mOrdNum);
        mDetailsTvNum.setText(curingOrderDetails.getOrderNum());
        mDetailsTvData.setText(curingOrderDetails.getOrderSingleTime());
        mDetailsTvMoney.setText(curingOrderDetails.getComPrice());
        mDetailsTvCoupon.setText(curingOrderDetails.getCouponPrice());
        mDetailsTvFor.setText(curingOrderDetails.getTrueMoney());
        mDetailsTvGet.setText(curingOrderDetails.getConsigneeType());
        if(TextUtils.isEmpty(curingOrderDetails.getIntegralNum())){
            mIntegral.setText("0");
        }else {
            mIntegral.setText(curingOrderDetails.getIntegralNum());
        }

        if(curingOrderDetails.getConsigneeType().equals("全国包回邮")){
            mOrderSend.setText("收货地址");
            mDetailsTvAddress.setText(curingOrderDetails.getConsigneeName()+curingOrderDetails.getDeliveredMobile());
            mTtvPri.setText(curingOrderDetails.getProvincialCity());
            mTvDetails.setText(curingOrderDetails.getAddressInDetail());
            mDetailsTvSend.setText(curingOrderDetails.getSto_name()+curingOrderDetails.getSto_phone());
            mTvCity.setText(curingOrderDetails.getDis_cityAddress());
            mOrderLin.setVisibility(View.VISIBLE);
        }
        else if(curingOrderDetails.getConsigneeType().equals("上门取送")){
            mOrderSend.setText("上门地址");
            mDetailsTvAddress.setText(curingOrderDetails.getConsigneeName()+curingOrderDetails.getDeliveredMobile());
            mTtvPri.setText(curingOrderDetails.getProvincialCity());
            mTvDetails.setText(curingOrderDetails.getAddressInDetail());
            mOrderLin.setVisibility(View.GONE);
        }
        else if(curingOrderDetails.getConsigneeType().equals("自送门店")){
            mOrderSend.setText("门店地址");
            mDetailsTvAddress.setText(curingOrderDetails.getConsigneeName()+curingOrderDetails.getDeliveredMobile());
            mTtvPri.setText(curingOrderDetails.getProvincialCity());
            mTvDetails.setText(curingOrderDetails.getAddressInDetail());
            mOrderLin.setVisibility(View.GONE);
        }



        mDetailsCuringTv1.setText(curingOrderDetails.getComName());
        mDetailsCuringTv2.setText(curingOrderDetails.getOrderMoney());
        mDetailsCuringTv3.setText(curingOrderDetails.getComCount());
        if(curingOrderDetails.getOrderState().equals("0")){
            if(curingOrderDetails.getIsovertime().equals("0")){
                mDetailsTvStatu.setText("未付款");
                mDetailsTv.setVisibility(View.VISIBLE);
            }else if(curingOrderDetails.getIsovertime().equals("1")){
                mDetailsTvStatu.setText("已取消");
                mDetailsTv.setVisibility(View.GONE);
            }

        }
        else if(curingOrderDetails.getOrderState().equals("1")){
            mDetailsTvStatu.setText("已取消");
            mDetailsTv.setVisibility(View.GONE);
        }
        else if(curingOrderDetails.getOrderState().equals("2")){
            mDetailsTvStatu.setText("停用");
            mDetailsTv.setVisibility(View.GONE);
        }
        else if(curingOrderDetails.getOrderState().equals("3")){
            mDetailsTvStatu.setText("确认出库");
            mDetailsTv.setVisibility(View.GONE);
        }
        else if(curingOrderDetails.getOrderState().equals("4")){
            mDetailsTvStatu.setText("已发货");
            mDetailsTv.setVisibility(View.GONE);
        }
        else if(curingOrderDetails.getOrderState().equals("5")){
            mDetailsTvStatu.setText("已收货");
            mDetailsTv.setVisibility(View.GONE);
        }
        else if(curingOrderDetails.getOrderState().equals("10")){
            mDetailsTvStatu.setText("已付款");
            mDetailsTv.setVisibility(View.GONE);
        }
        else if(curingOrderDetails.getOrderState().equals("Y001")){
            mDetailsTvStatu.setText("取件中");
            mDetailsTv.setVisibility(View.GONE);
        }
        else if(curingOrderDetails.getOrderState().equals("L006")){
            mDetailsTvStatu.setText("已接收");
            mDetailsTv.setVisibility(View.GONE);
        }
        else {
            mDetailsTvStatu.setText("处理中");
            mDetailsTv.setVisibility(View.GONE);
        }

        if(TextUtils.isEmpty(curingOrderDetails.getExpressDeliverCode())){
            if(curingOrderDetails.getOrderState().equals("10")&&curingOrderDetails.getConsigneeType().equals("全国包回邮")){
                mKd.setVisibility(View.VISIBLE);
                mLin1.setVisibility(View.VISIBLE);
                mLin2.setVisibility(View.GONE);
                mDetailsTv.setText("上传快递信息");
                mDetailsTv.setVisibility(View.VISIBLE);
            }else {
                mKd.setVisibility(View.GONE);
                mDetailsTv.setVisibility(View.GONE);
            }
        }else {
            mKd.setVisibility(View.VISIBLE);
            mLin1.setVisibility(View.GONE);
            mLin2.setVisibility(View.VISIBLE);
            mKdTv1.setText("快递公司："+curingOrderDetails.getExpressDeliverName());
            mKdTv2.setText("快递单号："+curingOrderDetails.getExpressDeliverCode());
        }
        LogUtils.e("curingOrderListEntity.getServerKHImg()----",curingOrderDetails.getServerKHImg());
        LogUtils.e("curingOrderListEntity.getApp_show_pic()()----",curingOrderDetails.getApp_show_pic());
        ImageLoaderUtils.displayImage(curingOrderDetails.getApp_show_pic(),mDetailsCuringImg);
        ImageLoaderUtils.displayImage(curingOrderDetails.getServerKHImg(),mDetailsTitImg);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ipone_kf:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                        + getString(R.string.common_service_phone)));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent); //电话客服
                break;
            case R.id.order_details_tv:
                if(curingOrderDetails.getOrderState().equals("10")&&curingOrderDetails.getConsigneeType().equals("全国包回邮")){
                    reqKd();

                }else {
                    Bundle b = new Bundle();
                    b.putString("mOrdNum",mOrdNum);
                    MeUiGoto.payment(mContext,b);//支付订单详情
                }

                break;
            case R.id.ipone_zx:
                service();//在线客服
                break;

        }
    }

    private void reqKd() {
        KdDTO cdto = new KdDTO();
        String time = TimeUtils.getSignTime();
        cdto.setExpdelicode(mEt2.getText().toString());
        cdto.setExpdeliname(mEt1.getText().toString());
        cdto.setOrderNum(entity.getOrderNum());
        cdto.setSign(time+AppConfig.SIGN_1);
        cdto.setTimestamp(time);
        CommonApiClient.kd(this, cdto, new CallBack<CuringOrderDetailsResult>() {
            @Override
            public void onSuccess(CuringOrderDetailsResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.d("快递信息成功");
                    mDetailsTv.setVisibility(View.GONE);
                }

            }
        });
    }

    private void service() {
        if (getApplicationInfo().packageName
                .equals(BaseApplication.getCurProcessName(getApplicationContext()))) {
                        /*IMKit SDK调用第二步, 建立与服务器的连接*/
            LogUtils.e("mRongyunToken", "" + AppContext.get("mRongyunToken", ""));
            RongIM.connect(AppContext.get("mRongyunToken", ""), new RongIMClient.ConnectCallback() {
                /*  *
                  *
                  Token 错误
                  ，
                  在线上环境下主要是因为 Token
                  已经过期，
                  您需要向 App
                  Server 重新请求一个新的
                  Token*/
                @Override
                public void onTokenIncorrect() {
                    LogUtils.e("", "--onTokenIncorrect");
                }

                /**
                 * 连接融云成功
                 *
                 * @param userid 当前
                 *               token
                 */
                @Override
                public void onSuccess(String userid) {
                    LogUtils.e("--onSuccess", "--onSuccess" + userid);
                    /**
                     *启动客服聊天界面。
                     *
                     *@param context 应用上下文。
                     *@param conversationType 开启会话类型。
                     *@param targetId 客服 Id。
                     *@param title 客服标题。*/
                    //                    RongIM.getInstance().startConversation(ProductBrowserActivity.this,
//                            io.rong.imlib.model.Conversation.ConversationType.APP_PUBLIC_SERVICE,
//                            "KEFU146286268172386", "客服");//开发环境

                    RongIM.getInstance().startConversation(CuringOrderDetailsActivity.this,
                            io.rong.imlib.model.Conversation.ConversationType.APP_PUBLIC_SERVICE,
                            "KEFU146578665455786", "客服");//生产环境
                }

                /*  *
                  *连接融云失败
                  @param
                  errorCode 错误码
                  可到官网 查看错误码对应的注释*/
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    LogUtils.e("--onError", "--onError" + errorCode);
                }
            });
        }
    }
}
