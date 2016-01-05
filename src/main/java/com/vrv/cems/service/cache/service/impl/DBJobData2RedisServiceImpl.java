package com.vrv.cems.service.cache.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.vrv.cems.service.base.bean.cache.CInstallPackCache;
import com.vrv.cems.service.base.bean.cache.CUpgradePackCache;
import com.vrv.cems.service.base.bean.cache.DeviceCache;
import com.vrv.cems.service.base.bean.cache.DeviceProduct;
import com.vrv.cems.service.base.bean.cache.PolicyCache;
import com.vrv.cems.service.base.bean.cache.ProductInfoOld;
import com.vrv.cems.service.base.bean.cache.UserCache;
import com.vrv.cems.service.base.interfaces.ICacheService;
import com.vrv.cems.service.cache.bean.CInstallPackBean;
import com.vrv.cems.service.cache.bean.CUpgradePackBean;
import com.vrv.cems.service.cache.bean.DBDeviceProdBean;
import com.vrv.cems.service.cache.bean.DeviceBean;
import com.vrv.cems.service.cache.bean.PolicyBean;
import com.vrv.cems.service.cache.bean.UserBean;
import com.vrv.cems.service.cache.core.Services;
import com.vrv.cems.service.cache.service.DBJobData2RedisService;
import com.vrv.cems.service.cache.util.ReflectUtils;


public class DBJobData2RedisServiceImpl implements DBJobData2RedisService{

	@Override
	public void putData2DeviceCache(List<DeviceBean> deviceBeanList) {
		// TODO 自动生成的方法存根
		for(DeviceBean deviceBean:deviceBeanList)
		{
			if(StringUtils.isNotBlank(deviceBean.getDevOnlyId())){
			DeviceCache deviceCache=new DeviceCache();
			ReflectUtils.copyObjectProperties(deviceCache, deviceBean, DeviceCache.class, DeviceBean.class);
			Services.deviceService.saveDevice(ICacheService.SERVICE_CODE,ICacheService.SAVEDEVICE,deviceCache);
			}
		}
	}

	@Override
	public void putData2UserCache(List<UserBean> userBeanList) {
		for(UserBean userBean:userBeanList)
		{
			 UserCache userCache=new UserCache();
			 String account=userBean.getAccount();
			 String userOnlyId=userBean.getUserOnlyId();
			 if(StringUtils.isNotBlank(userOnlyId)){
			 ReflectUtils.copyObjectProperties(userCache, userBean, UserCache.class, UserBean.class);		
				Services.userService.saveUser(ICacheService.SERVICE_CODE,ICacheService.SAVEUSER,userCache);
				 if(StringUtils.isNotBlank(account)){
					    Services.commonService.saveAccount2UserOnlyId(ICacheService.SERVICE_CODE,ICacheService.SAVEACCOUNT2USERONLYID, account, userOnlyId);
					 }
			 }
		}
	}

	@Override
	public void putData2CInstallPackCache(
			List<CInstallPackBean> cInstallPackBeanList) {
		for(CInstallPackBean cInstallPackBean:cInstallPackBeanList)
		{
			CInstallPackCache cInstallPackCache=new CInstallPackCache();
			
			ReflectUtils.copyObjectProperties(cInstallPackCache, cInstallPackBean, CInstallPackCache.class, CInstallPackBean.class);
			
			if(StringUtils.isNotBlank(cInstallPackCache.getCInstallPackId())){
				Services.productCInstallPackService.saveProductCInstallPack(ICacheService.SERVICE_CODE,ICacheService.SAVEPRODUCTCINSTALLPACK,cInstallPackCache);
			}
		}
		
	}

	@Override
	public void putData2PolicyCache(List<PolicyBean> policyBeanList) {
		// TODO 自动生成的方法存根
		for(PolicyBean policyBean:policyBeanList)
		{
			PolicyCache policyCache=new PolicyCache();
			
			ReflectUtils.copyObjectProperties(policyCache, policyBean, PolicyCache.class, PolicyBean.class);
			
			Services.policyService.savePolicy(ICacheService.SERVICE_CODE,ICacheService.SAVEPOLICY,policyCache);
		}
		
	}

	@Override
	public void putData2DevInsProOld(List<DBDeviceProdBean> newProdList) {
		
		List<DeviceProduct> deviceProductList = new ArrayList<DeviceProduct>();
		DeviceProduct deviceProduct = new DeviceProduct();
		List<ProductInfoOld> productInfoOldList = new ArrayList<ProductInfoOld>();
		String devOnlyId="";
		for(DBDeviceProdBean newProdBean:newProdList)
		{
			devOnlyId=newProdBean.getDevOnlyId();
			ProductInfoOld productInfoOld = new ProductInfoOld();
			deviceProduct.setProductType(newProdBean.getProductType());
			
			if(StringUtils.isNotBlank(newProdBean.getProductBaseVersion()))
			{
				productInfoOld.setType(0);
				productInfoOld.setVersion(newProdBean.getProductBaseVersion());
			}

			if(StringUtils.isNotBlank(newProdBean.getProductCustomVersion()))
			{
				productInfoOld.setType(1);
				productInfoOld.setVersion(newProdBean.getProductCustomVersion());
			}
			productInfoOld.setInstallTime(newProdBean.getInstallTime());
			productInfoOld.setProductName(newProdBean.getProductName());
			productInfoOld.setProductSign(newProdBean.getProductSign());	
			productInfoOldList.add(productInfoOld);			
			deviceProduct.setProductInfoOldList(productInfoOldList);	
		}
			deviceProductList.add(deviceProduct);
			if(StringUtils.isNotBlank(devOnlyId)){
				Services.deviceInsProOldService.saveDeviceInsProOld(ICacheService.SERVICE_CODE,ICacheService.SAVEDEVICEINSPROOLD, devOnlyId, deviceProductList);
			}
	}

	@Override
	public void putData2ProductCUpgradePack(
			List<CUpgradePackBean> cUpgradeBeanList) {
		
		for(CUpgradePackBean cUpgaradeBean:cUpgradeBeanList)
		{
			CUpgradePackCache cUpgradeCache=new CUpgradePackCache();
			
			ReflectUtils.copyObjectProperties(cUpgradeCache, cUpgaradeBean, CUpgradePackCache.class, CUpgradePackBean.class);
			
			if(StringUtils.isNotBlank(cUpgradeCache.getCUpgradePackId())){
				Services.productCUpgradePackService.saveProductCUpgradePack(ICacheService.SERVICE_CODE,ICacheService.SAVEPRODUCTCUPGRADEPACK,cUpgradeCache);
			}
		}
		
	}

	/*
	* Title: delData2PolicyCache
	*Description: 
	* @param policyBeanDelList 
	* @see com.vrv.cems.service.cache.service.DBJobData2RedisService#delData2PolicyCache(java.util.List) 
	*/
	@Override
	public void delData2PolicyCache(List<PolicyBean> policyBeanDelList) {
		for(PolicyBean delPolicyBean:policyBeanDelList)
		{
			String policyId=delPolicyBean.getId();
			Services.policyService.deletePolicyByPolicyId(ICacheService.SERVICE_CODE, ICacheService.DELETEPOLICYBYPOLICYID, policyId);
		}
		
	}

}
