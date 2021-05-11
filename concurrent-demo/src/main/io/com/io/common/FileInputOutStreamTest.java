package com.io.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 缓冲字符流-处理字符相关
 * 读写测试结果：数据类型不一样未测试
 *  读写测试结果：9903毫秒 1.55G文件 
 * */
public class FileInputOutStreamTest {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		String fileStr = "/Users/dzc/Desktop/视频/123.zip";
		byte[] fileByte = FileInputOutStreamTest.fileRead(fileStr);
	
		String filePath = "/Users/dzc/Downloads/11.txt";
		FileInputOutStreamTest.fileWrite(filePath,fileByte);
		long endTime = System.currentTimeMillis();
		
		System.out.println("文件读写耗时："+(endTime - startTime));
	}
	
	public static void fileWrite(String filePath, byte[]  content) {
	     FileOutputStream outputStream = null;
	     try {
	         File file = new File(filePath);
	         boolean isCreate = file.createNewFile();//创建文件
	         if (isCreate) {
	             outputStream = new FileOutputStream(file);//形参里面可追加true参数，表示在原有文件末尾追加信息
	             outputStream.write(content);
	         }else {
	             outputStream = new FileOutputStream(file,true);//表示在原有文件末尾追加信息
	             outputStream.write(content);
	         }
	     } catch (Exception e) {
	         e.printStackTrace();
	     } finally {
	         try {
	             outputStream.close();
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
	     }
	 }
		
	 public static byte[] fileRead(String filePath) {
	     File file = new File(filePath);
	     if (file.exists()) {
	         try {
	             //创建FileInputStream对象，读取文件内容
	             FileInputStream fis = new FileInputStream(file);
	             byte[] bys = new byte[(int)file.length()];
	             fis.read(bys, 0, bys.length);
	             return bys;
	         } catch (IOException ex) {
	             ex.printStackTrace();
	         }
				
	     }
		return null;
	 } 

}
