package com.vrv.cems.service.cache.service.impl;

import gnu.crypto.pki.ext.Extension.Value;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.sys.common.util.json.JsonConfigEx;
import com.sys.common.util.json.JsonUtils;
import com.vrv.cems.service.base.bean.cache.DeviceProduct;
import com.vrv.cems.service.base.bean.cache.ProductInfoNew;
import com.vrv.cems.service.base.bean.cache.ProductInfoOld;
import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.base.interfaces.ICacheService;
import com.vrv.cems.service.cache.bean.DeviceProductBean;
import com.vrv.cems.service.cache.bean.ProdInfoNewBean;
import com.vrv.cems.service.cache.bean.ProdInfoOldBean;
import com.vrv.cems.service.cache.service.DeviceInsProOldService;
import com.vrv.cems.service.cache.util.JSONUtil;
import com.vrv.cems.service.cache.util.RedisUtil;
import com.vrv.cems.service.cache.util.ReflectUtils;

/**
 * <B>说 明</B>:设备已安装产品信息Service实现类(默认)
 * 
 * @author 作 者 名：glw<br/>
 *         E-mail ：linwu_gao@163.com
 * 
 * @version 版 本 号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午9:47:53
 */
public class DeviceInsProOldServiceImpl implements DeviceInsProOldService {

