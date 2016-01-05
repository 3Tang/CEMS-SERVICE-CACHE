package com.vrv.cems.service.cache.service.impl; 

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.base.bean.cache.UserOnlineCache;
import com.vrv.cems.service.base.bean.cache.UserPolicyCache;
import com.vrv.cems.service.base.interfaces.ICacheService;
import com.vrv.cems.service.cache.bean.UserPolicyBean;
import com.vrv.cems.service.cache.service.UserPolicyService;
import com.vrv.cems.service.cache.util.RedisUtil;
import com.vrv.cems.service.cache.util.ReflectUtils;

/** 
 *   <B>说       明</B>:用户执行策略信息Service实现类(默认)
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午9:56:19 
 */
public class UserPolicyServiceImpl implements UserPolicyService {

	@Override
	public Result saveUserPolicy(String maxCode, String minCode,
			UserPolicyCache userPolicyCache) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.SAVEUSERPOLICY)){
			log.info("保存用户执行策略信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("保存用户执行策略信息参数不正确[功能号minCode不正确]!");
		}else if (userPolicyCache == null) {
			log.info("保存用户执行策略信息到缓存失败[用户执行策略信息为空]!");
			result.setCode(1);
			result.setInfo("保存用户执行策略信息到缓存失败[用户执行策略信息为空]!");
		} else if (StringUtils.isBlank(userPolicyCache.getUserOnlyId())) {
			log.info("保存用户执行策略信息到缓存失败[用户userOnlyId为空]!");
			result.setCode(2);
			result.setInfo("保存用户执行策略信息到缓存失败[用户userOnlyId为空]!");
		} else {
			// 后期考虑反射实现....
			// 新建一个实体类
			UserPolicyBean userPolicyBean = new UserPolicyBean();

			try {
				// 把cache类里的数据传给实体类
				BeanUtils.copyProperties(userPolicyBean, userPolicyCache);
				// 用反射得到实体类的属性值
				Map<String, String> fieldValueMap = ReflectUtils.setObject2Map(
						userPolicyBean, UserPolicyBean.class);
				// 把属性字段对应的值存入到Redis中
				for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
					try{
					RedisUtil.getInstance().hset(
							ICacheService.PREFIX_USERPOLICYCACHE
									+ userPolicyCache.getUserOnlyId(),
							entry.getKey(), entry.getValue());
					}
					catch(Exception e)
					{ 
						
						log.info("缓存saveUserPolicy异常!"+e.getMessage());
						result.setCode(3);
						result.setInfo("缓存saveUserPolicy异常![用户userOnlyId"+userPolicyCache.getUserOnlyId()+"异常]!");
						return result;
					}
				}
				log.info("保存用户执行策略信息到缓存成功!");
				result.setCode(0);
				result.setInfo("保存用户执行策略信息到缓存成功!");
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
	public Result updateUserPolicy(String maxCode, String minCode,
			UserPolicyCache userPolicyCache) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.UPDATEUSERPOLICY)){
			log.info("更新用户执行策略信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("更新用户执行策略信息参数不正确[功能号minCode不正确]!");
		}else if (userPolicyCache == null) {
			log.info("更新用户执行策略信息到缓存失败[用户执行策略信息为空]!");
			result.setCode(1);
			result.setInfo("更新用户执行策略信息到缓存失败[用户执行策略信息为空]!");
		} else if (StringUtils.isBlank(userPolicyCache.getUserOnlyId())) {
			log.info("保存用户执行策略信息到缓存失败[用户userOnlyId为空]!");
			result.setCode(2);
			result.setInfo("更新用户执行策略信息到缓存失败[用户userOnlyId为空]!");
		} else {
			// 新建一个实体类
			UserPolicyBean userPolicyBean = new UserPolicyBean();

			try {
				// 把cache类里的数据传给实体类
				BeanUtils.copyProperties(userPolicyBean, userPolicyCache);
				// 用反射得到实体类的属性值
				Map<String, String> fieldValueMap = ReflectUtils.setObject2Map(
						userPolicyBean, UserPolicyBean.class);
				// 把属性字段对应的值存入到Redis中
				for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
					try{
					RedisUtil.getInstance().hset(
							ICacheService.PREFIX_USERPOLICYCACHE
									+ userPolicyCache.getUserOnlyId(),
							entry.getKey(), entry.getValue());
					}
					catch(Exception e)
					{ 
						
						log.info("缓存updateUserPolicy异常!"+e.getMessage());
						result.setCode(3);
						result.setInfo("缓存updateUserPolicy异常![用户userOnlyId"+userPolicyCache.getUserOnlyId()+"异常]!");
						return result;
					}
				}
				log.info("更新用户执行策略信息到缓存成功!");
				result.setCode(0);
				result.setInfo("更新用户执行策略信息到缓存成功!");
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
	public UserPolicyCache queryUserPolicyByUserOnlyId(String maxCode,
			String minCode, String userOnlyId) {
		List<UserPolicyCache> userPolicyCacheList=new ArrayList<UserPolicyCache>();
		UserPolicyCache userPolicyCache = new UserPolicyCache();
		Result result = new Result();
		if(!minCode.equals(ICacheService.QUERYUSERPOLICYBYUSERONLYID)){
			log.info("根据userOnlyId查询用户执行策略信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("根据userOnlyId查询用户执行策略信息参数不正确[功能号minCode不正确]!");
		}else if (StringUtils.isBlank(userOnlyId)) {
			log.info("从缓存中查询用户执行策略信息失败[输入 userOnlyId=" + userOnlyId + " 为空]!");
			result.setCode(2);
			result.setInfo("从缓存中查询用户执行策略信息失败[用户userOnlyId为空]!");
			return null;
		} else if (!StringUtils.isBlank(userOnlyId)) {
			Map<String, String> fieldValueMap = new HashMap<String, String>();
			// 从缓存中取出的所有属性值给map
			try{
			fieldValueMap = RedisUtil.getInstance().hgetAll(
					ICacheService.PREFIX_USERPOLICYCACHE + userOnlyId);
			}
			 catch(Exception e)
			 {
				 
				 log.info("从缓存中查询用户执行策略信息失败[输入 userOnlyId=" + userOnlyId
							+ " 对应的值在缓存中未找到]!"+e.getLocalizedMessage());
				    result.setCode(3);
					result.setInfo("从缓存中查询用户执行策略信息失败[输入 userOnlyId=" + userOnlyId
							+ " 对应的值在缓存中未找到]!");
					userPolicyCache.clear();
					return userPolicyCache;
			 }
			if (fieldValueMap == null || fieldValueMap.size() < 1) {
				log.info("从缓存中查询用户执行策略信息失败[输入 userOnlyId=" + userOnlyId
						+ " 对应的值在缓存中未找到]!");
				userPolicyCache.clear();
				return userPolicyCache;
			} else {
				// 将查询出的Hash值依次对应让如到DeviceCache对象中，并返回
				// 考虑可以写工具类
				userPolicyCache = ReflectUtils.setMap2Ojbect(fieldValueMap,
						userPolicyCache, UserPolicyCache.class);
				log.info("查询用户执行策略信息成功!");
				result.setCode(0);
				result.setInfo("查询用户执行策略信息成功!");
				return userPolicyCache;
			}
		}
		userPolicyCache.clear();
		return userPolicyCache;
	}

	@Override
	public Result deleteUserPolicyByUserOnlyId(String maxCode, String minCode,
			String userOnlyId) {
		Long tempResult ;
		Result result = new Result();
		if(!minCode.equals(ICacheService.DELETEUSERPOLICYBYUSERONLYID)){
			log.info("根据userOnlyId删除用户执行策略信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("根据userOnlyId删除用户执行策略信息参数不正确[功能号minCode不正确]!");
		}else if (StringUtils.isBlank(userOnlyId)) {
			log.info("从缓存中删除用户执行策略信息失败[需要删除的用户执行策略信息输入值userOnlyId为空]!");
			result.setCode(1);
			result.setInfo("从缓存中删除用户执行策略信息失败[需要删除的用户执行策略信息输入值userOnlyId为空]!");

		} else {
			try{
			 tempResult = RedisUtil.getInstance().delete(
					ICacheService.PREFIX_USERPOLICYCACHE + userOnlyId);
			}
			catch(Exception e)
			{
				log.info("从缓存中删除设备信息失败!"+e.getMessage());
				result.setCode(3);
				result.setInfo("从缓存中删除设备信息失败!"+e.getMessage());
				tempResult=-1l;
				return result;
			}
			 if (tempResult >= 0) {
				result.setCode(0);
				result.setInfo("从缓存中删除用户执行策略信息成功");
			}

			log.info("删除返回为" + tempResult);
		}
		return result;
	}

}
 