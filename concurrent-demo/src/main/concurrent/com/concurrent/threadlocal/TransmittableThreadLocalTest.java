package com.concurrent.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.ttl.TransmittableThreadLocal;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * */
public class TransmittableThreadLocalTest {
	

	//private static TransmittableThreadLocal<Integer> requestIdThreadLocal = new TransmittableThreadLocal<>();
	//private static ThreadLocal<Integer> requestIdThreadLocal = new ThreadLocal<>();
	private static InheritableThreadLocal<Integer> requestIdThreadLocal = new InheritableThreadLocal<>();
	
    
	/**
	 * 消费线程池
	 * */
	ExecutorService consumePoolExecutors = Executors.newFixedThreadPool(10);
	/**
	 * 任务处理线程池
	 * */
	ExecutorService handPoolExecutors = Executors.newFixedThreadPool(10);	
	
	
	
	public static void main(String[] args) {
		TransmittableThreadLocalTest transmittableThreadLocal = new TransmittableThreadLocalTest();
		transmittableThreadLocal.intThread();
		transmittableThreadLocal.consumePoolThread();
		transmittableThreadLocal.consumePoolExecutors.shutdown();
		transmittableThreadLocal.handPoolExecutors.shutdown();
	}


	private void intThread() {
		requestIdThreadLocal.set(0);
	}
	
	/**
	 * 消费者线程池
	 * */
	private void consumePoolThread() {
		for (int i = 0; i < 10; i++) {
			consumePoolExecutors.submit(new ConsumeThead(i) );
		}
	}
	
	@Data
	@AllArgsConstructor
	class ConsumeThead implements Runnable{
		
		private int i;
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + "=====" + i);
			requestIdThreadLocal.set(i);
			
			handPoolExecutors.submit(new HandThead(Thread.currentThread().getName()));
		}
	}
	
	
	@Data
	@AllArgsConstructor
	class HandThead implements Runnable{
		
		private String threadName;
		
		@Override
		public void run() {
			System.out.println("threadName："+threadName+"，任务处理线程池获取值："+requestIdThreadLocal.get());
		}
	}
}
