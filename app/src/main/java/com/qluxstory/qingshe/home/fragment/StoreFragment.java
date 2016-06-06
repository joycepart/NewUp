package com.qluxstory.qingshe.home.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BasePullFragment;
import com.qluxstory.qingshe.common.dto.BaseDTO;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.widget.FullyLinearLayoutManager;
import com.qluxstory.qingshe.home.entity.SendEntity;
import com.qluxstory.qingshe.home.entity.StoreEntity;
import com.qluxstory.qingshe.home.entity.StoreResult;

import java.util.List;

import butterknife.Bind;

/**
 * 选择门店的fragment
 */
public class StoreFragment extends BasePullFragment {
    @Bind(R.id.store_list)
    RecyclerView mStoreList;
    BaseSimpleRecyclerAdapter mStoreListAdapter;
    CheckBox mSendCk;
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_store;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mStoreList.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        mStoreListAdapter=new BaseSimpleRecyclerAdapter<SendEntity>() {
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
        mStoreList.setAdapter(mStoreListAdapter);
        mStoreListAdapter.setOnItemClickListener(new BaseSimpleRecyclerAdapter.OnRecyclerViewItemClickListener(){

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
        reqStore();//门店地址
    }

    private void reqStore() {
        BaseDTO bdto=new BaseDTO();
        CommonApiClient.store(this, bdto, new CallBack<StoreResult>() {
            @Override
            public void onSuccess(StoreResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.d("门店地址成功");
                    List<StoreEntity> sData = result.getData();
                    if(sData != null && sData.size() > 0){
                        StoreEntity storeEntity=sData.get(0);
                        if(storeEntity.getFieldContent()==null) {
                            return;
                        }else {
                            mStoreListAdapter.removeAll();
                            mStoreListAdapter.append(sData);
                        }

                    }


                }
            }
        });
    }
}
