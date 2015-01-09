package com.movitech.framework.generic;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.movitech.framework.constant.Constant;
import com.movitech.framework.generic.interfaces.ISDCard;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Oct 23, 2014, 5:26:24 PM
 * @Description: Utils是静态工具类，包括Debug调试函数，标准Toast调用以及诸如防止多次点击的工具
 *
 **/

public class Utils {

	public static final String TAG = "daf";
	private static final boolean DEBUG = Constant.DEBUG;
	private static final String LOG_FILE_NAME = "debug.log";

	/**
	 * Debug function, used to print something
	 * 
	 * @param message, something that need to print
	 */
	public static void debug(String message) {
		debug(Utils.TAG, message);
	}

	/**
	 * Debug function, used to print something
	 * 
	 * @param tag, Debug tag
	 * @param message, something that need to print
	 */
	public static void debug(String tag, String message) {
		if (DEBUG) {
			Log.v(tag, message);
		} else {
			output(message);
		}
	}

	/**
	 * Print the message into log file
	 * 
	 * @param message
	 */
	public static synchronized void output(String message) {
		File file = new File(ISDCard.SD_LOG, LOG_FILE_NAME);
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		try {
			fos = new FileOutputStream(file, true);
			dos = new DataOutputStream(fos);
			SimpleDateFormat sdf = new SimpleDateFormat("MM.dd HH:mm:ss", Locale.US);
			String suffix = sdf.format(new Date());
			String content = suffix + "  " + message + "\n";
			dos.writeUTF(content);
		} catch (IOException e) {
			debug(e.toString());
		} catch (Exception e) {
			debug(e.toString());
		} finally {
			try {
				if (dos != null) {
					dos.flush();
					dos.close();
					dos = null;
				}
				if (fos != null) {
					fos.flush();
					fos.close();
					fos = null;
				}
			} catch (IOException e) {
				debug(e.toString());
			}
		}
	}

	/**
	 * Toast a message in debug level
	 * 
	 * @param activity
	 * @param message
	 */
	private static Toast mToast = null;
	public static final void toastMessage(final Activity activity, final String message) {
		toastMessage(activity, message, Toast.LENGTH_SHORT);
	}

	/**
	 * Toast a message in debug level with a length option
	 * 
	 * @param activity
	 * @param message
	 */
	public static final void toastMessage(final Activity activity, final String message, final int length) {
		toastMessage(activity, message, null, length);
	}

	/**
	 * Toast a message
	 * 
	 * @param activity
	 * @param message
	 * @param logLevel
	 */
	public static final void toastMessage(final Activity activity, final String message, String logLevel, final int length) {
		if ("w".equals(logLevel)) {
			Log.w(TAG, message);
		} else if ("e".equals(logLevel)) {
			Log.e(TAG, message);
		} else {
			Log.d(TAG, message);
		}
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (mToast != null) {
					mToast.cancel();
					mToast = null;
				}
				mToast = Toast.makeText(activity, message, length);
				mToast.show();
			}
		});
	}

	/**
	 * Check the Object is NULL
	 * 
	 * @param the object
	 * @return is or not
	 */
	public static boolean isNull(Object object) {
		boolean result;
		if (!(object instanceof String)) {
			return (null == object);
		}

		if (TextUtils.isEmpty((CharSequence) (object))) {
			result = true;
		} else {
			String str = String.valueOf(object);
			str = str.toLowerCase(Locale.ENGLISH);
			result = ("null").equals(str);
		}

		return result;
	}

	/**
	 * Prevent Double Click
	 * 
	 * @return is double click or not
	 */
	private static long lastClickTime;
	public static boolean isFastClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < 500) {
			return true;
		}

		lastClickTime = time;
		return false;
	}

	/**
	 * Get Status Bar Height
	 * 
	 * @param an available activity
	 * @return height in integer
	 */
	public static int getStatusBarHeight(Activity ctx) {
		Rect frame = new Rect();
		ctx.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);

		return frame.top;
	}

	/**
	 * Check the email format
	 * 
	 * @param the email
	 * @return right or wrong
	 */
	public static boolean isValidEmail(String email) {
		Pattern pattern = Patterns.EMAIL_ADDRESS;
		Matcher mc = pattern.matcher(email);
		return mc.matches();
	}

	/**
	 * Check the URL format
	 * 
	 * @param the url
	 * @return right or wrong
	 */
	public static boolean isValidURL(String url) {
		Pattern patterna = Patterns.WEB_URL;
		Matcher mca = patterna.matcher(url);
		return mca.matches();
	}

	/**
	 * If the Keyboard doesn't hide, hide it
	 * 
	 * @param an available activity
	 * @param v
	 */
	public static void autoCloseKeyboard(Activity activity, View v) {
		View view = activity.getWindow().peekDecorView();
		if (view != null && view.getWindowToken() != null) {
			InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
		}
	}

	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	private static String byteToString(byte[] bByte) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}

	/**
	 * Get String's MD5
	 * 
	 * @param the source string
	 * @return
	 */
	public static String getMD5(String str) {
		String resultString = null;
		try {
			resultString = new String(str);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteToString(md.digest(str.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			Utils.debug("getMD5 : " + e.toString());
		}
		return resultString;
	}

	/**
	 * Arrays add
	 * 
	 * @param arrays that need to add
	 * @return
	 */
	public static Integer[] intArrayPlus(Integer[]... arrays) {
		int size = 0;
		for (int i = 0; i < arrays.length; i++) {
			Integer[] temp = arrays[i];
			size += temp.length;
		}

		Integer[] all = new Integer[size];
		int dstPos = 0;
		for (int i = 0; i < arrays.length; i++) {
			System.arraycopy(arrays[i], 0, all, dstPos, arrays[i].length);
			dstPos += arrays[i].length;
		}

		return all;
	}

	/**
	 * Get device's DeviceId
	 * 
	 * @param context
	 * @return
	 */
	public static String getDeviceId(Context context) {
		TelephonyManager telephonyManager;

		telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

		String imeistring = telephonyManager.getDeviceId();

		return imeistring;
	}

	/**
	 * Reserves two decimal fractions
	 * 
	 * @param number need reserve
	 * @return reserved number
	 */
	public static double convert2dot(double number) {
		BigDecimal bg = new BigDecimal(number);
		double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	/**
	 * Reserves two decimal fractions
	 * 
	 * @param number need reserve
	 * @return reserved number in string format
	 */
	public static String convert2Double(double number) {
		return String.format(Locale.CHINA, "%.2f", number);
	}

}
