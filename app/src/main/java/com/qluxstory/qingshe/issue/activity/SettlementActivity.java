package com.qluxstory.qingshe.issue.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.alipay.Keys;
import com.qluxstory.qingshe.alipay.Rsa;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.dto.BaseDTO;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.SecurityUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.home.entity.AliPayResult;
import com.qluxstory.qingshe.home.entity.BalanceResult;
import com.qluxstory.qingshe.issue.IssueUiGoto;
import com.qluxstory.qingshe.issue.dto.SettlementDTO;
import com.qluxstory.qingshe.issue.entity.IssueProduct;
import com.qluxstory.qingshe.issue.entity.SettlementEntity;
import com.qluxstory.qingshe.issue.entity.SettlementResult;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import butterknife.Bind;

/**
 * 结算主页面
 */
public class SettlementActivity extends BaseTitleActivity {
    @Bind(R.id.set_ed)
    TextView mInBtn;
    @Bind(R.id.settlement_balance)
    TextView mSetBalance;
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
    @Bind(R.id.settle_title)
    TextView mTitle;
    @Bind(R.id.settle_term)
    TextView mTerm;
    @Bind(R.id.settle_img)
    ImageView mImg;
    @Bind(R.id.place_tv_nm)
    TextView mPlaceNm;
    @Bind(R.id.set_pay_Btn)
    Button mPayBtn;
    @Bind(R.id.palce_pay_wx)
    LinearLayout mPayWx;
    @Bind(R.id.palce_pay_alipay)
    LinearLayout mPayAlipay;
    @Bind(R.id.palce_pay_balance)
    LinearLayout mPayBalance;
    String mCaTerm;
    String mCaTitle;
    String mBalance;
    String mPic;
    String mTotalVount;
    String mParticipate;
    String mRecCode;
    String mBatCode;
    String mSnaCode  ;
    IssueProduct issueProduct;
    TextView mBaseEnsure;
    private static final int RQF_PAY = 1;

    private static final int RQF_LOGIN = 2;
    @Override
    protected int getContentResId() {
        return R.layout.activity_settlement;
    }

