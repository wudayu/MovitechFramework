package com.movitech.framework.handler;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;

import com.movitech.framework.db.IDbHandler;
import com.movitech.framework.db.OrmliteDbHandler;
import com.movitech.framework.model.DafUser;
import com.movitech.framework.net.INetHandler;
import com.movitech.framework.net.RetrofitNetHandler;
import com.movitech.framework.net.protocol.DafUserResult;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Mar 3, 2015, 10:26:49 AM
 * @Description: David Wu created this file
 *
 **/
public class DataHandler implements IDataHandler {

	private static Activity sContext = null;
	private static INetHandler sNetHandler = null;
	private static IDbHandler sDbHandler = null;

	/** Generate the Singleton */
	private static final IDataHandler instance = new DataHandler();

	private DataHandler() {};

	public static IDataHandler getInstance(Activity context) {
		sContext = context;
		sNetHandler = RetrofitNetHandler.getInstance();
		sDbHandler = OrmliteDbHandler.getInstance(context);
		
		return instance;
	}

	@Override
	public void getForUserInfo(final String userId, final DataCallback<DafUser> dcb) {
		Callback<DafUserResult> cbRetrofit = new Callback<DafUserResult>() {
			@Override
			public void failure(RetrofitError error) {
				RetrofitNetHandler.toastNetworkError(sContext, error);
				// 网络无法连接，连接本地数据库
				sDbHandler.getForUserInfo(userId, dcb);
			}
			@Override
			public void success(DafUserResult result, Response response) {
				// 获取到数据，首先写入数据库
				DafUser user = result.getObjValue();
				sDbHandler.setForUserInfo(user);
				dcb.onSuccess(user);
			}
		};
		sNetHandler.getForUserInfo(userId, cbRetrofit);
	}

}
