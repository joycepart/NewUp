package com.news.sph.issue.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.news.ptrrecyclerview.BaseRecyclerViewHolder;
import com.news.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.news.sph.AppConfig;
import com.news.sph.R;
import com.news.sph.common.base.BasePullScrollViewFragment;
import com.news.sph.common.bean.ViewFlowBean;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.common.utils.LogUtils;
import com.news.sph.common.widget.FullyLinearLayoutManager;
import com.news.sph.common.widget.ViewFlowLayout;
import com.news.sph.home.dto.TranDTO;
import com.news.sph.home.entity.TranResult;
import com.news.sph.issue.IssueUiGoto;
import com.news.sph.issue.dto.PicDTO;
import com.news.sph.issue.entity.IssDetailsEntity;
import com.news.sph.issue.entity.PicEntity;
import com.news.sph.issue.entity.PicResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 往期详情的fragment
 */
public class PastDetailsFragment extends BasePullScrollViewFragment {
    @Bind(R.id.ptd_vf_layout)
    ViewFlowLayout mPtdVfLayout;
    @Bind(R.id.ptd_list)
    RecyclerView mPtdList;
    @Bind(R.id.in_btn)
    Button mInBtn;
    @Bind(R.id.issue_product_it)
    LinearLayout mProductIt;
    BaseSimpleRecyclerAdapter mTranAdapter;
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_pro_tran_details;
    }

    @Override
    public void initData() {
        reqPic();//夺宝商品图片
        reqTranDetails();//夺宝往期详情

    }

    private void reqPic() {
        PicDTO dto=new PicDTO();
        dto.setSna_code("");
        CommonApiClient.pic(getActivity(), dto, new CallBack<PicResult>() {
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
                        mPtdVfLayout.updateView(list);

                    }

                }
            }
        });
    }

    private void reqTranDetails() {
        TranDTO dto=new TranDTO();
        dto.setSna_code("");
        dto.setBat_code("");
        CommonApiClient.tranDetails(getActivity(), dto, new CallBack<TranResult>() {
            @Override
            public void onSuccess(TranResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("夺宝往期详情成功");

                    }
            }
        });

    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mInBtn.setText("前往最新期");
        mInBtn.setOnClickListener(this);
        mPtdList.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        mTranAdapter=new BaseSimpleRecyclerAdapter<IssDetailsEntity>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_issue_product_list;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, IssDetailsEntity issDetailsEntity, int position) {
                holder.setText(R.id.tv_num,issDetailsEntity.getSna_begin_date());
                holder.setText(R.id.tv_user,issDetailsEntity.getSna_sell_out());
                holder.setText(R.id.product_data,issDetailsEntity.getSna_begin_date());
            }


        };
        mPtdList.setAdapter(mTranAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.in_btn:
                break;
            case R.id.issue_product_it:
                IssueUiGoto.graphicDetails(getActivity(),AppConfig.URL_TEMPLATE,"图文详情");
                break;
        }
        super.onClick(v);
    }
}
