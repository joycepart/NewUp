package com.qluxstory.qingshe.issue.fragment;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BasePullScrollViewFragment;
import com.qluxstory.qingshe.common.base.SimplePage;
import com.qluxstory.qingshe.common.bean.ViewFlowBean;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.common.utils.UIHelper;
import com.qluxstory.qingshe.common.widget.FullyLinearLayoutManager;
import com.qluxstory.qingshe.common.widget.ViewFlowLayout;
import com.qluxstory.qingshe.issue.IssueUiGoto;
import com.qluxstory.qingshe.issue.adapter.GridProductAdapter;
import com.qluxstory.qingshe.issue.dto.DetailsDTO;
import com.qluxstory.qingshe.issue.dto.LanderInDTO;
import com.qluxstory.qingshe.issue.dto.PicDTO;
import com.qluxstory.qingshe.issue.entity.IndianaListEntity;
import com.qluxstory.qingshe.issue.entity.IssDetailsEntity;
import com.qluxstory.qingshe.issue.entity.IssDetailsResult;
import com.qluxstory.qingshe.issue.entity.IssueProduct;
import com.qluxstory.qingshe.issue.entity.LanderInEntity;
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
public class ProductFrameFragment extends BasePullScrollViewFragment {
    @Bind(R.id.issue_vf_layout)
    ViewFlowLayout mVfLayout;
    @Bind(R.id.issue_rcy_list)
    RecyclerView mIssuelList;
    @Bind(R.id.issue_product_it)
    LinearLayout mIt;
    @Bind(R.id.issue_product_lin)
    LinearLayout mProductLin;

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
    @Bind(R.id.product_participate_num)
    TextView mParticipateNum;
    @Bind(R.id.iss_pro_peo)
    TextView mPeo;
    @Bind(R.id.product_data)
    TextView mProductData;
    @Bind(R.id.product_participate)
    TextView mParticipate;
    @Bind(R.id.product_progress)
    ProgressBar mGrogress;
    BaseSimpleRecyclerAdapter mIssuelListAdapter;
    IndianaListEntity indiana;
    private String mSnaCode;
    private String mTerm;
    private String mTitle;
    private String mPic;
    private String mBat;
    private String mSna;
    private String mUrl;
    IssueProduct issueProduct;
    private String total;
    List<LanderInEntity> mLanderInEntity;
    PopupWindow popGridWindow;
    int mSell;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_product_frame;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        issueProduct = AppContext.getInstance().getIssueProduct();
        Bundle b  = getArguments();
        if(b!=null){
                mBat=  b.getString("bat");
                mSna=  b.getString("sna");
//                mUrl = b.getString("mPic");
        }else{
                return;
            }

        mIt.setOnClickListener(this);
        mPast.setOnClickListener(this);
        mProductLin.setOnClickListener(this);

    }

    @Override
    public void initData() {

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
        dto.setUserPhone(AppContext.get("mobileNum",""));
        dto.setSna_code(mSna);
        dto.setBat_code(mBat);
        CommonApiClient.landerIn(this, dto, new CallBack<LanderInResult>() {
            @Override
            public void onSuccess(LanderInResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("登陆者参与的次数成功");
                    if(result.getData()==null||result.getData().size()==0){
                        mProductLin.setVisibility(View.GONE);
                        mParticipate.setVisibility(View.VISIBLE);
                    }else {
                        mLanderInEntity = result.getData();
                        total = result.getPage_total();
                        mProductLin.setVisibility(View.VISIBLE);
                        mParticipate.setVisibility(View.GONE);
                        mParticipateNum.setText(total);
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
        issueProduct.setmTotalCount(detailEntity.getSna_total_count());
        issueProduct.setmSnaOut(detailEntity.getSna_sell_out());
        LogUtils.e("setmSnaOut----",""+detailEntity.getSna_sell_out());
        issueProduct.setmSnaTerm(detailEntity.getSna_term());
        issueProduct.setmSnaCode(detailEntity.getSna_code());
        issueProduct.setmBatCode(detailEntity.getBat_code());
        issueProduct.setmSnaTitle(detailEntity.getSna_title());
        mSnaCode = detailEntity.getSna_code();
        mTerm = detailEntity.getSna_term();
        mTitle = detailEntity.getSna_title();
        mTvTerm.setText("第"+mTerm+"期");
        mName.setText(mTitle);
        mRandom.setText(detailEntity.getSna_remark());
        mPeople.setText("总需"+detailEntity.getSna_total_count()+"人次");
        int  mCount =Integer.parseInt(detailEntity.getSna_total_count());
        LogUtils.e("setProgress----",""+mCount);
        if(TextUtils.isEmpty(detailEntity.getSna_sell_out())){
            mSell = 0;
        }else {
            mSell = Integer.parseInt(detailEntity.getSna_sell_out());
        }

        LogUtils.e("setProgress----mSell",""+mSell);
        mGrogress.setProgress(mSell*100/mCount);
        LogUtils.e("setProgress----mGrogress",""+mSell*100/mCount);
        int value1 = mCount;
        int value2 = mSell;
        int result = value1-value2;
        mPeo.setText("距离揭晓还需"+result+"人次");
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
            case R.id.issue_product_lin:
                LogUtils.e("issue_product_lin---","issue_product_lin");
                showPopNm(mLanderInEntity);
                break;
            case R.id.grid_tv:
                backgroundAlpha(1f);
                popGridWindow.dismiss();
                break;

        }
        super.onClick(v);
    }

    private void showPopNm(List<LanderInEntity> mLanderInEntity) {
        LogUtils.e("showPopNm---","showPopNm");
        LayoutInflater inflater = (LayoutInflater)
                getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.from(getActivity()).inflate(R.layout.activity_grid_num, null);
        popGridWindow = new PopupWindow(view, WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,true);

        backgroundAlpha(0.7f);
        // 需要设置一下此参数，点击外边可消失
        popGridWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击窗口外边窗口消失
        popGridWindow.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        popGridWindow.setFocusable(false);
        TextView girdTv= (TextView) view.findViewById(R.id.grid_tv);
        girdTv.setOnClickListener(this);
        GridView girdView= (GridView) view.findViewById(R.id.grid_num);
        girdView.setAdapter(new GridProductAdapter(getActivity(), mLanderInEntity));
        View parent = getActivity().getWindow().getDecorView();//高度为手机实际的像素高度
        popGridWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
        //添加pop窗口关闭事件
        popGridWindow.setOnDismissListener(new poponDismissListener());
    }

    public class poponDismissListener implements PopupWindow.OnDismissListener{

        @Override
        public void onDismiss() {
            LogUtils.e("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
            popGridWindow.dismiss();
        }

    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }


    @Override
    public boolean pulltoRefresh() {
        return true;
    }


}
