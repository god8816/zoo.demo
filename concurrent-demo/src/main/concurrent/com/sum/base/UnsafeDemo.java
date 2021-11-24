package com.sum.base;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

public class UnsafeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*long address = UnsafeDemo.allocateMemory();
		long newAddress = UnsafeDemo.reallocateMemory(address,1L);
		UnsafeDemo.freeMemory(address);*/
		UnsafeDemo.setMemory();
		System.out.println(123);
	}
	
	//分配内存返回内存地址
	@SuppressWarnings("restriction")
	public static Long allocateMemory() {
		try {
			Field f = Unsafe.class.getDeclaredField("theUnsafe");
	        f.setAccessible(true);
	        Unsafe us = (Unsafe) f.get(null);
	        long id = us.allocateMemory(1024 * 1024 * 1024);
	        System.out.println(id); //4927422464 4900220928
	        return id;
		} catch (Exception  e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//释放内存
	@SuppressWarnings("restriction")
	public static void freeMemory(long address) {
		try {
			Field f = Unsafe.class.getDeclaredField("theUnsafe");
	        f.setAccessible(true);
	        Unsafe us = (Unsafe) f.get(null);
	        us.freeMemory(address);
	        System.out.println(address+"地址内存已经回收");
		} catch (Exception  e) {
			e.printStackTrace();
		}
	}
	
	//扩充内存返回新的内存地址
	@SuppressWarnings("restriction")
	public static Long reallocateMemory(long address, long bytes) {
		try {
			Field f = Unsafe.class.getDeclaredField("theUnsafe");
	        f.setAccessible(true);
	        Unsafe us = (Unsafe) f.get(null);
	        long id = us.reallocateMemory(address,bytes);
	        return id;
		} catch (Exception  e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//扩充内存返回新的内存地址
	@SuppressWarnings("restriction")
	public static void setMemory() {
		try {
			Field f = Unsafe.class.getDeclaredField("theUnsafe");
	        f.setAccessible(true);
	        Unsafe us = (Unsafe) f.get(null);
	        long address = us.allocateMemory(1);
	        us.setMemory(address, 8L, (byte) 999999999);
		} catch (Exception  e) {
			e.printStackTrace();
		}
	}
}
