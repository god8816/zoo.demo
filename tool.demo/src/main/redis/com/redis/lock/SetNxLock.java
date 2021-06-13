package com.redis.lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;


/**
 * 功能：redis - setNx - setNx实现分布式锁
 * 作者：丁志超
 */  
public class SetNxLock {
	

	
	static Jedis jedis = InItRedis.singleServerByRedisClients();
	
	public static void setNx(String key,String value,Integer timeOut) {
		jedis.setnx(key, value);
		try {
			if(jedis.exists(key)) {
				jedis.expire(key, timeOut);
				System.out.println("我获取了锁，干点活！");
				jedis.del("key");
			}
		} catch (Exception e) {
			
		} finally {
			jedis.del(key);
		}
	}
	
	public static void main(String[] args) {
		setNx("1","1",10);
	}
}  