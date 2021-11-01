package com.redis.lock;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能：redis - Redisson - Redisson实现分布式锁
 * 作者：丁志超
 */  
public class RedissonLock {
	private static final Logger logger = LoggerFactory.getLogger(RedissonLock.class);

	
	static RedissonClient redissonClient = InItRedis.singleServerByRedisson();
	
	public static void redissonLock(String key,String value,Integer timeOut) {
		RLock rLock = redissonClient.getLock(key);
		try {
			rLock.tryLock(timeOut, TimeUnit.SECONDS);
			logger.info("Redisson我获取了锁，该我干活了。");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			if(rLock.isLocked()) {
				rLock.unlock();
			}
		}
	}
	
	
	public static void main(String[] args) {
		redissonLock("2","2",10);
	}
}  