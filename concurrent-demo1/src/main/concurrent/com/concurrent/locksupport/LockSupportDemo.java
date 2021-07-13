package com.concurrent.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * 1： 解决线程阻塞与唤醒，LockSupport是jdk比较底层的类，在并发包到处都有LockSupport实现案例，其功能与wait,notify相似。
   2： 场景区别：wait,notify只能在对象获取锁的时候使用，LockSupport可以在任何场景使用。
 * */
public class LockSupportDemo {

	public static void main(String[] args) throws InterruptedException {
		MyThread myThread = new MyThread();
		Thread td = new Thread(myThread);
		td.start();
		
		for(int i=0;i<10;i++) {
			Thread.sleep(1000);
		}
		LockSupport.unpark(td); //解锁
		System.out.println("MyThread将要解除阻塞状态。");
	}
	
	
	static class MyThread implements Runnable{
		@Override
		public void run() {
			System.out.println("MyThread进入阻塞状态。");
			LockSupport.park(); //锁定
			System.out.println("MyThread解除阻塞状态。");
		}
	}
}
