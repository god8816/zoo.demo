package com.redis.lock;

import redis.clients.jedis.Jedis;

/**
 * 功能：redis - setNx - setNx实现分布式锁
 * 作者：丁志超
 */  
public class SetNxLock {

		public static void main(String[] args) {
			Jedis jedis = new Jedis("localhost");
			jedis.setnx("key", "value");
			try {
				if(jedis.exists("key")) {
					jedis.expire("key", 10);
					System.out.println("我获取了锁，干点活！");
					jedis.del("key");
				}
			} catch (Exception e) {
				
			} finally {
				jedis.del("key");
			}
		}
}  