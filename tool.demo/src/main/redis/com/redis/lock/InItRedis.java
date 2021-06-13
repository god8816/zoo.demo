package com.redis.lock;

import java.util.HashSet;
import java.util.Set;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis初始化
 * */
public class InItRedis {
	
	
	/**
	 * redis.clients - 单机版本
	 * */
	public static Jedis singleServerByRedisClients() {
		Jedis jedis = new Jedis("localhost");
		return jedis;
	}
	
	/**
	 * redis.clients - cluster集群版本
	 * */
	public static JedisCluster jedisClusterByRedisClients() {
		// 添加集群的服务节点Set集合
        Set<HostAndPort> hostAndPortsSet = new HashSet<HostAndPort>();
        // 添加节点
        hostAndPortsSet.add(new HostAndPort("10.213.3.107", 6379));
        hostAndPortsSet.add(new HostAndPort("10.213.6.159", 6379));
        hostAndPortsSet.add(new HostAndPort("10.213.3.105", 6379));
    
        // Jedis连接池配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲连接数, 默认8个
        jedisPoolConfig.setMaxIdle(100);
        // 最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(500);
        //最小空闲连接数, 默认0
        jedisPoolConfig.setMinIdle(0);
        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        jedisPoolConfig.setMaxWaitMillis(2000); // 设置2秒
        //对拿到的connection进行validateObject校验
        jedisPoolConfig.setTestOnBorrow(true);
        JedisCluster jedis  = new JedisCluster(hostAndPortsSet, jedisPoolConfig);
        return jedis;
	}
	
	
	
	/**
	 * redisson - 单机版
	 * */
	public static RedissonClient singleServerByRedisson() {
		Config config = new Config(); 
     	config.useSingleServer().setAddress("redis://localhost:6379");
     	RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
	}
	
	
	
	/**
	 * redisson - 集群版
	 * */
	public static RedissonClient clusterServerByRedisson() {
		Config config = new Config(); 
     	config.useClusterServers().addNodeAddress("rediss://10.213.3.107:6379","rediss://10.213.6.159:6379","rediss://10.213.3.105:6379");
     	RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
	}

}
