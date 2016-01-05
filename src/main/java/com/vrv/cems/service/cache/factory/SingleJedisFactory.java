/**   
* @Title: JedisFactoryRw.java 
* @Package com.vrv.cems.service.cache.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author tangtieqiao
		   tangtieqiao@vrvmail.com.cn
* @date 2015年8月6日 下午3:00:48 
* @version V1.0   
*/
package com.vrv.cems.service.cache.factory;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import redis.clients.jedis.BinaryJedis;

import com.vrv.cems.service.cache.util.SingleJedis;


/** 
 * @ClassName: SingleJedisFactory 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author tangtieqiao
			tangtieqiao@vrvmail.com.cn
 * @date 2015年8月6日 下午3:00:48 
 *  
 */
public class SingleJedisFactory implements PooledObjectFactory {
	
	private final String host;
	private final int port;
	private final int timeout;
	private final String password;
	private final int database;
	private final String clientName;

	public SingleJedisFactory(String host, int port, int timeout, String password,
			int database) {
		this(host, port, timeout, password, database, null);
	}

	public SingleJedisFactory(String host, int port, int timeout, String password,
			int database, String clientName) {
		this.host = host;
		this.port = port;
		this.timeout = timeout;
		this.password = password;
		this.database = database;
		this.clientName = clientName;
	}

	@Override
	public PooledObject makeObject() throws Exception {
		SingleJedis jedis = new SingleJedis(host, port, timeout);
		jedis.connect();
		if (null != password)
			jedis.auth(password);
		if (database != 0)
			jedis.select(database);
		if (clientName != null)
			jedis.clientSetname(clientName);
		return new DefaultPooledObject(jedis);
	}

	@Override
	public void destroyObject(PooledObject pooledJedis) throws Exception {
		BinaryJedis jedis = (BinaryJedis) pooledJedis.getObject();
		if (jedis.isConnected())
			try {
				try {
					jedis.quit();
				} catch (Exception e) {
				}
				jedis.disconnect();
			} catch (Exception e) {
			}
		
	}

	@Override
	public boolean validateObject(PooledObject pooledJedis) {
		BinaryJedis jedis = (BinaryJedis)pooledJedis.getObject();
        return jedis.isConnected() && jedis.ping().equals("PONG");
	}

	@Override
	public void activateObject(PooledObject pooledJedis) throws Exception {
		BinaryJedis jedis = (BinaryJedis) pooledJedis.getObject();
		if (jedis.getDB().longValue() != (long) database)
			jedis.select(database);
	}

	@Override
	public void passivateObject(PooledObject pooledobject) throws Exception {
		// TODO 自动生成的方法存根
	}

}
