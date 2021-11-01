package com.zk.watch;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
  
  
/**
 * 功能：zk 删除节点，可以递归删除和非递归删除 
 * 作者：丁志超
 */  
public class ZkAATest{  
	

	
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException, KeeperException, InterruptedException {
		// TODO Auto-generated method stub
		final String path = "/vem/terminal";
		String host = "zookeeper-dev.idc.yst.com.cn:2181";
		final ZooKeeper zk = new ZooKeeper(host,10000,null);

		String xx = getRealNodeTopic(zk,path,"NFZF1800002");

		System.out.println(xx);
		
	}
	
	private static String getRealNodeTopic(final ZooKeeper zk,final String topPath,String vimId) {
		try {
			String realNode = null;
			List<String> vimPathList = new ArrayList<String>();
			//1.获取ip集合
			List<String> pathList = zk.getChildren(topPath, false);
			//2.获取vimId所有的节点集合
			for (String ipPath : pathList) {
				String vimPath = new StringBuilder().append(topPath).append("/").append(ipPath).append("/").append(vimId).toString();
				vimPathList.add(vimPath);
			}
			//3.获取最新的机器节点
			if(vimPathList.size()>1) {
				long createTime = 0;
				for (String vimPath : vimPathList) {
					Stat stat = zk.exists(vimPath, true);
					if(stat!=null && createTime < stat.getCtime()) {
						createTime = stat.getCtime();
						realNode = vimPath;
					}
				}
			}else if(vimPathList.size()==1) {
				realNode = vimPathList.get(0);
			}else if(vimPathList.size()<1) {
				System.out.println("vimId节点不存在");
			}
			//4.获取节点数据
			if(Objects.nonNull(realNode)) {
				byte[] data = zk.getData(realNode, true, zk.exists(realNode, true));
				return new String(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}

  
}  