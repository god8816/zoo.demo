package com.concurrent.thread;


/**
 * 线程挂起线程 给恢复运行
 * */
public class SuspendAndResumeThread {
	
	Object o = new Object();
	

	public static void main(String[] args) {
	    new SuspendAndResumeThread().holdsLockTest();
	}
	
	Thread suspendThread = null;
	
	@SuppressWarnings("deprecation")
	public void holdsLockTest() {
		new Thread(new Runnable() {
	        @Override
	        public void run() {
	            synchronized(o) {
	             	suspendThread = Thread.currentThread();
	             	Thread.currentThread().suspend();
	                for(;;) {
	                 	System.out.println("Son ThreadID="+Thread.currentThread().getId()+",线程获取锁状态："+Thread.holdsLock(o));
	                }
	            }
	        }
	    }).start();
		
		suspendThread.resume();
	}

}
