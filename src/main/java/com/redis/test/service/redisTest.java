package com.redis.test.service;

import com.redis.test.util.RedisUtil;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: com.redis.test.service.redisTest</P>
 *
 * @author yl
 * @date 2017/4/16 23:20
 * <p>Company: UBill</p>
 */

public class redisTest {

    private  Jedis jedis;


    @Before
    public  void Before(){
        jedis = RedisUtil.getJedis();
    }

    @Test
    public void testString() {
        System.out.println(jedis.get("msg"));
        jedis.append("msg","good good study,day day up");
        System.out.println(jedis.get("msg"));
        jedis.del("msg");
        System.out.println(jedis.get("msg"));
        jedis.mset("msg","hello world","age","18","name","tom");
        jedis.incr("age");
        System.out.println(jedis.get("msg")+"-----"+jedis.get("age"));

    }

    @Test
    public void testMap(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("location","shenzhen");
        map.put("date","20170416");
        map.put("doctor","yang");
        jedis.hmset("lmap",map);
        List<String> list = jedis.hmget("lmap","location","date","doctor");
        System.out.println(list.getClass().toString()+list);
        jedis.hdel("lmap","doctor");
        System.out.println(jedis.hmget("lmap","location","date","doctor"));
        System.out.println(jedis.hlen("lmap"));
        System.out.println(jedis.exists("lmap"));
        System.out.println(jedis.hkeys("lmap"));
        System.out.println(jedis.hvals("lmap"));

        Iterator<String> it = jedis.hkeys("lmap").iterator();
        while (it.hasNext()){
            String key = it.next();
            System.out.println(jedis.hmget("lmap",key));
        }


    }

    @Test
    public void testList(){
        Jedis jedis = RedisUtil.getJedis();
        jedis .lpush("list","1","w","3","4");
        System.out.println(jedis.lrange("list",0,1));
        System.out.println(jedis.llen("list"));
        System.out.println(jedis.lrange("list",0,-1));
        jedis.rpush("list","a","b","c","d");
        System.out.println(jedis.lrange("list",0,-1));

    }

}
