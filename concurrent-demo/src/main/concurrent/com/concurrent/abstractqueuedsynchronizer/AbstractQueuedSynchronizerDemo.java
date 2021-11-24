package com.concurrent.abstractqueuedsynchronizer;

import java.util.concurrent.locks.ReentrantLock;

/**
 * AQS类用途：
 * AQS优秀博文：
 *   1：https://www.jianshu.com/p/ec47f71c3993
 *   2：https://www.infoq.cn/article/jdk1.8-abstractqueuedsynchronizer
 *   3：https://www.jianshu.com/p/cc308d82cc71
 *   4：https://blog.csdn.net/qq_40685275/article/details/99719415
 * */
public class AbstractQueuedSynchronizerDemo {

	 private static ReentrantLock lock = new ReentrantLock();

	    public static void main(String[] args) { 
	    	 
	        for (int i = 0; i < 5; i++) {
	            Thread thread = new Thread(() -> {
	                lock.lock();
	                try {
	                    Thread.sleep(1000);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                } finally {
	                    lock.unlock();
	                }
	            });
	            thread.start();
	        }
	    }
	    
	    
	    protected static boolean tryAcquire(int arg) {
	        throw new UnsupportedOperationException();
	    }

}
