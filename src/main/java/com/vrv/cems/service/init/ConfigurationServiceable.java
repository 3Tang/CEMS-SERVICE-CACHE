package com.vrv.cems.service.init; 



import org.apache.log4j.Logger;

import com.vrv.cems.service.register.impl.RegisterServiceImpl;


/** 
 *   <B>说       明</B>:ConfigurationServiceable 配置服务接口
 *
 * @author  作  者  名 ：tangtieqiao<br/>
 *		    E-mail ：tangtieqiao@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年5月13日下午3:16:47 
 */
public class ConfigurationServiceable implements Runnable {
	private static final Logger LOGGER = Logger.getLogger(ConfigurationServiceable.class);
	@Override
	public void run() {
		LOGGER.info("缓存服务开始注册...");
		try {
			//服务注册
			RegisterServiceImpl.registerService();
		} catch (Throwable e) {
			LOGGER.error("缓存服务注册失败！", e);
		}
			
		LOGGER.info("缓存服务注册成功...");
	}

}
 