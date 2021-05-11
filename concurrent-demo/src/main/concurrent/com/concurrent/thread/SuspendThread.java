package com.concurrent.thread;


/**
 * 线程挂起 后面的代码阻塞 且suspend不释放锁 TODO 发现thread一个bug，当两个锁获取中间不做任何操作suspend会释放掉锁
 * */
public class SuspendThread {
	
	Object o = new Object();
	

	public static void main(String[] args) {
	    new SuspendThread().holdsLockTest();
	}
	
	public void holdsLockTest() {
		new Thread(new Runnable() {
	        @Override
	        public void run() {
	            synchronized(o) {
	             	Thread.currentThread().suspend();
	                System.out.println("Son ThreadID="+Thread.currentThread().getId()+",线程获取锁状态："+Thread.holdsLock(o));
	                for(;;) {}
	            }
	        }
	    }).start();
		
		//此处无代码的话，锁会释放掉 TODO way？
		//System.out.println("Prent ThreadID="+Thread.currentThread().getId()+",线程获取锁状态："+Thread.holdsLock(o));
		
		synchronized (o) {
			System.out.println("Prent ThreadID="+Thread.currentThread().getId()+",线程获取锁状态："+Thread.holdsLock(o));
		}
	}

}
