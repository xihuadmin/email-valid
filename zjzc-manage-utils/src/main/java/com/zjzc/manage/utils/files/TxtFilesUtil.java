package com.zjzc.manage.utils.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.commons.lang3.StringUtils;

/**
 * txt文件操作类
 * 
 * @author qinzihao
 */
public class TxtFilesUtil {


	/**
	 * 创建文本文件
	 * @param filePath
	 * @return
	 */
	public static boolean creatTxtFile(String filePath) {
		try {
			if (StringUtils.isBlank(filePath)) {
				return false;
			}
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 读取文本文件
	 * @param filePath
	 * @return
	 */
	public static String readTxtFile(String filePath) {
		String result = "";
		String line;
		FileReader fileread;
		BufferedReader bufread = null;
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				return null;
			}
			fileread = new FileReader(file);
			bufread = new BufferedReader(fileread);
			
			while ((line = bufread.readLine()) != null) {
				result = result + line + "\r\n";
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (bufread != null) {
					bufread.close();
				}
			} catch (Exception e) {
				
			}
		}
		
	}

	/**
	 * 写入文本文件
	 * @param filePath - 文件绝对路径（包括文件名）
	 * @param content - 写入的文件内容
	 * @param and - false：覆盖原文件，true：在原基础上写入
	 * @throws IOException
	 */
	public static void writeTxtFile(String filePath, String content, boolean and) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			file.createNewFile();
		}
		// 读取原有内容
		String oldContent = readTxtFile(filePath);
		if (!and) {
			oldContent = "";
		}
		if (!"".equals(oldContent)) {
			oldContent = "\r\n";
		}
		String newContent = oldContent + content;
		RandomAccessFile rmf = null;
		try {
			// 写入内容
			rmf = new RandomAccessFile(file, "rw");
			rmf.writeBytes(newContent);
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (rmf != null) {
				try {
					rmf.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}
	}


	public static void main(String[] args) {
		
	}
}
