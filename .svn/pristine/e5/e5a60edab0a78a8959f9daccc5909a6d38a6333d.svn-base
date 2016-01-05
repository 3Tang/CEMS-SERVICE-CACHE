package com.vrv.cems.service.cache;



import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.vrv.cems.service.base.bean.cache.DeviceProduct;
import com.vrv.cems.service.base.bean.cache.ProductInfoNew;
import com.vrv.cems.service.base.bean.cache.ProductInfoOld;
import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.base.interfaces.ICacheService;
import com.vrv.cems.service.cache.bean.ProdInfoOldBean;
import com.vrv.cems.service.cache.service.DeviceInsProOldService;
import com.vrv.cems.service.cache.service.impl.DeviceInsProOldServiceImpl;

public class DeviceInsProOldServiceTest {
	/*private Logger log = Logger.getLogger(DeviceInsProOldServiceTest.class);
	private DeviceInsProOldService deviceInsProOldService;
	private List<ProductInfoOld> productInfoOldList=new ArrayList<ProductInfoOld>();
	private List<ProductInfoNew> productInfoNewList=new ArrayList<ProductInfoNew>();
	private List<DeviceProduct> devProdList=new ArrayList<DeviceProduct>();
	private ProductInfoOld prodOld1;
	private ProductInfoOld prodOld2;
	private Result result;
	private String ip;
	private String  devOnlyId;
	private List<Result> resultList = new ArrayList<Result>();
	*//**
	 * 初始化一些该单元测试类需要的数据
	 *//*
	@Before
	public void init(){
		
		deviceInsProOldService = new DeviceInsProOldServiceImpl();
		result = new Result();
		ip="192.168.32.102";
		devOnlyId="32113211321132113211321132113211";
		
		
		DeviceProduct deviceProduct1 =new DeviceProduct();
		prodOld1=new ProductInfoOld();
		prodOld2=new ProductInfoOld();
		deviceProduct1.setOsType("windows");
		deviceProduct1.setProductType("vdn");
		
		prodOld1.setProductName("prodname1111111");
		prodOld1.setProductSign("prodsign1111111");
		prodOld1.setVersion("prodVersion111111");
		prodOld1.setType(12);
	

		prodOld2.setProductName("prodname22222");
		prodOld2.setProductSign("prodsign222222");
		prodOld2.setVersion("prodVersion2222222");
		prodOld2.setType(22);
		
		productInfoOldList.add(prodOld1);
		productInfoOldList.add(prodOld2);
		deviceProduct1.setProductInfoOldList(productInfoOldList);
		
		devProdList.add(deviceProduct1);
		
		
	}
	
	*//**
	 * 保存已安装产品信息方法
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 *//*
	@Test
	public void testSaveDeviceInsProOld() 
	{
		result= deviceInsProOldService.saveDeviceInsProOld(ICacheService.SERVICE_CODE, ICacheService.SAVEDEVICE,devOnlyId,devProdList);
		log.info(result.getCode()+":"+result.getInfo());
		
		result=deviceInsProOldService.saveDeviceInsProOld(ICacheService.SERVICE_CODE,
				ICacheService.SAVEDEVICEINSPROOLD, devOnlyId,
				devProdList);
		if(result.getCode()!=0)
		{
			System.out.println("客户端心跳 保存 已安装产品信息缓存表 失败!");
			log.info("客户端心跳 保存 已安装产品信息缓存表 失败!");
			
		}
	}
	
	*//**
	 * 保存已安装产品信息方法
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 *//*
	@Test
	public void testQueryDeviceInsProOldByDevOnlyId() 
	{
		String devOnlyIdtest="32103210321032103210321032103210";
		List<DeviceProduct> deviceProdList =new ArrayList<DeviceProduct>();
		deviceProdList= deviceInsProOldService.	queryDeviceInsProOldByDevOnlyId(ICacheService.SERVICE_CODE, ICacheService.QUERYDEVICEINSPROOLDBYDEVONLYID,devOnlyIdtest);
		for(DeviceProduct devprod:deviceProdList)
		{
			log.info(devprod.getOsType()+":"+devprod.getProductType());
			
			for(ProductInfoOld prod:devprod.getProductInfoOldList())
			{
			
				log.info(prod.getProductName()+":"+prod.getVersion()+":"+prod.getType());
				
			}
		}
		
	}
	*/
	
}
