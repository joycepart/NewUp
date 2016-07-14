package com.qluxstory.qingshe.me.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.common.widget.EmptyLayout;
import com.qluxstory.qingshe.common.widget.FullyLinearLayoutManager;
import com.qluxstory.qingshe.curing.fragment.CuringFragment;
import com.qluxstory.qingshe.home.HomeUiGoto;
import com.qluxstory.qingshe.home.entity.CuringEntity;
import com.qluxstory.qingshe.me.MeUiGoto;
import com.qluxstory.qingshe.me.activity.CuringOrderActivity;
import com.qluxstory.qingshe.me.dto.CancelDTO;
import com.qluxstory.qingshe.me.dto.CuringOrderListDTO;
import com.qluxstory.qingshe.me.entity.CuringOrderListEntity;
import com.qluxstory.qingshe.me.entity.CuringOrderListResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 养护订单详情中添加的fragment，继承BasePullScrollViewFragment，不刷新加载，现在使用
 */
public class CuringOrderNewListFragment extends BasePullScrollViewFragment {
    @Bind(R.id.curingordernew_list)
    RecyclerView mCuringordernewList;
    BaseSimpleRecyclerAdapter mCuringordernewListAdapter;
    private static final String TYPE = "type";
    private int type;
    List<CuringOrderListEntity> list = new ArrayList<>();
    RelativeLayout lin;
    TextView mTv;

