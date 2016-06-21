package com.qluxstory.qingshe.home.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.DialogUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.home.HomeUiGoto;
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
    private String mProvince,mCity,mArea;

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
                if(TextUtils.isEmpty(mEtConsignee.getText().toString())){
                    DialogUtils.showPrompt(this,"请填写收货人姓名","确定");
                }
                else if(TextUtils.isEmpty(mEtNum.getText().toString())||mEtNum.getText().toString().length()<11){
                    DialogUtils.showPrompt(this,"请填写正确手机号","确定");
                }
                else if(TextUtils.isEmpty(mEtCity.getText().toString())){
                    DialogUtils.showPrompt(this,"请选择省，市","确定");
                }
                else if(TextUtils.isEmpty(mEtAddress.getText().toString())){
                    DialogUtils.showPrompt(this,"请填写详细地址","确定");
                }else {
                    reqAdd();//添加收货地址
                }

                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
            case R.id.et_city:
                HomeUiGoto.city(this);//选择省，市
                break;
            default:
                break;
        }
    }

    private void reqAdd() {
        AddAddressDTO dto = new AddAddressDTO();
        String time = TimeUtils.getSignTime();
        dto.setSign(time+AppConfig.SIGN_1);
        dto.setTimestamp(time);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case HomeUiGoto.CITY_REQUEST:
                mProvince = data.getStringExtra("mCurrentProviceName");
                mCity = data.getStringExtra("mCurrentCityName");
                mArea = data.getStringExtra("mCurrentDistrictName");
                mEtCity.setText(mProvince+mCity+mArea);
                break;
            default:
                break;

        }
    }
}
