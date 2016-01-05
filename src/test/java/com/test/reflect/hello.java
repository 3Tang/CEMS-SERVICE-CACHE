package com.test.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.sys.common.util.ReflectionUtils;

public class hello {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		// TODO 自动生成的方法存根
			Class<?> demo=null;
			try {
				demo=Class.forName("com.test.reflect.Person");
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
			Person per=(Person) demo.newInstance();
			per.setAge(12);
			per.setName("lisi");
			
			//ReflectionUtils.
			
			Constructor<?> cons[] =demo.getConstructors();
			for(Constructor<?> con:cons)
			{
				System.out.println("con"+con);
			}
			Method[] meths=demo.getMethods();
			for(Method meth:meths)
			{
				System.out.println("meth"+meth);
			}
			
			Field[] fields=demo.getDeclaredFields();
			for(Field field:fields)
			{
				System.out.println("field"+field);
				
/*				System.out.println("is"+Modifier.toString(field.getModifiers())+field.getType());
*/			}
			
			Method  sayChina=demo.getMethod("sayChina");
			sayChina.invoke(demo.newInstance());
			
			Method setName=demo.getMethod("setName",String.class);
			setName.invoke(per, "alin");
			System.out.println("name is"+demo.getMethod("getName").invoke(per));
			
			Field sex=demo.getDeclaredField("sex");
			sex.setAccessible(true);
			sex.set(per, "男");
			 System.out.println(sex.get(per));
			
			/*System.out.println("per.getAge()"+per.getAge());
			System.out.println("per.getName()"+per.getName());*/
	}

}
