package com.concurrent.semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 用法说明： 给出额定的线程数，谁拿到线程谁执行，拿不到等待。
 * Semaphore是一种基于计数的信号量，它可以设定一个阈值，基于此，多个线程竞争获取许可信号，做自己的申请后归还，
 * 超过阈值后，线程申请许可信号将被阻塞。Semaphore可以用来构建一些对象池，资源池之类的，比如数据库连接池，
 * 我们也可以创建计数为1的Semaphore,将其作为一种类似互斥锁的机制，这也叫二元信号量，表示两种互斥状态。
 * 用法如下：
 * availablePermits函数用来获取当前可用的资源数量
 * wc.acquire();//申请资源
 * wc.release();//释放资源
 * */
public class SemaphoreDemo {

	public static void main(String[] args) {
		  Semaphore semaphore = new Semaphore(3);//最多支持多少个线程
	      for (int i = 1; i <= 10; i++) {
	            new User(semaphore, i+"").start();
	      }
	}
}


class User extends Thread{
    Semaphore wc;
    String name;

    public User(Semaphore wc, String name) {
        this.wc = wc;
        this.name = name;
    }

    @Override
    public void run() {
        int availablePermits = wc.availablePermits();
        if(availablePermits>0){
            System.out.println(name+":天助我也，我有茅坑的哟");
        } else {
            System.out.println(name+":我靠，居然没有茅坑了");
        }
        try {
            wc.acquire();
            System.out.println(name+":终于能上厕所了，倍儿爽！！！！");
            Thread.sleep(new Random().nextInt(1000));
            System.out.println(name+":厕所上完了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            wc.release();
        }
    }
}

