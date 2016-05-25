package com.news.sph.issue.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.news.ptrrecyclerview.BaseRecyclerAdapter;
import com.news.ptrrecyclerview.PtrRecyclerView;
import com.news.sph.AppConfig;
import com.news.sph.R;
import com.news.sph.common.base.BaseListFragment;
import com.news.sph.common.dto.BaseDTO;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.issue.adapter.IssueAdapter;
import com.news.sph.issue.entity.AdvertisingResult;
import com.news.sph.issue.entity.IndianaListEntity;
import com.news.sph.issue.entity.IndianaListResult;
import com.news.sph.issue.entity.WinningResult;
import com.news.sph.issue.IssueUiGoto;
import com.news.sph.common.utils.LogUtils;

import java.io.Serializable;
import java.util.List;


public class IssueFragment extends BaseListFragment<IndianaListEntity> {
    PtrRecyclerView mRecyclerview;
    List<IndianaListEntity> mIndianaData;

    @Override
    public void initView(View view) {
        mRecyclerview = (PtrRecyclerView) view.findViewById(R.id.base_recyclerview);
        View header = LayoutInflater.from(getActivity()).inflate(
                R.layout.view_issue_top, null);
        mRecyclerview.addHeaderView(header);

    }

    @Override
    public BaseRecyclerAdapter<IndianaListEntity> createAdapter() {
        return new IssueAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "IssueFragment";
    }

    @Override
    public List<IndianaListEntity> readList(Serializable seri) {
        return ((IndianaListResult)seri).getData();
    }

    @Override
    protected void sendRequestData() {
        BaseDTO dto=new BaseDTO();
        CommonApiClient.indianaList(getActivity(), dto, new CallBack<IndianaListResult>() {
            @Override
            public void onSuccess(IndianaListResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("夺宝列表成功");
                    indianaListResult(result);

                }
            }
        });

    }


    private void winning() {
        BaseDTO dto=new BaseDTO();
        CommonApiClient.winning(getActivity(), dto, new CallBack<WinningResult>() {
            @Override
            public void onSuccess(WinningResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("中奖轮播成功");
                    winningResult(result);

                }

            }
        });

    }

    private void winningResult(WinningResult result) {

    }

    private void advertisingResult(AdvertisingResult result) {

    }



    private void indianaList() {


    }

    private void indianaListResult(IndianaListResult result) {
        mIndianaData = result.getData();

    }

    private void advertisingPicture() {
        BaseDTO dto=new BaseDTO();
        CommonApiClient.adcertising(getActivity(), dto, new CallBack<AdvertisingResult>() {
            @Override
            public void onSuccess(AdvertisingResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("广告图片成功");
                    advertisingResult(result);

                }

            }
        });
    }

    @Override
    public void initData() {
        advertisingPicture();//夺宝顶部图片
        winning();//中奖轮播
        indianaList();//夺宝列表
    }

    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        IssueUiGoto.productDetail(getActivity());//商品详情页
        super.onItemClick(itemView, itemBean, position);
    }
}
