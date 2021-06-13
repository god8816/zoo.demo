package com.redis.lock;

import redis.clients.jedis.Jedis;

/**
 * 功能：redis - Lua - Lua实现分布式锁
 * 作者：丁志超
 */  
public class LuaLock {
	
	static Jedis jedis = InItRedis.singleServerByRedisClients();
	
	public static void lualock(String key,String value,Integer timeOut) {
		 lock(key, value,timeOut);
		 System.out.println("我获取了锁，该我干活了。");
		 unlock(key, value);
    }
	
	
		
	/**
	 * 加锁语法
	 * key:redis key
	 * value:redis value
	 * time: redis timeouts 锁过期时间一般大于最耗时的业务消耗的时间 
	 * 语法参考文档：https://www.runoob.com/redis/redis-scripting.html
	 * */	
	public static String lock(String key, String value,Integer timeOut ) {
			/**
	         *  -- 加锁脚本，其中KEYS[]为外部传入参数
	         *  -- KEYS[1]表示key 
	         *  -- ARGV[1]表示value
	         *  -- ARGV[2]表示过期时间
	         */
		    String lua_getlock_script = "if redis.call('SETNX','"+key+"','"+value+"') == 1 then" +
                "     return redis.call('pexpire','"+key+"','"+timeOut+"')" +
                " else" +
                "     return 0 " +
                "end";
			
			//在缓存中添加脚本但不执行
			String scriptId = jedis.scriptLoad(lua_getlock_script);
			//查询脚本是否添加
			//Boolean isExists = jedis.scriptExists(scriptId);
			//执行脚本 返回1表示成功，返回0表示失败
			Object num = jedis.eval(lua_getlock_script);;
			return String.valueOf(num);
	}
	
	
	
	/**
	 * 释放锁语法
	 * key:redis key
	 * value:redis value
	 * time: redis timeouts 锁过期时间一般大于最耗时的业务消耗的时间 
	 * 语法参考文档：https://www.runoob.com/redis/redis-scripting.html
	 * */	
	public static String unlock(String key, String value ) {
			/**
	         *  -- 加锁脚本，其中KEYS[]为外部传入参数
	         *  -- KEYS[1]表示key 
	         *  -- ARGV[1]表示value
	         *  -- ARGV[2]表示过期时间
	         */
		    String lua_unlock_script  =
		              "if redis.call('get','"+key+"') == '"+value+"' then " +
		                      " return redis.call('del','"+key+"') " +
		                      "else  return 0 " +
		                      "end";
			//在缓存中添加脚本但不执行
			String scriptId = jedis.scriptLoad(lua_unlock_script);
			//查询脚本是否添加
			//Boolean isExists = jedis.scriptExists(scriptId);
			//执行脚本 返回1表示成功，返回0表示失败
			Object num = jedis.eval(lua_unlock_script);;
			return String.valueOf(num);
	}
	
	
	public static void main(String[] args) {
		lualock("122333", "33331",10 );
	}
}  