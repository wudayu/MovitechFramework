package com.movitech.framework.net.service;

import retrofit.Callback;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;

import com.movitech.framework.model.TypedImage;
import com.movitech.framework.net.INetHandler;
import com.movitech.framework.net.protocol.DafStringResult;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Dec 10, 2014, 3:47:03 PM
 * @Description: ImageService是用来向上传图片的Retrofit的Service
 *
 **/

public interface ImageService {
	/** Upload Picture */
	@Multipart
	@POST("/rest/broker/updateBrokerPic")
	void uploadPic(@Query("id") String relationId, @Part(INetHandler.UPLOAD_PIC_FILE_KEY) TypedImage imageResource, Callback<DafStringResult> cb);
}
