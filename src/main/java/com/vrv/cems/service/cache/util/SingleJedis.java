/**   
* @Title: JedisRwRw.java 
* @Package com.vrv.cems.service.cache.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author tangtieqiao
		   tangtieqiao@vrvmail.com.cn
* @date 2015年8月6日 上午10:23:19 
* @version V1.0   
*/
package com.vrv.cems.service.cache.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.util.Pool;

import com.vrv.cems.service.cache.interfaces.JedisBase;
import com.vrv.cems.service.cache.pool.SingleJedisPool;
import com.vrv.cems.service.quartz.DeviceOnlineJob;

/** 
 * @ClassName: JedisRw
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author tangtieqiao
			tangtieqiao@vrvmail.com.cn
 * @date 2015年8月6日 上午10:23:19 
 *  
 */
public class SingleJedis extends Jedis implements JedisBase{
	private static final Logger log = Logger.getLogger(SingleJedis.class);
	private   JedisPool jedisPool;

	public SingleJedis(String host, int port) {
		super(host, port);
		// TODO 自动生成的构造函数存根
	}

	
	public SingleJedis(String host, int port,int timeout) {
		super(host, port,timeout);
		// TODO 自动生成的构造函数存根
	}


	/** 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param config
	* @param hosts
	* @param parseInt 
	*/
	public SingleJedis(JedisPoolConfig config, String hosts, int parseInt) {
		super(hosts, parseInt, parseInt);
		jedisPool= new JedisPool(config, hosts, parseInt, 60000);
	
	}

	public Boolean exists(String key) {
		  boolean value = false;
	        Jedis jedis = null;
	        try {
	            jedis = jedisPool.getResource();
	           return jedis.exists(key);
	        } catch (Exception e) {
	            //释放redis对象
	            jedisPool.returnBrokenResource(jedis);
	            e.printStackTrace();
	        } finally {
	            //返还到连接池
	            close(jedis);
	        }
	 
	        return value;
	}
	
	/**
	*  设置key的有效期，并存储数据
	* @param key 主键    seconds 时间  value 值
	* @return String  result
	* @throws 
	* @date 2014-08-06 15:31:41
	*/ 
	public String setex(String key,int seconds,String value) {
	    Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.setex(key, seconds, value);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
		return null;
	}
	
    /**
     * 获取数据
     * @param key
     * @return
     */
    public String get(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
 
        return value;
    }
 
    
    /**
	*  Hash类型：获取key下所有属性
	* @param key主键   
	* @return Set<String>  result
	* @date 2014-08-11 13:19:52
	*/ 
	public Set<String> keys(String key) {
		 Set<String> value = null;
	        Jedis jedis = null;
	        try {
	            jedis = jedisPool.getResource();
	            return jedis.keys(key);
	        } catch (Exception e) {
	            //释放redis对象
	            jedisPool.returnBrokenResource(jedis);
	            e.printStackTrace();
	        } finally {
	            //返还到连接池
	            close(jedis);
	        }
	 
	        return value;
	}
    
	/**
	*  数据
	* @param key 主键  
	* @return Long  result
	* @throws 
	* @date 2014-08-06 16:05:22
	*/ 
	public Long delete(String key) {
		Long value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(key);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
 
        return value;
	}
    
    public   void close(Jedis jedis) {
        try {
            jedisPool.returnResource(jedis);
 
        } catch (Exception e) {
            if (jedis.isConnected()) {
                jedis.quit();
                jedis.disconnect();
            }
        }
    }
 
    /**
     * 获取数据
     * 
     * @param key
     * @return
     */
    public   byte[] get(byte[] key) {
 
        byte[] value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
 
        return value;
    }
 
