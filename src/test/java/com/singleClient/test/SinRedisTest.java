/**   
* @Title: SinRedisTest.java 
* @Package com.singleClient.test 
* @Description: TODO(用一句话描述该文件做什么) 
* @author tangtieqiao
		   tangtieqiao@vrvmail.com.cn
* @date 2015年8月5日 下午4:12:39 
* @version V1.0   
*/
package com.singleClient.test;

import org.junit.Test;

import redis.clients.jedis.Connection;

/** 
 * @ClassName: SinRedisTest 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author tangtieqiao
			tangtieqiao@vrvmail.com.cn
 * @date 2015年8月5日 下午4:12:39 
 *  
 */
public class SinRedisTest {
	private Connection client;
	@Test
	public void testSet()
	{
		String key="test";
		String value="lalala";
	/*	//SinRedisUtil.getInstance().init();
		SinRedisUtil.getInstance().set(key, value);
		String result=SinRedisUtil.getInstance().get(key);
		System.out.println("result"+result);*/
		
		
	}
	

}
