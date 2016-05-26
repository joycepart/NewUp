package com.news.sph.me.activity;

import android.view.View;

import com.news.sph.AppConfig;
import com.news.sph.AppContext;
import com.news.sph.R;
import com.news.sph.common.base.BaseTitleActivity;
import com.news.sph.common.dto.BaseDTO;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.common.utils.LogUtils;
import com.news.sph.me.entity.TransactionEntity;
import com.news.sph.me.entity.TransactionResult;

import java.util.List;

/**
 * 交易明细主页面  现在改走base- simpleActivity  调用UIHelper.showFragment（）；
 */
public class TransactionDetailActivity extends BaseTitleActivity {
    private String strPhoneNum;
    @Override
    protected int getContentResId() {
        return R.layout.activity_transaction_detail;
    }

    @Override
    public void initView() {
        setTitleText("交易明细");
        strPhoneNum = AppContext.getInstance().getUser().getmUserMobile();

    }

    private void Transaction() {
        BaseDTO bdto=new BaseDTO();
        bdto.setMembermob(strPhoneNum);
        bdto.setSign(AppConfig.SIGN_1);
        CommonApiClient.getTransaction(this, bdto, new CallBack<TransactionResult>() {
            @Override
            public void onSuccess(TransactionResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.d("交易明细成功");
                    transactionResult(result.getData());

                }
            }
        });

    }

    private void transactionResult(List<TransactionEntity> data) {

    }

    @Override
    public void initData() {
        Transaction();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.base_titlebar_back:
                baseGoBack();
                break;

            default:
                break;
        }
        super.onClick(v);
    }
}
