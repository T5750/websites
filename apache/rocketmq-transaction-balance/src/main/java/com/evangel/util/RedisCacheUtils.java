package com.evangel.util;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public final class RedisCacheUtils {
	private static ShardedJedis shard;
	private static ShardedJedisPool pool;
	private static Jedis jedis;
	static {
		jedis = new Jedis(Globals.IP_ADDRESS, 6379);
		// 启动服务器时刷新缓存
		// jedis.flushAll();
		// 分片
		List<JedisShardInfo> shards = Arrays.asList(new JedisShardInfo(
				Globals.IP_ADDRESS, 6379));
		shard = new ShardedJedis(shards);
		GenericObjectPoolConfig goConfig = new GenericObjectPoolConfig();
		// 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取
		goConfig.setMaxTotal(100);
		// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例
		goConfig.setMaxIdle(20);
		// 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException
		goConfig.setMaxWaitMillis(-1);
		// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
		goConfig.setTestOnBorrow(true);
		pool = new ShardedJedisPool(goConfig, shards);
	}

	private RedisCacheUtils() {
	}

	public static Jedis getJedis() {
		return jedis;
	}

	public static void colseJedis() {
		jedis.disconnect();
	}

	public static ShardedJedis getShardedJedis() {
		return pool.getResource();
	}

	public static void colseShardedJedis(ShardedJedis sj) {
		pool.returnResourceObject(sj);
	}

	public static String get(String key) {
		ShardedJedis sj = RedisCacheUtils.getShardedJedis();
		String value = sj.get(key);
		RedisCacheUtils.colseShardedJedis(sj);
		return value;
	}

	public static void set(String key, String value) {
		ShardedJedis sj = RedisCacheUtils.getShardedJedis();
		sj.set(key, value);
		RedisCacheUtils.colseShardedJedis(sj);
	}

	public static void del(String key) {
		ShardedJedis sj = RedisCacheUtils.getShardedJedis();
		Long count = sj.del(key);
		RedisCacheUtils.colseShardedJedis(sj);
		System.out.println(count);
	}

	public static void main(String[] args) {
		// SharedJedis
		for (int i = 0; i < 20; i++) {
			ShardedJedis sj = RedisCacheUtils.getShardedJedis();
			sj.set("name", "com.evangel");
			System.out.println(sj.get("name"));
			RedisCacheUtils.colseShardedJedis(sj);
		}
		// Jedis
		// Jedis j = RedisCacheUtils.getJedis();
		// System.out.println(j.keys("nam*"));
		// RedisCacheUtils.colseJedis();
	}
}
