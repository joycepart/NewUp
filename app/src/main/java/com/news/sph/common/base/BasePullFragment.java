package com.news.sph.common.base;

import android.view.View;

import com.news.sph.R;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 下拉刷新的Fragment的基类
 */
public abstract class BasePullFragment extends BaseFragment {

    private PtrClassicFrameLayout ptf;

    /**
     * 是否打开下拉刷新。默认关闭
     *
     * @return
     */
    public boolean pulltoRefresh() {
        return false;
    }

    public void refreshComplete() {
        ptf.refreshComplete();
    }

    public int getRootLayoutId(){
        return R.layout.base_pull_fragment;
    }


    @Override
    public void initView(View view) {
        ptf = (PtrClassicFrameLayout) view.findViewById(R.id.base_ptr_frame);
        ptf.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                sendRequestData();

            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                if (pulltoRefresh()) {
                    return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
                }
                return pulltoRefresh();
            }
        });
    }

    //请求数据方法
    protected void sendRequestData() {

    }
}