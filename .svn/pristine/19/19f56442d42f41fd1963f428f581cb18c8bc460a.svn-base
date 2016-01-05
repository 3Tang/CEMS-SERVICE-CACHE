package com.vrv.cems.service.quartz;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.vrv.cems.service.cache.service.JobService;



/** 
 *   <B>说       明</B>:PolicyCacheJob缓存策略信息JOB
 *
 * @author  作  者  名 ：tangtieqiao<br/>
 *		    E-mail ：tangtieqiao@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年5月12日上午8:57:02 
 */
public class PolicyCacheJob implements Job{

	private static final Logger LOGGER = Logger.getLogger(PolicyCacheJob.class);
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			LOGGER.info("定时缓存策略信息的定时器任务开始执行...");
			JobService.getInstance().policyCacheRefresh();
			LOGGER.info("定时缓存策略信息的定时器任务执行结束...");
		} catch (Exception e) {
			LOGGER.error("定时缓存策略信息的定时器任务执行失败...", e);
			e.printStackTrace();
		}
	}
}
