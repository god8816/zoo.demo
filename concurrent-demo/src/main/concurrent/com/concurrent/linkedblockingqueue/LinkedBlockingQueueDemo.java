package com.concurrent.linkedblockingqueue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 有界阻塞队列，默认数据量Integer.MAX_VALUE
 * 效率： 1千万数据处理，1生产者5消费者，需要4197毫秒，4秒。
 * */
public class LinkedBlockingQueueDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int tjNum = 0; 
		final LinkedBlockingQueue clq = new LinkedBlockingQueue();
		
		new Thread(new Runnable() {
            public void run() {
            	    Long time1 = System.currentTimeMillis();
                for(int i=0;i<10000000;i++) {
                  clq.offer(i);
                }
                Long time2 = System.currentTimeMillis();
                System.out.println("时间花销："+(time2-time1));
            }
        }).start();
		
		
		
		new Thread(new Runnable() {
            public void run() {
                for(;;) {
                   clq.poll();
                }
            }
        }).start();
		
		
		new Thread(new Runnable() {
            public void run() {
                for(;;) {
                   clq.poll();
                }
            }
        }).start();
		
		
		new Thread(new Runnable() {
            public void run() {
                for(;;) {
                   clq.poll();
                }
            }
        }).start();
		
		new Thread(new Runnable() {
            public void run() {
                for(;;) {
                   clq.poll();
                }
            }
        }).start();
		
		new Thread(new Runnable() {
            public void run() {
                for(;;) {
                  clq.poll(); 
                }
            }
        }).start();
		
		new Thread(new Runnable() {
            public void run() {
                for(;;) {
                	  try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                  System.out.println("队列大小："+clq.size());
                }
            }
        }).start();
	}

}
