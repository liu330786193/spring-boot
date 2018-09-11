package com.yongjun.stock.cache;

import redis.clients.jedis.Jedis;

/**
 * Created by lyl on 2016/9/13.
 */
public interface RedisHandler<T> {
    /**
     * Redis执行方法
     *
     * @param jedis
     * @return
     */
    T execute(Jedis jedis);
}
