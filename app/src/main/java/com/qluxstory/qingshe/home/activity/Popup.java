package com.qluxstory.qingshe.home.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qluxstory.qingshe.R;

/**
 * Created by lenovo on 2016/5/31.
 */
public class Popup extends Activity implements OnClickListener {
    private View mMenuView; // PopupWindow 菜单布局
    private Context context; // 上下文参数
    private OnClickListener myOnClick; // PopupWindow 菜单 空间单击事件
    private  TextView mReduce,mPlus;
    private  EditText mNum;
    private Button mFreBtn,mFive,mTen,mTtew;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_frequency);
        Init();
    }

    private void Init() {
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
                mFive.setTextColor(context.getResources().getColor(R.color.color_ff));
                break;
            case R.id.ten:
                mNum.setText("10");
                mFive.setFocusable(false);
                mTen.setTextColor(context.getResources().getColor(R.color.color_ff));
                mTen.setFocusable(true);
                mTtew.setFocusable(false);
                break;
            case R.id.ttew:
                mNum.setText("20");
                mFive.setFocusable(false);
                mTen.setFocusable(false);
                mTtew.setFocusable(true);
                mTtew.setTextColor(context.getResources().getColor(R.color.color_ff));
                break;
            case R.id.fre_btn:
                Intent intent = new Intent();
                intent.putExtra("num",mNum.getText().toString());
                setResult(1100,intent);

                break;
        }

    }
}
