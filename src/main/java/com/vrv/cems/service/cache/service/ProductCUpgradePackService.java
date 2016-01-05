package com.vrv.cems.service.cache.service; 

import java.util.Map;

import org.apache.log4j.Logger;

import com.vrv.cems.service.base.bean.cache.CUpgradePackCache;
import com.vrv.cems.service.base.bean.cache.Result;

/** 
 *   <B>说       明</B>:产品升级包信息Service接口
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午10:03:44 
 */
public interface ProductCUpgradePackService {

	public Logger log = Logger.getLogger(ProductCUpgradePackService.class);

	/**
	 * 保存产品升级包信息
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：1401
	 * @param cUpgradePackCache	需要保存的产品升级包信息
	 * @return
	 *		Result(0,保存产品升级包信息到缓存成功.);;
	 *		Result(1,保存产品升级包信息到缓存失败[产品信息为空]!);;
	 *		Result(2,保存产品升级包信息到缓存失败[产品信息cUpgradePackId为空]!);;
	 */
	public Result saveProductCUpgradePack(String maxCode,String minCode,CUpgradePackCache cUpgradePackCache);
	
	/**
	 * 更新产品升级包信息
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：1402
	 * @param cUpgradePackCache	需要更新的产品升级包信息
	 * @return
	 *		Result(0,更新产品升级包信息到缓存成功.);;
	 *		Result(1,更新产品升级包信息到缓存失败[产品信息为空]!);;
	 *		Result(2,更新产品升级包信息到缓存失败[产品信息cUpgradePackId为空]!);;
	 */
	public Result updateProductCUpgradePack(String maxCode,String minCode,CUpgradePackCache cUpgradePackCache);
	
	/**
	 * 更新产品升级包信息,指定属性更新
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：1403
	 * @param cUpgradePackId	需要更新的产品升级包ID
	 * @param fieldValueMap		需要更新的指定属性Key和Value
	 * @return
	 *		Result(0,更新产品升级包信息到缓存成功.);;
	 *		Result(1,更新产品升级包信息到缓存失败[产品信息为空]!);;
	 *		Result(2,更新产品升级包信息到缓存失败[产品信息cUpgradePackId为空]!);;
	 */
	public Result updateProductCUpgradePackByField(String maxCode,String minCode,String cUpgradePackId,Map<String,String> fieldValueMap);
	
	/**
	 * 查询产品升级包信息
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：1404
	 * @param cUpgradePackId	需要查询的产品升级包ID
	 * @return
	 *		CUpgradePackCache	需要查询的产品升级包信息
	 */
	CUpgradePackCache queryProductCUpgradePackById(String maxCode,String minCode,String cUpgradePackId);
	
	/**
	 * 删除产品升级包信息
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：1405
	 * @param cUpgradePackId	需要更新的产品升级包ID
	 * @return
	 *		Result(0,从缓存中删除产品升级包信息成功.);;
	 *		Result(1,从缓存中删除产品升级包信息失败[产品信息cUpgradePackId为空]!);;
	 */
	public Result deleteProductCUpgradePackById(String maxCode,String minCode,String cUpgradePackId);
}
 