package com.news.sph.me.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.news.sph.AppConfig;
import com.news.sph.AppContext;
import com.news.sph.R;
import com.news.sph.common.base.BaseFragment;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.me.activity.CuringOrderActivity;
import com.news.sph.me.adapter.CuringOrderAdapter;
import com.news.sph.me.dto.CuringOrderListDTO;
import com.news.sph.me.entity.CuringOrderListResult;
import com.news.sph.common.utils.LogUtils;

import butterknife.Bind;

/**
 * Created by lenovo on 2016/5/17.
 */
public class CuringOrderListFragment extends BaseFragment {
    @Bind(R.id.curingorder_list)
    ListView mCuringorderList;
    private static final String TYPE = "type";
    private int type;
    private String strPhoneNum;
    private String appreqtype;
    private int[] mImgpic = new int[] { R.drawable.choice_pic_01, R.drawable.choice_pic_02,
            R.drawable.choice_pic_01, R.drawable.choice_pic_02, R.drawable.choice_pic_01,
            R.drawable.choice_pic_02, R.drawable.choice_pic_01, R.drawable.choice_pic_02 };
    private String[] mStr1= new String[] {
            "第一期","第二期","第三期","第四期","第五期","第六期","第七期","第八期",
    };

    private String[] mStr2= new String[] {
            "1%","2%","3%","4%","5%","6%","7%","8%",
    };

    public static CuringOrderListFragment newInstance(int type) {
        CuringOrderListFragment fragment = new CuringOrderListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);//传递Type
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt(TYPE, CuringOrderActivity.TAB_D);

        }
//        mCuringorderList.setEmptyView();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.item_fragment_curingorder;
    }

    @Override
    public void retry() {

    }

    @Override
    public void initView(View view) {
        strPhoneNum = AppContext.getInstance().getUser().getmUserMobile();
        appreqtype = "";
    }

    private void curingOrder() {
        CuringOrderListDTO cdto=new CuringOrderListDTO();
        cdto.setMembermob(strPhoneNum);
        cdto.setAppreqtype(appreqtype);
        cdto.setSign(AppConfig.SIGN_1);
        CommonApiClient.CuringOrderList(this, cdto, new CallBack<CuringOrderListResult>() {
            @Override
            public void onSuccess(CuringOrderListResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.d("养护订单成功");
                }

            }
        });
    }


    @Override
    public void initData() {
        curingOrder();
        mCuringorderList.setAdapter(new CuringOrderAdapter(getActivity(),mImgpic,mStr1,mStr2));

    }
}
