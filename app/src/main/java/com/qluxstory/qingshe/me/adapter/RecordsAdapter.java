package com.qluxstory.qingshe.me.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.MainActivity;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.issue.activity.SettlementActivity;
import com.qluxstory.qingshe.me.entity.RecordsEntity;

/**
 * Created by lenovo on 2016/5/26.
 */
public class RecordsAdapter extends BaseSimpleRecyclerAdapter<RecordsEntity> {
    Context mContext;
    private Button btn;
    private String mTerm,mTitle,mBalance,mPic;

    public RecordsAdapter (Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_records;
    }

    @Override
    public void bindData(final BaseRecyclerViewHolder holder, RecordsEntity recordsEntity, int position) {
            mTerm = recordsEntity.getRec_term();
            mTitle = recordsEntity.getSna_title();
            mBalance = recordsEntity.getRec_pay_balance();
            mPic = recordsEntity.getPic_url();

            if(recordsEntity.getRec_state().equals("0")){
                holder.setText(R.id.records_payment,"未付款");
                holder.setText(R.id.records_btn,"去支付");

            }else if(recordsEntity.getRec_state().equals("1")){
                holder.setText(R.id.records_payment,"已付款");
                holder.setText(R.id.records_btn,"继续夺宝");
            }
            else if(recordsEntity.getRec_state().equals("2")){
                holder.setText(R.id.records_payment,"已中奖");
                holder.setText(R.id.records_btn,"去支付");
            }
            else if(recordsEntity.getRec_state().equals("3")){
                holder.setText(R.id.records_payment,"未抢中");
                holder.setText(R.id.records_btn,"去支付");
            }
            else if(recordsEntity.getRec_state().equals("4")){
                holder.setText(R.id.records_payment,"派奖中");
                holder.setText(R.id.records_btn,"去支付");
            }
            else if(recordsEntity.getRec_state().equals("5")){
                holder.setText(R.id.records_payment,"已完结");
                holder.setText(R.id.records_btn,"去支付");
            }
            else if(recordsEntity.getRec_state().equals("6")){
                holder.setText(R.id.records_payment,"已取消");
                holder.setText(R.id.records_btn,"继续夺宝");
            }
          holder.setText(R.id.records_title,mTitle);
          holder.setText(R.id.records_term,"第"+mTerm+"期");
          holder.setText(R.id.records_my,mBalance);
          ImageView mRecordsImg=holder.getView( R.id.records_img);
          ImageLoaderUtils.displayImage(mPic, mRecordsImg);

           btn = holder.getView(R.id.records_btn);
           btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn.getText().toString().equals("继续夺宝")){
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.putExtra("tag",2);
                    mContext.startActivity(intent);
                }else {
                    Intent intent = new Intent(mContext, SettlementActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("mTerm",mTerm);
                    bundle.putString("mTitle",mTitle);
                    bundle.putString("mBalance",mBalance);
                    bundle.putString("mPic",mPic);
                    intent.putExtra("bundle",bundle);
                    mContext.startActivity(intent);
                }


            }
        });



        }



}
