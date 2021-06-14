package com.zk.lock;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;

public class MultiLockDemo {
    private static CuratorFramework       client  = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(3000, 2));
    private static String                 path1   = "/mutex/001";
    private static String                 path2   = "/mutex/002";
    private static List<InterProcessLock> mutexes = new ArrayList<>();
    private static InterProcessMutex      mutex1;
    private static InterProcessMutex      mutex2;

    static {
        mutex1 = new InterProcessMutex(client, path1);
        mutex2 = new InterProcessMutex(client, path2);
        mutexes.add(mutex1);
        mutexes.add(mutex2);
        client.start();
    }

    public static void main(String[] args) throws Exception {
        InterProcessMultiLock multiLock = new InterProcessMultiLock(mutexes);
        multiLock.acquire();
        System.out.println("acquired multi lock");
        startThread0();
        startThread1();
        Thread.sleep(5000);
        multiLock.release();
        System.out.println("release multi lock");
        Thread.sleep(50000);
        client.close();
    }

    public static void startThread0() throws InterruptedException {
        Thread.sleep(1000);
        new Thread(() -> {
            try {
                System.out.println("thread0 acquring");
                mutex1.acquire();
                System.out.println("thread0 acquired");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    mutex1.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void startThread1() throws InterruptedException {
        Thread.sleep(1000);
        new Thread(() -> {
            try {
                System.out.println("thread1 acquiring");
                mutex2.acquire();
                System.out.println("thread1 acquired");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    mutex2.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}