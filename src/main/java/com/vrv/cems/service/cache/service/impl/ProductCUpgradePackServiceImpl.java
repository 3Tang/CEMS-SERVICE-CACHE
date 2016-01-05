package com.vrv.cems.service.cache.service.impl; 

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.vrv.cems.service.base.bean.cache.CInstallPackCache;
import com.vrv.cems.service.base.bean.cache.CUpgradePackCache;
import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.base.interfaces.ICacheService;
import com.vrv.cems.service.cache.bean.CUpgradePackBean;
import com.vrv.cems.service.cache.service.ProductCUpgradePackService;
import com.vrv.cems.service.cache.util.RedisUtil;
import com.vrv.cems.service.cache.util.ReflectUtils;

/** 
 *   <B>说       明</B>:产品升级包信息Service实现类(默认)
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午10:04:23 
 */
public class ProductCUpgradePackServiceImpl implements
		ProductCUpgradePackService {

	@Override
	public Result saveProductCUpgradePack(String maxCode, String minCode,
			CUpgradePackCache cUpgradePackCache) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.SAVEPRODUCTCUPGRADEPACK)){
			log.info("保存产品升级包信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("保存产品升级包信息参数不正确[功能号minCode不正确]!");

		}else if (cUpgradePackCache == null) {
			log.info("保存产品升级包信息到缓存失败[产品升级包信息为空]!");
			result.setCode(1);
			result.setInfo("保存产品升级包信息到缓存失败[产品升级包信息为空]!");
		} else if (StringUtils.isBlank(cUpgradePackCache.getCUpgradePackId())) {
			log.info("保存产品升级包信息到缓存失败[用户cUpgradePackId为空]!");
			result.setCode(2);
			result.setInfo("保存产品升级包信息到缓存失败[用户cUpgradePackId为空]!");
		} else {
			// 后期考虑反射实现....
			// 新建一个实体类
			CUpgradePackBean cUpgradePackBean = new CUpgradePackBean();

			try {
				// 把cache类里的数据传给实体类
				BeanUtils.copyProperties(cUpgradePackBean, cUpgradePackCache);
				// 用反射得到实体类的属性值
				
				Map<String, String> fieldValueMap = ReflectUtils.setObject2Map(
						cUpgradePackBean, CUpgradePackBean.class);
				
				// 把属性字段对应的值存入到Redis中
				for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
					try{
					RedisUtil.getInstance().hset(
							ICacheService.PREFIX_PRODUCTCUPGRADEPACKCACHE
									+ cUpgradePackCache.getCUpgradePackId(),
							entry.getKey(), entry.getValue());
					}
					catch(Exception e)
					{ 
						
						log.info("缓存saveProductCUpgradePack异常!"+e.getMessage());
						result.setCode(3);
						result.setInfo("缓存saveProductCUpgradePack异常![产品升级包cupgradePackId"+cUpgradePackCache.getCUpgradePackId()+"异常]!");
						return result;
					}
				}
				log.info("保存产品升级包信息到缓存成功!");
				result.setCode(0);
				result.setInfo("保存产品升级包信息到缓存成功!");
			} catch (IllegalAccessException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public Result updateProductCUpgradePack(String maxCode, String minCode,
			CUpgradePackCache cUpgradePackCache) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.UPDATEPRODUCTCUPGRADEPACK)){
			log.info("更新产品升级包信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("更新产品升级包信息参数不正确[功能号minCode不正确]!");

		}else if (cUpgradePackCache == null) {
			log.info("更新产品升级包信息到缓存失败[产品升级包信息为空]!");
			result.setCode(1);
			result.setInfo("更新产品升级包信息到缓存失败[产品升级包信息为空]!");
		} else if (StringUtils.isBlank(cUpgradePackCache.getCUpgradePackId())) {
			log.info("保存产品升级包信息到缓存失败[用户cUpgradePackId为空]!");
			result.setCode(2);
			result.setInfo("更新产品升级包信息到缓存失败[用户cUpgradePackId为空]!");
		} else {
			// 新建一个实体类
			CUpgradePackBean cUpgradePackBean = new CUpgradePackBean();

			try {
				// 把cache类里的数据传给实体类
				BeanUtils.copyProperties(cUpgradePackBean, cUpgradePackCache);
				// 用反射得到实体类的属性值
				Map<String, String> fieldValueMap = ReflectUtils.setObject2Map(
						cUpgradePackBean, CUpgradePackBean.class);
				// 把属性字段对应的值存入到Redis中
				for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
					try{
					RedisUtil.getInstance().hset(
							ICacheService.PREFIX_PRODUCTCUPGRADEPACKCACHE
									+ cUpgradePackCache.getCUpgradePackId(),
							entry.getKey(), entry.getValue());
					}
					catch(Exception e)
					{ 
						
						log.info("缓存updateProductCUpgradePack异常!"+e.getMessage());
						result.setCode(3);
						result.setInfo("缓存updateProductCUpgradePack异常![产品升级包cupgradePackId"+cUpgradePackCache.getCUpgradePackId()+"异常]!");
						return result;
					}
				}
				log.info("更新产品升级包信息到缓存成功!");
				result.setCode(0);
				result.setInfo("更新产品升级包信息到缓存成功!");
			} catch (IllegalAccessException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public Result updateProductCUpgradePackByField(String maxCode,
			String minCode, String cUpgradePackId,
			Map<String, String> fieldValueMap) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.UPDATEPRODUCTCUPGRADEPACKBYFIELD)){
			log.info("更新产品升级包信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("更新产品升级包信息参数不正确[功能号minCode不正确]!");

		}else if (fieldValueMap == null && fieldValueMap.size() < 1) {
			log.info("更新产品升级包信息到缓存失败[需要更新的属性名及属性值为空]!");
			result.setCode(1);
			result.setInfo("更新产品升级包信息到缓存失败[需要更新的属性名及属性值为空]!");

		} else if (StringUtils.isBlank(cUpgradePackId)) {
			log.info("更新产品升级包信息到缓存失败[用户cUpgradePackId为空]!");
			result.setCode(2);
			result.setInfo("更新产品升级包信息到缓存失败[用户cUpgradePackId为空]!");
		} else {
			for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
				try{
				RedisUtil.getInstance().hset(
						ICacheService.PREFIX_PRODUCTCUPGRADEPACKCACHE + cUpgradePackId,
						entry.getKey(), entry.getValue());
				}
				catch(Exception e)
				{ 
					
					log.info("缓存updateProductCUpgradePackByField异常!"+e.getMessage());
					result.setCode(3);
					result.setInfo("缓存updateProductCUpgradePackByField异常![产品升级包cupgradePackId"+cUpgradePackId+"异常]!");
					return result;
				}
			}
			log.info("更新产品升级包信息到缓存成功!");
			result.setCode(0);
			result.setInfo("更新产品升级包信息到缓存成功!");
		}
		return result;
	}

	@Override
	public CUpgradePackCache queryProductCUpgradePackById(String maxCode,
			String minCode, String cUpgradePackId) {
		Result result = new Result();
		List<CUpgradePackCache> cUpgradePackCacheList=new ArrayList<CUpgradePackCache>();
		CUpgradePackCache cUpgradePackCache = new CUpgradePackCache();
		if(!minCode.equals(ICacheService.QUERYPRODUCTCUPGRADEPACKBYID)){
			log.info("查询产品升级包信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("查询产品升级包信息参数不正确[功能号minCode不正确]!");

		}else if (StringUtils.isBlank(cUpgradePackId)) {
			log.info("从缓存中查询产品升级包信息失败[输入 cUpgradePackId=" + cUpgradePackId + " 为空]!");
			result.setCode(2);
			result.setInfo("从缓存中查询产品升级包信息失败[用户cUpgradePackId为空]!");
			return null;
		} else if (!StringUtils.isBlank(cUpgradePackId)) {
			Map<String, String> fieldValueMap = new HashMap<String, String>();
			// 从缓存中取出的所有属性值给map
			try{
			fieldValueMap = RedisUtil.getInstance().hgetAll(
					ICacheService.PREFIX_PRODUCTCUPGRADEPACKCACHE + cUpgradePackId);
			}
			catch(Exception e)
			 {
				 
				 log.info("从缓存中查询产品升级包信息失败[输入 cUpgradePackId=" + cUpgradePackId
							+ " 对应的值在缓存中未找到]!"+e.getLocalizedMessage());
				    result.setCode(3);
					result.setInfo("从缓存中查询设备信息失败[输入 devOnlyId=" + cUpgradePackId
							+ " 对应的值在缓存中未找到]!");
					cUpgradePackCache.clear();
					return cUpgradePackCache;
			 }
			if (fieldValueMap == null || fieldValueMap.size() < 1) {
				log.info("从缓存中查询产品升级包信息失败[输入 cUpgradePackId=" + cUpgradePackId
						+ " 对应的值在缓存中未找到]!");
				cUpgradePackCache.clear();
				return cUpgradePackCache;
			} else {
				// 将查询出的Hash值依次对应让如到DeviceCache对象中，并返回
				// 考虑可以写工具类
				cUpgradePackCache = ReflectUtils.setMap2Ojbect(fieldValueMap,
						cUpgradePackCache, CUpgradePackCache.class);
				log.info("查询产品升级包信息成功!");
				result.setCode(0);
				result.setInfo("查询产品升级包成功!");
				return cUpgradePackCache;
			}
		}
		cUpgradePackCache.clear();
		return cUpgradePackCache;
	}

	@Override
	public Result deleteProductCUpgradePackById(String maxCode, String minCode,
			String cUpgradePackId) {
		Long tempResult ;
		Result result = new Result();
		if(!minCode.equals(ICacheService.DELETEPRODUCTCUPGRADEPACKBYID)){
			log.info("删除产品升级包信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("删除产品升级包信息参数不正确[功能号minCode不正确]!");

		}else if (StringUtils.isBlank(cUpgradePackId)) {
			log.info("从缓存中删除产品升级包信息失败[需要删除的产品升级包信息输入值cUpgradePackId为空]!");
			result.setCode(1);
			result.setInfo("从缓存中删除产品升级包信息失败[需要删除的产品升级包信息输入值cUpgradePackId为空]!");

		} else {
			try{
				tempResult= RedisUtil.getInstance().delete(
					ICacheService.PREFIX_PRODUCTCUPGRADEPACKCACHE + cUpgradePackId);
			}
			catch(Exception e)
			{
				log.info("从缓存中删除设备信息失败!"+e.getMessage());
				result.setCode(3);
				result.setInfo("从缓存中删除设备信息失败!"+e.getMessage());
				tempResult=-1l;
				return result;
			}

			if (tempResult >= 0) {
				result.setCode(0);
				result.setInfo("从缓存中删除产品升级包信息成功");
			}

			log.info("删除返回为" + tempResult);
		}
		return result;
	}

}
 