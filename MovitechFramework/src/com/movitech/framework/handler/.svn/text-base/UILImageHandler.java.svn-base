package com.movitech.framework.handler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.movitech.framework.R;
import com.movitech.framework.constant.Constant;
import com.movitech.framework.constant.ImageLoaderHelper;
import com.movitech.framework.constant.ReqCode;
import com.movitech.framework.generic.SDCard;
import com.movitech.framework.generic.Utils;
import com.movitech.framework.generic.interfaces.ISDCard;
import com.movitech.framework.handler.IFileHandler.CacheDir;
import com.movitech.framework.views.SelectPicPopupWindow;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Oct 28, 2014, 4:26:35 PM
 * @Description: UILImageHander是使用了Universal Image Loader来控制图片的单例类，它可以加载普通图片，加载圆角图片，加载圆形图片以及选择图片来源，对图片进行切割处理等功能
 *
 **/

public class UILImageHandler implements IImageHandler {

	private static Context sContext = null;
	private static ISDCard sSDCard = null;
	private static IFileHandler sFileHandler = null;

	ImageLoader imageLoader = null;
	ImageLoaderConfiguration defaultUilLoader = null;

	DisplayImageOptions defaultUilDisplay = null;
	DisplayImageOptions roundUilDisplay = null;
	DisplayImageOptions headerUilDisplay = null;

	String mImageCacheDir = null;

	/** Generate Singleton */
	private static final IImageHandler instance = new UILImageHandler();

	private UILImageHandler() {}

	public static IImageHandler getInstance(Context context) {
		sContext = context;
		sSDCard = SDCard.getInstance();
		sFileHandler = FileHandler.getInstance(context);

		return instance;
	}

	@Override
	public void initImageLoader() {
		initUIL();

		if (imageLoader == null)
			imageLoader = ImageLoader.getInstance();
		if (!imageLoader.isInited())
			imageLoader.init(defaultUilLoader);

		mImageCacheDir = sFileHandler.getCacheDirByType(CacheDir.IMAGE);
	}

	@Override
	public void cleanImageCache() {
		cleanImageCache(0, 0);
	}

	@Override
	public void cleanImageCache(long size, long millSecAgo) {
		sFileHandler.cleanCache(mImageCacheDir, size, millSecAgo);
	}

	private void initUIL() {
		defaultUilDisplay = new DisplayImageOptions.Builder()
//				.showImageOnLoading(R.drawable.default_houseimg) // resource or drawable
//		        .showImageForEmptyUri(R.drawable.default_houseimg) // resource or drawable
//		        .showImageOnFail(R.drawable.default_houseimg) // resource or drawable
		        // .resetViewBeforeLoading(false)  // default false
		        // .delayBeforeLoading(1000)
		        .cacheInMemory(false) // default false
		        .cacheOnDisk(true) // default false
		        // .preProcessor(...)
		        // .postProcessor(...)
		        // .extraForDownloader(...)
		        .considerExifParams(true) // default
		        .imageScaleType(ImageScaleType.EXACTLY) // default IN_SAMPLE_POWER_OF_2
		        .bitmapConfig(Bitmap.Config.RGB_565) // default 8888
		        // .decodingOptions(...)
		        .displayer(new FadeInBitmapDisplayer(500)) // default SimpleBitmapDisplayer
		        // .handler(new Handler()) // default
		        .build();

		roundUilDisplay = new DisplayImageOptions.Builder()
//				.showImageOnLoading(R.drawable.default_houseimg)
//		        .showImageForEmptyUri(R.drawable.default_houseimg)
//		        .showImageOnFail(R.drawable.default_houseimg)
		        .cacheOnDisk(true)
		        .considerExifParams(true)
		        .imageScaleType(ImageScaleType.EXACTLY)
		        .bitmapConfig(Bitmap.Config.RGB_565)
		        .displayer(new RoundedBitmapDisplayer(sContext.getResources().getDimensionPixelSize(R.dimen.round_corner_small)))
		        .build();

		headerUilDisplay = new DisplayImageOptions.Builder()
//				.showImageOnLoading(R.drawable.default_houseimg)
//		        .showImageForEmptyUri(R.drawable.default_houseimg)
//		        .showImageOnFail(R.drawable.default_houseimg)
		        .cacheOnDisk(true)
		        .considerExifParams(true)
		        .imageScaleType(ImageScaleType.EXACTLY)
		        .bitmapConfig(Bitmap.Config.ARGB_8888)
		        .displayer(new RoundedBitmapDisplayer(sContext.getResources().getDimensionPixelSize(R.dimen.round_corner_circle)))
		        .build();

		defaultUilLoader = new ImageLoaderConfiguration.Builder(sContext)
		// .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
        // .diskCacheExtraOptions(480, 800, null)
        // .taskExecutor(...)
        // .taskExecutorForCachedImages(...)
        // .threadPoolSize(3) // default
        // .threadPriority(Thread.NORM_PRIORITY - 1) // default
        .tasksProcessingOrder(QueueProcessingType.LIFO) // default FIFO
        // .denyCacheImageMultipleSizesInMemory()
        // .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
        // .memoryCacheSize(2 * 1024 * 1024)
        // .memoryCacheSizePercentage(13) // default
        .diskCache(new UnlimitedDiscCache(new File(ISDCard.SD_IMAGE_CACHE))) // default
        // .diskCacheExtraOptions(maxImageWidthForDiskCache, maxImageHeightForDiskCache, processorForDiskCache)
        .diskCacheSize(128 * 1024 * 1024)
        // .diskCacheFileCount(100)
        // .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
        // .imageDownloader(new BaseImageDownloader(mContext)) // default
        // .imageDecoder(new BaseImageDecoder()) // default
        .defaultDisplayImageOptions(defaultUilDisplay) // default
        // .writeDebugLogs()
        .build();
	}

