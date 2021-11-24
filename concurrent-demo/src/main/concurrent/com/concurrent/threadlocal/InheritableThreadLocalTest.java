package com.concurrent.threadlocal;


/**
 * 
 * */
public class InheritableThreadLocalTest {
	
	//private static ThreadLocal<String> requestIdThreadLocal = new ThreadLocal<>();
	private static InheritableThreadLocal<String> requestIdThreadLocal = new InheritableThreadLocal<>();
	
	public static void main(String[] args) {
	    new InheritableThreadLocalTest().intThread();
	    new InheritableThreadLocalTest().parentTosonThread();
	}


	private void intThread() {
		requestIdThreadLocal.set("父线程有个值要传递");
	}
	
	private void parentTosonThread() {
		System.out.println("在父线程打印信息："+requestIdThreadLocal.get());
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("在子线程打印信息："+requestIdThreadLocal.get());
			}
		});
		thread.start();
	}
}
