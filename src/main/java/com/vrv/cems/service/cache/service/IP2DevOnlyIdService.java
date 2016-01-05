package com.vrv.cems.service.cache.service; 

import org.apache.log4j.Logger;

import com.vrv.cems.service.base.bean.cache.Result;

/** 
 *   <B>说       明</B>:IP与DevOnlyId关系Service接口
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午10:02:18 
 */
public interface IP2DevOnlyIdService {

	public Logger log = Logger.getLogger(IP2DevOnlyIdService.class);

	/**
	 * 保存IP与devOnlyId对应关系
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1301
	 * @param ip			IP
	 * @param devOnlyId		设备唯一ID
	 * @return
	 *		Result(0,保存IP与devOnlyId对应关系到缓存成功.);;
	 *		Result(1,保存IP与devOnlyId对应关系到缓存失败[IP为空]!);;
	 *		Result(2,保存IP与devOnlyId对应关系到缓存失败[devOnlyId为空]!);;
	 */
	public Result saveIP2DevOnlyId(String maxCode,String minCode,String ip,String devOnlyId);
	
	/**
	 * 更新IP与devOnlyId对应关系
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1302
	 * @param ip			IP
	 * @param devOnlyId		设备唯一ID
	 * @return
	 *		Result(0,更新IP与devOnlyId对应关系到缓存成功.);;
	 *		Result(1,更新IP与devOnlyId对应关系到缓存失败[IP为空]!);;
	 *		Result(2,更新IP与devOnlyId对应关系到缓存失败[devOnlyId为空]!);;
	 */
	public Result updateIP2DevOnlyId(String maxCode,String minCode,String ip,String devOnlyId);
	
	/**
	 * 查询IP与devOnlyId对应关系
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1303
	 * @param ip			IP
	 * @return
	 *		devOnlyId		IP对应的设备唯一ID
	 */
	public String queryIP2DevOnlyId(String maxCode,String minCode,String ip);
	
	/**
	 * 删除IP与devOnlyId对应关系
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1304
	 * @param ip			IP
	 * @return
	 *		Result(0,从缓存中删除IP与devOnlyId对应关系成功.);;
	 *		Result(1,从缓存中删除IP与devOnlyId对应关系失败[IP为空]!);;
	 */
	public Result deleteIP2DevOnlyId(String maxCode,String minCode,String ip);
}
 