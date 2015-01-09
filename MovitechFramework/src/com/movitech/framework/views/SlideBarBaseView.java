package com.movitech.framework.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.movitech.framework.R;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Oct 21, 2014, 11:34:58 AM
 * @Description: SlideBarBaseView是用来在数据中有一系列字母开头的情况下通过滑动选择响应字母数据的控件，一般位于屏幕右侧
 *
 **/

public class SlideBarBaseView extends View {

	public static final char[] DEFAULT_INDEX = { 'A', 'B', 'C', 'D', 'E', 'F',
			'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	public static final char[] SPECIAL_CHAR_INDEX = { 'A', 'B', 'C', 'D', 'E', 'F',
		'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
		'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '#' };

	private char[] indexs = SPECIAL_CHAR_INDEX;

	Paint mPaint = null;

	public interface OnSlideBarBaseViewFlipListener {

		void onFlip(int index, String mChar);

		void onFlipUp();
	}

	private OnSlideBarBaseViewFlipListener flipListener = null;

	/**
	 * Set SlideBar OnFlip Listener
	 * 
	 * @param mListener
	 */
	public void setFlipListener(OnSlideBarBaseViewFlipListener mListener) {
		this.flipListener = mListener;
	}

	public SlideBarBaseView(Context context) {
		super(context);
		init();
	}

	public SlideBarBaseView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public SlideBarBaseView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		mPaint = new Paint();
		mPaint.setTextSize(getResources().getDimensionPixelOffset(
				R.dimen.px24));
		mPaint.setTextAlign(Paint.Align.CENTER);
		mPaint.setColor(Color.parseColor("#FF136AFF"));
		mPaint.setTypeface(Typeface.DEFAULT_BOLD);
		mPaint.setAntiAlias(true);
	}

	public void setIndexs(char[] wantedIndexs) {
		this.indexs = wantedIndexs;
		invalidate();
	}

	public void setTextColor(int color) {
		mPaint.setColor(color);
	}

	protected void onDraw(Canvas canvas) {
		for (int i = 0; i < indexs.length; i++) {
			float xPos = getWidth() / 2;
			float yPos = (getHeight() / indexs.length) * i
					+ (getHeight() / indexs.length);
			canvas.drawText(String.valueOf(indexs[i]), xPos, yPos, mPaint);
		}
		super.onDraw(canvas);
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float touchY = event.getY();

		float everyHeight = ((float) getHeight() / indexs.length);

		int touchId = (int) (touchY / everyHeight);

		if (touchId < 0) {
			touchId = 0;
		}

		if (touchId > indexs.length - 1) {
			touchId = indexs.length - 1;
		}

		if (event.getAction() == MotionEvent.ACTION_DOWN
				|| event.getAction() == MotionEvent.ACTION_MOVE) {
			if (this.flipListener != null) {
				this.flipListener.onFlip(touchId,
						String.valueOf(indexs[touchId]));
			}
		} else {
			if (this.flipListener != null) {
				this.flipListener.onFlipUp();
			}
		}
		return true;
	}

}
