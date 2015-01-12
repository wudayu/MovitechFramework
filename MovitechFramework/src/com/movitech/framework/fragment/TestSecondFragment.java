package com.movitech.framework.fragment;

import java.net.URISyntaxException;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
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
 * @Created Time: Oct 21, 2014, 7:55:16 PM
 * @Description: Test Fragment No.2
 *
 **/

public class TestSecondFragment extends BaseFragment {

	INetHandler netHandler = null;
	TextView tvWeather = null;

	@Override
	protected View initContainer(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_test_second, null);
	}

	@Override
	protected void initComponents(View fragView) {
		tvWeather = (TextView) fragView.findViewById(R.id.tv_weather);

		netHandler = RetrofitNetHandler.getInstance();
	}

	@Override
	protected void initEvents() {
		tvWeather.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				try {
					Intent gaodeMapIntent = new Intent("android.intent.action.VIEW", android.net.Uri.parse("androidamap://showTraffic?sourceApplication=softname&poiid=BGVIS1&lat="+"39.98871"+"&lon="+"116.43234"+"&level=10&dev=0"));
					gaodeMapIntent.setPackage("com.autonavi.minimap");
					@SuppressWarnings("deprecation")
					Intent baiduMapIntent = Intent.getIntent("intent://map/direction?origin=我的位置&destination=latlng:39.98871,116.43234|name:这是目的地&mode=driving#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
					if (gaodeMapIntent.resolveActivity(TestSecondFragment.this.getActivity().getPackageManager()) != null) {
						startActivity(gaodeMapIntent);
					} else if (baiduMapIntent.resolveActivity(TestSecondFragment.this.getActivity().getPackageManager()) != null) {
						startActivity(baiduMapIntent);
					} else {
						Utils.toastMessage(TestSecondFragment.this.getActivity(), "");
					}
				} catch (URISyntaxException e) {
					Utils.toastMessage(TestSecondFragment.this.getActivity(), "");
				}
			}
		});
	}

	@Override
	protected void initData() {
		Drawable testIcon = getResources().getDrawable(R.drawable.ic_launcher);
		testIcon.setBounds(0, 0, testIcon.getIntrinsicHeight(), testIcon.getIntrinsicWidth());
		tvWeather.setCompoundDrawables(testIcon, null, null, null);
		tvWeather.setCompoundDrawablePadding(10);
	}

	@Override
	protected void afterAllSet() {
		processingDialog = new ProcessingDialog(TestSecondFragment.this.getActivity());
		processingDialog.show();
		netHandler.getForWeather(WeatherCityCode.findCityCodeByCityName("苏州"), new Callback<WeatherResult>() {
			@Override
			public void success(WeatherResult result, Response response) {
				tvWeather.setText("SUCCESS: " + result);
				dismissProcessingDialog();
			}
			@Override
			public void failure(RetrofitError error) {
				RetrofitNetHandler.toastNetworkError(TestSecondFragment.this.getActivity(), error);
				dismissProcessingDialog();
			}
		});
	}
}
