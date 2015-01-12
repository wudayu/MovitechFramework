package com.movitech.framework.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.movitech.framework.R;
import com.movitech.framework.activity.TestActivity;
import com.movitech.framework.generic.Utils;
import com.movitech.framework.handler.IImageHandler;
import com.movitech.framework.handler.UILImageHandler;
import com.movitech.framework.listener.BannerViewOnItemClickListener;
import com.movitech.framework.views.BannerView;
import com.movitech.framework.views.CountDownView;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Oct 21, 2014, 7:52:32 PM
 * @Description: Test Fragment No.1
 *
 **/

public class TestFirstFragment extends BaseFragment {

	ImageView ivTest = null;
	BannerView bvBanner = null;
	EditText edtTestAutohide = null;
	CountDownView tvCountDown = null;

	IImageHandler imageHandler = null;

	@Override
	protected View initContainer(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_test_first, null);
	}

	@Override
	protected void initComponents(View fragView) {
		edtTestAutohide = (EditText) fragView.findViewById(R.id.edt_test_autohide);
		bvBanner = (BannerView) fragView.findViewById(R.id.bv_banner);
		tvCountDown = (CountDownView) fragView.findViewById(R.id.tv_count_down);
		ivTest = (ImageView) fragView.findViewById(R.id.iv_test);

		imageHandler = UILImageHandler.getInstance(this.getActivity());
	}

	@Override
	protected void initEvents() {
		ivTest.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(TestFirstFragment.this.getActivity(), TestActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void initData() {
	}

	@Override
	protected void afterAllSet() {
		testData();
	}

	private void testData() {
		// HomePageBanner
		List<View> views = new ArrayList<View>();
		ImageView view1 = (ImageView) LayoutInflater.from(this.getActivity()).inflate(R.layout.item_img_banner_view, null);
		imageHandler.loadImage("http://pic1.win4000.com/wallpaper/d/53e85d4307c60.jpg", view1);
		view1.setOnClickListener(new BannerViewOnItemClickListener(this.getActivity(), BannerViewOnItemClickListener.IDENTIFIER_TEST_FIRST));
		ImageView view2 = (ImageView) LayoutInflater.from(this.getActivity()).inflate(R.layout.item_img_banner_view, null);
		imageHandler.loadImage("http://d.hiphotos.baidu.com/image/pic/item/72f082025aafa40fc8e997d5a964034f78f0198e.jpg", view2);
		view2.setOnClickListener(new BannerViewOnItemClickListener(this.getActivity(), BannerViewOnItemClickListener.IDENTIFIER_TEST_SECOND));
		ImageView view3 = (ImageView) LayoutInflater.from(this.getActivity()).inflate(R.layout.item_img_banner_view, null);
		imageHandler.loadImage("http://pic1.win4000.com/wallpaper/f/538eb7e2ee428.jpg", view3);
		view3.setOnClickListener(new BannerViewOnItemClickListener(this.getActivity(), BannerViewOnItemClickListener.IDENTIFIER_TEST_THIRD));
		ImageView view4 = (ImageView) LayoutInflater.from(this.getActivity()).inflate(R.layout.item_img_banner_view, null);
		imageHandler.loadImage("http://e.hiphotos.baidu.com/image/h%3D900%3Bcrop%3D0%2C0%2C1440%2C900/sign=6c166314be315c605c9567efbd8aa861/4ec2d5628535e5dd6bf96ade74c6a7efcf1b62de.jpg", view4);
		view4.setOnClickListener(new BannerViewOnItemClickListener(this.getActivity(), BannerViewOnItemClickListener.IDENTIFIER_TEST_FOURTH));
		ImageView view5 = (ImageView) LayoutInflater.from(this.getActivity()).inflate(R.layout.item_img_banner_view, null);
		imageHandler.loadImage("http://pic1.win4000.com/wallpaper/f/538eb7e2ee428.jpg", view5);
		view5.setOnClickListener(new BannerViewOnItemClickListener(this.getActivity(), BannerViewOnItemClickListener.IDENTIFIER_TEST_FIFTH));
		ImageView view6 = (ImageView) LayoutInflater.from(this.getActivity()).inflate(R.layout.item_img_banner_view, null);
		imageHandler.loadImage("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1301/14/c1/17394579_1358152845572_800x600.jpg", view6);
		view6.setOnClickListener(new BannerViewOnItemClickListener(this.getActivity(), BannerViewOnItemClickListener.IDENTIFIER_TEST_SIXTH));
		views.add(view1);
		views.add(view2);
		views.add(view3);
		views.add(view4);
		views.add(view5);
		views.add(view6);
		bvBanner.setViews(views);
		bvBanner.setRolling(3000);

		// CountDownView
		tvCountDown.setCountDownAndStart(5000, 10);
		tvCountDown.setOnCountDownFinishListener(new CountDownView.OnCountDownFinishListener() {
			@Override
			public void onFinish() {
				if (TestFirstFragment.this.getActivity() != null) {
					Toast.makeText(TestFirstFragment.this.getActivity(), "Done", Toast.LENGTH_LONG).show();
					Utils.autoCloseKeyboard(TestFirstFragment.this.getActivity(), edtTestAutohide);
				}
			}
		});

		// ImageView
		imageHandler.loadImage("http://d.hiphotos.baidu.com/image/pic/item/72f082025aafa40fc8e997d5a964034f78f0198e.jpg", ivTest);
	}

}
