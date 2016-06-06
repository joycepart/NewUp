package com.qluxstory.qingshe.issue.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseFragment;
import com.qluxstory.qingshe.common.utils.DialogUtils;
import com.qluxstory.qingshe.issue.IssueUiGoto;

import butterknife.Bind;

/**
 * 夺宝 商品详情的fragment
 */
public class ProductDetailsFragment extends BaseFragment {
    @Bind(R.id.in_btn)
    Button mBtn;
    private  TextView mReduce,mPlus;
    private EditText mNum;
    private Button mFreBtn,mFive,mTen,mTtew;
    private String mNumber;

    @Override
    protected void retry() {

    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_issue_product_details;
    }

    @Override
    public void initView(View view) {
        ProductFrameFragment frameFragment = new ProductFrameFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        frameFragment.setArguments(getArguments());
        fragmentTransaction.replace(R.id.issue_product_frame, frameFragment);
        fragmentTransaction.commit();
        mBtn.setOnClickListener(this);

    }
    @Override
    public void initData() {

    }



    private void showPop() {
        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.popup_frequency, null);
        final Dialog dialog = DialogUtils.showDialog(getActivity(), view);
        mReduce = (TextView) view
                .findViewById(R.id.reduce);
        mNum = (EditText) view
                .findViewById(R.id.num);
        mPlus = (TextView) view
                .findViewById(R.id.plus);
        mFive = (Button) view
                .findViewById(R.id.five);
        mTen = (Button) view
                .findViewById(R.id.ten);
        mTtew = (Button) view
                .findViewById(R.id.ttew);
        mFreBtn = (Button) view
                .findViewById(R.id.fre_btn);

        mReduce.setOnClickListener(this);
        mNum.setOnClickListener(this);
        mPlus.setOnClickListener(this);
        mFive.setOnClickListener(this);
        mTen.setOnClickListener(this);
        mTtew.setOnClickListener(this);
        mFreBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.in_btn:
                showPop();
                break;
            case R.id.reduce:
                int rnum = Integer.valueOf(mNum.getText().toString());
                rnum++;
                mNum.setText(Integer.toString(rnum));
                break;
            case R.id.num:
                break;
            case R.id.plus:
                int pnum = Integer.valueOf(mNum.getText().toString());
                pnum++;
                mNum.setText(Integer.toString(pnum));
                break;
            case R.id.five:
                mNum.setText("5");
                mFive.setFocusable(true);
                mTen.setFocusable(false);
                mTtew.setFocusable(false);
                mFive.setTextColor(getActivity().getResources().getColor(R.color.color_ff));
                break;
            case R.id.ten:
                mNum.setText("10");
                mFive.setFocusable(false);
                mTen.setTextColor(getActivity().getResources().getColor(R.color.color_ff));
                mTen.setFocusable(true);
                mTtew.setFocusable(false);
                break;
            case R.id.ttew:
                mNum.setText("20");
                mFive.setFocusable(false);
                mTen.setFocusable(false);
                mTtew.setFocusable(true);
                mTtew.setTextColor(getActivity().getResources().getColor(R.color.color_ff));
                break;
            case R.id.fre_btn:
                mNumber = mNum.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("mBalance",mNumber);
                IssueUiGoto.settlement(getActivity(),bundle);//结算

                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IssueUiGoto.POPUP_REQUEST){
//            String num = data.getStringExtra("num");
//            Bundle bd = new Bundle();
//            bd.putString("mBalance",num);
//            bd.putString("mPic",mUrl);
//            bd.putString("mTerm",mTerm);
//            bd.putString("mTitle",mTitle);
//
        }
    }
}
