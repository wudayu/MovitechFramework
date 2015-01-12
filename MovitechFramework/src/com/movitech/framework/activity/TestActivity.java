package com.movitech.framework.activity;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.widget.TextView;

import com.movitech.framework.R;
import com.movitech.framework.constant.WeatherCityCode;
import com.movitech.framework.generic.Utils;
import com.movitech.framework.net.INetHandler;
import com.movitech.framework.net.RetrofitNetHandler;
import com.movitech.framework.net.protocol.WeatherResult;
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

	TextView tvTest = null;
	INetHandler netHandler = RetrofitNetHandler.getInstance();

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
	}

	@Override
	protected void afterAllSet() {
		processingDialog = new ProcessingDialog(this);
		processingDialog.show();
		netHandler.getForWeather(WeatherCityCode.findCityCodeByCityName("苏州"), new Callback<WeatherResult>() {
			@Override
			public void success(WeatherResult result, Response response) {
				tvTest.setText("SUCCESS; HEADERS: " + response.getHeaders() + ";\n\n BODY: " + response.getBody());
				Utils.toastMessage(TestActivity.this, "" + result);
				dismissProcessingDialog();
			}
			@Override
			public void failure(RetrofitError error) {
				RetrofitNetHandler.toastNetworkError(TestActivity.this, error);
				dismissProcessingDialog();
			}
		});
	}
}
