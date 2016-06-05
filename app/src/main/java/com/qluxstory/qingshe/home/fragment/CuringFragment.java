package com.qluxstory.qingshe.home.fragment;

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
import com.qluxstory.qingshe.common.base.BasePullFragment;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.widget.FullyLinearLayoutManager;
import com.qluxstory.qingshe.home.HomeUiGoto;
import com.qluxstory.qingshe.home.activity.CuringActivity;
import com.qluxstory.qingshe.home.dto.CuringDTO;
import com.qluxstory.qingshe.home.entity.CuringEntity;
import com.qluxstory.qingshe.home.entity.CuringResult;
import com.qluxstory.qingshe.home.entity.ProductDetails;

import butterknife.Bind;

/**
 * 专业养护的fragment
 */
public class CuringFragment extends BasePullFragment {
    @Bind(R.id.curing_list)
    RecyclerView mCuringList;
    BaseSimpleRecyclerAdapter mCuringListAdapter;
    private static final String TYPE = "type";
    private int type;
    private  String mUrl;
    private  String mPrice;
    private  String mName;
    private  String mPic;
    private  String mCode;
    ProductDetails mProductDetails;

    public static CuringFragment newInstance(int type) {
        CuringFragment fragment = new CuringFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);//传递Type
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void initView(View view) {
        super.initView(view);
        mProductDetails = AppContext.getInstance().getProductDetails();
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt(TYPE, CuringActivity.TAB_A);
        }
        mCuringList.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        mCuringListAdapter=new BaseSimpleRecyclerAdapter<CuringEntity>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_curing_veiwpager;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, CuringEntity curingEntity, int position) {

                holder.setText(R.id.curing_titlte,curingEntity.getSell_name());
                holder.setText(R.id.curing_tv,curingEntity.getSell_description());
                holder.setText(R.id.curing_money,curingEntity.getSell_price());
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
        mCuringList.setAdapter(mCuringListAdapter);
        mCuringListAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View itemView, Object itemBean, int position) {
                CuringEntity curingEntity = (CuringEntity) itemBean;
                mUrl= AppConfig.Server_Html+curingEntity.getSell_only_code();
                mProductDetails.setSellName(curingEntity.getSell_name());
                mProductDetails.setSellOnlyCode(curingEntity.getSell_only_code());
                mProductDetails.setSellPrice(curingEntity.getSell_price());
                mProductDetails.setSellPic(curingEntity.getSell_pic());
                mProductDetails.setSellSort(curingEntity.getSell_sort());
                HomeUiGoto.curingProductDetails(getActivity(),mUrl);//专业养护之商品详情
            }
        });

    }

    @Override
    public void initData() {
        reqCuring();
    }

    protected void reqCuring() {
        CuringDTO dto = new CuringDTO();
        dto.setSort(type);
        dto.setPageIndex(PAGE_INDEX);
        dto.setPageSize(PAGE_SIZE);
        CommonApiClient.curing(this, dto, new CallBack<CuringResult>() {
            @Override
            public void onSuccess(CuringResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("专业养护成功");
                    mCuringListAdapter.removeAll();
                    mCuringListAdapter.append(result.getData());
                }

            }
        });

    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_curing;
    }
}
