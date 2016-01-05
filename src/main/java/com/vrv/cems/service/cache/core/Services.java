package com.vrv.cems.service.cache.core;

import com.vrv.cems.service.cache.service.CommonService;
import com.vrv.cems.service.cache.service.DBJobData2RedisService;
import com.vrv.cems.service.cache.service.DeviceInsProNewService;
import com.vrv.cems.service.cache.service.DeviceInsProOldService;
import com.vrv.cems.service.cache.service.DeviceKeyService;
import com.vrv.cems.service.cache.service.DeviceOnlineService;
import com.vrv.cems.service.cache.service.DevicePolicyService;
import com.vrv.cems.service.cache.service.DeviceService;
import com.vrv.cems.service.cache.service.IP2DevOnlyIdService;
import com.vrv.cems.service.cache.service.IPMAC2DevOnlyIdService;
import com.vrv.cems.service.cache.service.JobDataFromDBService;
import com.vrv.cems.service.cache.service.PolicyService;
import com.vrv.cems.service.cache.service.ProductCInstallPackService;
import com.vrv.cems.service.cache.service.ProductCUpgradePackService;
import com.vrv.cems.service.cache.service.UserOnlineService;
import com.vrv.cems.service.cache.service.UserPolicyService;
import com.vrv.cems.service.cache.service.UserService;
import com.vrv.cems.service.cache.service.impl.CommonServiceImpl;
import com.vrv.cems.service.cache.service.impl.DBJobData2RedisServiceImpl;
import com.vrv.cems.service.cache.service.impl.DeviceInsProNewServiceImpl;
import com.vrv.cems.service.cache.service.impl.DeviceInsProOldServiceImpl;
import com.vrv.cems.service.cache.service.impl.DeviceKeyServiceImpl;
import com.vrv.cems.service.cache.service.impl.DeviceOnlineServiceImpl;
import com.vrv.cems.service.cache.service.impl.DevicePolicyServiceImpl;
import com.vrv.cems.service.cache.service.impl.DeviceServiceImpl;
import com.vrv.cems.service.cache.service.impl.IP2DevOnlyIdServiceImpl;
import com.vrv.cems.service.cache.service.impl.IPMAC2DevOnlyIdServiceImpl;
import com.vrv.cems.service.cache.service.impl.JobDataFromDBServiceImpl;
import com.vrv.cems.service.cache.service.impl.PolicyServiceImpl;
import com.vrv.cems.service.cache.service.impl.ProductCInstallPackServiceImpl;
import com.vrv.cems.service.cache.service.impl.ProductCUpgradePackServiceImpl;
import com.vrv.cems.service.cache.service.impl.UserOnlineServiceImpl;
import com.vrv.cems.service.cache.service.impl.UserPolicyServiceImpl;
import com.vrv.cems.service.cache.service.impl.UserServiceImpl;



/** 
 *   <B>说       明</B>:Services CacheService内部服务类
 *
 * @author  作  者  名 ：tangtieqiao<br/>
 *		    E-mail ：tangtieqiao@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年5月12日上午9:00:21 
 */
public class Services {

	/**
	 * 设备缓存服务类
	 */
	public static DeviceService deviceService ;

	/**
	 * 应安装产品信息服务类
	 */ 
	public static DeviceInsProNewService deviceInsProNewService;

	/**
	 * 已安装产品信息层
	 */
	public static DeviceInsProOldService deviceInsProOldService;

	/**
	 * 设备会话秘钥服务类
	 */
	public static DeviceKeyService deviceKeyService;

	/**
	 * 设备在线服务类
	 */
	public static DeviceOnlineService deviceOnlineService;

	/**
	 * 设备策略服务类
	 */
	public static DevicePolicyService devicePolicyService;

	/**
	 * IP对应DevOnlyID服务类
	 */
	public static IP2DevOnlyIdService ip2DevOnlyIdService;

	/**
	 * IPMAC对应DevOnlyID服务类
	 */
	public static IPMAC2DevOnlyIdService ipMAC2DevOnlyIdService;
	/**
	 * 策略服务类
	 */
	public static PolicyService policyService;
	/**
	 * 产品安装包服务类
	 */
	public static ProductCInstallPackService productCInstallPackService;
	
	/**
	 * 产品升级包服务类
	 */
	public static ProductCUpgradePackService productCUpgradePackService;

	/**
	 * 用户在线服务类
	 */
	public static UserOnlineService userOnlineService;

	/**
	 * 用户策略服务类
	 */
	public static UserPolicyService userPolicyService;
	
	/**
	 * 用户服务类
	 */
	public static UserService userService;
	
	
	/**
	 * 从db里取数据
	 */
	public static JobDataFromDBService jobDataFromDBService;
	
	
	
	/**
	 * 把db数据放入Redis
	 */
	public static DBJobData2RedisService dbJobData2RedisService;
	
	/**
	 * 其他服务类
	 */
	public static CommonService commonService;
	

	static
	{
		deviceService=new DeviceServiceImpl();
		dbJobData2RedisService=new DBJobData2RedisServiceImpl();
		jobDataFromDBService=new JobDataFromDBServiceImpl();
		userService=new UserServiceImpl();
		userPolicyService=new UserPolicyServiceImpl();
		userOnlineService=new UserOnlineServiceImpl();
		productCUpgradePackService =new ProductCUpgradePackServiceImpl();
		productCInstallPackService=new ProductCInstallPackServiceImpl();
		policyService=new PolicyServiceImpl();
		ipMAC2DevOnlyIdService=new IPMAC2DevOnlyIdServiceImpl();
		ip2DevOnlyIdService=new IP2DevOnlyIdServiceImpl();
		devicePolicyService=new DevicePolicyServiceImpl();
		deviceOnlineService=new DeviceOnlineServiceImpl();
		deviceKeyService=new DeviceKeyServiceImpl();
		deviceInsProOldService=new DeviceInsProOldServiceImpl();
		deviceInsProNewService=new DeviceInsProNewServiceImpl();
		commonService=new CommonServiceImpl();
		
	}
}
