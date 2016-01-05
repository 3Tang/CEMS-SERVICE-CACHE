package com.vrv.cems.service.quartz;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.vrv.cems.service.cache.service.JobService;

public class DeviceCacheJob implements Job{
	private static final Logger LOGGER = Logger.getLogger(DeviceCacheJob.class);
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			LOGGER.info("定时缓存设备信息的定时器任务开始执行...");
			JobService.getInstance().deviceCacheRefresh();
			LOGGER.info("定时缓存设备信息的定时器任务执行结束...");
		} catch (Exception e) {
			LOGGER.error("定时缓存设备信息的定时器任务执行失败...", e);
			e.printStackTrace();
		}
}
}
