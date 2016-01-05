package com.vrv.cems.service.dbtools; 

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


/** 
 *   <B>说       明</B>:查询语句拼装类。
 *
 * @author  作  者  名：陈 明<br/>
 *		    E-mail ：chenming@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月19日 下午4:43:43 
 */
public class SelectSQL {
	private static final Logger LOGGER = Logger.getLogger(SelectSQL.class);
	
	/**
	 * 生成简单的查询语句
	 * @param clazz
	 * @return
	 */
	public static String genSelectSQL(Class<?> clazz){
		Field idField=ModelParse.getPrimaryKey(clazz);
		String idColumnName =ModelParse.getColumnName(idField);
		
		String primaryKey = "";
		if(idColumnName!=null&&!("id".equals(idColumnName))){
			primaryKey = idColumnName;
		}else {
			primaryKey = idField.getName();
		}
		
		return genSelectSQL(clazz,primaryKey,"desc");
	}
	
	/**
	 * 生成简单的查询语句
	 * @param clazz
	 * @return
	 */
	public static String genSelectSQL(Class<?> clazz,String orderBy,String order){
		StringBuffer sber=new StringBuffer();
		Field[] fields= clazz.getDeclaredFields();
		String orderByName = "";
		if(null!=fields){
		    sber.append(" SELECT ");
				for(Field field : fields){
					if(!field.isAnnotationPresent(IgnoreColumn.class)){
						//列名
						String columnName = ModelParse.getColumnName(field);
						//实体类字段名称
						//String fieldName = field.getName();
						//判断列名是否与字段名称是否相等,用于sql语句orderBy使用。
						/*if(columnName.equals(fieldName)){
							orderByName = fieldName;
						}else{
							orderByName = columnName;
						}*/
						sber.append(columnName);
						if(!"".equals(columnName.trim())){
						sber.append(",");
						}
					}
				}
			sber.delete(sber.length()-1,sber.length());
			sber.append(" FROM ");
			sber.append(ModelParse.getTableName(clazz));
			/*sber.append(" ORDER BY ");
			sber.append(orderBy);
			sber.append(" ");
			sber.append(order);*/
		}
		LOGGER.info("生成的查询语句为："+sber.toString());
		return sber.toString();
	}

	/**
	 * 根据sql模型生成查询语句。
	 * @param model sql模型对象。
	 * @return sql查询语句。
	 */
	public static String genSelectSQL(SqlModel model){
		StringBuffer sber=new StringBuffer();
		sber.append(" SELECT ");
		sber.append(StringUtils.join(model.getColumnNames(),","));
	    sber.append(" FROM ");
	    sber.append(model.getTableName());
	    sber.append(" where ");
	    sber.append(model.getCondition());
	    
	    LOGGER.info("生成的查询语句为："+sber.toString());
		return sber.toString();
	}
	
}
 