    public static CuringOrderNewListFragment newInstance(int type) {
        CuringOrderNewListFragment fragment = new CuringOrderNewListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);//传递Type
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_cutingordernew;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt(TYPE, CuringOrderActivity.TAB_A);

        }
        mCuringordernewList.setLayoutManager(new FullyLinearLayoutManager(getActivity()));

    }

    @Override
    public void initData() {

        reqOrder();
        mCuringordernewListAdapter=new BaseSimpleRecyclerAdapter<CuringOrderListEntity>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_list_cuingorder;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, CuringOrderListEntity curingOrderListEntity, final int position) {
                mTv = holder.getView(R.id.list_curing_tv2);
                lin = holder.getView(R.id.curingorder_rel);
                list.add(position,curingOrderListEntity);
                holder.setText(R.id.order_num,curingOrderListEntity.getOrderNum());
                holder.setText(R.id.order_data,curingOrderListEntity.getOrderSingleTime());
                holder.setText(R.id.list_curing_tv1,curingOrderListEntity.getComName());
                holder.setText(R.id.list_curing_tv4,curingOrderListEntity.getOrderMoney());
                holder.setText(R.id.curing_vp_tv,curingOrderListEntity.getComCount());
                LogUtils.e("getOrderState---", "" + curingOrderListEntity.getOrderState());
                LogUtils.e("getIsovertime---", "" + curingOrderListEntity.getIsovertime());
                if(curingOrderListEntity.getOrderState().equals("0")){
                    if(curingOrderListEntity.getIsovertime().equals("0")){
                        mTv.setText("未付款");
                        lin.setVisibility(View.VISIBLE);
                        Button mCanle = holder.getView(R.id.curingorder_canle);
                        mCanle.setOnClickListener(new View.OnClickListener() {
                            RelativeLayout rel =lin;
                            TextView tv = mTv;
                            @Override
                            public void onClick(View v) {
                                LogUtils.e("mCanle---","取消订单");
                                LogUtils.e("position---",""+position);
                                LogUtils.e("list---",""+list.get(position).getOrderNum());
                                CancelDTO cdto=new CancelDTO();
                                String time = TimeUtils.getSignTime();
                                cdto.setMembermob(AppContext.get("mobileNum",""));
                                cdto.setSign(time+AppConfig.SIGN_1);
                                cdto.setTimestamp(time);
                                cdto.setOrderNum(list.get(position).getOrderNum());
                                CommonApiClient.cancel(getActivity(), cdto, new CallBack<CuringOrderListResult>() {
                                    @Override
                                    public void onSuccess(CuringOrderListResult result) {
                                        if(AppConfig.SUCCESS.equals(result.getStatus())){
                                            LogUtils.e("取消订单成功");
                                            rel.setVisibility(View.INVISIBLE);
                                            tv.setText("已取消");
                                        }

                                    }
                                });


                            }
                        });
                        TextView mPay = holder.getView(R.id.curingorder_pay);
                        mPay.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle b = new Bundle();
                                b.putString("mOrdNum",list.get(position).getOrderNum());
                                MeUiGoto.payment(getActivity(),b);//支付订单详情

                            }
                        });
                    }
                    else if(curingOrderListEntity.getIsovertime().equals("1")){
                        mTv.setText("已取消");
                        lin.setVisibility(View.INVISIBLE);
                    }
                }else if(curingOrderListEntity.getOrderState().equals("1")){
                    mTv.setText("已取消");
                    lin.setVisibility(View.INVISIBLE);
                }

                else if(curingOrderListEntity.getOrderState().equals("2")){
                    mTv.setText("停用");
                    lin.setVisibility(View.INVISIBLE);
                }
                else if(curingOrderListEntity.getOrderState().equals("3")){
                    mTv.setText("确认出库");
                    lin.setVisibility(View.INVISIBLE);
                }
                else if(curingOrderListEntity.getOrderState().equals("4")){
                    mTv.setText("已发货");
                    lin.setVisibility(View.INVISIBLE);
                }
                else if(curingOrderListEntity.getOrderState().equals("5")){
                    mTv.setText("已收货");
                    lin.setVisibility(View.INVISIBLE);
                }
                else if(curingOrderListEntity.getOrderState().equals("10")){
                    mTv.setText("已付款");
                    lin.setVisibility(View.INVISIBLE);
                }
                else if(curingOrderListEntity.getOrderState().equals("Y001")){
                    mTv.setText("取件中");
                    lin.setVisibility(View.INVISIBLE);
                }
                else if(curingOrderListEntity.getOrderState().equals("L006")){
                    mTv.setText("已接收");
                    lin.setVisibility(View.INVISIBLE);
                }
                else if(curingOrderListEntity.getOrderState().equals("11")){
                    mTv.setText("已评论");
                    lin.setVisibility(View.INVISIBLE);
                }else {
                    mTv.setText("处理中");
                    lin.setVisibility(View.INVISIBLE);
                }
                ImageView mInformationImg=holder.getView( R.id.list_curing_img);
                ImageLoaderUtils.displayImage(curingOrderListEntity.getCom_show_pic(), mInformationImg);
            }


        };

        mCuringordernewList.setAdapter(mCuringordernewListAdapter);
        mCuringordernewListAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View itemView, Object itemBean, int position) {
                CuringOrderListEntity entitiy = (CuringOrderListEntity) itemBean;
                Bundle b = new Bundle();
                b.putSerializable("entitiy", entitiy);
                MeUiGoto.curingOrderdetails(getActivity(), b);//养护订单详情
            }
        });

    }

    private void reqOrder() {
        CuringOrderListDTO cdto=new CuringOrderListDTO();
        String time = TimeUtils.getSignTime();
        cdto.setMembermob(AppContext.get("mobileNum", ""));
        if(type ==1){
            cdto.setAppreqtype("000");
        }else if(type ==2){
            cdto.setAppreqtype("0");
        }else if(type ==3){
            cdto.setAppreqtype("10");
        }else if(type ==4){
            cdto.setAppreqtype("5");
        }
        cdto.setSign(time + AppConfig.SIGN_1);
        cdto.setTimestamp(time);
        cdto.setPageIndex(PAGE_INDEX);
        cdto.setPageSize(PAGE_SIZE);
        CommonApiClient.CuringOrderList(this, cdto, new CallBack<CuringOrderListResult>() {
            @Override
            public void onSuccess(CuringOrderListResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.d("养护订单成功");
                    mErrorLayout.setErrorMessage("暂无订单记录", mErrorLayout.FLAG_NODATA);
                    mErrorLayout.setErrorImag(R.drawable.siaieless1, mErrorLayout.FLAG_NODATA);
                    if(null==result.getData()){
                        mErrorLayout.setErrorType(EmptyLayout.NODATA);
                    }else {
                        mCuringordernewListAdapter.removeAll();
                        mCuringordernewListAdapter.append(result.getData());
                    }

                }

            }
        });

    }
}
