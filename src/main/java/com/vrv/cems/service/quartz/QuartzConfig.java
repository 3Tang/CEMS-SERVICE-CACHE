package com.vrv.cems.service.quartz;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.JobBuilder.newJob;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.sys.common.util.Assert;
import com.sys.common.util.DateUtils;
import com.vrv.cems.service.base.SystemConstants;
import com.vrv.cems.service.base.bean.TimerBean;
import com.vrv.cems.service.cache.Lifecycle;
import com.vrv.cems.service.cache.util.XmlUtil;





/** 
 *   <B>说       明</B>:QuartzConfig定时器总配置类
 *
 * @author  作  者  名 ：tangtieqiao<br/>
 *		    E-mail ：tangtieqiao@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年5月12日上午8:56:13 
 */
public class QuartzConfig {
	private static final Logger LOGGER = Logger.getLogger(QuartzConfig.class);


	
	private static final QuartzConfig INSTANCE = new QuartzConfig();
	
	public static QuartzConfig getInstance(){
		return INSTANCE;
	}
	
	/**
	 * 任务执行器。
	 */
	private Scheduler scheduler;
	private QuartzConfig(){
		SchedulerFactory sf = new StdSchedulerFactory();
		try {
			scheduler = sf.getScheduler();
		} catch (SchedulerException e) {
			LOGGER.error("Quartz调度器创建失败!",e);
		}
	}
	
    /**
     * 获取CronTrigger信息。
     * @param triggerKey 触发器Key。
     * @return CronTrigger对象。
     */
    public CronTrigger getTrigger(TriggerKey triggerKey){
    	CronTrigger oldTrigger = null;
		try {
			oldTrigger = (CronTrigger)scheduler.getTrigger(triggerKey);
		} catch (SchedulerException e) {
			LOGGER.error("Quartz调度器获取CronTrigger失败!",e);
			return oldTrigger;
		}
		return oldTrigger;
    }
    /**
     * 重新恢复触发器相关的job任务 。
     * @param triggerkey 目标任务Key。
     * @param trigger 计划定时器执行时间。
     * @return 重新恢复时间。
     */
    public  Date rescheduleJob(TriggerKey triggerkey, Trigger trigger){  
        try {
			return scheduler.rescheduleJob(triggerkey, trigger);
		} catch (SchedulerException e) {
			LOGGER.error("Quartz调度器重新恢复["+triggerkey.getName()+"]任务失败!",e);
			throw new RuntimeException(e);
		}  
    } 
	
	
	/**
	 * 清理设备在线信息策略JOB
	 */
	private static final String DEVICE_ONLINE_JOB_NAME = "deviceOnlineJob";
	/**
	 * 定时缓存策略信息的定时器JOB
	 */
	private static final String POLICY_CACHE_JOB_NAME = "policyCacheJob";
	/**
	 * 定时缓存设备信息的定时器JOB
	 */
	private static final String DEVICE_CACHE_JOB_NAME = "deviceCacheJob";
	/**
	 * 定时缓存用户信息JOB
	 */
	private static final String USER_CACHE_JOB_NAME = "userCacheJob";
	/**
	 * 定时缓存产品信息JOB
	 */
	private static final String PRODUCT_CACHE_JOB_NAME = "productCacheJob";
	 /**
	 * 拉取自身服务JOB
	 */
	private static final String POLICY_XML_JOB_NAME = "PolicyXmlJob";


