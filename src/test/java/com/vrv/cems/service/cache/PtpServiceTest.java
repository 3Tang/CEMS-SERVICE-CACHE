package com.vrv.cems.service.cache;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.junit.Before;
import org.junit.Test;

import com.sys.common.util.PrintUtils;
import com.vrv.cems.service.base.bean.cache.DeviceOnlineCache;
import com.vrv.cems.service.base.bean.cache.DeviceProduct;
import com.vrv.cems.service.base.bean.cache.PolicyCache;
import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.base.bean.cache.UserCache;
import com.vrv.cems.service.base.interfaces.ICacheService;
import com.vrv.cems.service.cache.action.ICacheServiceAction;
import com.vrv.cems.service.cache.core.Services;
import com.vrv.cems.service.cache.util.RedisUtil;
import com.vrv.voa.client.ServiceFactory;

public class PtpServiceTest {

	/*
	private Logger log = Logger.getLogger(PtpServiceTest.class);
	String uuid;
	String ptpResult;
	int expiredTime;
	String account;
	String userOnlyId;

	private Result result;
	@Before
	public void init(){
		
		//uuid="23452345234523452345234523452345";
		uuid="123445";
		ptpResult=" [{'procId':'进程ID','mark':'进程标记：0-不可结束进程；1-可结束','procName':'进程名称','procUrl:'进程路径','cpuPer':'cpu百分比','memory':'内存'，'threadCount':'线程数'，'handleCount':'句柄数'，'desc':'描述'}]";
		//expiredTime=5;
	}
	
	
	//验证Key在缓存中是否已存在
	@Test
	public void operation() throws TException
	{
		result = iCacheServiceAction.savePtp(ICacheService.SERVICE_CODE, ICacheService.SAVEPTP, uuid,ptpResult);
		log.info("第一步存储"+result.getCode()+":"+result.getInfo());
		
		//String userOnlyId="77777777777777777777777777777777";
		//String uuid2="3aca2bb0e9dc43848824278541bddc31";
		result=iCacheServiceAction.isExist(ICacheService.SERVICE_CODE, ICacheService.ISEXIST,ICacheService.PREFIX_PTPCACHE,uuid);
		log.info("第二步判断是否存在"+result.getCode()+":"+result.getInfo());
		//String uuid2="3aca2bb0e9dc43848824278541bddc31";
		//String ptpResult=iCacheServiceAction.queryPtp(ICacheService.SERVICE_CODE, ICacheService.QUERYPTP,uuid);
		if(StringUtils.isBlank(ptpResult))
		{
			System.out.println("ptpResult为空");
		}
		log.info("第三步查询"+"ptpResult:"+ptpResult);
		
		result = iCacheServiceAction.deletePtp(ICacheService.SERVICE_CODE, ICacheService.DELETEPTP,uuid);
		log.info("第四步删除"+result.getCode()+":"+result.getInfo());
		
		result=iCacheServiceAction.isExist(ICacheService.SERVICE_CODE, ICacheService.ISEXIST,ICacheService.PREFIX_PTPCACHE,uuid);
		log.info("第五步再次判断是否存在"+result.getCode()+":"+result.getInfo());
		List<DeviceProduct> dpList = new ArrayList<DeviceProduct>();	
		ICacheService cacheClient = null;
		String devOnlyId="31f77825c62498111ee1a58586ac105b";
		dpList = iCacheServiceAction.queryDeviceInsProNewByDevOnlyId(
				ICacheService.SERVICE_CODE,
				ICacheService.QUERYDEVICEINSPRONEWBYDEVONLYID, devOnlyId);
		
		deviceOnlineCache(devOnlyId:ed25aacda704689f76c06090a266992c, loginTime:2015-07-02 20:47:33,
				activeTime:2015-07-02 19:36:53, sessionId:ed25aacda704689f76c06090a266992c,
				udpActiveTime:2015-07-02 20:44:26, routeIp:192.168.32.14, udpPort:22116);
		
		DeviceOnlineCache deviceOnlineCache=new DeviceOnlineCache();
		deviceOnlineCache.setDevOnlyId("ed25aacda704689f76c06090a266992c");
		deviceOnlineCache.setLoginTime("2015-07-02 20:47:33");
		deviceOnlineCache.setActiveTime("2015-07-02 19:36:53");
		deviceOnlineCache.setSessionId("ed25aacda704689f76c06090a266992c");
		deviceOnlineCache.setUdpActiveTime("2015-07-02 20:44:26");
		deviceOnlineCache.setRouteIp("192.168.32.14");
		deviceOnlineCache.setUdpPort("22116");
		
		iCacheServiceAction.saveDeviceOnline(ICacheService.SERVICE_CODE, ICacheService.SAVEDEVICEONLINE, deviceOnlineCache);
		
		Set<String> devOnlyIdSet = new HashSet<String>();
		
		devOnlyIdSet= RedisUtil.getInstance().keys(
					ICacheService.PREFIX_DEVICEONLINECACHE + "*");
		 
		devOnlyIdSet= RedisUtil.getInstance().keys(
					ICacheService.PREFIX_DEVICECACHE + "*");
		
		 DeviceOnlineCache deviceOnline=iCacheServiceAction.queryDeviceOnlineByDevOnlyId(ICacheService.SERVICE_CODE, ICacheService.QUERYDEVICEONLINEBYDEVONLYID, "ed25aacda704689f76c06090a266992c");
		 PrintUtils.print(deviceOnline);
	}
	
	@Test
	public void testPtp()
	{
		String uuid="f2be61e68d823c988c740d4778fac890";
		
		String uuid2="12244";
		String value2="12255";
		
		String value=null;
		Result result=null;
		try {
			iCacheServiceAction.savePtp(ICacheService.SERVICE_CODE, ICacheService.SAVEPTP, uuid2, value2);
			result=iCacheServiceAction.isExist(ICacheService.SERVICE_CODE, ICacheService.ISEXIST, ICacheService.PREFIX_PTPCACHE, uuid2);
			 value=iCacheServiceAction.queryPtp(ICacheService.SERVICE_CODE, ICacheService.QUERYPTP, uuid2);
		} catch (TException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.println("result"+result.toString());
		System.out.println("value"+value);
	}*/
	
