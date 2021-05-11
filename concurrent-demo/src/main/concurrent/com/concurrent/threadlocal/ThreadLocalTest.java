package com.concurrent.threadlocal;


/**
 * 
 * */
public class ThreadLocalTest {
	
	Object o = new Object();
	

	public static void main(String[] args) {
	    new ThreadLocalTest().test();
	}


	private void test() {
		ThreadLocal<String> obj = new ThreadLocal<>();
	 
		 
		
	}
}
