package com.vrv.cems.service.cache.service.impl; 

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.vrv.cems.service.base.bean.cache.CInstallPackCache;
import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.base.bean.cache.UserOnlineCache;
import com.vrv.cems.service.base.interfaces.ICacheService;
import com.vrv.cems.service.cache.bean.CInstallPackBean;
import com.vrv.cems.service.cache.service.ProductCInstallPackService;
import com.vrv.cems.service.cache.util.RedisUtil;
import com.vrv.cems.service.cache.util.ReflectUtils;

/** 
 *   <B>说       明</B>:产品安装包信息Service实现类(默认)
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午9:57:59 
 */
public class ProductCInstallPackServiceImpl implements
		ProductCInstallPackService {

	@Override
	public Result saveProductCInstallPack(String maxCode, String minCode,
			CInstallPackCache cInstallPackCache) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.SAVEPRODUCTCINSTALLPACK)){
			log.info("保存产品安装包信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("保存产品安装包信息参数不正确[功能号minCode不正确]!");

		}else if (cInstallPackCache == null) {
			log.info("保存产品安装包信息到缓存失败[产品安装包信息为空]!");
			result.setCode(1);
			result.setInfo("保存产品安装包信息到缓存失败[产品安装包信息为空]!");
		} else if (StringUtils.isBlank(cInstallPackCache.getCInstallPackId())) {
			log.info("保存产品安装包信息到缓存失败[用户cInstallPackId为空]!");
			result.setCode(2);
			result.setInfo("保存产品安装包信息到缓存失败[用户cInstallPackId为空]!");
		} else {
			// 后期考虑反射实现....
			// 新建一个实体类
			CInstallPackBean cInstallPackBean = new CInstallPackBean();

			try {
				// 把cache类里的数据传给实体类
				BeanUtils.copyProperties(cInstallPackBean, cInstallPackCache);
				// 用反射得到实体类的属性值
				Map<String, String> fieldValueMap = ReflectUtils.setObject2Map(
						cInstallPackBean, CInstallPackBean.class);
				// 把属性字段对应的值存入到Redis中
				for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
					try{
					RedisUtil.getInstance().hset(
							ICacheService.PREFIX_PRODUCTCINSTALLPACKCACHE
									+ cInstallPackCache.getCInstallPackId(),
							entry.getKey(), entry.getValue());
					}
					catch(Exception e)
					{ 
						
						log.info("缓存saveProductCInstallPack异常!"+e.getMessage());
						result.setCode(3);
						result.setInfo("缓存saveProductCInstallPack异常![产品cInstallPackId"+cInstallPackCache.getCInstallPackId()+"异常]!");
						return result;
					}
				}
				log.info("保存产品安装包信息到缓存成功!");
				result.setCode(0);
				result.setInfo("保存产品安装包信息到缓存成功!");
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
	public Result updateProductCInstallPack(String maxCode, String minCode,
			CInstallPackCache cInstallPackCache) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.UPDATEPRODUCTCINSTALLPACK)){
			log.info("更新产品安装包信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("更新产品安装包信息参数不正确[功能号minCode不正确]!");

		}else if (cInstallPackCache == null) {
			log.info("更新产品安装包信息到缓存失败[产品安装包信息为空]!");
			result.setCode(1);
			result.setInfo("更新产品安装包信息到缓存失败[产品安装包信息为空]!");
		} else if (StringUtils.isBlank(cInstallPackCache.getCInstallPackId())) {
			log.info("保存产品安装包信息到缓存失败[用户cInstallPackId为空]!");
			result.setCode(2);
			result.setInfo("更新产品安装包信息到缓存失败[用户cInstallPackId为空]!");
		} else {
			// 新建一个实体类
			CInstallPackBean cInstallPackBean = new CInstallPackBean();

			try {
				// 把cache类里的数据传给实体类
				BeanUtils.copyProperties(cInstallPackBean, cInstallPackCache);
				// 用反射得到实体类的属性值
				Map<String, String> fieldValueMap = ReflectUtils.setObject2Map(
						cInstallPackBean, CInstallPackBean.class);
				// 把属性字段对应的值存入到Redis中
				for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
					try{
					RedisUtil.getInstance().hset(
							ICacheService.PREFIX_PRODUCTCINSTALLPACKCACHE
									+ cInstallPackCache.getCInstallPackId(),
							entry.getKey(), entry.getValue());
					}
					catch(Exception e)
					{ 
						
						log.info("缓存updateProductCInstallPack异常!"+e.getMessage());
						result.setCode(3);
						result.setInfo("缓存updateProductCInstallPack异常![产品cInstallPackId"+cInstallPackCache.getCInstallPackId()+"异常]!");
						return result;
					}
				}
				log.info("更新产品安装包信息到缓存成功!");
				result.setCode(0);
				result.setInfo("更新产品安装包信息到缓存成功!");
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
	public Result updateProductCInstallPackByField(String maxCode,
			String minCode, String cInstallPackId,
			Map<String, String> fieldValueMap) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.UPDATEPRODUCTCINSTALLPACKBYFIELD)){
			log.info("根据map更新产品安装包信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("根据map更新产品安装包信息参数不正确[功能号minCode不正确]!");

		}else if (fieldValueMap == null && fieldValueMap.size() < 1) {
			log.info("更新产品安装包信息到缓存失败[需要更新的属性名及属性值为空]!");
			result.setCode(1);
			result.setInfo("更新产品安装包信息到缓存失败[需要更新的属性名及属性值为空]!");

		} else if (StringUtils.isBlank(cInstallPackId)) {
			log.info("更新产品安装包信息到缓存失败[用户cInstallPackId为空]!");
			result.setCode(2);
			result.setInfo("更新产品安装包信息到缓存失败[用户cInstallPackId为空]!");
		} else {
			for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
				try{
				RedisUtil.getInstance().hset(
						ICacheService.PREFIX_PRODUCTCINSTALLPACKCACHE + cInstallPackId,
						entry.getKey(), entry.getValue());
				}
				catch(Exception e)
				{ 
					
					log.info("缓存updateProductCInstallPackByField异常!"+e.getMessage());
					result.setCode(3);
					result.setInfo("缓存updateProductCInstallPackByField异常![产品cInstallPackId"+cInstallPackId+"异常]!");
					return result;
				}
			}
			log.info("更新产品安装包信息到缓存成功!");
			result.setCode(0);
			result.setInfo("更新产品安装包信息到缓存成功!");
		}
		return result;
	}

	@Override
	public CInstallPackCache queryProductCInstallPackById(String maxCode,
			String minCode, String cInstallPackId) {
		Result result = new Result();
		List<CInstallPackCache> cInstallPackCacheList=new ArrayList<CInstallPackCache>();
		CInstallPackCache cInstallPackCache = new CInstallPackCache();

		if(!minCode.equals(ICacheService.QUERYPRODUCTCINSTALLPACKBYID)){
			log.info("查询产品安装包信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("查询产品安装包信息参数不正确[功能号minCode不正确]!");

		}else if (StringUtils.isBlank(cInstallPackId)) {
			log.info("从缓存中查询产品安装包信息失败[输入 cInstallPackId=" + cInstallPackId + " 为空]!");
			result.setCode(2);
			result.setInfo("从缓存中查询产品安装包信息失败[用户cInstallPackId为空]!");
			return null;
		} else if (!StringUtils.isBlank(cInstallPackId)) {
			Map<String, String> fieldValueMap = new HashMap<String, String>();
			// 从缓存中取出的所有属性值给map
			try{
			fieldValueMap = RedisUtil.getInstance().hgetAll(
					ICacheService.PREFIX_PRODUCTCINSTALLPACKCACHE + cInstallPackId);
			}
			catch(Exception e)
			 {
				 
				 log.info("从缓存中查询产品安装包信息失败[输入 cInstallPackId=" + cInstallPackId
							+ " 对应的值在缓存中未找到]!"+e.getLocalizedMessage());
				    result.setCode(3);
					result.setInfo("从缓存中查询产品安装包信息失败[输入 cInstallPackId=" + cInstallPackId
							+ " 对应的值在缓存中未找到]!");
					cInstallPackCache.clear();
					return cInstallPackCache;
			 }
			if (fieldValueMap == null || fieldValueMap.size() < 1) {
				log.info("从缓存中查询产品安装包信息失败[输入 cInstallPackId=" + cInstallPackId
						+ " 对应的值在缓存中未找到]!");
				cInstallPackCache.clear();
				return cInstallPackCache;
			} else {
				// 将查询出的Hash值依次对应让如到DeviceCache对象中，并返回
				// 考虑可以写工具类
				cInstallPackCache = ReflectUtils.setMap2Ojbect(fieldValueMap,
						cInstallPackCache, CInstallPackCache.class);
				log.info("从缓存中查询产品安装包信息成功");
				result.setCode(0);
				result.setInfo("从缓存中查询产品安装包信息成功!");
				return cInstallPackCache;
			}
		}
		cInstallPackCache.clear();
		return cInstallPackCache;
	}

	@Override
	public Result deleteProductCInstallPackById(String maxCode, String minCode,
			String cInstallPackId) {
		Result result = new Result();
		Long tempResult;
		if(!minCode.equals(ICacheService.DELETEPRODUCTCINSTALLPACKBYID)){
			log.info("删除产品安装包信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("删除产品安装包信息参数不正确[功能号minCode不正确]!");

		}else if (StringUtils.isBlank(cInstallPackId)) {
			log.info("从缓存中删除产品安装包信息失败[需要删除的产品安装包信息输入值cInstallPackId为空]!");
			result.setCode(1);
			result.setInfo("从缓存中删除产品安装包信息失败[需要删除的产品安装包信息输入值cInstallPackId为空]!");

		} else {
			try{
			 tempResult = RedisUtil.getInstance().delete(
					ICacheService.PREFIX_PRODUCTCINSTALLPACKCACHE + cInstallPackId);
			}
			catch(Exception e)
			{
				log.info("从缓存中删除产品安装包信息失败!"+e.getMessage());
				result.setCode(3);
				result.setInfo("从缓存中删除产品安装包信息失败!"+e.getMessage());
				tempResult=-1l;
				return result;
			}
			if (tempResult >= 0) {
				result.setCode(0);
				result.setInfo("从缓存中删除产品安装包信息成功");
			}

			log.info("删除返回为" + tempResult);
		}
		return result;
	}
	}


 