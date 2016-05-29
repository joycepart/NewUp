package com.news.sph.issue.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.news.ptrrecyclerview.BaseRecyclerAdapter;
import com.news.ptrrecyclerview.BaseRecyclerViewHolder;
import com.news.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.news.sph.AppConfig;
import com.news.sph.R;
import com.news.sph.common.base.BasePullScrollViewFragment;
import com.news.sph.common.base.SimplePage;
import com.news.sph.common.dto.BaseDTO;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.common.utils.ImageLoaderUtils;
import com.news.sph.common.utils.LogUtils;
import com.news.sph.common.utils.UIHelper;
import com.news.sph.common.widget.FullyLinearLayoutManager;
import com.news.sph.issue.IssueUiGoto;
import com.news.sph.issue.entity.AdvertisingResult;
import com.news.sph.issue.entity.IndianaListEntity;
import com.news.sph.issue.entity.IndianaListResult;
import com.news.sph.issue.entity.WinningEntity;
import com.news.sph.issue.entity.WinningResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/*
夺宝岛的fragment
 */
public class IssueFragment extends BasePullScrollViewFragment {
    @Bind(R.id.issue_list)
    RecyclerView mIssuelList;
    @Bind(R.id.issue_top_img)
    ImageView mIssueTopImg;
    @Bind(R.id.view_flipper)
    ViewFlipper mViewFlipper;
    BaseSimpleRecyclerAdapter mIssueAdapter;
    List<IndianaListEntity> mData;
    ArrayList<IndianaListEntity> list = new ArrayList<>();

    @Override
    public void initView(View view) {
        super.initView(view);
        mIssueTopImg.setOnClickListener(this);
        mIssuelList.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        mIssueAdapter=new BaseSimpleRecyclerAdapter<IndianaListEntity>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_issue_list;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, IndianaListEntity indianaListEntity, int position) {
                holder.setText(R.id.issue_tv1,"第"+indianaListEntity.getSna_term()+"期");
                holder.setText(R.id.issue_tv2,indianaListEntity.getSna_title());
//                int mSell = Integer.parseInt(indianaListEntity.getSna_sell_out());
//                int mTotal = Integer.parseInt(indianaListEntity.getSna_total_count());
//                String mStr = String.valueOf(mSell*100/mTotal);
//                holder.setText(R.id.issue_tv3,mStr+"%");
                ImageView iv=holder.getView(R.id.issue_img);
                ImageLoaderUtils.displayImage(indianaListEntity.getPic_url(),iv);
            }


        };
        mIssuelList.setAdapter(mIssueAdapter);
        mIssueAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View itemView, Object itemBean, int position) {
                Bundle b = new Bundle();
                b.putSerializable("list",list);
                UIHelper.showFragment(getActivity(), SimplePage.PRODUCT_DETAILS,b);//夺宝商品详情
            }
        });

    }


    @Override
    protected void sendRequestData() {
        reqPicture();//夺宝顶部图片
        reqWinning();//中奖轮播
        reqList();//夺宝列表
    }


    private void reqWinning() {
        BaseDTO dto=new BaseDTO();
        CommonApiClient.winning(this, dto, new CallBack<WinningResult>() {
            @Override
            public void onSuccess(WinningResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("中奖轮播成功");
                    bindData(result.getData());
                }

            }
        });

    }

    private void bindData(List<WinningEntity> data) {
        List<TextView> list = new ArrayList<TextView>();

        for(int i = 0; i < 5; i++)
        {
            TextView tv = (TextView) new TextView(getActivity());
            tv.setText(data.get(i).getSna_lucky_people());
            LogUtils.e("data------"+data.get(i).getSna_lucky_people());
            list.add(tv);
        }
        LogUtils.e("list------"+list);

       for(int i = 0; i < list.size(); i++)
        {
            mViewFlipper.addView(list.get(i));
        }
        mViewFlipper.setInAnimation(getActivity(), R.anim.push_up_in);
        mViewFlipper.setOutAnimation(getActivity(), R.anim.push_up_out);
        mViewFlipper.startFlipping();
    }


    private void reqPicture() {
        BaseDTO dto=new BaseDTO();
        CommonApiClient.adcertising(this, dto, new CallBack<AdvertisingResult>() {
            @Override
            public void onSuccess(AdvertisingResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("广告图片成功");
                    ImageLoaderUtils.displayImage(result.getData().get(0).getSpec_pic(),mIssueTopImg);

                }

            }
        });
    }

    @Override
    public void initData() {
        sendRequestData();

    }

    private void reqList() {
        BaseDTO dto=new BaseDTO();
        dto.setPageSize(PAGE_SIZE);
        dto.setPageIndex(mCurrentPage);
        CommonApiClient.indianaList(this, dto, new CallBack<IndianaListResult>() {
            @Override
            public void onSuccess(IndianaListResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("夺宝列表成功");
                    mData = result.getData();
                    mIssueAdapter.removeAll();
                    mIssueAdapter.append(mData);

                    if (mData != null && mData.size() != 0) {
                        for (int i = 0; i < mData.size(); i++) {
                            IndianaListEntity indianaList = new IndianaListEntity();
                            indianaList.setSna_code(mData.get(i).getSna_code());
                            indianaList.setBat_code(mData.get(i).getBat_code());
                            list.add(indianaList);
                        }
                    }

                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.issue_top_img:
                IssueUiGoto.special(getActivity(),AppConfig.URL_SPECIAL);
                break;

        }
        super.onClick(v);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_issue;
    }

    @Override
    public boolean pulltoRefresh() {
        return true;
    }

    public interface OnClick{
        public void onClick(View view);
    }
}
