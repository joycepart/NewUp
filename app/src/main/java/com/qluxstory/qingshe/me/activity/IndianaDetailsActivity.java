package com.qluxstory.qingshe.me.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.me.dto.RecordIndianaDTO;
import com.qluxstory.qingshe.me.entity.RecordIndianaResult;

import butterknife.Bind;

/**
 * 夺宝记录之夺宝详情主页面
 */
public class IndianaDetailsActivity extends BaseTitleActivity {
    @Bind(R.id.indiana_tv_look)
    TextView mTvLook;
    @Bind(R.id.in_btn)
    Button mInBtn;
    @Override
    protected int getContentResId() {
        return R.layout.activity_indiana_details;
    }

    @Override
    public void initView() {
        setTitleText("夺宝详情");
        mInBtn.setText("继续夺宝");
    }

    @Override
    public void initData() {
        reqIndianaDetails();

    }

    private void reqIndianaDetails() {
        RecordIndianaDTO dto=new RecordIndianaDTO();
        dto.setBat_code("");
        dto.setPageSize(PAGE_SIZE);
        dto.setPageIndex(mCurrentPage);
        CommonApiClient.recordsDetails(this, dto, new CallBack<RecordIndianaResult>() {
            @Override
            public void onSuccess(RecordIndianaResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("夺宝详情成功");

                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.indiana_tv_look:
                break;
            case R.id.in_btn:
                break;

        }
        super.onClick(v);
    }
}
