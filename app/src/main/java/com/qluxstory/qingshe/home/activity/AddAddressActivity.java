package com.qluxstory.qingshe.home.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.SecurityUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.home.dto.AddAddressDTO;
import com.qluxstory.qingshe.home.entity.AddAddressEntity;
import com.qluxstory.qingshe.home.entity.AddAddressResult;
import com.qluxstory.qingshe.home.entity.Consignee;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 新增地址主页面
 */
public class AddAddressActivity extends BaseTitleActivity {
    @Bind(R.id.base_titlebar_back)
    TextView mBaseTitlebarBack;
    @Bind(R.id.et_consignee)
    EditText mEtConsignee;
    @Bind(R.id.et_num)
    EditText mEtNum;
    @Bind(R.id.et_city)
    EditText mEtCity;
    @Bind(R.id.et_address)
    EditText mEtAddress;
    @Bind(R.id.add_Btn)
    Button mAddBtn;
    Consignee consignee;

    @Override
    protected int getContentResId() {
        return R.layout.activity_addaddress;
    }

    @Override
    public void initView() {
        setTitleText("新增地址");
        mEtCity.setOnClickListener(this);
        consignee = AppContext.getInstance().getConsignee();


    }

    @Override
    public void initData() {

    }


    @OnClick(R.id.add_Btn)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_Btn:
                reqAdd();//添加收货地址
                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
            case R.id.et_city:
                Intent intent = new Intent(this,TestPopActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void reqAdd() {
        AddAddressDTO dto = new AddAddressDTO();
        LogUtils.e("未加密前的----", TimeUtils.getSignTime()+AppConfig.SIGN_1);
        LogUtils.e("加密后的---", SecurityUtils.MD5(TimeUtils.getSignTime() + AppConfig.SIGN_1));
        dto.setSign(AppConfig.SIGN_1);
        dto.setTimestamp(TimeUtils.getSignTime());
        dto.setMembermob(AppContext.get("mobileNum",""));
        dto.setProvincity(mEtCity.getText().toString());
        dto.setDelivmobile(mEtNum.getText().toString());
        dto.setConname(mEtConsignee.getText().toString());
        dto.setAddredetail(mEtAddress.getText().toString());
        CommonApiClient.addAdress(this, dto, new CallBack<AddAddressResult>() {
            @Override
            public void onSuccess(AddAddressResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("添加收货地址成功");
                    AddAddressEntity addData  = result.getData().get(0);
                    consignee.setProvincialCity(addData.getProvinCity());
                    consignee.setDeliveredMobile(addData.getDelivMobile());
                    consignee.setAddressInDetail(addData.getAddreDetail());
                    consignee.setConsigneeName(addData.getConName());
                    finish();
                }

            }
        });
    }
}
