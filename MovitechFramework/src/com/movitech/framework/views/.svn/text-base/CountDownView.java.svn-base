package com.movitech.framework.views;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Oct 23, 2014, 11:33:15 AM
 * @Description: CountDownView是一个显示倒计时的控件
 *
 **/

public class CountDownView extends LinearLayout {

	private Context mContext = null;
	private CountDownTimer mCountDown = null;
	private OnCountDownFinishListener finishListener = null;

	public CountDownView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public CountDownView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		this.mContext = context;
	}

	public void setCountDownAndStart(long totalMillis, long timeGap) {
		final TextView tvCountDown = new TextView(mContext);
		this.addView(tvCountDown);
		mCountDown = new CountDownTimer(totalMillis, timeGap) {
			@Override
			public void onTick(long millisUntilFinished) {
				long millis = millisUntilFinished / 10 % 100;
				long second = millisUntilFinished / 1000 % 60;
				long min = millisUntilFinished / 1000 / 60 % 60;
				long hour = millisUntilFinished / 1000 / 3600 % 24;

				tvCountDown.setText("" + (hour > 9 ? hour : "0" + hour) + " : " + (min > 9 ? min : "0" + min) + " : " + (second > 9 ? second : "0" + second) + " : " + (millis > 9 ? millis : "0" + millis));
			}

			@Override
			public void onFinish() {
				tvCountDown.setText("00 : 00 : 00 : 00");
				mCountDown = null;
				if (finishListener != null)
					finishListener.onFinish();
			}
		};

		mCountDown.start();
	}

	public interface OnCountDownFinishListener {
		public void onFinish();
	}

	public void setOnCountDownFinishListener(OnCountDownFinishListener finishListener) {
		this.finishListener = finishListener;
	}
}
