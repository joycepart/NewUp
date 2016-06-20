package com.qluxstory.qingshe.home.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.home.entity.CuringEntity;

/**
 * Created by lenovo on 2016/5/18.
 */
public class CuringAdapter extends BaseSimpleRecyclerAdapter<CuringEntity> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_curing_veiwpager;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, CuringEntity curingEntity, int position) {
        holder.setText(R.id.curing_titlte,curingEntity.getSell_name());
        holder.setText(R.id.curing_tv,curingEntity.getSell_description());
        holder.setText(R.id.curing_money,curingEntity.getSell_price().replace(".00",""));
        TextView tv=holder.getView( R.id.curing_coupon);
        if(curingEntity.getSell_first_discription().isEmpty()){
            tv.setVisibility(View.GONE);
        }else {
            holder.setText(R.id.curing_coupon,curingEntity.getSell_first_discription());
        }
        ImageView mImg=holder.getView( R.id.curing_img);
        ImageLoaderUtils.displayImage(curingEntity.getSell_pic(),mImg);

    }
}
