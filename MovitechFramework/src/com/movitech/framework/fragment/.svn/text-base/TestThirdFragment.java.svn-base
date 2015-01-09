package com.movitech.framework.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.movitech.framework.R;
import com.movitech.framework.constant.ImageLoaderHelper;
import com.movitech.framework.constant.ReqCode;
import com.movitech.framework.generic.Utils;
import com.movitech.framework.handler.IImageHandler;
import com.movitech.framework.handler.UILImageHandler;
import com.movitech.framework.model.DafUser;
import com.movitech.framework.net.INetHandler;
import com.movitech.framework.net.RetrofitNetHandler;
import com.movitech.framework.net.protocol.DafStringResult;
import com.movitech.framework.net.protocol.DafUserResult;
import com.movitech.framework.views.ProcessingDialog;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Oct 21, 2014, 7:55:48 PM
 * @Description: Test Fragment No.3
 *
 **/

public class TestThirdFragment extends BaseFragment {

	public static final String CURR_USER_ID = "4fef1bb5822e47ca9453443f7fa4820c";

	ImageView ivHeader = null;

	IImageHandler imageHandler = null;
	INetHandler netHandler = null;

	String takePicturePath = null;
	String cuttedImagePath = null;
	List<String> filePathes = new ArrayList<String>();
	String uploadedUUid = null;
	int currPicIndex = -1;

	DafUser currUser = null;

	@Override
	protected View initContainer(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_test_third, null);
	}

	@Override
	protected void initComponents(View fragView) {
		ivHeader = (ImageView) fragView.findViewById(R.id.iv_header);

		imageHandler = UILImageHandler.getInstance(getActivity());
		netHandler = RetrofitNetHandler.getInstance();
	}

	@Override
	protected void initEvent() {
		ivHeader.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				takePicturePath = imageHandler.getNewTmpImagePath();
				imageHandler.selectGetImageWay(TestThirdFragment.this.getActivity(), ivHeader, takePicturePath);
			}
		});
	}

	@Override
	protected void initData() {
	}

	@Override
	protected void afterAllSet() {
		processingDialog = new ProcessingDialog(this.getActivity(), true, null);
		processingDialog.show();

		netHandler.getForUserInfo(CURR_USER_ID, new Callback<DafUserResult>() {
			@Override
			public void success(DafUserResult result, Response response) {
				Utils.debug("result = " + result);
				imageHandler.loadHeaderImage(result.getResultSuccess() ? result.getObjValue().getPhotosrc() : "", ivHeader);
				dismissProcessingDialog();
			}
			@Override
			public void failure(RetrofitError error) {
				RetrofitNetHandler.toastNetworkError(TestThirdFragment.this.getActivity(), error);
				dismissProcessingDialog();
			}
		});
	}

	public void cutTheImage(Uri uri) {
		cuttedImagePath = imageHandler.getNewTmpImagePath();
		imageHandler.cutTheImageHead(this.getActivity(), uri, cuttedImagePath);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		try {
			if (resultCode == Activity.RESULT_OK) {
				switch (requestCode) {
				// 如果是直接从相册获取
				case ReqCode.ALBUM:
					cutTheImage(data.getData());
					break;
				// 如果是调用相机拍照时
				case ReqCode.CAMERA:
					cutTheImage(Uri.fromFile(new File(takePicturePath)));
					break;
				// 取得裁剪后的图片
				case ReqCode.CUTTED:
					setPicToView(cuttedImagePath);
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setPicToView(String cuttedImagePath) {
		String compressedPath = imageHandler.compressImage(cuttedImagePath);
		imageHandler.loadHeaderImage(ImageLoaderHelper.URI_PREFIX_FILE + compressedPath, ivHeader);
		++currPicIndex;
		filePathes.add(compressedPath);

		uploadPic(compressedPath);
	}

	void uploadPic(String filePath) {
		processingDialog = new ProcessingDialog(this.getActivity(), "Uploading ...", false, null);
		processingDialog.show();
		netHandler.postForUploadPic(CURR_USER_ID, filePath, new Callback<DafStringResult>() {
			@Override
			public void success(DafStringResult result, Response response) {
				uploadedUUid = result.getStr();
				dismissProcessingDialog();
			}
			@Override
			public void failure(RetrofitError error) {
				RetrofitNetHandler.toastNetworkError(TestThirdFragment.this.getActivity(), error);
				dismissProcessingDialog();
			}
		});
	}

}
