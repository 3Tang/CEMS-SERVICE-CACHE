package com.vrv.cems.service.cache.service; 

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.vrv.cems.service.base.bean.cache.DeviceCache;
import com.vrv.cems.service.base.bean.cache.Result;

/** 
 *   <B>说       明</B>:设备信息Service接口
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月21日 下午6:36:02 
 */
public interface DeviceService {
	public Logger log = Logger.getLogger(DeviceService.class);
	/**
	 * 保存设备信息
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：101
	 * @param deviceCache	需要保存的设备信息
	 * @return
	 *		Result(0,保存设备信息到缓存成功.);
	 *		Result(1,保存设备信息到缓存失败[设备信息为空]!);
	 *		Result(2,保存设备信息到缓存失败[设备devOnlyId为空]!);
	 */
	public Result saveDevice(String maxCode, String minCode,DeviceCache deviceCache);
	
	/**
	 * 更新设备信息
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：102
	 * @param deviceCache	需要更新的设备信息
	 * @return	
	 *		Result(0,更新设备信息到缓存成功.);
	 *		Result(1,更新设备信息到缓存失败[设备信息为空]!);
	 *		Result(2,更新设备信息到缓存失败[设备devOnlyId为空]!);
	 */
	public Result updateDevice(String maxCode,String minCode,DeviceCache deviceCache);
	
	/**
	 * 查询设备信息,根据devOnlyId
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：105
	 * @param devOnlyId			设备唯一ID
	 * @return	
	 *		DeviceCache	查询到的设备信息
	 */
	public DeviceCache queryDeviceByDevOnlyId(String maxCode,String minCode,String devOnlyId);
	
	/**
	 * 删除设备信息,根据devOnlyId 
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：108
	 * @param devOnlyId			devOnlyId
	 * @return	
	 *		Result(0,从缓存中删除设备信息成功.);</br>
	 *		Result(1,从缓存中删除设备信息失败[需要删除的设备信息输入值devOnlyId为空]!);</br>
	 */
	public Result deleteDeviceByDevOnlyId(String maxCode, String minCode,String devOnlyId);
	
	
	
	/**
	 * 更新设备信息,只更新指定属性信息
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：103
	 * @param devOnlyId			设备唯一ID
	 * @param fieldValueMap		需要更新的属性名及属性值
	 * @return	
	 *		Result(0,更新设备信息到缓存成功.);
	 *		Result(1,更新设备信息到缓存失败[需要更新的属性名及属性值为空]!);
	 *		Result(2,更新设备信息到缓存失败[设备devOnlyId为空]!);
	 */
	public Result updateDeviceByField(String maxCode,String minCode,String devOnlyId,Map<String,String> fieldValueMap);
	
	
	/**
	 * 查询设备信息
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：104
	 * @param deviceCache		需要查询的设备信息(至少devOnlyId不能为空)
	 * @return	
	 *		DeviceCache	查询到的设备信息
	 */
	public DeviceCache queryDevice(String maxCode,String minCode, DeviceCache deviceCache);
	
	
	/**
	 * 查询设备信息,根据IP 
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：106
	 * @param ip				设备IP地址
	 * @return	
	 *		DeviceCache		查询到的设备信息
	 */
	public DeviceCache queryDeviceByIp(String maxCode,String minCode,String ip);
	
	
	/**
	 * 删除设备信息 
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：107
	 * @param deviceCache		需要删除的设备信息(至少devOnlyId不能为空)
	 * @return	
	 *		Result(0,从缓存中删除设备信息成功.);
	 *		Result(1,从缓存中删除设备信息失败[需要删除的设备信息输入值为空]!);
	 *		Result(2,从缓存中删除设备信息失败[需要删除的设备信息输入值devOnlyId为空]!);
	 */
	public Result deleteDevice(String maxCode,String minCode,DeviceCache deviceCache);
	
	
	
	/**
	 * 删除设备信息,根据IP 
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：109
	 * @param ip				IP地址
	 * @return	
	 *		Result(0,从缓存中删除设备信息成功.);
	 *		Result(1,从缓存中删除设备信息失败[需要删除的设备信息输入值ip为空]!);
	 */
	public Result deleteDeviceByIp(String maxCode,String minCode,String ip);
	
	
	/**
	 * 批量保存设备信息
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：110
	 * @param deviceCacheList	需要保存的设备信息集合
	 * @return
	 *		Result(0,保存设备信息到缓存成功.);
	 *		Result(1,保存设备信息到缓存失败[设备信息为空]!);
	 *		Result(2,保存设备信息到缓存失败[设备devOnlyId为空]!);
	 */
	List<Result> batchSaveDevice(String maxCode,String minCode,List<DeviceCache> deviceCacheList);
	
	

	/**
	 * 批量更新设备信息
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：111
	 * @param deviceCacheList	需要保存的设备信息集合
	 * @return
	 *		Result(0,更新设备信息到缓存成功.);
	 *		Result(1,更新设备信息到缓存失败[需要更新的属性名及属性值为空]!);
	 *		Result(2,更新设备信息到缓存失败[设备devOnlyId为空]!);
	 */
	List<Result> batchUpdateDevice(String maxCode,String minCode,List<DeviceCache> deviceCacheList);
	
	
	
	/**
	 * 批量查询设备信息
	 * @param maxCode				缓存服务号：00FF0700
	 * @param minCode				缓存服务功能号：112
	 * @param deviceCacheList		需要查询的设备信息集合(至少devOnlyId不能为空)
	 * @return	
	 *		List<DeviceCache>	查询到的设备信息
	 */
	List<DeviceCache> batchQueryDevice(String maxCode,String minCode,List<DeviceCache> deviceCacheList);
	
	
	/**
	 * 批量查询设备信息，根据设备devOnlyId
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：113
	 * @param deviceCache		需要查询的设备信息(至少devOnlyId不能为空)
	 * @return	
	 *		List<DeviceCache>	查询到的设备信息集合
	 */
	List<DeviceCache> batchQueryDeviceByDevOnlyId(String maxCode,String minCode,List<String> devOnlyIdList);
	
	
	/**
	 * 批量删除设备信息 
	 * @param maxCode				缓存服务号：00FF0700
	 * @param minCode				缓存服务功能号：114
	 * @param deviceCacheList		需要删除的设备信息集合(至少devOnlyId不能为空)
	 * @return	
	 *		Result(0,从缓存中删除设备信息成功.);
	 *		Result(1,从缓存中删除设备信息失败[需要删除的设备信息输入值为空]!);
	 *		Result(2,从缓存中删除设备信息失败[需要删除的设备信息输入值devOnlyId为空]!);
	 */
	List<Result> batchDeleteDevice(String maxCode,String minCode,List<DeviceCache> deviceCacheList);
	
	
	/**
	 * 批量删除设备信息，根据devOnlyId
	 * @param maxCode				缓存服务号：00FF0700
	 * @param minCode				缓存服务功能号：115
	 * @param devOnlyIdList			需要删除的设备信息devOnlyId集合
	 * @return	
	 *		Result(0,从缓存中删除设备信息成功.);
	 *		Result(1,从缓存中删除设备信息失败[需要删除的设备信息输入值为空]!);
	 *		Result(2,从缓存中删除设备信息失败[需要删除的设备信息输入值devOnlyId为空]!);
	 */
	List<Result> batchDeleteDeviceByDevOnlyId(String maxCode,String minCode,List<String> devOnlyIdList);
	
	
	
	
	
	
}
 