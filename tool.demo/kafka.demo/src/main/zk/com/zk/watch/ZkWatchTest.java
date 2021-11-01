package com.zk.watch;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.alibaba.fastjson.JSON;
  
  
/**
 * 功能：zk 删除节点，可以递归删除和非递归删除 
 * 作者：丁志超
 */  
public class ZkWatchTest{  
	
	private ZooKeeper connection(final String host,Watcher watcher) throws IOException{
		return new ZooKeeper(host,10000,watcher);
	}
	
	
	/**
	 * 创建一个永远不希望被删除的节点创建
	 * 如果被删除，自动创建
	 * @throws InterruptedException 
	 * @throws KeeperException 
	 * @throws IOException 
	 * 
	 * */
	private static void createNodeWithNoDelete(final ZooKeeper zk,final String node) {
		try {
			Stat stat = zk.exists(node, true);
			if(stat==null){
			  zk.create(node, new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			  getNodeData(zk,node,new Watcher(){
					public void process(WatchedEvent event) {
						if(event.getType()==Event.EventType.NodeDeleted){
							System.out.println("[节点删除1]"+event.getPath()+":"+event.getType());
							if(event.getPath().equals(node)) {
								createNodeWithNoDelete(zk,node);
							}
						}
						
						if(event.getType()==Event.EventType.NodeDataChanged){
							System.out.println("[节点数据变更]"+event.getPath()+":"+event.getType());
							
						}
						
						System.out.println("节点事件类型1："+JSON.toJSONString(event.getType().None));
					};
				});
		}
	}
	
	/**
	 * 创建节点+Watcher事件
	 * 如果被删除，自动创建
	 * @throws InterruptedException 
	 * @throws KeeperException 
	 * @throws IOException 
	 * 
	 * */
    public static String createNode(ZooKeeper zk,String path,Watcher watcher) {
		try {
			String nodeStr = zk.create(path, new byte[0], Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
             zk.getData(path, watcher, zk.exists(path, false));
            return nodeStr;
        } catch (KeeperException e) {

        } catch (InterruptedException e) {
   
        }
        return null;
    }
	
    public static String getNodeData(ZooKeeper zk,String path,Watcher watcher) {
        byte[] data;
		try {
            data = zk.getData(path, watcher, zk.exists(path, false));
            return new String(data);
        } catch (KeeperException e) {

        } catch (InterruptedException e) {
   
        }
        return null;
    }
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException, KeeperException, InterruptedException {
		// TODO Auto-generated method stub
		final String nodeStr = "/vem/terminal";
		String host = "zookeeper-dev.idc.yst.com.cn:2181";
		final ZooKeeper zk = new ZkWatchTest().connection(host,new Watcher() {

			public void process(WatchedEvent event) {
				System.out.println(JSON.toJSONString(event.getState()));
			}});
		
		List<String> list = zk.getChildren(nodeStr, false);
		System.out.println(list);
		
		createNodeWithNoDelete(zk,"/dzc");
		
		System.out.println(123);

	}

  
}  