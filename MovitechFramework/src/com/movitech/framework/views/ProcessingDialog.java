package com.movitech.framework.views;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.movitech.framework.R;

/**
*
* @author: Wu Dayu
* @En_Name: David Wu
* @E-mail: wudayu@gmail.com
* @Created Time: Oct 21, 2014, 11:09:16 AM
* @Description: ProcessingDialog是一个AlertDialog，当应用后台有任务的时候用来显示加载信息
*
**/

public class ProcessingDialog extends AlertDialog {

	private String mMessage = null;

	public ProcessingDialog(Context context, String message, boolean cancelable, OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);

		this.mMessage = message;
	}

	public ProcessingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}

	public ProcessingDialog(Context context, int theme) {
		super(context, theme);
	}

	public ProcessingDialog(Context context) {
		super(context);
	}

	@Override
	public void show() {
		super.show();

		setContentView(R.layout.layout_davidwu_processing_dialog);

		TextView tvMessage = (TextView) findViewById(R.id.iv_message);
		if (mMessage != null)
			tvMessage.setText(mMessage);

		ImageView ivCircle = (ImageView) findViewById(R.id.iv_processing);
		ivCircle.setBackgroundResource(R.drawable.processing_dialog_animation);
		AnimationDrawable frameAnimation = (AnimationDrawable) ivCircle.getBackground();
		frameAnimation.start();
	}

}
