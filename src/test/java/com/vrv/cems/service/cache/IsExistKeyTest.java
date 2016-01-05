package com.vrv.cems.service.cache;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.junit.Before;
import org.junit.Test;

import com.vrv.cems.service.base.bean.cache.DeviceCache;
import com.vrv.cems.service.base.bean.cache.DeviceKeyCache;
import com.vrv.cems.service.base.bean.cache.DeviceOnlineCache;
import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.base.interfaces.ICacheService;
import com.vrv.cems.service.cache.action.ICacheServiceAction;

public class IsExistKeyTest {
//	private Logger log = Logger.getLogger(IsExistKeyTest.class);
//	DeviceCache deviceCache = new DeviceCache();
//	DeviceKeyCache deviceKeyCache=new DeviceKeyCache();
//	String sessionId;
//	private ICacheServiceAction iCacheServiceAction=new ICacheServiceAction();
//	private Result result;
//	@Before
//	public void init(){
//		
//		deviceCache.setId("22222222222222222222222222222222");
//		deviceCache.setDevOnlyId("77777777777777777777777777777777");
//		deviceCache.setIp("192.168.32.104");
//		//继续完善...
//		deviceCache.setClientId("72019b56795b49fe8ce1cd0d3477ef");
//		deviceCache.setClientVersion("1.2");
//		deviceCache.setIpNumber("192.168.0.10");
//		deviceCache.setUserOnlyId("72019b56795b49fe8ce1cd0d3477efd7");
////		deviceCache.setState(16);
//		
//		 sessionId= "33333333333333333333333333333333";
//		
//		
//		
//		deviceKeyCache.setDevOnlyId("66666666666666666666666666666666");
//		deviceKeyCache.setSessionId(sessionId);
//		deviceKeyCache.setKeyType("DES");
//		
//	}
//	
//	
//	//验证Key在缓存中是否已存在
//	@Test
//	public void isExist() throws TException
//	{
//		result = iCacheServiceAction.saveDevice(ICacheService.SERVICE_CODE, ICacheService.SAVEDEVICE, deviceCache);
//		log.info("第一步存储"+result.getCode()+":"+result.getInfo());
//		
//		String devOnlyId="77777777777777777777777777777777";
//		
//		result=iCacheServiceAction.isExist(ICacheService.SERVICE_CODE, ICacheService.ISEXIST,ICacheService.PREFIX_DEVICECACHE,devOnlyId);
//		log.info("第二步判断是否存在"+result.getCode()+":"+result.getInfo());
//		
//		result = iCacheServiceAction.deleteDeviceByDevOnlyId(ICacheService.SERVICE_CODE, ICacheService.DELETEDEVICEBYDEVONLYID, devOnlyId);
//		log.info("第三步删除"+result.getCode()+":"+result.getInfo());
//		
//		result = iCacheServiceAction.isExist(ICacheService.SERVICE_CODE, ICacheService.SAVEDEVICE,ICacheService.PREFIX_DEVICECACHE,devOnlyId);
//		log.info("第四步再次判断是否存在"+result.getCode()+":"+result.getInfo());
//	}
//	
//	@Test
//	public void isExistSessionIdInDeviceKeyCache() throws TException
//	{
//		result = iCacheServiceAction.saveDeviceKey(ICacheService.SERVICE_CODE, ICacheService.SAVEDEVICEKEY, deviceKeyCache);
//		log.info("第一步存储"+result.getCode()+":"+result.getInfo());
//		
//		
//		
//		result=iCacheServiceAction.isExistSessionIdInDeviceKeyCache(ICacheService.SERVICE_CODE, ICacheService.SAVEDEVICE,sessionId);
//		log.info("第二步判断是否存在"+result.getCode()+":"+result.getInfo());
//		
//		result = iCacheServiceAction.deleteDeviceKeyBySessionId(ICacheService.SERVICE_CODE, ICacheService.DELETEDEVICEBYDEVONLYID, sessionId);
//		log.info("第三步删除"+result.getCode()+":"+result.getInfo());
//		
//		result = iCacheServiceAction.isExistSessionIdInDeviceKeyCache(ICacheService.SERVICE_CODE, ICacheService.SAVEDEVICE,sessionId);
//		log.info("第四步再次判断是否存在"+result.getCode()+":"+result.getInfo());
//	}
//	
//	
}
