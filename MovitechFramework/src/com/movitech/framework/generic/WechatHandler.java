package com.movitech.framework.generic;

import android.content.Context;

import com.movitech.framework.constant.Constant;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Jan 12, 2015, 9:46:09 AM
 * @Description: David Wu created this file.
 *
 **/

public class WechatHandler {

	/** Generate Singleton */
	private static final WechatHandler instance = new WechatHandler();
	private static IWXAPI api = null;

	private WechatHandler() {}

	public static WechatHandler getInstance(Context context) {
		api = WXAPIFactory.createWXAPI(context, Constant.WX_APP_ID);
		api.registerApp(Constant.WX_APP_ID);

		return instance;
	}

	/** 链接微信支付 耗时操作 */
	public boolean connectWechatPay(String appId, String partnerId, String prepayId, String noncestr, String timeStamp, String sign) {
		PayReq req = new PayReq();
		req.appId = appId;
		req.partnerId = partnerId;
		req.prepayId = prepayId;
		req.nonceStr = noncestr;
		req.timeStamp = timeStamp;
		req.packageValue = "Sign=WXPay";
		req.sign = sign;

		//启动微信支付接口
		boolean succ = api.sendReq(req);

		return succ;
	}

}