    @Override
    public void initView() {
        setTitleText("结算");
        mBaseEnsure = (TextView) findViewById(R.id.base_titlebar_ensure);
        mBaseEnsure.setVisibility(View.GONE);
        issueProduct = AppContext.getInstance().getIssueProduct();
        mCaTerm = issueProduct.getmSnaTerm();
        mCaTitle = issueProduct.getmSnaTitle();
        mPic = issueProduct.getmPicUrl();
        mTotalVount = issueProduct.getmTotalCount();
        mParticipate = issueProduct.getmSnaOut();
        mBatCode = issueProduct.getmBatCode();
        mSnaCode = issueProduct.getmSnaCode();
        if(!TextUtils.isEmpty(issueProduct.getmRecCode())){
            mRecCode = issueProduct.getmRecCode();
        }else {
            mRecCode = "";
        }

        mTitle.setText(mCaTitle);
        mTerm.setText("第"+mCaTerm+"期");
        mPlaceNm.setText(mBalance);
        ImageLoaderUtils.displayImage(mPic,mImg);
        Intent intent = getIntent();
        if(intent!=null){
            mBalance = intent.getBundleExtra("bundle").getString("mBalance");
            mPlaceNm.setText(mBalance);
        }


        mInBtn.setText(AppContext.get("mobileNum",""));

        mPayBtn.setOnClickListener(this);
        mPayWx.setOnClickListener(this);
        mPayAlipay.setOnClickListener(this);
        mPayBalance.setOnClickListener(this);
        mSetTv.setOnClickListener(this);
        mSetAgree.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    private final CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                mPayBtn.setEnabled(true);
            } else {
                mPayBtn.setEnabled(false);
            }
        }
    };

    @Override
    public void initData() {
        reqBalance();//会员余额
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
            case R.id.palce_pay_wx:
                mSetWx.setChecked(true);
                mSetZhi.setChecked(false);
                mSetHui.setChecked(false);
                break;
            case R.id.palce_pay_alipay:
                mSetWx.setChecked(false);
                mSetZhi.setChecked(true);
                mSetHui.setChecked(false);
                break;
            case R.id.palce_pay_balance:
                mSetWx.setChecked(false);
                mSetZhi.setChecked(false);
                mSetHui.setChecked(true);
                break;

        }
        super.onClick(v);
    }

    private void reqBalance() {
        BaseDTO dto = new BaseDTO();
        dto.setSign(AppConfig.SIGN_1);
        dto.setTimestamp(TimeUtils.getSignTime());
        dto.setMembermob(AppContext.get("mobileNum", ""));
        CommonApiClient.balance(this, dto, new CallBack<BalanceResult>() {
            @Override
            public void onSuccess(BalanceResult result) {
                if (AppConfig.SUCCESS.equals(result.getStatus())) {
                    LogUtils.e("会员余额成功");
                    mSetBalance.setText(result.getData().get(0).getCashAmountMoney());

                }

            }
        });
    }

    private void settlement() {
        mPayBtn.setEnabled(false);
        SettlementDTO gdto=new SettlementDTO();
        if(mSetWx.isChecked()){
            gdto.setType("2");
        }

        if(mSetZhi.isChecked()){
            gdto.setType("1");
        }
        if(mSetHui.isChecked()){
            gdto.setType("3");
        }

        gdto.setUserPhone(AppContext.get("mobileNum",""));
        gdto.setInfor_phone(AppContext.get("mobileNum",""));
        gdto.setBat_code(mBatCode);
        gdto.setSna_code(mSnaCode);
        gdto.setRec_participate_count(mParticipate);
        gdto.setBalance(mBalance);
        gdto.setSna_total_count(mTotalVount);
        gdto.setTerm(mCaTerm);
        gdto.setRec_code(mRecCode);
        gdto.setSign(AppConfig.SIGN_1);
        gdto.setTime(TimeUtils.getSignTime());
        CommonApiClient.settlement(this, gdto, new CallBack<SettlementResult>() {
            @Override
            public void onSuccess(SettlementResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.e("结算成功");
                }

                if(mSetZhi.isChecked()){
                    reqAlipayPay(result.getData());
                    mPayBtn.setEnabled(true);
                }else if(mSetWx.isChecked()){
                    reqWx(result.getData());
                    mPayBtn.setEnabled(true);

                }else if(mSetHui.isChecked()){
                    IssueUiGoto.payment(SettlementActivity.this);//支付结果页
                    mPayBtn.setEnabled(true);
                }

            }
        });

    }
    IWXAPI msgApi;
    private void reqWx(List<SettlementEntity> data) {
        msgApi = WXAPIFactory.createWXAPI(this, AppConfig.Wx_App_Id);
        msgApi.registerApp(AppConfig.Wx_App_Id);
        if (msgApi != null) {
            if (msgApi.isWXAppInstalled()) {
                String characterEncoding = "UTF-8";
                String mWxKey = data.get(0).getPrivateKey();
                PayReq req = new PayReq();
                req.appId = AppConfig.Wx_App_Id;
                req.partnerId = data.get(0).getPartnerID();
                req.prepayId = data.get(0).getPrepayid();
                req.packageValue = "Sign=WXPay";
                req.nonceStr = TimeUtils.getSignTime();
                String time =  TimeUtils.getSignTime();
                req.timeStamp = time;
                String str = "appid="+AppConfig.Wx_App_Id
                        +"&noncestr="+TimeUtils.getSignTime()
                        +"&package="+"Sign=WXPay"
                        +"&partnerid="+data.get(0).getPartnerID()
                        +"&prepayid="+data.get(0).getPrepayid()
                        +"&timestamp="+time;
                String sing = str.trim().toString()+"&key="+mWxKey;
                LogUtils.e("sing---------",sing);
                req.sign = SecurityUtils.MD5(sing.trim().toString());
                LogUtils.e("sign------------MD5",SecurityUtils.MD5(sing.trim().toString()));
                msgApi.sendReq(req);
            }
        }
    }

    private void reqAlipayPay(List<SettlementEntity> data) {
        String info = getNewOrderInfo(data);//这个是订单信息
        // 这里根据签名方式对订单信息进行签名
        LogUtils.e("Keys.PRIVATE----", Keys.PRIVATE);
        String strsign = Rsa.sign(info, Keys.PRIVATE);
        LogUtils.e("strsign----",""+strsign);
        try {
            // 仅需对sign 做URL编码
            strsign = URLEncoder.encode(strsign, "UTF-8");
            LogUtils.e("strsign----utf8",""+strsign);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 组装好参数
        final String orderInfo = info + "&sign=\"" +strsign + "\"&" + getSignType();
        LogUtils.e("orderInfo----",""+orderInfo);
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(SettlementActivity.this);//支付宝接口了，支付宝现在把很多功能都封装
                String result = alipay.pay(orderInfo,true);//返回的结果
                LogUtils.e("result-----------", "result = " + result);
                Message msg = new Message();
                msg.what = RQF_PAY;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    Handler mHandler = new Handler() {

        public void handleMessage(android.os.Message msg) {

            AliPayResult payResult = new AliPayResult((String) msg.obj);
            /**
             * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
             * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
             * docType=1) 建议商户依赖异步通知
             */
            LogUtils.e("payResult---",""+payResult);
            String resultInfo = payResult.getResult();// 同步返回需要验证的信息

            String resultStatus = payResult.getResultStatus();
            Log.e("resultStatus----",""+resultStatus);
            // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
            switch (msg.what) {
                case RQF_PAY:
                    if (TextUtils.equals(resultStatus, "9000")) {
                        IssueUiGoto.payment(SettlementActivity.this);//支付结果页
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(SettlementActivity.this, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(SettlementActivity.this, "支付失败",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    break;
                default:
                    break;
            }
        };
    };
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

    //获得订单信息的方法
    private String getNewOrderInfo(List<SettlementEntity> data) {


        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + data.get(0).getPartnerID() + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + data.get(0).getSellerAccount()+ "\"";

         //商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + data.get(0).getOrderNum() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + data.get(0).getProductName() + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + data.get(0).getProductName() + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + data.get(0).getAmount() + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&kAliPayNotifyURL=" + "\"" + AppConfig.BASE_URL+"/dbnotify_url.aspx" + "\"";
        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

}
