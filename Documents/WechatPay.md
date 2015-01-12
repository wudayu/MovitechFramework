# 微信支付  

### 原理介绍  
1. App通过微信支付的认证之后，即可在Android端调用相关操作。
2. Android端需要获取很多ID，但这些ID不应写在本地，需要由服务端返回，否则安全性较差，维护也不方便。
3. 基于第二条，Android端需要服务器提供一个接口。这个接口的两个传参是：订单ID，价格（人民币）。其返回参数为appId, partnerId, prepayId, noncestr, timeStamp, sign，共6个参数。这部分接口需要服务端人员去做。
4. 获取到参数之后就可以启动微信支付页面。
5. 在微信支付成功后，有两部分回调。第一个回调是在微信服务器的回调，通常这个回调中写入的内容是告诉我们的应用服务器支付已经成功，可以写入数据库相关内容等。第二个回调是Android端的回调类，通过此回调类，Android端可以做一些相关的成功失败界面显示效果等。

### Android端调用  
1. 相关类包括以下几个：  
		1) .activity.TradingResultActivity 这是App本身用来展示支付成功或者失败的界面。  
		2) .constant.PayMode 这是支付方式的常量类。  
		3) .handler.IWechatHandler 这是微信相关方法接口。  
		4) .handler.WechatHandler 这是微信相关方法的实现类。  
		5) .wxapi.WXPayEntryActivity 这是微信的回调类，微信在相关操作完成之后就会调用此类相关方法。  
2. 相关流程  
		1) 在相关页面使用IWechatHandler wechatHandler = WechatHandler.getInstance(mContext);来获取微信操作对象。  
		2) 在使用服务器的接口得到各种ID，之后调用wechatHandler.connectWechatPay()，这是一个耗时过程，当成功之后就会跳转到微信的支付页面。
		3) 在WXPayEntryActivity中，编写相关回调处理即可。