package com.movitech.framework.db;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.movitech.framework.R;
import com.movitech.framework.generic.Utils;
import com.movitech.framework.model.DafUser;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Mar 3, 2015, 10:24:54 AM
 * @Description: David Wu created this file
 *
 **/
public class OrmliteDbHandler implements IDbHandler {

	private static Context sContext = null;

	/** Generate the Singleton */
	private final static IDbHandler instance = new OrmliteDbHandler();

	private OrmliteDbHandler() {};

	public static IDbHandler getInstance(Context context) {
		sContext = context;

		return instance;
	}

	DatabaseHelper mDatabaseHelper = null;

	private DatabaseHelper getHelper() {
	    if (mDatabaseHelper == null) {
	    	mDatabaseHelper = OpenHelperManager.getHelper(sContext, DatabaseHelper.class);
	    }

	    return mDatabaseHelper;
	}

	@Override
	public void getForUserInfo(String userId, DataCallback<DafUser> dcb) {
		DatabaseHelper dbHelper = getHelper();
		try {
			RuntimeExceptionDao<DafUser, Integer> userDao = RuntimeExceptionDao.createDao(dbHelper.getConnectionSource(), DafUser.class);
			List<DafUser> users = userDao.queryForEq(DafUser.USER_ID, userId);
			if (users != null && users.size() > 0) {
				dcb.onSuccess(users.get(0));
			} else {
				dcb.onFailure(sContext.getString(R.string.error_local_database_has_no_data), null);
			}
		} catch (SQLException e) {
			dcb.onFailure(sContext.getString(R.string.error_local_database_connot_be_connected), null);
			e.printStackTrace();
		};
	}

	@Override
	public void setForUserInfo(DafUser user) {
		DatabaseHelper dbHelper = getHelper();
		try {
			RuntimeExceptionDao<DafUser, Integer> userDao = RuntimeExceptionDao.createDao(dbHelper.getConnectionSource(), DafUser.class);
			if (user != null) {
				// 有数据则添加或更新
				userDao.createOrUpdate(user);
			} else {
				// 没数据则清空表，保持与服务器统一
				userDao.deleteBuilder().delete();
			}
		} catch (SQLException e) {
			Utils.debug("--:(------can not connect database---");
			e.printStackTrace();
		};
	}

}
