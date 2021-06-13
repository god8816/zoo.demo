package com.test.ctr;



import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.redis.lock.LuaLock;
import com.redis.lock.RedissonLock;
import com.redis.lock.SetNxLock;

/**
 * @描述 分布式锁实现性能测试
 * @author dzc
 */
@Slf4j
@RestController
public class LockController {


    /**
     * setNx 性能测试
     * @return
     */
	@GetMapping("/redislock")
    public void redislockTest(){
		
		String key = UUID.randomUUID().toString();
		Integer timeOut = 10;
		//setNx 测试 1562
		//SetNxLock.setNx(key, key, timeOut);
		
		//Redisson 测试 1552
		RedissonLock.redissonLock(key, key, timeOut);
		
		//LuaLock 测试 1206TPS
		LuaLock.lualock(key, key, timeOut);
		
    }

  
}
