package com.concurrent.thread;

import com.alibaba.fastjson.JSON;

/*
 * setUncaughtExceptionHandler 优先级高于 setDefaultUncaughtExceptionHandler
 * */
public class ExceptionThead {

	public static void main(String[] args) {
		new ExceptionThead().exceptionTest();
	}

	
	
	@SuppressWarnings("static-access")
	public void exceptionTest() {
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
			  	JSON.parseArray("12324");
			}
		});
		/*thread1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("我是setUncaughtExceptionHandler");
			}
		});*/
		
		//获取main线程
		//Thread.currentThread();
		
		//thread1线程
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("我是setDefaultUncaughtExceptionHandler");	
			}
		});
		thread1.start();
		System.out.println(1234);
	}
}
