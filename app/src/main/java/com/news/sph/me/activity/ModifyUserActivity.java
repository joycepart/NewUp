package com.news.sph.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.news.sph.AppConfig;
import com.news.sph.AppContext;
import com.news.sph.R;
import com.news.sph.common.base.BaseTitleActivity;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.me.dto.ModifyDTO;
import com.news.sph.me.entity.ModifyEntity;
import com.news.sph.me.entity.ModifyResult;
import com.news.sph.utils.LogUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改用户信息主页面
 */
public class ModifyUserActivity extends BaseTitleActivity {
    @Bind(R.id.base_titlebar_ensure)
    TextView mBaseTitlebarEnsure;
    @Bind(R.id.modify_et)
    EditText mModifyEt;
    @Bind(R.id.modify_Btn)
    Button mModifyBtn;
    private String mNewName, mMembername;


    @Override
    protected int getContentResId() {
        return R.layout.activity_me_modifyuser;
    }

    @Override
    public void initView() {
        setTitleText("修改用户名");
        mBaseTitlebarEnsure.setVisibility(View.GONE);
    }

    @Override
    public void initData() {

    }


    private void goBack() {

    }

    private void modify() {
        mNewName = mModifyEt.getText().toString();
        ModifyDTO mdto = new ModifyDTO();
        mdto.setMembername(mNewName);
        mdto.setMembermob(AppContext.getInstance().getUser().getmUserMobile());
        mdto.setSign(AppConfig.SIGN_1);
        CommonApiClient.modifyUser(this, mdto, new CallBack<ModifyResult>() {
            @Override
            public void onSuccess(ModifyResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("修改用户名成功");
                    ModifyResult(result.getData());


                }

            }
        });
    }

    private void ModifyResult(List<ModifyEntity> data) {
        mMembername = data.get(0).getMembername();
        Intent intent = new Intent();
        intent.putExtra("mMembername", mMembername);
        setResult(100, intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.modify_Btn)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.base_titlebar_back:
                goBack();
                break;
            case R.id.modify_Btn:
                modify();
                break;
            default:
                break;
        }
    }
}
