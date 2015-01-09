package com.movitech.framework.handler;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Oct 28, 2014, 4:24:44 PM
 * @Description: IImageHandler是图片处理者的接口类
 *
 **/

public interface IImageHandler {

	public static final String TMP_IMAGE_INITIAL = "TMP_IMG_";
	public static final String COMPRESSED_IMAGE_INITIAL = "COMPRESSED_IMG_";

	public static final String IMAGE_CONTENT_TYPE = "image/*";

	public static final String FORMAT_IN_STRING_JPEG = "JPEG";
	public static final String FORMAT_IN_STRING_PNG = "PNG";
	public static final String SUFFIX_JPEG = ".jpg";
	public static final String SUFFIX_PNG = ".png";

	public static final int IMAGE_LOAD_NORMAL = 0x101;
	public static final int IMAGE_LOAD_ROUND_CORNER = 0x102;
	public static final int IMAGE_LOAD_HEAD = 0x103;


	public Bitmap resizeBitmap(Bitmap bitmap, float toW, float toH,
			int scaleType);

	public Bitmap resizeBitmap(String path, int max);

	public Point getScaledSize(String path, int max);

	public Bitmap getBitmapFromCachedFile(String url, int defRes);

	public int isRotatedImage(String path);

	public Bitmap rotateBitmap(Bitmap bitmap, int angle);

	public byte[] getBitmapData(Bitmap source);

	public String compress(String pathOri, int quality);

	/**
	 * The methods below are very useful.
	 */
	public void cleanImageCache();

	public void cleanImageCache(long size, long millSecAgo);

	public void initImageLoader();

	public void loadImage(String uri, ImageView imageView);

	public void loadImage(String uri, ImageView imageView, ImageLoadingProgressListener progresslistener);

	public void loadImage(String uri, ImageView imageView, ImageLoadingListener loadingListener, ImageLoadingProgressListener progresslistener);

	public void loadRoundCornerImage(String uri, ImageView imageView);

	public void loadRoundCornerImage(String uri, ImageView imageView, ImageLoadingProgressListener progresslistener);

	public void loadRoundCornerImage(String uri, ImageView imageView, ImageLoadingListener loadingListener, ImageLoadingProgressListener progresslistener);

	public void loadHeaderImage(String uri, ImageView imageView);

	public String getNewTmpImagePath();

	public String getNewTmpImagePath(String imageSuffix);

	public void selectGetImageWay(final Activity activity, View hangView, final String takePicturePath);

	public void cutTheImage(Activity activity, Uri uri, String cuttedImagePath, int aspectX, int aspectY, int outputX, int outputY, String outputFormat);

	public void cutTheImageNormal(Activity activity, Uri uri, String cuttedImagePath);

	public void cutTheImageHead(Activity activity, Uri uri, String cuttedImagePath);

	public String compressImage(String imagePath);

	public String compressImage(String imagePath, int quality);
}
