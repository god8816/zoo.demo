package com.concurrent.priorityblockingqueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 自定义规则有序消息队列
 * 效率：1千万数据时间花费2610毫秒，一生产者2消费者
 * */
public class PriorityBlockingQueueDemo {

	public static void main(String[] args) throws InterruptedException {
        final PriorityBlockingQueue<Task> q = new PriorityBlockingQueue<Task>();
        
        
		new Thread(new Runnable() {
            public void run() {
	            	for(;;) {
	    	    	        try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			           int max=1000000,min=1;
			           int ran2 = (int) (Math.random()*(max-min)+min); 
	   	    		       Task t1 = new PriorityBlockingQueueDemo().new Task();
			    	    	   t1.setId(ran2);
			    	    	   t1.setName("id为"+ran2);
			    	    	   q.add(t1);
	    	      }	
            }
        }).start();
		
		
		new Thread(new Runnable() {
            public void run() {
            	     for(;;) {
            	    	    try {
       					  Thread.sleep(100);
       					} catch (InterruptedException e) {
       						// TODO Auto-generated catch block
       						e.printStackTrace();
       					}
       		           int max=1000000,min=1;
       		           int ran2 = (int) (Math.random()*(max-min)+min); 
           	    		       Task t1 = new PriorityBlockingQueueDemo().new Task();
       		    	    	   t1.setId(ran2);
       		    	    	   t1.setName("id为"+ran2);
       		    	    	   q.add(t1);
            	     }	
            }
        }).start();
		


		new Thread(new Runnable() {
            public void run() {
              	try {
					for(;;) {
						Thread.sleep(5);
						System.out.println("元素ID："+q.take().getId()+",元素value："+q.take().name);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }).start();

	}
	
	
	public class Task implements Comparable<Task>{
		private int id ;
		private String name;
		
		public int getId() {
		   return id;
		}
		public void setId(int id) {
		   this.id = id;
		}
		
		public String getName() {
		   return name;
		}
		
		public void setName(String name) {
	  	   this.name = name;
		}
		
		public int compareTo(Task task) {
		   return this.id > task.id ? 1 : (this.id < task.id ? -1 : 0);
		}
		
		public String toString(){
		   return this.id + "," + this.name;
		}
		
	}

}
