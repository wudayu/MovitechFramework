package com.movitech.framework.net;

import retrofit.Callback;

import com.movitech.framework.net.protocol.DafStringResult;
import com.movitech.framework.net.protocol.DafUserResult;
import com.movitech.framework.net.protocol.WeatherResult;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Dec 9, 2014, 10:14:43 AM
 * @Description: INetHandler是网络访问接口，其中还包括了网络地址等信息
 *
 **/

public interface INetHandler {

	/** Schema */
	public static final String PREFIX_HTTP = "http://";
	public static final String PREFIX_HTTPS = "https://";
	/** Test Address & Official Address */
	public static final String SERVER_URL_TEST = "121.41.23.220:8099/broker";
	public static final String SERVER_URL_OFFICAL_PRE = "";
	public static final String SERVER_URL_OFFICAL = "";
	/** Default Server Address Prefix */
	public static final String PREFIX_DEFAULT = PREFIX_HTTP;
	/** Server URL Names TODO Switch to the last one when publish */
	public static final String SERVER_URL_NAME = SERVER_URL_TEST;
//	public static final String SERVER_URL_NAME = SERVER_URL_OFFICAL_PRE;
//	public static final String SERVER_URL_NAME = SERVER_URL_OFFICAL;

	/** Used by the Class which implements INetHandler */
	public static final String SERVER_URL_FOR_RETROFIT = PREFIX_DEFAULT + SERVER_URL_NAME;
	public static final String SERVER_URL_WEATHER = "http://www.weather.com.cn/data/cityinfo";
	public static final String CONTANT_CODE = "UTF-8";
	public static final String UPLOAD_PIC_FILE_KEY = "attachFile";
	/** Http Headers
	public static final String HEADER_CLIENT_SESSION = "client-session";
	public static final String HEADER_CLIENT_VERSION = "client-version";
	public static final String HEADER_API_VERSION = "api-version";
	public static final String HEADER_CONTENT_TYPE = "Content-Type";
	public static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";
	public static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
	public static final String HEADER_CONNECTION = "Connection";
	public static final String HEADER_REGION_CODE = "Region-Code";
	public static final String HEADER_HOST = "Host";
	*/


	/*****************
	 * Methods Below *
	 *****************/

	/** Get Weather Info */
	public void getForWeather(String code, Callback<WeatherResult> cb);

	/** Get User Info */
	public void getForUserInfo(String userId, Callback<DafUserResult> cb);

	/** Upload Picture */
	public void postForUploadPic(String relationId, String imagePath, Callback<DafStringResult> cb);

}
