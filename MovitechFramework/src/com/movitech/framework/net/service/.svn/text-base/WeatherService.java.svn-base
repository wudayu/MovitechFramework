package com.movitech.framework.net.service;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

import com.movitech.framework.net.protocol.WeatherResult;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Dec 9, 2014, 11:01:09 AM
 * @Description: WeatherService是用来向气象台请求天气信息的Retrofit的Service
 *
 **/

public interface WeatherService {
	/** Get Weather Info */
	@GET("/{code}.html")
	void getWeather(@Path("code") String code, Callback<WeatherResult> cb);
}
