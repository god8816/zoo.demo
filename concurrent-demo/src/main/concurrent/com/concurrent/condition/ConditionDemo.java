package com.concurrent.condition;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 是一个信号线程控制与lock搭配使用，当执行条件不满足的时候，线程wait，当执行条件达到释放信号执行。
 * 优秀博文：https://www.cnblogs.com/boothsun/p/8531665.html
 * */
public class ConditionDemo {

	public static void main(String[] args) throws Exception{
		final ReentrantLock reentrantLock = new ReentrantLock();
	    final Condition condition = reentrantLock.newCondition(); 
		
	    AtomicInteger count = new AtomicInteger(0) ;
	    new Thread(()->{
	        reentrantLock.lock();
	        try {
	            for(;count.intValue() < 10; count.incrementAndGet()) {
	                Thread.sleep(1000L);
	            }
	            System.out.println(Thread.currentThread().getName() + "当前count=" +count.intValue() + "已达目标 准备发出信号量");
	            condition.signal();
	        }catch (Exception e) {
	            e.printStackTrace();
	        }finally {
	            reentrantLock.unlock();
	        }
	    }).start();

	    if(count.intValue() <= 10) {
	        reentrantLock.lock();
	        System.out.println(Thread.currentThread().getName() + "当前count=" + count.intValue() + "。准备await");
	        condition.await(); //主线程wait，等待signal释放信号
	        reentrantLock.unlock();
	        System.out.println("当前count=" + count.intValue());
	    }
	}

}
