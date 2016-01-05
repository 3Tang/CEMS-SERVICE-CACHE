package com.vrv.cems.service.cache.service; 

import java.util.List;

import org.apache.log4j.Logger;

import com.vrv.cems.service.base.bean.cache.DeviceProduct;
import com.vrv.cems.service.base.bean.cache.Result;

/** 
 *   <B>说       明</B>:设备已安装产品信息Service接口
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午9:47:06 
 */
public interface DeviceInsProOldService {
	

	public Logger log = Logger.getLogger(DeviceInsProOldService.class);
	/**
	 * 保存设备已安装产品信息
	 * @param maxCode				缓存服务号：00FF0700
	 * @param minCode				缓存服务功能号：401
	 * @param devOnlyId				需要更新的设备devOnlyId
	 * @param deviceProducutList	需要保存的设备已安装产品信息
	 * @return
	 *		Result(0,保存设备已安装产品信息到缓存成功.);;
	 *		Result(1,保存设备已安装产品信息到缓存失败[设备已安装产品信息为空]!);;
	 *		Result(2,保存设备已安装产品信息到缓存失败[设备已安装产品信息devOnlyId为空]!);;
	 */
	public Result saveDeviceInsProOld(String maxCode,String minCode,String devOnlyId,List<DeviceProduct> deviceProductList);
	
	
	/**
	 * 更新设备已安装所有产品信息
	 * @param maxCode				缓存服务号：00FF0700
	 * @param minCode				缓存服务功能号：402
	 * @param devOnlyId				需要更新的设备devOnlyId
	 * @param List<DeviceProduct>	需要更新的设备所有已安装产品信息(至少devOnlyId不能为空);
	 * @return
	 *		Result(0,更新设备已安装产品信息到缓存成功.);;
	 *		Result(1,更新设备已安装产品信息到缓存失败[设备已安装产品信息为空]!);;
	 *		Result(2,更新设备已安装产品信息到缓存失败[设备已安装产品信息devOnlyId为空]!);;
	 */
	public Result updateDeviceInsProOld(String maxCode,String minCode,String devOnlyId,List<DeviceProduct> deviceProductList);
	
	
	/**
	 * 查询设备已安装产品信息，根据设备devOnlyId
	 *
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：406
	 * @param devOnlyId		需要查询的设备devOnlyId
	 * @return
	 *		List<DeviceProduct>	需要查询的设备所有已安装产品信息
	 */
	public List<DeviceProduct>	queryDeviceInsProOldByDevOnlyId(String maxCode,String minCode,String devOnlyId);
	
	
	/**
	 * 删除设备已安装产品信息，根据设备devOnlyId
	 *
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：408
	 * @param devOnlyId		需要删除的设备devOnlyId
	 * @return
	 *		Result(0,从缓存中删除设备已安装产品信息成功.);;
	 *		Result(1,从缓存中删除设备已安装产品信息失败[设备已安装产品信息为空]!);;
	 *		Result(2,从缓存中删除设备已安装产品信息失败[设备已安装产品信息devOnlyId为空]!);;
	 */
	public Result deleteDeviceInsProOldByDevOnlyId(String maxCode,String minCode,String devOnlyId);
	
}
 