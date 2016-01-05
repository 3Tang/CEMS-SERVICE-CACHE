/**   
* @Title: JedisRwPool.java 
* @Package com.vrv.cems.service.cache.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author tangtieqiao
		   tangtieqiao@vrvmail.com.cn
* @date 2015年8月6日 下午2:50:13 
* @version V1.0   
*/
package com.vrv.cems.service.cache.pool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

import com.vrv.cems.service.cache.factory.SingleJedisFactory;
import com.vrv.cems.service.cache.util.SingleJedis;

/** 
 * @ClassName: SingleJedisPool 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author tangtieqiao
			tangtieqiao@vrvmail.com.cn
 * @date 2015年8月6日 下午2:50:13 
 *  
 */
public class SingleJedisPool extends Pool<Jedis>{
	
	public SingleJedisPool(String host, int port) {
		this(new GenericObjectPoolConfig(), host, port, 2000, null, 0, null);
	}
	
	public SingleJedisPool(GenericObjectPoolConfig poolConfig, String host, int port) {
		this(poolConfig, host, port, 2000, null, 0, null);
	}

	public SingleJedisPool(GenericObjectPoolConfig poolConfig, String host, int port,
			int timeout, String password, int database, String clientName) {
		super(poolConfig, new SingleJedisFactory(host, port, timeout, password,
				database, clientName));
	}
	
	public SingleJedis getResource() {
		SingleJedis singleJedis = (SingleJedis) super.getResource();
		singleJedis.setDataSource(this);
		return singleJedis;
	}
}