	@Override
	public Result saveDeviceInsProOld(String maxCode, String minCode,
			String devOnlyId, List<DeviceProduct> deviceProductList) {
		Result result = new Result();
		// TODO 自动生成的方法存根
		if(!minCode.equals(ICacheService.SAVEDEVICEINSPROOLD))
		{
			log.info("保存设备已安装产品信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("保存设备已安装产品信息参数不正确[功能号minCode不正确]!");
	
		}
		else if (StringUtils.isBlank(devOnlyId)) {
			log.info("保存设备已安装产品信息到缓存失败[设备devOnlyId为空]!");
			result.setCode(2);
			result.setInfo("保存设备已安装产品信息到缓存失败[设备devOnlyId为空]!");
		}else{
			for (DeviceProduct deviceProduct : deviceProductList) {
			if (deviceProduct == null) {
				log.info("保存设备已安装产品信息到缓存失败[设备已安装产品信息为空]!");
				result.setCode(1);
				result.setInfo("保存设备已安装产品信息到缓存失败[设备已安装产品信息为空]!");
			} else {
				// 后期考虑反射实现....
				// 新建一个实体类
				DeviceProductBean deviceProductBean = new DeviceProductBean();
				List<ProdInfoOldBean> prodInfoOldBeanList =new ArrayList<ProdInfoOldBean>();
				for(ProductInfoOld prodInfoOld:deviceProduct.getProductInfoOldList())
				{   
					ProdInfoOldBean prodInfoOldBean= new ProdInfoOldBean();
					try {
						BeanUtils.copyProperties(prodInfoOldBean,prodInfoOld);
					} catch (IllegalAccessException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					prodInfoOldBeanList.add(prodInfoOldBean);
				}
				
				deviceProductBean.setProductInfoOld(prodInfoOldBeanList);
				deviceProductBean.setOsType(deviceProduct.getOsType());
				deviceProductBean.setProductType(deviceProduct.getProductType());
				
				
			    String value = JsonUtils.object2Json(deviceProductBean);
			
			    try{
				RedisUtil.getInstance().set(ICacheService.PREFIX_DEVICEINSPROOLDCACHE
						+ devOnlyId, value);
				log.info("保存设备已安装产品信息到缓存成功!");
				result.setCode(0);
				result.setInfo("保存设备已安装产品信息到缓存成功!");
			    }
				catch(Exception e)
				{ 
					
					log.info("缓存saveDeviceInsProOld异常!"+e.getMessage());
					result.setCode(3);
					result.setInfo("缓存saveDeviceInsProOld异常![设备devOnlyId"+devOnlyId+"异常]!");
					return result;
				}
				
			}
		}
		}
		return result;
	}

	@Override
	public Result updateDeviceInsProOld(String maxCode, String minCode,
			String devOnlyId, List<DeviceProduct> deviceProductList) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.UPDATEDEVICEINSPROOLD))
		{
			log.info("更新设备已安装产品信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("更新设备已安装产品信息参数不正确[功能号minCode不正确]!");
	
		}
		else if (StringUtils.isBlank(devOnlyId)) {
			log.info("更新设备已安装产品信息到缓存失败[设备devOnlyId为空]!");
			result.setCode(2);
			result.setInfo("更新设备已安装产品信息到缓存失败[设备devOnlyId为空]!");
		}else {
		for (DeviceProduct deviceProduct : deviceProductList) {
			if (deviceProduct == null) {
				log.info("更新设备已安装产品信息到缓存失败[设备已安装产品信息为空]!");
				result.setCode(1);
				result.setInfo("更新设备已安装产品信息到缓存失败[设备已安装产品信息为空]!");
			} else {

				// 后期考虑反射实现....
				// 新建一个实体类
				DeviceProductBean deviceProductBean = new DeviceProductBean();
				List<ProdInfoOldBean> prodInfoOldBeanList =new ArrayList<ProdInfoOldBean>();
				for(ProductInfoOld prodInfoOld:deviceProduct.getProductInfoOldList())
				{   
					ProdInfoOldBean prodInfoOldBean= new ProdInfoOldBean();
					try {
						BeanUtils.copyProperties(prodInfoOldBean,prodInfoOld);
					} catch (IllegalAccessException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					prodInfoOldBeanList.add(prodInfoOldBean);
				}
				
				deviceProductBean.setProductInfoOld(prodInfoOldBeanList);
				deviceProductBean.setOsType(deviceProduct.getOsType());
				deviceProductBean.setProductType(deviceProduct.getProductType());
				
				
			    String value = JsonUtils.object2Json(deviceProductBean);
			
			    try{
				RedisUtil.getInstance().set(ICacheService.PREFIX_DEVICEINSPROOLDCACHE
						+ devOnlyId, value);
				log.info("保存设备已安装产品信息到缓存成功!");
				result.setCode(0);
				result.setInfo("保存设备已安装产品信息到缓存成功!");
			    }
				catch(Exception e)
				{ 
					
					log.info("缓存updateDeviceInsProOld异常!"+e.getMessage());
					result.setCode(3);
					result.setInfo("缓存updateDeviceInsProOld异常![设备devOnlyId"+devOnlyId+"异常]!");
					return result;
				}
			
			}
		}
	}
		return result;
	}

	@Override
	public List<DeviceProduct> queryDeviceInsProOldByDevOnlyId(String maxCode,
			String minCode, String devOnlyId) {
		Result result = new Result();
		List<DeviceProduct> deviceProductList =new ArrayList<DeviceProduct>();
		List<ProductInfoOld> productInfoOldList =new ArrayList<ProductInfoOld>();
		String value ;
		if(!minCode.equals(ICacheService.QUERYDEVICEINSPROOLDBYDEVONLYID))
		{
			log.info("查询设备已安装产品信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("查询设备已安装产品信息参数不正确[功能号minCode不正确]!");
	
		}
		else if (StringUtils.isBlank(devOnlyId)) {
			log.info("查询设备已安装产品信息到缓存失败[设备devOnlyId为空]!");
			result.setCode(2);
			result.setInfo("查询设备已安装产品信息到缓存失败[设备devOnlyId为空]!");
		}else
		{
			try{
			// 从缓存中取出的所有属性值给map
			 value = RedisUtil.getInstance().get(
					ICacheService.PREFIX_DEVICEINSPROOLDCACHE
							+ devOnlyId);
			}
			catch(Exception e)
			{
				log.info("查询设备已安装产品信息到缓存失败["+e.getMessage()+"]");
				result.setCode(3);
				result.setInfo("查询设备已安装产品信息到缓存失败["+e.getMessage()+"]");
				
				return deviceProductList;
			}
			if (value== null||"".equals(value)) {
				log.info("从缓存中查询设备已安装产品信息失败[输入 devOnlyId="
						+ devOnlyId + " 对应的值在缓存中未找到]!");
				return deviceProductList;
			} else {
				// 将查询出的Hash值依次对应让如到deviceProduct对象中，并返回
				// 考虑可以写工具类
				DeviceProductBean deviceProdBean=new DeviceProductBean();
				DeviceProduct deviceProduct =new DeviceProduct();
			    deviceProdBean=(DeviceProductBean) JSONUtil.json2DevProdBean(value, ProdInfoOldBean.class);
					   
			   for(ProdInfoOldBean prodOldBean:deviceProdBean.getProductInfoOld()){
					ProductInfoOld prodOld=new ProductInfoOld();
					ReflectUtils.copyObjectProperties(prodOld, prodOldBean, ProductInfoOld.class, ProdInfoOldBean.class);
					productInfoOldList.add(prodOld);
				}
			   deviceProduct.setProductInfoOldList(productInfoOldList);
			   deviceProduct.setOsType(deviceProdBean.getOsType());
			   deviceProduct.setProductType(deviceProdBean.getProductType());

			   deviceProductList.add(deviceProduct);
				log.info("从缓存中查询设备已安装产品信息成功[输入 devOnlyId="
						+ devOnlyId + " 正确]");
				result.setCode(0);
				result.setInfo("从缓存中查询设备已安装产品信息成功!");
				
			}
		}
		return deviceProductList;
	}

	@Override
	public Result deleteDeviceInsProOldByDevOnlyId(String maxCode,
			String minCode, String devOnlyId) {
		Result result = new Result();
		Long tempResult ;
		if(!minCode.equals(ICacheService.DELETEDEVICEINSPROOLDBYDEVONLYID))
		{
			log.info("删除设备已安装产品信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("删除设备已安装产品信息参数不正确[功能号minCode不正确]!");
	
		}
		else if (StringUtils.isBlank(devOnlyId)) {
			log.info("从缓存中删除设备已安装产品信息失败[需要删除的设备已安装产品信息输入值devOnlyId为空]!");
			result.setCode(1);
			result.setInfo("从缓存中删除设备已安装产品信息失败[需要删除的设备已安装产品信息输入值devOnlyId为空]!");

		} else {
			try{
			 tempResult = RedisUtil.getInstance().delete(
					ICacheService.PREFIX_DEVICEINSPROOLDCACHE + devOnlyId);
			}
			catch(Exception e)
			{
				log.info("从缓存中删除设备已安装产品失败["+e.getMessage()+"]!");
				result.setCode(3);
				result.setInfo("从缓存中删除设备已安装产品失败["+e.getMessage()+"]!");
				tempResult=-1l;
				return result;
			}
			if (tempResult >= 0) {
				result.setCode(0);
				result.setInfo("从缓存中删除设备已安装产品信息成功");
			}

			log.info("删除返回为" + tempResult);
		}
		return result;

	}

}
