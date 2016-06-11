package com.qluxstory.qingshe.me.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.MainActivity;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.issue.IssueUiGoto;
import com.qluxstory.qingshe.issue.entity.IssueProduct;
import com.qluxstory.qingshe.me.entity.RecordsEntity;

/**
 * Created by lenovo on 2016/5/26.
 */
public class RecordsAdapter extends BaseSimpleRecyclerAdapter<RecordsEntity> {
    Context mContext;
    private Button btn;
    private String mTerm,mTitle,mBalance,mPic,mRec,mBat,mSna;
    IssueProduct issueProduct;

    public RecordsAdapter (Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_records;
    }

    @Override
    public void bindData(final BaseRecyclerViewHolder holder, final RecordsEntity recordsEntity, int position) {
            issueProduct = AppContext.getInstance().getIssueProduct();
            mTerm = recordsEntity.getRec_term();
            mTitle = recordsEntity.getSna_title();
            mBalance = recordsEntity.getRec_pay_balance();
            mPic = recordsEntity.getPic_url();
            mRec = recordsEntity.getRec_code();
            mBat = recordsEntity.getBat_code();
            mSna = recordsEntity.getSna_code();

            btn = holder.getView(R.id.records_btn);
             LogUtils.e("recordsEntity.getRec_state()---",recordsEntity.getRec_state());

            if(recordsEntity.getRec_state().equals("0")){
                holder.setText(R.id.records_payment,"未付款");
                btn.setText("去支付");

            }else if(recordsEntity.getRec_state().equals("1")){
                holder.setText(R.id.records_payment,"已付款");
                btn.setText("继续夺宝");
            }
            else if(recordsEntity.getRec_state().equals("2")){
                holder.setText(R.id.records_payment,"已中奖");
                btn.setText("继续夺宝");
            }
            else if(recordsEntity.getRec_state().equals("3")){
                holder.setText(R.id.records_payment,"未抢中");
                btn.setText("继续夺宝");
            }
            else if(recordsEntity.getRec_state().equals("4")){
                holder.setText(R.id.records_payment,"派奖中");
                btn.setText("继续夺宝");
            }
            else if(recordsEntity.getRec_state().equals("5")){
                holder.setText(R.id.records_payment,"已完结");
                btn.setText("继续夺宝");
            }
            else if(recordsEntity.getRec_state().equals("6")){
                holder.setText(R.id.records_payment,"已取消");
                btn.setText("继续夺宝");
            }
          holder.setText(R.id.records_title,mTitle);
          holder.setText(R.id.records_term,"第"+mTerm+"期");
          holder.setText(R.id.records_my,mBalance);
          ImageView mRecordsImg=holder.getView( R.id.records_img);
          ImageLoaderUtils.displayImage(mPic, mRecordsImg);
          btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button mBtn = (Button) v;
                LogUtils.e("btn--",btn.getText().toString());
                if(mBtn.getText().toString().equals("继续夺宝")){
                    LogUtils.e("btn.getText().toString()---if",btn.getText().toString());
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.putExtra("tag",2);
                    mContext.startActivity(intent);
                }else if(mBtn.getText().toString().equals("去支付")){
                    issueProduct.setmRecCode(mRec);
                    issueProduct.setmBatCode(mBat);
                    issueProduct.setmSnaCode(mSna);
                    issueProduct.setmSnaTitle(mTitle);
                    issueProduct.setmSnaTerm(mTerm);
                    issueProduct.setmPicUrl(mPic);
                    Bundle bundle = new Bundle();
                    bundle.putString("mBalance",mBalance);
                    IssueUiGoto.settlement(mContext,bundle);//结算
                }


            }
        });



        }



}
