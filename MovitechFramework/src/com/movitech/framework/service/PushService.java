package com.movitech.framework.service;

import java.util.Timer;
import java.util.TimerTask;

import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.movitech.framework.MainApp;
import com.movitech.framework.net.INetHandler;
import com.movitech.framework.net.RetrofitNetHandler;
import com.movitech.framework.receiver.PushReceiver;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Nov 18, 2014, 5:01:46 PM
 * @Description: PushService是通过向服务器拉取推送来进行推送显示的，PushService的开关是Constant.needPush
 *
 **/

public class PushService extends Service {

	INetHandler netHandler = RetrofitNetHandler.getInstance();
	NotificationManager notificationManager = null;
	MainApp mApp = null;

	Timer timer = null;

	public static final long PUSH_GAP = 5000;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		initValues();
		registerBroadcast();

		timer = new Timer();
		timer.schedule(new PushTimerTask(this), 2000, PUSH_GAP);

		super.onCreate();
	}

	private void initValues() {
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mApp = (MainApp) getApplication();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		flags = START_STICKY;
		return super.onStartCommand(intent, flags, startId);
	}

	private void registerBroadcast() {
		IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);

		filter.addAction(Intent.ACTION_BOOT_COMPLETED);
		filter.addAction(Intent.ACTION_BATTERY_CHANGED);
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_USER_PRESENT);

		BroadcastReceiver mReceiver = new PushReceiver();

		registerReceiver(mReceiver, filter);
	}


	class PushTimerTask extends TimerTask {

		Context context;
		int i;

		public PushTimerTask(Context context) {
			this.context = context;
			this.i = 0;
		}

		@Override
		public void run() {
			/*
			String keyCode = "testKeyCode";

			NotificationCompat.Builder mBuilder =
			        new NotificationCompat.Builder(context)
					.setAutoCancel(true)
			        .setSmallIcon(R.drawable.ic_launcher)
			        .setContentTitle(getString(R.string.app_name))//getString(R.string.app_name))
			        .setContentText("Current = " + i++);

			Intent resultIntent = new Intent(context, MainActivity_.class);
			resultIntent.putExtra(ExtraNames.PUSH_TYPE, keyCode);

			TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
			stackBuilder.addNextIntent(resultIntent);
			PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
			mBuilder.setContentIntent(resultPendingIntent);
		
			notificationManager.notify(0x1022, mBuilder.build());
			*/

			// toast(i++);
		}
	}

	@Override
	public void onDestroy() {
		Intent pushIntent = new Intent("com.movitech.framework.push");
		sendBroadcast(pushIntent);

		super.onDestroy();
	}

}
