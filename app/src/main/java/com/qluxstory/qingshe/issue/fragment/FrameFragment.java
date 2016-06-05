package com.qluxstory.qingshe.issue.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseFragment;
import com.qluxstory.qingshe.common.base.SimplePage;
import com.qluxstory.qingshe.common.bean.ViewFlowBean;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.common.utils.UIHelper;
import com.qluxstory.qingshe.common.widget.FullyLinearLayoutManager;
import com.qluxstory.qingshe.common.widget.PopupProductDetails;
import com.qluxstory.qingshe.common.widget.ViewFlowLayout;
import com.qluxstory.qingshe.issue.IssueUiGoto;
import com.qluxstory.qingshe.issue.dto.DetailsDTO;
import com.qluxstory.qingshe.issue.dto.LanderInDTO;
import com.qluxstory.qingshe.issue.dto.PicDTO;
import com.qluxstory.qingshe.issue.entity.IndianaListEntity;
import com.qluxstory.qingshe.issue.entity.IssDetailsEntity;
import com.qluxstory.qingshe.issue.entity.IssDetailsResult;
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
 * Created by lenovo on 2016/6/5.
 */
public class FrameFragment extends BaseFragment {
    @Bind(R.id.issue_vf_layout)
    ViewFlowLayout mVfLayout;
    @Bind(R.id.issue_rcy_list)
    RecyclerView mIssuelList;
    @Bind(R.id.issue_product_it)
    LinearLayout mIt;
    @Bind(R.id.issue_product_past)
    LinearLayout mPast;
    @Bind(R.id.iss_pro_tv_term)
    TextView mTvTerm;
    @Bind(R.id.iss_pro_tv_name)
    TextView mName;
    @Bind(R.id.iss_pro_tv_random)
    TextView mRandom;
    @Bind(R.id.iss_pro_people)
    TextView mPeople;
    @Bind(R.id.iss_pro_peo)
    TextView mPeo;
    @Bind(R.id.product_data)
    TextView mProductData;
    @Bind(R.id.product_participate)
    TextView mParticipate;
    BaseSimpleRecyclerAdapter mIssuelListAdapter;
    IndianaListEntity indiana;
    private String mSnaCode;
    private String mTerm;
    private String mTitle;
    private String mPic;
    private String mBat;
    private String mSna;
    private String mUrl;
    PopupProductDetails popMenus;
    private View view;
    private PopupWindow popupWindow;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    @Override
    protected void retry() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_frame;
    }

    @Override
    public void initView(View view) {
        mIt.setOnClickListener(this);
        mPast.setOnClickListener(this);

    }

    @Override
    public void initData() {
        Bundle b  = getArguments();
        if(b!=null){
            mBat=  b.getString("bat");
            mSna=  b.getString("sna");
            mUrl = b.getString("mPic");
        }else{
            return;
        }

        reqPic();//夺宝商品图片
        reqDetails();//夺宝详情
        reqParticipate();//登陆者参与的次数
        reqQecord();//夺宝详情之夺宝记录

        mIssuelList.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        mIssuelListAdapter =new BaseSimpleRecyclerAdapter<RecordIndianaEntity>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_issue_product;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, RecordIndianaEntity recordIndianaEntity, int position) {
                holder.setText(R.id.product_tv1,recordIndianaEntity.getRec_phone());
                holder.setText(R.id.product_tv2,recordIndianaEntity.getRec_participate_count());
                holder.setText(R.id.product_tv3,recordIndianaEntity.getRec_participate_date());
            }


        };
        mIssuelList.setAdapter(mIssuelListAdapter);

    }

    private void reqParticipate() {
        LanderInDTO dto=new LanderInDTO();
        dto.setSign(AppConfig.SIGN_1);
        dto.setTime(TimeUtils.getSignTime());
        dto.setMembermob(AppContext.get("mobileNum",""));
        dto.setSna_code(mSna);
        dto.setBat_code(mBat);
        CommonApiClient.landerIn(this, dto, new CallBack<LanderInResult>() {
            @Override
            public void onSuccess(LanderInResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("登陆者参与的次数成功");
                    if(result.getData().get(0).getReceive_ran_num()!=null){
                        mParticipate.setText("您参与了"+result.getData().get(0).getReceive_ran_num()+"次夺宝");
                    }else {
                        mParticipate.setText("您未参与本次夺宝活动");
                    }

                }

            }
        });
    }

    private void reqQecord() {
        RecordIndianaDTO dto=new RecordIndianaDTO();
        dto.setBat_code(mBat);
        dto.setPageSize(PAGE_SIZE);
        dto.setPageIndex(PAGE_INDEX);
        CommonApiClient.recordsDetails(getActivity(), dto, new CallBack<RecordIndianaResult>() {
            @Override
            public void onSuccess(RecordIndianaResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("夺宝详情之夺宝记录成功");
                    List<RecordIndianaEntity> rData = result.getData();
                    if(rData != null && rData.size() > 0){
                        RecordIndianaEntity recordIndianaEntity=rData.get(0);
                        if(recordIndianaEntity.getRec_participate_count()==null) {
                            return;
                        }
                        mIssuelListAdapter.removeAll();
                        mIssuelListAdapter.append(rData);
                    }
                }

            }
        });

    }

    private void bindResult(List<IssDetailsEntity> data) {
        IssDetailsEntity detailEntity=data.get(0);
        mSnaCode = detailEntity.getSna_code();
        mTerm = detailEntity.getSna_term();
        mTitle = detailEntity.getSna_title();
        mTvTerm.setText("第"+mTerm+"期");
        mName.setText(mTitle);
        mRandom.setText(detailEntity.getSna_remark());
        mPeople.setText("总需"+detailEntity.getSna_total_count()+"人次");
//        int value1 = Integer.valueOf(detailEntity.getSna_total_count());
//        int value2 = Integer.valueOf(detailEntity.getSna_sell_out());
//        int result = value1-value2;
//        mPeo.setText("距离揭晓还需"+result+"人次");
        mProductData.setText(detailEntity.getSna_begin_date());
    }


    private void reqPic() {
        PicDTO pdto=new PicDTO();
        pdto.setSna_code(mSna);
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
        dto.setSna_code(mSna);
        dto.setBat_code(mBat);

        CommonApiClient.issDetails(this, dto, new CallBack<IssDetailsResult>() {
            @Override
            public void onSuccess(IssDetailsResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("夺宝详情成功");
                    bindResult(result.getData());

                }

            }
        });
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
//                showPopMenu();
                break;

        }
        super.onClick(v);
    }

}
