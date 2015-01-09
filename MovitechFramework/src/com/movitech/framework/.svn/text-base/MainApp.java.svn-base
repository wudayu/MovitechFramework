package com.movitech.framework;

import android.app.Application;

import com.nostra13.universalimageloader.utils.L;
import com.movitech.framework.handler.IImageHandler;
import com.movitech.framework.handler.UILImageHandler;

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
	}

	/**
	 * 初始化 Universal Image Loader
	 */
	private void initUIL() {
		imageHandler = UILImageHandler.getInstance(this);
		imageHandler.initImageLoader();

		L.writeLogs(false);
	}

}
