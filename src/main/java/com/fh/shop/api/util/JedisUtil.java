package com.fh.shop.api.util;

import redis.clients.jedis.Jedis;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月07日 21:11
 */
public class JedisUtil  {
    /*String set*/
    public static  void  set(String key,String value){
        Jedis jedis =null;
        try {
            jedis = RedisPool.RedisPool();
            jedis.set(key,value);
        } finally {
            if(jedis!=null){
                jedis.close();
                jedis=null;
            }
        }
    }

    /**
     * setNxExist
     */
    public static boolean setNxExpire(String key,String value,Integer time){
        Jedis jedis = null;
        try {
            jedis = RedisPool.RedisPool();
            Long setnx = jedis.setnx(key, value);
            if (setnx==1){
                jedis.expire(key,time);
                return true;
            }else {
                return false;
            }
        } finally{
            if (jedis!=null){
                jedis.close();
                jedis=null;
            }
        }
    }

    /*incr自增*/
    public static Long incrKey(String key,int time){
        Jedis jedis =null;
        Long incr=null;
        try {
            jedis = RedisPool.RedisPool();
            incr  = jedis.incr(key);
            /*判断是否是第一次访问*/
            if (incr==1){
                jedis.expire(key,time);
            }
            return  incr;
        } finally {
            if(jedis!=null){
                jedis.close();
                jedis=null;
            }
        }
    }

    /*过期时间*/
    public static  void expire (String key,int time){
        Jedis jedis =null;
        try {
            jedis = RedisPool.RedisPool();
            jedis.expire(key,time);
        } finally {
            if(jedis!=null){
                jedis.close();
                jedis=null;
            }
        }
    }



    /*String get*/
    public static String get(String key){
        Jedis jedis = null;
        String result="";
        try {
            jedis = RedisPool.RedisPool();
            result  = jedis.get(key);
        } finally{
            if (jedis!=null)
                jedis.close();
                jedis=null;
        }
        return  result;
    }

    /**
     * main
     */
    public static void main(String[] args) {
        JedisUtil.set("xiammin","11222");
        String xiammin = JedisUtil.get("xiammin");
        System.out.println(xiammin);
    }
}


