package com.qluxstory.qingshe.issue.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qluxstory.ptrrecyclerview.BaseRecyclerAdapter;
import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BasePullScrollViewFragment;
import com.qluxstory.qingshe.common.base.SimplePage;
import com.qluxstory.qingshe.common.dto.BaseDTO;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.UIHelper;
import com.qluxstory.qingshe.common.widget.FullyLinearLayoutManager;
import com.qluxstory.qingshe.issue.IssueUiGoto;
import com.qluxstory.qingshe.issue.entity.AdvertisingResult;
import com.qluxstory.qingshe.issue.entity.IndianaListEntity;
import com.qluxstory.qingshe.issue.entity.IndianaListResult;
import com.qluxstory.qingshe.issue.entity.IssueProduct;
import com.qluxstory.qingshe.issue.entity.WinningEntity;
import com.qluxstory.qingshe.issue.entity.WinningResult;
import com.qluxstory.qingshe.me.MeUiGoto;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/*
夺宝岛的fragment
 */
public class IssueFragment extends BasePullScrollViewFragment {
    @Bind(R.id.issue_list)
    RecyclerView mIssuelList;
    @Bind(R.id.ll_text1)
    LinearLayout mLlText1;
    @Bind(R.id.ll_text2)
    LinearLayout mLlText2;
    @Bind(R.id.ll_text1_tv)
    TextView mTt1;
    @Bind(R.id.ll_text2_tv)
    TextView mTt2;
    @Bind(R.id.issue_top_img)
    ImageView mIssueTopImg;
    BaseSimpleRecyclerAdapter mIssueAdapter;
    IssueProduct issueProduct;
    private int flag=0;//0代表 text1 out
    private int index=2;
    Animation in,out;
    Handler mHandler=new Handler();


    @Override
    public void initView(View view) {
        super.initView(view);
        issueProduct = AppContext.getInstance().getIssueProduct();
        mIssueTopImg.setOnClickListener(this);
        in= AnimationUtils.loadAnimation(getActivity(), R.anim.in);
        out=AnimationUtils.loadAnimation(getActivity(), R.anim.out);




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
                int mSell;
                if(TextUtils.isEmpty(indianaListEntity.getSna_sell_out())){
                     mSell =Integer.parseInt("0");
                }else {
                     mSell = Integer.parseInt(indianaListEntity.getSna_sell_out());
                }
                int mTotal = Integer.parseInt(indianaListEntity.getSna_total_count());
                String mStr = String.valueOf(mSell*100/mTotal);
                LogUtils.e("mStr----",""+mStr);
                holder.setText(R.id.issue_tv3,mStr);
                ImageView iv=holder.getView(R.id.issue_img);
                ImageLoaderUtils.displayImage(indianaListEntity.getPic_url(),iv);
            }


        };
        mIssuelList.setAdapter(mIssueAdapter);
        mIssueAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View itemView, Object itemBean, int position) {
                Boolean bool= AppContext.get("isLogin",true);
                if(bool!=true){
                    MeUiGoto.login(getActivity());//登录
                }else {
                    Bundle b = new Bundle();
                    IndianaListEntity entity=(IndianaListEntity)itemBean;
                    issueProduct.setmPicUrl(entity.getPic_url());
                    String bat = entity.getBat_code();
                    String sna = entity.getSna_code();
                    String url = entity.getPic_url();
                    b.putString("bat",bat);
                    b.putString("sna",sna);
                    b.putString("mPic",url);
                    UIHelper.showFragment(getActivity(), SimplePage.PRODUCT_DETAILS,b);//夺宝商品详情
                }

            }
        });

    }


    @Override
    protected void sendRequestData() {
        reqPicture();//夺宝顶部图片
        reqWinning();//中奖轮播
        reqList();//夺宝列表
    }

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            if (flag==0) {
                mLlText1.startAnimation(out);
                mTt2.setText("恭喜"+iList.get(index++%iList.size())+"夺得"+pList.get(index++%iList.size()));
                mLlText2.startAnimation(in);
                flag=1;
            }else {
                mTt1.setText("恭喜"+iList.get(index++%iList.size())+"夺得"+pList.get(index++%iList.size()));
                mLlText1.startAnimation(in);
                mLlText2.startAnimation(out);
                flag=0;
            }
            mHandler.postDelayed(this,5000);
        }
    };

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

    ArrayList<String> iList ;
    ArrayList<String> pList ;
    private void bindData(List<WinningEntity> data) {
        iList = new ArrayList<>();
        pList = new ArrayList<>();
        for(int i = 0;i<data.size();i++){
            iList.add(data.get(i).getSna_lucky_people());
        }
        for(int i = 0;i<data.size();i++){
            pList.add(data.get(i).getSna_title());
        }
        mHandler.postDelayed(runnable,5000);
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
                    mIssueAdapter.append(result.getData());
//                    refreshComplete();

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

    @Override
    public void onStop() {
        mHandler.removeCallbacks(runnable);
        super.onStop();
    }
}
