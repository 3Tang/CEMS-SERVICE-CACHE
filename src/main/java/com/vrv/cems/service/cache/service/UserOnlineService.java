package com.vrv.cems.service.cache.service; 

import java.util.Map;

import org.apache.log4j.Logger;

import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.base.bean.cache.UserOnlineCache;

/** 
 *   <B>说       明</B>:用户在线信息Service接口
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午9:54:11 
 */
public interface UserOnlineService {

	public Logger log = Logger.getLogger(UserOnlineService.class);

	/**
	 * 保存用户在线情况信息
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：801
	 * @param userOnlineCache	需要保存的用户在线情况信息
	 * @return
	 *		Result(0,保存用户在线情况信息到缓存成功.);;
	 *		Result(1,保存用户在线情况信息到缓存失败[用户在线情况信息为空]!);;
	 *		Result(2,保存用户在线情况信息到缓存失败[用户在线情况信息中userOnlyId为空]!);;
	 */
	public Result saveUserOnline(String maxCode,String minCode,UserOnlineCache userOnlineCache);
	
	/**
	 * 更新用户在线情况信息
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：802
	 * @param userOnlineCache	需要更新的用户在线情况信息
	 * @return
	 *		Result(0,更新用户在线情况信息到缓存成功.);;
	 *		Result(1,更新用户在线情况信息到缓存失败[用户在线情况信息为空]!);;
	 *		Result(2,更新用户在线情况信息到缓存失败[用户在线情况信息中userOnlyId为空]!);;
	 */
	public Result updateUserOnline(String maxCode,String minCode,UserOnlineCache userOnlineCache);
	
	/**
	 * 更新用户在线情况信息，更新指定属性
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：803
	 * @param userOnlyId		需要更新的用户userOnlyId
	 * @param userOnlineCache	需要更新的用户在线情况信息
	 * @return
	 *		Result(0,更新用户在线情况信息到缓存成功.);;
	 *		Result(1,更新用户在线情况信息到缓存失败[用户在线情况信息中userOnlyId为空]!);;
	 *		Result(2,更新用户在线情况信息到缓存失败[用户在线情况信息中，被指定更新的属性Key和Value为空]!);;
	 */
	public Result updateUserOnlineByField(String maxCode,String minCode,String userOnlyId,Map<String,String> fieldValueMap );
	
	/**
	 * 查询用户在线情况信息，根据userOnlyId
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：804
	 * @param userOnlyId	需要查询的用户userOnlyId
	 * @return
	 *		UserOnlieCache	用户在线情况信息
	 */
	UserOnlineCache queryUserOnlineByUserOnlyId(String maxCode,String minCode,String userOnlyId);
	
	/**
	 * 删除用户在线情况信息，根据userOnlyId
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：805
	 * @param userOnlyId	需要删除的用户userOnlyId
	 * @return
	 *		Result(0,从缓存中删除用户在线情况信息成功.);;
	 *		Result(1,从缓存中删除用户在线情况信息失败[用户在线情况信息中userOnlyId为空]!);;
	 */
	public Result deleteUserOnlineByUserOnlyId(String maxCode,String minCode,String userOnlyId);
	
}
 