    public   String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, value);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
		return null;
    }
 
    public   void set(byte[] key, byte[] value, int time) {
 
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            jedis.expire(key, time);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
    }
 
	

 
    public   Long hset(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
           return jedis.hset(key, field, value);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
		return null;
    }
 
    /**
     * 获取数据
     * 
     * @param key
     * @return
     */
    public  String hget(String key, String field) {
 
        String value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.hget(key, field);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
 
        return value;
    }
 
    /**
     * 获取数据
     * 
     * @param key
     * @return
     */
    public   byte[] hget(byte[] key, byte[] field) {
 
        byte[] value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.hget(key, field);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
 
        return value;
    }
 
    public   void hdel(byte[] key, byte[] field) {
 
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hdel(key, field);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
    }
 
    /**
     * 存储REDIS队列 顺序存储
     * @param byte[] key reids键名
     * @param byte[] value 键值
     */
    public   void lpush(byte[] key, byte[] value) {
 
        Jedis jedis = null;
        try {
 
            jedis = jedisPool.getResource();
            jedis.lpush(key, value);
 
        } catch (Exception e) {
 
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
 
        } finally {
 
            //返还到连接池
            close(jedis);
 
        }
    }
 
    /**
     * 存储REDIS队列 反向存储
     * @param byte[] key reids键名
     * @param byte[] value 键值
     */
    public   void rpush(byte[] key, byte[] value) {
 
        Jedis jedis = null;
        try {
 
            jedis = jedisPool.getResource();
            jedis.rpush(key, value);
 
        } catch (Exception e) {
 
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
 
        } finally {
 
            //返还到连接池
            close(jedis);
 
        }
    }
 
    /**
     * 将列表 source 中的最后一个元素(尾元素)弹出，并返回给客户端
     * @param byte[] key reids键名
     * @param byte[] value 键值
     * @return 
     */
    public   byte[] rpoplpush(byte[] key, byte[] destination) {
 
        Jedis jedis = null;
        try {
 
            jedis = jedisPool.getResource();
            return jedis.rpoplpush(key, destination);
 
        } catch (Exception e) {
 
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
 
        } finally {
 
            //返还到连接池
            close(jedis);
 
        }
		return destination;
    }
 
    /**
     * 获取队列数据
     * @param byte[] key 键名
     * @return
     */
    public   List<byte[]> lpopList(byte[] key) {
 
        List<byte[]> list = null;
        Jedis jedis = null;
        try {
 
            jedis = jedisPool.getResource();
            list = jedis.lrange(key, 0, -1);
 
        } catch (Exception e) {
 
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
 
        } finally {
 
            //返还到连接池
            close(jedis);
 
        }
        return list;
    }
 
    /**
     * 获取队列数据
     * @param byte[] key 键名
     * @return
     */
    public   byte[] rpop(byte[] key) {
 
        byte[] bytes = null;
        Jedis jedis = null;
        try {
 
            jedis = jedisPool.getResource();
            bytes = jedis.rpop(key);
 
        } catch (Exception e) {
 
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
 
        } finally {
 
            //返还到连接池
            close(jedis);
 
        }
        return bytes;
    }
 

 

 

 
  
 
    public   List<byte[]> lrange(byte[] key, int from, int to) {
        List<byte[]> result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.lrange(key, from, to);
 
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
 
        } finally {
            //返还到连接池
            close(jedis);
 
        }
        return result;
    }
 

    public  List<String> hmget(Object key, String... fields) {
        List<String> result = null;
        Jedis jedis = null;
        try {
 
            jedis = jedisPool.getResource();
            result = jedis.hmget(key.toString(), fields);
 
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
 
        } finally {
            //返还到连接池
            close(jedis);
 
        }
        return result;
    }
 
    public  void hmset(Object key, Map<String, String> hash, int time) {
        Jedis jedis = null;
        try {
 
            jedis = jedisPool.getResource();
            jedis.hmset(key.toString(), hash);
            jedis.expire(key.toString(), time);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
 
        } finally {
            //返还到连接池
            close(jedis);
 
        }
    }
 
    
    public Map<String, String>  hgetAll(String key) {
        Map<String, String> result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hgetAll(key);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
 
        } finally {
            //返还到连接池
            close(jedis);
        }
        return result;
    }
 
    public   Long del(byte[] key) {
 
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
           return  jedis.del(key);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
		return null;
    }
 
    public  Long llen(byte[] key) {
        long len = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.llen(key);
        } catch (Exception e) {
            //释放redis对象
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
        return len;
    }
 


	

}
