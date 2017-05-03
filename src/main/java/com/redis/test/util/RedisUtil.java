package com.redis.test.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <p>Title: com.redis.test.util.${FILE_NAME}</P>
 *
 * @author yl
 * @date 2017/4/16 22:17
 * <p>Company: UBill</p>
 */
public class RedisUtil {
    //redis服务器IP
    private static String IP = "*************";

    //redis端口号
    private static int PORT = 6379;

    //访问密码
    private static String PW = "";

    //redis连接的最大数目，默认为8
    private static int MAX_ACTIVE = 1024;

    //redis连接池最大空闲数
    private static int MAX_IDLE = 200;

    //连接超时时间，设置为-1表示永不超时
    private static int MAX_WAIT = 8000;

    private static int TIME_OUT = 8000;

    //在获取一个redis实例前，是否进行验证
    private static boolean TEST_ON_BORROW = true;

    //Jedis连接池
    private static JedisPool jedisPool = null;

    //初始化redis连接池

    static {
        try {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(MAX_ACTIVE);
            jedisPoolConfig.setMaxIdle(MAX_IDLE);
            jedisPoolConfig.setMaxWaitMillis(MAX_WAIT);
            jedisPoolConfig.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(jedisPoolConfig, IP, PORT, TIME_OUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取jedis实例
    public static synchronized Jedis getJedis() {
        try {
            if (jedisPool != null) {
                return jedisPool.getResource();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    //释放jedis资源
    public static void relaseResource(Jedis jedis){
        if(jedis!=null)
            jedisPool.returnResource(jedis);
    }

}
