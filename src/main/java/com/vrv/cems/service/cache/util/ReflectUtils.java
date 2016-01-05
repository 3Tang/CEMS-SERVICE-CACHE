package com.vrv.cems.service.cache.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.vrv.cems.service.base.bean.cache.DeviceCache;
import com.vrv.cems.service.cache.service.PolicyService;

public class ReflectUtils {
	public static Logger log = Logger.getLogger(ReflectUtils.class);
	public static <T> Map<String, String> setObject2Map(T t, Class<?> tagetClass) throws IllegalArgumentException, IllegalAccessException {
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
		
		try{	
		for(Map.Entry<String, String> entry:fieldValueMap.entrySet())
		{
			Field[] fields=tagetClass.getDeclaredFields();
			
			for(Field field:fields)
			{
				
				if(field.getName().equalsIgnoreCase(entry.getKey()))
				{
					field.setAccessible(true);
					log.info("field:"+field.getType()+":"+field.getName());
					log.info("entry:"+entry.getKey());
					
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
	
	
public static <T, S> T copyObjectProperties(T t,S s, Class<?> destClass,Class<?> sourceClass) {
	
	   Map<String,String> fieldValueMap=new HashMap<String,String>();
	   if (t == null) {
			throw new IllegalArgumentException("No destination bean specified");
		}

		if (s == null) {
			throw new IllegalArgumentException("No origin bean specified");
		}
		if (log.isDebugEnabled()) {
			log.debug("ReflectUtils.copyProperties(" + t + ", " + s
					+ ")");
		}

	   
		try{	
		
			fieldValueMap=setObject2Map(s,sourceClass);
			t=setMap2Ojbect(fieldValueMap,t,destClass);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return t;
	}
	
}
