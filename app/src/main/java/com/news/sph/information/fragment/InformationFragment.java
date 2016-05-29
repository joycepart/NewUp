package com.news.sph.information.fragment;

import android.view.View;

import com.news.ptrrecyclerview.BaseRecyclerAdapter;
import com.news.sph.AppConfig;
import com.news.sph.common.base.BaseListFragment;
import com.news.sph.common.dto.BaseDTO;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.common.utils.LogUtils;
import com.news.sph.information.InformationUiGoto;
import com.news.sph.information.adapter.InformationAdapter;
import com.news.sph.information.entity.InformationEntity;
import com.news.sph.information.entity.InformationResult;

import java.io.Serializable;
import java.util.List;

/*
消息的fragment
*/

public class InformationFragment extends BaseListFragment<InformationEntity> {
    List<InformationEntity> mInforData;
    private String mNewsBigTitle;
    private String mNewsCode;
    private String mUrl;


    @Override
    public void initView(View view) {
        super.initView(view);

    }

    @Override
    public BaseRecyclerAdapter<InformationEntity> createAdapter() {
        return new InformationAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "InformationFragment";
    }

    @Override
    public List<InformationEntity> readList(Serializable seri) {
        return ((InformationResult)seri).getData();
    }

    @Override
    protected void sendRequestData() {
        BaseDTO gdto=new BaseDTO();
        gdto.setPageSize(PAGE_SIZE);
        gdto.setPageIndex(mCurrentPage);
        CommonApiClient.getNews(this, gdto, new CallBack<InformationResult>() {
            @Override
            public void onSuccess(InformationResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("获取新闻成功");
                    mInforData = result.getData();
                    requestDataSuccess(result);//获取到数据后调用该语句，进行数据缓存
                    setDataResult(mInforData);//设置数据
                }

            }
        });


    }


    public boolean autoRefreshIn(){
        return true;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        mNewsBigTitle =mInforData.get(position).getNews_big_title();
        mNewsCode = mInforData.get(position).getNews_code();
        mUrl= AppConfig.News_Html+mNewsCode;
        InformationUiGoto.newsDetail(getActivity(),mNewsBigTitle,mUrl);//新闻详情页，h5页面
        super.onItemClick(itemView, itemBean, position);

    }
}
