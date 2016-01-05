package com.vrv.cems.service.cache.service; 

import org.apache.log4j.Logger;

import com.vrv.cems.service.base.bean.cache.DevicePolicyCache;
import com.vrv.cems.service.base.bean.cache.Result;

/** 
 *   <B>说       明</B>:设备执行策略信息Service接口
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午9:51:00 
 */
public interface DevicePolicyService {


	public Logger log = Logger.getLogger(DevicePolicyService.class);

	/**
	 * 保存设备执行策略信息
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：601
	 * @param devicePolicyCache	需要保存的设备执行策略信息
	 * @return
	 *		Result(0,保存设备执行策略信息到缓存成功.);;
	 *		Result(1,保存设备执行策略信息到缓存失败[设备执行策略信息为空]!);;
	 *		Result(2,保存设备执行策略信息到缓存失败[设备执行策略信息devOnlyId为空]!);;
	 */
	public Result saveDevicePolicy(String maxCode,String minCode,DevicePolicyCache devicePolicyCache);
	
	/**
	 * 更新设备执行策略信息
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：602
	 * @param devicePolicyCache	需要更新的设备执行策略信息
	 * @return
	 *		Result(0,更新设备执行策略信息到缓存成功.);;
	 *		Result(1,更新设备执行策略信息到缓存失败[设备执行策略信息为空]!);;
	 *		Result(2,更新设备执行策略信息到缓存失败[设备执行策略信息devOnlyId为空]!);;
	 */
	public Result updateDevicePolicy(String maxCode,String minCode,DevicePolicyCache devicePolicyCache);
	
	/**
	 * 查询设备执行策略信息，根据设备devOnlyId
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：603
	 * @param devOnlyId			需要查询的设备devOnlyId
	 * @return
	 *		DevicePolicyCache	需要查询的设备执行策略信息
	 */
	public DevicePolicyCache queryDevicePolicyByDevOnlyId(String maxCode,String minCode,String devOnlyId);
	
	/**
	 * 查询设备执行策略信息，根据设备IP 
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：604
	 * @param ip				需要查询的设备IP 
	 * @return
	 *		DevicePolicyCache	需要查询的设备执行策略信息
	 */
	public DevicePolicyCache queryDevicePolicyByIp(String maxCode,String minCode,String ip);
	
	/**
	 * 删除设备执行策略信息，根据设备devOnlyId
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：605
	 * @param devOnlyId			需要删除的设备devOnlyId
	 * @return
	 *		Result(0,从缓存中删除设备执行策略信息成功.);;
	 *		Result(1,从缓存中删除设备执行策略信息失败[设备执行策略信息为空]!);;
	 *		Result(2,从缓存中删除设备执行策略信息失败[设备执行策略信息devOnlyId为空]!);;
	 */
	public Result deleteDevicePolicyByDevOnlyId(String maxCode,String minCode,String devOnlyId);
	
	/**
	 * 删除设备执行策略信息，根据设备IP
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：606
	 * @param ip				需要删除的设备IP
	 * @return
	 *		Result(0,从缓存中删除设备执行策略信息成功.);;
	 *		Result(1,从缓存中删除设备执行策略信息失败[设备IP为空]!);;
	 */
	public Result deleteDevicePolicyByIp(String maxCode,String minCode,String ip);
	
}
 