	@Override
	public void loadHeaderImage(String uri, ImageView imageView) {
		loadImage(uri, imageView, headerUilDisplay, null, null);
	}

	@Override
	public void loadRoundCornerImage(String uri, ImageView imageView) {
		loadRoundCornerImage(uri, imageView, null, null);
	}

	public void loadRoundCornerImage(String uri, ImageView imageView, ImageLoadingProgressListener progresslistener) {
		loadRoundCornerImage(uri, imageView, null, progresslistener);
	}

	public void loadRoundCornerImage(String uri, ImageView imageView, ImageLoadingListener loadingListener, ImageLoadingProgressListener progresslistener) {
		loadImage(uri, imageView, roundUilDisplay, loadingListener, progresslistener);
	}

	@Override
	public void loadImage(String uri, ImageView imageView) {
		loadImage(uri, imageView, null, null);
	}

	@Override
	public void loadImage(String uri, ImageView imageView, ImageLoadingProgressListener progresslistener) {
		loadImage(uri, imageView, defaultUilDisplay, null, progresslistener);

		// the method that must be implemented in ImageLoadingProgressListener is like this:
		// void onProgressUpdate(String imageUri, View view, int current, int total);
	}

	@Override
	public void loadImage(String uri, ImageView imageView, ImageLoadingListener loadingListener, ImageLoadingProgressListener progresslistener) {
		loadImage(uri, imageView, defaultUilDisplay, loadingListener, progresslistener);
	}

	private void loadImage(String uri, ImageView imageView, DisplayImageOptions options, ImageLoadingListener outsideLoadingListener, ImageLoadingProgressListener progressListener) {
		// use image cache
		String fileName = sFileHandler.getFileNameInUrl(uri);
		String fullPath = mImageCacheDir + fileName;
		if (sFileHandler.isFileExists(fullPath)) {
			imageLoader.displayImage(ImageLoaderHelper.URI_PREFIX_FILE + fullPath, imageView, options, outsideLoadingListener, progressListener);
		} else {
			imageLoader.displayImage(uri, imageView, options, new SaveCacheImageLoadingListener(fullPath, outsideLoadingListener), progressListener);
		}
	}

	public class SaveCacheImageLoadingListener implements ImageLoadingListener {

		String fullPath = null;
		ImageLoadingListener outsideLoadingListener = null;

		public SaveCacheImageLoadingListener(String fullPath, ImageLoadingListener outsideLoadingListener) {
			this.fullPath = fullPath;
			this.outsideLoadingListener = outsideLoadingListener;
		}

