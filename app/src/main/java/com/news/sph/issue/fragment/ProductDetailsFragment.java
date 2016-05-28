package com.news.sph.issue.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.news.ptrrecyclerview.BaseRecyclerViewHolder;
import com.news.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.news.sph.AppConfig;
import com.news.sph.R;
import com.news.sph.common.base.BasePullScrollViewFragment;
import com.news.sph.common.base.SimplePage;
import com.news.sph.common.bean.ViewFlowBean;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.common.utils.LogUtils;
import com.news.sph.common.utils.UIHelper;
import com.news.sph.common.widget.FullyLinearLayoutManager;
import com.news.sph.common.widget.ViewFlowLayout;
import com.news.sph.issue.IssueUiGoto;
import com.news.sph.issue.dto.DetailsDTO;
import com.news.sph.issue.dto.PicDTO;
import com.news.sph.issue.entity.IssDetailsEntity;
import com.news.sph.issue.entity.IssDetailsResult;
import com.news.sph.issue.entity.PicEntity;
import com.news.sph.issue.entity.PicResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 夺宝 商品详情的fragment
 */
public class ProductDetailsFragment extends BasePullScrollViewFragment {
    @Bind(R.id.issue_vf_layout)
    ViewFlowLayout mVfLayout;
    @Bind(R.id.issue_rcy_list)
    RecyclerView mIssuelList;
    @Bind(R.id.issue_product_it)
    LinearLayout mIt;
    @Bind(R.id.issue_product_past)
    LinearLayout mPast;
    @Bind(R.id.in_btn)
    Button mInBtn;
    BaseSimpleRecyclerAdapter mListAdapter;
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_issue_product_details;
    }

    @Override
    public void initData() {
        reqPic();//夺宝商品图片
        reqDetails();//夺宝详情

    }

    private void reqPic() {
        PicDTO dto=new PicDTO();
        dto.setSna_code("");
        CommonApiClient.pic(this, dto, new CallBack<PicResult>() {
            @Override
            public void onSuccess(PicResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("夺宝商品图片成功");
                    List<PicEntity> mDatas = result.getData();
                    if (mDatas != null && mDatas.size() != 0) {
                        ArrayList<ViewFlowBean> list = new ArrayList<>();
                        for (int i = 0; i < mDatas.size(); i++) {
                            ViewFlowBean bean = new ViewFlowBean();
                            bean.setImgUrl(AppConfig.BASE_URL + mDatas.get(i).getSna_pic_url());
                            list.add(bean);
                        }
                        mVfLayout.updateView(list);

                    }

                }
            }
        });

    }

    private void reqDetails() {
        DetailsDTO dto=new DetailsDTO();
        dto.setSna_code("");
        dto.setBat_code("");
        CommonApiClient.issDetails(this, dto, new CallBack<IssDetailsResult>() {
            @Override
            public void onSuccess(IssDetailsResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("夺宝详情成功");

                }

            }
        });
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        Bundle bundle = new Bundle();
        ArrayList list = bundle.getParcelableArrayList("list");
        mIt.setOnClickListener(this);
        mPast.setOnClickListener(this);
        mIssuelList.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        mListAdapter=new BaseSimpleRecyclerAdapter<IssDetailsEntity>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_issue_product;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, IssDetailsEntity issDetailsEntity, int position) {
                holder.setText(R.id.product_tv1,issDetailsEntity.getSna_begin_date());
                holder.setText(R.id.product_tv2,issDetailsEntity.getSna_begin_date());
                holder.setText(R.id.product_tv3,issDetailsEntity.getSna_begin_date());
            }


        };
        mIssuelList.setAdapter(mListAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.issue_product_it:
                IssueUiGoto.graphicDetails(getActivity(),AppConfig.URL_TEMPLATE,"图文详情");
                break;
            case R.id.issue_product_past:
                UIHelper.showFragment(getActivity(), SimplePage.TOANNOUNCE);
                break;
            case R.id.in_btn:
                IssueUiGoto.settlement(getActivity());
                break;

        }
        super.onClick(v);
    }
}
