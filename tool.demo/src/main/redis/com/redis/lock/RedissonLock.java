package com.redis.lock;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

/**
 * 功能：redis - Redisson - Redisson实现分布式锁
 * 作者：丁志超
 */  
public class RedissonLock {

	public static void main(String[] args) {
		redissonLock("2","2",10);
	}
	
	
	public static void redissonLock(String key,String value,Integer timeOut) {
     	RedissonClient redissonClient =InItRedis.singleServerByRedisson();
		RLock rLock = redissonClient.getLock(key);
		try {
			rLock.tryLock(timeOut, TimeUnit.SECONDS);
			System.out.println("我获取了锁，该我干活了。");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			if(rLock.isLocked()) {
				rLock.unlock();
			}
		}
		
	}
}  