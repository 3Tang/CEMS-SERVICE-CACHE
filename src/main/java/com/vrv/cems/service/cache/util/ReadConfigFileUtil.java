package com.vrv.cems.service.cache.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sys.common.util.IPAddressUtils;
import com.sys.common.util.StringUtils;
import com.vrv.cems.service.init.CacheServiceInfo;


public class ReadConfigFileUtil {
	private static final Logger LOGGER = Logger.getLogger(ReadConfigFileUtil.class);
	/**
	 * 获取相关配置文件
	 * @param filePath 文件路径。
	 * @return Properties对象。
	 */
	public static Properties getProperties(String filePath){
		FileInputStream fileInputStream = null;
		Properties prop = null;
		try {
			 fileInputStream = new FileInputStream(filePath);
			 prop = new Properties();
			 prop.load(fileInputStream);
		} catch (FileNotFoundException e) {
			LOGGER.error("策略分发服务中未找到"+filePath+".properties文件！", e);
		} catch (IOException e) {
			LOGGER.error("策略分发服务加载"+filePath+".properties文件失败！", e);
		}finally{
			if(fileInputStream!=null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
	/**
	 * 获取策略分发服务相关配置信息
	 * @param properties 配置文件对象。
	 * @return 策略分发服务配置信息
	 */
	public static CacheServiceInfo getCacheServiceInfo(Properties properties){
		CacheServiceInfo cacheServiceInfo = new CacheServiceInfo();
		String ip = properties.getProperty(CacheServiceInfo.SERVICE_IP);
		if(StringUtils.isBlank(ip)){
			ip = IPAddressUtils.getLocalStringIPAddress();
		}
		cacheServiceInfo.setIp(ip);
		cacheServiceInfo.setPort(properties.getProperty(CacheServiceInfo.SERVICE_PORT));
		cacheServiceInfo.setName(properties.getProperty(CacheServiceInfo.SERVICE_NAME));
		cacheServiceInfo.setSsid(properties.getProperty(CacheServiceInfo.SERVICE_SSID));
		cacheServiceInfo.setCode(properties.getProperty(CacheServiceInfo.SERVICE_CODE));
		cacheServiceInfo.setVersion(properties.getProperty(CacheServiceInfo.SERVICE_VERSION));
		cacheServiceInfo.setDescription(properties.getProperty(CacheServiceInfo.SERVICE_DESCRIPTION));
		cacheServiceInfo.setSelectorThreads(properties.getProperty(CacheServiceInfo.SERVICE_SELECTORTHREADS));
		cacheServiceInfo.setWorkerThreads(properties.getProperty(CacheServiceInfo.SERVICE_WORKERTHREADS));
		cacheServiceInfo.setServerId(IPAddressUtils.ip2UUID(ip));
		return cacheServiceInfo;
		
	}
	/**
	 * 根据Key值获取Key值。
	 * @param properties 配置文件对象。
	 * @param key 键。
	 * @return key值。
	 */
	public static String getValue(Properties properties,String key){
		return properties.getProperty(key);
	}
}
