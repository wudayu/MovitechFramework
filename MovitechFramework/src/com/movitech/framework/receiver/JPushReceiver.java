package com.movitech.framework.receiver;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.data.JPushLocalNotification;

import com.movitech.framework.activity.TestActivity;
import com.movitech.framework.generic.Utils;


/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Jan 13, 2015, 10:26:21 AM
 * @Description: JPushReceiver是极光推送的自定义PushReceiver
 *
 **/

public class JPushReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
		Utils.debug("[JPush] onReceive(); Action = " + intent.getAction() + "; extras = " + printBundle(bundle));

		/* 对不同的Action进行不同的处理 */
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
    		// 当接收到 注册ID 广播时
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Utils.debug("[JPush] 接收到 注册ID 广播; RegId = " + regId);
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
        	// 当接收到 推送自定义消息 广播时
        	Utils.debug("[JPush] 接收到 推送自定义消息 广播; Message = " + bundle.getString(JPushInterface.EXTRA_MESSAGE));

        	/** 处理自定义消息，如弹出Notification，极为常用 **/
        	processCustomMessage(context, bundle);
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
        	// 当接收到 推送通知 广播时
        	int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Utils.debug("[JPush] 接收到 推送通知 广播; 通知ID = " + notifactionId);
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
        	// 当接收到 用户点开通知 广播时，与
            Utils.debug("[JPush] 接收到 用户点击通知 广播;");

            /** 打开相应的Activity **/
        	openRelevantActivity(context, bundle);
        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
        	// 当接收到 富媒体通知 广播时
            Utils.debug("[JPush] 接收到 富媒体通知 广播; 数据 = " + bundle.getString(JPushInterface.EXTRA_EXTRA));
        } else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
        	// 当接收到 链接改变 广播时
        	boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
        	Utils.debug("[JPush Warning] 链接改变，" + intent.getAction() +" connected state change to " + connected);
        } else {
        	// 当无法处理当前广播时
        	Utils.debug("[JPush Error] 无法处理当前广播" + intent.getAction());
        }
	}

	// 打印所有的Extra数据
	private static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			}else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
				sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
			} 
			else {
				sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
			}
		}
		return sb.toString();
	}

	/** 发送自定义消息到TestActivity */
	private void processCustomMessage(Context context, Bundle bundle) {
		/*
		String title = bundle.getString(JPushInterface.EXTRA_TITLE);
		String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
		if (TextUtils.isEmpty(title)) {
			Utils.debug("Unexpected: empty title (friend). Give up");
			return;
		}

		boolean needIncreaseUnread = true;

		if (title.equalsIgnoreCase("test")) {
			Utils.debug("Message from myself. Give up");
			needIncreaseUnread = false;
			if (!Constant.DEBUG) {
				return;
			}
		}

		String channel = null;
		String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
		try {
			JSONObject extrasJson = new JSONObject(extras);
			channel = extrasJson.optString("KEY_CHANNEL");
		} catch (Exception e) {
			Utils.debug("Unexpected: extras is not a valid json");
		}

		// Send message to UI (Webview) only when UI is up 
		if (TestActivity.isForeground) {
			Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
			msgIntent.putExtra(Constants.KEY_MESSAGE, message);
			msgIntent.putExtra(Constants.KEY_TITLE, title);
			if (null != channel) {
				msgIntent.putExtra(Constants.KEY_CHANNEL, channel);
			}

			JSONObject all = new JSONObject();
			try {
				all.put(Constants.KEY_TITLE, title);
				all.put(Constants.KEY_MESSAGE, message);
				all.put(Constants.KEY_EXTRAS, new JSONObject(extras));
			} catch (JSONException e) {
			}
			msgIntent.putExtra("all", all.toString());

			context.sendBroadcast(msgIntent);
		}

		String chatting = title;
		if (!StringUtils.isEmpty(channel)) {
			chatting = channel;
		}

		String currentChatting = "test02";//MyPreferenceManager.getString(Constants.PREF_CURRENT_CHATTING, null);
		if (chatting.equalsIgnoreCase(currentChatting)) {
			Utils.debug("Is now chatting with - " + chatting + ". Dont show notificaiton.");
			needIncreaseUnread = false;
			if (!Constant.DEBUG) {
				return;
			}
		}

		if (needIncreaseUnread) {
			unreadMessage(title, channel);
		}

		NotificationHelper.showMessageNotification(context, nm, title, message, channel);
		*/
		// 将网络通知变成了本地通知
		JPushLocalNotification ln = new JPushLocalNotification();
		ln.setBuilderId(0);
		ln.setContent("hhh");
		ln.setTitle("ln");
		ln.setNotificationId(11111111) ;
		ln.setBroadcastTime(System.currentTimeMillis() + 1000 * 2);

		Map<String , Object> map = new HashMap<String, Object>() ;
		map.put("name", "jpush") ;
		map.put("test", "111") ;
		JSONObject json = new JSONObject(map) ;
		ln.setExtras(json.toString()) ;
		JPushInterface.addLocalNotification(context, ln);
	}

	/** 打开响应的Activity */
	private void openRelevantActivity(Context context, Bundle bundle) {
		Intent i = new Intent(context, TestActivity.class);
		i.putExtras(bundle);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(i);
	}

}
