package com.vrv.cems.service.cache;



import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.vrv.cems.service.cache.service.DeviceService;
import com.vrv.cems.service.cache.service.impl.DeviceServiceImpl;
import com.vrv.cems.service.cache.util.RedisUtil;


/** 
 *   <B>说       明</B>:设备信息Service单元测试类
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月21日 下午10:03:20 
 */
public class DeviceServiceTest {
	/*private Logger log = Logger.getLogger(DeviceServiceTest.class);
	private DeviceService deviceService;
	private Result result;
	private DeviceCache deviceCache = new DeviceCache();
	private DeviceCache deviceCache1 = new DeviceCache();
	private DeviceCache deviceCache2 = new DeviceCache();
	private ICacheServiceAction iCacheServiceAction=new ICacheServiceAction();
	private List<DeviceCache> deviceCacheList = new ArrayList<DeviceCache>();
	private List<Result> resultList = new ArrayList<Result>();
	*//**
	 * 初始化一些该单元测试类需要的数据
	 *//*
	@Before
	public void init(){
		
		
		
		deviceService = new DeviceServiceImpl();
		result = new Result();
		
		deviceCache.setId("22222222222222222222222222222222");
		deviceCache.setDevOnlyId("77777777777777777777777777777777");
		deviceCache.setIp("192.168.32.104");
		//继续完善...
		deviceCache.setClientId("72019b56795b49fe8ce1cd0d3477ef");
		deviceCache.setClientVersion("1.2");
		deviceCache.setIpNumber("192.168.0.10");
		deviceCache.setUserOnlyId("72019b56795b49fe8ce1cd0d3477efd7");
		deviceCache.setState(16);
		
		
		deviceCache1 = new DeviceCache();
		deviceCache1.setId("33333333333333333333333333333333");
		deviceCache1.setDevOnlyId("tttttttttttttttttttttttttttttttt");
		deviceCache1.setIp("192.168.32.101");
		//继续完善...
		deviceCache1.setClientId("12345678123456781234567812345678");
		deviceCache1.setClientVersion("1.3");
		deviceCache1.setIpNumber("192.168.0.11");
		deviceCache1.setUserOnlyId("12345678123456781234567812345678");
		deviceCache1.setState(1);
	
	}
	

	
	@Test
	public void testSaveDevice() throws TException{
		
		result = iCacheServiceAction.saveDevice(ICacheService.SERVICE_CODE, ICacheService.SAVEDEVICE, deviceCache);
		log.info(result.getCode()+":"+result.getInfo());
		String minCode="101";
		if(minCode!=ICacheService.SAVEDEVICE)
		{
			System.out.println("好吧,我错了!");
			log.info("好吧,我错了!");
		}
	}
	
   
	*//**
	 * 测试更新设备信息方法
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws TException 
	 *//*
	@Test
	public void testUpdateDevice() throws IllegalAccessException, InvocationTargetException, TException{
		String devOnlyId = "77777777777777777777777777777777";
		String devOnlyId2 = "77777777777777777777777777777777";

		//更新操作先查询，后更新
		//查询
	
		DeviceCache deviceCacheOld = iCacheServiceAction.queryDeviceByDevOnlyId(ICacheService.SERVICE_CODE, ICacheService.QUERYDEVICEBYDEVONLYID, devOnlyId2);
		
		log.info("更新前缓存中的值返回结果为："+deviceService.result.getInfo());
		log.info("更新前缓存中的值："+deviceCacheOld.toString());
		
		//属性Copy
		BeanUtils.copyProperties(deviceCache, deviceCacheOld);
		deviceCache.setId("11111111111111111111111111111111");
		deviceCache.setIp("192.168.32.103");
		
		//更新
		result = iCacheServiceAction.updateDevice(ICacheService.SERVICE_CODE, ICacheService.SAVEDEVICE, deviceCache);
		log.info(result.getCode()+":"+result.getInfo());
		
		//输出是否更新了
		deviceCache = iCacheServiceAction.queryDeviceByDevOnlyId(ICacheService.SERVICE_CODE, ICacheService.QUERYDEVICEBYDEVONLYID, devOnlyId2);
		log.info("更新后缓存中的值："+deviceCache.toString());
	}
	
	*//**
	 * 测试删除设备信息方法
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws TException 
	 *//*
	@Test
	public void testDeleteDevice() throws IllegalAccessException, InvocationTargetException, TException{
		String devOnlyId = "77777777777777777777777777777777";
		
		result = iCacheServiceAction.deleteDeviceByDevOnlyId(ICacheService.SERVICE_CODE, ICacheService.DELETEDEVICEBYDEVONLYID, devOnlyId);
		log.info("删除结果："+result.toString());
	}
	
	
	*//**
	 * 测试更新设备信息方法
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws TException 
	 *//*
	@Test
	public void testUpdateDeviceByField() throws IllegalAccessException, InvocationTargetException, TException{
		String devOnlyId="77777777777777777777777777777777";
		//DeviceCache deviceCacheOld = deviceService.queryDeviceByIp(ICacheService.SERVICE_CODE, ICacheService.QUERYDEVICEBYDEVONLYID, ip);
		DeviceCache deviceCacheOld = iCacheServiceAction.queryDeviceByDevOnlyId(ICacheService.SERVICE_CODE, ICacheService.QUERYDEVICEBYDEVONLYID, devOnlyId);
		log.info("更新前缓存中的值："+deviceCacheOld.toString());
		//查询
   		Map<String,String> fieldValueMap = new HashMap();
		fieldValueMap.put("state", "20");
		result = iCacheServiceAction. updateDeviceByField(ICacheService.SERVICE_CODE, ICacheService.UPDATEDEVICEBYFIELD, devOnlyId, fieldValueMap);
		log.info(result.getCode()+":"+result.getInfo());
		
		//输出是否更新了
		deviceCache = iCacheServiceAction.queryDeviceByDevOnlyId(ICacheService.SERVICE_CODE, ICacheService.QUERYDEVICEBYDEVONLYID, devOnlyId);
		log.info("更新后缓存中的值："+deviceCache.toString());
	}
	
	*//**
	 * 测试批量保存设备信息方法
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 *//*
	@Test
	public void testBatchSaveDevicey() throws IllegalAccessException, InvocationTargetException{
		//设置存储对象
		deviceCache2 = new DeviceCache();
		deviceCache2.setId("33333333333333333333333333333333");
		deviceCache2.setDevOnlyId("33333333333333333333333333333333");
		deviceCache2.setIp("192.168.32.101");
		//继续完善...
		deviceCache2.setClientId("12345678123456781234567812345678");
		deviceCache2.setClientVersion("1.3");
		deviceCache2.setIpNumber("192.168.0.11");
		deviceCache2.setUserId("12345678123456781234567812345678");
		deviceCache2.setState(1);
		
		DeviceCache deviceCache3 = new DeviceCache();
		deviceCache3.setId("33333333333333333333333333333333");
		deviceCache3.setDevOnlyId("44444444444444444444444444444444");
		deviceCache3.setIp("192.168.32.101");
		//继续完善...
		deviceCache3.setClientId("12345678123456781234567812345678");
		deviceCache3.setClientVersion("1.3");
		deviceCache3.setIpNumber("192.168.0.11");
		deviceCache3.setUserId("12345678123456781234567812345678");
		deviceCache3.setState(1);
		
		
		deviceCacheList.add(deviceCache2);
		deviceCacheList.add(deviceCache3);
		
		resultList = deviceService.batchSaveDevice(ICacheService.SERVICE_CODE, ICacheService.SAVEDEVICE, deviceCacheList);
		for(Result result:resultList){
			log.info(result.getCode()+":"+result.getInfo());
		}
	}
	
	
	*//**
	 * 测试查询保存设备信息方法
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 *//*
	@Test
	public void testBatchQueryDeviceByDevOnlyId() throws IllegalAccessException, InvocationTargetException{
	
		List<String> devOnlyIds=new ArrayList<String>();
		devOnlyIds.add("33333333333333333333333333333333");
		devOnlyIds.add("99999999999999999999999999999999");
		devOnlyIds.add("");
		List<DeviceCache> queryDeviceList = new ArrayList<DeviceCache>();
		queryDeviceList = deviceService.batchQueryDeviceByDevOnlyId(ICacheService.SERVICE_CODE, ICacheService.BATCHQUERYDEVICEBYDEVONLYID,devOnlyIds);
		
		for(DeviceCache deviceCache:queryDeviceList)
		{
			log.info(deviceCache.getDevOnlyId()+"..."+deviceCache.getIp());
		}
		
	}
	

	
	
	*//**
	 * 测试批量删除设备信息方法
	 * @throws TException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 *//*
	@Test
	public void testBatchDeleteDeviceByDevOnlyId() throws IllegalAccessException, InvocationTargetException{
	
		List<String> devOnlyIds=new ArrayList<String>();
		devOnlyIds.add("33333333333333333333333333333333");
		devOnlyIds.add("55555555555555555555555555555555");
		List<DeviceCache> queryDeviceList = new ArrayList<DeviceCache>();
		resultList = deviceService.batchDeleteDeviceByDevOnlyId(ICacheService.SERVICE_CODE, ICacheService.BATCHQUERYDEVICEBYDEVONLYID,devOnlyIds);
		
		for(Result result:resultList)
		{
			log.info(result.getCode()+"..."+result.getInfo());
		}
		
	}
	
	
	@Test
	public void testSomeThing() throws TException
	{
		String devOnlyId="2014416";
		Date loginDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String loginTime = sdf.format(loginDate);
		// 设备登陆接受输入存入缓存设备在线表
		//DeviceOnlineCache deviceOnlineCache = new DeviceOnlineCache();
		
		String sessionId= "33333333333333333333333333333333";
		String ip="192.168.32.110";
		String devOnlyId9=iCacheServiceAction.queryIP2DevOnlyId(ICacheService.SERVICE_CODE, ICacheService.QUERYIP2DEVONLYID, ip);
		System.out.println("devOnlyId"+devOnlyId9);
		
		String ip="192.168.120.185";
		String devOnlyId9=iCacheServiceAction.queryIP2DevOnlyId(ICacheService.SERVICE_CODE, ICacheService.QUERYIP2DEVONLYID, ip);
		System.out.println("devOnlyId"+devOnlyId9);		
		DeviceCache deviceCache=iCacheServiceAction.queryDeviceByIp(ICacheService.SERVICE_CODE, ICacheService.QUERYDEVICEBYIP, ip);
		DeviceCache deviceCache2=iCacheServiceAction.queryDeviceByDevOnlyId(ICacheService.SERVICE_CODE, ICacheService.QUERYDEVICEBYDEVONLYID, devOnlyId9);
		System.out.println("deviceCache"+deviceCache.toString());
		System.out.println("deviceCache2"+deviceCache2.toString());
		DeviceOnlineCache deviceOnlineCache=iCacheServiceAction.queryDeviceOnlineByDevOnlyId(ICacheService.SERVICE_CODE, ICacheService.QUERYDEVICEONLINEBYDEVONLYID,devOnlyId9);
		System.out.println("deviceOnlineCache"+deviceOnlineCache.toString());
		DeviceOnlineCache deviceOnlineCache2=iCacheServiceAction.queryDeviceOnlineByIp(ICacheService.SERVICE_CODE, ICacheService.QUERYDEVICEONLINEBYIP, ip);
		System.out.println("deviceOnlineCache2"+deviceOnlineCache2.toString());
		
		if(StringUtils.isBlank(devOnlyId9)){
			System.out.println("devOnlyId is null");
		}
		else
		{
			System.out.println(devOnlyId9);
		}
		
		deviceOnlineCache.setLoginTime(loginTime);
		deviceOnlineCache.setSessionId(sessionId);
		deviceOnlineCache.setDevOnlyId(devOnlyId);
		deviceOnlineCache.setRouteIp("192.168.0.47");
		deviceOnlineCache.setActiveTime(loginTime);
		
		DeviceKeyCache deviceKeyCache=new DeviceKeyCache();
		deviceKeyCache.setDevOnlyId(devOnlyId);
		deviceKeyCache.setSessionId(sessionId);
		deviceKeyCache.setKeyType("DES");
		
		iCacheServiceAction.saveDeviceKey(ICacheService.SERVICE_CODE,
				ICacheService.SAVEDEVICEONLIE,deviceKeyCache);
		
		
		iCacheServiceAction.saveDeviceOnline(ICacheService.SERVICE_CODE,
				ICacheService.SAVEDEVICEONLIE, deviceOnlineCache);
				
		
		
		DevicePolicyCache policy=iCacheServiceAction.queryDevicePolicyByDevOnlyId(ICacheService.SERVICE_CODE,
				ICacheService.SAVEDEVICEONLIE, devOnlyId);
		String policyVersion = policy.getPolicyVersion();
		Set<String> set=RedisUtil.getInstance().keys(ICacheService.PREFIX_DEVICECACHE+"*");
		String key="53d9cebb3cf6323d5c4888278ac548c7";
		RedisUtil.getInstance().hgetAll(key);
		Iterator iterator = set.iterator();
				System.out.println("set.size()"+set.size());
		while(iterator.hasNext()){
			System.out.println((String)iterator.next());
		}
		
		 String ip="192.168.32.121";
	     String mac="00-01-02-03-04-05";
	     
	     String devOnlyId2 = iCacheServiceAction
					.queryIPMAC2DevOnlyId(ICacheService.SERVICE_CODE,
							ICacheService.QUERYIPMAC2DEVONLYID, ip,mac);
	     
	     if(StringUtils.isNotBlank(devOnlyId2)){
	    	 System.out.println("devOnlyId2"+devOnlyId2);
	     }else
	     {
	    	 System.out.println("devOnlyId2 is null");
	     }
	     
	     
	 	String devOnlyId3 = iCacheServiceAction.queryIP2DevOnlyId(
				ICacheService.SERVICE_CODE,
				ICacheService.QUERYIP2DEVONLYID, ip);
	     if(StringUtils.isNotBlank(devOnlyId3)){
	    	 System.out.println("devOnlyId3"+devOnlyId3);
	     }else
	     {
	    	 System.out.println("devOnlyId3 is null");
	     }
	     
	     
	      result = iCacheServiceAction.isExist(ICacheService.SERVICE_CODE,
					ICacheService.ISEXIST,
					ICacheService.PREFIX_DEVICECACHE, devOnlyId);
	 
	     System.out.println("key是否存在于缓存"+"result.getCode()"+result.getCode()+"result.getInfo()"+result.getInfo());
		System.out.println(RedisUtil.getInstance().exists(ICacheService.PREFIX_DEVICESESSIONCACHE+sessionId));
		
		String devOnliekey=ICacheService.PREFIX_DEVICEONLINECACHE+"2222222222222222222";
		
		System.out.println("devOnlyId"+devOnliekey.replace(ICacheService.PREFIX_DEVICEONLINECACHE, ""));
		PrintUtils.printlnBean(policy);
		
		//log.info("policyVersion"+policyVersion);
		
		 DeviceOnlineCache 	deviceOnlineCache2 =iCacheServiceAction.queryDeviceOnlineByDevOnlyId(ICacheService.SERVICE_CODE, ICacheService.QUERYDEVICEONLIE, devOnlyId);
		 PrintUtils.printlnBean(deviceOnlineCache);

		 DeviceKeyCache  deviceKeyCache  =iCacheServiceAction.queryDeviceKeyByDevOnlyId(ICacheService.SERVICE_CODE, ICacheService.QUERYDEVICEKEYBYDEVONLYID, devOnlyId);
		 PrintUtils.printlnBean(deviceKeyCache);
		
		
		
	}*/
}
 