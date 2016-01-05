package com.vrv.cems.service.cache.service;

import org.apache.log4j.Logger;

import com.vrv.cems.service.base.bean.cache.Result;

public interface PtpService {
	
	

	public Logger log = Logger.getLogger(PtpService.class);
	/**
	 * 保存点对点缓存数据
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1601
	 * @param uuid		    MD5(maxCode+minCode+devOnlyId )
	 * @param ptpResult	            点对点操作返回结果
	 * @param expiredTime   客户端返回点对点结果的有效期，单位：秒	
	 * @return
	 * Result(0,保存点对点缓存数据成功.);
	 * Result(1,保存点对点缓存数据失败[uuid为空]!);
	 */
	Result savePtp(String maxCode,String minCode,String uuid,String ptpResult);
	
	/**
	 * 更新点对点缓存数据
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1602
	 * @param uuid		    MD5(maxCode+minCode+devOnlyId )
	 * @return
	 * Result(0,更新点对点缓存数据成功.);
	 * Result(1,更新点对点缓存数据失败[uuid为空]!);
	 */
	Result updatePtp (String maxCode,String minCode, String uuid,String ptpResult);
	
	
	/**
	 * 查询点对点缓存数据
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1603
	 * @param uuid		    MD5(maxCode+minCode+devOnlyId )
	 * @return
	 * Result(0,查询点对点缓存数据成功.);
	 * Result(1,查询点对点缓存数据失败[uuid为空]!);
	 */
	String queryPtp(String maxCode,String minCode, String uuid);
	
	/**
	 * 删除点对点缓存数据
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1604
	 * @param uuid		    MD5(maxCode+minCode+devOnlyId )
	 * @return
	 * Result(0,删除点对点缓存数据成功.);
	 * Result(1,删除点对点缓存数据失败[uuid为空]!);
	 */
	Result  deletePtp (String maxCode,String minCode,String  uuid);
	
}
