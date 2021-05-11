package com.concurrent.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 线程积累到一定数目然后执行
 * 场景作用：当线程到达一定的数目，然后并发执行，各种压测场景用到
 * */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
            new Writer(barrier).start();
        }
    }
}


class Writer extends Thread{
    CyclicBarrier cyclicBarrier;
    public Writer(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier=cyclicBarrier;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始写入数据.....");
        try {
            Thread.sleep(1000);//模拟耗时
            System.out.println(Thread.currentThread().getName()+"写入数据成功.....");
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName()+"写入数据完毕.....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}