package com.news.sph.information.fragment;

import android.view.View;
import android.widget.TextView;

import com.news.ptrrecyclerview.BaseRecyclerAdapter;
import com.news.sph.AppConfig;
import com.news.sph.R;
import com.news.sph.common.base.BaseListFragment;
import com.news.sph.common.dto.BaseDTO;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.information.adapter.InformationAdapter;
import com.news.sph.information.entity.InformationEntity;
import com.news.sph.information.entity.InformationResult;
import com.news.sph.information.utils.InformationUiGoto;
import com.news.sph.utils.LogUtils;

import java.io.Serializable;
import java.util.List;

/*
消息的fragment
*/

public class InformationFragment extends BaseListFragment<InformationEntity> {
    TextView mTopTv;
    private String mNewsBigTitle;
    private String mNewsCode;
    private String mUrl;


    @Override
    public void initView(View view) {
        mTopTv = (TextView) view.findViewById(R.id.top_tv);
        mTopTv.setText("系统通知");
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
        CommonApiClient.getNews(getActivity(), gdto, new CallBack<InformationResult>() {
            @Override
            public void onSuccess(InformationResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("获取新闻成功");
                    requestDataSuccess(result);//获取到数据后调用该语句，进行数据缓存
                    setDataResult(result.getData());//设置数据
                    getResult(result.getData());
                }

            }
        });


    }

    private void getResult(List<InformationEntity> result) {
        mNewsBigTitle = result.get(0).getNews_big_title();
        mNewsCode = result.get(0).getNews_code();
    }

    public boolean autoRefreshIn(){
        return true;
    }

    @Override
    public void initData() {
        sendRequestData();
    }

    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        mUrl= AppConfig.News_Html+mNewsCode;
        InformationUiGoto.newsDetail(getActivity(),mNewsBigTitle,mUrl);//新闻详情页，h5页面
        super.onItemClick(itemView, itemBean, position);

    }
}
