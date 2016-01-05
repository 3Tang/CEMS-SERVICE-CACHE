package com.vrv.cems.service.quartz;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.vrv.cems.service.cache.service.JobService;

public class UserCacheJob implements Job{
	private static final Logger LOGGER = Logger.getLogger(UserCacheJob.class);

	@Override
	public void execute(JobExecutionContext paramJobExecutionContext)
			throws JobExecutionException {
		// TODO 自动生成的方法存根
		try {
			LOGGER.info("定时缓存用户信息的定时器任务开始执行...");
			JobService.getInstance().userCacheRefresh();
			LOGGER.info("定时缓存用户信息的定时器任务执行结束...");
		} catch (Exception e) {
			LOGGER.error("定时缓存用户信息的定时器任务执行失败...", e);
			e.printStackTrace();
		}
	}

	
}
