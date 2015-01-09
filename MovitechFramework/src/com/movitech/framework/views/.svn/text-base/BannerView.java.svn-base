package com.movitech.framework.views;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.movitech.framework.R;
import com.movitech.framework.adapter.ViewPagerAdapter;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Oct 22, 2014, 9:58:24 AM
 * @Description: BannerView是用来展示广告的Banner控件，通过setRolling函数能够设置它的自动滚动机制
 *
 **/

public class BannerView extends RelativeLayout {

	private Context mContext = null;
	private SwitchViewPager mViewPager = null;
	private DotPageIndicator mIndicator = null;
	private ViewPagerAdapter mAdapter = null;

	public BannerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.mContext = context;
		init();
	}

	public BannerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		init();
	}

	public BannerView(Context context) {
		super(context);
		this.mContext = context;
		init();
	}

	private void init() {
		this.mAdapter = new ViewPagerAdapter();

		this.mViewPager = new SwitchViewPager(this.mContext);
		this.mIndicator = new DotPageIndicator(this.mContext);
		initLayouts();

		this.mViewPager.setAdapter(this.mAdapter);
		this.mIndicator.setViewPager(this.mViewPager);
	}

	private void initLayouts() {
		RelativeLayout.LayoutParams lpViewPager = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		this.addView(mViewPager, lpViewPager);

		RelativeLayout.LayoutParams lpIndicator = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, mContext.getResources().getDimensionPixelSize(R.dimen.dp06));
		lpIndicator.bottomMargin = mContext.getResources().getDimensionPixelSize(R.dimen.dp04);
		lpIndicator.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		this.addView(mIndicator, lpIndicator);
	}

	/**
	 * get all views list
	 * @return views list
	 */
	public List<View> getViews() {
		return this.mAdapter.getViews();
	}

	/**
	 * set views which need display
	 * @param views
	 */
	public void setViews(List<View> views) {
		this.mAdapter.addAll(views);

		mAdapter.notifyDataSetChanged();
	}

	/**
	 * add a single view
	 */
	public void addView(View view) {
		this.mAdapter.addView(view);

		mAdapter.notifyDataSetChanged();
	}

	/**
	 * set BannerView into rolling mode
	 * @param timeGap，Banner rolling time gap in millisecond
	 */
	public void setRolling(int timeGap) {
		this.mViewPager.setTimeGap(timeGap);
		this.mViewPager.setRollable(true);
	}

}
