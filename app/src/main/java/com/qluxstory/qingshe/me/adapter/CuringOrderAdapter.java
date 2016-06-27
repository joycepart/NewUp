package com.qluxstory.qingshe.me.adapter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.me.MeUiGoto;
import com.qluxstory.qingshe.me.dto.CancelDTO;
import com.qluxstory.qingshe.me.entity.CuringOrderListEntity;
import com.qluxstory.qingshe.me.entity.CuringOrderListResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/5/17.
 */
public class CuringOrderAdapter extends BaseSimpleRecyclerAdapter<CuringOrderListEntity> {
    RelativeLayout lin;
    TextView mTv;
    FragmentActivity mContext;
    private String mOrdNum;
    List<CuringOrderListEntity> list = new ArrayList<>();

    public CuringOrderAdapter(FragmentActivity context) {
        mContext = context;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_list_cuingorder;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, final CuringOrderListEntity curingOrderListEntity, final int position) {

            mTv = holder.getView(R.id.list_curing_tv2);
            lin = holder.getView(R.id.curingorder_rel);
            list.add(position,curingOrderListEntity);
            holder.setText(R.id.order_num,curingOrderListEntity.getOrderNum());
            holder.setText(R.id.order_data,curingOrderListEntity.getOrderSingleTime());
            holder.setText(R.id.list_curing_tv1,curingOrderListEntity.getComName());
            holder.setText(R.id.list_curing_tv4,curingOrderListEntity.getOrderMoney());
            holder.setText(R.id.curing_vp_tv,curingOrderListEntity.getComCount());
            LogUtils.e("getOrderState---",""+curingOrderListEntity.getOrderState());
            LogUtils.e("getIsovertime---",""+curingOrderListEntity.getIsovertime());
            if(curingOrderListEntity.getOrderState().equals("0")){
                if(curingOrderListEntity.getIsovertime().equals("0")){
                    mTv.setText("未付款");
                    lin.setVisibility(View.VISIBLE);
                    Button mCanle = holder.getView(R.id.curingorder_canle);
                    mCanle.setOnClickListener(new View.OnClickListener() {
                        RelativeLayout rel =lin;
                        TextView tv = mTv;
                        @Override
                        public void onClick(View v) {
                            LogUtils.e("mCanle---","取消订单");
                            LogUtils.e("position---",""+position);
                            LogUtils.e("list---",""+list.get(position).getOrderNum());
                            CancelDTO cdto=new CancelDTO();
                            String time = TimeUtils.getSignTime();
                            cdto.setMembermob(AppContext.get("mobileNum",""));
                            cdto.setSign(time+AppConfig.SIGN_1);
                            cdto.setTimestamp(time);
                            cdto.setOrderNum(list.get(position).getOrderNum());
                            CommonApiClient.cancel(mContext, cdto, new CallBack<CuringOrderListResult>() {
                                @Override
                                public void onSuccess(CuringOrderListResult result) {
                                    if(AppConfig.SUCCESS.equals(result.getStatus())){
                                        LogUtils.e("取消订单成功");
                                        rel.setVisibility(View.INVISIBLE);
                                        tv.setText("已取消");
                                    }

                                }
                            });


                        }
                    });
                    TextView mPay = holder.getView(R.id.curingorder_pay);
                    mPay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle b = new Bundle();
                            b.putString("mOrdNum",list.get(position).getOrderNum());
                            MeUiGoto.payment(mContext,b);//支付订单详情

                        }
                    });
                }
                else if(curingOrderListEntity.getIsovertime().equals("1")){
                    mTv.setText("已取消");
                    lin.setVisibility(View.INVISIBLE);
                }
            }else if(curingOrderListEntity.getOrderState().equals("1")){
                mTv.setText("已取消");
                lin.setVisibility(View.INVISIBLE);
            }

            else if(curingOrderListEntity.getOrderState().equals("2")){
                mTv.setText("停用");
                lin.setVisibility(View.INVISIBLE);
            }
            else if(curingOrderListEntity.getOrderState().equals("3")){
                mTv.setText("确认出库");
                lin.setVisibility(View.INVISIBLE);
            }
            else if(curingOrderListEntity.getOrderState().equals("4")){
                mTv.setText("已发货");
                lin.setVisibility(View.INVISIBLE);
            }
            else if(curingOrderListEntity.getOrderState().equals("5")){
                mTv.setText("已收货");
                lin.setVisibility(View.INVISIBLE);
            }
            else if(curingOrderListEntity.getOrderState().equals("10")){
                mTv.setText("已付款");
                lin.setVisibility(View.INVISIBLE);
            }
            else if(curingOrderListEntity.getOrderState().equals("Y001")){
                mTv.setText("取件中");
                lin.setVisibility(View.INVISIBLE);
            }
            else if(curingOrderListEntity.getOrderState().equals("L006")){
                mTv.setText("已接收");
                lin.setVisibility(View.INVISIBLE);
            }else {
                mTv.setText("处理中");
                lin.setVisibility(View.INVISIBLE);
            }
            ImageView mInformationImg=holder.getView( R.id.list_curing_img);
            ImageLoaderUtils.displayImage(curingOrderListEntity.getCom_show_pic(), mInformationImg);

        }

}
