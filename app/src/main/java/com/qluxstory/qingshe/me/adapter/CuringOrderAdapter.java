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
        holder.setText(R.id.list_curing_tv2,curingOrderListEntity.getStatus());
        ImageView mInformationImg=holder.getView( R.id.list_curing_img);
        ImageLoaderUtils.displayImage(curingOrderListEntity.getCom_show_pic(), mInformationImg);



    }


}
