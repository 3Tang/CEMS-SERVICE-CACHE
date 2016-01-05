package com.vrv.cems.service.cache.service; 

import com.vrv.cems.service.base.bean.cache.DeviceKeyCache;

import java.util.Map;

import org.apache.log4j.Logger;

import com.vrv.cems.service.base.bean.cache.DeviceKeyCache;
import com.vrv.cems.service.base.bean.cache.Result;

/** 
 *   <B>说       明</B>:设备会话密钥Service 接口
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午9:45:04 
 */
public interface DeviceKeyService {
	

	public Logger log = Logger.getLogger(DeviceKeyService.class);
	/**
	 * 保存设备会话密钥信息
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：301
	 * @param deviceKeyCache	需要保存的设备会话密钥信息
	 * @return
	 *		Result(0,保存设备会话密钥信息到缓存成功.);;
	 *		Result(1,保存设备会话密钥信息到缓存失败[设备会话密钥信息为空]!);;
	 *		Result(2,保存设备会话密钥信息到缓存失败[设备会话密钥信息sessionId为空]!);;
	 */
	public Result saveDeviceKey(String maxCode,String minCode, DeviceKeyCache deviceKeyCache);
	
	
	/**
	 * 更新设备会话密钥信息
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：302
	 * @param deviceKeyCache	需要更新的设备会话密钥信息
	 * @return
	 *		Result(0,更新设备会话密钥信息到缓存成功.);;
	 *		Result(1,更新设备会话密钥信息到缓存失败[设备会话密钥信息为空]!);;
	 *		Result(2,更新设备会话密钥信息到缓存失败[设备会话密钥信息sessionId为空]!);;
	 */
	public Result updateDeviceKey(String maxCode,String minCode, DeviceKeyCache deviceKeyCache);
	
	
	/**
	 * 更新设备会话密钥信息，更新指定属性
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：303
	 * @param sessionId			需要更新的设备会话sessionId
	 * @param fieldValueMap		需要更新的设备会话指定属性Key和Value值
	 * @return
	 *		Result(0,更新设备会话密钥信息到缓存成功.);;
	 *		Result(1,更新设备会话密钥信息到缓存失败[设备会话密钥信息为空]!);;
	 *		Result(2,更新设备会话密钥信息到缓存失败[设备会话密钥信息sessionId为空]!);;
	 */
	public Result updateDeviceKeyByField(String maxCode,String minCode,String sessionId,Map<String,String> fieldValueMap );
	
	
	/**
	 * 查询设备会话密钥信息
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：304
	 * @param deviceKeyCache	需要查询的设备会话密钥信息(至少sessionId不能为空);
	 * @return
	 *		public DeviceKeyCache		需要查询的设备会话密钥信息
	 */
	public DeviceKeyCache queryDeviceKey(String maxCode,String minCode, DeviceKeyCache deviceKeyCache);
	
	
	/**
	 * 查询设备会话密钥信息,根据会话sessionId
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：305
	 * @param sessionId			需要查询的设备会话sessionId
	 * @return
	 *		public DeviceKeyCache		需要查询的设备会话密钥信息
	 */
	public DeviceKeyCache queryDeviceKeyBySessionId(String maxCode,String minCode,String sessionId);
	
	
	/**
	 * 查询设备会话密钥信息,根据设备devOnlyId
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：306
	 * @param devOnlyId			需要查询的设备devOnlyId
	 * @return
	 *		public DeviceKeyCache		需要查询的设备会话密钥信息
	 */
	public DeviceKeyCache queryDeviceKeyByDevOnlyId(String maxCode,String minCode,String devOnlyId);
	
	
	/**
	 * 删除设备会话密钥信息
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：307
	 * @param deviceKeyCache	需要删除的设备会话密钥信息(至少sessionId不能为空);
	 * @return
	 *		Result(0,从缓存中删除设备会话密钥信息成功.);;
	 *		Result(1,从缓存中删除设备会话密钥信息失败[需要删除的设备信息输入值sessionId为空]!);;
	 */
	public Result deleteDeviceKey(String maxCode,String minCode, DeviceKeyCache deviceKeyCache);
	
	
	/**
	 * 删除设备会话密钥信息,根据会话sessionId
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：308
	 * @param sessionId			需要删除的设备会话sessionId
	 * @return
	 *		Result(0,从缓存中删除设备会话密钥信息成功.);;
	 *		Result(1,从缓存中删除设备会话密钥信息失败[需要删除的设备信息输入值sessionId为空]!);;
	 */
	public Result deleteDeviceKeyBySessionId(String maxCode,String minCode,String sessionId);
	
	
	/**
	 * 删除设备会话密钥信息,根据设备devOnlyId
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：309
	 * @param devOnlyId			需要删除的设备devOnlyId
	 * @return
	 *		Result(0,从缓存中删除设备会话密钥信息成功.);;
	 *		Result(1,从缓存中删除设备会话密钥信息失败[需要删除的设备信息输入值devOnlyId为空]!);;
	 */
	public Result deleteDeviceKeyByDevOnlyId(String maxCode,String minCode,String devOnlyId);

	
	/**
	 * 验证sessionId 在DeviceKeyCache中是否存在
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：310
	 * @param sessionId			需要验证的sessionId
	 * @return
	 *		Result(0,存在.);
	 *		Result(1,不存在.);
	 *		Result(2,验证信息参数不正确[sessionId为空]!);
	 */
	public Result isExistSessionIdInDeviceKeyCache(String maxCode,
			String minCode, String sessionId);
	
	
	
}
 