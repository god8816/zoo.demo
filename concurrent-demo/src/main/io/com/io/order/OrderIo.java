package com.io.order;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 顺序IO测试
 * 顺序IO的读写在中间件使用的很频繁，尤其是在队列中。几乎所有的队列（kafka,qmq等使用文件存储消息）都采用了顺序IO读写。
 * 与随机读写不同的是，顺序读写是优先分配一块文件空间，然后后续内容追加到对应空间内。
 * 在使用顺序IO进行文件读写时候，需要知道上次写入的地方，所以需要维护一个索引或者轮询获得一个没有写入位置
 * 读写测试结果：4104毫秒 1.55G文件 
 * */
public class OrderIo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		long startTime = System.currentTimeMillis();
		String fileStr = "/Users/dzc/Desktop/视频/123.zip";
		byte[] fileByte = OrderIo.fileOrderRead(fileStr, 0);
		
		int index = 0;
		String filePath = "/Users/dzc/Downloads/11.txt";
		String content = "33333-2222222-1111111-abc123ABC--abc123ABC-abc123ABC-abc123ABC-abc123ABC-abc123ABC-abc123ABC";
		long xx = OrderIo.fileOrderWrite(filePath,Integer.MAX_VALUE,fileByte, index);
		long endTime = System.currentTimeMillis();
		
		System.out.println("文件读写耗时："+(endTime - startTime));
	}
	
	
	/**
	 * 顺序写
	 * @param filePath 文件路径
	 * @param initSize 申请文件空间大小
	 * @param bs 写入内容
	 * @param index 写入文件位置
	 * @author dzc
	 * @return long 文本末尾长度
	 * */
	public static long fileOrderWrite(String filePath,long initSize, byte[] bs, int index) {
	     File file = new File(filePath);
	     RandomAccessFile randomAccessTargetFile;
	     MappedByteBuffer map;
	     try {
	          randomAccessTargetFile = new RandomAccessFile(file, "rw");
	          FileChannel targetFileChannel = randomAccessTargetFile.getChannel();
	          map = targetFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, initSize);
	          map.position(index);
	          map.put(bs);
	          randomAccessTargetFile.close();
	          return map.position();
	     } catch (IOException e) {
	          e.printStackTrace();
	     } finally {
	     }
	     return 0L;
	}
	
	
	
	/**
	 * 顺序读-文件大小不能超过Int max byte
	 * @param filePath
	 * @param index 
	 * @author dzc
	 * @return String
	 * */
	public static byte[] fileOrderRead(String filePath, long index) {
	     File file = new File(filePath);
	     RandomAccessFile randomAccessTargetFile;
	     MappedByteBuffer map;
	     try {
	          randomAccessTargetFile = new RandomAccessFile(file, "rw");
	          FileChannel targetFileChannel = randomAccessTargetFile.getChannel();
	          map = targetFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, index);
	          byte[] byteArr = new byte[(int)file.length()];
	          map.get(byteArr, 0, (int) index);
	          return byteArr;
	     } catch (IOException e) {
	          e.printStackTrace();
	     } finally {
	     }
	     return null;
	}

}
