package com.io.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * 缓冲字符流-处理字符相关
 * 读写测试结果：数据类型不一样未测试
 * */
public class BufferedWriterTest {

	public static void main(String[] args) {
		
	}
	
    public static void fileWrite(String filePath, String content) {
        File file = new File(filePath);
        //创建FileWriter对象
        BufferedWriter writer = null;
        try {
             //如果文件不存在，创建文件
             if (!file.exists())
                  file.createNewFile();
             writer = new BufferedWriter(new FileWriter(file));
             writer.write(content);//写入内容
             writer.flush();
             writer.close();
        } catch (IOException e) {
             e.printStackTrace();
        }
   }
    
    
   public static void fileRead(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
             try {
                  //创建FileReader对象，读取文件中的内容
                  BufferedReader reader = new BufferedReader(new FileReader(file));
                  String line;
                  while ((line = reader.readLine()) != null) {
                       System.out.print(line);
                  }
                  reader.close();
             } catch (IOException ex) {
                  ex.printStackTrace();
             }
                
        }
   }

}
