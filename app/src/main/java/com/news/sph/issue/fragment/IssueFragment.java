package com.news.sph.issue.fragment;

import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.news.ptrrecyclerview.BaseRecyclerAdapter;
import com.news.ptrrecyclerview.PtrRecyclerView;
import com.news.sph.AppConfig;
import com.news.sph.common.base.BaseListFragment;
import com.news.sph.common.dto.BaseDTO;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.issue.adapter.IssueAdapter;
import com.news.sph.issue.entity.AdvertisingResult;
import com.news.sph.issue.entity.IndianaListEntity;
import com.news.sph.issue.entity.IndianaListResult;
import com.news.sph.issue.entity.WinningResult;
import com.news.sph.issue.utils.IssueUiGoto;
import com.news.sph.utils.LogUtils;
import com.news.sph.utils.TDevice;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.Serializable;
import java.util.List;


public class IssueFragment extends BaseListFragment<IndianaListEntity> {

//    @Bind(R.id.ptr_frame_issue_product)
//    PtrFrameLayout ptr_frame_issue_product;
//    @Bind(R.id.issue_product_list)
//    ListView issue_product_list;

    PtrRecyclerView mRecyclerview;
    private String mPicUrl ;
    private String mUrl ;
    ImageView mIssueTopImg;
    List<IndianaListEntity> mIndianaData;

    @Override
    public void initView(View view) {
//        setTitleText("夺宝岛");

//        mRecyclerview = (PtrRecyclerView) view.findViewById(R.id.base_recyclerview);
//        View header = LayoutInflater.from(getActivity()).inflate(
//                R.layout.view_issue_top, null);

//        issue_product_list.addHeaderView(header);


//        mRecyclerview.addHeaderView(header);
//        mIssueTopImg = (ImageView) view.findViewById(R.id.issue_top_img);

    }

    @Override
    public BaseRecyclerAdapter<IndianaListEntity> createAdapter() {
        return new IssueAdapter();
    }

    @Override
    protected String getCacheKeyPrefix() {
        return "IssueFragment";
    }

    @Override
    public List<IndianaListEntity> readList(Serializable seri) {
        return ((IndianaListResult)seri).getData();
    }

    @Override
    protected void sendRequestData() {
        if(!TDevice.hasInternet(getContext())){
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                IndianaListResult result=new IndianaListResult();
                result.setData(mIndianaData);
                requestDataSuccess(result);//获取到数据后调用该语句，进行数据缓存
                setDataResult(mIndianaData);//设置数据


            }
        }, 1500);

    }

    private void getQequest() {
        advertisingPicture();//夺宝顶部图片
        winning();//中奖轮播
        indianaList();//夺宝列表
    }

    private void winning() {
        BaseDTO dto=new BaseDTO();
        CommonApiClient.winning(getActivity(), dto, new CallBack<WinningResult>() {
            @Override
            public void onSuccess(WinningResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("中奖轮播成功");
                    winningResult(result);

                }

            }
        });

    }

    private void winningResult(WinningResult result) {

    }

    private void advertisingResult(AdvertisingResult result) {
//        mPicUrl = result.getData().get(0).getSpec_pic();
//        loadAdverImg();

    }

    private void loadAdverImg() {
        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        mUrl = AppConfig.BASE_URL+mPicUrl;
        ImageLoader.getInstance().displayImage(mUrl, mIssueTopImg, options);
    }

    private void indianaList() {
        BaseDTO dto=new BaseDTO();
        CommonApiClient.indianaList(getActivity(), dto, new CallBack<IndianaListResult>() {
            @Override
            public void onSuccess(IndianaListResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("夺宝列表成功");
                    indianaListResult(result);

                }

            }
        });

    }

    private void indianaListResult(IndianaListResult result) {
        mIndianaData = result.getData();

    }

    private void advertisingPicture() {
        BaseDTO dto=new BaseDTO();
        CommonApiClient.adcertising(getActivity(), dto, new CallBack<AdvertisingResult>() {
            @Override
            public void onSuccess(AdvertisingResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("广告图片成功");
                    advertisingResult(result);

                }

            }
        });
    }

    @Override
    public void initData() {
        getQequest();
    }

    @Override
    public void onItemClick(View itemView, Object itemBean, int position) {
        IssueUiGoto.productDetail(getActivity());//商品详情页
        super.onItemClick(itemView, itemBean, position);
    }
}
