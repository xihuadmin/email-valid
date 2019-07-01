package com.zjzc.manage.utils.sftp;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


public class ZipCompress {

	/**
	 * 程序实现了ZIP压缩。共分为2部分 ： 压缩（compression）与解压（decompression）
	 * <p>
	 * 大致功能包括用了多态，递归等JAVA核心技术，可以对单个文件和任意级联文件夹进行压缩和解压。 需在代码中自定义源输入路径和目标输出路径。
	 * <p>
	 * 在本段代码中，实现的是压缩部分；解压部分见本包中Decompression部分。
	 * 
	 * @author
	 * 
	 */

	private static int k = 1; // 定义递归次数变量

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		decompression("D:\\kkkkk\\1.zip","D:\\kkkkk\\");
		//compression("E:\\logs\\BatReturn3050201607121468319192.zip", "E:\\logs\\BatReturn3050201607121468319192.txt");
	}

	private static void zip(String zipFileName, File inputFile)
			throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));
		BufferedOutputStream bo = new BufferedOutputStream(out);
		zip(out, inputFile, inputFile.getName(), bo);
		bo.close();
		out.close(); // 输出流关闭
	}

	private static void zip(ZipOutputStream out, File f, String base,
			BufferedOutputStream bo) throws Exception { // 方法重载
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			if (fl.length == 0) {
				out.putNextEntry(new ZipEntry(base + "/")); // 创建zip压缩进入点base
				System.out.println(base + "/");
			}
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + "/" + fl[i].getName(), bo); // 递归遍历子文件夹
			}
			System.out.println("第" + k + "次递归");
			k++;
		} else {
			out.putNextEntry(new ZipEntry(base)); // 创建zip压缩进入点base
			FileInputStream in = new FileInputStream(f);
			BufferedInputStream bi = new BufferedInputStream(in);
			int b;
			while ((b = bi.read()) != -1) {
				bo.write(b); // 将字节流写入当前zip目录
			}
			bi.close();
			in.close(); // 输入流关闭
		}
	}
	
	/**
	 * 压缩
	 * @param zipPath 压缩后的地址
	 * @param filePath 压缩的文件或者文件夹
	 */
	public static void compression(String zipPath,String filePath){
		try {
			zip(zipPath, new File(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 解压
	 * @param zipPath 解压的地址
	 * @param filePath 解压后的文件地址
	 * return 解压后的文件名称
	 */
	public static String decompression(String zipPath,String filePath){
		long startTime=System.currentTimeMillis();
		StringBuffer fileName = new StringBuffer();
        try {
            ZipInputStream Zin=new ZipInputStream(new FileInputStream(zipPath));//输入源zip路径 
            BufferedInputStream Bin=new BufferedInputStream(Zin);
            File Fout=null;
            ZipEntry entry;
            try {
                while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){
                    Fout=new File(filePath,entry.getName());
                    if(!Fout.exists()){
                        (new File(Fout.getParent())).mkdirs();
                    }
                    FileOutputStream out=new FileOutputStream(Fout);
                    BufferedOutputStream Bout=new BufferedOutputStream(out);
                    int b;
                    while((b=Bin.read())!=-1){
                        Bout.write(b);
                    }
                    Bout.close();
                    out.close();
                    fileName.append(Fout.getName()).append(",");
                    //System.out.println(Fout+"解压成功"+Fout.getName());
                }
                Bin.close();
                Zin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long endTime=System.currentTimeMillis();
        System.out.println("耗费时间： "+(endTime-startTime)+" ms");
        return fileName.toString();
	}
}
