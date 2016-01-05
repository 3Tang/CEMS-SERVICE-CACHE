package com.vrv.cems.service.cache.service; 

import org.apache.log4j.Logger;

import com.vrv.cems.service.base.bean.cache.Result;

/** 
 *   <B>说       明</B>:IPMAC与DevOnlyId关系Service接口
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午10:00:38 
 */
public interface IPMAC2DevOnlyIdService {

	public Logger log = Logger.getLogger(IPMAC2DevOnlyIdService.class);

	/**
	 * 保存IPMAC与devOnlyId的对应关系
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1201
	 * @param ip			IP 
	 * @param mac			MAC
	 * @param devOnlyId		设备唯一ID
	 * @return
	 *		Result(0,保存IPMAC与devOnlyId的对应关系成功.);;
	 *		Result(1,保存IPMAC与devOnlyId的对应关系失败[IP为空]!);;
	 *		Result(2,保存IPMAC与devOnlyId的对应关系失败[MAC为空]!);;
	 *		Result(3,保存IPMAC与devOnlyId的对应关系失败[devOnlyId为空]!);;
	 */
	public Result saveIPMAC2DevOnlyId(String maxCode,String minCode,String ip,String mac,String devOnlyId);
	
	/**
	 * 更新IPMAC与devOnlyId的对应关系
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1202
	 * @param ip			IP 
	 * @param mac			MAC
	 * @param devOnlyId		设备唯一ID
	 * @return
	 *		Result(0,更新IPMAC与devOnlyId的对应关系成功.);;
	 *		Result(1,更新IPMAC与devOnlyId的对应关系失败[IP为空]!);;
	 *		Result(2,更新IPMAC与devOnlyId的对应关系失败[MAC为空]!);;
	 *		Result(3,更新IPMAC与devOnlyId的对应关系失败[devOnlyId为空]!);;
	 */
	public Result updateIPMAC2DevOnlyId(String maxCode,String minCode,String ip,String mac,String devOnlyId);
	
	/**
	 * 查询IPMAC与devOnlyId的对应关系
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1203
	 * @param ip			IP 
	 * @param mac			MAC
	 * @return
	 *		devOnlyId		设备唯一ID
	 */
	public String queryIPMAC2DevOnlyId(String maxCode,String minCode,String ip,String mac);
	
	/**
	 * 删除IPMAC与devOnlyId的对应关系
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1204
	 * @param ip			IP 
	 * @param mac			MAC
	 * @return
	 *		Result(0,删除IPMAC与devOnlyId的对应关系成功.);;
	 *		Result(1,删除IPMAC与devOnlyId的对应关系失败[IP为空]!);;
	 *		Result(2,删除IPMAC与devOnlyId的对应关系失败[MAC为空]!);;
	 */
	public Result deleteIPMAC2DevOnlyId(String maxCode,String minCode,String ip,String mac);
	
}
 