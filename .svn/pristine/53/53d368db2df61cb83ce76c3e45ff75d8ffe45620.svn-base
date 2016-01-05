package com.vrv.cems.service.cache.service; 

import java.util.Map;

import org.apache.log4j.Logger;

import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.base.bean.cache.UserCache;

/** 
 *   <B>说       明</B>:用户信息Service接口
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午9:52:22 
 */
public interface UserService {

	public Logger log = Logger.getLogger(UserService.class);

	/**
	 * 保存用户信息
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：701
	 * @param userCache		需要保存的用户信息
	 * @return
	 *		Result(0,保存用户信息到缓存成功.);;
	 *		Result(1,保存用户信息到缓存失败[用户信息为空]!);;
	 *		Result(2,保存用户信息到缓存失败[用户信息userOnlyId为空]!);;
	 */
	public Result saveUser(String maxCode,String minCode,UserCache userCache);
	
	/**
	 * 更新用户信息
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：702
	 * @param userCache		需要更新的用户信息
	 * @return
	 *		Result(0,更新用户信息到缓存成功.);;
	 *		Result(1,更新用户信息到缓存失败[用户信息为空]!);;
	 *		Result(2,更新用户信息到缓存失败[用户信息userOnlyId为空]!);;
	 */
	public Result updateUser(String maxCode,String minCode,UserCache userCache);
	
	/**
	 * 更新用户信息,更新指定属性
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：703
	 * @param userCache		需要更新的用户信息
	 * @return
	 *		Result(0,更新用户信息到缓存成功.);;
	 *		Result(1,更新用户信息到缓存失败[用户信息userOnlyId为空]!);;
	 *		Result(2,更新用户信息到缓存失败[需要更新的指定属性Key和Value为空]!);;
	 */
	public Result updateUserByField(String maxCode,String minCode,String userOnlyId,Map<String,String> fieldValueMap );
	
	/**
	 * 查询用户信息，根据userOnlyId
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：704
	 * @param userOnlyId	需要查询的用户userOnlyId
	 * @return
	 *		UserCache		需要查询的用户信息
	 */
	public UserCache queryUserByUserOnlyId(String maxCode,String minCode,String userOnlyId);
	
	/**
	 * 删除用户信息，根据userOnlyId
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：705
	 * @param userOnlyId	需要删除的用户userOnlyId
	 * @return
	 *		Result(0,从缓存中删除用户信息成功.);;
	 *		Result(1,从缓存中删除用户信息失败[用户信息userOnlyId为空]!);;
	 */
	public Result deleteUserByUserOnlyId(String maxCode,String minCode,String userOnlyId);
}
 