		/**
		 * Is called when image loading task was started
		 *
		 * @param imageUri Loading image URI
		 * @param view     View for image
		 */
		@Override
		public void onLoadingStarted(String imageUri, View view) {
			if (outsideLoadingListener != null)
				outsideLoadingListener.onLoadingStarted(imageUri, view);
		}
		/**
		 * Is called when an error was occurred during image loading
		 *
		 * @param imageUri   Loading image URI
		 * @param view       View for image. Can be <b>null</b>.
		 * @param failReason {@linkplain com.nostra13.universalimageloader.core.assist.FailReason The reason} why image
		 *                   loading was failed
		 */
		@Override
		public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
			if (outsideLoadingListener != null)
				outsideLoadingListener.onLoadingFailed(imageUri, view, failReason);
		}

		/**
		 * Is called when image is loaded successfully (and displayed in View if one was specified)
		 *
		 * @param imageUri    Loaded image URI
		 * @param view        View for image. Can be <b>null</b>.
		 * @param loadedImage Bitmap of loaded and decoded image
		 */
		@Override
		public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
			sFileHandler.saveBitmap(fullPath, loadedImage, CompressFormat.PNG);

			if (outsideLoadingListener != null)
				outsideLoadingListener.onLoadingComplete(imageUri, view, loadedImage);
		}

		/**
		 * Is called when image loading task was cancelled because View for image was reused in newer task
		 *
		 * @param imageUri Loading image URI
		 * @param view     View for image. Can be <b>null</b>.
		 */
		@Override
		public void onLoadingCancelled(String imageUri, View view) {
			if (outsideLoadingListener != null)
				outsideLoadingListener.onLoadingCancelled(imageUri, view);
		}
	}

	/**
	 * 按照指定宽高缩放图片
	 * 
	 * @param bitmap
	 * @param toW
	 * @param toH
	 * @param scaleType
	 *            0：按照宽高缩放；1:按照宽度缩放；2:按照高度缩放
	 * @return
	 */
	@Override
	public Bitmap resizeBitmap(Bitmap bitmap, float toW, float toH,
			int scaleType) {
		int bitmapW = bitmap.getWidth();
		int bitmapH = bitmap.getHeight();
		// 判断是否需要缩放
		if (toW == bitmapW && toH == bitmapH) {
			return bitmap;
		}

		Matrix matrix = new Matrix();
		float scaleW, scaleH;
		scaleW = toW / bitmapW;
		scaleH = toH / bitmapH;
		if (scaleType == 0) {
			matrix.postScale(scaleW, scaleH);
		} else if (scaleType == 1) {
			matrix.postScale(scaleW, scaleW);
		} else {
			matrix.postScale(scaleH, scaleH);
		}

		Bitmap returenBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmapW,
				bitmapH, matrix, true);
		matrix = null;
		return returenBitmap;
	}

	/**
	 * 获取重新计算大小的Bitmap 最大边不超过max
	 * 
	 * @param path
	 * @param max
	 * @return
	 */
	@Override
	public Bitmap resizeBitmap(String path, int max) {
		int sample = 1;
		BitmapFactory.Options opts = new Options();
		opts.inJustDecodeBounds = true;
		opts.inSampleSize = sample;
		BitmapFactory.decodeFile(path, opts);
		int w = opts.outWidth;
		int h = opts.outHeight;
		if (Math.max(w, h) > max * 4) {
			sample = 8;
		} else if (Math.max(w, h) > max * 2) {
			sample = 4;
		} else if (Math.max(w, h) > max) {
			sample = 2;
		}
		opts.inPreferredConfig = Config.ARGB_8888;
		opts.inJustDecodeBounds = false;
		opts.inSampleSize = sample;
		try {
			return (sample == 1) ? BitmapFactory.decodeFile(path, opts)
					: getScaledBitmap(BitmapFactory.decodeFile(path, opts), max);
		} catch (OutOfMemoryError e) {
			Utils.debug(e.toString());
			opts.inSampleSize = sample * 2;
			try {
				return getScaledBitmap(BitmapFactory.decodeFile(path, opts),
						max);
			} catch (OutOfMemoryError e1) {
				try {
					Utils.debug(e1.toString());
					opts.inSampleSize = sample * 2;
					return getScaledBitmap(
							BitmapFactory.decodeFile(path, opts), max);
				} catch (Exception e2) {
					Utils.debug(e2.toString());
					return null;
				}
			}
		}
	}

	@Override
	public Point getScaledSize(String path, int max) {
		BitmapFactory.Options opts = new Options();
		opts.inJustDecodeBounds = true;
		opts.inSampleSize = 1;
		BitmapFactory.decodeFile(path, opts);
		int w = opts.outWidth;
		int h = opts.outHeight;
		float scale = 1.0f;
		int destW = w;
		int destH = h;
		if (w > h) {
			scale = (max * 1.0f) / w;
			destW = max;
			destH = (int) (scale * h);
		} else {
			scale = (max * 1.0f) / h;
			destH = max;
			destW = (int) (scale * w);
		}
		return new Point(destW, destH);
	}

	/**
	 * 获取缩放之后的Bitmap
	 * 
	 * @param bitmap
	 * @param max
	 * @return
	 */
	private Bitmap getScaledBitmap(Bitmap bitmap, int max) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		float scale = 1.0f;
		int destW = w;
		int destH = h;
		if (w > h) {
			scale = (max * 1.0f) / w;
			destW = max;
			destH = (int) (scale * h);
		} else {
			scale = (max * 1.0f) / h;
			destH = max;
			destW = (int) (scale * w);
		}
		return Bitmap.createScaledBitmap(bitmap, destW, destH, true);
	}


	@Override
	public Bitmap getBitmapFromCachedFile(String url, int defRes) {
		String path = sFileHandler.getCacheDirByType(IFileHandler.CacheDir.IMAGE) + sFileHandler.getFileNameInUrl(url);
		if (!sFileHandler.isFileExists(path)) {
			return BitmapFactory.decodeResource(sContext.getResources(), defRes);
		}
		return BitmapFactory.decodeFile(path);
	}

	/**
	 * 判断原始素材的方向 （适用于相机拍摄的照片）
	 * 
	 * @param path
	 * @return
	 */
	@Override
	public int isRotatedImage(String path) {
		int angle = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientationPhoto = exifInterface.getAttributeInt("Orientation",
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientationPhoto) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				angle = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				angle = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				angle = 270;
				break;
			default:
				angle = 0;
				break;
			}
		} catch (IOException e) {
			Utils.debug(e.toString());
		}
		return angle;
	}

	/**
	 * 旋转Bitmap
	 * 
	 * @param bitmap
	 * @param angle
	 * @return
	 */
	@Override
	public Bitmap rotateBitmap(Bitmap bitmap, int angle) {
		Matrix m = new Matrix();
		m.setRotate(angle);
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		try {
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, m, true);
		} catch (OutOfMemoryError oom) {
			try {
				m.postScale(1.0f, 1.0f);
				bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, m,
						true);
			} catch (Exception e) {
				Utils.debug(e.toString());
			}
		}
		return bitmap;
	}

	/**
	 * 获取bitmap数据
	 * 
	 * @param source
	 * @return
	 */
	@Override
	public byte[] getBitmapData(Bitmap source) {
		if (source == null) {
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		source.compress(CompressFormat.JPEG, 80, baos);
		byte[] result = baos.toByteArray();
		if (null != baos) {
			try {
				baos.close();
			} catch (IOException e) {
				Utils.debug(e.toString());
			}
		}
		return result;
	}

	@Override
	public String compress(String pathOri, int quality) {
		String suffix = null;

		if (pathOri.endsWith(".png")) {
			suffix = ".png";
		} else {
			suffix = ".jpg";
		}

		int angle = isRotatedImage(pathOri);

		String tmpPath = mImageCacheDir + COMPRESSED_IMAGE_INITIAL
				+ System.currentTimeMillis() + suffix;

		Bitmap bitmap = null;

		CompressFormat format = CompressFormat.PNG;

		if (pathOri.endsWith(".png")) {
			bitmap = resizeBitmap(pathOri, 1024);

			format = CompressFormat.PNG;
		} else {
			bitmap = resizeBitmap(pathOri, 1024);

			if (angle != 0) {
				bitmap = rotateBitmap(bitmap, angle);
			}

			format = CompressFormat.JPEG;
		}

		if (bitmap == null) {
			return "";
		}

		saveBitmap(new File(tmpPath), bitmap, quality, format);

		return tmpPath;
	}

	private boolean saveBitmap(File file, Bitmap bitmap, int quality,
			CompressFormat format) {
		if (sSDCard.isSdcardAvaliable()) {
			try {
				FileOutputStream out = new FileOutputStream(file);

				if (bitmap.compress(format, quality, out)) {
					out.flush();
					out.close();
					return true;
				}
			} catch (FileNotFoundException e) {
				Utils.debug(e.toString());
			} catch (IOException e) {
				Utils.debug(e.toString());
			}
		} else {
			Utils.debug(sContext.getString(R.string.error_can_not_find_sdcard));
		}

		return false;
	}

	@Override
	public String getNewTmpImagePath() {
		return getNewTmpImagePath(IImageHandler.SUFFIX_JPEG);
	}

	@Override
	public String getNewTmpImagePath(String imageSuffix) {
		return mImageCacheDir + TMP_IMAGE_INITIAL + System.currentTimeMillis() + imageSuffix;
	}

	@Override
	public void selectGetImageWay(final Activity activity, View hangView, final String takePicturePath) {
		InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm.isActive()) {
			imm.hideSoftInputFromWindow(hangView.getWindowToken(), 0);
		}

		final SelectPicPopupWindow menuWindow = new SelectPicPopupWindow(activity);
		menuWindow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				menuWindow.dismiss();
				switch (v.getId()) {
				case R.id.btn_take_photo:
					// 跳转相机拍照
					String sdStatus = Environment.getExternalStorageState();
					if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
						Utils.toastMessage(activity, sContext.getString(R.string.error_can_not_find_sdcard));
						return;
					}
					Intent intentTakenPic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					// 指定调用相机拍照后的照片存储的路径
					intentTakenPic.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(takePicturePath)));
					activity.startActivityForResult(intentTakenPic, ReqCode.CAMERA);
					break;
				case R.id.btn_pick_photo:
					Intent intentPickPic = new Intent(Intent.ACTION_PICK, null);
					intentPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_CONTENT_TYPE);
					activity.startActivityForResult(intentPickPic, ReqCode.ALBUM);
					break;
				default:
					break;
				}
			}
		});
		// show the choose window
		menuWindow.showAtLocation(hangView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
	}

	@Override
	public void cutTheImage(Activity activity, Uri uri, String cuttedImagePath, int aspectX, int aspectY, int outputX, int outputY, String outputFormat) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, IMAGE_CONTENT_TYPE);
		intent.putExtra("crop", "true");

		// aspectX aspectY 宽高的比例
		if (aspectX > 0)
			intent.putExtra("aspectX", aspectX);
		if (aspectY > 0)
			intent.putExtra("aspectY", aspectY);

		// outputX outputY 裁剪图片宽高
		if (outputX > 0)
			intent.putExtra("outputX", outputX);
		if (outputY > 0)
			intent.putExtra("outputY", outputY);

		intent.putExtra("outputFormat", FORMAT_IN_STRING_JPEG);
		intent.putExtra("return-data", false);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(cuttedImagePath)));

		activity.startActivityForResult(intent, ReqCode.CUTTED);
	}

	@Override
	public void cutTheImageNormal(Activity activity, Uri uri, String cuttedImagePath) {
		cutTheImage(activity, uri, cuttedImagePath, -1, -1, -1, -1, FORMAT_IN_STRING_JPEG);
	}

	@Override
	public void cutTheImageHead(Activity activity, Uri uri, String cuttedImagePath) {
		cutTheImage(activity, uri, cuttedImagePath, 1, 1, -1, -1, FORMAT_IN_STRING_JPEG);
	}

	@Override
	public String compressImage(String imagePath) {
		return compressImage(imagePath, Constant.IMAGE_QUALITY);
	}

	@Override
	public String compressImage(String imagePath, int quality) {
		return compress(imagePath, quality);
	}

}
