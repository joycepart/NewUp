package com.qluxstory.qingshe.me.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.me.dto.ModifyDTO;
import com.qluxstory.qingshe.me.entity.ModifyResult;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 修改用户信息主页面
 */
public class ModifyUserActivity extends BaseTitleActivity {
    @Bind(R.id.modify_et)
    EditText mModifyEt;
    @Bind(R.id.modify_Btn)
    Button mModifyBtn;
    @Bind(R.id.modify_img)
    ImageView mModifyImg;
    @Bind(R.id.base_titlebar_ensure)
    TextView mEnsure;
    private String mNewName;

    @Override
    protected int getContentResId() {
        return R.layout.activity_me_modifyuser;
    }

    @Override
    public void initView() {
        setTitleText("修改用户名");
        mEnsure.setVisibility(View.GONE);
        mModifyEt.setText(AppContext.get("mUserName",""));
        mModifyImg.setOnClickListener(this);
        mModifyBtn.setOnClickListener(this);
    }

    @Override
    public void initData() {
    }


    private void modify() {
        mNewName = mModifyEt.getText().toString();
        ModifyDTO mdto = new ModifyDTO();
        mdto.setMembername(mNewName);
        mdto.setMembermob(AppContext.get("mobileNum",""));
        mdto.setSign(AppConfig.SIGN_1);
        mdto.setTimestamp(TimeUtils.getSignTime());
        CommonApiClient.modifyUser(this, mdto, new CallBack<ModifyResult>() {
            @Override
            public void onSuccess(ModifyResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("修改用户名成功");
                    String mName =result.getData().get(0).getMembername();
                    AppContext.set("mUserName",mName);
                    LogUtils.e("修改用户名成功mUserName",AppContext.get("mUserName",""));
                    finish();

                }

            }
        });
    }



    @OnClick(R.id.modify_Btn)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.modify_Btn:
                modify();//修改用户名
                break;
            case R.id.modify_img:
                mModifyEt.setText("");
//                mModifyImg.setVisibility(View.GONE);
                break;
            default:
                break;
        }
        super.onClick(v);
    }
}
