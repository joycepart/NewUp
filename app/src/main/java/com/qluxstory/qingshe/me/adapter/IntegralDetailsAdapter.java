package com.qluxstory.qingshe.me.adapter;

import android.graphics.Color;
import android.widget.TextView;

import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.me.entity.IntegralDetailEntity;

/**
 * Created by lenovo on 2016/6/25.
 */
public class IntegralDetailsAdapter extends BaseSimpleRecyclerAdapter<IntegralDetailEntity> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.fragment_integral_detail;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, IntegralDetailEntity integralDetailEntity, int position) {
        TextView tv = holder.getView(R.id.tran_detail_money);
        if(integralDetailEntity.getIntegralExpen().equals("1")){
            LogUtils.e("getIntegralExpen----",""+integralDetailEntity.getIntegralExpen());
            holder.setText(R.id.tran_detail_name,"签到");
            tv.setText("+"+integralDetailEntity.getIntegralNum());
            tv.setTextColor(Color.GREEN);;
        }else if(integralDetailEntity.getIntegralExpen().equals("0")){
            LogUtils.e("getIntegralExpen---",""+integralDetailEntity.getIntegralExpen());
            holder.setText(R.id.tran_detail_name,"消费");
            tv.setText("-"+integralDetailEntity.getIntegralNum());
            tv.setTextColor(Color.RED);;
        }
        holder.setText(R.id.tran_detail_data,integralDetailEntity.getIntegralData());


    }


}
