package com.zjzc.manage.utils.files;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

/**
 * 文件处理工具,用于获取当前登录用户目录
 * 
 * @author lijun,time 2015-2-28 下午5:22:33
 * 
 */
public class FileUtils {

//    public static final String HOME_CONFIG_DIR = "ps-platform";

	private static String FILE_SEPARATOR = System.getProperty("file.separator");
	private static String USER_HOME = System.getProperty("user.home");
	private static String OS_TYPE = System.getProperty("os.name");

	/**
	 * <pre>
	 * 获取配置文件所在目录
	 *  windows系统： %USER_HOME%/.$PATH/
	 *  linux系统：/opt/$PATH/config/
	 * 
	 * @param dirName 工作目录名称
	 * @return
	 *
	 * </pre>
	 */
	private static String getHome(String dirName) {

		String homePath;
		if (OS_TYPE.toLowerCase().contains("windows")) {
			homePath = USER_HOME + (USER_HOME.endsWith(FILE_SEPARATOR) ? "" : FILE_SEPARATOR) + "." + dirName + FILE_SEPARATOR;
		} else {
			homePath = FILE_SEPARATOR + "opt" + FILE_SEPARATOR + dirName + FILE_SEPARATOR + "config" + FILE_SEPARATOR;
		}

		createDir(homePath);
		return homePath;
	}

	/**
	 * 获取配置文件放置的路径
	 * 
	 * @return 返回目录名称
	 */
	public static String getConfigPath(String pathName) {
		return getHome(pathName);
	}

	/**
	 * 递归创建文件目录
	 * 
	 * @param filePath
	 * @return
	 */
	public static File createDir(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdir();
		}
		return file;
	}

	/**
	 * 获取文件名称，不带文件后缀
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileName(final File file) {
		String fileName = file.getName();
		String simpleName = fileName.substring(fileName.indexOf(File.separator) + 1);
		String name = simpleName.substring(0, simpleName.lastIndexOf('.'));
		if (StringUtils.isEmpty(name))
			return simpleName;
		return name;
	}

	/**
	 * 获取文件的后缀名,以小写返回
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getExt(final File file) {
		String fileName = file.getName();
		return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
	}
}
