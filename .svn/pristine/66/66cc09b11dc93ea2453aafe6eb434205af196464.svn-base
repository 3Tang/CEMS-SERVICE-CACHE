package com.vrv.cems.service.cache.service; 

import java.util.Map;

import org.apache.log4j.Logger;

import com.vrv.cems.service.base.bean.cache.CInstallPackCache;
import com.vrv.cems.service.base.bean.cache.Result;

/** 
 *   <B>说       明</B>:产品安装包信息Service接口
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午9:56:53 
 */
public interface ProductCInstallPackService {

	public Logger log = Logger.getLogger(ProductCInstallPackService.class);

	/**
	 * 保存产品安装包信息
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：1001
	 * @param cInstallPackCache	需要保存的产品安装包信息
	 * @return
	 *		Result(0,保存产品安装包信息到缓存成功.);;
	 *		Result(1,保存产品安装包信息到缓存失败[产品信息为空]!);;
	 *		Result(2,保存产品安装包信息到缓存失败[产品信息cInstallPackId为空]!);;
	 */
	public Result saveProductCInstallPack(String maxCode,String minCode,CInstallPackCache cInstallPackCache);
	
	/**
	 * 更新产品安装包信息
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：1002
	 * @param cInstallPackCache	需要更新的产品安装包信息
	 * @return
	 *		Result(0,更新产品安装包信息到缓存成功.);;
	 *		Result(1,更新产品安装包信息到缓存失败[产品信息为空]!);;
	 *		Result(2,更新产品安装包信息到缓存失败[产品信息cInstallPackId为空]!);;
	 */
	public Result updateProductCInstallPack(String maxCode,String minCode,CInstallPackCache cInstallPackCache);
	
	/**
	 * 更新产品安装包信息,指定属性更新
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：1003
	 * @param cInstallPackId	需要更新的产品安装包ID
	 * @param fieldValueMap		需要更新的指定属性Key和Value
	 * @return
	 *		Result(0,更新产品安装包信息到缓存成功.);;
	 *		Result(1,更新产品安装包信息到缓存失败[产品信息为空]!);;
	 *		Result(2,更新产品安装包信息到缓存失败[产品信息cInstallPackId为空]!);;
	 */
	public Result updateProductCInstallPackByField(String maxCode,String minCode,String cInstallPackId,Map<String,String> fieldValueMap );
	
	/**
	 * 查询产品安装包信息
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：1004
	 * @param cInstallPackId	需要查询的产品安装包ID
	 * @return
	 *		CInstallPackCache	需要查询的产品安装包信息
	 */
	public CInstallPackCache queryProductCInstallPackById(String maxCode,String minCode,String cInstallPackId);
	
	/**
	 * 删除产品安装包信息
	 * @param maxCode			缓存服务号：00FF0700
	 * @param minCode			缓存服务功能号：1005
	 * @param cInstallPackId	需要更新的产品安装包ID
	 * @return
	 *		Result(0,从缓存中删除产品安装包信息成功.);;
	 *		Result(1,从缓存中删除产品安装包信息失败[产品信息cInstallPackId为空]!);;
	 */
	public Result deleteProductCInstallPackById(String maxCode,String minCode,String cInstallPackId);
	
}
 