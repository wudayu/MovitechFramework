package com.movitech.framework;

import android.app.Application;
import cn.jpush.android.api.JPushInterface;

import com.movitech.framework.constant.Constant;
import com.movitech.framework.handler.IImageHandler;
import com.movitech.framework.handler.UILImageHandler;
import com.nostra13.universalimageloader.utils.L;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Oct 28, 2014, 4:58:16 PM
 * @Description: MainApp是整个项目使用的Application，其中初始化了一些操作，如果有需要也可以添加一些全局变量
 *
 **/

public class MainApp extends Application {

	IImageHandler imageHandler = null;
	
	@Override
	public void onCreate() {
		super.onCreate();

		initUIL();
		initJPush();
	}

	/**
	 * 初始化 Universal Image Loader
	 */
	private void initUIL() {
		imageHandler = UILImageHandler.getInstance(this);
		imageHandler.initImageLoader();

		L.writeLogs(false);
	}

	/**
	 * 初始化 JPush
	 */
	private void initJPush() {
		JPushInterface.setDebugMode(Constant.DEBUG);
        JPushInterface.init(this);
	}

}
