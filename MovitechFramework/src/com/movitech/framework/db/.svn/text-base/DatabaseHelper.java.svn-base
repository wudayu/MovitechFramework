package com.movitech.framework.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.movitech.framework.R;
import com.movitech.framework.model.DafUser;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Mar 3, 2015, 5:44:00 PM
 * @Description: David Wu created this file
 *
 **/
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	private static final String DATABASE_NAME = "movitech_emb.db";
	private static final int DATABASE_VERSION = 1;
	// private Context mContext;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.db_config);
		// mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(DatabaseHelper.class.getName(),
					"--------begin creating database tables--------");
			TableUtils.createTable(connectionSource, DafUser.class);
			Log.i(DatabaseHelper.class.getName(),
					"--------end creating database tables--------");
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		// upgrade db to version 2
		switch (oldVersion) {
		case 1:
//			upgrade_1_2();
			break;
		default:
			break;
		}

	}

	/**
	 * 数据库版本从1升级到2操作
	 
	private void upgrade_1_2() {
		try {
			Dao<DuduContact, Integer> dao = getDao(DafWeather.class);
			dao.executeRaw("ALTER TABLE `DuduContact` ADD COLUMN remark VARCHAR(200);");
			dao.executeRaw("UPDATE `DuduContact` SET  remark = dudu_nickname;");
			// 触发联系人操作
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

}
