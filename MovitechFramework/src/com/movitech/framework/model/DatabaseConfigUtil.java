package com.movitech.framework.model;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

/**
 * 
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Mar 4, 2015, 5:04:14 PM
 * @Description: When created a new database model(entity) we need run this method for best performance
 * and in case of an Android project, you have to remove Android Lib from the launch configuration for that specific class.
 * 
 **/
public class DatabaseConfigUtil extends OrmLiteConfigUtil {
	public static void main(String[] args) throws Exception {
		writeConfigFile("db_config.bin");
	}
}