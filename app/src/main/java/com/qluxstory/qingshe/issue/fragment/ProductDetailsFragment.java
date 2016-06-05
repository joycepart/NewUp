package com.qluxstory.qingshe.issue.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseFragment;
import com.qluxstory.qingshe.issue.IssueUiGoto;

import butterknife.Bind;

/**
 * 夺宝 商品详情的fragment
 */
public class ProductDetailsFragment extends BaseFragment {
    @Bind(R.id.in_btn)
    Button mBtn;

    @Override
    protected void retry() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_issue_product_details;
    }

    @Override
    public void initView(View view) {
        mBtn.setOnClickListener(this);
        ProductFrameFragment frameFragment = new ProductFrameFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        frameFragment.setArguments(getArguments());
        fragmentTransaction.replace(R.id.issue_product_frame, frameFragment);
        fragmentTransaction.commit();

    }
    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.in_btn:
                showPop();
                break;
           
        }
    }

    private void showPop() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IssueUiGoto.POPUP_REQUEST){
            String num = data.getStringExtra("num");
            Bundle bd = new Bundle();
//            bd.putString("mBalance",num);
//            bd.putString("mPic",mUrl);
//            bd.putString("mTerm",mTerm);
//            bd.putString("mTitle",mTitle);
            IssueUiGoto.settlement(getActivity(),bd);//结算
        }
    }
}
