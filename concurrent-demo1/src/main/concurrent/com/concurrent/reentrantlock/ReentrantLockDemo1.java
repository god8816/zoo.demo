package com.concurrent.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 * */
public class ReentrantLockDemo1 {

	static ReentrantLock lock = new ReentrantLock();
	
	public static void main(String[] args) {
		//syn实现可重入锁
		for (int i = 0; i < 30; i++) {
			new Thread(new  Runnable() {
				@Override
				public void run() {
					try {
						 String threadName = Thread.currentThread().getName();
						 ReentrantLockDemo1.lockCrs(threadName);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
	
	/**
	 * syn实现重入锁
	 * */
	public static void synCrs(String threadName) throws InterruptedException {
        synchronized (ReentrantLockDemo3.class) {
				Thread.sleep(3000);
				System.out.println("synchronized方法，线程名称：" +threadName+"，获取锁");
		}
	}
	
	/**
	 * lock实现重入锁
	 * 备注一定要释放锁，否则会线程等待
	 * */
	public static void lockCrs(String threadName) throws InterruptedException {
		try {
			lock.lock();
			Thread.sleep(3000);
			System.out.println("ReentrantLock方法，线程名称：" +threadName+"，获取锁，锁对象：" + lock);
		} finally {
			//注释会线程等待
			lock.unlock();
		}
	}

}
