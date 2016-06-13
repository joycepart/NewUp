package com.qluxstory.qingshe.me.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPopupWindow;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.MainActivity;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.entity.BaseEntity;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.DialogUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.me.dto.WithdrawalsDTO;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 申请提现主页面
 */
public class WithdrawalsActivity extends BaseTitleActivity {
    @Bind(R.id.withd_md)
    LinearLayout mWithdMd;
    @Bind(R.id.withd_lin_bank)
    LinearLayout mLinBank;
    @Bind(R.id.withd_mon)
    EditText mWithdMon;
    @Bind(R.id.withd_us)
    EditText mWithdUs;
    @Bind(R.id.withd_nm)
    EditText mWithdNm;
    @Bind(R.id.withd_ipone)
    EditText mWithdIpone;
    @Bind(R.id.withd_bank)
    EditText mWithdBank;
    @Bind(R.id.withd_tv_md)
    TextView mWithdTv;
    @Bind(R.id.pay_Btn)
    Button mWBtn;
    private String strPhoneNum,mMon,mUs,mNm,mIpone,mWTv,mBank;
    private ArrayList<String> sList;
    TextView mBaseEnsure;

    @Override
    protected int getContentResId() {
        return R.layout.activity_withdrawals;
    }

    @Override
    public void initView() {
        setTitleText("提现");
        mBaseEnsure = (TextView) findViewById(R.id.base_titlebar_ensure);
        mBaseEnsure.setVisibility(View.GONE);
        mWTv = mWithdTv.getText().toString();
        if(mWTv.equals("支付宝")){
            mLinBank.setVisibility(View.GONE);
        }else {
            mLinBank.setVisibility(View.VISIBLE);
        }
        strPhoneNum = AppContext.get("mobileNum","");

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.withd_md, R.id.pay_Btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.withd_md:
                showModePop();
                break;
            case R.id.pay_Btn:
                mMon = mWithdMon.getText().toString();
                mUs = mWithdUs.getText().toString();
                mNm = mWithdNm.getText().toString();
                mIpone = mWithdIpone.getText().toString();
                mBank = mWithdBank.getText().toString();
                if(TextUtils.isEmpty(mMon)||mMon.length()<3){
                    DialogUtils.showPrompt(this,"请填写正确的提现金额!","确定");
                }

                else if(TextUtils.isEmpty(mUs)){
                    DialogUtils.showPrompt(this,"请填写账户！","确定");
                }
                else if(TextUtils.isEmpty(mNm)){
                    DialogUtils.showPrompt(this,"请填写姓名！","确定");
                }
                else if(TextUtils.isEmpty(mIpone)||mIpone.length()<11){
                    DialogUtils.showPrompt(this,"请填写手机号！","确定");
                }
                else if(mLinBank.getVisibility() == View.VISIBLE&&TextUtils.isEmpty(mBank)){
                    DialogUtils.showPrompt(this,"请填写银行号！","确定");
                }
               else {
                    Withdrawals();
                }

                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
        }
    }

    private void showModePop() {
        sList = new ArrayList<>();
        sList.add("支付宝");
        sList.add("银行卡");
        OptionsPopupWindow tipPopup = new OptionsPopupWindow(this);
        tipPopup.setPicker(sList);//设置里面list
        tipPopup.setOnoptionsSelectListener(new OptionsPopupWindow.OnOptionsSelectListener() {//确定的点击监听
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                if ("支付宝".equals(sList.get(options1))) {
                    mWithdTv.setText("支付宝");
                    mLinBank.setVisibility(View.GONE);

                } else if("银行卡".equals(sList.get(options1))){
                    mWithdTv.setText("银行卡");
                    mLinBank.setVisibility(View.VISIBLE);
                }

            }
        });
        tipPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {//设置窗体消失后，屏幕恢复亮度
            @Override
            public void onDismiss() {
                closePopupWindow();
            }
        });
        tipPopup.showAtLocation(mWithdBank, Gravity.BOTTOM, 0, 0);//显示的位置
        //弹窗后背景变暗
        openPopupWindow();

    }

    /**
     *  打开窗口 
     */
    private void openPopupWindow() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.7f;
        getWindow().setAttributes(params);
    }

    /**
     *  关闭窗口 
     */
    private void closePopupWindow() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 1f;
        getWindow().setAttributes(params);
    }


    private void Withdrawals() {
        WithdrawalsDTO mDto = new WithdrawalsDTO();
        mDto.setMembermob(strPhoneNum);
        mDto.setSign(AppConfig.SIGN_1);
        mDto.setTimestamp(TimeUtils.getSignTime());
        mDto.setAccounttype(mWTv);
        mDto.setAccountnumber(mUs);
        mDto.setPresentmoney(mMon);
        mDto.setReservemobile(mIpone);
        mDto.setReservename(mNm);
        CommonApiClient.IncomeWith(this, mDto, new CallBack<BaseEntity>() {
            @Override
            public void onSuccess(BaseEntity result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.d("提现请求成功");
                    Intent intent = new Intent(WithdrawalsActivity.this, MainActivity.class);
                    intent.putExtra("tag",4);
                    startActivity(intent);

                }

            }
        });
    }
}
