package com.movitech.framework.listener;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Oct 22, 2014, 8:09:05 PM
 * @Description: BannerViewOnItemClickListener是显示各种滚动广告的控件的监听器，可以对不同类型的广告进行不同的处理
 *
 **/

public class BannerViewOnItemClickListener implements OnClickListener {

	private Context mContext;
	private int mPageIdentifier;

	public static final int IDENTIFIER_TEST_FIRST = 101;
	public static final int IDENTIFIER_TEST_SECOND = 102;
	public static final int IDENTIFIER_TEST_THIRD = 103;
	public static final int IDENTIFIER_TEST_FOURTH = 104;
	public static final int IDENTIFIER_TEST_FIFTH = 105;
	public static final int IDENTIFIER_TEST_SIXTH = 106;

	public BannerViewOnItemClickListener(Context context, int pageIdentifier) {
		this.mContext = context;
		this.mPageIdentifier = pageIdentifier;
	}

	@Override
	public void onClick(View v) {
		switch (mPageIdentifier) {
		case IDENTIFIER_TEST_FIRST:
			Toast.makeText(mContext, "Banner first", Toast.LENGTH_SHORT).show();
			break;
		case IDENTIFIER_TEST_SECOND:
			Toast.makeText(mContext, "Banner second", Toast.LENGTH_SHORT).show();
			break;
		case IDENTIFIER_TEST_THIRD:
			Toast.makeText(mContext, "Banner third", Toast.LENGTH_SHORT).show();
			break;
		case IDENTIFIER_TEST_FOURTH:
			Toast.makeText(mContext, "Banner fourth", Toast.LENGTH_SHORT).show();
			break;
		case IDENTIFIER_TEST_FIFTH:
			Toast.makeText(mContext, "Banner fifth", Toast.LENGTH_SHORT).show();
			break;
		case IDENTIFIER_TEST_SIXTH:
			Toast.makeText(mContext, "Banner sixth", Toast.LENGTH_SHORT).show();
			break;
		}
	}
}
