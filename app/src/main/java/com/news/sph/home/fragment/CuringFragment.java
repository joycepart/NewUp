package com.news.sph.home.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.news.sph.AppConfig;
import com.news.sph.R;
import com.news.sph.common.base.BaseFragment;
import com.news.sph.home.activity.CuringActivity;
import com.news.sph.home.adapter.CuringAdapter;
import com.news.sph.home.HomeUiGoto;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by lenovo on 2016/5/18.
 */
public class CuringFragment extends BaseFragment {
    private String mUrlFocus;
    private String mFocusTitle;
    @Bind(R.id.ptr_frame_curing)
    PtrFrameLayout mPtrFrameCuring;
    @Bind(R.id.curing_list)
    ListView mCuringList;
    private static final String TYPE = "type";
    private int type;
    private int[] mImgpic = new int[] { R.drawable.choice_pic_01, R.drawable.choice_pic_02,
            R.drawable.choice_pic_01, R.drawable.choice_pic_02, R.drawable.choice_pic_01,
            R.drawable.choice_pic_02, R.drawable.choice_pic_01, R.drawable.choice_pic_02 };
    private String[] mStr1= new String[] {
            "第一期","第二期","第三期","第四期","第五期","第六期","第七期","第八期",
    };

    private String[] mStr2= new String[] {
            "1%","2%","3%","4%","5%","6%","7%","8%",
    };

    private ImageView img;

    public static CuringFragment newInstance(int type) {
        CuringFragment fragment = new CuringFragment();
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
            type = bundle.getInt(TYPE, CuringActivity.TAB_A);
        }
    }



    @Override
    protected int getLayoutResId() {
        return R.layout.item_fragment_curing;
    }

    @Override
    public void initView(View view) {
        mCuringList.setAdapter(new CuringAdapter(getActivity(),mImgpic,mStr1,mStr2));
        mCuringList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mUrlFocus = AppConfig.URL_TEMPLATE;
                mFocusTitle= "商品详情 - 倾奢";
                HomeUiGoto.productDetails(getActivity(),mUrlFocus,mFocusTitle);
            }
        });
    }

    @Override
    public void initData() {

    }
}