	public void init() {
		try {
			FileInputStream policyFileIn=new FileInputStream(SystemConstants.PATH_POLICY_XML);
			ServiceParamBean serviceBean=XmlUtil.xmlToObject(policyFileIn, ServiceParamBean.class);
			List<TimerBean> timerBeans =serviceBean.getTimers();
			if(timerBeans.isEmpty()){
				throw new IllegalArgumentException("定时器配置参数不能为空！");
			}

		for(TimerBean timerBean : timerBeans){
				String jobName = timerBean.getName();
				String group = timerBean.getGroup();
				String trigger = timerBean.getTrigger();
				String cycle = timerBean.getCycle();
				if(POLICY_CACHE_JOB_NAME.equals(jobName)){
						//创建任务执行工作计划
						JobDetail policyCacheJob = newJob(PolicyCacheJob.class).withIdentity(jobName, group).build();
						//设置任务执行工作计划定时器执行时间
						CronTrigger policyCacheJobTrigger = newTrigger().withIdentity(trigger, group).withSchedule(cronSchedule(cycle)).build();
						Date date = scheduler.scheduleJob(policyCacheJob, policyCacheJobTrigger);
			 			LOGGER.info("jobName=["+jobName+"];group=["+group+"];dateTime=["+DateUtils.format(date)+"]定时器添加成功！");
					
				
				}else if(DEVICE_CACHE_JOB_NAME.equals(jobName))
					{
						//创建任务执行工作计划
						JobDetail deviceCacheJob = newJob(DeviceCacheJob.class).withIdentity(jobName, group).build();
						//设置任务执行工作计划定时器执行时间
						CronTrigger deviceCacheJobTrigger = newTrigger().withIdentity(trigger, group).withSchedule(cronSchedule(cycle)).build();
						Date date = scheduler.scheduleJob(deviceCacheJob, deviceCacheJobTrigger);
			 			LOGGER.info("jobName=["+jobName+"];group=["+group+"];dateTime=["+DateUtils.format(date)+"]定时器添加成功！");
				
					
					}
				else if(USER_CACHE_JOB_NAME.equals(jobName))
				{
					JobDetail userCacheJob = newJob(UserCacheJob.class).withIdentity(jobName, group).build();
					//设置任务执行工作计划定时器执行时间
					CronTrigger userCacheJobTrigger = newTrigger().withIdentity(trigger, group).withSchedule(cronSchedule(cycle)).build();
					Date date = scheduler.scheduleJob(userCacheJob, userCacheJobTrigger);
		 			LOGGER.info("jobName=["+jobName+"];group=["+group+"];dateTime=["+DateUtils.format(date)+"]定时器添加成功！");
				
				}
				else if(PRODUCT_CACHE_JOB_NAME.equals(jobName))
				{
					//创建任务执行工作计划
					JobDetail productCacheJob= newJob(CInstallPackJob.class).withIdentity(jobName, group).build();
					//设置任务执行工作计划定时器执行时间
					CronTrigger productCacheJobTrigger = newTrigger().withIdentity(trigger, group).withSchedule(cronSchedule(cycle)).build();
					Date date = scheduler.scheduleJob(productCacheJob, productCacheJobTrigger);
		 			LOGGER.info("jobName=["+jobName+"];group=["+group+"];dateTime=["+DateUtils.format(date)+"]定时器添加成功！");
				
				}
				else if(POLICY_XML_JOB_NAME.equals(jobName))
				{
					//创建任务执行工作计划
					JobDetail policyXMLJob = newJob(PolicyXmlJob.class).withIdentity(jobName, group).build();
					//设置任务执行工作计划定时器执行时间
					CronTrigger policyXMLJobTrigger = newTrigger().withIdentity(trigger, group).withSchedule(cronSchedule(cycle)).build();
					Date date = scheduler.scheduleJob(policyXMLJob, policyXMLJobTrigger);
		 			LOGGER.info("jobName=["+jobName+"];group=["+group+"];dateTime=["+DateUtils.format(date)+"]定时器添加成功！");
				
				}
				else if(DEVICE_ONLINE_JOB_NAME.equals(jobName))
				{
					//创建任务执行工作计划
					JobDetail deviceOnlineJob = newJob(DeviceOnlineJob.class).withIdentity(jobName, group).build();
					//设置任务执行工作计划定时器执行时间
					CronTrigger deviceOnlineJobTrigger = newTrigger().withIdentity(trigger, group).withSchedule(cronSchedule(cycle)).build();
					Date date = scheduler.scheduleJob(deviceOnlineJob, deviceOnlineJobTrigger);
		 			LOGGER.info("jobName=["+jobName+"];group=["+group+"];dateTime=["+DateUtils.format(date)+"]定时器添加成功！");
				
				}
				
			}
		
			scheduler.start();
			LOGGER.info("定时器配置启动完成...");
		}	
		catch (SchedulerException e) {
			LOGGER.error("Quartz调度器启动失败!",e);
		} catch (FileNotFoundException e) {
			LOGGER.error("定时器配置开始启动失败!",e);
		}
			}
	
	public void destroy() {
		LOGGER.info("定时器配置执行销毁...");
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			LOGGER.error("Quartz调度器关闭失败!",e);
		} 
		LOGGER.info("定时器配置执行销毁完成...");
	}

	}
	
		

