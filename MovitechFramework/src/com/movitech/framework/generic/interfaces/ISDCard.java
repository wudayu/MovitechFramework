package com.movitech.framework.generic.interfaces;

import android.os.Environment;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Oct 28, 2014, 4:22:41 PM
 * @Description: SDCard相关操作接口
 *
 **/

public interface ISDCard {

	public static final String SD_CARD = Environment.getExternalStorageDirectory().getAbsolutePath();

	public static String SD_CARD_DAF = SD_CARD + "/daf/";
	public static String SD_DOWNLOAD = SD_CARD + "/daf/download/";
	public static String SD_LOG = SD_CARD + "/daf/log/";
	public static String SD_IMAGE_CACHE = SD_CARD + "/daf/image/cache/";

	public boolean isSdcardAvaliable();

	public boolean isAvaiableSpace(long size);

}
