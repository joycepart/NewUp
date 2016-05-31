package com.qluxstory.qingshe.me.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.me.MeUiGoto;
import com.qluxstory.qingshe.me.dto.UserPicDTO;
import com.qluxstory.qingshe.me.entity.UserPicResult;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 用户信息主页面
 */
public class UserInformationActivity extends BaseTitleActivity {

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

    private String mUsName, mUsNum, mUsImg,mNewName;


    @Override
    protected int getContentResId() {
        return R.layout.activity_me_userinformation;
    }

    @Override
    public void initView() {
        mUsName = AppContext.getInstance().getUser().getmUserName();
        mUsNum = AppContext.getInstance().getUser().getmUserMobile();
        mUsImg = AppContext.getInstance().getUser().getmPictruePath();
        mUserName.setText(mUsName);
        mTvName.setText(mUsName);
        mTvNum.setText(mUsNum);
        ImageLoader.getInstance().displayImage(mUsImg,
                mUserImg, ImageLoaderUtils.getAvatarOptions());
//        ImageLoaderUtils.displayImage(mUsImg,mUserImg);
        mUserInformation.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }



    @OnClick({R.id.user_img, R.id.user_information})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_img:
//                reqUserPic();
                break;
            case R.id.user_information:
                MeUiGoto.modifyUser(this);//修改用户名
                break;
            case R.id.base_titlebar_back:
                goBack();
                break;
        }
    }

    @Override
    protected void baseGoBack() {
        super.baseGoBack();
    }

    private void goBack() {
        setResult(1002);
        finish();
    }

    private void reqUserPic() {
        UserPicDTO bdto=new UserPicDTO();
        bdto.setMemberheadimg("");
        bdto.setMembermob(mUsNum);
        bdto.setSign(AppConfig.SIGN_1);
        CommonApiClient.userPic(this, bdto, new CallBack<UserPicResult>() {
            @Override
            public void onSuccess(UserPicResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.d("修改用户图像成功");
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mNewName = AppContext.getInstance().getUser().getmUserName();
        mUserName.setText(mUsName);
        mTvName.setText(mUsName);
        super.onActivityResult(requestCode, resultCode, data);
    }


}
