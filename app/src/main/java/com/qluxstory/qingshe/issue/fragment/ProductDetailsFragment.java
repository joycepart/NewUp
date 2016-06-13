package com.qluxstory.qingshe.issue.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
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
    Dialog dialog;

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
        dialog = DialogUtils.showDialog(getActivity(), view);
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
                showPopWin(getActivity());
                break;
            case R.id.reduce:
                int rnum = Integer.valueOf(mNum.getText().toString());
                rnum--;
                mNum.setText(Integer.toString(rnum));
                break;
            case R.id.plus:
                int pnum = Integer.valueOf(mNum.getText().toString());
                pnum++;
                mNum.setText(Integer.toString(pnum));
                break;
            case R.id.five:
                mNum.setText("5");
                mFive.setEnabled(true);
                mTen.setEnabled(false);
                mTtew.setEnabled(false);
//                mFive.setTextColor(getActivity().getResources().getColor(R.color.color_ff));
                break;
            case R.id.ten:
                mNum.setText("10");
                mFive.setEnabled(false);
                mTen.setEnabled(true);
                mTtew.setEnabled(false);
//                mTen.setTextColor(getActivity().getResources().getColor(R.color.color_ff));
                break;
            case R.id.ttew:
                mNum.setText("20");
                mFive.setEnabled(false);
                mTen.setEnabled(false);
                mTtew.setEnabled(true);
//                mTtew.setTextColor(getActivity().getResources().getColor(R.color.color_ff));
                break;
            case R.id.fre_btn:
                mNumber = mNum.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("mBalance",mNumber);
                IssueUiGoto.settlement(getActivity(),bundle);//结算
                break;
        }

    }


    private void showPopWin(Context context) {
        LayoutInflater inflater = (LayoutInflater)
                getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.from(getActivity()).inflate(R.layout.popup_frequency, null);
        final PopupWindow popWindow = new PopupWindow(view,WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,true);
        // 需要设置一下此参数，点击外边可消失
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击窗口外边窗口消失
        popWindow.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        popWindow.setFocusable(true);
        backgroundAlpha(0.7f);
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
        mPlus.setOnClickListener(this);
        mFive.setOnClickListener(this);
        mTen.setOnClickListener(this);
        mTtew.setOnClickListener(this);
        mFreBtn.setOnClickListener(this);
        //防止虚拟软键盘被弹出菜单遮住
        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        View parent = getActivity().getWindow().getDecorView();//高度为手机实际的像素高度
        popWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(0f);
            }
        });
    }


    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }


}
