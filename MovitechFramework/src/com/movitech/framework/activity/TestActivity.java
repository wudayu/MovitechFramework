package com.movitech.framework.activity;

import android.widget.TextView;
import cn.jpush.android.api.JPushInterface;

import com.movitech.framework.R;
import com.movitech.framework.generic.Utils;
import com.movitech.framework.handler.DataHandler;
import com.movitech.framework.handler.IDataHandler;
import com.movitech.framework.handler.IDataHandler.DataCallback;
import com.movitech.framework.model.DafUser;
import com.movitech.framework.views.ProcessingDialog;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Dec 9, 2014, 10:24:43 AM
 * @Description: TestActivity用来测试某些功能
 *
 **/

public class TestActivity extends BaseActivity {

	public static boolean isForeground = false;

	TextView tvTest = null;
	IDataHandler dataHandler = null;

	@Override
	protected void initContainer() {
		setContentView(R.layout.activity_test);
	}

	@Override
	protected void initComponents() {
		tvTest = (TextView) findViewById(R.id.tv_test);
	}

	@Override
	protected void initEvents() {
	}

	@Override
	protected void initData() {
		dataHandler = DataHandler.getInstance(this);
	}

	@Override
	protected void afterAllSet() {
		processingDialog = new ProcessingDialog(this);
		processingDialog.show();
		dataHandler.getForUserInfo("9f35c28e869f42aba79d6a64211ce1a2", new DataCallback<DafUser>() {
			@Override
			public void onSuccess(DafUser object) {
				Utils.debug("DafUser = " + object);
				dismissProcessingDialog();
			}

			@Override
			public void onFailure(String errInfo, Exception excep) {
				Utils.debug("errInfo = " + errInfo);
				dismissProcessingDialog();
			}
		});
	}

	@Override
	protected void onResume() {
		isForeground = true;
		JPushInterface.onResume(this);

		super.onResume();
	}

	@Override
	protected void onPause() {
		isForeground = false;
		JPushInterface.onPause(this);

		super.onPause();
	}

}
