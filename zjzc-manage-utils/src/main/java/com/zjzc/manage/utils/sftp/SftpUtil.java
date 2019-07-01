package com.zjzc.manage.utils.sftp;

import com.zjzc.manage.utils.config.ValueStatic;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;

@Slf4j
public class SftpUtil {

	/** Sftp */
	ChannelSftp sftp = null;
	Session sshSession = null;
	Channel channel = null;
	/** 主机 */
	private String host = "";
	/** 端口 */
	private int port = 0;
	/** 用户名 */
	private String username = "";
	/** 密码 */
	private String password = "";

	/**
	 * 构造函数
	 * 
	 * @param host
	 *            主机
	 * @param port
	 *            端口
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * 
	 */
	public SftpUtil(String host, int port, String username, String password) {

		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	/**
	 * 连接sftp服务器
	 * 
	 * @throws Exception
	 */
	public void connect() throws Exception {

		JSch jsch = new JSch();
		sshSession = jsch.getSession(this.username, this.host,
				this.port);
		log.debug(SftpUtil.class + "Session created.");

		sshSession.setPassword(password);
		Properties sshConfig = new Properties();
		sshConfig.put("StrictHostKeyChecking", "no");
		sshSession.setConfig(sshConfig);
		sshSession.connect(1000);
		log.debug(SftpUtil.class + " Session connected.");

		log.debug(SftpUtil.class + " Opening Channel.");
		channel = sshSession.openChannel("sftp");
		channel.connect();
		this.sftp = (ChannelSftp) channel;
		log.debug(SftpUtil.class + " Connected to " + this.host + ".");
	}

	/**
	 * Disconnect with server
	 * 
	 * @throws Exception
	 */
	public void disconnect() throws Exception {
		if (this.sftp != null) {
			if (this.sftp.isConnected()) {
				this.sftp.disconnect();
			} else if (this.sftp.isClosed()) {
				log.debug(SftpUtil.class + " sftp is closed already");
			}
		}
		if (channel != null) {
			channel.disconnect();
		}
		if (sshSession != null) {
			sshSession.disconnect();
		}
	}

	/**
	 * 上传单个文件
	 * 
	 * @param directory
	 *            上传的目录
	 * @param uploadFile
	 *            要上传的文件
	 * 
	 * @throws Exception
	 */
	public void upload(String directory, String uploadFile) throws Exception {
		this.sftp.cd(directory);
		File file = new File(uploadFile);
		this.sftp.put(new FileInputStream(file), file.getName());
	}

	/**
	 * 上传目录下全部文件
	 * 
	 * @param directory
	 *            上传的目录
	 * 
	 * @throws Exception
	 */
	public void uploadByDirectory(String directory) throws Exception {

		String uploadFile = "";
		List<String> uploadFileList = this.listFiles(directory);
		Iterator<String> it = uploadFileList.iterator();

		while (it.hasNext()) {
			uploadFile = it.next().toString();
			this.upload(directory, uploadFile);
		}
	}

	/**
	 * 下载单个文件
	 * 
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件
	 * @param saveDirectory
	 *            存在本地的路径
	 * 
	 * @throws Exception
	 */
	public void download(String directory, String downloadFile,
			String saveDirectory) throws Exception {
		String saveFile = saveDirectory + "/" + downloadFile;
		log.info(directory);
		log.info(downloadFile);
		this.sftp.cd(directory);
		File file = new File(saveFile);
		File iodiy=new File(saveDirectory);
		if (!iodiy.exists()) {//判断是否存在upload文件夹，没有就创建文件夹
			iodiy.mkdirs();
		}
		if(!file.exists()){
		    file.createNewFile();
		}

		FileOutputStream f = new FileOutputStream(file);
		try {
			//downloadFile = downloadFile.replaceAll("[(]", "\\\\(").replaceAll("[)]", "\\\\)");
			log.info(downloadFile);
			this.sftp.get(downloadFile, f);
			f.close();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 下载目录下全部文件
	 * 
	 * @param directory
	 *            下载目录
	 * 
	 * @param saveDirectory
	 *            存在本地的路径
	 * 
	 * @throws Exception
	 */
	public void downloadByDirectory(String directory, String saveDirectory,String pname)
			throws Exception {
		String downloadFile = "";
		List<String> downloadFileList = this.listFiles(directory);
		Iterator<String> it = downloadFileList.iterator();
		int i = 0;
		Long start ,end;
		while (it.hasNext()) {
			start = System.currentTimeMillis();
			downloadFile = it.next().toString();
			if (downloadFile.toString().indexOf(".") < 0) {
				continue;
			}
			i++;
			this.download(directory, downloadFile, saveDirectory);
			end = System.currentTimeMillis();
			log.info("【下载文件】【{}】文件夹下第{}个文件下载完成,文件名称:{},下载时间:{}",pname,i,downloadFile,(end - start)/100 + "秒");
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param deleteFile
	 *            要删除的文件
	 * @return 删除验证，异常就为已经删除成功
	 */
	public boolean delete(String deleteFile){
		try {
			this.sftp.ls(deleteFile);
			this.sftp.rm(deleteFile);
			this.sftp.ls(deleteFile);
		}catch (Exception e){
			return true;
		}
		return false;
	}

	/**
	 * 列出目录下的文件
	 * 
	 * @param directory
	 *            要列出的目录
	 * 
	 * @return list 文件名列表
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<String> listFiles(String directory) throws Exception {
		try{
			this.sftp.cd(directory);
		}catch (Exception e){
			log.error("【查找文件{}】该文件夹不存在！",directory);
			return new ArrayList<>();
		}
		Vector fileList;
		List<String> fileNameList = new ArrayList<String>();
		try {
			fileList = this.sftp.ls(directory);
			if (fileList == null){
				return null;
			}
			Iterator it = fileList.iterator();

			while (it.hasNext()) {
				String fileName = ((LsEntry) it.next()).getFilename();
				if (".".equals(fileName) || "..".equals(fileName)) {
					continue;
				}
				fileNameList.add(fileName);

			}
		}catch (Exception e){
			log.error("【获取文件夹列表】异常:路径{},异常{}",directory,e);
		}
		return fileNameList;
	}

	/**
	 * 更改文件名
	 * 
	 * @param directory
	 *            文件所在目录
	 * @param oldFileNm
	 *            原文件名
	 * @param newFileNm
	 *            新文件名
	 * 
	 * @throws Exception
	 */
	public void rename(String directory, String oldFileNm, String newFileNm)
			throws Exception {
		this.sftp.cd(directory);
		this.sftp.rename(oldFileNm, newFileNm);
	}

	public void cd(String directory) throws Exception {
		this.sftp.cd(directory);
	}

	public InputStream get(String directory) throws Exception {
		InputStream streatm = this.sftp.get(directory);
		return streatm;
	}

	/**
	 * 上传文件到sftp文件夹
	 * @param uploadFile 上传文件路径
	 * type 1.结算文件 2.充值文件 3.提现文件
	 * @return
	 * @throws Exception
	 */
	public static boolean toupload(String uploadFile){
		SftpUtil sftp = null;
		try{
	        String ip = ValueStatic.getSftpIp().trim();
	        int port = ValueStatic.getSftpPort();
	        String username = ValueStatic.getSftpUserName().trim();
	        String password = new DesUtil().decrypt(ValueStatic.getSftpPassWord().trim());
	        String remotePath = ValueStatic.getSftpFilePath().trim();
	        sftp=new SftpUtil(ip, port, username, password);
	        sftp.connect();//连接sftp
	        String serviceFile= remotePath;
	        sftp.upload(serviceFile, uploadFile);
	        sftp.disconnect();
	        return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally {
			if(sftp != null){
				try {
					sftp.disconnect();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		//System.out.println(SftpUtil.toupload("D:\\import\\121212.txt"));//上传
		/*System.out.println(SftpUtil.todownload("zzptqlw1.QLW20171225020.zip.20171225192657580_1","D:\\upload\\"));
		File file = new File("D:\\upload\\zzptqlw1.QLW20171225020.zip.20171225192657580_1");
		if (file.exists() && file.isFile()) {
			log.info("【文件大小】：{}", (file.length() / (1024 * 1024)) + "M");
		}else {
			log.warn("【文件读取】失败：文件不存在");
		}*/
		System.out.print("djaskjdl(gfgdf)".replaceAll("[(]", "\\\\(").replaceAll("[)]", "\\\\)"));
		System.out.print("/data/sftp/sysadmin/storage/QLW钱来网/2018-01-15/(2018)湛仲字第E00268733号/身份证_李美_借字(天津)20171227001号.jpg".replaceAll("身份证_李美_借字(天津)20171227001号.jpg",""));
	}
	/**
	 * 下载文件到本地
	 * @param uploadFile 文件名称
	 * type 1.结算文件 2.充值文件 3.提现文件
	 * @param saveFile 保存文件路径
	 * @return boolean
	 */
	public static boolean todownload(String uploadFile,String saveFile){
		SftpUtil sftp=null;
		try{
			Long start = System.currentTimeMillis();
			String remotePath = ValueStatic.getSftpFilePath();
			sftp=new SftpUtil(ValueStatic.getSftpIp(), ValueStatic.getSftpPort(),
					ValueStatic.getSftpUserName(), new DesUtil().decrypt(ValueStatic.getSftpPassWord()));
			sftp.connect();//连接sftp
			String serviceFile= remotePath;
			sftp.download(serviceFile, uploadFile,saveFile);
			Long end = System.currentTimeMillis();
			log.info("【耗时】时间：{}",(end-start)/1000 + "秒");
			sftp.disconnect();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally {
			if(sftp != null){
				try {
					sftp.disconnect();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public static boolean todownloadByAllPath(String uploadFile,String saveFile,String fileanme){
		SftpUtil sftp = null;
		try{
			Long start = System.currentTimeMillis();
			sftp=new SftpUtil(ValueStatic.getSftpIp(), ValueStatic.getSftpPort(),
					ValueStatic.getSftpUserName(), new DesUtil().decrypt(ValueStatic.getSftpPassWord()));
			sftp.connect();//连接sftp
			String serviceFile= uploadFile.replaceAll(fileanme,"");
			sftp.download(serviceFile, fileanme,saveFile);
			sftp.disconnect();
			Long end = System.currentTimeMillis();
			log.info("【耗时】时间：{}",(end-start)/1000 + "秒");
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally {
			if(sftp != null){
				try {
					sftp.disconnect();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}

	}
}
