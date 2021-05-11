package com.concurrent.unsafe;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 无界非阻塞队列，注意内存不要爆
 * 效率： 1千万数据处理，1生产者5消费者，需要1195毫秒，1秒。
 * */
public class ConcurrentLinkedQueueDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int tjNum = 0; 
		final ConcurrentLinkedQueue clq = new ConcurrentLinkedQueue();
		
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
                  System.out.println("队列大小为0，说明消费完毕："+clq.size());
                }
            }
        }).start();
	}

}
