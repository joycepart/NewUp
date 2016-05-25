package com.news.sph.home.fragment;

import android.view.View;

import com.news.sph.AppConfig;
import com.news.sph.R;
import com.news.sph.common.base.BasePullFragment;
import com.news.sph.common.bean.ViewFlowBean;
import com.news.sph.common.dto.BaseDTO;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.common.utils.LogUtils;
import com.news.sph.common.widget.ViewFlowLayout;
import com.news.sph.home.entity.HomeAdcerEntity;
import com.news.sph.home.entity.HomeAdcerResult;
import com.news.sph.home.entity.HomeRecomendResult;
import com.news.sph.home.entity.HomeRecommendEntity;
import com.news.sph.home.entity.HomeSpecialEntity;
import com.news.sph.home.entity.HomeSpecialResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 首页的fragment
 */
public class HomeFragment extends BasePullFragment {
    List<HomeRecommendEntity> mRecomendData;
    List<HomeSpecialEntity> mHomeSpecial;
    @Bind(R.id.vf_layout)
    ViewFlowLayout mVfLayout;

    //view_home_view
    @Override
    public void initView(View view) {

        mVfLayout.setOnItemClickListener(new ViewFlowLayout.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {


            }
        });

    }

    private void reqAdver() {
        BaseDTO dto = new BaseDTO();
        CommonApiClient.homeAdcer(getActivity(), dto, new CallBack<HomeAdcerResult>() {
            @Override
            public void onSuccess(HomeAdcerResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("首页广告成功");
                    List<HomeAdcerEntity> mAdDatas = result.getData();
                    if (mAdDatas != null && mAdDatas.size() != 0) {
                        ArrayList<ViewFlowBean> list = new ArrayList<>();
                        for (int i = 0; i < mAdDatas.size(); i++) {
                            ViewFlowBean bean = new ViewFlowBean();
                            bean.setImgUrl(AppConfig.BASE_URL + mAdDatas.get(i).getmSpecPic());
                            list.add(bean);
                        }
                        mVfLayout.updateView(list);
                    }
                }
            }
        });
    }


    private void reqRecommend() {
        BaseDTO dto = new BaseDTO();
        CommonApiClient.homeRecommend(getActivity(), dto, new CallBack<HomeRecomendResult>() {
            @Override
            public void onSuccess(HomeRecomendResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("首页推荐成功");

                }

            }
        });
    }


    private void reqSpecial() {
        BaseDTO dto = new BaseDTO();
        CommonApiClient.homeSpecial(getActivity(), dto, new CallBack<HomeSpecialResult>() {
            @Override
            public void onSuccess(HomeSpecialResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("首页专题成功");

                }

            }
        });
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
    }


    @Override
    public void initData() {
        sendRequestData();
    }

    @Override
    protected void sendRequestData() {
        reqAdver();//首页广告
        reqSpecial();//首页专题列表
        reqRecommend();//首页推荐
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public boolean pulltoRefresh() {
        return true;
    }
}
