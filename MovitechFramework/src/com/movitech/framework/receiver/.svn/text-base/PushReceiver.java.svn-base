package com.movitech.framework.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.movitech.framework.constant.Constant;
import com.movitech.framework.service.PushService;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Nov 19, 2014, 4:19:16 PM
 * @Description: PushReceiver是用来唤醒PushService的广播。它们是一对，通过向服务器拉取推送来进行推送显示的，PushService的开关是Constant.needPush
 *
 **/

public class PushReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (Constant.needPush) {
			Intent pushServiceIntent = new Intent(context, PushService.class);
			context.startService(pushServiceIntent);
		}
	}

}
