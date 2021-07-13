package com.io.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化反序列化定义：序列化 (Serialization)是将对象的状态信息转换为可以存储或传输的形式的过程。在序列化期间，对象将其当前状态写入到临时或持久性存储区。以后，可以通过从存储区中读取或反序列化对象的状态，重新创建该对象。
 * fastjson定义：字符串处理工具类基于属性，序列化出字符串然后落库及传输
 * 
 * 序列化反序列化版本要保持一致：默认serialVersionUID = 1L和自定义
 * 相关文章：https://hollischuang.github.io/toBeTopJavaer/#/basics/java-basic/serialVersionUID-modify
 * fastjson：https://hollischuang.github.io/toBeTopJavaer/#/basics/java-basic/bug-in-fastjson
 * 如何利用fastjson攻击：后续研究
 * */
public class JDKSerializable {

	/**
	 * JDK对象序列化为二进制
	 * */
	public static void ObjToSerializable(String path,Object obj) {
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream(path);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(obj);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in"+path);
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	
	
	/**
	 * JDK对象序列化为二进制
	 * @param <T>
	 * @return 
	 * */
	public static <T> T SerializableToObj(String path,Class<T> clazz) {
	      T obj = null;
	      try
	      {
	         FileInputStream fileIn = new FileInputStream(path);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         obj = clazz.cast(in.readObject());
	         in.close();
	         fileIn.close();
	         return obj;
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return null;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println(clazz.getName()+" class not found");
	         c.printStackTrace();
	         return null;
	      }
	}
	
	
}
