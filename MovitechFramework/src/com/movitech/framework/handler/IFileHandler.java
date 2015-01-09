package com.movitech.framework.handler;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Oct 28, 2014, 4:16:57 PM
 * @Description: IFileHandler是文件处理者的接口类
 *
 **/

public interface IFileHandler {

	public enum CacheDir {
		IMAGE("/.image/"), LOG("/.log/"), AUDIO("/.audio/"), VIDEO("/.video/"), AVATAR(
				"/.avatar/");

		private String dir;

		private CacheDir(String dir) {
			this.dir = dir;
		}

		@Override
		public String toString() {
			return String.valueOf(this.dir);
		}

		public String getDir() {
			return dir;
		}
	}

	public enum DataDir {
		BACKUP("/.backup/"), GIF("/.gif/"), FLAGS("/.flags/"), DATABASE("/.db/"), EMOJI(
				"/.emoji/");

		private String dir;

		private DataDir(String dir) {
			this.dir = dir;
		}

		@Override
		public String toString() {
			return String.valueOf(this.dir);
		}

		public String getDir() {
			return dir;
		}
	}

	public String getCacheDirByType(CacheDir dir);

	public String getFileDirByType(DataDir dir);

	public String getFileDir();

	public String getCacheDir();

	public boolean isFileExists(String filePath);

	public boolean createFolder(String folderPath);

	public boolean deleteFile(String filePathAndName);

	public boolean deleteFolder(String path, boolean recursive);

	public boolean deleteFilesInFloder(String path, boolean recursive);

	public String getFileBaseName(final String fileName);

	public String getFileExtension(final String fileName);

	public boolean copyFile(String sourcePath, String targetPath);

	public void copyFile(File src, File dst) throws IOException;

	public String getDirPath(String filePath);

	public String getFileName(String filePath);

	public String getFileNameWithoutSuffix(String path);

	public String readTextFile(String path);

	public ArrayList<String> listFiles(String path, FileFilter filter, boolean recursive);

	public boolean renameFile(String pathOri, String pathNew);

	public String getFileNameInUrl(String url);

	public String getDataSizeTxt(Long size);

	public void saveBitmap(String fullPath, Bitmap bitmap, CompressFormat format);

	public void saveBitmap(String fullPath, Bitmap bitmap, int quality, CompressFormat format);

	public void saveBitmap(File file, Bitmap bitmap, int quality, CompressFormat format);

	public Bitmap decodeFile(File f);

	public void copyFileFromAsset(InputStream source, File dst);

	public long getFolderOrFileSize(String path);

	public void cleanCache(String path, long size, long millSecAgo);

	public void createNoMediaFile(String path);

	public String getStringFromAssets(String fileName);

}
