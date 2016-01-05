package com.vrv.cems.service.cache.service; 

import java.util.List;

import org.apache.log4j.Logger;

import com.vrv.cems.service.base.bean.cache.DeviceProduct;
import com.vrv.cems.service.base.bean.cache.Result;

/** 
 *   <B>说       明</B>:设备应安装产品信息Service接口
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午9:48:42 
 */
public interface DeviceInsProNewService {
	
	public Logger log = Logger.getLogger(DeviceInsProNewService.class);

	/**
	 * 保存设备应安装产品信息
	 * @param maxCode				缓存服务号：00FF0700
	 * @param minCode				缓存服务功能号：501
	 * @param devOnlyId				需要保存的设备devOnlyId
	 * @param deviceProductList		需要保存的设备应安装产品信息
	 * @return
	 *		Result(0,保存设备应安装产品信息到缓存成功.);;
	 *		Result(1,保存设备应安装产品信息到缓存失败[设备应安装产品信息为空]!);;
	 *		Result(2,保存设备应安装产品信息到缓存失败[设备应安装产品信息devOnlyId为空]!);;
	 */
	public Result saveDeviceInsProNew(String maxCode,String minCode,String devOnlyId,List<DeviceProduct> deviceProductList);
	
	
	/**
	 * 更新设备应安装产品信息
	 * @param maxCode				缓存服务号：00FF0700
	 * @param minCode				缓存服务功能号：502
	 * @param devOnlyId				需要更新的设备devOnlyId
	 * @param deviceProductList		需要更新的设备应安装产品信息集合
	 * @return
	 *		Result(0,更新设备应安装产品信息到缓存成功.);;
	 *		Result(1,更新设备应安装产品信息到缓存失败[设备应安装产品信息为空]!);;
	 *		Result(2,更新设备应安装产品信息到缓存失败[设备应安装产品信息devOnlyId为空]!);;
	 */
	public Result updateDeviceInsProNew(String maxCode,String minCode,String devOnlyId,List<DeviceProduct> deviceProductList);
	
	
	/**
	 * 查询设备应安装产品信息
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：505
	 * @param devOnlyId		需要更新的设备devOnlyId
	 * @return
	 *		List<DeviceProduct>	设备应安装的产品信息集合
	 */
	List<DeviceProduct> queryDeviceInsProNewByDevOnlyId(String maxCode,String minCode,String devOnlyId);
	
	
	/**
	 * 删除设备应安装所有产品信息
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：506
	 * @param devOnlyId		需要删除的设备devOnlyId
	 * @return
	 *		Result(0,从缓存中删除设备应安装所有产品信息成功.);;
	 *		Result(1,从缓存中删除设备应安装所有产品信息失败[设备devOnlyId为空]!);;
	 */
	public Result deleteDeviceInsProNewByDevOnlyId(String maxCode,String minCode,String devOnlyId);
	
	
}
 