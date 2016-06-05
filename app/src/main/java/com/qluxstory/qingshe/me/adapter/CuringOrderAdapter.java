package com.qluxstory.qingshe.me.adapter;

import android.widget.ImageView;

import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.me.entity.CuringOrderListEntity;

/**
 * Created by lenovo on 2016/5/17.
 */
public class CuringOrderAdapter extends BaseSimpleRecyclerAdapter<CuringOrderListEntity> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_list_cuingorder;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, CuringOrderListEntity curingOrderListEntity, int position) {

            holder.setText(R.id.order_num,curingOrderListEntity.getOrderNum());
            holder.setText(R.id.order_data,curingOrderListEntity.getOrderSingleTime());
            holder.setText(R.id.list_curing_tv1,curingOrderListEntity.getComName());
            holder.setText(R.id.list_curing_tv4,curingOrderListEntity.getOrderMoney());
            holder.setText(R.id.curing_vp_tv,curingOrderListEntity.getComCount());
            if(curingOrderListEntity.getOrderState().equals("0")){
                if(curingOrderListEntity.getIsovertime().equals("0")){
                    holder.setText(R.id.list_curing_tv2,"未付款");
                }
                if(curingOrderListEntity.getIsovertime().equals("1")){
                    holder.setText(R.id.list_curing_tv2,"已取消");
                }
            }else if(curingOrderListEntity.getOrderState().equals("2")){
                holder.setText(R.id.list_curing_tv2,"停用");
            }
            else if(curingOrderListEntity.getOrderState().equals("3")){
                holder.setText(R.id.list_curing_tv2,"确认出库");
            }
            else if(curingOrderListEntity.getOrderState().equals("4")){
                holder.setText(R.id.list_curing_tv2,"已发货");
            }
            else if(curingOrderListEntity.getOrderState().equals("5")){
                holder.setText(R.id.list_curing_tv2,"已收货");
            }
            else if(curingOrderListEntity.getOrderState().equals("10")){
                holder.setText(R.id.list_curing_tv2,"已付款");
            }
            else if(curingOrderListEntity.getOrderState().equals("Y001")){
                holder.setText(R.id.list_curing_tv2,"取件中");
            }
            else if(curingOrderListEntity.getOrderState().equals("L006")){
                holder.setText(R.id.list_curing_tv2,"已接收");
            }else {
                holder.setText(R.id.list_curing_tv2,"处理中");
            }
            ImageView mInformationImg=holder.getView( R.id.list_curing_img);
            ImageLoaderUtils.displayImage(curingOrderListEntity.getCom_show_pic(), mInformationImg);

        }

}
