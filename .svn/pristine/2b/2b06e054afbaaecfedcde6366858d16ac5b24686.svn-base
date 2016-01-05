package com.vrv.cems.service.cache.service.impl; 

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.vrv.cems.service.base.bean.cache.DeviceKeyCache;
import com.vrv.cems.service.base.bean.cache.DeviceOnlineCache;
import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.base.interfaces.ICacheService;
import com.vrv.cems.service.cache.bean.DeviceKeyBean;
import com.vrv.cems.service.cache.bean.DeviceOnlineBean;
import com.vrv.cems.service.cache.core.Services;
import com.vrv.cems.service.cache.service.DeviceKeyService;
import com.vrv.cems.service.cache.util.RedisUtil;
import com.vrv.cems.service.cache.util.ReflectUtils;

/** 
 *   <B>说       明</B>:设备会话密钥Service实现类(默认)
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午9:45:58 
 */
public class DeviceKeyServiceImpl implements DeviceKeyService {
	@Override
	public Result saveDeviceKey(String maxCode, String minCode,
			DeviceKeyCache deviceKeyCache) {
		Result result = new Result();
		
		if(!minCode.equals(ICacheService.SAVEDEVICEKEY))
		{
			log.info("保存设备会话密钥信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("保存设备会话密钥信息参数不正确[功能号minCode不正确]!");
	
		}
		else if (deviceKeyCache == null) {
			log.info("保存设备会话密钥信息到缓存失败[设备会话密钥信息为空]!");
			result.setCode(1);
			result.setInfo("保存设备会话密钥信息到缓存失败[设备会话密钥信息为空]!");
		} else if (StringUtils.isBlank(deviceKeyCache.getSessionId())) {
			log.info("保存设备会话密钥信息到缓存失败[设备会话密钥信息sessionId为空]!");
			result.setCode(2);
			result.setInfo("保存设备会话密钥信息到缓存失败[设备会话密钥信息sessionId为空]!");
		} else {
			// 后期考虑反射实现....
			// 新建一个实体类
			DeviceKeyBean deviceKeyBean = new DeviceKeyBean();	
			try {
				// 把cache类里的数据传给实体类
				BeanUtils.copyProperties(deviceKeyBean, deviceKeyCache);
				// 用反射得到实体类的属性值
				Map<String, String> fieldValueMap = ReflectUtils.setObject2Map(
						deviceKeyBean, DeviceKeyBean.class);
				// 把属性字段对应的值存入到Redis中
				for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
					try{
					RedisUtil.getInstance().hset(
							ICacheService.PREFIX_DEVICESESSIONCACHE
									+ deviceKeyCache.getSessionId(),
							entry.getKey(), entry.getValue());
					
					
					}
					catch(Exception e)
					{ 
						
						log.info("缓存saveDeviceKey异常!"+e.getMessage());
						result.setCode(3);
						result.setInfo("缓存saveDeviceKey异常![设备sessionId"+deviceKeyCache.getSessionId()+"异常]!");
						return result;
					}
				}
				log.info("保存设备会话密钥信息到缓存成功!");
				result.setCode(0);
				result.setInfo("保存设备会话密钥信息到缓存成功!");	
			} catch (IllegalAccessException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public Result updateDeviceKey(String maxCode, String minCode,
			DeviceKeyCache deviceKeyCache) {
		Result result = new Result();
				if(!minCode.equals(ICacheService.UPDATEDEVICEKEY)){
					 log.info("更新设备会话密钥信息参数不正确[功能号minCode不正确]!");
					 result.setCode(4);
					 result.setInfo("设备会话密钥信息参数不正确[功能号minCode不正确]!");
	
					}else if (deviceKeyCache == null) {
					log.info("更新设备会话密钥信息到缓存失败[设备会话密钥信息为空]!");
					result.setCode(1);
					result.setInfo("更新设备会话密钥信息到缓存失败[设备会话密钥信息为空]!");
				} else if (StringUtils.isBlank(deviceKeyCache.getSessionId())) {
					log.info("保存设备会话密钥信息到缓存失败[设备sessionId为空]!");
					result.setCode(2);
					result.setInfo("更新设备会话密钥信息到缓存失败[设备sessionId为空]!");
				} else {
					// 新建一个实体类
					DeviceKeyBean deviceKeyBean = new DeviceKeyBean();

					try {
						// 把cache类里的数据传给实体类
						BeanUtils.copyProperties(deviceKeyBean, deviceKeyCache);
						// 用反射得到实体类的属性值
						Map<String, String> fieldValueMap = ReflectUtils.setObject2Map(
								deviceKeyBean, DeviceKeyBean.class);
						// 把属性字段对应的值存入到Redis中
						for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
							try{
							RedisUtil.getInstance().hset(
									ICacheService.PREFIX_DEVICESESSIONCACHE
											+ deviceKeyCache.getSessionId(),
									entry.getKey(), entry.getValue());
							}
							catch(Exception e)
							{ 
								
								log.info("缓存updateDeviceKey异常!"+e.getMessage());
								result.setCode(3);
								result.setInfo("缓存updateDeviceKey异常![设备sessionId"+deviceKeyCache.getSessionId()+"异常]!");
								return result;
							}
						}
						log.info("更新设备会话密钥信息到缓存成功!");
						result.setCode(0);
						result.setInfo("更新设备会话密钥信息到缓存成功!");
					} catch (IllegalAccessException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				return result;
	}

	@Override
	public Result updateDeviceKeyByField(String maxCode, String minCode,
			String sessionId, Map<String, String> fieldValueMap) {
		Result result = new Result();
				if(!minCode.equals(ICacheService.UPDATEDEVICEKEYBYFIELD)){
					log.info("根据map更新设备会话密钥信息参数不正确[功能号minCode不正确]!");
					result.setCode(4);
					result.setInfo("根据map更新设备会话密钥信息参数不正确[功能号minCode不正确]!");

				}
				else if (fieldValueMap == null && fieldValueMap.size() < 1) {
					log.info("更新设备会话密钥信息到缓存失败[需要更新的属性名及属性值为空]!");
					result.setCode(1);
					result.setInfo("更新设备会话密钥信息到缓存失败[需要更新的属性名及属性值为空]!");

				} else if (StringUtils.isBlank(sessionId)) {
					log.info("更新设备会话密钥信息到缓存失败[设备sessionId为空]!");
					result.setCode(2);
					result.setInfo("更新设备会话密钥信息到缓存失败[设备sessionId为空]!");
				} else {
					for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
						try{
						RedisUtil.getInstance().hset(
								ICacheService.PREFIX_DEVICESESSIONCACHE + sessionId,
								entry.getKey(), entry.getValue());
						}
						catch(Exception e)
						{ 
							
							log.info("缓存updateDeviceKey异常!"+e.getMessage());
							result.setCode(3);
							result.setInfo("缓存updateDeviceKey异常![设备sessionId"+sessionId+"异常]!");
							return result;
						}
					}
					log.info("更新设备会话密钥信息到缓存成功!");
					result.setCode(0);
					result.setInfo("更新设备会话密钥信息到缓存成功!");
				}
				return result;
	}

	@Override
	public DeviceKeyCache queryDeviceKey(String maxCode, String minCode,
			DeviceKeyCache deviceKeyCache) {
		Result result = new Result();
		// TODO 自动生成的方法存根
				List<DeviceKeyCache> deviceKeyCacheList=new ArrayList<DeviceKeyCache>();
				if(!minCode.equals(ICacheService.QUERYDEVICEKEY)){
					log.info("查询设备会话密钥信息参数不正确[功能号minCode不正确]!");
					result.setCode(4);
					result.setInfo("查询设备会话密钥信息参数不正确[功能号minCode不正确]!");

				}else if (deviceKeyCache == null) {
					log.info("查询设备会话密钥信息到缓存失败[设备会话密钥信息为空]!");
					result.setCode(1);
					result.setInfo("查询设备会话密钥信息到缓存失败[设备会话密钥信息为空]!");
				} else if (StringUtils.isBlank(deviceKeyCache.getSessionId())) {
					log.info("查询设备会话密钥信息到缓存失败[设备sessionId为空]!");
					result.setCode(2);
					result.setInfo("查询设备会话密钥信息到缓存失败[设备sessionId为空]!");
				} else {
					Map<String, String> fieldValueMap = new HashMap<String, String>();
					// 从缓存中取出的所有属性值给map
					try{
					fieldValueMap = RedisUtil.getInstance().hgetAll(
							ICacheService.PREFIX_DEVICESESSIONCACHE
									+ deviceKeyCache.getSessionId());
					}
					catch(Exception e)
					{
						log.info("查询设备会话密钥信息到缓存失败["+e.getMessage()+"]");
						result.setCode(3);
						result.setInfo("查询设备会话密钥信息到缓存失败["+e.getMessage()+"]");
						deviceKeyCache.clear();
						return deviceKeyCache;
					}
					if (fieldValueMap == null || fieldValueMap.size() < 1) {
						log.info("从缓存中查询设备会话密钥信息失败[输入 sessionId="
								+ deviceKeyCache.getSessionId() + " 对应的值在缓存中未找到]!");
						deviceKeyCache.clear();
						return deviceKeyCache;
					} else {
						// 将查询出的Hash值依次对应让如到deviceKeyCache对象中，并返回
						// 考虑可以写工具类
						deviceKeyCache = ReflectUtils.setMap2Ojbect(fieldValueMap,
								deviceKeyCache, DeviceKeyCache.class);
						
						log.info("从缓存中查询设备会话密钥信息成功[输入 sessionId="
								+ deviceKeyCache.getSessionId() + " 正确]");
						result.setCode(0);
						result.setInfo("从缓存中查询设备会话密钥信息成功!");
						return deviceKeyCache;
					}
				}
				deviceKeyCache.clear();
				return deviceKeyCache;
	}

	@Override
	public DeviceKeyCache queryDeviceKeyBySessionId(String maxCode,
			String minCode, String sessionId) {
		Result result = new Result();
		// TODO 自动生成的方法存根
		List<DeviceKeyCache> deviceKeyCacheList = new ArrayList<DeviceKeyCache>();
		DeviceKeyCache deviceKeyCache = new DeviceKeyCache();
			if(!minCode.equals(ICacheService.QUERYDEVICEKEYBYSESSIONID)){
				log.info("根据sessionId查询设备会话密钥信息参数不正确[功能号minCode不正确]!");
				result.setCode(4);
				result.setInfo("根据sessionId查询设备会话密钥信息参数不正确[功能号minCode不正确]!");

				}
			else if (StringUtils.isBlank(sessionId)) {
					log.info("从缓存中查询设备会话密钥信息失败[输入 sessionId=" + sessionId + " 为空]!");
					result.setCode(2);
					result.setInfo("从缓存中查询设备会话密钥信息失败[设备sessionId为空]!");
					deviceKeyCache.clear();
					return deviceKeyCache;
				} else if (!StringUtils.isBlank(sessionId)) {
					Map<String, String> fieldValueMap = new HashMap<String, String>();
					// 从缓存中取出的所有属性值给map
					try{
					fieldValueMap = RedisUtil.getInstance().hgetAll(
							ICacheService.PREFIX_DEVICESESSIONCACHE + sessionId);
					}
					catch(Exception e)
					{
						log.info("查询设备会话密钥信息到缓存失败["+e.getMessage()+"]");
						result.setCode(3);
						result.setInfo("查询设备会话密钥信息到缓存失败["+e.getMessage()+"]");
						deviceKeyCache.clear();
						return deviceKeyCache;
					}
					if (fieldValueMap == null || fieldValueMap.size() < 1) {
						log.info("从缓存中查询设备会话密钥信息失败[输入 sessionId=" + sessionId
								+ " 对应的值在缓存中未找到]!");
						deviceKeyCache.clear();
						return deviceKeyCache;
					} else {
						
						// 将查询出的Hash值依次对应让如到deviceKeyCache对象中，并返回
						// 考虑可以写工具类
						deviceKeyCache = ReflectUtils.setMap2Ojbect(fieldValueMap,
								deviceKeyCache, DeviceKeyCache.class);
						
						log.info("从缓存中查询设备会话密钥信息成功[输入 sessionId="
								+ deviceKeyCache.getSessionId() + " 正确]");
						result.setCode(0);
						result.setInfo("从缓存中查询设备会话密钥信息成功!");
						return deviceKeyCache;
					}
				}
				deviceKeyCache.clear();
				return deviceKeyCache;
	}

	@Override
	public DeviceKeyCache queryDeviceKeyByDevOnlyId(String maxCode,String minCode,String devOnlyId) {
		Result result = new Result();
		DeviceKeyCache deviceKeyCache = new DeviceKeyCache();
			if(!minCode.equals(ICacheService.QUERYDEVICEKEYBYDEVONLYID)){
				log.info("根据devOnlyId查询设备会话密钥信息参数不正确[功能号minCode不正确]!");
				result.setCode(4);
				result.setInfo("根据devOnlyId查询设备会话密钥信息参数不正确[功能号minCode不正确]!");

				}
			else 	if (StringUtils.isBlank(devOnlyId)) {
					log.info("从缓存中查询设备会话密钥信息失败[输入 devOnlyId=" + devOnlyId + " 为空]!");
					result.setCode(1);
					result.setInfo("保存设备会话密钥信息到缓存失败[设备会话密钥信息为空]!");
					deviceKeyCache.clear();
					return deviceKeyCache;
				} else if (!StringUtils.isBlank(devOnlyId)) {
					Map<String, String> fieldValueMap = new HashMap<String, String>();
					// 根据是设备在线用devOnlyId查sessinId,在用session查DeviceKeyCache
					DeviceOnlineCache deviceOnlineCache = Services.deviceOnlineService.queryDeviceOnlineByDevOnlyId(ICacheService.SERVICE_CODE, ICacheService.QUERYDEVICEONLINEBYDEVONLYID, devOnlyId);
					String sessionId=deviceOnlineCache.getSessionId();
					try{
					fieldValueMap = RedisUtil.getInstance().hgetAll(
							ICacheService.PREFIX_DEVICESESSIONCACHE + sessionId);
					}
					catch(Exception e)
					{
						log.info("查询设备会话密钥信息到缓存失败["+e.getMessage()+"]");
						result.setCode(3);
						result.setInfo("查询设备会话密钥信息到缓存失败["+e.getMessage()+"]");
						deviceKeyCache.clear();
						return deviceKeyCache;
					}
					if (fieldValueMap == null && fieldValueMap.size() < 1) {
						log.info("从缓存中查询设备会话密钥信息失败[输入 sessionId=" + sessionId
								+ " 对应的值在缓存中未找到]!");
						deviceKeyCache.clear();
						return deviceKeyCache;
					} else {
						
						// 将查询出的Hash值依次对应让如到deviceKeyCache对象中，并返回
						// 考虑可以写工具类
						deviceKeyCache = ReflectUtils.setMap2Ojbect(fieldValueMap,
								deviceKeyCache, DeviceKeyCache.class);
						log.info("从缓存中查询设备会话密钥信息成功[输入 sessionId="
								+ deviceKeyCache.getSessionId() + " 正确]");
						result.setCode(0);
						result.setInfo("从缓存中查询设备会话密钥信息成功!");
						return deviceKeyCache;
					}
				}
				deviceKeyCache.clear();
				return deviceKeyCache;
	}

	@Override
	public Result deleteDeviceKey(String maxCode, String minCode,
			DeviceKeyCache deviceKeyCache) {
		Result result = new Result();
		Long tempResult;
		if(!minCode.equals(ICacheService.DELETEDEVICEKEY)){
			log.info("删除设备会话密钥信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("删除设备会话密钥信息参数不正确[功能号minCode不正确]!");

			}
		else if (deviceKeyCache == null) {
			log.info("从缓存中删除设备会话密钥信息失败[需要删除的设备会话密钥信息输入值为空]!]!");
			result.setCode(1);
			result.setInfo("从缓存中删除设备会话密钥信息失败[需要删除的设备会话密钥信息输入值为空]!");
		} else if (StringUtils.isBlank(deviceKeyCache.getSessionId())) {
			log.info("从缓存中删除设备会话密钥信息失败[需要删除的设备会话密钥信息输入值sessionId 为空]!");
			result.setCode(2);
			result.setInfo("从缓存中删除设备会话密钥信息失败[需要删除的设备会话密钥信息输入值sessionId为空]!");

		} else {
			try{
			 tempResult = RedisUtil.getInstance().delete(
					ICacheService.PREFIX_DEVICESESSIONCACHE
							+ deviceKeyCache.getSessionId());
			}
			catch(Exception e)
			{
				log.info("从缓存中删除设备信息失败["+e.getMessage()+"]!");
				result.setCode(3);
				result.setInfo("从缓存中删除设备信息失败["+e.getMessage()+"]!");
				tempResult=-1l;
				return result;
			}
			if (tempResult >= 0) {
				result.setCode(0);
				result.setInfo("从缓存中删除设备会话密钥信息成功!");
			}
			log.info("从缓存中删除设备会话密钥信息返回为" + tempResult);
		}
		return result;
	}

	@Override
	public Result deleteDeviceKeyBySessionId(String maxCode, String minCode,
			String sessionId) {
		Result result = new Result();
				Long tempResult;
				if(!minCode.equals(ICacheService.DELETEDEVICEKEYBYSESSIONID)){
					log.info("删除设备会话密钥信息参数不正确[功能号minCode不正确]!");
					result.setCode(4);
					result.setInfo("删除设备会话密钥信息参数不正确[功能号minCode不正确]!");

					}
				else if (StringUtils.isBlank(sessionId)) {
					log.info("从缓存中删除设备会话密钥信息失败[输入 sessionId=" + sessionId + " 为空]!");
					result.setCode(1);
					result.setInfo("从缓存中删除设备会话密钥信息失败[需要删除的设备会话密钥信息输入值ip为空]!");
				} else {
					Map<String, String> fieldValueMap = new HashMap<String, String>();
					try{
					 tempResult = RedisUtil.getInstance().delete(
							ICacheService.PREFIX_DEVICESESSIONCACHE + sessionId);
					}
					catch(Exception e)
					{
						log.info("从缓存中删除设备信息失败["+e.getMessage()+"]!");
						result.setCode(3);
						result.setInfo("从缓存中删除设备信息失败["+e.getMessage()+"]!");
						tempResult=-1l;
						return result;
					}
					if (tempResult >= 0) {
						result.setCode(0);
						result.setInfo("删除设备会话密钥信息成功!");
					}
					log.info("从缓存中删除设备会话密钥信息返回为" + tempResult);
				}
				return result;
	}

	@Override
	public Result deleteDeviceKeyByDevOnlyId(String maxCode, String minCode,
			String devOnlyId) {
		Result result = new Result();
				Long tempResult ;
				if(!minCode.equals(ICacheService.DELETEDEVICEKEYBYDEVONLYID)){
					log.info("删除设备会话密钥信息参数不正确[功能号minCode不正确]!");
					result.setCode(4);
					result.setInfo("删除设备会话密钥信息参数不正确[功能号minCode不正确]!");

					}
				else if (StringUtils.isBlank(devOnlyId)) {
					log.info("从缓存中删除设备会话密钥信息失败[需要删除的设备会话密钥信息输入值devOnlyId为空]!");
					result.setCode(1);
					result.setInfo("从缓存中删除设备会话密钥信息失败[需要删除的设备会话密钥信息输入值devOnlyId为空]!");
				} else {
					// 根据是设备在线用devOnlyId查sessinId,在用session查DeviceKeyCache
					DeviceOnlineCache deviceOnlineCache = Services.deviceOnlineService.queryDeviceOnlineByDevOnlyId(ICacheService.SERVICE_CODE, ICacheService.QUERYDEVICEONLINEBYDEVONLYID, devOnlyId);
					String sessionId=deviceOnlineCache.getSessionId();
					try{
					 tempResult = RedisUtil.getInstance().delete(
							ICacheService.PREFIX_DEVICESESSIONCACHE + sessionId);
					}
					catch(Exception e)
					{
						log.info("从缓存中删除设备信息失败["+e.getMessage()+"]!");
						result.setCode(3);
						result.setInfo("从缓存中删除设备信息失败["+e.getMessage()+"]!");
						tempResult=-1l;
						return result;
					}

					if (tempResult >= 0) {
						result.setCode(0);
						result.setInfo("从缓存中删除设备会话密钥信息成功");
					}

					log.info("删除返回为" + tempResult);
				}
				return result;
	}

	@Override
	public Result isExistSessionIdInDeviceKeyCache(String maxCode,
			String minCode, String sessionId) {
		Result result = new Result();
	    boolean isExist=false;
	    if(!minCode.equals(ICacheService.ISEXISTSESSIONIDINDEVICEKEYCACHE)){
			log.info("验证设备会话密钥信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("验证设备会话密钥信息参数不正确[功能号minCode不正确]!");

			}
	    else if (StringUtils.isBlank(sessionId)) {
			log.info("验证设备会话密钥信息参数不正确[sessionId为空]!");
			result.setCode(2);
			result.setInfo("验证设备会话密钥信息参数不正确[sessionId为空]!");
		} else {
			
			//deviceKeyCache.
			isExist=RedisUtil.getInstance().exists(ICacheService.PREFIX_DEVICESESSIONCACHE+sessionId);
			
			if(isExist)
			{
				log.info("sessionId 在DeviceKeyCache中存在!");
				result.setCode(0);
				result.setInfo("sessionId 在DeviceKeyCache中存在!");
				
			}
			else
			{
				log.info("sessionId 在DeviceKeyCache中不存在!");
				result.setCode(1);
				result.setInfo("sessionId 在DeviceKeyCache中不存在!");
			}
		}
		
		return result;
	}

}
 