package com.news.sph.me.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.news.sph.AppContext;
import com.news.sph.R;
import com.news.sph.common.base.BaseActivity;
import com.news.sph.common.base.BaseTitleActivity;
import com.news.sph.me.entity.User;
import com.news.sph.me.utils.MeUiGoto;
import com.news.sph.utils.LogUtils;

import butterknife.Bind;

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
    private String mUsName,mUsNum,mUsImg;


    @Override
    protected int getContentResId() {
        return R.layout.activity_me_userinformation;
    }

    @Override
    public void initView() {

        mBaseView.setVisibility(View.VISIBLE);

        mUsName = AppContext.getInstance().getUser().getUserName();
        mUsNum = AppContext.getInstance().getUser().getUserMobile();
        mUsImg = AppContext.getInstance().getUser().getPictruePath();
        LogUtils.e("userName: "+mUsName);
        mUserName.setText(mUsName);
        mTvName.setText(mUsName);
        mTvNum.setText(mUsNum);
        mUserInformation.setOnClickListener(this);



    }

    @Override
    public void initData() {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
            case R.id.user_information:
                Intent intent = new Intent(this,ModifyUserActivity.class);
                this.startActivityForResult(intent,100 ); ;
//                MeUiGoto.modifyUser(this);//修改用户名
                break;

            default:
                break;
        }
        super.onClick(v);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(100==resultCode){
            mUsName=data.getExtras().getString("mMembername");
            mUserName.setText(mUsName);
            mTvName.setText(mUsName);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
