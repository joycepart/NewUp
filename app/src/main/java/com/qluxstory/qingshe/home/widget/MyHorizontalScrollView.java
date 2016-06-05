package com.qluxstory.qingshe.home.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.qluxstory.qingshe.home.adapter.HorizontalScrollViewAdapter;

public class MyHorizontalScrollView extends HorizontalScrollView
{

	private static final String TAG = "MyHorizontalScrollView";

	/**
	 * HorizontalListView中的LinearLayout
	 */
	private LinearLayout mContainer;

	/**
	 * mContainer的宽度
	 */
	private int mContainerWidth;

	/**
	 * 数据适配器
	 */
	private HorizontalScrollViewAdapter mAdapter;
	/**
	 * 每屏幕最多显示的个数
	 */
	private int mCountOneScreen;
	/**
	 * 屏幕的宽度
	 */
	private int mScreenWitdh;

	public MyHorizontalScrollView(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		// 获得屏幕宽度
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWitdh = outMetrics.widthPixels;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mContainer = (LinearLayout) getChildAt(0);
	}

	int scroolw=0;

	public int getScroolw() {
		return scroolw;
	}

	public int getCountOneScreen(){
		return mCountOneScreen;
	}
	

	public int getContainerWidth(){
		return mContainerWidth;
	}
	/**
	 * 初始化数据，设置数据适配器
	 * 
	 * @param mAdapter
	 */
	public void initDatas(HorizontalScrollViewAdapter mAdapter)
	{
		this.mAdapter = mAdapter;
		mContainer = (LinearLayout) getChildAt(0);
		// 获得适配器中第一个View
		final View view = mAdapter.getView(0, null, mContainer);
		mContainer.addView(view);


			int w = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
			int h = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
			mContainer.measure(w, h);
			view.measure(w, h);
			mContainerWidth=mContainer.getMeasuredWidth();
			int mChildWidth=view.getMeasuredWidth();
			// 计算每次加载多少个View
			int mCountOneScreen = (mContainerWidth / mChildWidth == 0)?mContainerWidth / mChildWidth+1:mContainerWidth / mChildWidth+2;
			scroolw=mChildWidth*mCountOneScreen;

			//初始化第一屏幕的元素
			initScreenChildren();
	}

	/**
	 * 加载View
	 */
	public void initScreenChildren()
	{
		mContainer = (LinearLayout) getChildAt(0);
		mContainer.removeAllViews();
		
		for (int i = 0; i < mAdapter.getCount(); i++)
		{
			View view = mAdapter.getView(i, null, mContainer);
			
			mContainer.addView(view);
			
		}

		

	}

	

}
