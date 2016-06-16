package com.qluxstory.qingshe.me.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.me.MeUiGoto;
import com.qluxstory.qingshe.me.entity.CuringOrderListEntity;

/**
 * Created by lenovo on 2016/5/17.
 */
public class CuringOrderAdapter extends BaseSimpleRecyclerAdapter<CuringOrderListEntity> {
    RelativeLayout lin;
    TextView mTv;
    Context mContext;
    private String mOrdNum;

    public CuringOrderAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_list_cuingorder;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, final CuringOrderListEntity curingOrderListEntity, int position) {
            mTv = holder.getView(R.id.list_curing_tv2);
            lin = holder.getView(R.id.curingorder_rel);
            holder.setText(R.id.order_num,mOrdNum);
            holder.setText(R.id.order_data,curingOrderListEntity.getOrderSingleTime());
            holder.setText(R.id.list_curing_tv1,curingOrderListEntity.getComName());
            holder.setText(R.id.list_curing_tv4,curingOrderListEntity.getOrderMoney());
            holder.setText(R.id.curing_vp_tv,curingOrderListEntity.getComCount());
            if(curingOrderListEntity.getOrderState().equals("0")){
                if(curingOrderListEntity.getIsovertime().equals("0")){
                    mTv.setText("未付款");
                    lin.setVisibility(View.VISIBLE);
                    TextView mCanle = holder.getView(R.id.curingorder_canle);
                    mCanle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tv = (TextView) v;
                            lin.setVisibility(View.INVISIBLE);
                            mTv.setText("已取消");//这里应该还需要有一个请求接口
                        }
                    });
                    TextView mPay = holder.getView(R.id.curingorder_pay);
                    mPay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle b = new Bundle();
                            mOrdNum = curingOrderListEntity.getOrderNum();
                            LogUtils.e("mOrdNum----",mOrdNum);
                            b.putString("mOrdNum",mOrdNum);
                            MeUiGoto.payment(mContext,b);//支付订单详情

                        }
                    });
                }
                if(curingOrderListEntity.getIsovertime().equals("1")){
                    mTv.setText("已取消");
                    lin.setVisibility(View.INVISIBLE);
                }
            }else if(curingOrderListEntity.getOrderState().equals("2")){
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
