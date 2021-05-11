package com.concurrent.thread;

import com.alibaba.fastjson.JSON;

public class DumpStackThread {

	public static void main(String[] args) {
		 Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JSON.parseArray("1343");
			}
		});
		 t.start();
		 Thread.dumpStack();
		 StackTraceElement[] xx = t.getStackTrace();
		 System.out.println(JSON.toJSONString(xx));
	}

}
