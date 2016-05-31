package com.qluxstory.qingshe.home.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

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
import com.qluxstory.qingshe.home.HomeUiGoto;
import com.qluxstory.qingshe.home.dto.SelectDTO;
import com.qluxstory.qingshe.home.entity.Consignee;
import com.qluxstory.qingshe.home.entity.SelectEntity;
import com.qluxstory.qingshe.home.entity.SelectResult;

import java.util.List;

import butterknife.Bind;

/**
 * 选择地址的fragment
 */
public class SelectFragment extends BasePullFragment {
    @Bind(R.id.select_list)
    RecyclerView mSelectList;
    @Bind(R.id.select_address)
    LinearLayout mSelectAddress;
    CheckBox mSelect;
    BaseSimpleRecyclerAdapter mSelectListAdapter;
    Consignee consignee;
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_select;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mSelectAddress.setOnClickListener(this);
        mSelectList.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        mSelectListAdapter=new BaseSimpleRecyclerAdapter<SelectEntity>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_select_address;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, SelectEntity selectEntity, int position) {
                mSelect = holder.getView(R.id.select_ck);
                holder.setText(R.id.send_province,selectEntity.getProvinCity());
                holder.setText(R.id.select_detail,selectEntity.getAddreDetail());
                holder.setText(R.id.select_service,selectEntity.getConName());
                holder.setText(R.id.select_ipone,selectEntity.getDelivMobile());
//                holder.setText(R.id.send_province,consignee.getProvincity());
//                holder.setText(R.id.select_detail,consignee.getAddredetail());
//                holder.setText(R.id.select_service,consignee.getConname());
//                holder.setText(R.id.select_ipone,consignee.getDelivmobile());

            }


        };
        mSelectList.setAdapter(mSelectListAdapter);

        mSelectListAdapter.setOnItemClickListener(new BaseSimpleRecyclerAdapter.OnRecyclerViewItemClickListener(){

            @Override
            public void onItemClick(View itemView, Object itemBean, int position) {
//                mSelect.setChecked(true);
//                getActivity().finish();

            }
        });




    }

    @Override
    public void initData() {
        reqSelect();//查询收货地址
    }

    private void reqSelect() {
        SelectDTO dto=new SelectDTO();
        dto.setConcode("");
        dto.setSign(AppConfig.SIGN_1);
        dto.setMembermob(AppContext.get("mobileNum",""));
        CommonApiClient.selcet(getActivity(), dto, new CallBack<SelectResult>() {
            @Override
            public void onSuccess(SelectResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("查询收货地址成功");
                    List<SelectEntity> sData = result.getData();
                    if(sData != null && sData.size() > 0){
                        SelectEntity selectEntity=sData.get(0);
                        if(selectEntity.getConName()==null) {
                            return;
                        }
                        mSelectListAdapter.removeAll();
                        mSelectListAdapter.append(sData);
                    }

                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.select_address:
                HomeUiGoto.addAddress(getActivity());//添加新地址
                break;

        }
    }

    public void upAddress(){
        LogUtils.e("requestCode---upAddress","upAddress.HomeUiGoto.NEWADD_REQUEST");
        mSelectListAdapter=new BaseSimpleRecyclerAdapter<SelectEntity>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_select_address;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, SelectEntity selectEntity, int position) {
                mSelect = holder.getView(R.id.select_ck);
                holder.setText(R.id.send_province,consignee.getProvincity());
                holder.setText(R.id.select_detail,consignee.getAddredetail());
                holder.setText(R.id.select_service,consignee.getConname());
                holder.setText(R.id.select_ipone,consignee.getDelivmobile());

            }


        };
        mSelectList.setAdapter(mSelectListAdapter);
        mSelectListAdapter.setOnItemClickListener(new BaseSimpleRecyclerAdapter.OnRecyclerViewItemClickListener(){

            @Override
            public void onItemClick(View itemView, Object itemBean, int position) {
                mSelect.setChecked(true);
                getActivity().finish();

            }
        });

    }
}
