package com.movitech.framework.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Oct 23, 2014, 3:27:51 PM
 * @Description: BaseViewPager继承于support.v4.view.ViewPager。可以修改ViewPager切换时滚动的速度，但这部分代码暂时被注释了
 *
 **/

public class BaseViewPager extends ViewPager {

	public BaseViewPager(Context context) {
		super(context);
	}

	public BaseViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/* ViewPager rolling time code
	void init() {
		try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(this.getContext(),
                    new AccelerateInterpolator());
            field.set(this, scroller);
            scroller.setmDuration(200);
        } catch (Exception e) {
            Log.e("BaseViewPager", e.toString());
        }
	}

	class FixedSpeedScroller extends Scroller {
	    private int mDuration = 1500;

	    public FixedSpeedScroller(Context context) {
	        super(context);
	    }

	    public FixedSpeedScroller(Context context, Interpolator interpolator) {
	        super(context, interpolator);
	    }

	    @Override
	    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
	        // Ignore received duration, use fixed one instead
	        super.startScroll(startX, startY, dx, dy, mDuration);
	    }

	    @Override
	    public void startScroll(int startX, int startY, int dx, int dy) {
	        // Ignore received duration, use fixed one instead
	        super.startScroll(startX, startY, dx, dy, mDuration);
	    }

	    public void setmDuration(int time) {
	        mDuration = time;
	    }

	    public int getmDuration() {
	        return mDuration;
	    }
	}
	*/
}