	@Test
	public void testDeviceOnline()
	{
		Set<String> devOnlyIdSet = new HashSet<String>();
		devOnlyIdSet = RedisUtil.getInstance().keys(
				ICacheService.PREFIX_DEVICEONLINECACHE + "*");
		if (devOnlyIdSet.size() > 0) {
			Iterator iterator = devOnlyIdSet.iterator();
			while (iterator.hasNext()) {
				
				DeviceOnlineCache deviceOnlineCache = new DeviceOnlineCache();
				String devOnlineKey = (String) iterator.next();
				String devOnlyId = devOnlineKey.replace(
						ICacheService.PREFIX_DEVICEONLINECACHE, "");
				deviceOnlineCache = Services.deviceOnlineService
						.queryDeviceOnlineByDevOnlyId(
								ICacheService.SERVICE_CODE,
								ICacheService.QUERYDEVICEONLINEBYDEVONLYID,
								devOnlyId);

				String activeTimeStr = deviceOnlineCache.getActiveTime();
				System.out.println("activeTimeStr"+activeTimeStr);
			}
	}
}
	
	@Test
	public void testPolicyCache()
	{
		 ICacheServiceAction iCacheServiceAction=new ICacheServiceAction();
		String policyId="b48cb898d6b04b909a9c1392d41072a9";
		PolicyCache policyCache =new PolicyCache();
		try {
			policyCache=iCacheServiceAction.queryPolicyByPolicyId(ICacheService.SERVICE_CODE,ICacheService.QUERYPOLICYBYPOLICYID,policyId);
		} catch (TException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		System.out.println(policyCache.toString());
	}
	
	@Test
	public void testIsExist()
	{
		ICacheServiceAction iCacheServiceAction=new ICacheServiceAction();
		
		String userOnlyId="60f3c9fe03fee9250d4a6865f143bdf0";
		String testIp="111.112.113.114";
		String testDevOnlyId="111112113114";
		Result userExist=new Result();
		UserCache userCache =new UserCache();
	
		String id="0c548442eedf4993bd7674fcbb7aa74e";
		String value="[{'procId':'进程ID','flag':'1','procName':'进程名称','procUrl':'进程路径','cpuPer':'所占cpu百分比','memory':'所占内存','threadCount':'线程数','handleCount':'句柄数','desc':'描述'}]";

			try {
				userExist=iCacheServiceAction.savePtp(ICacheService.SERVICE_CODE, ICacheService.SAVEPTP, id, value);

			} catch (TException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		
		
		System.out.println("userExist "+userExist.getCode()+":"+userExist.getInfo());
		
	}
	
	
}