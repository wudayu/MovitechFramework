package com.movitech.framework.fragment;

import com.movitech.framework.views.ProcessingDialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Oct 21, 2014, 7:53:03 PM
 * @Description: BaseFragment是所有Fragment的根类。它使用getActivity方法初始化了mContext作为上下文对象
 *
 **/

public abstract class BaseFragment extends Fragment {

	protected Context mContext = null;
	protected ProcessingDialog processingDialog = null;

	protected abstract View initContainer(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
	protected abstract void initComponents(View fragView);
	protected abstract void initEvents();
	protected abstract void initData();
	protected abstract void afterAllSet();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		mContext = getActivity();

		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View fragView = initContainer(inflater, container, savedInstanceState);

		initComponents(fragView);
		initEvents();
		initData();
		afterAllSet();

		return fragView;
	}

	protected void dismissProcessingDialog() {
		if (processingDialog != null) {
			processingDialog.dismiss();
		}
	}

}
