package com.qluxstory.qingshe.curing.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qluxstory.ptrrecyclerview.BaseRecyclerAdapter;
import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BasePullScrollViewFragment;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.widget.FullyLinearLayoutManager;
import com.qluxstory.qingshe.home.HomeUiGoto;
import com.qluxstory.qingshe.home.dto.CuringDTO;
import com.qluxstory.qingshe.home.entity.CuringEntity;
import com.qluxstory.qingshe.home.entity.CuringResult;
import com.qluxstory.qingshe.home.entity.ProductDetails;

import butterknife.Bind;

/**
 * Created by lenovo on 2016/6/26.
 */
public class MajorNewFragment extends BasePullScrollViewFragment {
    @Bind(R.id.majornew_list)
    RecyclerView mMajornewList;
    @Bind(R.id.majornew_img)
    ImageView mImg;
    BaseSimpleRecyclerAdapter mMajornewListAdapter;
    private static final String TYPE = "type";
    private int type;
    ProductDetails mProductDetails;
    private  String mUrl;

    public static MajorNewFragment newInstance(int type) {
        MajorNewFragment fragment = new MajorNewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);//传递Type
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_majornew;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mProductDetails = AppContext.getInstance().getProductDetails();
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt(TYPE, CuringFragment.TAB_A);
            LogUtils.e("type---",""+type);

        }
        if(type ==1){
            mImg.setBackgroundResource(R.drawable.qingxi);
        }else if(type ==2){
            mImg.setBackgroundResource(R.drawable.yanghu);
        }else if(type ==3){
            mImg.setBackgroundResource(R.drawable.zhuanyeweixiu);
        }
        mMajornewList.setLayoutManager(new FullyLinearLayoutManager(getActivity()));

    }

    @Override
    public void initData() {
        reqMajor();
        mMajornewListAdapter=new BaseSimpleRecyclerAdapter<CuringEntity>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_curing_veiwpager;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, CuringEntity curingEntity, int position) {
                holder.setText(R.id.curing_titlte,curingEntity.getSell_name());
                holder.setText(R.id.curing_tv,curingEntity.getSell_description());
                holder.setText(R.id.curing_money,curingEntity.getSell_price().replace(".00",""));
                TextView tv=holder.getView( R.id.curing_coupon);
                if(curingEntity.getSell_first_discription().isEmpty()){
                    tv.setVisibility(View.GONE);
                }else {
                    holder.setText(R.id.curing_coupon,curingEntity.getSell_first_discription());
                }
                ImageView mImg=holder.getView( R.id.curing_img);
                ImageLoaderUtils.displayImage(curingEntity.getSell_pic(),mImg);
            }


        };
        mMajornewList.setAdapter(mMajornewListAdapter);
        mMajornewListAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View itemView, Object itemBean, int position) {
                CuringEntity curingEntity = (CuringEntity) itemBean;
                mUrl= AppConfig.BASE_URL+AppConfig.Server_Html+curingEntity.getSell_only_code();
                mProductDetails.setSellName(curingEntity.getSell_name());
                mProductDetails.setSellOnlyCode(curingEntity.getSell_only_code());
                mProductDetails.setSellPrice(curingEntity.getSell_price());
                mProductDetails.setSellPic(curingEntity.getSell_pic());
                mProductDetails.setSellSort(curingEntity.getSell_sort());
                HomeUiGoto.curingProductDetails(getActivity(),mUrl,curingEntity.getSell_pic(),curingEntity.getSell_only_code(),curingEntity.getSell_name(),curingEntity.getSell_price());//专业养护之商品详情

            }
        });
    }

    private void reqMajor() {
        CuringDTO dto = new CuringDTO();
        dto.setSort(type);
        dto.setPageIndex(PAGE_INDEX);
        dto.setPageSize(PAGE_SIZE);
        CommonApiClient.curing(this, dto, new CallBack<CuringResult>() {
            @Override
            public void onSuccess(CuringResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("专业养护成功");
                    LogUtils.e("result----",""+result);
                    mErrorLayout.setErrorMessage("暂无订单记录",mErrorLayout.FLAG_NODATA);
                    mErrorLayout.setErrorImag(R.drawable.siaieless1,mErrorLayout.FLAG_NODATA);
                    if(null==result.getData()){
                        return;
                    }else {
                        mMajornewListAdapter.removeAll();
                        mMajornewListAdapter.append(result.getData());
                    }
                }

            }
        });
    }
}
