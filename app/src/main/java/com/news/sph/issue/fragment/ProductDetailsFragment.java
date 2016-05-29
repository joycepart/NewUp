package com.news.sph.issue.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.news.sph.issue.entity.IndianaListEntity;
import com.news.sph.issue.entity.IssDetailsEntity;
import com.news.sph.issue.entity.IssDetailsResult;
import com.news.sph.issue.entity.PicEntity;
import com.news.sph.issue.entity.PicResult;
import com.news.sph.me.dto.RecordIndianaDTO;
import com.news.sph.me.entity.RecordIndianaEntity;
import com.news.sph.me.entity.RecordIndianaResult;

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
    @Bind(R.id.iss_pro_tv_term)
    TextView mTerm;
    @Bind(R.id.iss_pro_tv_name)
    TextView mName;
    @Bind(R.id.iss_pro_tv_random)
    TextView mRandom;
    @Bind(R.id.iss_pro_people)
    TextView mPeople;
    @Bind(R.id.iss_pro_peo)
    TextView mPeo;
    @Bind(R.id.in_btn)
    Button mInBtn;
    BaseSimpleRecyclerAdapter mListAdapter;
    ArrayList<IndianaListEntity> list;
    String mSnaCode;
    String mCaTerm;
    String mCaTitle;
    String mCaNum;
    String mCaPic;
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_issue_product_details;
    }

    @Override
    public void initData() {
        Bundle b  = getArguments();
        list = (ArrayList<IndianaListEntity>) b.getSerializable("list");
        reqPic();//夺宝商品图片
        reqDetails();//夺宝详情
        reqQecord();//夺宝详情之夺宝记录

    }

    private void reqQecord() {
        RecordIndianaDTO dto=new RecordIndianaDTO();
        for (int i = 0; i < list.size(); i++) {
            IndianaListEntity indiana = list.get(i);
            dto.setBat_code(indiana.getBat_code());
        }
        dto.setPageSize(PAGE_SIZE);
        dto.setPageIndex(mCurrentPage);
        CommonApiClient.recordsDetails(getActivity(), dto, new CallBack<RecordIndianaResult>() {
            @Override
            public void onSuccess(RecordIndianaResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("夺宝详情之夺宝记录成功");
                    List<RecordIndianaEntity> rData = result.getData();
                    LogUtils.e("result.getData().1"+result.getData().get(0).getRec_participate_count());
                    LogUtils.e("result.getData().2"+result.getData().get(0).getRec_participate_date());
                    LogUtils.e("result.getData().3"+result.getData().get(0).getRec_phone());
                    if(rData != null && rData.size() != 0&&rData.get(0)!=null){
                        LogUtils.e("出现");
                        mIssuelList.setVisibility(View.VISIBLE);
                        mListAdapter.removeAll();
                        mListAdapter.append(result.getData());
                    }else {
                        LogUtils.e("小说");
                        mIssuelList.setVisibility(View.GONE);
                    }


                }

            }
        });
        
    }

    private void bindData(List<IssDetailsEntity> data) {
        mSnaCode = data.get(0).getSna_code();
        mCaTerm = "第"+data.get(0).getSna_term()+"期";
        mCaTitle = data.get(0).getSna_title();
        mTerm.setText(mCaTerm);
        mName.setText(mCaTitle);
        mRandom.setText(data.get(0).getSna_remark());
        mPeople.setText("总需"+data.get(0).getSna_total_count()+"人次");
        mPeo.setText("距离揭晓还需"+data.get(0).getSna_sell_out()+"人次");
    }


    private void reqPic() {
        PicDTO pdto=new PicDTO();
        for (int i = 0; i < list.size(); i++) {
            IndianaListEntity indiana = list.get(i);
            pdto.setSna_code(indiana.getSna_code());
        }
        CommonApiClient.pic(this, pdto, new CallBack<PicResult>() {
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
        for (int i = 0; i < list.size(); i++) {
            IndianaListEntity indiana = list.get(i);
            dto.setSna_code(indiana.getSna_code());
            dto.setBat_code(indiana.getBat_code());
        }

        CommonApiClient.issDetails(this, dto, new CallBack<IssDetailsResult>() {
            @Override
            public void onSuccess(IssDetailsResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("夺宝详情成功");
                    bindData(result.getData());

                }

            }
        });
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mIt.setOnClickListener(this);
        mPast.setOnClickListener(this);
        mInBtn.setOnClickListener(this);
        mIssuelList.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        mListAdapter=new BaseSimpleRecyclerAdapter<RecordIndianaEntity>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_issue_product;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, RecordIndianaEntity recordIndianaEntity, int position) {
                holder.setText(R.id.product_tv1,recordIndianaEntity.getRec_phone());
                holder.setText(R.id.product_tv2,"参与"+recordIndianaEntity.getRec_participate_count()+"人次");
                holder.setText(R.id.product_tv3,recordIndianaEntity.getRec_participate_date());
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
                Bundle b = new Bundle();
                b.putString("mSnaCode",mSnaCode);
                UIHelper.showFragment(getActivity(), SimplePage.TOANNOUNCE,b);//往期揭晓
                break;
            case R.id.in_btn:
//                DialogUtils.showDialog(getActivity(),view);
                Bundle bd = new Bundle();
                bd.putString("mCaTerm",mCaTerm);
                bd.putString("mCaTitle",mCaTitle);
                IssueUiGoto.settlement(getActivity(),bd);//结算
                break;

        }
        super.onClick(v);
    }
}
