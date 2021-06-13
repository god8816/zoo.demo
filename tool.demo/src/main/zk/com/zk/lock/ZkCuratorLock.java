package com.zk.lock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 功能：zk - zk Curator客户端 - 实现分布式锁测试
 * 作者：丁志超
 */  
public class ZkCuratorLock{  
	
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
    
 
    
    
	/**
	 * ZK Curator Lock - 单机版本
	 * */
	public static void zkCuratorLock() {
		//实例化分布式锁
	    InterProcessLock lock = new InterProcessSemaphoreMutex(client, lockPath);
		//获取锁
		try {
			lock.acquire();
			System.out.println("我获取了锁，该我干活了。");
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
	

	
	public static void main(String[] args){
		zkCuratorLock();
	}
}  