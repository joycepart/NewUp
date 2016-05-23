package com.news.sph.common.loadingandretry;

import android.content.Context;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.news.sph.R;

public class LoadingAndRetryLayout extends FrameLayout {
	private View mLoadingView;
	private View mRetryView;
	private View mContentView;
	private View mEmptyView;
	private LayoutInflater mInflater;

	public LoadingAndRetryLayout(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	public LoadingAndRetryLayout(Context context, AttributeSet attrs) {
		this(context, attrs, -1);
		init(context);
	}

	public LoadingAndRetryLayout(Context context) {
		this(context, null);
		init(context);
	}

	public void init(Context context) {
		mInflater = LayoutInflater.from(context);
		View view = mInflater.inflate(R.layout.base_loading_retry, this, false);
		mLoadingView = view.findViewById(R.id.base_loading);
		mRetryView = view.findViewById(R.id.base_retry);
		mEmptyView = view.findViewById(R.id.base_empty);

		mLoadingView.setVisibility(View.GONE);
		mRetryView.setVisibility(View.GONE);
		mEmptyView.setVisibility(View.GONE);

		addView(view);
	}

	private boolean isMainThread() {
		return Looper.myLooper() == Looper.getMainLooper();
	}

	public void showLoading() {
		if (isMainThread()) {
			showView(mLoadingView);
		} else {
			post(new Runnable() {
				@Override
				public void run() {
					showView(mLoadingView);
				}
			});
		}
	}

	public void showRetry() {
		if (isMainThread()) {
			showView(mRetryView);
		} else {
			post(new Runnable() {
				@Override
				public void run() {
					showView(mRetryView);
				}
			});
		}

	}

	public void showEmpty() {
		if (isMainThread()) {
			showView(mEmptyView);
		} else {
			post(new Runnable() {
				@Override
				public void run() {
					showView(mEmptyView);
				}
			});
		}
	}

	public void showContent() {
		if (isMainThread()) {
			showView(mContentView);
		} else {
			post(new Runnable() {
				@Override
				public void run() {
					showView(mContentView);
				}
			});
		}
	}

	private void showView(View view) {
		if (view == null)
			return;

		if (view == mLoadingView) {
			mLoadingView.setVisibility(View.VISIBLE);
			if (mRetryView != null)
				mRetryView.setVisibility(View.GONE);
			if (mContentView != null)
				mContentView.setVisibility(View.GONE);
			if (mEmptyView != null)
				mEmptyView.setVisibility(View.GONE);
		} else if (view == mRetryView) {
			mRetryView.setVisibility(View.VISIBLE);
			if (mLoadingView != null)
				mLoadingView.setVisibility(View.GONE);
			if (mContentView != null)
				mContentView.setVisibility(View.GONE);
			if (mEmptyView != null)
				mEmptyView.setVisibility(View.GONE);
		} else if (view == mContentView) {
			mContentView.setVisibility(View.VISIBLE);
			if (mLoadingView != null)
				mLoadingView.setVisibility(View.GONE);
			if (mRetryView != null)
				mRetryView.setVisibility(View.GONE);
			if (mEmptyView != null)
				mEmptyView.setVisibility(View.GONE);
		} else if (view == mEmptyView) {
			mEmptyView.setVisibility(View.VISIBLE);
			if (mLoadingView != null)
				mLoadingView.setVisibility(View.GONE);
			if (mRetryView != null)
				mRetryView.setVisibility(View.GONE);
			if (mContentView != null)
				mContentView.setVisibility(View.GONE);
		}

	}

	public View setLoadingView(int layoutId) {
		View loadingView = mInflater.inflate(layoutId, this, false);
		;
		removeView(mLoadingView);
		addView(loadingView);
		mLoadingView = loadingView;
		return mLoadingView;
	}

	public View setEmptyView(int layoutId) {
		View emptyView = mInflater.inflate(layoutId, this, false);
		;
		removeView(mEmptyView);
		addView(emptyView);
		mEmptyView = emptyView;
		return mEmptyView;
	}

	public View setRetryView(int layoutId) {
		View retryView = mInflater.inflate(layoutId, this, false);
		;
		removeView(mRetryView);
		addView(retryView);
		mRetryView = retryView;
		return mRetryView;

	}

	public View setContentView(View view) {
		view.setVisibility(View.GONE);
		addView(view);
		mContentView = view;
		return mContentView;
	}

	public View getRetryView() {
		return mRetryView;
	}

	public View getLoadingView() {
		return mLoadingView;
	}

	public View getEmptyView() {
		return mEmptyView;
	}
}
