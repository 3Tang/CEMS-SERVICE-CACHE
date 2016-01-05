package com.vrv.test.db; 

import java.lang.reflect.Field;



/** 
 *   <B>说       明</B>:数据模型解析类。
 *
 * @author  作  者  名：陈 明<br/>
 *		    E-mail ：chenming@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月19日 下午1:29:53 
 */
public class ModelParse {
	/**
	 * 获取对象实例中指定字段的值。
	 * @param model 实体对象。
	 * @param fieldName 字段名称。
	 * @return 字段属性值。
	 */
	public static Object getProperty(Object model,String fieldName){
		Object value=null;
		try{
		    value = model.getClass().getDeclaredMethod("get"+headUpper(fieldName),new Class[]{}).invoke(model, new Object[]{});
		    if(null == value){
		    	return null;
		    }
		    return String.valueOf(value);
		}catch(Exception e){
			e.printStackTrace();
		}
		return value==null?"":value;
	}
	/**
	 * 获取实体对象注解的表名称
	 * @param model 实体类。
	 * @return 表名称。
	 */
	public static String getTableName(Class<?> model){
		return model.getAnnotation(Table.class).name();
	}
	/**
	 * 获取列名称
	 * @param field 属性字段
	 * @return
	 */
	public static String getColumnName(Field field){
		String columnName="";
		if(field.isAnnotationPresent(Column.class)){
			columnName= field.getAnnotation(Column.class).value();
		}
		/*else{
		columnName= addUnderline(field.getName());
		}*/
		return columnName;
	}
	/**
	 * 获取实体类型中的所有字段名称。
	 * @param clazz 实体类型。
	 * @return 实体类中以逗号[,]分隔的字段名称的字符串。
	 */
	public static String getColumnNames(Class<?> clazz){
		Field[] fields = clazz.getDeclaredFields();
		StringBuffer buffer = new StringBuffer();
		for(Field field:fields){
			if(!field.isAnnotationPresent(IgnoreColumn.class)){
				if(field.isAnnotationPresent(Column.class)){
					buffer.append(field.getAnnotation(Column.class).value());
				}else{
					buffer.append(field.getName());
				}
				buffer.append(",");
			}
		}
		buffer.delete(buffer.length()-1, buffer.length());
		return buffer.toString();
	}
	/**
	 * 字段类型判断
	 * @param field 对象属性字段。
	 * @return 为True表示是数值。
	 */
	public static boolean isNumber(Field field){
		Class<?> type = field.getType();
		
		if(int.class==type || long.class==type || double.class==type){
			return true;
		}
		return false;
	}
	/**
	 * 默认ID是主键，如要覆盖用primary注解
	 * @param clazz 对象类型
	 * @return
	 */
	public static Field getPrimaryKey(Class<?> clazz){
		Field[] fields=clazz.getDeclaredFields();
		Field tempField = null;
		for(Field field : fields){
			if(field.isAnnotationPresent(PrimaryKey.class)){
				return field;
			}
			/*if("id".equalsIgnoreCase(field.getName())){
				tempField = field;
			}*/
		}
		return tempField;
	}
	/**
	 * 将字符串首字母转换为大写。
	 * @param value 转换的字符串。
	 * @return 转换为大写的字符串。
	 */
	public static String headUpper(String value){
		char[] chars=value.toCharArray();
		
		if(null!=chars && chars.length>1 && Character.isUpperCase(chars[1])){
			
		}else{
		    chars[0]=Character.toUpperCase(chars[0]);
		}
		return String.valueOf(chars);
	}
	/**
	 * 去掉下划线,并转为小写
	 * @param tname
	 * @return
	 */
	public static String dropUnderline(String tname){
		StringBuffer columnName=new StringBuffer();
		tname=tname.toLowerCase();
		
		char[] tnames=tname.toCharArray();
		boolean _show=false;
		for(int i=0;i<tnames.length;i++){
			if("_".equals(String.valueOf(tnames[i]))){
				_show=true;
				continue;
			}
			if(_show){
				columnName.append(String.valueOf(Character.toUpperCase(tnames[i])));
				_show=false;
			}else{
				columnName.append(String.valueOf(tnames[i]));
			}
		}
		return columnName.toString();
	}
	
	/**
	 * 增加下划线,并转为大写
	 * @param tname
	 * @return
	 */
	public static String addUnderline(String tname){
		String columnName="";
		
		char[] tnames=tname.toCharArray();
		boolean _show=false;
		for(int i=0;i<tnames.length;i++){
			if(Character.isUpperCase(tnames[i])&&i>0){
				_show=true;
			}
			if(_show){
				columnName+="_"+tnames[i];
				_show=false;
			}else{
				columnName+=tnames[i];
			}
		}
		return columnName.toLowerCase();
	}
	/**
	 * 获取实体字段名称。
	 * @param model 实体类。
	 * @param columnName 列名称。
	 * @return 返回实体对象字段名称。
	 */
	public static String getFieldName(Class<?> model,String columnName){
		Field[] fields=model.getDeclaredFields();
		for(Field field:fields){
			if(field.isAnnotationPresent(Column.class)){
				if(columnName.equalsIgnoreCase(field.getAnnotation(Column.class).value())){
					return field.getName();
				}
				if(field.getName().equalsIgnoreCase(columnName)){
					return field.getName();
				}
				
			}
		}
		return null;
	}
	/**
	 * 判断实体的ID值是否有效。
	 * @param model
	 * @return
	 */
	public static boolean idValid(Object model){
		Field fid=getPrimaryKey(model.getClass());
		String id=String.valueOf(getProperty(model, fid.getName())).trim();
		if(null!=id&&!"".equals(id)&&!"0".equals(id)&&!"null".equalsIgnoreCase(id)){
			return true;
		}
		return false;
	}
}
 