package com.vrv.cems.service.cache.service.impl; 

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;










import com.vrv.cems.service.base.bean.cache.DeviceOnlineCache;
import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.base.interfaces.ICacheService;
import com.vrv.cems.service.cache.bean.DeviceOnlineBean;
import com.vrv.cems.service.cache.bean.DeviceOnlineBean;
import com.vrv.cems.service.cache.core.Services;
import com.vrv.cems.service.cache.service.DeviceOnlineService;
import com.vrv.cems.service.cache.util.RedisUtil;
import com.vrv.cems.service.cache.util.ReflectUtils;

/** 
 *   <B>说       明</B>:设备在线Service实现类(默认)
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午9:43:25 
 */
public class DeviceOnlineServiceImpl implements DeviceOnlineService {
	@Override
	public Result saveDeviceOnline(String maxCode, String minCode,
			DeviceOnlineCache deviceOnlineCache) {
		Result result = new Result();
				if(!minCode.equals(ICacheService.SAVEDEVICEONLINE)){
					log.info("保存设备在线信息参数不正确[功能号minCode不正确]!");
					result.setCode(4);
					result.setInfo("保存设备在线信息参数不正确[功能号minCode不正确]!");

				}
				else if (deviceOnlineCache == null) {
					log.info("保存设备在线信息到缓存失败[设备在线信息为空]!");
					result.setCode(1);
					result.setInfo("保存设备在线信息到缓存失败[设备在线信息为空]!");
				} else if (StringUtils.isBlank(deviceOnlineCache.getDevOnlyId())) {
					log.info("保存设备在线信息到缓存失败[设备devOnlyId为空]!");
					result.setCode(2);
					result.setInfo("保存设备在线信息到缓存失败[设备devOnlyId为空]!");
				} else {
					// 后期考虑反射实现....
					// 新建一个实体类
					DeviceOnlineBean deviceOnlineBean = new DeviceOnlineBean();

					try {
						// 把cache类里的数据传给实体类
						BeanUtils.copyProperties(deviceOnlineBean, deviceOnlineCache);
						// 用反射得到实体类的属性值
						Map<String, String> fieldValueMap = ReflectUtils.setObject2Map(
								deviceOnlineBean, DeviceOnlineBean.class);
						// 把属性字段对应的值存入到Redis中
						for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
							try{
							RedisUtil.getInstance().hset(
									ICacheService.PREFIX_DEVICEONLINECACHE
											+ deviceOnlineCache.getDevOnlyId(),
									entry.getKey(), entry.getValue());
							}
							catch(Exception e)
							{ 
								
								log.info("缓存saveDeviceOnline异常!"+e.getMessage());
								result.setCode(3);
								result.setInfo("缓存saveDeviceOnline异常![设备devOnlyId"+deviceOnlineCache.getDevOnlyId()+"异常]!");
								return result;
							}
						}
						log.info("保存设备在线信息到缓存成功!");
						result.setCode(0);
						result.setInfo("保存设备在线信息到缓存成功!");
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
	public Result updateDeviceOnline(String maxCode, String minCode,
			DeviceOnlineCache deviceOnlineCache) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.UPDATEDEVICEONLINE)){
			log.info("更新设备在线信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("更新设备在线信息参数不正确[功能号minCode不正确]!");

		}
		else if (deviceOnlineCache == null) {
			log.info("更新设备在线信息到缓存失败[设备在线信息为空]!");
			result.setCode(1);
			result.setInfo("更新设备在线信息到缓存失败[设备在线信息为空]!");
		} else if (StringUtils.isBlank(deviceOnlineCache.getDevOnlyId())) {
			log.info("保存设备在线信息到缓存失败[设备devOnlyId为空]!");
			result.setCode(2);
			result.setInfo("更新设备在线信息到缓存失败[设备devOnlyId为空]!");
		} else {
			// 新建一个实体类
			DeviceOnlineBean deviceOnlineBean = new DeviceOnlineBean();

			try {
				// 把cache类里的数据传给实体类
				BeanUtils.copyProperties(deviceOnlineBean, deviceOnlineCache);
				// 用反射得到实体类的属性值
				Map<String, String> fieldValueMap = ReflectUtils.setObject2Map(
						deviceOnlineBean, DeviceOnlineBean.class);
				// 把属性字段对应的值存入到Redis中
				for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
					try{
					RedisUtil.getInstance().hset(
							ICacheService.PREFIX_DEVICEONLINECACHE
									+ deviceOnlineCache.getDevOnlyId(),
							entry.getKey(), entry.getValue());
					}
					catch(Exception e)
					{ 
						
						log.info("缓存updateDeviceOnline异常!"+e.getMessage());
						result.setCode(3);
						result.setInfo("缓存updateDeviceOnline异常![设备devOnlyId"+deviceOnlineCache.getDevOnlyId()+"异常]!");
						return result;
					}
				}
				log.info("更新设备在线信息到缓存成功!");
				result.setCode(0);
				result.setInfo("更新设备在线信息到缓存成功!");
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
	public Result updateDeviceOnlineByField(String maxCode, String minCode,
			String devOnlyId, Map<String, String> fieldValueMap) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.UPDATEDEVICEONLINEBYFIELD)){
			log.info("根据map更新设备在线信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("根据map更新设备在线信息参数不正确[功能号minCode不正确]!");

		}
		else if (fieldValueMap == null && fieldValueMap.size() < 1) {
			log.info("更新设备在线信息到缓存失败[需要更新的属性名及属性值为空]!");
			result.setCode(1);
			result.setInfo("更新设备在线信息到缓存失败[需要更新的属性名及属性值为空]!");

		} else if (StringUtils.isBlank(devOnlyId)) {
			log.info("更新设备在线信息到缓存失败[设备devOnlyId为空]!");
			result.setCode(2);
			result.setInfo("更新设备在线信息到缓存失败[设备devOnlyId为空]!");
		} else {
			for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
				try{
				RedisUtil.getInstance().hset(
						ICacheService.PREFIX_DEVICEONLINECACHE + devOnlyId,
						entry.getKey(), entry.getValue());
				}
				catch(Exception e)
				{ 
					
					log.info("缓存updateDeviceOnlineByField异常!"+e.getMessage());
					result.setCode(3);
					result.setInfo("缓存updateDeviceOnlineByField异常![设备devOnlyId"+devOnlyId+"异常]!");
					return result;
				}
			}
			log.info("更新设备在线信息到缓存成功!");
			result.setCode(0);
			result.setInfo("更新设备在线信息到缓存成功!");
		}
		return result;
	}

	@Override
	public DeviceOnlineCache queryDeviceOnline(String maxCode, String minCode,
			DeviceOnlineCache deviceOnlineCache) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.QUERYDEVICEONLINE)){
			log.info("查询设备在线信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("查询设备在线信息参数不正确[功能号minCode不正确]!");

		}
		else if (deviceOnlineCache == null) {
			log.info("查询设备在线信息到缓存失败[设备在线信息为空]!");
			result.setCode(1);
			result.setInfo("查询设备在线信息到缓存失败[设备在线信息为空]!");
		} else if (StringUtils.isBlank(deviceOnlineCache.getDevOnlyId())) {
			log.info("查询设备在线信息到缓存失败[设备devOnlyId为空]!");
			result.setCode(2);
			result.setInfo("查询设备在线信息到缓存失败[设备devOnlyId为空]!");
		} else {
			Map<String, String> fieldValueMap = new HashMap<String, String>();
			// 从缓存中取出的所有属性值给map
			try{
			fieldValueMap = RedisUtil.getInstance().hgetAll(
					ICacheService.PREFIX_DEVICEONLINECACHE
							+ deviceOnlineCache.getDevOnlyId());
			}
			catch(Exception e)
			{
				log.info("查询设备在线信息到缓存失败["+e.getMessage()+"]");
				result.setCode(3);
				result.setInfo("查询设备在线信息到缓存失败["+e.getMessage()+"]");
				deviceOnlineCache.clear();
				return deviceOnlineCache;
			}
			if (fieldValueMap == null || fieldValueMap.size() < 1) {
				log.info("从缓存中查询设备在线信息失败[输入 devOnlyId="
						+ deviceOnlineCache.getDevOnlyId() + " 对应的值在缓存中未找到]!");
				deviceOnlineCache.clear();
				return deviceOnlineCache;
			} else {
				// 将查询出的Hash值依次对应让如到deviceOnlineCache对象中，并返回
				// 考虑可以写工具类
				deviceOnlineCache = ReflectUtils.setMap2Ojbect(fieldValueMap,
						deviceOnlineCache, DeviceOnlineCache.class);
				log.info("从缓存中查询设备在线信息成功[输入 devOnlyId="
						+ deviceOnlineCache.getDevOnlyId() + " 正确]");
				result.setCode(0);
				result.setInfo("从缓存中查询设备在线信息成功!");
			
				return deviceOnlineCache;
			}
		}
		deviceOnlineCache.clear();
		return deviceOnlineCache;
	}

	@Override
	public DeviceOnlineCache queryDeviceOnlineByDevOnlyId(String maxCode,
			String minCode, String devOnlyId) {
		Result result = new Result();
		List<DeviceOnlineCache> deviceOnlineCacheList=new ArrayList<DeviceOnlineCache>();
		DeviceOnlineCache deviceOnlineCache = new DeviceOnlineCache();
		if(!minCode.equals(ICacheService.QUERYDEVICEONLINEBYDEVONLYID)){
			log.info("根据devOnlyId设备在线信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("根据devOnlyId设备在线信息参数不正确[功能号minCode不正确]!");

		}
		else if (StringUtils.isBlank(devOnlyId)) {
			log.info("从缓存中查询设备在线信息失败[输入 devOnlyId=" + devOnlyId + " 为空]!");
			result.setCode(2);
			result.setInfo("从缓存中查询设备在线信息失败[设备devOnlyId为空]!");
			return null;
		} else if (!StringUtils.isBlank(devOnlyId)) {
			Map<String, String> fieldValueMap = new HashMap<String, String>();
			// 从缓存中取出的所有属性值给map
			try{
			fieldValueMap = RedisUtil.getInstance().hgetAll(
					ICacheService.PREFIX_DEVICEONLINECACHE + devOnlyId);
			}
			 catch(Exception e)
			 {
				 
				 log.info("从缓存中查询设备在线信息失败[输入 devOnlyId=" + devOnlyId
							+ " 对应的值在缓存中未找到]!"+e.getLocalizedMessage());
				    result.setCode(3);
					result.setInfo("从缓存中查询设备在线信息失败[输入 devOnlyId=" + devOnlyId
							+ " 对应的值在缓存中未找到]!");
					deviceOnlineCache.clear();
					return deviceOnlineCache;
			 }
			if (fieldValueMap == null && fieldValueMap.size() < 1) {
				log.info("从缓存中查询设备在线信息失败[输入 devOnlyId=" + devOnlyId
						+ " 对应的值在缓存中未找到]!");
				deviceOnlineCache.clear();
				return deviceOnlineCache;
			} else {
				
				// 将查询出的Hash值依次对应让如到deviceOnlineCache对象中，并返回
				// 考虑可以写工具类
				deviceOnlineCache = ReflectUtils.setMap2Ojbect(fieldValueMap,
						deviceOnlineCache, DeviceOnlineCache.class);
				log.info("从缓存中查询设备在线信息成功[输入 devOnlyId="
						+ deviceOnlineCache.getDevOnlyId() + " 正确]");
				result.setCode(0);
				result.setInfo("从缓存中查询设备在线信息成功!");
				
				return deviceOnlineCache;
			}
		}
		deviceOnlineCache.clear();
		return deviceOnlineCache;
	}

	@Override
	public DeviceOnlineCache queryDeviceOnlineByIp(String maxCode,
			String minCode, String ip) {
		Result result = new Result();		
		DeviceOnlineCache deviceOnlineCache = new DeviceOnlineCache();
		if(!minCode.equals(ICacheService.QUERYDEVICEONLINEBYIP)){
			log.info("根据IP设备在线信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("设备在线信息参数不正确[功能号minCode不正确]!");
		}
		else if (StringUtils.isBlank(ip)) {
			log.info("从缓存中查询设备在线信息失败[输入 ip=" + ip + " 为空]!");
			result.setCode(1);
			result.setInfo("保存设备在线信息到缓存失败[设备在线信息为空]!");
			
		} else if (!StringUtils.isBlank(ip)) {
			Map<String, String> fieldValueMap = new HashMap<String, String>();
			// 从ip2DevOnlyIdService中查询到devOnlyId
			String devOnlyId = Services.ip2DevOnlyIdService.queryIP2DevOnlyId(
					ICacheService.SERVICE_CODE, ICacheService.QUERYIP2DEVONLYID, ip);
			try{
			fieldValueMap = RedisUtil.getInstance().hgetAll(
					ICacheService.PREFIX_DEVICEONLINECACHE + devOnlyId);
			}
			catch(Exception e)
			{
				log.info("查询设备在线信息到缓存失败["+e.getMessage()+"]");
				result.setCode(3);
				result.setInfo("查询设备在线信息到缓存失败["+e.getMessage()+"]");
				deviceOnlineCache.clear();
				return deviceOnlineCache;
			}
			if (fieldValueMap == null || fieldValueMap.size() < 1) {
				log.info("从缓存中查询设备在线信息失败[输入 devOnlyId=" + devOnlyId
						+ " 对应的值在缓存中未找到]!");
				deviceOnlineCache.clear();
				return deviceOnlineCache;
			} else {
				
				// 将查询出的Hash值依次对应让如到deviceOnlineCache对象中，并返回
				// 考虑可以写工具类
				deviceOnlineCache = ReflectUtils.setMap2Ojbect(fieldValueMap,
						deviceOnlineCache, DeviceOnlineCache.class);
				log.info("从缓存中查询设备在线信息成功[输入 devOnlyId="
						+ deviceOnlineCache.getDevOnlyId() + " 正确]");
				result.setCode(0);
				result.setInfo("从缓存中查询设备在线信息成功!");
				return deviceOnlineCache;
			}
		}
		deviceOnlineCache.clear();
		return deviceOnlineCache;
	}

	@Override
	public Result deleteDeviceOnline(String maxCode, String minCode,
			DeviceOnlineCache deviceOnlineCache) {
		Result result = new Result();		
		Long tempResult;
			if(!minCode.equals(ICacheService.DELETEDEVICEONLIE)){
				log.info("删除设备在线信息参数不正确[功能号minCode不正确]!");
				result.setCode(4);
				result.setInfo("删除设备在线信息参数不正确[功能号minCode不正确]!");

			}
			else if (deviceOnlineCache == null) {
					log.info("从缓存中删除设备在线信息失败[需要删除的设备在线信息输入值为空]!]!");
					result.setCode(1);
					result.setInfo("从缓存中删除设备在线信息失败[需要删除的设备在线信息输入值为空]!");
				}else if (StringUtils.isBlank(deviceOnlineCache.getDevOnlyId())) {
					log.info("从缓存中删除设备在线信息失败[需要删除的设备在线信息输入值devOnlyId 为空]!");
					result.setCode(2);
					result.setInfo("从缓存中删除设备在线信息失败[需要删除的设备在线信息输入值devOnlyId为空]!");

				} else {
					try{
					 tempResult = RedisUtil.getInstance().delete(
							ICacheService.PREFIX_DEVICEONLINECACHE
									+ deviceOnlineCache.getDevOnlyId());
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
						result.setInfo("从缓存中删除设备在线信息成功!");
					}
					log.info("从缓存中删除设备在线信息返回为" + tempResult);
				}
				return result;
	}

	@Override
	public Result deleteDeviceOnlineByDevOnlyId(String maxCode, String minCode,
			String devOnlyId) {
		Result result = new Result();
		Long tempResult;
		if(!minCode.equals(ICacheService.DELETEDEVICEONLIEBYDEVONLYID)){
			log.info("根据devOnlyId删除设备在线信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("根据devOnlyId删除设备在线信息参数不正确[功能号minCode不正确]!");
		}
		else if (StringUtils.isBlank(devOnlyId)) {
			log.info("从缓存中删除设备在线信息失败[需要删除的设备在线信息输入值devOnlyId为空]!");
			result.setCode(1);
			result.setInfo("从缓存中删除设备在线信息失败[需要删除的设备在线信息输入值devOnlyId为空]!");

		} else {
			try{
				tempResult= RedisUtil.getInstance().delete(
					ICacheService.PREFIX_DEVICEONLINECACHE + devOnlyId);
			}
			catch(Exception e)
			{
				log.info("从缓存中删除设备在线信息失败!"+e.getMessage());
				result.setCode(3);
				result.setInfo("从缓存中删除设备在线信息失败!"+e.getMessage());
				tempResult=-1l;
				return result;
			}
			 if (tempResult>=0) {
				result.setCode(0);
				result.setInfo("从缓存中删除设备在线信息成功");
			}

			log.info("删除返回为" + tempResult);
		}
		return result;
	}
	
	@Override
	public Result deleteDeviceOnlineByIp(String maxCode, String minCode,
			String ip) {
		Result result = new Result();
		Long tempResult;
		if(!minCode.equals(ICacheService.DELETEDEVICEONLIEBYIP)){
			log.info("根据ip删除设备在线信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("根据ip删除设备在线信息参数不正确[功能号minCode不正确]!");

		}
		else if (StringUtils.isBlank(ip)) {
			log.info("从缓存中删除设备在线信息失败[输入 ip=" + ip + " 为空]!");
			result.setCode(1);
			result.setInfo("从缓存中删除设备在线信息失败[需要删除的设备在线信息输入值ip为空]!");
		} else {
			Map<String, String> fieldValueMap = new HashMap<String, String>();
			// 从ip2DevOnlyIdService中查询到devOnlyId
			String devOnlyId = Services.ip2DevOnlyIdService.queryIP2DevOnlyId(
					ICacheService.SERVICE_CODE, ICacheService.QUERYIP2DEVONLYID, ip);
			try{
			 tempResult = RedisUtil.getInstance().delete(
					ICacheService.PREFIX_DEVICEONLINECACHE + devOnlyId);
			}
			catch(Exception e)
			{
				log.info("从缓存中删除设备在线信息失败!"+e.getMessage());
				result.setCode(3);
				result.setInfo("从缓存中删除设备在线信息失败!"+e.getMessage());
				tempResult=-1l;
				return result;
			}
			 if (tempResult >= 0) {
				result.setCode(0);
				result.setInfo("删除设备在线信息成功!");
			}
			log.info("从缓存中删除设备在线信息返回为" + tempResult);
		}
		return result;
	}

}
 