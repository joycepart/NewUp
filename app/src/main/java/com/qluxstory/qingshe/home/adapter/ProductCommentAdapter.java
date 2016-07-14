package com.qluxstory.qingshe.home.adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;
import com.qluxstory.qingshe.R;
import com.qluxstory.qingshe.common.utils.ImageLoaderUtils;
import com.qluxstory.qingshe.common.utils.LogUtils;
import com.qluxstory.qingshe.home.entity.ProductCommentEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/7/5.
 */
public class ProductCommentAdapter extends BaseSimpleRecyclerAdapter<ProductCommentEntity> {
    private final FragmentActivity context;
    Context mContext;
    PopupWindow popGridWindow;
    List<ProductCommentEntity> list =  new ArrayList<>();


    public ProductCommentAdapter(FragmentActivity activity) {
        this.context = activity;
    }

    //    public ProductCommentAdapter(Context context) {
//        mContext = context;
//    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.fragment_productcomment;
    }

    @Override
    public void bindData(final BaseRecyclerViewHolder holder, final ProductCommentEntity productCommentEntity,final int position) {
        ImageView mImg=holder.getView( R.id.pc_img);
        ImageView mImgFrist=holder.getView( R.id.pdc_comment_img_01);
        ImageView mImgSecond=holder.getView( R.id.pdc_comment_img_02);
        ImageView mImgThird=holder.getView( R.id.pdc_comment_img_03);
        LinearLayout mLin=holder.getView( R.id.pdt_lin);

        ImageLoaderUtils.displayImage(productCommentEntity.getHeadimg(), mImg);
        holder.setText(R.id.pc_name, productCommentEntity.getRev_nickname());
        holder.setText(R.id.pc_data, productCommentEntity.getRev_time());
        holder.setText(R.id.pc_tv_pl, productCommentEntity.getRev_content());
        Button mPsOne=holder.getView( R.id.pdc_ps_btn_one);
        Button mPsTwo=holder.getView( R.id.pdc_ps_btn_two);
        Button mPsThree=holder.getView( R.id.pdc_ps_btn_three);
        Button mPsFour=holder.getView( R.id.pdc_ps_btn_four);
        Button mPsFive=holder.getView( R.id.pdc_ps_btn_five);
        Button mCsOne=holder.getView( R.id.pdc_cs_btn_one);
        Button mCsTwo=holder.getView( R.id.pdc_cs_btn_two);
        Button mCsThree=holder.getView( R.id.pdc_cs_btn_three);
        Button mCsFour=holder.getView( R.id.pdc_cs_btn_four);
        Button mCsFive=holder.getView( R.id.pdc_cs_btn_five);
        Button mDsOne=holder.getView( R.id.pdc_ds_btn_one);
        Button mDsTwo=holder.getView( R.id.pdc_ds_btn_two);
        Button mDsThree=holder.getView( R.id.pdc_ds_btn_three);
        Button mDsFour=holder.getView( R.id.pdc_ds_btn_four);
        Button mDsFive=holder.getView( R.id.pdc_ds_btn_five);

        String rev = productCommentEntity.getRev_level();
        String kf = productCommentEntity.getKf_level();
        String dis = productCommentEntity.getDis_level();
        if(rev.equals("1")){
            mPsOne.setBackgroundResource(R.drawable.xingxing_huang);
            mPsTwo.setBackgroundResource(R.drawable.xingxing_hu);
            mPsThree.setBackgroundResource(R.drawable.xingxing_hu);
            mPsFour.setBackgroundResource(R.drawable.xingxing_hu);
            mPsFive.setBackgroundResource(R.drawable.xingxing_hu);
        }
        else if(rev.equals("2")){
            mPsOne.setBackgroundResource(R.drawable.xingxing_huang);
            mPsTwo.setBackgroundResource(R.drawable.xingxing_huang);
            mPsThree.setBackgroundResource(R.drawable.xingxing_hu);
            mPsFour.setBackgroundResource(R.drawable.xingxing_hu);
            mPsFive.setBackgroundResource(R.drawable.xingxing_hu);
        }
        else if(rev.equals("3")){
            mPsOne.setBackgroundResource(R.drawable.xingxing_huang);
            mPsTwo.setBackgroundResource(R.drawable.xingxing_huang);
            mPsThree.setBackgroundResource(R.drawable.xingxing_huang);
            mPsFour.setBackgroundResource(R.drawable.xingxing_hu);
            mPsFive.setBackgroundResource(R.drawable.xingxing_hu);
        }
        else if(rev.equals("4")){
            mPsOne.setBackgroundResource(R.drawable.xingxing_huang);
            mPsTwo.setBackgroundResource(R.drawable.xingxing_huang);
            mPsThree.setBackgroundResource(R.drawable.xingxing_huang);
            mPsFour.setBackgroundResource(R.drawable.xingxing_huang);
            mPsFive.setBackgroundResource(R.drawable.xingxing_hu);
        }
        else if(rev.equals("5")){
            mPsOne.setBackgroundResource(R.drawable.xingxing_huang);
            mPsTwo.setBackgroundResource(R.drawable.xingxing_huang);
            mPsThree.setBackgroundResource(R.drawable.xingxing_huang);
            mPsFour.setBackgroundResource(R.drawable.xingxing_huang);
            mPsFive.setBackgroundResource(R.drawable.xingxing_huang);
        }


        if(kf.equals("1")){
            mCsOne.setBackgroundResource(R.drawable.xingxing_huang);
            mCsTwo.setBackgroundResource(R.drawable.xingxing_hu);
            mCsThree.setBackgroundResource(R.drawable.xingxing_hu);
            mCsFour.setBackgroundResource(R.drawable.xingxing_hu);
            mCsFive.setBackgroundResource(R.drawable.xingxing_hu);
        }
        else if(kf.equals("2")){
            mCsOne.setBackgroundResource(R.drawable.xingxing_huang);
            mCsTwo.setBackgroundResource(R.drawable.xingxing_huang);
            mCsThree.setBackgroundResource(R.drawable.xingxing_hu);
            mCsFour.setBackgroundResource(R.drawable.xingxing_hu);
            mCsFive.setBackgroundResource(R.drawable.xingxing_hu);
        }
        else if(kf.equals("3")){
            mCsOne.setBackgroundResource(R.drawable.xingxing_huang);
            mCsTwo.setBackgroundResource(R.drawable.xingxing_huang);
            mCsThree.setBackgroundResource(R.drawable.xingxing_huang);
            mCsFour.setBackgroundResource(R.drawable.xingxing_hu);
            mCsFive.setBackgroundResource(R.drawable.xingxing_hu);
        }
        else if(kf.equals("4")){
            mCsOne.setBackgroundResource(R.drawable.xingxing_huang);
            mCsTwo.setBackgroundResource(R.drawable.xingxing_huang);
            mCsThree.setBackgroundResource(R.drawable.xingxing_huang);
            mCsFour.setBackgroundResource(R.drawable.xingxing_huang);
            mCsFive.setBackgroundResource(R.drawable.xingxing_hu);
        }
        else if (kf.equals("5")){
            mCsOne.setBackgroundResource(R.drawable.xingxing_huang);
            mCsTwo.setBackgroundResource(R.drawable.xingxing_huang);
            mCsThree.setBackgroundResource(R.drawable.xingxing_huang);
            mCsFour.setBackgroundResource(R.drawable.xingxing_huang);
            mCsFive.setBackgroundResource(R.drawable.xingxing_huang);
        }



        if(dis.equals("1")){
            mDsOne.setBackgroundResource(R.drawable.xingxing_huang);
            mDsTwo.setBackgroundResource(R.drawable.xingxing_hu);
            mDsThree.setBackgroundResource(R.drawable.xingxing_hu);
            mDsFour.setBackgroundResource(R.drawable.xingxing_hu);
            mDsFive.setBackgroundResource(R.drawable.xingxing_hu);
        }
        else if(dis.equals("2")){
            mDsOne.setBackgroundResource(R.drawable.xingxing_huang);
            mDsTwo.setBackgroundResource(R.drawable.xingxing_huang);
            mDsThree.setBackgroundResource(R.drawable.xingxing_hu);
            mDsFour.setBackgroundResource(R.drawable.xingxing_hu);
            mDsFive.setBackgroundResource(R.drawable.xingxing_hu);
        }
        else if(dis.equals("3")){
            mDsOne.setBackgroundResource(R.drawable.xingxing_huang);
            mDsTwo.setBackgroundResource(R.drawable.xingxing_huang);
            mDsThree.setBackgroundResource(R.drawable.xingxing_huang);
            mDsFour.setBackgroundResource(R.drawable.xingxing_hu);
            mDsFive.setBackgroundResource(R.drawable.xingxing_hu);
        }
        else if(dis.equals("4")){
            mDsOne.setBackgroundResource(R.drawable.xingxing_huang);
            mDsTwo.setBackgroundResource(R.drawable.xingxing_huang);
            mDsThree.setBackgroundResource(R.drawable.xingxing_huang);
            mDsFour.setBackgroundResource(R.drawable.xingxing_huang);
            mDsFive.setBackgroundResource(R.drawable.xingxing_hu);
        }
        else if(dis.equals("5")){
            mDsOne.setBackgroundResource(R.drawable.xingxing_huang);
            mDsTwo.setBackgroundResource(R.drawable.xingxing_huang);
            mDsThree.setBackgroundResource(R.drawable.xingxing_huang);
            mDsFour.setBackgroundResource(R.drawable.xingxing_huang);
            mDsFive.setBackgroundResource(R.drawable.xingxing_huang);
        }

        if(TextUtils.isEmpty(productCommentEntity.getFirst_pic())
                &&TextUtils.isEmpty(productCommentEntity.getSecond_pic())
                &&TextUtils.isEmpty(productCommentEntity.getThird_pic())){
            mLin.setVisibility(View.GONE);
        }else {
            mLin.setVisibility(View.VISIBLE);
            mImgFrist.setVisibility(View.VISIBLE);
            ImageLoaderUtils.displayImage(productCommentEntity.getFirst_pic(), mImgFrist);
            if(TextUtils.isEmpty(productCommentEntity.getSecond_pic())){
                mImgSecond.setVisibility(View.GONE);
            }else {
                mImgSecond.setVisibility(View.VISIBLE);
                ImageLoaderUtils.displayImage(productCommentEntity.getSecond_pic(), mImgSecond);
            }
            if(TextUtils.isEmpty(productCommentEntity.getThird_pic())){
                mImgThird.setVisibility(View.GONE);
            }else {
                mImgThird.setVisibility(View.VISIBLE);
                ImageLoaderUtils.displayImage(productCommentEntity.getThird_pic(), mImgThird);
            }
        }

        list.add(position,productCommentEntity);

        mImgFrist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopNm(list.get(position).getFirst_pic());
            }
        });
        mImgSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopNm(list.get(position).getSecond_pic());
            }
        });
        mImgThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopNm(list.get(position).getThird_pic());
            }
        });
    }

    private void showPopNm(String pic) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.from(context).inflate(R.layout.item_pdt_comment, null);
        popGridWindow = new PopupWindow(view, WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,true);

        backgroundAlpha(0.7f);
        // 需要设置一下此参数，点击外边可消失
        popGridWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击窗口外边窗口消失
        popGridWindow.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        popGridWindow.setFocusable(false);
        ImageView img= (ImageView) view.findViewById(R.id.it_img);
        ImageLoaderUtils.displayImage(pic, img);
        View parent = context.getWindow().getDecorView();//高度为手机实际的像素高度
        popGridWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
        //添加pop窗口关闭事件
        popGridWindow.setOnDismissListener(new poponDismissListener());
    }

    public class poponDismissListener implements PopupWindow.OnDismissListener{

        @Override
        public void onDismiss() {
            LogUtils.e("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
            popGridWindow.dismiss();
        }

    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        context.getWindow().setAttributes(lp);
    }



}
