package com.movitech.framework.wxapi;

import android.content.Intent;
import android.os.Bundle;

import com.movitech.framework.activity.BaseActivity;
import com.movitech.framework.constant.Constant;
import com.movitech.framework.generic.Utils;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelpay.PayResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Jan 9, 2015, 4:25:05 PM
 * @Description: 这是微信支付的回调类
 *
 **/

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {
	
    private IWXAPI api;
	private PayResp payResp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    	api = WXAPIFactory.createWXAPI(this, Constant.WX_APP_ID);
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	// 微信返回后接收返回代码，做该做的处理
	@Override
	public void onResp(BaseResp resp) {
		payResp = ((PayResp) resp);

		Utils.debug(Utils.TAG, "onPayFinish, errCode = " + resp.errCode);

		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			switch (resp.errCode) {
			// 认证被否决
			case BaseResp.ErrCode.ERR_AUTH_DENIED:
				payFail();
				break;
			// 一般错误
			case BaseResp.ErrCode.ERR_COMM:
				payFail();
				break;
			// 正确返回
			case BaseResp.ErrCode.ERR_OK:
				paySuccess();
				break;
			// 发送失败
			case BaseResp.ErrCode.ERR_SENT_FAILED:
				payFail();
				break;
			// 不支持错误
			case BaseResp.ErrCode.ERR_UNSUPPORT:
				payFail();
				break;
			// 用户取消
			case BaseResp.ErrCode.ERR_USER_CANCEL:
				userCanceled();
				break;
			// 其他情况（一般不可能使用以下代码）
			default:
				payFail();
				break;
			}
		}
	}

	/** 用户取消处理方法 */
	private void userCanceled() {
		this.finish();
	}

	/** 成功处理方法 */
	private void paySuccess() {
		closePayOrder();
//		Intent intent = new Intent(this, TradingResultActivity_.class);
//		intent.putExtra(ExtraNames.IS_PAY_SUCCESSED, true);
//		intent.putExtra(ExtraNames.PAY_NUM, payResp.prepayId);//微信服务器返回 订单ID
//		intent.putExtra(ExtraNames.PAY_MODE, PayMode.WEI_XIN);
//		startActivity(intent);
		this.finish();
	}

	/** 失败处理方法 */
	private void payFail() {
		closePayOrder();
//		Intent intent = new Intent(this, TradingResultActivity_.class);
//		intent.putExtra(ExtraNames.IS_PAY_SUCCESSED, false);
//		startActivity(intent);
		this.finish();
	}

	private void closePayOrder() {
//		Intent intent = new Intent(BroadcastActions.FINISH_PAY_ORDER);
//		LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
	}

	@Override
	protected void initContainer() {
	}

	@Override
	protected void initComponents() {
	}

	@Override
	protected void initEvent() {
	}

	@Override
	protected void initData() {
	}

	@Override
	protected void afterAllSet() {
	}
}