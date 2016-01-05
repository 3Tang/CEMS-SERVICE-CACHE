package com.vrv.cems.service.cache;

import static com.vrv.cems.service.base.SystemConstants.PATH_CONFIG_PROPERTIES;
import static com.vrv.cems.service.base.SystemConstants.PATH_LOG4J_PROPERTIES;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.tanukisoftware.wrapper.WrapperListener;
import org.tanukisoftware.wrapper.WrapperManager;

import com.sys.common.util.CommentedProperties;
import com.vrv.cems.service.base.SystemConstants;
import com.vrv.cems.service.base.server.Service;
import com.vrv.cems.service.base.server.factory.ServiceFactory;
import com.vrv.cems.service.cache.action.ICacheServiceAction;
import com.vrv.cems.service.cache.core.CacheServer;
import com.vrv.cems.service.cache.util.InitSystem;
import com.vrv.cems.service.cache.util.PolicyConfigService;
import com.vrv.cems.service.cache.util.ReadConfigFileUtil;
import com.vrv.cems.service.configure.quartzJob.QuartzConfigure;
import com.vrv.cems.service.init.CacheServiceInfo;
import com.vrv.cems.service.init.ConfigurationServiceable;
import com.vrv.cems.service.quartz.QuartzConfig;
import com.vrv.cems.service.quartz.ServiceParamBean;

/**
 * <B>说 明</B>:缓存服务(CEMS-SERVICE-CACHE)主程序
 * 
 * @author 作 者 名：glw<br/>
 *         E-mail ：linwu_gao@163.com
 * 
 * @version 版 本 号：V1.0.<br/>
 *          创建时间：2014年8月22日 下午2:35:09
 */
public class ServiceMain implements WrapperListener {
	private static final Logger logger = Logger.getLogger(ServiceMain.class);
	public static ServiceParamBean serviceParamBean;
	private static CacheServiceInfo cacheServiceInfo;
	/**
	 * 任务执行线程池服务
	 */
	private final ExecutorService exec = Executors.newCachedThreadPool();
	
	/**
	 * 静态代码块，初始化一部分参数
	 */
	static{
		PropertyConfigurator.configure(PATH_LOG4J_PROPERTIES);
		try {
			serviceParamBean= PolicyConfigService.getInstance().getLocalPolicyParam(SystemConstants.PATH_POLICY_XML);
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		cacheServiceInfo = ReadConfigFileUtil.getCacheServiceInfo(ReadConfigFileUtil.getProperties(PATH_CONFIG_PROPERTIES));
	}


	/**
	 * 键盘控制事件. 例如：Ctrl + c 停止服务
	 */
	@Override
	public void controlEvent(int arg0) {
		// TODO Auto-generated method stub
		WrapperManager.stop(arg0);
	}
	
	Service service = ServiceFactory.DEFAULT.getService();

	QuartzConfig quartzConfigure = null;

	/**
	 * 启动服务
	 */

	public Integer start(String[] arg0) {
		try{
		//1.启动服务
		logger.info("缓存服务启动中...");
		new Thread(new Runnable() {
			public void run() {
				try {
					service.start(new ICacheServiceAction());
				} catch (Exception e) {
					logger.error("缓存服务启动启动失败", e);
				}
			}
		}).start();
		logger.info("缓存服务启动成功");
		//2.注册服务
		service.register();
		
		//3.定时器
		QuartzConfig.getInstance().init();
		
		
		
	} catch (Exception e) {
		  logger.error("启动缓存服务失败", e);
	}
	return null;
	}

	/**
	 * 停止服务
	 */
	@Override
	public int stop(int arg0) {
		exec.shutdown();
		//定时器配置执行销毁
		QuartzConfig.getInstance().destroy();
		return 0;
	}

	/**
	 * 主入口
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("程序引导启动...");
		WrapperManager.start(new ServiceMain(), args);
	}

}
