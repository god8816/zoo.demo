package com.zk.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 功能：zk - zk Curator客户端 - 实现分布式锁测试
 * 作者：丁志超
 */  
public class ZkCuratorLock{  
	
    //zk分布式锁创建节点在零时目录zklock下创建
    static String lockPath = "/zklock";
	static //实例化分布式锁
	CuratorFramework zkClient = null;
    
	private static CuratorFramework getZkClient() {
        //String zkServerAddress = "127.0.0.1:2181";
        String zkServerAddress = "zookeeper-test.idc.yst.com.cn:2181,zookeeper-test1.idc.yst.com.cn:2181,zookeeper-test2.idc.yst.com.cn:2181";
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3, 5000);
        CuratorFramework zkClient = CuratorFrameworkFactory.builder()
                .connectString(zkServerAddress)
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
        zkClient.start();
        return zkClient;
    }
	

    
    
	/**
	 * ZK Curator Lock - 单机版本
	 * */
	public static void zkCuratorLock() {
		if(zkClient==null) {
			zkClient = getZkClient();
		}
		
        InterProcessMutex lock = new InterProcessMutex(zkClient, lockPath);
        
		//获取锁
		try {
			lock.acquire();
			System.out.println("我获取了锁，该我干活了。");
		} catch (Exception e) {
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