package com.zk.watch;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
  
  
/**
 * 功能：zk 删除节点，可以递归删除和非递归删除 
 * 作者：丁志超
 */  
public class ZkCreateNodeTest{  
	

	
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException, KeeperException, InterruptedException {
		// TODO Auto-generated method stub
		final String path = "/huawei/kafka";
		String host = "zookeeper-test.idc.yst.com.cn:2181";
		final ZooKeeper zk = new ZooKeeper(host,10000,null);

		String xx = zk.create(path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

		System.out.println(xx);
		
	}
}  