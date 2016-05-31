package com.qluxstory.qingshe.issue.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BasePullScrollViewFragment;
import com.qluxstory.qingshe.common.base.SimplePage;
import com.qluxstory.qingshe.common.bean.ViewFlowBean;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.UIHelper;
import com.qluxstory.qingshe.common.widget.FullyLinearLayoutManager;
import com.qluxstory.qingshe.common.widget.ViewFlowLayout;
import com.qluxstory.qingshe.home.dto.TranDTO;
import com.qluxstory.qingshe.home.entity.TranEntity;
import com.qluxstory.qingshe.home.entity.TranResult;
import com.qluxstory.qingshe.issue.IssueUiGoto;
import com.qluxstory.qingshe.issue.dto.AnnouncedDTO;
import com.qluxstory.qingshe.issue.dto.LanderInDTO;
import com.qluxstory.qingshe.issue.dto.PicDTO;
import com.qluxstory.qingshe.issue.entity.AnnouncedEntity;
import com.qluxstory.qingshe.issue.entity.AnnouncedResult;
import com.qluxstory.qingshe.issue.entity.LanderInResult;
import com.qluxstory.qingshe.issue.entity.PicEntity;
import com.qluxstory.qingshe.issue.entity.PicResult;
import com.qluxstory.qingshe.me.dto.RecordIndianaDTO;
import com.qluxstory.qingshe.me.entity.RecordIndianaEntity;
import com.qluxstory.qingshe.me.entity.RecordIndianaResult;

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
    @Bind(R.id.tran_term)
    TextView mTranTerm;
    @Bind(R.id.tran_title)
    TextView mTranTitle;
    @Bind(R.id.tran_rad)
    TextView mTranRad;
    @Bind(R.id.issue_tv_city)
    TextView mTvCity;
    @Bind(R.id.issue_tv_user)
    TextView mTvUser;
    @Bind(R.id.issue_tv_data)
    TextView mTvData;
    @Bind(R.id.issue_tv_ple)
    TextView mTvPle;
    @Bind(R.id.ple)
    TextView mPle;
    @Bind(R.id.product_data)
    TextView mProData;
    @Bind(R.id.issue_calculation)
    TextView mCalculation;
    @Bind(R.id.issue_product_it)
    LinearLayout mProductIt;

    BaseSimpleRecyclerAdapter mTranAdapter;
    String mSnaCode;
    String mBatCode;
    String mBat;
    String mTvLucky;
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_pro_tran_details;
    }

    @Override
    public void initData() {
        Bundle b  = getArguments();
        mSnaCode = b.getString("mCode");
        mBatCode = b.getString("mBat");
        reqPic();//夺宝商品图片
        reqQecord();//夺宝详情之夺宝记录
        reqTranDetails();//夺宝往期详情
        reqDetails();//登陆者参与的次数
        announced();//揭晓信息

    }

    private void reqDetails() {
        LanderInDTO dto=new LanderInDTO();
        dto.setSign(AppConfig.SIGN_1);
        dto.setMembermob("");
        dto.setSna_code(mSnaCode);
        dto.setBat_code(mBatCode);
        CommonApiClient.landerIn(this, dto, new CallBack<LanderInResult>() {
            @Override
            public void onSuccess(LanderInResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("登陆者参与的次数成功");

                }

            }
        });
    }

    private void announced() {
        AnnouncedDTO dto=new AnnouncedDTO();
        dto.setSna_code(mSnaCode);
        dto.setBat_code(mBatCode);
        CommonApiClient.announced(this, dto, new CallBack<AnnouncedResult>() {
            @Override
            public void onSuccess(AnnouncedResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("揭晓信息成功");
                    bind(result.getData());

                }

            }
        });
    }

    private void bind(List<AnnouncedEntity> data) {
        mBat = data.get(0).getBat_code();
        mTvLucky = data.get(0).getSna_lucky_num();
        mTvCity.setText(mTvLucky);
        mTvUser.setText(data.get(0).getSna_lucky_people());
        mTvData.setText(data.get(0).getParticipate_date());
    }

    private void reqQecord() {
        RecordIndianaDTO dto=new RecordIndianaDTO();
        dto.setBat_code(mBatCode);
        dto.setPageSize(PAGE_SIZE);
        dto.setPageIndex(mCurrentPage);
        CommonApiClient.recordsDetails(getActivity(), dto, new CallBack<RecordIndianaResult>() {
            @Override
            public void onSuccess(RecordIndianaResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("夺宝详情之夺宝记录成功");

                }

            }
        });
    }

    private void reqPic() {
        PicDTO dto=new PicDTO();
        dto.setSna_code(mSnaCode);
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
                        mPtdVfLayout.updateView(list);

                    }

                }
            }
        });
    }

    private void reqTranDetails() {
        TranDTO dto=new TranDTO();
        dto.setSna_code(mSnaCode);
        dto.setBat_code(mBatCode);
        CommonApiClient.tranDetails(this, dto, new CallBack<TranResult>() {
            @Override
            public void onSuccess(TranResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("夺宝往期详情成功");
                    bindData(result.getData());
                    }
            }
        });

    }

    private void bindData(List<TranEntity> data) {
        mTranTerm.setText("第"+data.get(0).getSna_term()+"期");
        mTranTitle.setText(data.get(0).getSna_title());
        mTranRad.setText(data.get(0).getSna_remark());
        mPle.setText("总需"+data.get(0).getSna_total_count()+"人次");
        mTvPle.setText(data.get(0).getSna_sell_out());
        mProData.setText(data.get(0).getSna_begin_date());
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mInBtn.setText("前往最新期");
        mInBtn.setOnClickListener(this);
        mCalculation.setOnClickListener(this);
        mProductIt.setOnClickListener(this);
        mPtdList.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        mTranAdapter=new BaseSimpleRecyclerAdapter<RecordIndianaEntity>() {
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
        mPtdList.setAdapter(mTranAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.in_btn:
                break;
            case R.id.issue_calculation:
                Bundle b = new Bundle();
                b.putString("mBat",mBat);
                b.putString("mTvLucky",mTvLucky);
                UIHelper.showFragment(getActivity(), SimplePage.CALULATION,b);//计算详情
                break;
            case R.id.issue_product_it:
                IssueUiGoto.graphicDetails(getActivity(),AppConfig.URL_TEMPLATE,"图文详情");
                break;
        }
        super.onClick(v);
    }
}
