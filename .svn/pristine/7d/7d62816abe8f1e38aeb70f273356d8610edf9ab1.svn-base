package com.vrv.cems.service.cache.service.impl; 

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.vrv.cems.service.base.bean.cache.Result;
 import com.vrv.cems.service.base.bean.cache.SensitiveCache;
import com.vrv.cems.service.base.interfaces.ICacheService;
import com.vrv.cems.service.cache.bean.SensitiveBean;
import com.vrv.cems.service.cache.service.SensitiveService;
import com.vrv.cems.service.cache.service.SensitiveService;
import com.vrv.cems.service.cache.util.RedisUtil;
import com.vrv.cems.service.cache.util.ReflectUtils;

/** 
 *   <B>说       明</B>:敏感规则库信息Service实现类(默认)
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年10月28日 下午8:34:24 
 */
public class SensitiveServiceImpl implements SensitiveService {

	@Override
	public Result saveSensitive(String maxCode, String minCode,
			SensitiveCache sensitiveCache) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.SAVESENSITIVE)){
			log.info("保存敏感规则库信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("保存敏感规则库信息参数不正确[功能号minCode不正确]!");
		} else if (sensitiveCache == null) {
			log.info("保存敏感规则库信息到缓存失败[敏感规则库信息为空]!");
			result.setCode(1);
			result.setInfo("保存敏感规则库信息到缓存失败[敏感规则库信息为空]!");
		} else {
			// 后期考虑反射实现....
			// 新建一个实体类
			SensitiveBean sensitiveBean = new SensitiveBean();
			try {
				// 把cache类里的数据传给实体类
				BeanUtils.copyProperties(sensitiveBean, sensitiveCache);
				// 用反射得到实体类的属性值
				Map<String, String> fieldValueMap = ReflectUtils.setObject2Map(
						sensitiveBean, SensitiveBean.class);
				// 把属性字段对应的值存入到Redis中
				for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
					try{
						RedisUtil.getInstance().hset(
								ICacheService.PREFIX_SENSITIVECACHE
										+ sensitiveCache.getName(),
								entry.getKey(), entry.getValue());
					} catch(Exception e) { 
						log.info("缓存saveSensitive异常!"+e.getMessage());
						result.setCode(3);
						result.setInfo("缓存saveSensitive异常![敏感规则库name"+sensitiveCache.getName()+"异常]!");
						return result;
					}
				}
				log.info("保存设备信息到缓存成功!");
				result.setCode(0);
				result.setInfo("保存设备信息到缓存成功!");
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
	public Result updateSensitive(String maxCode, String minCode,
			SensitiveCache sensitiveCache) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.UPDATESENSITIVE)){
			log.info("更新敏感规则库信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("更新敏感规则库信息参数不正确[功能号minCode不正确]!");

		} else if (sensitiveCache == null) {
			log.info("更新敏感规则库信息到缓存失败[敏感规则库信息为空]!");
			result.setCode(1);
			result.setInfo("更新敏感规则库信息到缓存失败[敏感规则库信息为空]!");
		} else {
			// 新建一个实体类
			SensitiveBean sensitiveBean = new SensitiveBean();
			try {
				// 把cache类里的数据传给实体类
				BeanUtils.copyProperties(sensitiveBean, sensitiveCache);
				// 用反射得到实体类的属性值
				Map<String, String> fieldValueMap = ReflectUtils.setObject2Map(
						sensitiveBean, SensitiveBean.class);
				// 把属性字段对应的值存入到Redis中
				for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
					try{
					RedisUtil.getInstance().hset(
							ICacheService.PREFIX_SENSITIVECACHE
									+ sensitiveCache.getName(),
							entry.getKey(), entry.getValue());
					} catch(Exception e) { 
						log.info("缓存updateSensitive异常!"+e.getMessage());
						result.setCode(3);
						result.setInfo("缓存updateSensitive异常![敏感规则库name"+sensitiveCache.getName()+"异常]!");
						return result;
					}
				}
				log.info("更新敏感规则库信息到缓存成功!");
				result.setCode(0);
				result.setInfo("更新敏感规则库信息到缓存成功!");
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
	public SensitiveCache querySensitive(String maxCode, String minCode,
			String fileName) {
		Result result = new Result();
		SensitiveCache sensitiveCache = new SensitiveCache();
		if(!minCode.equals(ICacheService.QUERYSENSITIVE)){
			log.info("根据fileName查询敏感规则库信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("根据fileName查询敏感规则库信息参数不正确[功能号minCode不正确]!");

		} else if (StringUtils.isBlank(fileName)) {
			log.info("从缓存中查询敏感规则库信息失败[输入 fileName=" + fileName + " 为空]!");
			result.setCode(2);
			result.setInfo("从缓存中查询敏感规则库信息失败[敏感规则库文件名fileName为空]!");
		} else if (!StringUtils.isBlank(fileName)) {
			Map<String, String> fieldValueMap = new HashMap<String, String>();
			// 从缓存中取出的所有属性值给map
			try{
			 fieldValueMap = RedisUtil.getInstance().hgetAll(
					ICacheService.PREFIX_SENSITIVECACHE + fileName);
			} catch(Exception e) {
				 log.info("从缓存中查询敏感规则库信息失败[输入 fileName=" + fileName
							+ " 对应的值在缓存中未找到]!"+e.getLocalizedMessage());
				    result.setCode(3);
					result.setInfo("从缓存中查询敏感规则库信息失败[输入 fileName=" + fileName
							+ " 对应的值在缓存中未找到]!");
					sensitiveCache.clear();
					return sensitiveCache;
			}
			if (fieldValueMap == null || fieldValueMap.size() < 1) {
				log.info("从缓存中查询敏感规则库信息失败[输入 fileName=" + fileName
						+ " 对应的值在缓存中未找到]!");
				sensitiveCache.clear();
				return sensitiveCache;
			} else {
				// 将查询出的Hash值依次对应让如到DeviceCache对象中，并返回
				// 考虑可以写工具类
				sensitiveCache = ReflectUtils.setMap2Ojbect(fieldValueMap,
						sensitiveCache, SensitiveCache.class);
				log.info("从缓存取得的敏感规则库信息为"+sensitiveCache.toString());
				log.info("从缓存中查询设备信息成功");
				result.setCode(0);
				result.setInfo("从缓存中查询设备信息成功!");
				return sensitiveCache;
			}
		}
		sensitiveCache.clear();
		return sensitiveCache;
	}

	@Override
	public String querySensitiveByField(String maxCode, String minCode,
			String fileName, String fieldKey) {
		String fieldValue = "";
		Result result = new Result();	
		if(!minCode.equals(ICacheService.QUERYSENSITIVEBYFIELD)){
			log.info("根据fileName查询敏感规则库信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("根据fileName查询敏感规则库信息参数不正确[功能号minCode不正确]!");
		} else if (StringUtils.isBlank(fileName)) {
			log.info("从缓存中查询敏感规则库信息失败[输入 fileName=" + fileName + " 为空]!");
			result.setCode(1);
			result.setInfo("保存敏感规则库信息到缓存失败[敏感规则库fileName为空]!");
		} else if (!StringUtils.isBlank(fieldKey)) {
			Map<String, String> fieldValueMap = new HashMap<String, String>();
			try{
				fieldValueMap = RedisUtil.getInstance().hgetAll(
					ICacheService.PREFIX_SENSITIVECACHE + fileName);
				if(fieldValueMap != null && fieldValueMap.size()>0){
					// 将查询出的Hash值依次对应让如到SensitiveCache对象中，并返回
					// 考虑可以写工具类
//					sensitiveCache = ReflectUtils.setMap2Ojbect(fieldValueMap,
//							sensitiveCache, SensitiveCache.class);
					fieldValue = fieldValueMap.get(fieldKey);
					log.info("从缓存中查询敏感规则库信息成功[输入 fieldKey=" + fieldValue + " 正确]");
					return fieldValue;
				}else {
					log.info("从缓存中查询敏感规则库信息失败[输入 fieldKey=" + fieldValue
							+ " 对应的值在缓存中未找到]!");
					fieldValue = "";
					return fieldValue;
				}
			}catch(Exception e){
				log.info("查询敏感规则库信息到缓存失败["+e.getMessage()+"]");
				result.setCode(3);
				result.setInfo("查询敏感规则库信息到缓存失败["+e.getMessage()+"]");
				fieldValue = "";
				return fieldValue;
			}
		}
		return fieldValue;
	}

	@Override
	public Result deleteSensitive(String maxCode, String minCode,
			String fileName) {
		// TODO 自动生成的方法存根
		Long tempResult = null;
		Result result = new Result();
		if(!minCode.equals(ICacheService.DELETESENSITIVE)){
			log.info("删除敏感规则库信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("删除敏感规则库信息参数不正确[功能号minCode不正确]!");
		}
		if(fileName != null && !"".equals(fileName)){
			SensitiveCache sensitiveCache = querySensitive(maxCode, minCode, fileName);
			if (sensitiveCache == null) {
				log.info("从缓存中删除敏感规则库信息失败[需要删除的敏感规则库信息输入值为空]!]!");
				result.setCode(1);
				result.setInfo("从缓存中删除敏感规则库信息失败[需要删除的敏感规则库信息输入值为空]!");
			} else {
				try{
					tempResult= RedisUtil.getInstance().delete(
							ICacheService.PREFIX_SENSITIVECACHE + fileName);
					} catch(Exception e) {
						log.info("从缓存中删除敏感规则库信息失败["+e.getMessage()+"]!");
						result.setCode(3);
						result.setInfo("从缓存中删除敏感规则库信息失败["+e.getMessage()+"]!");
						tempResult=-1l;
					}
					if (tempResult >= 0) {
						result.setCode(0);
						result.setInfo("从缓存中删除敏感规则库信息成功!");
					}
					log.info("从缓存中删除敏感规则库信息返回为" + tempResult);
			}
		} else if (StringUtils.isBlank(fileName)) {
			log.info("从缓存中删除设备信息失败[需要删除的敏感规则库信息输入值fileName 为空]!");
			result.setCode(2);
			result.setInfo("从缓存中删除设备信息失败[需要删除的敏感规则库信息输入值fileName为空]!");
		}
		return result;
	}


}
 