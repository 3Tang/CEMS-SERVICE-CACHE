package com.vrv.cems.service.quartz;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.sys.common.util.Assert;
import com.vrv.cems.service.base.bean.TimerBean;
import com.vrv.cems.service.cache.Lifecycle;

public class ProductCacheJobConfig implements Lifecycle{
	private static final Logger LOGGER = Logger.getLogger(ProductCacheJobConfig.class);
	private ProductCacheJobConfig(){}
	
	private static final ProductCacheJobConfig INSTANCE = new ProductCacheJobConfig();
	
	public static ProductCacheJobConfig getInstance(){
		return INSTANCE;
	}
	/**
	 * 任务执行器。
	 */
	private Scheduler scheduler = null;
	
	public void init(TimerBean timerBean) {
		Assert.notNull(timerBean, "定时缓存产品信息的定时器对象不能为null!");
		LOGGER.info("定时缓存产品信息的的定时器配置开始启动...");
		try {
			SchedulerFactory sf = new StdSchedulerFactory();
			scheduler = sf.getScheduler();
			String jobName = timerBean.getName();
			String group = timerBean.getGroup();
			String trigger = timerBean.getTrigger();
			String cycle = timerBean.getCycle();
			//创建任务执行工作计划
			JobDetail productCacheJob= newJob(CInstallPackJob.class).withIdentity(jobName, group).build();
			//设置任务执行工作计划定时器执行时间
			CronTrigger productCacheJobTrigger = newTrigger().withIdentity(trigger, group).withSchedule(cronSchedule(cycle)).build();
			scheduler.scheduleJob(productCacheJob, productCacheJobTrigger);
			//启动调度器。
			scheduler.start();
			LOGGER.info("定时缓存产品信息的定时器配置启动完成...");
		} catch (SchedulerException e) {
			LOGGER.error("定时缓存产品信息的定时器配置开始启动失败!",e);
		}
	}

	
	public void destroy() {
		LOGGER.info("定时缓存产品信息的定时器配置执行销毁...");
		if(scheduler != null){
			try {
				if(!scheduler.isShutdown()){
					scheduler.shutdown();
				}
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
		LOGGER.info("定时缓存产品信息的定时器配置执行销毁完成...");
	}
}
