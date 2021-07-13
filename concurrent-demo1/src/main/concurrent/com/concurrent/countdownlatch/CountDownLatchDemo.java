package com.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 线程计数器
 * 场景作用：当其他线程执行完毕后主线程执行
 * */
public class CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(new Runnable() {
            public void run() {
                System.out.println("我是子线程开始执行。。。。");
                try {
                    Thread.sleep(5000);//子线程处理事情
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();//每次减一
                System.out.println("我是子线程执行结束。。。。");
            }
        }).start();
        
        new Thread(new Runnable() {
            public void run() {
                System.out.println("我是子线程开始执行。。。。");
                try {
                    Thread.sleep(5000);//子线程处理事情
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println("我是子线程执行结束。。。。");
            }
        }).start();
        countDownLatch.await();//如果不为0，则一直等待
        System.out.println("主线程开始执行任务");
        
        for (int i = 0; i < 10; i++) {
            System.out.println("main: i="+i);
        }
    
	}

}
