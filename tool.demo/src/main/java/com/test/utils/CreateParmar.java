package com.test.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CreateParmar {

	public static void main(String[] args) throws IOException {
		
		StringBuilder buder = new StringBuilder("");
		for(int i=1;i<=12;i++) {
			 
			 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			 Calendar todayCal = new GregorianCalendar(2020, 01, 01,0,0,0);
			 todayCal.add(Calendar.MONTH, i-1);
			 String today = format.format(todayCal.getTime());
			 
			 Calendar tomorrowCal = new GregorianCalendar(2020, 01, 01,0,0,0);
			 tomorrowCal.add(Calendar.MONTH, i); 
			 String tomorrow = format.format(tomorrowCal.getTime());
			 buder.append(today+","+tomorrow + System.getProperty("line.separator"));
			   
		}
		
		createFile("111",buder.toString());

	}
	
	
	public static void createFile(String tableName,String contentSql) throws IOException {
	    String filePath = "/logs/";
	    File dir = new File(filePath);
	    // 一、检查放置文件的文件夹路径是否存在，不存在则创建
	    if (!dir.exists()) {
	        dir.mkdirs();// mkdirs创建多级目录
	    }
	    File checkFile = new File(filePath + "/"+tableName+".cvs");
	    FileWriter writer = null;
	    try {
	        // 二、检查目标文件是否存在，不存在则创建
	        if (!checkFile.exists()) {
	            checkFile.createNewFile();// 创建目标文件
	        }
	        // 三、向目标文件中写入内容
	        // FileWriter(File file, boolean append)，append为true时为追加模式，false或缺省则为覆盖模式
	        writer = new FileWriter(checkFile, false);
	        writer.append(contentSql);
	        writer.flush();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (null != writer)
	            writer.close();
	    }
	}

}
