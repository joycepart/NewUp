package com.qluxstory.qingshe.special.fragment;

import android.view.View;

import com.qluxstory.ptrrecyclerview.BaseRecyclerAdapter;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseListFragment;
import com.qluxstory.qingshe.common.dto.BaseDTO;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.widget.EmptyLayout;
import com.qluxstory.qingshe.special.UnusedUiGoto;
import com.qluxstory.qingshe.special.adapter.UnusedAdapter;
import com.qluxstory.qingshe.special.entity.HotTopEntity;
import com.qluxstory.qingshe.special.entity.HotTopResult;

import java.io.Serializable;
import java.util.List;
/*专题的fragment
        */

public class UnusedFragment extends BaseListFragment<HotTopEntity> {
    List<HotTopEntity> mTopData;
    private String mName;
    private String mUrl;
    HotTopEntity entity;

    @Override
    public void initView(View view) {
        super.initView(view);
    }

    @Override
    public BaseRecyclerAdapter<HotTopEntity> createAdapter() {
        return new UnusedAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "UnusedFragment";
    }

    @Override
    public List<HotTopEntity> readList(Serializable seri) {
        return ((HotTopResult)seri).getData();
    }

    @Override
    protected void sendRequestData() {
        BaseDTO udto=new BaseDTO();
        udto.setPageSize(PAGE_SIZE);
        udto.setPageIndex(mCurrentPage);
        CommonApiClient.hotTopics(this, udto, new CallBack<HotTopResult>() {
            @Override
            public void onSuccess(HotTopResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("热门专题成功");
                    mTopData = result.getData();
                    mErrorLayout.setErrorMessage("暂无专题",mErrorLayout.FLAG_NODATA);
                    mErrorLayout.setErrorImag(R.drawable.siaieless1,mErrorLayout.FLAG_NODATA);
                    if(null==mTopData){
                        mErrorLayout.setErrorType(EmptyLayout.NODATA);
                    }else {
                        requestDataSuccess(result);//获取到数据后调用该语句，进行数据缓存
                        setDataResult(mTopData);//设置数据
                    }
                }

            }
        });

    }



    @Override
    public void initData() {
    }

    public boolean autoRefreshIn(){
        return true;
    }
    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        entity = (HotTopEntity) itemBean;
        mUrl = AppConfig.BASE_URL+AppConfig.Server_Special+entity.getID();
        mName = entity.getSpec_name();
        UnusedUiGoto.special(getActivity(),mUrl,mName,entity.getID());//   专题/广告详情页面
        super.onItemClick(itemView, itemBean, position);
    }
}
