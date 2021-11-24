package com.io.serializable;


import com.alibaba.fastjson.JSON;
import com.io.serializable.JDKSerializable;

public class JDKSerializableTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setName("丁志超");
		
		String userStr = JSON.toJSONString(user);
		System.out.println(userStr);
		
		JDKSerializable.ObjToSerializable("/Users/dzc/Downloads/11.txt", user);
		
		User user123 =JDKSerializable.SerializableToObj("/Users/dzc/Downloads/11.txt", User.class);
		
		
		
		
		String xx = "{\"name\":\"丁志超\"}";
		User userxx = JSON.parseObject(xx, User.class);
		System.out.println(123);
	}

}
