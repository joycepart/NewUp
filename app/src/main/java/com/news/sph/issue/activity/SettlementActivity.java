package com.news.sph.issue.activity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.news.sph.AppConfig;
import com.news.sph.R;
import com.news.sph.common.base.BaseTitleActivity;
import com.news.sph.common.http.CallBack;
import com.news.sph.common.http.CommonApiClient;
import com.news.sph.common.utils.LogUtils;
import com.news.sph.issue.IssueUiGoto;
import com.news.sph.issue.dto.SettlementDTO;
import com.news.sph.issue.entity.SettlementResult;

import butterknife.Bind;

/**
 * 结算主页面
 */
public class SettlementActivity extends BaseTitleActivity {
    @Bind(R.id.set_ed)
    EditText mInBtn;
    @Bind(R.id.set_cb_wx)
    CheckBox mSetWx;
    @Bind(R.id.set_cb_zhi)
    CheckBox mSetZhi;
    @Bind(R.id.set_cb_hui)
    CheckBox mSetHui;
    @Bind(R.id.set_cb_agree)
    CheckBox mSetAgree;
    @Bind(R.id.set_tv)
    TextView mSetTv;
    @Bind(R.id.placeorder_tv)
    TextView mPlaceorderTv;
    @Bind(R.id.place_tv_nm)
    TextView mPlaceNm;
    @Bind(R.id.set_pay_Btn)
    Button mPayBtn;

    @Override
    protected int getContentResId() {
        return R.layout.activity_settlement;
    }

    @Override
    public void initView() {
        setTitleText("结算");
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.set_tv:
                IssueUiGoto.special(this,AppConfig.Server_Html+"");
                break;
            case R.id.set_pay_Btn:
                settlement();//下单并开奖
                break;
        }
        super.onClick(v);
    }

    private void settlement() {
        SettlementDTO gdto=new SettlementDTO();
        gdto.setType("");
        gdto.setUserPhone("");
        gdto.setInfor_phone("");
        gdto.setBat_code("");
        gdto.setSna_code("");
        gdto.setRec_participate_count("");
        gdto.setBalance("");
        gdto.setSna_total_count("");
        gdto.setTerm("");
        gdto.setRec_code("");
        gdto.setSign(AppConfig.SIGN_1);
        CommonApiClient.settlement(this, gdto, new CallBack<SettlementResult>() {
            @Override
            public void onSuccess(SettlementResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("结算成功");
                }

            }
        });

    }
}
