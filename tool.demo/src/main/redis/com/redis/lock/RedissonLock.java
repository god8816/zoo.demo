package com.redis.lock;

import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * 功能：redis - Redisson - Redisson实现分布式锁
 * 作者：丁志超
 */  
public class RedissonLock {

	public static void main(String[] args) {
		Config config = new Config(); 
     	config.useSingleServer().setAddress("localhost");
     	RedissonClient redissonClient = Redisson.create(config);
		RLock rLock = redissonClient.getLock("key");
		try {
			rLock.tryLock(10, TimeUnit.SECONDS);
			System.out.println("我获取了锁，该我干活了。");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rLock.isLocked()) {
				rLock.unlock();
			}
		}
		
	}
}  