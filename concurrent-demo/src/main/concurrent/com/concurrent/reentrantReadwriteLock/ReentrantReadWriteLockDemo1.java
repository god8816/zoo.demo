package com.concurrent.reentrantReadwriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

/**
 * 可重入锁
 * */
public class ReentrantReadWriteLockDemo1 {

	static ReadLock readerLock = new ReentrantReadWriteLock().readLock();
	
	public static void main(String[] args) {
		for (int i = 0; i < 30; i++) {
			new Thread(new  Runnable() {
				@Override
				public void run() {
					try {
						 String threadName = Thread.currentThread().getName();
						 ReentrantReadWriteLockDemo1.lockCrs(threadName);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
	
	
	/**
	 * lock实现重入锁
	 * 备注一定要释放锁，否则会线程等待
	 * */
	public static void lockCrs(String threadName) throws InterruptedException {
		try {
			readerLock.lock();
			Thread.sleep(3000);
			System.out.println("ReentrantLock方法，线程名称：" +threadName+"，获取锁，锁对象：" + readerLock);
		} finally {
			//注释会线程等待
			readerLock.unlock();
		}
	}
	
	

}
