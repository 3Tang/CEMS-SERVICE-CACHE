package com.vrv.cems.service.cache.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;


import com.vrv.cems.service.cache.bean.DeviceProductBean;
import com.vrv.cems.service.cache.bean.ProdInfoNewBean;
import com.vrv.cems.service.cache.bean.ProdInfoOldBean;

public class JSONUtil {

	  public static Object json2DevProdBean(String json, Class<?> clazz)   {

	   
		  JSONObject jsonObject = null;
			jsonObject = JSONObject.fromObject(json);
			Map classMap = new HashMap();
			Object tempObj = null;
			try {
				//创建ProdInfoOldBean对象
				tempObj = clazz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			if("ProdInfoOldBean".equals(clazz.getSimpleName())){
				classMap.put("productInfoOld",ProdInfoOldBean.class);
			}
			else if("ProdInfoNewBean".equals(clazz.getSimpleName())){
				classMap.put("productInfoNew",ProdInfoNewBean.class);
			}
			Object obj = null;
			if(classMap.size()>0){
				obj = JSONObject.toBean(jsonObject,DeviceProductBean.class,classMap);
			}else{
				obj = JSONObject.toBean(jsonObject,DeviceProductBean.class);
			}
			return obj;
	
	     
	    }
}
