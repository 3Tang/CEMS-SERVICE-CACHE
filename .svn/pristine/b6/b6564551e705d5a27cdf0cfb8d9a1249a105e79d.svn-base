package com.vrv.cems.service.cache.util;


import org.apache.log4j.Logger;

import com.vrv.cems.service.quartz.QuartzConfig;

/**
 * <B>说 明</B>:初始化系统
 * 
 * @author 作 者 名：<br/>
 *         E-mail ：daicheng@vrvmail.com.cn
 * 
 * @version 版 本 号：V1.0.<br/>
 *          创建时间：2015-02-09 20:19:40
 */
public class InitSystem {
	private static final Logger logger = Logger.getLogger(InitSystem.class);
	/**
	 * 策略定时器配置服务类。
	 */
	private QuartzConfig quartzConfig;
	
/*	private void startpolicyQuart() {
		new Thread() {
			public void run() {
				quartzConfig = new QuartzConfig();
				quartzConfig.init();//启动定时器配置服务	
			}
		}.start();
		logger.info("启动策略配置定时器任务成功!");
	}*/
	
/*	*//**
	 * 策略里的定时器服务
	 *//*
	public void policyQuartzService() {
		startpolicyQuart();
	}*/
	
	/**
	 * 停止策略配置定时任务
	 */
	public void stopPolicyQuart()
	{
		if(quartzConfig!=null){
			quartzConfig.destroy();
			logger.info("停止策略配置定时任务成功!");
		}
	}
	
}
