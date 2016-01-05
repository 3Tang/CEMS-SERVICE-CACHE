/**   
* @Title: SingleClientRedisInit.java 
* @Package com.singleClient.test 
* @Description: TODO(用一句话描述该文件做什么) 
* @author tangtieqiao
		   tangtieqiao@vrvmail.com.cn
* @date 2015年8月5日 下午3:48:42 
* @version V1.0   
*/
package com.singleClient.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.sys.common.util.CommentedProperties;
import com.vrv.cems.service.cache.util.BinaryJedisCluster;
import com.vrv.cems.service.cache.util.RedisUtil;


/** 
 * @ClassName: SingleClientRedisInit 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author tangtieqiao
			tangtieqiao@vrvmail.com.cn
 * @date 2015年8月5日 下午3:48:42 
 *  
 */
public class SinRedisUtil {
	
	
	private SinRedisUtil(){init();}
	
	private static class SingletonHolder {
		private static SinRedisUtil instance = new SinRedisUtil();
	}

	public static SinRedisUtil getInstance() {
		return SingletonHolder.instance;
	}
	
	protected Log log = LogFactory.getLog(this.getClass().getName());
	
	/** 获取配置文件中的相关属性。 */
	private Properties pros;
	
	/** 获取集群IP属性 */
	private String host;
	
	/** 获取集群IP对应端口 */
	private String port;
	
	/** 获取集群JedisCluster对象 */
	private  Jedis jc; 
	
	private static CommentedProperties prop = new CommentedProperties();

	void init() {
		InputStream in = null;
		try {
			//读取配置文件中集群IP和端口
			pros = new Properties();
			//in = this.getClass().getResourceAsStream("/redis.properties");
			File file = new File(prop.getPropertyFromCemsDat("path.redis.properties"));
			in = new FileInputStream(file);
			pros.load(in);

			host = pros.getProperty("single.redis.host");
			port = pros.getProperty("single.redis.port");
			
			System.out.println("获取的单台redis的IP为"+host);
			System.out.println("获取的单台redis的port为"+port);
			JedisPool pool = new JedisPool(new JedisPoolConfig(), host,Integer.parseInt(port));
			jc = pool.getResource();
		
		} catch (Exception ex) {
			log.error("没有找到redis配置文件", ex);
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	/*---------针对全局或者key-value类型数据操作-----------*/
	/**
	*  保存字符类型数据
	* @param key 主键   value 值
	* @return String  result
	* @throws 
	* @date 2014-08-06 15:31:41
	*/ 
	public String set(String key,String value) {
		return jc.set(key, value);
	}

	/**
	* 获取并更新数据
	* @param key 主键   value 值
	* @return String  result
	* @throws 
	* @date 2014-08-06 15:31:41
	*/ 
	public String getSet(String key,String value) {
		return jc.getSet(key, value);
	}
	/**
	*  判断key是否存在
	* @param key 主键  
	* @return boolean  result
	* @date 2014-08-07 16:46:44
	*/ 
	public boolean exists(String key) {
		return jc.exists(key);
	}
	/**
	*  判断key不存在，则保存数据
	* @param key 主键  
	 * @return 
	* @return
	* @date 2014-08-07 16:46:44
	*/ 
	public Long setnx(String key,String value) {
		 return jc.setnx(key, value);
	}
	
	/**
	*  清空所有数据
	* @param 
	* @return String   result
	* @date 2014-08-07 16:46:44
	*/ 
	public String flushDB() {
		return jc.flushDB();
	}
	/**
	*  设置key的有效期，并存储数据
	* @param key 主键    seconds 时间  value 值
	* @return String  result
	* @throws 
	* @date 2014-08-06 15:31:41
	*/ 
	public String setex(String key,int seconds,String value) {
		return jc.setex(key, seconds, value);
	}
	
	
	/**
	* 根据key获取value
	* @param key 主键   
	* @return String  result
	* @throws 
	* @date 2014-08-06 15:32:00
	*/ 
	public String get(String key) {
		return jc.get(key);
	}
}
