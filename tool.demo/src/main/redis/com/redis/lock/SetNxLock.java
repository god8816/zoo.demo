package com.redis.lock;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.KeeperException;

/**
 * 功能：redis - setNx - setNx实现分布式锁
 * 作者：丁志超
 */  
public class SetNxLock {

		//实例化客户端
		private static RetryPolicy retryPolicy  = new ExponentialBackoffRetry(1000,3);
	    private static CuratorFramework client = CuratorFrameworkFactory.builder()
	            .connectString("zookeeper-dev.idc.yst.com.cn:2181")
	            .sessionTimeoutMs(3000)
	            .connectionTimeoutMs(5000)
	            .retryPolicy(retryPolicy)
	            .build();
	    
	    //zk分布式锁创建节点在零时目录zklock下创建
	    static String lockPath = "/zklock";

		
		public static void main(String[] args) throws NoSuchAlgorithmException, IOException, KeeperException, InterruptedException {
		    //实例化分布式锁
		    InterProcessLock lock = new InterProcessSemaphoreMutex(client, lockPath);
			
			//获取锁
			try {
				lock.acquire();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				//释放锁
				try {
					lock.release();
				} catch (Exception e) {
				}
			}
		}
		
	}  