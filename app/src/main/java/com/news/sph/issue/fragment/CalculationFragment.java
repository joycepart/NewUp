package com.news.sph.issue.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.news.ptrrecyclerview.BaseRecyclerViewHolder;
import com.news.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.news.sph.AppConfig;
import com.news.sph.R;
import com.news.sph.common.base.BasePullScrollViewFragment;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.common.utils.LogUtils;
import com.news.sph.common.widget.FullyLinearLayoutManager;
import com.news.sph.issue.dto.CalcutionDTO;
import com.news.sph.issue.entity.CalculationEntity;
import com.news.sph.issue.entity.CalculationResult;

import butterknife.Bind;

/**
 * 计算详情的fragment
 */
public class CalculationFragment extends BasePullScrollViewFragment {
    @Bind(R.id.calculation_list)
    RecyclerView mCalculationList;
    @Bind(R.id.cal_tv_lucky)
    TextView mCalTvLucky;
    BaseSimpleRecyclerAdapter mCalculationAdapter;
    String mBatCode;
    String mTvLucky;
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_calculation;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        Bundle b  = getArguments();
        mBatCode = b.getString("mBat");
        mTvLucky = b.getString("mTvLucky");
        mCalTvLucky.setText(mTvLucky);
        mCalculationList.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        mCalculationAdapter=new BaseSimpleRecyclerAdapter<CalculationEntity>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_list_calculation;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, CalculationEntity calculationEntity, int position) {
//                holder.setText(R.id.list_tv1,calculationEntity.);
//                holder.setText(R.id.list_tv2,calculationEntity.);
//                holder.setText(R.id.list_tv3,calculationEntity.);
//                holder.setText(R.id.list_tv4,calculationEntity.);

            }


        };
        mCalculationList.setAdapter(mCalculationAdapter);
    }

    @Override
    public void initData() {
        reqCaculation();//根据批次获取50个时间之和

    }

    private void reqCaculation() {
        CalcutionDTO dto=new CalcutionDTO();
        dto.setBat_code(mBatCode);
        CommonApiClient.calculation(this, dto, new CallBack<CalculationResult>() {
            @Override
            public void onSuccess(CalculationResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("计算详情成功");

                }

            }
        });
    }
}
