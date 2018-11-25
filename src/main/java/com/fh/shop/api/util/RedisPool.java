package com.fh.shop.api.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月07日 20:56
 */
public class RedisPool{
        /*jedis连接redis*/
    /*私有静态的全局变量*/
    private static JedisPool pool;

   private static  void   initpool(){
       /*jedis连接池*/
       JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
       jedisPoolConfig.setMaxIdle(1000);
       jedisPoolConfig.setMinIdle(100);
       jedisPoolConfig.setMaxTotal(100);
       jedisPoolConfig.setTestOnCreate(true);
       jedisPoolConfig.setTestOnBorrow(true);
       pool=new JedisPool(jedisPoolConfig,"192.168.211.128",6379);
   }




   /*静态代码块*/
    static {
        initpool();
    }


    /*私有的构造参数*/
    public static Jedis RedisPool(){
       return  pool.getResource();
    }
}
