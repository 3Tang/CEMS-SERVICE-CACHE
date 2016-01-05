package com.vrv.cems.service.cache.service;

import java.nio.ByteBuffer;

import org.apache.log4j.Logger;

import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.cache.bean.InParam;

public interface CommonService {
	public Logger log = Logger.getLogger(CommonService.class);;
	/**
	 * 验证Key在缓存中是否已存在
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1
	 * @param prefix		前缀
	 * @param key 			需要验证的Key
	 * @return
	 *		Result(0,存在.);;
	 *		Result(1,不存在.);;
	 *		Result(2,验证信息参数不正确[前缀prifix为空]!);;
	 *		Result(3,验证信息参数不正确[key为空]!);;
	 */
	Result isExist(String maxCode,String minCode,String prefix,String key);
	
	/**
     * 下发缓存服务策略
     * @param maxCode		缓存服务号：00FF0700
     * @param minCode		缓存服务功能号：1000
     * 
     * @param maxCode
     * @param minCode
     * @param checkCode
     * @param isZip
     * @param data
     * @param isEncrypt
     * @param key
     * @param flag
     */
	ByteBuffer getDataTS(String maxCode, String minCode, String checkCode,
			boolean isZip, ByteBuffer data, boolean isEncrypt, String key,
			int flag);;
	
	
	
	/**
	 * 保存account与userOnlyId对应关系
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1501
	 * @param account			用户账号
	 * @param userOnlyId		用户唯一ID
	 * @return
	 *		Result(0,保存account与userOnlyId对应关系到缓存成功.);;
	 *		Result(1,保存account与userOnlyId对应关系到缓存失败[account为空]!);;
	 *		Result(2,保存account与userOnlyId对应关系到缓存失败[userOnlyId为空]!);;
	 */
	Result saveAccount2UserOnlyId(String maxCode,String minCode,String account,String userOnlyId);
	
	/**
	 * 更新account与userOnlyId对应关系
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1502
	 * @param account			用户账号
	 * @param userOnlyId	设备唯一ID
	 * @return
	 *		Result(0,更新account与userOnlyId对应关系到缓存成功.);;
	 *		Result(1,更新account与userOnlyId对应关系到缓存失败[account为空]!);;
	 *		Result(2,更新account与userOnlyId对应关系到缓存失败[userOnlyId为空]!);;
	 */
	Result updateAccount2UserOnlyId (String maxCode,String minCode, String account,String userOnlyId);
	
	/**
	 * 查询account与userOnlyId对应关系
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1503
	 * @param account			用户账号
	 * @return
	 *		userOnlyId	用户唯一ID
	 */
	String queryAccount2UserOnlyId (String maxCode,String minCode,String account);
	
	/**
	 * 删除account与userOnlyId对应关系
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1504
	 * @param account			用户账号
	 * @return
	 *		Result(0,从缓存中删除account与userOnlyId对应关系成功.);;
	 *		Result(1,从缓存中删除account与userOnlyId对应关系失败[account为空]!);;
	 */
	Result deleteAccount2UserOnlyId (String maxCode,String minCode,String account);
}
