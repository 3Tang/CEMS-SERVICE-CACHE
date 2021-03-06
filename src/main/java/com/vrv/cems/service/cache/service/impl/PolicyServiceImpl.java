package com.vrv.cems.service.cache.service.impl; 

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.vrv.cems.service.base.bean.cache.DeviceCache;
import com.vrv.cems.service.base.bean.cache.PolicyCache;
import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.base.interfaces.ICacheService;
import com.vrv.cems.service.cache.bean.PolicyBean;
import com.vrv.cems.service.cache.bean.PolicyBean;
import com.vrv.cems.service.cache.service.PolicyService;
import com.vrv.cems.service.cache.util.RedisUtil;
import com.vrv.cems.service.cache.util.ReflectUtils;

/** 
 *   <B>说       明</B>:策略信息Service实现类(默认)
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午9:59:15 
 */
public class PolicyServiceImpl implements PolicyService {

	@Override
	public Result savePolicy(String maxCode, String minCode,
			PolicyCache policyCache) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.SAVEPOLICY)){
			log.info("保存策略信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("保存策略信息参数不正确[功能号minCode不正确]!");

		}else if (policyCache == null) {
					log.info("保存策略信息到缓存失败[策略信息为空]!");
					result.setCode(1);
					result.setInfo("保存策略信息到缓存失败[策略信息为空]!");
				} else if (StringUtils.isBlank(policyCache.getId())) {
					log.info("保存策略信息到缓存失败[策略policyId为空]!");
					result.setCode(2);
					result.setInfo("保存策略信息到缓存失败[策略policyId为空]!");
				} else {
					// 后期考虑反射实现....
					// 新建一个实体类
					PolicyBean policyBean = new PolicyBean();

					try {
						// 把cache类里的数据传给实体类
						ReflectUtils.copyObjectProperties(policyBean, policyCache, PolicyBean.class, PolicyCache.class);
						// 用反射得到实体类的属性值
						Map<String, String> fieldValueMap = ReflectUtils.setObject2Map(
								policyBean, PolicyBean.class);
						// 把属性字段对应的值存入到Redis中
						for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
							try{
							RedisUtil.getInstance().hset(
									ICacheService.PREFIX_POLICYCACHE
											+ policyCache.getId(),
									entry.getKey(), entry.getValue());
							}
							catch(Exception e)
							{ 
								
								log.info("缓存savePolicy异常!"+e.getMessage());
								result.setCode(3);
								result.setInfo("缓存savePolicy异常![策略信息policyId"+policyCache.getId()+"异常]!");
								return result;
							}
						}
						log.info("保存策略信息到缓存成功!");
						result.setCode(0);
						result.setInfo("保存策略信息到缓存成功!");
					} catch (IllegalAccessException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				return result;
	}

	@Override
	public Result updatePolicy(String maxCode, String minCode,
			PolicyCache policyCache) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.UPDATEPOLICY)){
			log.info("更新策略信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("更新策略信息参数不正确[功能号minCode不正确]!");

		}else if (policyCache == null) {
					log.info("更新策略信息到缓存失败[策略信息为空]!");
					result.setCode(1);
					result.setInfo("更新策略信息到缓存失败[策略信息为空]!");
				} else if (StringUtils.isBlank(policyCache.getId())) {
					log.info("保存策略信息到缓存失败[策略policyId为空]!");
					result.setCode(2);
					result.setInfo("更新策略信息到缓存失败[策略policyId为空]!");
				} else {
					// 新建一个实体类
					PolicyBean policyBean = new PolicyBean();

					try {
						// 把cache类里的数据传给实体类
						ReflectUtils.copyObjectProperties(policyBean, policyCache, PolicyBean.class, PolicyCache.class);
						// 用反射得到实体类的属性值
						Map<String, String> fieldValueMap = ReflectUtils.setObject2Map(
								policyBean, PolicyBean.class);
						// 把属性字段对应的值存入到Redis中
						for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
							try{
							RedisUtil.getInstance().hset(
									ICacheService.PREFIX_POLICYCACHE
											+ policyCache.getId(),
									entry.getKey(), entry.getValue());
							}
							catch(Exception e)
							{ 
								
								log.info("缓存updatePolicy异常!"+e.getMessage());
								result.setCode(3);
								result.setInfo("缓存updatePolicy异常![策略信息policyId"+policyCache.getId()+"异常]!");
								return result;
							}
						}
						log.info("更新策略信息到缓存成功!");
						result.setCode(0);
						result.setInfo("更新策略信息到缓存成功!");
					} catch (IllegalAccessException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				return result;
	}

	@Override
	public Result updatePolicyByField(String maxCode, String minCode,
			String policyId, Map<String, String> fieldValueMap) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.UPDATEPOLICYBYFIELD)){
			log.info("根据map更新策略信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("根据map更新策略信息参数不正确[功能号minCode不正确]!");

		}else if (fieldValueMap == null && fieldValueMap.size() < 1) {
			log.info("更新策略信息到缓存失败[需要更新的属性名及属性值为空]!");
			result.setCode(1);
			result.setInfo("更新策略信息到缓存失败[需要更新的属性名及属性值为空]!");

		} else if (StringUtils.isBlank(policyId)) {
			log.info("更新策略信息到缓存失败[策略policyId为空]!");
			result.setCode(2);
			result.setInfo("更新策略信息到缓存失败[策略policyId为空]!");
		} else {
			for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
				try{
				RedisUtil.getInstance().hset(
						ICacheService.PREFIX_POLICYCACHE + policyId,
						entry.getKey(), entry.getValue());
				}
				catch(Exception e)
				{ 
					
					log.info("缓存updatePolicyByField异常!"+e.getMessage());
					result.setCode(3);
					result.setInfo("缓存updatePolicyByField异常![策略信息policyId"+policyId+"异常]!");
					return result;
				}
			}
			log.info("更新策略信息到缓存成功!");
			result.setCode(0);
			result.setInfo("更新策略信息到缓存成功!");
		}
		return result;
	}

	@Override
	public PolicyCache queryPolicyByPolicyId(String maxCode, String minCode,
			String policyId) {
		Result result = new Result();
		PolicyCache policyCache = new PolicyCache();
		if(!minCode.equals(ICacheService.QUERYPOLICYBYPOLICYID)){
			log.info("根据policyId查询策略信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("根据policyId查询策略信息参数不正确[功能号minCode不正确]!");

		}else if (StringUtils.isBlank(policyId)) {
			log.info("从缓存中查询策略信息失败[输入 policyId=" + policyId + " 为空]!");
			result.setCode(2);
			result.setInfo("从缓存中查询策略信息失败[策略policyId为空]!");
			return null;
		} else if (!StringUtils.isBlank(policyId)) {
			Map<String, String> fieldValueMap = new HashMap<String, String>();
			// 从缓存中取出的所有属性值给map
			try{
			fieldValueMap = RedisUtil.getInstance().hgetAll(
					ICacheService.PREFIX_POLICYCACHE + policyId);
			}
			 catch(Exception e)
			 {
				 
				 log.info("从缓存中查询策略信息失败[输入 policyId=" + policyId
							+ " 对应的值在缓存中未找到]!"+e.getLocalizedMessage());
				    result.setCode(3);
					result.setInfo("从缓存中查询策略信息失败[输入 policyId=" + policyId
							+ " 对应的值在缓存中未找到]!");
					policyCache.clear();
					return policyCache;
			 }
			if (fieldValueMap == null || fieldValueMap.size() < 1) {
				log.info("从缓存中查询策略信息失败[输入 policyId=" + policyId
						+ " 对应的值在缓存中未找到]!");
				policyCache.clear();
				return policyCache;
			} else {
				
				// 将查询出的Hash值依次对应让如到DeviceCache对象中，并返回
				// 考虑可以写工具类
				policyCache = ReflectUtils.setMap2Ojbect(fieldValueMap,
						policyCache, PolicyCache.class);
				log.info("从缓存中查询策略信息成功");
				result.setCode(0);
				result.setInfo("从缓存中查询策略信息成功!");
				return policyCache;
			}
		}
		policyCache.clear();
		return policyCache;
	}

	@Override
	public Result deletePolicyByPolicyId(String maxCode, String minCode,
			String policyId) {
		Result result = new Result();
		Long tempResult;
		if(!minCode.equals(ICacheService.DELETEPOLICYBYPOLICYID)){
			log.info("根据policyId删除策略信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("根据policyId删除策略信息参数不正确[功能号minCode不正确]!");

		}else if (StringUtils.isBlank(policyId)) {
					log.info("从缓存中删除策略信息失败[需要删除的策略信息输入值policyId为空]!");
					result.setCode(1);
					result.setInfo("从缓存中删除策略信息失败[需要删除的策略信息输入值policyId为空]!");

				} else {
					try{
						tempResult= RedisUtil.getInstance().delete(
							ICacheService.PREFIX_POLICYCACHE + policyId);
					}
					catch(Exception e)
					{
						log.info("从缓存中删除策略信息失败!"+e.getMessage());
						result.setCode(3);
						result.setInfo("从缓存中删除策略信息失败!"+e.getMessage());
						tempResult=-1l;
						return result;
					}
					if (tempResult >= 0) {
						result.setCode(0);
						result.setInfo("从缓存中删除策略信息成功");
					}

					log.info("删除返回为" + tempResult);
				}
				return result;
	}

}
 