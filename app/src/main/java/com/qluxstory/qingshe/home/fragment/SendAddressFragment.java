package com.qluxstory.qingshe.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BasePullFragment;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.widget.FullyLinearLayoutManager;
import com.qluxstory.qingshe.home.dto.SendDTO;
import com.qluxstory.qingshe.home.entity.SendEntity;
import com.qluxstory.qingshe.home.entity.SendResult;

import java.util.List;

import butterknife.Bind;

/**
 * 寄送地址的fragment
 */
public class SendAddressFragment extends BasePullFragment {
    @Bind(R.id.send_list)
    RecyclerView mSendList;
    BaseSimpleRecyclerAdapter mSendListAdapter;
    CheckBox mSendCk;
    private String rturn;
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_send_address;
    }

    @Override
    public void initView(final View view) {
        super.initView(view);
        Bundle b  = getArguments();
        if(b!=null){
            rturn=  b.getString("rturn");
        }else{
            return;
        }
        mSendList.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        mSendListAdapter=new BaseSimpleRecyclerAdapter<SendEntity>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_send_address;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, SendEntity sendEntity, int position) {
                mSendCk = holder.getView(R.id.send_ck);
                holder.setText(R.id.send_service,sendEntity.getSto_name());
                holder.setText(R.id.send_ipone,sendEntity.getSto_phone());
                holder.setText(R.id.send_province,sendEntity.getDis_province());
                holder.setText(R.id.send_city,sendEntity.getDis_city());
                holder.setText(R.id.send_area,sendEntity.getDis_area());
                holder.setText(R.id.send_addre,sendEntity.getDis_address());

            }


        };
        mSendList.setAdapter(mSendListAdapter);
        mSendListAdapter.setOnItemClickListener(new BaseSimpleRecyclerAdapter.OnRecyclerViewItemClickListener(){

            @Override
            public void onItemClick(View itemView, Object itemBean, int position) {
                SendEntity sendEntity = (SendEntity) itemBean;
                mSendCk.setChecked(true);
                AppContext.set("Dis_province_send",sendEntity.getSto_name()+sendEntity.getSto_phone()+
                        sendEntity.getDis_province()+sendEntity.getDis_city()+sendEntity.getDis_area());
                getActivity().finish();

            }
        });
    }

    @Override
    public void initData() {
        reqSend();//配送地址

    }

    private void reqSend() {
        SendDTO bdto=new SendDTO();
        bdto.setType(rturn);
        CommonApiClient.send(this, bdto, new CallBack<SendResult>() {
            @Override
            public void onSuccess(SendResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.d("配送地址成功");
                    List<SendEntity> sData = result.getData();
                    if(sData != null && sData.size() > 0){
                        SendEntity sendEntity=sData.get(0);
                        if(sendEntity.getDis_address()==null) {
                            return;
                        }else {
                            mSendListAdapter.removeAll();
                            mSendListAdapter.append(sData);
                        }

                    }


                }
            }
        });
    }

}
