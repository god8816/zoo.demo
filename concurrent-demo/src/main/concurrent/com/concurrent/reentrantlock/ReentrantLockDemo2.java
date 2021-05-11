package com.concurrent.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁:调用锁的顺序和输出顺序一致
 * 非公平锁：反之
 * */
public class ReentrantLockDemo2 {

	public static void main(String[] args) {
		//公平锁
		//gps();
		
		//非公平锁
		fgps();
		
		//无锁
		//ws();
		
		
	}
	
	/**
	 * 公平锁实现
	 * */
	public static void gps() {
        ReentrantLock lock = new ReentrantLock();
        for (int i = 1; i <= 10; i++) {
        	    String num = String.valueOf(i);
            new Thread(new Runnable() {
				@Override
				public void run() {
					lock.lock();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(num);
					lock.unlock();
					
				}
			},num).start();
        }
	}
	
	/**
	 * 非公平锁实现
	 * */
	public static void fgps() {
        ReentrantLock lock = new ReentrantLock();
        for (int i = 1; i <= 10; i++) {
        	    String num = String.valueOf(i);
            new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						lock.lockInterruptibly();
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(num);
					lock.unlock();
					
				}
			},num).start();
        }
	}
	
	
	public static void ws() {
        for (int i = 1; i <= 10; i++) {
        	    String num = String.valueOf(i);
            new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(num);
					
				}
			},num).start();
        }
	}

}
