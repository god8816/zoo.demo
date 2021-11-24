package com.concurrent.thread;

/**
 * 
 * */
public class InterruptThread {
    /**
     * 通过线程中断控制线程是否执行
     * */
	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				for(;;) {
					while (!Thread.currentThread().isInterrupted()) {
						 System.out.println("我没有中断，不要停");
					}
				}
			}
		});
		thread.start();
		thread.interrupt();
		Boolean threadStatus = thread.isInterrupted();
		System.out.println(threadStatus);
	}

}
