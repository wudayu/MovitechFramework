package com.movitech.framework.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import org.apache.http.util.EncodingUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.movitech.framework.constant.Constant;
import com.movitech.framework.generic.Utils;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Oct 28, 2014, 4:17:32 PM
 * @Description: FileHandler用来处理文件操作
 *
 **/

public class FileHandler implements IFileHandler {

	public static final String DEFAULT_ENCODING = "UTF-8";
	private static Context sContext = null;

	/** Generate Singleton */
	private static final IFileHandler instance = new FileHandler();

	private FileHandler() {}

	public static IFileHandler getInstance(Context context) {
		sContext = context;
		return instance;
	}

	public String getCacheDirByType(CacheDir dir) {
		String path = this.getCacheDir() + dir;

		File file = new File(path);

		if (!file.exists()) {
			file.mkdirs();
		}

		return path;
	}

	public String getFileDirByType(DataDir dir) {
		String path = this.getFileDir() + dir;

		File file = new File(path);

		if (!file.exists()) {
			file.mkdirs();
		}

		return path;
	}

	/**
	 * 获取可用的文件目录 优先/sdcard/Android/data/mypacketname/file
	 * 不可用时返回/data/data/mypacketname/file
	 * 
	 * @return path
	 * @author kooze
	 */
	public String getFileDir() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File file = sContext.getExternalFilesDir(null);

