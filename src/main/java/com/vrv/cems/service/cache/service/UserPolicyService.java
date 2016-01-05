package com.vrv.cems.service.cache.service; 

import org.apache.log4j.Logger;

import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.base.bean.cache.UserPolicyCache;

/** 
 *   <B>说       明</B>:用户策略信息Service 接口
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午9:55:51 
 */
public interface UserPolicyService {

	public Logger log = Logger.getLogger(UserPolicyService.class);

	/**
	 * 保存用户执行策略信息
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：901
	 * @param userPolicyCache	需要保存的用户执行策略信息
	 * @return
	 *		Result(0,保存用户执行策略信息到缓存成功.);;
	 *		Result(1,保存用户执行策略信息到缓存失败[用户执行策略信息为空]!);;
	 *		Result(2,保存用户执行策略信息到缓存失败[用户执行策略信息userOnlyId为空]!);;
	 */
	public Result saveUserPolicy(String maxCode,String minCode,UserPolicyCache userPolicyCache);
	
	
	/**
	 * 更新用户执行策略信息
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：902
	 * @param userPolicyCache	需要更新的用户执行策略信息
	 * @return
	 *		Result(0,更新用户执行策略信息到缓存成功.);;
	 *		Result(1,更新用户执行策略信息到缓存失败[用户执行策略信息为空]!);;
	 *		Result(2,更新用户执行策略信息到缓存失败[用户执行策略信息userOnlyId为空]!);;
	 */
	public Result updateUserPolicy(String maxCode,String minCode,UserPolicyCache userPolicyCache);
	
	

	/**
	 * 查询用户执行策略信息
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：903
	 * @param userOnlyId	需要查询的用户userOnlyId
	 * @return
	 *		UserPolicyCache	需要查询的用户执行策略信息
	 */
	UserPolicyCache queryUserPolicyByUserOnlyId(String maxCode,String minCode,String userOnlyId);
	
	
	/**
	 * 删除用户执行策略信息，根据userOnlyId
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：904
	 * @param userOnlyId	需要删除的用户userOnlyId
	 * @return
	 *		Result(0,从缓存中删除用户执行策略信息成功.);;
	 *		Result(1,从缓存中删除用户执行策略信息失败[用户执行策略信息userOnlyId为空]!);;
	 */
	public Result deleteUserPolicyByUserOnlyId(String maxCode,String minCode,String userOnlyId);

	
	
}
 