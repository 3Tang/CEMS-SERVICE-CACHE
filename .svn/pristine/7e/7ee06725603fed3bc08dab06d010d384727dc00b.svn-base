package com.vrv.cems.service.cache.service.impl; 

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.vrv.cems.service.base.bean.cache.DevicePolicyCache;
import com.vrv.cems.service.base.bean.cache.DevicePolicyCache;
import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.base.interfaces.ICacheService;
import com.vrv.cems.service.cache.bean.DevicePolicyBean;
import com.vrv.cems.service.cache.core.Services;
import com.vrv.cems.service.cache.service.DevicePolicyService;
import com.vrv.cems.service.cache.util.RedisUtil;
import com.vrv.cems.service.cache.util.ReflectUtils;

/** 
 *   <B>说       明</B>:设备执行策略信息Service实现类(默认)
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午9:51:37 
 */
public class DevicePolicyServiceImpl implements DevicePolicyService {

	@Override
	public Result saveDevicePolicy(String maxCode, String minCode,
			DevicePolicyCache devicePolicyCache) {
		Result result = new Result();
			if(!minCode.equals(ICacheService.SAVEDEVICEPOLICY)){
					log.info("保存设备执行策略信息参数不正确[功能号minCode不正确]!");
					result.setCode(4);
					result.setInfo("保存设备执行策略信息参数不正确[功能号minCode不正确]!");

			}
			else	if (devicePolicyCache == null) {
					log.info("保存设备执行策略信息到缓存失败[设备执行策略信息为空]!");
					result.setCode(1);
					result.setInfo("保存设备执行策略信息到缓存失败[设备执行策略信息为空]!");
				} else if (StringUtils.isBlank(devicePolicyCache.getDevOnlyId())) {
					log.info("保存设备执行策略信息到缓存失败[设备策略devOnlyId为空]!");
					result.setCode(2);
					result.setInfo("保存设备执行策略信息到缓存失败[设备策略devOnlyId为空]!");
				} else {
					// 后期考虑反射实现....
					// 新建一个实体类
					DevicePolicyBean devicePolicyBean = new DevicePolicyBean();

					try {
						// 把cache类里的数据传给实体类
						BeanUtils.copyProperties(devicePolicyBean, devicePolicyCache);
						// 用反射得到实体类的属性值
						Map<String, String> fieldValueMap = ReflectUtils.setObject2Map(
								devicePolicyBean, DevicePolicyBean.class);
						// 把属性字段对应的值存入到Redis中
						for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
							try{
							RedisUtil.getInstance().hset(
									ICacheService.PREFIX_DEVICEPOLICYCACHE
											+ devicePolicyCache.getDevOnlyId(),
									entry.getKey(), entry.getValue());
							}
							catch(Exception e)
							{ 
								
								log.info("缓存saveDevicePolicy异常!"+e.getMessage());
								result.setCode(3);
								result.setInfo("缓存saveDevicePolicy异常![设备devOnlyId"+devicePolicyCache.getDevOnlyId()+"异常]!");
								return result;
							}
						}
						log.info("保存设备执行策略信息到缓存成功!");
						result.setCode(0);
						result.setInfo("保存设备执行策略信息到缓存成功!");
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
	public Result updateDevicePolicy(String maxCode, String minCode,
			DevicePolicyCache devicePolicyCache) {
		Result result = new Result();
				if(!minCode.equals(ICacheService.UPDATEDEVICEPOLICY)){
					log.info("更新设备执行策略信息参数不正确[功能号minCode不正确]!");
					result.setCode(4);
					result.setInfo("更新设备执行策略信息参数不正确[功能号minCode不正确]!");

				}
				else if (devicePolicyCache == null) {
					log.info("更新设备执行策略信息到缓存失败[设备执行策略信息为空]!");
					result.setCode(1);
					result.setInfo("更新设备执行策略信息到缓存失败[设备执行策略信息为空]!");
				} else if (StringUtils.isBlank(devicePolicyCache.getDevOnlyId())) {
					log.info("保存设备执行策略信息到缓存失败[设备策略devOnlyId为空]!");
					result.setCode(2);
					result.setInfo("更新设备执行策略信息到缓存失败[设备策略devOnlyId为空]!");
				} else {
					// 新建一个实体类
					DevicePolicyBean devicePolicyBean = new DevicePolicyBean();
					try {
						// 把cache类里的数据传给实体类
						BeanUtils.copyProperties(devicePolicyBean, devicePolicyCache);
						// 用反射得到实体类的属性值
						Map<String, String> fieldValueMap = ReflectUtils.setObject2Map(
								devicePolicyBean, DevicePolicyBean.class);
						// 把属性字段对应的值存入到Redis中
						for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
							try{
							RedisUtil.getInstance().hset(
									ICacheService.PREFIX_DEVICEPOLICYCACHE
											+ devicePolicyCache.getDevOnlyId(),
									entry.getKey(), entry.getValue());
							}
							catch(Exception e)
							{ 
								
								log.info("缓存updateDevicePolicy异常!"+e.getMessage());
								result.setCode(3);
								result.setInfo("缓存updateDevicePolicy异常![设备devOnlyId"+devicePolicyCache.getDevOnlyId()+"异常]!");
								return result;
							}
						}
						log.info("更新设备执行策略信息到缓存成功!");
						result.setCode(0);
						result.setInfo("更新设备执行策略信息到缓存成功!");
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
	public DevicePolicyCache queryDevicePolicyByDevOnlyId(String maxCode,
			String minCode, String devOnlyId) {
		Result result = new Result();
		List<DevicePolicyCache> devicePolicyCacheList=new ArrayList<DevicePolicyCache>();
		DevicePolicyCache devicePolicyCache=new DevicePolicyCache();
		boolean isExists=false;
		if(!minCode.equals(ICacheService.QUERYDEVICEPOLICYBYDEVONLYID)){
			log.info("查询设备执行策略信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("查询设备执行策略信息参数不正确[功能号minCode不正确]!");

		}
		else if (StringUtils.isBlank(devOnlyId)) {
			log.info("从缓存中查询设备执行策略信息失败[输入 devOnlyId=" + devOnlyId + " 为空]!");
			result.setCode(2);
			result.setInfo("从缓存中查询设备执行策略信息失败[设备策略devOnlyId为空]!");
			
		} else if (!StringUtils.isBlank(devOnlyId)) {
			Map<String, String> fieldValueMap = new HashMap<String, String>();
			// 从缓存中取出的所有属性值给map			
			try{
			fieldValueMap = RedisUtil.getInstance().hgetAll(
					ICacheService.PREFIX_DEVICEPOLICYCACHE + devOnlyId);
			}
			catch(Exception e)
			 {
				 log.info("从缓存中查询设备执行策略信息失败[输入 devOnlyId=" + devOnlyId
							+ " 对应的值在缓存中未找到]!"+e.getLocalizedMessage());
				    result.setCode(3);
					result.setInfo("从缓存中查询设备执行策略信息失败[输入 devOnlyId=" + devOnlyId
							+ " 对应的值在缓存中未找到]!");
					devicePolicyCache.clear();
					return devicePolicyCache;
			 }
			
			if (fieldValueMap == null || fieldValueMap.size() < 1) {
				log.info("从缓存中查询设备执行策略信息失败[输入 devOnlyId=" + devOnlyId
						+ " 对应的值在缓存中未找到]!");
				devicePolicyCache.clear();
				return devicePolicyCache;
			} else {
				 devicePolicyCache = new DevicePolicyCache();
				// 将查询出的Hash值依次对应让如到DevicePolicyCache对象中，并返回
				// 考虑可以写工具类
				devicePolicyCache = ReflectUtils.setMap2Ojbect(fieldValueMap,
						devicePolicyCache, DevicePolicyCache.class);
				log.info("从缓存中查询查询设备执行策略信息成功[输入 devOnlyId="
						+ devOnlyId + " 正确]");
				result.setCode(0);
				result.setInfo("从缓存中查询设备执行策略信息成功!");
				return devicePolicyCache;
			}
		}
		devicePolicyCache.clear();
		return devicePolicyCache;
	}

	@Override
	public DevicePolicyCache queryDevicePolicyByIp(String maxCode,
			String minCode, String ip) {
		Result result = new Result();
		DevicePolicyCache devicePolicyCache = new DevicePolicyCache();
		if(!minCode.equals(ICacheService.QUERYDEVICEPOLICYBYIP)){
			log.info("查询设备执行策略信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("查询设备执行策略信息参数不正确[功能号minCode不正确]!");

		}
		else if (StringUtils.isBlank(ip)) {
			log.info("从缓存中查询设备执行策略信息失败[输入 ip=" + ip + " 为空]!");
			result.setCode(1);
			result.setInfo("保存设备执行策略信息到缓存失败[设备执行策略信息为空]!");
			return null;
		} else if (!StringUtils.isBlank(ip)) {
			Map<String, String> fieldValueMap = new HashMap<String, String>();
			// 从ip2DevOnlyIdService中查询到devOnlyId
			String devOnlyId = Services.ip2DevOnlyIdService.queryIP2DevOnlyId(
					ICacheService.SERVICE_CODE, ICacheService.QUERYIP2DEVONLYID, ip);
			try{
			fieldValueMap = RedisUtil.getInstance().hgetAll(
					ICacheService.PREFIX_DEVICEPOLICYCACHE + devOnlyId);
			}
			catch(Exception e)
			{
				log.info("查询设备执行策略信息到缓存失败["+e.getMessage()+"]");
				result.setCode(3);
				result.setInfo("查询设备执行策略信息到缓存失败["+e.getMessage()+"]");
				return null;
			}
			if (fieldValueMap == null || fieldValueMap.size() < 1) {
				log.info("从缓存中查询设备执行策略信息失败[输入 devOnlyId=" + devOnlyId
						+ " 对应的值在缓存中未找到]!");
				return null;
			} else {
				// 将查询出的Hash值依次对应让如到DevicePolicyCache对象中，并返回
				// 考虑可以写工具类
				devicePolicyCache = ReflectUtils.setMap2Ojbect(fieldValueMap,
						devicePolicyCache, DevicePolicyCache.class);				
				log.info("从缓存中查询查询设备执行策略信息成功[输入 devOnlyId="
						+ devOnlyId + " 正确]");
				result.setCode(0);
				result.setInfo("从缓存中查询设备执行策略信息成功!");
				return devicePolicyCache;
			}
		}
		return null;
	}

	@Override
	public Result deleteDevicePolicyByDevOnlyId(String maxCode, String minCode,
			String devOnlyId) {
		Result result = new Result();
		Long tempResult ;
				if(!minCode.equals(ICacheService.DELETEDEVICEPOLICYBYDEVONLYID)){
					log.info("删除设备执行策略信息参数不正确[功能号minCode不正确]!");
					result.setCode(4);
					result.setInfo("删除设备执行策略信息参数不正确[功能号minCode不正确]!");

				}
				else	if (StringUtils.isBlank(devOnlyId)) {
					log.info("从缓存中删除设备执行策略信息失败[需要删除的设备执行策略信息输入值devOnlyId为空]!");
					result.setCode(1);
					result.setInfo("从缓存中删除设备执行策略信息失败[需要删除的设备执行策略信息输入值devOnlyId为空]!");

				} else {
					try{
						tempResult= RedisUtil.getInstance().delete(
							ICacheService.PREFIX_DEVICEPOLICYCACHE + devOnlyId);
					}
					catch(Exception e)
					{
						log.info("从缓存中删除设备执行策略信息失败["+e.getMessage()+"]!");
						result.setCode(3);
						result.setInfo("从缓存中删除设备执行策略信息失败["+e.getMessage()+"]!");
						tempResult=-1l;
						return result;
					}
					if (tempResult >= 0) {
						result.setCode(0);
						result.setInfo("从缓存中删除设备执行策略信息成功");
					}

					log.info("删除返回为" + tempResult);
				}
				return result;
	}

	@Override
	public Result deleteDevicePolicyByIp(String maxCode, String minCode,
			String ip) {
		Result result = new Result();
		Long tempResult = null;
		if(!minCode.equals(ICacheService.DELETEDEVICEPOLICYBYIP)){
			log.info("根据IP删除设备执行策略信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("根据IP删除设备执行策略信息参数不正确[功能号minCode不正确]!");

		}
		else if (StringUtils.isBlank(ip)) {
			log.info("从缓存中删除设备执行策略信息失败[输入 ip=" + ip + " 为空]!");
			result.setCode(1);
			result.setInfo("从缓存中删除设备执行策略信息失败[需要删除的设备执行策略信息输入值ip为空]!");
		} else {
			Map<String, String> fieldValueMap = new HashMap<String, String>();
			// 从ip2DevOnlyIdService中查询到devOnlyId
			String devOnlyId = Services.ip2DevOnlyIdService.queryIP2DevOnlyId(
					ICacheService.SERVICE_CODE, ICacheService.QUERYIP2DEVONLYID, ip);
			try{
			 tempResult = RedisUtil.getInstance().delete(
					ICacheService.PREFIX_DEVICEPOLICYCACHE + devOnlyId);
			}
			catch(Exception e)
			{
				log.info("从缓存中删除设备执行策略信息失败["+e.getMessage()+"]!");
				result.setCode(3);
				result.setInfo("从缓存中删除设备执行策略信息失败["+e.getMessage()+"]!");
				tempResult=-1l;
				return result;
			}
			if (tempResult >= 0) {
				result.setCode(0);
				result.setInfo("删除设备执行策略信息成功!");
			}
			log.info("从缓存中删除设备执行策略信息返回为" + tempResult);
		}
		return result;
	}

}
 