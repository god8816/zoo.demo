package com.concurrent.thread;

/**
 * 唤醒会终止阻塞 测试线程如使用了锁 sleep，同步锁的wait，socket的receiver，accept等方法时会清除中断状态
 * */
public class InterruptWaitThread {
	

	public static void main(String[] args) {
         new InterruptWaitThread().interruptWaitTest();
	}
	
	
	//子线程定义
	Thread sonThread = null;
	Object o = new Object();
	 
	public void interruptWaitTest() {
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
			  	 sonThread = Thread.currentThread();
			  	 sonThread.interrupt();
			  	 try {
					sonThread.wait(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread1.start();
		//此处断点需要等待10秒，等待sonThread自动唤醒
		Boolean threadStatus = sonThread.isInterrupted();
		System.out.println("线程1中断状态："+threadStatus);  
	}

}
