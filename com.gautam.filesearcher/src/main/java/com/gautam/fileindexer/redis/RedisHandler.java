/**
 * Copyright (c) 2011 by InfoArmy Inc.  All Rights Reserved.
 * This file contains proprietary information of InfoArmy Inc.
 * Copying, use, reverse engineering, modification or reproduction of
 * this file without prior written approval is prohibited.
 *
 */
package com.gautam.fileindexer.redis;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

/**
 * @author gautamb
 *
 */

public class RedisHandler {

	private static final String MINUS_INF = "-inf";
	private static final String PLUS_INF = "+inf";

	private Jedis redisServer = new Jedis();

	public void put(String key, String value) {
		redisServer.set(key, value);
	}

	public void flushAll() {
		redisServer.flushAll();
	}

	public String get(String key) {
		return redisServer.get(key);
	}

	public void pushToSet(String key, String value) {
		redisServer.zincrby(key, 1, value);
	}

	// TODO : need not expose the tuple, if we create a VO and expose it, it
	// would be a better design because internal implementaion of temple element
	// is bytes is persisted
	public Set<Tuple> getTopValues(String key) {
		return redisServer.zrevrangeByScoreWithScores(key, PLUS_INF, MINUS_INF);
	}

	public List<String> getAll(String key) {
		return redisServer.lrange(key, 0, -1);
	}

}
