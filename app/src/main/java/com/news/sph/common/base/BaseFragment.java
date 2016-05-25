package com.news.sph.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.news.sph.AppConfig;
import com.news.sph.R;
import com.news.sph.common.eventbus.ErrorEvent;
import com.news.sph.common.interf.IBaseFragment;
import com.news.sph.common.loadingandretry.LoadingAndRetryManager;
import com.news.sph.common.loadingandretry.OnLoadingAndRetryListener;
import com.news.sph.common.utils.LogUtils;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Fragment的基类
 */
public abstract class BaseFragment extends Fragment implements
        View.OnClickListener, IBaseFragment {
    protected boolean prepared = false;
    protected LoadingAndRetryManager mLoadingAndRetryManager;
    private View rootView;

    public int getRootLayoutId(){
        return R.layout.base_fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(getRootLayoutId(), container,
                false);
        LinearLayout llContent = (LinearLayout) mView
                .findViewById(R.id.base_content_layout);
        if (rootView == null) {
            registerEventBus();
            rootView = inflater.inflate(getLayoutResId(), null);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        llContent.addView(rootView, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        ButterKnife.bind(this, mView);
        prepared = true;//注意：prepared=true在initView方法之间
        return mView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mLoadingAndRetryManager = LoadingAndRetryManager.generate(
                this, new OnLoadingAndRetryListener() {
                    @Override
                    public void setRetryEvent(View retryView) {
                        BaseFragment.this.setRetryEvent(retryView);
                    }
                });
        mLoadingAndRetryManager.showContent();
        initView(view);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }


    protected void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    protected void unRegisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy() {
        unRegisterEventBus();
        ButterKnife.unbind(this);
        super.onDestroy();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(!prepared)return;
        if (isVisibleToUser) {
            onVisible();
        }else{
            inVisible();
        }
    }

    public void onVisible() {
    }
    public void inVisible() {
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 布局资源文件
     *
     * @return
     */
    protected abstract int getLayoutResId();

    public void setRetryEvent(View retryView) {
//        View view = retryView.findViewById(R.id.id_btn_retry);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "start reload data",
//                        Toast.LENGTH_SHORT).show();
//                // loadData();
//            }
//        });
    }

    /**
     * 显示加载中的Dialog
     */
    public void showDialogLoading() {
        showDialogLoading(null);
    }

    /**
     * 显示加载中遮罩层Dialog，带消息提示
     *
     * @param msg
     */
    public void showDialogLoading(String msg) {
        FragmentActivity activity = getActivity();
        if (getActivity() != null) {
            if (activity instanceof BaseActivity) {
                if (TextUtils.isEmpty(msg)) {
                    ((BaseActivity) activity).showDialogLoading();
                } else {
                    ((BaseActivity) activity).showDialogLoading(msg);
                }
            }
        }
    }


    /**
     * 隐藏遮罩层
     */
    public void hideDialogLoading() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            if (activity instanceof BaseActivity) {
                ((BaseActivity) activity).hideDialogLoading();
            }
        }
    }

    public void onEventMainThread(ErrorEvent event) {
        String status = event.getStatus();
        String message = event.getMsg();
        if (event.getContext().equals(getActivity())) {
           LogUtils.i("error_status:" + status+"  "+"error_msg:" + message);
            if(!AppConfig.SUCCESS.equals(status)) {
                onErrorMsg();
            }
        }
    }

    protected void onErrorMsg(){

    }
}
