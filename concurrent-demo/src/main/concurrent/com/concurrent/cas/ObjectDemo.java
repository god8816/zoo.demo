package com.concurrent.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * CAS类用途：
 * CAS优秀博文：
 *   1：https://blog.csdn.net/qq_37113604/article/details/81582784
 * */
public class ObjectDemo {

	    private static AtomicInteger ctl = new AtomicInteger(1000);
	 
	    public static void main(String[] args) { 
	    	     
	    	    for(int i=0;i<100;i++) {
	    	    	   Thread thread = new Thread(new Runnable() {
						
						@Override
						public void run() {
							ctl.compareAndSet(ctl.get(), ctl.incrementAndGet());
							System.out.println(ctl.get());
						}
			   });
	    	    	   thread.start();
	    	    }
	    }
	    
	

}
