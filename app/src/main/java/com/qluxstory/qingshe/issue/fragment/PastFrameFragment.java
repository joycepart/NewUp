package com.qluxstory.qingshe.issue.fragment;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
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
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.common.utils.UIHelper;
import com.qluxstory.qingshe.common.widget.FullyLinearLayoutManager;
import com.qluxstory.qingshe.common.widget.ViewFlowLayout;
import com.qluxstory.qingshe.home.dto.TranDTO;
import com.qluxstory.qingshe.home.entity.TranEntity;
import com.qluxstory.qingshe.home.entity.TranResult;
import com.qluxstory.qingshe.issue.IssueUiGoto;
import com.qluxstory.qingshe.issue.adapter.GridProductAdapter;
import com.qluxstory.qingshe.issue.dto.AnnouncedDTO;
import com.qluxstory.qingshe.issue.dto.IssueIndianaDTO;
import com.qluxstory.qingshe.issue.dto.LanderInDTO;
import com.qluxstory.qingshe.issue.dto.PicDTO;
import com.qluxstory.qingshe.issue.entity.AnnouncedEntity;
import com.qluxstory.qingshe.issue.entity.AnnouncedResult;
import com.qluxstory.qingshe.issue.entity.LanderInEntity;
import com.qluxstory.qingshe.issue.entity.LanderInResult;
import com.qluxstory.qingshe.issue.entity.PicEntity;
import com.qluxstory.qingshe.issue.entity.PicResult;
import com.qluxstory.qingshe.me.entity.RecordIndianaEntity;
import com.qluxstory.qingshe.me.entity.RecordIndianaResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by lenovo on 2016/6/5.
 */
public class PastFrameFragment extends BasePullScrollViewFragment {
    @Bind(R.id.ptd_vf_layout)
    ViewFlowLayout mPtdVfLayout;
    @Bind(R.id.ptd_list)
    RecyclerView mPtdList;
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
    @Bind(R.id.issue_tran_img)
    ImageView mTranImg;
    @Bind(R.id.issue_calculation)
    TextView mCalculation;
    @Bind(R.id.issue_product_it)
    LinearLayout mProductIt;
    @Bind(R.id.tran_progress)
    ProgressBar mProgress;
    @Bind(R.id.tran_participate)
    TextView mTranPate;
    @Bind(R.id.tran_lin)
    LinearLayout mTranLin;
    @Bind(R.id.tran_num)
    TextView mTranNum;

    BaseSimpleRecyclerAdapter mTranAdapter;
    String mSnaCode;
    String mBatCode;
    String mBat;
    String mTvLucky;
    String mCode;
    List<LanderInEntity> mLanderInEntity;
    PopupWindow popGridWindow;
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_past_frame;
    }
    @Override
    public void initView(View view) {
        super.initView(view);
        mProgress.setProgress(100);
        Bundle b  = getArguments();
        if(b!=null){
            mSnaCode = b.getString("mSna");
            mBatCode = b.getString("mBat");
        }
        mTranLin.setOnClickListener(this);
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
                holder.setText(R.id.product_tv1,recordIndianaEntity.getRec_phone().substring(0,3)+"******"+recordIndianaEntity.getRec_phone().substring(9,recordIndianaEntity.getRec_phone().length()));
                holder.setText(R.id.product_tv2,recordIndianaEntity.getRec_participate_count());
                holder.setText(R.id.product_tv3,recordIndianaEntity.getRec_participate_date());
            }


        };
        mPtdList.setAdapter(mTranAdapter);
    }


    @Override
    public void initData() {
        reqPic();//夺宝商品图片
        reqQecord();//夺宝详情之夺宝记录
        reqTranDetails();//夺宝往期详情
        reqParticipate();//登陆者参与的次数
        announced();//揭晓信息
    }
    private void reqParticipate() {
        LanderInDTO dto=new LanderInDTO();
        String time = TimeUtils.getSignTime();
        dto.setSign(time+AppConfig.SIGN_1);
        dto.setTime(time);
        dto.setMembermob(AppContext.get("mobileNum",""));
        dto.setSna_code(mSnaCode);
        dto.setBat_code(mBatCode);
        CommonApiClient.landerIn(this, dto, new CallBack<LanderInResult>() {
            @Override
            public void onSuccess(LanderInResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("登陆者参与的次数成功");
                    if(result.getData()==null||result.getData().size()==0){
                        mTranLin.setVisibility(View.GONE);
                        mTranPate.setVisibility(View.VISIBLE);
                    }else {
                        mLanderInEntity = result.getData();
                        mTranLin.setVisibility(View.VISIBLE);
                        mTranPate.setVisibility(View.GONE);
                        mTranNum.setText(result.getPage_total());
                    }

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
        AnnouncedEntity entity = data.get(0);
        mBat = entity.getBat_code();
        mTvLucky = entity.getSna_lucky_num();
        mTvCity.setText(mTvLucky);
        mTvUser.setText(entity.getSna_lucky_people().substring(0,3)+"******"+entity.getSna_lucky_people().substring(9,entity.getSna_lucky_people().length()));
        mTvData.setText(entity.getParticipate_date());
        ImageLoaderUtils.displayAvatarImage(entity.getHeadImg(),mTranImg);
    }

    private void reqQecord() {
        IssueIndianaDTO dto=new IssueIndianaDTO();
        dto.setBat_code(mBatCode);
        dto.setPageSize(PAGE_SIZE);
        dto.setPageIndex(PAGE_INDEX);
        CommonApiClient.recordsDetails(getActivity(), dto, new CallBack<RecordIndianaResult>() {
            @Override
            public void onSuccess(RecordIndianaResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("夺宝详情之夺宝记录成功");
                    if(null==result.getData()){
                        return;
                    }else {
                        mTranAdapter.removeAll();
                        mTranAdapter.append(result.getData());
                    }

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
                    if(null==result.getData()){
                        return;
                    }else {
                        bindData(result.getData());
                    }

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
        mCode = data.get(0).getSna_code();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tran_lin:
                showPopNm(mLanderInEntity);
                break;
            case R.id.grid_tv:
                backgroundAlpha(1f);
                popGridWindow.dismiss();
            case R.id.issue_calculation:
                Bundle b = new Bundle();
                b.putString("mBat",mBat);
                b.putString("mTvLucky",mTvLucky);
                UIHelper.showFragment(getActivity(), SimplePage.CALULATION,b);//计算详情
                break;
            case R.id.issue_product_it:
                IssueUiGoto.graphicDetails(getActivity(),AppConfig.BASE_URL+AppConfig.Server_SnatchCommodity+mCode,"图文详情");
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

}
