package com.qluxstory.qingshe.information.fragment;

import android.view.View;

import com.qluxstory.ptrrecyclerview.BaseRecyclerAdapter;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseListFragment;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.widget.EmptyLayout;
import com.qluxstory.qingshe.information.InformationUiGoto;
import com.qluxstory.qingshe.information.adapter.InformationAdapter;
import com.qluxstory.qingshe.information.dto.InformationDTO;
import com.qluxstory.qingshe.information.entity.InformationEntity;
import com.qluxstory.qingshe.information.entity.InformationResult;
import com.qluxstory.qingshe.me.MeUiGoto;

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
    InformationEntity entity;


    @Override
    public void initView(View view) {
        super.initView(view);
        LogUtils.e("initView-----","initView");
        boolean bool= AppContext.get("isLogin",false);
        if(!bool){
            MeUiGoto.login(getActivity());//登录
        }else {

        }

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
        LogUtils.e("sendRequestData-----","sendRequestData");
        InformationDTO gdto=new InformationDTO();
        gdto.setUserPhone(AppContext.get("mobileNum",""));
        gdto.setPageSize(PAGE_SIZE);
        gdto.setPageIndex(mCurrentPage);
        CommonApiClient.getNews(this, gdto, new CallBack<InformationResult>() {
            @Override
            public void onSuccess(InformationResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("获取新闻成功");
                    mErrorLayout.setErrorMessage("暂无新闻",mErrorLayout.FLAG_NODATA);
                    mErrorLayout.setErrorImag(R.drawable.siaieless1,mErrorLayout.FLAG_NODATA);
                    if(null==result.getData()){
                        mErrorLayout.setErrorType(EmptyLayout.NODATA);
                    }else {
                        mInforData = result.getData();
                        requestDataSuccess(result);//获取到数据后调用该语句，进行数据缓存
                        setDataResult(mInforData);//设置数据
                    }

                }

            }
        });


    }


    public boolean autoRefreshIn(){
        return true;
    }

    @Override
    public void initData() {
        LogUtils.e("initData-----","initData");
    }

    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        entity = (InformationEntity) itemBean;
        mNewsBigTitle =entity.getNews_big_title();
        mNewsCode = entity.getNews_code();
        mUrl= AppConfig.BASE_URL+AppConfig.News_Html+mNewsCode;
        InformationUiGoto.newsDetail(getActivity(),mNewsBigTitle,mUrl);//新闻详情页，h5页面
        super.onItemClick(itemView, itemBean, position);

    }
}
