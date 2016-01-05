package com.vrv.cems.service.cache.service.impl; 

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.sys.common.util.json.JsonUtils;
import com.vrv.cems.service.base.bean.cache.DeviceProduct;
import com.vrv.cems.service.base.bean.cache.ProductInfoNew;
import com.vrv.cems.service.base.bean.cache.ProductInfoOld;
import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.base.interfaces.ICacheService;
import com.vrv.cems.service.cache.bean.DeviceProductBean;
import com.vrv.cems.service.cache.bean.ProdInfoNewBean;
import com.vrv.cems.service.cache.bean.ProdInfoOldBean;
import com.vrv.cems.service.cache.service.DeviceInsProNewService;
import com.vrv.cems.service.cache.util.JSONUtil;
import com.vrv.cems.service.cache.util.RedisUtil;
import com.vrv.cems.service.cache.util.ReflectUtils;

/** 
 *   <B>说       明</B>:设备应安装产品信息Service实现类(默认);
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午9:49:20 
 */
public class DeviceInsProNewServiceImpl implements DeviceInsProNewService {

	@Override
	public Result saveDeviceInsProNew(String maxCode, String minCode,
			String devOnlyId, List<DeviceProduct> deviceProductList) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.SAVEDEVICEINSPRONEW))
		{
			log.info("保存设备应安装产品信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("保存设备应安装产品信息参数不正确[功能号minCode不正确]!");
			
		}
		else if (StringUtils.isBlank(devOnlyId)) {
					log.info("保存设备应安装产品信息到缓存失败[设备devOnlyId为空]!");
					result.setCode(2);
					result.setInfo("保存设备应安装产品信息到缓存失败[设备devOnlyId为空]!");
				}else{
				for (DeviceProduct deviceProduct : deviceProductList) {
					if (deviceProduct == null) {
						log.info("保存设备应安装产品信息到缓存失败[设备应安装产品信息为空]!");
						result.setCode(1);
						result.setInfo("保存设备应安装产品信息到缓存失败[设备应安装产品信息为空]!");
					} else {
						// 后期考虑反射实现....
						// 新建一个实体类
						DeviceProductBean deviceProductBean = new DeviceProductBean();
						List<ProdInfoNewBean> prodInfoNewBeanList =new ArrayList<ProdInfoNewBean>();
						for(ProductInfoNew prodInfoNew:deviceProduct.getProductInfoNewList())
						{   
							ProdInfoNewBean prodInfoNewBean= new ProdInfoNewBean();
							try {
								BeanUtils.copyProperties(prodInfoNewBean,prodInfoNew);
							} catch (IllegalAccessException e) {
								// TODO 自动生成的 catch 块
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								// TODO 自动生成的 catch 块
								e.printStackTrace();
							}
							prodInfoNewBeanList.add(prodInfoNewBean);
						}
						
						deviceProductBean.setProductInfoNew(prodInfoNewBeanList);
						deviceProductBean.setOsType(deviceProduct.getOsType());
						deviceProductBean.setProductType(deviceProduct.getProductType());
						
						
					    String value = JsonUtils.object2Json(deviceProductBean);
					
					    try{
						RedisUtil.getInstance().set(ICacheService.PREFIX_DEVICEINSPRONEWCACHE
								+ devOnlyId, value);
						log.info("保存设备安装产品信息到缓存成功!");
						result.setCode(0);
						result.setInfo("保存设备应安装产品信息到缓存成功!");
					    }
						catch(Exception e)
						{ 
							
							log.info("缓存saveDeviceInsProNew异常!"+e.getMessage());
							result.setCode(3);
							result.setInfo("缓存saveDeviceInsProNew异常![设备devOnlyId"+devOnlyId+"异常]!");
							return result;
						}
					
						
					}
				}
				}
				return result;
	}

	@Override
	public Result updateDeviceInsProNew(String maxCode, String minCode,
			String devOnlyId, List<DeviceProduct> deviceProductList) {
		Result result = new Result();
				if(!minCode.equals(ICacheService.UPDATEDEVICEINSPRONEW))
				{
					log.info("更新设备应安装产品信息参数不正确[功能号minCode不正确]!");
					result.setCode(4);
					result.setInfo("更新设备应安装产品信息参数不正确[功能号minCode不正确]!");
			
				}
				else if (StringUtils.isBlank(devOnlyId)) {
					log.info("更新设备应安装产品信息到缓存失败[设备devOnlyId为空]!");
					result.setCode(2);
					result.setInfo("更新设备应安装产品信息到缓存失败[设备devOnlyId为空]!");
				} else{
				for (DeviceProduct deviceProduct : deviceProductList) {
					if (deviceProduct == null) {
						log.info("更新设备应安装产品信息到缓存失败[设备应安装产品信息为空]!");
						result.setCode(1);
						result.setInfo("更新设备应安装产品信息到缓存失败[设备应安装产品信息为空]!");
					} else {
						// 后期考虑反射实现....
						// 新建一个实体类
						DeviceProductBean deviceProductBean = new DeviceProductBean();
						List<ProdInfoNewBean> prodInfoNewBeanList =new ArrayList<ProdInfoNewBean>();
						for(ProductInfoNew prodInfoNew:deviceProduct.getProductInfoNewList())
						{   
							ProdInfoNewBean prodInfoNewBean= new ProdInfoNewBean();
							try {
								BeanUtils.copyProperties(prodInfoNewBean,prodInfoNew);
							} catch (IllegalAccessException e) {
								// TODO 自动生成的 catch 块
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								// TODO 自动生成的 catch 块
								e.printStackTrace();
							}
							prodInfoNewBeanList.add(prodInfoNewBean);
						}
						
						deviceProductBean.setProductInfoNew(prodInfoNewBeanList);
						deviceProductBean.setOsType(deviceProduct.getOsType());
						deviceProductBean.setProductType(deviceProduct.getProductType());
						
						
					    String value = JsonUtils.object2Json(deviceProductBean);
					
					    try{
						RedisUtil.getInstance().set(ICacheService.PREFIX_DEVICEINSPRONEWCACHE
								+ devOnlyId, value);
						log.info("更新设备安装产品信息到缓存成功!");
						result.setCode(0);
						result.setInfo("更新设备应安装产品信息到缓存成功!");
					    }
						catch(Exception e)
						{ 
							
							log.info("缓存updateDeviceInsProNew异常!"+e.getMessage());
							result.setCode(3);
							result.setInfo("缓存updateDeviceInsProNew异常![设备devOnlyId"+devOnlyId+"异常]!");
							return result;
						}
						
					}
				}
				}
				return result;
	}

	@Override
	public List<DeviceProduct> queryDeviceInsProNewByDevOnlyId(String maxCode,
			String minCode, String devOnlyId) {
		Result result = new Result();
		List<DeviceProduct> deviceProductList =new ArrayList<DeviceProduct>();
		List<ProductInfoNew> productInfoNewList=new  ArrayList<ProductInfoNew>();
		String value ;
		if(!minCode.equals(ICacheService.QUERYDEVICEINSPRONEWBYDEVONLYID))
		{
			log.info("查询设备应安装产品信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("查询设备应安装产品信息参数不正确[功能号minCode不正确]!");
	
		}
		else if (StringUtils.isBlank(devOnlyId)) {
			log.info("查询设备应安装产品信息到缓存失败[设备devOnlyId为空]!");
			result.setCode(2);
			result.setInfo("查询设备应安装产品信息到缓存失败[设备devOnlyId为空]!");
		}else
		{	
			try{
			// 从缓存中取出的所有属性值给map
			value = RedisUtil.getInstance().get(
					ICacheService.PREFIX_DEVICEINSPRONEWCACHE
							+ devOnlyId);
			}
			catch(Exception e)
			{
				log.info("查询设备应安装产品信息到缓存失败["+e.getMessage()+"]");
				result.setCode(3);
				result.setInfo("查询设备应安装产品信息到缓存失败["+e.getMessage()+"]");
				return deviceProductList;
			}
			if (value== null||"".equals(value)) {
				log.info("从缓存中查询设备应安装产品信息失败[输入 devOnlyId="
						+ devOnlyId + " 对应的值在缓存中未找到]!");
				return deviceProductList;
			} else {
				// 将查询出的Hash值依次对应让如到deviceProduct对象中，并返回
				// 考虑可以写工具类
				DeviceProductBean deviceProdBean=new DeviceProductBean();
				DeviceProduct deviceProduct =new DeviceProduct();
			   deviceProdBean=(DeviceProductBean) JSONUtil.json2DevProdBean(value, ProdInfoNewBean.class);
					   
			   for(ProdInfoNewBean prodInfoNewBean:deviceProdBean.getProductInfoNew()){
				   ProductInfoNew prodNew=new ProductInfoNew();
					ReflectUtils.copyObjectProperties(prodNew, prodInfoNewBean, ProductInfoNew.class, ProdInfoNewBean.class);
					productInfoNewList.add(prodNew);
				}
			   deviceProduct.setProductInfoNewList(productInfoNewList);
			   deviceProduct.setOsType(deviceProdBean.getOsType());
			   deviceProduct.setProductType(deviceProdBean.getProductType());

			   deviceProductList.add(deviceProduct);
				log.info("从缓存中查询设备应安装产品信息成功[输入 devOnlyId="
						+ devOnlyId + " 正确]");
				result.setCode(0);
				result.setInfo("从缓存中查询设备应安装产品信息成功!");
			}
		}
		return deviceProductList;
	}

	@Override
	public Result deleteDeviceInsProNewByDevOnlyId(String maxCode,
			String minCode, String devOnlyId) {
		Result result = new Result();
		Long tempResult;
		if(!minCode.equals(ICacheService.DELETEDEVICEINSPRONEWBYDEVONLYID))
		{
			log.info("删除设备应安装产品信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("删除设备应安装产品信息参数不正确[功能号minCode不正确]!");
	
		}
		else if (StringUtils.isBlank(devOnlyId)) {
			log.info("从缓存中删除设备应安装产品信息失败[需要删除的设备应安装产品信息输入值devOnlyId为空]!");
			result.setCode(1);
			result.setInfo("从缓存中删除设备应安装产品信息失败[需要删除的设备应安装产品信息输入值devOnlyId为空]!");

		} else {
			try{
			 tempResult = RedisUtil.getInstance().delete(
					ICacheService.PREFIX_DEVICEINSPRONEWCACHE + devOnlyId);
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
				result.setInfo("从缓存中删除设备应安装产品信息成功");
			}

			log.info("删除返回为" + tempResult);
		}
		return result;
	}
	
	
	
}
 