			if (file != null) {
				return file.getAbsolutePath();
			}
		}

		return sContext.getFilesDir().getAbsolutePath();
	}

	/**
	 * 获取可用的缓存目录 优先/sdcard/Android/data/mypacketname/cache
	 * 不可用时返回/data/data/mypacketname/file
	 * 
	 * @return path
	 * @author kooze
	 */
	public String getCacheDir() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File file = sContext.getExternalCacheDir();

			if (file != null) {
				return file.getAbsolutePath();
			}
		}

		return sContext.getCacheDir().getAbsolutePath();
	}

	/**
	 * 判断文件或文件夹是否存在
	 * 
	 * @param filePath
	 * @return
	 */
	@Override
	public boolean isFileExists(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}

	/**
	 * 创建文件夹，可递归创建
	 * 
	 * @param folderPath
	 * @return
	 */
	@Override
	public boolean createFolder(String folderPath) {
		File path = new File(folderPath);
		if (path.isDirectory()) {
			return true;
		}
		return path.mkdirs();
	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 * @return
	 */
	@Override
	public boolean deleteFile(String filePathAndName) {
		File file = new File(filePathAndName);
		if (file.isFile())// Don't need file.exists()
		{
			file.delete();
			return true;
		}
		return false;
	}

	/**
	 * 删除目录,支持递归
	 * 
	 * @param path
	 * @param recursive
	 *            是否递归删除
	 * @return
	 */
	@Override
	public boolean deleteFolder(String path, boolean recursive) {
		File thisFolder = new File(path);
		if (thisFolder.isDirectory()) {
			File[] childFiles = thisFolder.listFiles();
			if (childFiles != null && childFiles.length > 0) {
				for (File file : childFiles) {
					if (file.isDirectory()) {
						if (recursive) {
							String p = file.getAbsolutePath();
							deleteFolder(p, recursive);
						}
					} else {
						file.delete();
					}
				}
			}
			thisFolder.delete();
			return true;
		}
		return false;
	}

	/**
	 * 删除目录下所有文件,支持递归
	 * 
	 * @param path
	 * @param recursive
	 *            是否递归删除
	 * @return
	 */
	@Override
	public boolean deleteFilesInFloder(String path, boolean recursive) {
		File thisFolder = new File(path);
		if (thisFolder.isDirectory()) {
			File[] childFiles = thisFolder.listFiles();
			if (childFiles != null && childFiles.length > 0) {
				for (File file : childFiles) {
					if (file.isDirectory()) {
						if (recursive) {
							String p = file.getAbsolutePath();
							deleteFolder(p, recursive);
						}
					} else {
						file.delete();
					}
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 获取文件名，不包含扩展名
	 * 
	 * @param fileName
	 *            name of file
	 * @return file base name
	 */
	@Override
	public String getFileBaseName(final String fileName) {
		if (fileName == null || fileName.indexOf(".") == -1) {
			return null;
		}
		return fileName.substring(0, fileName.indexOf("."));
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	@Override
	public String getFileExtension(final String fileName) {
		if (fileName == null || fileName.lastIndexOf(".") == -1
				|| fileName.lastIndexOf(".") == fileName.length() - 1) {
			return "";
		}
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	/**
	 * 复制文件
	 * 
	 * @param sourcePath
	 * @param targetPath
	 * @return
	 */
	@Override
	public boolean copyFile(String sourcePath, String targetPath) {
		File sourcefile = new File(sourcePath);
		if (sourcefile.isFile()) {
			String dirPath = getDirPath(targetPath);
			if (dirPath != null) {
				boolean successful = createFolder(dirPath);
				if (successful) {
					InputStream is = null;
					FileOutputStream fos = null;
					try {
						is = new FileInputStream(sourcePath);
						fos = new FileOutputStream(targetPath);
						byte[] buffer = new byte[1024];
						int length = -1;
						while ((length = is.read(buffer)) != -1) {
							fos.write(buffer, 0, length);
						}
						return true;
					} catch (FileNotFoundException e) {
						Utils.debug(e.toString());
					} catch (IOException e) {
						Utils.debug(e.toString());
					} finally {
						try {
							if (fos != null) {
								fos.close();
								fos = null;
							}
							if (is != null) {
								is.close();
								is = null;
							}
						} catch (IOException e) {
							Utils.debug(e.toString());
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * 复制文件
	 * 
	 * @param src
	 * @param dst
	 * @throws IOException
	 */
	@Override
	@SuppressWarnings("resource")
	public void copyFile(File src, File dst) throws IOException {
		FileChannel inChannel = new FileInputStream(src).getChannel();
		FileChannel outChannel = new FileOutputStream(dst).getChannel();
		try {
			inChannel.transferTo(0, inChannel.size(), outChannel);
		} finally {
			if (inChannel != null) {
				inChannel.close();
			}
			if (outChannel != null) {
				outChannel.close();
			}
		}
	}

	/**
	 * 从文件路径中提取目录路径
	 * 
	 * @param filePath
	 * @return
	 */
	@Override
	public String getDirPath(String filePath) {
		int separatePos = filePath.lastIndexOf('/');
		return separatePos == -1 ? null : filePath.substring(0, separatePos);
	}

	/**
	 * 获取文件名称
	 */
	@Override
	public String getFileName(String filePath) {
		int separatePos = filePath.lastIndexOf('/');
		return separatePos == -1 ? null : filePath.substring(separatePos + 1);
	}

	/**
	 * 获取文件名称 不包含扩展名
	 * 
	 * @param path
	 * @return
	 */
	@Override
	public String getFileNameWithoutSuffix(String path) {
		if (Utils.isNull(path)) {
			return "";
		}
		File f = new File(path);
		if (f.exists()) {
			String name = f.getName();
			if (name.contains(".")) {
				return name.substring(0, name.lastIndexOf("."));
			} else {
				return name;
			}
		} else {
			return "";
		}
	}

	/**
	 * 读取文本文件到字符串中
	 * 
	 * @param path
	 * @return
	 */
	@Override
	public String readTextFile(String path) {
		File file = new File(path);
		if (file.isFile()) {
			InputStream is = null;
			BufferedReader buffreader = null;
			try {
				String content = null;
				is = new FileInputStream(file);
				if (is != null) {
					buffreader = new BufferedReader(new InputStreamReader(is));
					String line = null;
					while ((line = buffreader.readLine()) != null) {
						content += line;// 这里要不要加\n看应用中具体需求吧。
					}
				}
				return content;
			} catch (java.io.FileNotFoundException e) {
				Utils.debug(e.toString());
			} catch (IOException e) {
				Utils.debug(e.toString());
			} finally {
				try {
					if (buffreader != null) {
						buffreader.close();
						buffreader = null;
					}
					if (is != null) {
						is.close();
						is = null;
					}
				} catch (IOException e) {
					Utils.debug(e.toString());
				}
			}
		}
		return null;
	}

	/**
	 * 列出指定目录下的文件，支持过滤器和递归。 因为担心开发者在写FileFilter时不经意把当前目录下的子目录过滤掉，所以代码中做了特殊处理，
	 * 要求FileFilter中返回的筛选结果只能是文件。
	 * 
	 * @param path
	 * @param filter
	 * @param recursive
	 * @return
	 */
	@Override
	public ArrayList<String> listFiles(String path, FileFilter filter,
			boolean recursive) {
		ArrayList<String> resultList = new ArrayList<String>();
		File thisPath = new File(path);
		if (thisPath.isDirectory()) {
			ArrayList<File> fileList = new ArrayList<File>();
			File[] files = thisPath.listFiles(filter);
			// 过滤掉结果中的目录
			if (files != null) {
				for (File f : files) {
					if (f.isFile()) {
						fileList.add(f);
					}
				}
			}
			// 过滤掉当前目录中的文件，仅获取目录
			files = thisPath.listFiles();
			if (files != null) {
				for (File f : files) {
					if (f.isDirectory()) {
						fileList.add(f);
					}
				}
			}
			files = null;
			for (File file : fileList) {
				if (file.isDirectory()) {
					if (recursive) {
						ArrayList<String> list = listFiles(
								file.getAbsolutePath(), filter, recursive);
						resultList.addAll(list);
					}
				} else {
					resultList.add(file.getAbsolutePath());
				}
			}
		}
		thisPath = null;
		return resultList;
	}

	/**
	 * 重命名文件
	 */
	@Override
	public boolean renameFile(String pathOri, String pathNew) {
		File fOri = new File(pathOri);
		File fNew = new File(pathNew);
		if (fOri.exists() && !fNew.exists()) {
			return fOri.renameTo(fNew);
		} else {
			Utils.debug("Rename file failed!");
			return false;
		}
	}

	/**
	 * 获取URL中的文件名
	 */
	@Override
	public String getFileNameInUrl(String url) {
		if (Utils.isNull(url) || url.length() < 1) {
			return "";
		}
		String str = "?name=";
		if (url.contains(str)) {
			return url.substring(url.lastIndexOf(str) + str.length(),
					url.length());
		}
		if (url.contains("/")) {
			return url.substring(url.lastIndexOf("/") + 1, url.length());
		}
		return "";
	}

	/**
	 * 获取文件大小描述
	 */
	@Override
	public String getDataSizeTxt(Long size) {
		double sss = 0;
		String unit = null;
		if (size < 1024) {
			sss = (double) size;
			unit = "B";
		} else if (size < 1024 * 1024) {
			sss = (double) (size * 1.0f / 1024);
			unit = "KB";
		} else if (size < 1024 * 1024 * 1024) {
			sss = (double) (size * 1.0f / 1024 / 1024);
			unit = "MB";
		} else {
			return "ERROR";
		}
		/** 保留两位小数点，四舍五入 */
		BigDecimal bigDecimal = new BigDecimal(sss);
		sss = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return sss + unit;
	}

	/**
	 * 保存Bitmap为图片文件 <br>
	 * 需要目标图片格式 <br>
	 * 图片质量默认为80
	 */
	@Override
	public void saveBitmap(String fullPath, Bitmap bitmap, CompressFormat format) {
		File file = new File(fullPath);
		saveBitmap(file, bitmap, Constant.IMAGE_QUALITY, format);
	}

	/**
	 * 保存Bitmap为图片 <br>
	 * 需要目标图片格式，图片质量
	 */
	@Override
	public void saveBitmap(String fullPath, Bitmap bitmap, int quality,
			CompressFormat format) {
		File file = new File(fullPath);
		saveBitmap(file, bitmap, quality, format);
	}

	/**
	 * 保存Bitmap为图片
	 */
	@Override
	public void saveBitmap(File file, Bitmap bitmap, int quality,
			CompressFormat format) {
		if (null == bitmap) {
			return;
		}

		FileOutputStream out;

		try {
			out = new FileOutputStream(file);

			if (bitmap.compress(format, quality, out)) {
				out.flush();
				out.close();
			}
		} catch (FileNotFoundException e) {
			Utils.debug("saveBitmap 1 : " + e.toString());
		} catch (IOException e) {
			Utils.debug("saveBitmap 2 : " + e.toString());
		}
	}

	/**
	 * 解码文件
	 */
	@Override
	public Bitmap decodeFile(File f) {
		try {
			return BitmapFactory.decodeStream(new FileInputStream(f));
		} catch (FileNotFoundException e) {
			Utils.debug("decodeFile : " + e.toString());
			return null;
		}
	}

	/**
	 * 拷贝文件
	 * 
	 * @param source
	 * @param dst
	 */
	@Override
	public void copyFileFromAsset(InputStream source, File dst) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(dst);
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = source.read(buffer)) != -1) {
				fos.write(buffer, 0, length);
			}
			fos.flush();
		} catch (Exception e) {
			Utils.debug("copyFileFromAsset : " + e.toString());
			dst.delete();
		} finally {
			if (null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
					Utils.debug("copyFileFromAsset 1 : " + e.toString());
				}
			}
			if (null != source) {
				try {
					source.close();
				} catch (IOException e) {
					Utils.debug("copyFileFromAsset 2 : " + e.toString());
				}
			}

		}

	}

	/**
	 * 获取文件或者文件夹大小
	 * 
	 * @param path
	 * @return size (long/in bytes)
	 */
	@Override
	public long getFolderOrFileSize(String path) {

		File file = new File(path);
		long size = 0;
		File[] fileList = file.listFiles();
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isDirectory()) {
				size += getFolderOrFileSize(fileList[i].getPath());
			} else {
				size += fileList[i].length();
			}
		}
		return size;
	}

	/**
	 * 当缓存文件夹达到阈值，清理工作
	 * 
	 * @param millSec
	 *            前的文件删除
	 */
	private void doCleanCache(long millSec, String path) {
		long curtime = System.currentTimeMillis();
		File file = new File(path);
		File[] fileList = file.listFiles();
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isDirectory()) {
				doCleanCache(millSec, fileList[i].getAbsolutePath());
			} else {
				if (fileList[i].lastModified() < curtime - millSec) {
					fileList[i].delete();
				}
			}
		}
	}

	/**
	 * 
	 * @param path
	 * @param size
	 *            (in bytes)
	 * @param millSecAgo
	 * @author kooze
	 */
	@Override
	public void cleanCache(String path, long size, long millSecAgo) {
		if (getFolderOrFileSize(path) > size) {
			doCleanCache(millSecAgo, path);
		}
	}

	/**
	 * 
	 * @param path
	 * @author kooze
	 */
	@Override
	public void createNoMediaFile(String path) {
		File file = new File(path);
		if (!file.exists() || !file.isDirectory()) {
			return;
		}
		File nomedia = new File(path + "/.nomedia");
		if (!nomedia.exists()) {
			try {
				nomedia.createNewFile();
			} catch (IOException e) {
				Utils.debug("createNoMedia : " + e.toString());
			}
		}
	}

	public String getStringFromAssets(String fileName) {
		String result = "";
		try {
			InputStream in = sContext.getAssets().open(fileName);
			// 获取文件的字节数
			int lenght = in.available();
			// 创建byte数组
			byte[] buffer = new byte[lenght];
			// 将文件中的数据读到byte数组中
			in.read(buffer);
			result = EncodingUtils.getString(buffer, DEFAULT_ENCODING);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
