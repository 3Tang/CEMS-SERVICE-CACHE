package com.test.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ezmorph.Morpher;
import net.sf.ezmorph.object.DateMorpher;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.sys.common.util.ReflectionUtils;
import com.sys.common.util.json.JsonConfigEx;
import com.sys.common.util.json.JsonUtils;
import com.vrv.cems.service.base.bean.cache.DeviceCache;
import com.vrv.cems.service.base.bean.cache.DeviceProduct;
import com.vrv.cems.service.base.bean.cache.ProductInfoOld;
import com.vrv.cems.service.cache.bean.DeviceBean;
import com.vrv.cems.service.cache.bean.DeviceProductBean;
import com.vrv.cems.service.cache.bean.ProdInfoNewBean;
import com.vrv.cems.service.cache.bean.ProdInfoOldBean;
import com.vrv.cems.service.cache.util.JSONUtil;
import com.vrv.cems.service.cache.util.ReflectUtils;
import com.vrv.cems.service.dbtools.DBUtil;

public class ReflectTest {
	  static Logger log = Logger.getLogger(ReflectTest.class);
	 public static void main(String [] args) throws IllegalAccessException, InvocationTargetException, ParseException
	 {
		 /*DeviceCache deviceCache = new DeviceCache();
		 DeviceCache deviceCache2 = new DeviceCache();
			deviceCache.setId("22222222222222222222222222222222");
			deviceCache.setDevOnlyId("99999999999999999999999999999999");
			deviceCache.setIp("192.168.32.102");
			
			DeviceBean deviceBean =new DeviceBean();
			DeviceBean deviceBean2 =new DeviceBean();
			BeanUtils.copyProperties(deviceBean, deviceCache);
			
			log.info("deviceBean.getId()"+deviceBean.getId());
			log.info("deviceBean.getDevOnlyId()"+deviceBean.getDevOnlyId());
			log.info("deviceBean.getIp()"+deviceBean.getIp());
			
			Map<String, String> fieldValueMap=saveBean(deviceBean,DeviceBean.class);
			
			for(Map.Entry<String, String> entry:fieldValueMap.entrySet())
			{
				log.info(entry.getKey()+"..."+entry.getValue());
			}
			
			deviceCache2=setMap2Ojbect(fieldValueMap,deviceCache2,DeviceCache.class);
			log.info(deviceCache2.getId()+"..."+deviceCache2.getIp());
			
			String str="192.168.32.111";
			str.length();
			String strs [] ="192.168.32.111".split("\\.");
			for(int i=0;i<strs.length;i++)
			{
				String s=strs[i];
				log.info("String s"+s);
			}
			log.info("length"+strs.length+"str.length()"+str.length());
			//复杂对象转为json
			List<DeviceProduct> devProdList=new ArrayList<DeviceProduct>();
			DeviceProduct deviceProduct1 =new DeviceProduct();
			
			 List<ProductInfoOld> productInfoOldList =new ArrayList<ProductInfoOld>();
			 List<ProdInfoOldBean> prodInfoOldBeanList =new ArrayList<ProdInfoOldBean>();
			 ProductInfoOld prodOld1=new ProductInfoOld();
			 ProductInfoOld prodOld2=new ProductInfoOld();
			 
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
			DeviceProductBean deviceProductBean = new DeviceProductBean();
			ProdInfoOldBean prodInfoOldBean1 =new ProdInfoOldBean();
			ProdInfoOldBean prodInfoOldBean2 =new ProdInfoOldBean();
			ProdInfoNewBean prodInfoNewBean=new ProdInfoNewBean();
			BeanUtils.copyProperties(prodInfoOldBean1,prodOld1);
			BeanUtils.copyProperties(prodInfoOldBean2,prodOld2);
			prodInfoOldBeanList.add(prodInfoOldBean1);
			prodInfoOldBeanList.add(prodInfoOldBean2);
			deviceProductBean.setProductInfoOld(prodInfoOldBeanList);
			deviceProductBean.setOsType(deviceProduct1.getOsType());
			
		    String value = JsonUtils.object2Json(deviceProductBean);
		       
			//JSONObject jsonObject = JSONObject.fromObject(deviceProduct1);  
			JSONArray arry= JSONArray .fromObject(deviceProduct1);
			//String value=JsonUtils.object2Json(deviceProduct1);
			//String value=jsonObject.toString();
			System.out.println("devProdList String is:"+value);
			
			//json转为复杂对象
			//JsonUtils.json2Object(value, ProdInfoOldBean.class);
			//DeviceProductBean deviceProdBean=(DeviceProductBean) JSONUtil.json2DevProdBean(value, ProdInfoOldBean.class);
			//deviceProductBean =(DeviceProductBean) JsonUtils.json2Object(value);
			
			//DeviceProductBean JsonUtils.json2Object(value, ProdInfoOldBean.class);
			Map classMap=new HashMap();
			classMap.put("productInfoOld",ProdInfoOldBean.class);
			DeviceProductBean deviceProdBean=JsonUtils.json2Object(value, DeviceProductBean.class, classMap);
			
			System.out.println("deviceProdBean.getOsType()"+deviceProdBean.getOsType()
					+"deviceProdBean.getProductType()"+deviceProdBean.getProductType()
					);
			
			for(ProdInfoOldBean prodOldBean:deviceProdBean.getProductInfoOld()){
				ProductInfoOld prodOld=new ProductInfoOld();
				BeanUtils.copyProperties(prodOld, prodOldBean);
				ReflectUtils.copyObjectProperties(prodOld, prodOldBean, ProductInfoOld.class, ProdInfoOldBean.class);
				//System.out.println("prodOldBean"+prodOldBean.toString());
				System.out.println("各个属性"+prodOld.toString());
				productInfoOldList.add(prodOld);
			}
			
			deviceProduct1.setProductInfoOldList(productInfoOldList);
			deviceProduct1.setOsType(deviceProductBean.getOsType());
			deviceProduct1.setProductType(deviceProductBean.getProductType());
			
			*/
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date currentDate=new Date();
			long currentTime=currentDate.getTime();
			System.out.println("当前时间"+sdf.format(currentDate));
			System.out.println("currentTime"+currentTime);
			System.out.println("systemcurrentTime"+System.currentTimeMillis());
			String config="2015-05-09 10:51:38";
			Date configdate = sdf.parse(config);
			long configTime =configdate.getTime();
			
			if((currentTime-configTime)/(60*1000)>5)
			{
				System.out.println("时间相隔大于五分钟");
			}
			else
			{
				System.out.println("时间相隔小于五分钟");
			}
		     String devOnlyId="34bf0bc7199247a4b3dae1ed3529702b";
			String updateSql="update cems_device set isOpened=1 where devOnlyId=?";
			try {
				DBUtil.queryRunner.update(updateSql, devOnlyId);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
			
	 }
	private static <T> Map<String, String> saveBean(T t, Class<?> tagetClass) throws IllegalArgumentException, IllegalAccessException {
		// TODO 自动生成的方法存根
		Map<String,String> fieldValueMap=new HashMap<String,String>();
		Field[] fields=tagetClass.getDeclaredFields();
		for(Field field:fields)
		{
			field.setAccessible(true);
			Object obj=field.get(t);
			
			if(obj!=null)
			{
				fieldValueMap.put(field.getName(), obj.toString());
			}
		}
		
		return fieldValueMap;
		
	}
	
	public static <T> T setMap2Ojbect(Map<String, String> fieldValueMap,T t, Class<?> tagetClass) {
		// TODO 自动生成的方法存根
		try{
		for(Map.Entry<String, String> entry:fieldValueMap.entrySet())
		{
			Field[] fields=tagetClass.getDeclaredFields();
			for(Field field:fields)
			{
				if(field.getName().equals(entry.getKey()))
				{
					field.setAccessible(true);
					
					
					if("int".equals(field.getType().getSimpleName()))
					{
						field.set(t, Integer.parseInt(entry.getValue()));	
					}
					else{
						field.set(t, entry.getValue());
					}
						
					
				}
			}
			
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return t;
	}

}
