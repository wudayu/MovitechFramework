package com.movitech.framework.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;

import com.movitech.framework.constant.BroadcastActions;
import com.movitech.framework.generic.Utils;
import com.movitech.framework.views.ProcessingDialog;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Oct 20, 2014, 9:52:39 PM
 * @Description: BaseActivity是一切Activity的父类，它包括一个用来关闭所有Activity的本地广播。除此之外，如果我们希望让所有Activity都做某件事情的话，我们需要将其放在BaseActivity中去做
 *
 **/

public abstract class BaseActivity extends FragmentActivity {

	protected Context mContext;
	protected ProcessingDialog processingDialog = null;

	/** 初始化界面容器 */
	protected abstract void initContainer();
	/** 初始化控件 */
	protected abstract void initComponents();
	/** 初始化事件 */
	protected abstract void initEvents();
	/** 初始化数据 */
	protected abstract void initData();
	/** 在一切初始化结束后的程序入口 */
	protected abstract void afterAllSet();

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		mContext = BaseActivity.this;

		initContainer();
		initComponents();
		initEvents();
		initData();
		afterAllSet();

		// 注册 关闭所有Activity 广播
		LocalBroadcastManager.getInstance(mContext).registerReceiver(finishReceiver, new IntentFilter(BroadcastActions.FINISH_ACTIVITY));
	}

	private BroadcastReceiver finishReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			finish();
		}
	};

	@Override
	protected void onDestroy() {
		// 注销 关闭所有Activity 广播
		try {
			LocalBroadcastManager.getInstance(mContext).unregisterReceiver(finishReceiver);
		} catch (Exception e) {
			Utils.debug("BaseActivity : " + e.toString());
		}

		super.onDestroy();
	}

	protected void closeAllActivity() {
		LocalBroadcastManager.getInstance(BaseActivity.this).sendBroadcast(new Intent(BroadcastActions.FINISH_ACTIVITY));
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	protected void dismissProcessingDialog() {
		if (processingDialog != null) {
			processingDialog.dismiss();
		}
	}
}
