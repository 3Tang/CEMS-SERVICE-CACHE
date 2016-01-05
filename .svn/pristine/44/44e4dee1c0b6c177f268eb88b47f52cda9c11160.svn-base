package com.vrv.cems.service.cache.service; 

import java.util.Map;

import org.apache.log4j.Logger;

import com.vrv.cems.service.base.bean.cache.DeviceOnlineCache;
import com.vrv.cems.service.base.bean.cache.Result;

/** 
 *   <B>说       明</B>:设备在线Service接口
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午9:41:25 
 */
public interface DeviceOnlineService {
	public Logger log = Logger.getLogger(DeviceOnlineService.class);
	
	/**
	 * 保存设备在线情况信息
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：201
	 * @param deviceOnlineCache	需要保存的设备在线情况信息
	 * @return
	 *		Result(0,保存设备在线情况信息到缓存成功.);;
	 *		Result(1,保存设备在线情况信息到缓存失败[设备在线情况为空]!);;
	 *		Result(2,保存设备在线情况信息到缓存失败[设备在线情况devOnlyId为空]!);;
	 */
	public Result saveDeviceOnline(String maxCode,String minCode,DeviceOnlineCache deviceOnlineCache);
	
	
	/**
	 * 更新设备在线情况信息
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：202
	 * @param deviceOnlineCache	需要更新的设备在线情况信息
	 * @return
	 *		Result(0,更新设备在线情况信息到缓存成功.);;
	 *		Result(1,更新设备在线情况信息到缓存失败[设备在线情况信息为空]!);;
	 *		Result(2,更新设备在线情况信息到缓存失败[设备在线情况devOnlyId为空]!);;
	 */
	public Result updateDeviceOnline(String maxCode,String minCode,DeviceOnlineCache deviceOnlineCache);
	
	
	/**
	 * 更新设备在线情况,只更新指定属性信息
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：203
	 * @param devOnlyId			需要更新设备devOnlyId
	 * @param fieldValueMap		需要更新设备在线情况的属性Key及Value值
	 * @return
	 *		Result(0,更新设备在线情况到缓存成功.);;
	 *		Result(1,更新设备在线情况到缓存失败[设备在线情况属性Key及Value为空]!);;
	 *		Result(2,更新设备在线情况到缓存失败[设备在线情况devOnlyId为空]!);;
	 */
	public Result updateDeviceOnlineByField(String maxCode,String minCode,String devOnlyId,Map<String,String> fieldValueMap );
	
	
	/**
	 * 查询设备在线情况
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：204
	 * @param deviceOnlineCache	需要查询的设备在线情况(至少devOnlyId不能为空);
	 * @return
	 *		DeviceOnlineCache	需要查询的设备在线情况
	 */
	public DeviceOnlineCache queryDeviceOnline(String maxCode,String minCode,DeviceOnlineCache deviceOnlineCache);
	
	
	/**
	 * 查询设备在线情况，根据devOnlyId
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：205
	 * @param devOnlyId			需要查询的设备devOnlyId
	 * @return
	 *		DeviceOnlineCache	需要查询的设备在线情况
	 */
	public DeviceOnlineCache queryDeviceOnlineByDevOnlyId(String maxCode,String minCode,String devOnlyId);
	
	
	/**
	 * 查询设备在线情况，根据IP 
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：206
	 * @param ip				需要查询的设备IP
	 * @return
	 *		DeviceOnlineCache	需要查询的设备在线情况信息
	 */
	public DeviceOnlineCache queryDeviceOnlineByIp(String maxCode,String minCode,String ip);
	
	

	/**
	 * 删除设备在线情况
	 * @param maxCode				缓存服务号：00FF0700
	 * @param minCode				缓存服务功能号：207
	 * @param deviceOnlineCache		需要删除的在线设备情况(至少devOnlyId不能为空);
	 * @return
	 *		Result(0,从缓存中删除设备在线情况信息成功.);;
	 *		Result(1,从缓存中删除设备在线情况信息失败[需要删除的设备信息输入值为空]!);;
	 *		Result(2,从缓存中删除设备在线情况信息失败[需要删除的设备信息输入值devOnlyId为空]!);;
	 */
	public Result deleteDeviceOnline(String maxCode,String minCode,DeviceOnlineCache deviceOnlineCache);
	
	
	/**
	 * 删除设备在线情况，根据devOnlyId
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：208
	 * @param devOnlyId		需要删除的在线设备devOnlyId
	 * @return
	 *		Result(0,从缓存中删除设备在线情况信息成功.);;
	 *		Result(1,从缓存中删除设备在线情况信息失败[需要删除的设备信息输入值devOnlyId为空]!);;
	 */
	public Result deleteDeviceOnlineByDevOnlyId(String maxCode,String minCode,String devOnlyId);
	
	
	/**
	 * 删除设备在线情况，根据IP
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：209
	 * @param ip			需要删除的在线设备IP
	 * @return
	 *		Result(0,从缓存中删除设备在线情况信息成功.);;
	 *		Result(1,从缓存中删除设备在线情况信息失败[需要删除的设备信息输入值ip为空]!);;
	 */
	public Result deleteDeviceOnlineByIp(String maxCode,String minCode,String ip);
	
	
	
	
}
 