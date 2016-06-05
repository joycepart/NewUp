package com.qluxstory.qingshe.me.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.qluxstory.qingshe.AppConfig;
import com.qluxstory.qingshe.AppContext;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.base.BaseTitleActivity;
import com.qluxstory.qingshe.common.http.CallBack;
import com.qluxstory.qingshe.common.http.CommonApiClient;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.common.utils.PhotoSystemUtils;
import com.qluxstory.qingshe.common.utils.TimeUtils;
import com.qluxstory.qingshe.me.MeUiGoto;
import com.qluxstory.qingshe.me.dto.UserPicDTO;
import com.qluxstory.qingshe.me.entity.UserPicResult;

import java.io.File;
import java.io.IOException;

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
    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";

    private String mMemberheadimg;
    Bitmap image;
    private String mImg;

    @Override
    protected int getContentResId() {
        return R.layout.activity_me_userinformation;
    }

    @Override
    public void initView() {
        mUserName.setText(AppContext.get("mUserName",""));
        mTvName.setText(AppContext.get("mUserName",""));
        mTvNum.setText(AppContext.get("mobileNum",""));
        ImageLoaderUtils.displayAvatarImage(AppContext.get("mPictruePath",""),
                mUserImg);
        mUserInformation.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }



    @OnClick({R.id.user_img, R.id.user_information})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_img:
                MeUiGoto.selectPic(this);//修改用户图像

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
        bdto.setMemberheadimg(mMemberheadimg);
        bdto.setMembermob(AppContext.get("mobileNum",""));
        bdto.setSign(AppConfig.SIGN_1);
        bdto.setTimestamp(TimeUtils.getSignTime());
        CommonApiClient.userPic(this, bdto, new CallBack<UserPicResult>() {
            @Override
            public void onSuccess(UserPicResult result) {
                if(AppConfig.SUCCESS.equals(result.getStatus())){
                    LogUtils.d("修改用户图像成功");
                    AppContext.set("mPictruePath",result.getData().get(0).getMemberHeadimg());
                }
            }
        });

    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        LogUtils.e("data--------",""+data);
        switch (requestCode) {
            case MeUiGoto.SELECT_REQUEST:
                if (data != null) {
                    //取得返回的Uri,基本上选择照片的时候返回的是以Uri形式，但是在拍照中有得机子呢Uri是空的，所以要特别注意
                    Uri mImageCaptureUri = data.getData();
                    LogUtils.e("Uri--------------", mImageCaptureUri + "");
                    mImg = PhotoSystemUtils.getRealFilePath(this, mImageCaptureUri);
                    LogUtils.e("mImg--------------", mImg + "");
                    //返回的Uri不为空时，那么图片信息数据都会在Uri中获得。如果为空，那么我们就进行下面的方式获取
                    if (mImageCaptureUri != null) {
                        try {
                            image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mImageCaptureUri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ImageLoader.getInstance().displayImage("file:///" + mImg,
                                mUserImg, ImageLoaderUtils.getAvatarOptions());

                    }

                } else {

                        if (PhotoSystemUtils.hasSdcard()) {
                            File tempFile = new File(
                                    Environment.getExternalStorageDirectory(),
                                    IMAGE_FILE_NAME);
                            Uri uri = Uri.fromFile(tempFile);
                            mImg = PhotoSystemUtils.getRealFilePath(this,uri);
                            try {
                                image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            ImageLoader.getInstance().displayImage("file:///"+mImg,
                                    mUserImg, ImageLoaderUtils.getAvatarOptions());;

                        } else {
                            Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG)
                                    .show();
                        }

                        }
//                    mMemberheadimg = ImageLoaderUtils.imgToBase64(mImg,image,null);
                    reqUserPic();//修改用户图像


                break;
            case MeUiGoto.MODIFYUSER_REQUEST:
                String mName = AppContext.get("mUserName","");
                mUserName.setText(mName);
                mTvName.setText(mName);
                break;
            default:
                break;

        }
    }


}
