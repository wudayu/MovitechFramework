package com.movitech.framework.net.service;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

import com.movitech.framework.net.protocol.DafUserResult;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Dec 10, 2014, 3:29:18 PM
 * @Description: UserService是用来完成用户相关的工作的Retrofit的Service
 *
 **/

public interface UserService {
	/** Get User Info */
	@GET("/rest/broker/getBrokerInfo")
	void getUser(@Query("id") String userId, @Query("fromApp") String fromApp, Callback<DafUserResult> cb);
}
