package com.qluxstory.qingshe.common.widget;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
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
public class PopupProductDetails extends PopupWindow implements View.OnClickListener{
    private View mMenuView; // PopupWindow 菜单布局
    private Context context; // 上下文参数
    private View.OnClickListener myOnClick; // PopupWindow 菜单 空间单击事件
    private  TextView mReduce,mPlus;
    private  EditText mNum;
    private Button mFreBtn,mFive,mTen,mTtew;

    public PopupProductDetails(FragmentActivity detailsFragment, View.OnClickListener itemsOnClick) {
        this.context = detailsFragment;
        Init();
    }


    private void Init() {
        LayoutInflater inflater = (LayoutInflater) context
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

        mReduce.setOnClickListener(this);
        mNum.setOnClickListener(this);
        mPlus.setOnClickListener(this);
        mFive.setOnClickListener(this);
        mTen.setOnClickListener(this);
        mTtew.setOnClickListener(this);
        mFreBtn.setOnClickListener(this);

        // 导入布局
        this.setContentView(mMenuView);
        // 设置动画效果
//        this.setAnimationStyle(R.anim.fade_in);
//        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
//        lp.alpha = 0.7f;
//        getWindow().setAttributes(lp);

        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置可触
        this.setFocusable(true);
//        ColorDrawable dw = new ColorDrawable(0x0000ff);
//        this.setBackgroundDrawable(dw);
        // 单击弹出窗以外处 关闭弹出窗
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = mMenuView.findViewById(R.id.ll_pop).getTop();
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
                Bundle b  = new Bundle();
                b.putString("num",mNum.getText().toString());
                IssueUiGoto.settlement(context,b);//结算
                dismiss();

//                setResult(1100,intent);

                break;
        }

    }
}
