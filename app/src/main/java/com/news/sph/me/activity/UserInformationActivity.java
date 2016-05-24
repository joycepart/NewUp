package com.news.sph.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.news.sph.AppConfig;
import com.news.sph.AppContext;
import com.news.sph.R;
import com.news.sph.common.base.BaseTitleActivity;
import com.news.sph.utils.ImageLoaderUtils;
import com.news.sph.utils.LogUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 用户信息主页面
 */
public class UserInformationActivity extends BaseTitleActivity {
    @Bind(R.id.base_titlebar_ensure)
    TextView mBaseTitlebarEnsure;
    @Bind(R.id.base_view)
    TextView mBaseView;
    @Bind(R.id.user_name)
    TextView mUserName;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.user_img)
    ImageView mUserImg;
    @Bind(R.id.tv_num)
    TextView mTvNum;
    @Bind(R.id.user_information)
    LinearLayout mUserInformation;

    private String mUsName, mUsNum, mUsImg,mPicUrl;


    @Override
    protected int getContentResId() {
        return R.layout.activity_me_userinformation;
    }

    @Override
    public void initView() {

        mBaseView.setVisibility(View.VISIBLE);
        mUsName = AppContext.getInstance().getUser().getmUserName();
        mUsNum = AppContext.getInstance().getUser().getmUserMobile();
        mUsImg = AppContext.getInstance().getUser().getmPictruePath();
        mPicUrl = AppConfig.BASE_URL+mUsImg;
        mUserName.setText(mUsName);
        mTvName.setText(mUsName);
        mTvNum.setText(mUsNum);
        ImageLoaderUtils.displayImage(mPicUrl,mUserImg);

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (100 == resultCode) {
            mUsName = data.getExtras().getString("mMembername");
            mUserName.setText(mUsName);
            mTvName.setText(mUsName);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.user_img, R.id.user_information})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_img:
                baseGoBack();
                break;
            case R.id.user_information:
                Intent intent = new Intent(this, ModifyUserActivity.class);
                this.startActivityForResult(intent, 100);;
//                MeUiGoto.modifyUser(this);//修改用户名
                break;
        }
    }
}
