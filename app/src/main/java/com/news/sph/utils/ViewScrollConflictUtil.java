/**
 * 
 */
package com.news.sph.utils;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;

/**
 * 防止滑动冲突的工具类
 */
public class ViewScrollConflictUtil implements OnTouchListener {
	/** 滑动冲突的view **/
	private ViewGroup scrollView;
	/** 滑动冲突-记录滑动距离 **/
	private float xDistance, yDistance;
	/** 滑动冲突-记录按下的坐标 **/
	private float mLastMotionX, mLastMotionY;
	/** 滑动冲突-是否是左右滑动 **/
	private boolean mIsBeingDragged = true;

	public ViewScrollConflictUtil(ViewGroup scrollView) {
		super();
		this.scrollView = scrollView;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		final float x = event.getRawX();
		final float y = event.getRawY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			xDistance = yDistance = 0f;
			mLastMotionX = x;
			mLastMotionY = y;
		case MotionEvent.ACTION_MOVE:
			final float xDiff = Math.abs(x - mLastMotionX);
			final float yDiff = Math.abs(y - mLastMotionY);
			xDistance += xDiff;
			yDistance += yDiff;

			float dx = xDistance - yDistance;
			/** 左右滑动避免和下拉刷新冲突 **/
			if (xDistance > yDistance
					|| Math.abs(xDistance - yDistance) < 0.00001f) {
				mIsBeingDragged = true;
				mLastMotionX = x;
				mLastMotionY = y;
				v.getParent().requestDisallowInterceptTouchEvent(true);
			} else {
				mIsBeingDragged = false;
				v.getParent().requestDisallowInterceptTouchEvent(false);
			}
			break;
		case MotionEvent.ACTION_CANCEL:
			if (mIsBeingDragged) {
				v.getParent().requestDisallowInterceptTouchEvent(false);
			}
		default:
			break;
		}

		return false;
	}

}
