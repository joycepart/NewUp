package com.news.sph.me.activity;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.news.sph.AppConfig;
import com.news.sph.AppContext;
import com.news.sph.R;
import com.news.sph.common.base.BaseTitleActivity;
import com.news.sph.common.dto.BaseDTO;
import com.news.sph.common.entity.BaseEntity;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.me.adapter.MyCouponAdapter;
import com.news.sph.me.entity.MyCouponEntity;
import com.news.sph.me.entity.MyCouponResult;
import com.news.sph.utils.LogUtils;
import com.news.sph.utils.ToastUtils;

import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 我的优惠劵主页面
 */
public class MyCouponActivity extends BaseTitleActivity {
    @Bind(R.id.base_titlebar_ensure)
    TextView mBaseTitlebarEnsure;
    @Bind(R.id.mycoupon)
    PtrFrameLayout mMycoupon;
    @Bind(R.id.mycoupon_list)
    ListView mMycouponList;
    private String strPhoneNum;
    private String[] mNoviceTv;
    private String[] mCouponTv;
    private String[] mItemTv;
    private String[] mTimeTv;
    private String[] mDataTv;
    private int[] mCouponImg;
    @Override
    protected int getContentResId() {
        return R.layout.activity_me_mycoupon;
    }

    @Override
    public void initView() {
        setTitleText("我的优惠劵");
        mBaseTitlebarEnsure.setVisibility(View.GONE);
        strPhoneNum = AppContext.getInstance().getUser().getUserMobile();
        Coupon();
        mMycouponList.setAdapter(new MyCouponAdapter(this,mNoviceTv,mCouponTv,mItemTv,mTimeTv,mDataTv,mCouponImg));
        mMycoupon.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ToastUtils.showShort(getApplicationContext(), "开始刷新");
                mMycoupon.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showShort(getApplicationContext(), "刷新完成");
                        mMycoupon.refreshComplete();
                    }
                }, 3000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

    }

    private void Coupon() {
        BaseDTO bdto=new BaseDTO();
        bdto.setMembermob(strPhoneNum);
        bdto.setSign(AppConfig.SIGN_1);
        CommonApiClient.getCoupon(this, bdto, new CallBack<MyCouponResult>() {
            @Override
            public void onSuccess(MyCouponResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.d("优惠劵请求成功");
                    myCouponResult(result.getData());
                }
            }
        });
    }

    private void myCouponResult(List<MyCouponEntity> data) {

    }


    @Override
    public void initData() {


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
            default:
                break;
        }
        super.onClick(v);
    }
}
