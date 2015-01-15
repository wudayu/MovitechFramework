# 极光推送  

### 原理介绍  
1. App首先需要申请相应的极光推送账号，并将包名等应用信息写入在账号里创建的应用的信息中，生成AppKey。
2. 在App开发时将各个组件按照规则写好。
3. 在服务端发送自定义消息后，App端会接收到此次Message，之后到达自定义的JPushReceiver的onReceive中，此时会根据Action使用JPushLocalNotification，将获取到的消息展示出来，并定义这个Notification的Extras。此时若用户点击了这个Notification则会再次跳入onReceive中，这时会根据Action和刚刚写入的Extras来决定之后做什么事情，一般是打开一个Activity之类的。

### Android端调用  
1. 相关类包括以下几个：  
		1) .activity.TestActivity 这是用来展示点击收到的推送后打开的页面。  
		2) .constant.PayMode 这是支付方式的常量类。  
		3) .receiver.JPushReceiver 这是极光推送处理的Receiver，大部分推送逻辑在这里完成。  
		4) .MainApp 在这个app的Application中完成JPush初始化。  
		5) AndroidManifest.xml 这里记录了JPush配置信息，包括：响应权限，必须注册的JPush组件，自定义Receiver的注册，以及AppKey。  
2. 相关流程  
		1) 首先在以上几个文件中创建相应的代码和组件。  
		2) 在.receiver.JPushReceiver中的onReceive方法中对不同的类型的情况进行处理即可。