package com.qluxstory.qingshe.home.activity;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.issue.IssueUiGoto;

/**
 * Created by lenovo on 2016/5/31.
 */
public class Popup extends PopupWindow implements OnClickListener{
    private View mMenuView; // PopupWindow 菜单布局
    private Context mContext; // 上下文参数
    private  TextView mReduce,mPlus;
    private  EditText mNum;
    private Button mFreBtn,mFive,mTen,mTtew;
    private String mNumber;

    public Popup(Context context, OnClickListener myOnClick) {
        this.mContext = context;
        this.myOnClick = myOnClick;
        Init();
    }

    public Popup(View view, int i, int i1, boolean b) {

    }


    private void Init() {
        // PopupWindow 导入
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popup_frequency, null);
        mReduce = (TextView) mMenuView
                .findViewById(R.id.reduce);
        mNum = (EditText) mMenuView
                .findViewById(R.id.num);
        mPlus = (TextView) mMenuView
                .findViewById(R.id.plus);
        mFive = (Button) mMenuView
                .findViewById(R.id.five);
        mTen = (Button) mMenuView
                .findViewById(R.id.ten);
        mTtew = (Button) mMenuView
                .findViewById(R.id.ttew);
        mFreBtn = (Button) mMenuView
                .findViewById(R.id.fre_btn);
        mReduce.setOnClickListener(myOnClick);
        mPlus.setOnClickListener(myOnClick);
        mFive.setOnClickListener(myOnClick);
        mTen.setOnClickListener(myOnClick);
        mTtew.setOnClickListener(myOnClick);
        mFreBtn.setOnClickListener(myOnClick);

        // 导入布局
        this.setContentView(mMenuView);
        // 设置动画效果
//        this.setAnimationStyle(R.style.AnimationFade);
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置可触
        this.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x0000000);
        this.setBackgroundDrawable(dw);
        // 单击弹出窗以外处 关闭弹出窗
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = mMenuView.findViewById(R.id.fre_lin).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });


    }
    private OnClickListener myOnClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.reduce:
                    int rnum = Integer.valueOf(mNum.getText().toString());
                    rnum++;
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
                    break;
                case R.id.ten:
                    mNum.setText("10");
                    mFive.setEnabled(false);
                    mTen.setEnabled(true);
                    mTtew.setEnabled(false);
                    break;
                case R.id.ttew:
                    mNum.setText("20");
                    mFive.setEnabled(false);
                    mTen.setEnabled(false);
                    mTtew.setEnabled(true);
                    break;
                case R.id.fre_btn:
                    mNumber = mNum.getText().toString();
                    Bundle bundle = new Bundle();
                    bundle.putString("mBalance",mNumber);
                    IssueUiGoto.settlement(mContext,bundle);//结算
                    break;
            }


        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reduce:
                int rnum = Integer.valueOf(mNum.getText().toString());
                rnum++;
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
                break;
            case R.id.ten:
                mNum.setText("10");
                mFive.setEnabled(false);
                mTen.setEnabled(true);
                mTtew.setEnabled(false);
                break;
            case R.id.ttew:
                mNum.setText("20");
                mFive.setEnabled(false);
                mTen.setEnabled(false);
                mTtew.setEnabled(true);
                break;
            case R.id.fre_btn:
                mNumber = mNum.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("mBalance",mNumber);
                IssueUiGoto.settlement(mContext,bundle);//结算
                break;
        }

    }
}
