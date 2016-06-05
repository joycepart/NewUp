package com.qluxstory.qingshe.common.widget;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.me.adapter.PopWithdrawalsAdapter;

/**
 * Created by lenovo on 2016/6/5.
 */
public class PopuoWithdrawals extends PopupWindow implements View.OnClickListener {
    private View mMenuView; // PopupWindow 菜单布局
    private Context context; // 上下文参数
    private View.OnClickListener myOnClick; // PopupWindow 菜单 空间单击事件
    private TextView mCancel,mDetermine;
    private ListView mList;
    String[] mStr = {"支付宝","银行卡"};

    public PopuoWithdrawals(FragmentActivity detailsFragment, View.OnClickListener itemsOnClick) {
        this.context = detailsFragment;
        Init();
    }


    private void Init() {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.pop_withdrawals, null);
        mCancel = (TextView) mMenuView
                .findViewById(R.id.pop_cancel);
        mDetermine = (TextView) mMenuView
                .findViewById(R.id.pop_determine);
        mList = (ListView) mMenuView
                .findViewById(R.id.pop_list);



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
        mList.setAdapter(new PopWithdrawalsAdapter(context,mStr));
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        mCancel.setOnClickListener(this);
        mDetermine.setOnClickListener(this);
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
            case R.id.pop_cancel:
                dismiss();
                break;
            case R.id.pop_determine:

                break;

        }

    }
}
