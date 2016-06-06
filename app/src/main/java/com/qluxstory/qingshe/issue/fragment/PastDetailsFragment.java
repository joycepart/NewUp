package com.qluxstory.qingshe.issue.fragment;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.qluxstory.qingshe.MainActivity;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseFragment;

import butterknife.Bind;

/**
 * 往期详情的fragment
 */
public class PastDetailsFragment extends BaseFragment {
    @Bind(R.id.in_btn)
    Button mInBtn;



    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_pro_tran_details;
    }
    @Override
    public void initView(View view) {
        PastFrameFragment frameFragment = new PastFrameFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        frameFragment.setArguments(getArguments());
        fragmentTransaction.replace(R.id.issue_past_frame, frameFragment);
        fragmentTransaction.commit();
        mInBtn.setText("前往最新期");
        mInBtn.setOnClickListener(this);

    }
    @Override
    public void initData() {

    }

    @Override
    protected void retry() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.in_btn:
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("tag",2);
                getActivity().startActivity(intent);
                break;

        }
        super.onClick(v);
    }

}
