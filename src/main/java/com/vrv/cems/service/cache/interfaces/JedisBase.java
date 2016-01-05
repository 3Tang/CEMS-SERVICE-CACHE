package com.vrv.cems.service.cache.interfaces; 

import java.util.Set;

import redis.clients.jedis.BasicCommands;
import redis.clients.jedis.JedisCommands;

/** 
 *   <B>说       明</B>:Jedis公共接口
 *
 * @author  作  者  名：chenjinlong<br/>
 *		    E-mail ：chenjinlong@vrvmail.com.cn
 *
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年8月6日 下午3:46:07 
 */
public interface JedisBase  extends JedisCommands,BasicCommands{
	
	public abstract String flushDB();
	
	public abstract Set<String> keys(String key);

	/** 
	* @Title: close 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param   参数说明 
	* @return void    返回类型 
	* @throws 
	*/
	public abstract void close();
}
 