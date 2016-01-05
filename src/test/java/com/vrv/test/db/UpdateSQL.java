package com.vrv.test.db;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/** 
 *   <B>说       明</B>:查询语句拼装类。
 *
 * @author  作  者  名：陈 明<br/>
 *		    E-mail ：chenming@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年9月11日 下午15:23:43 
 */
public class UpdateSQL {
	private static final Logger LOGGER = Logger.getLogger(UpdateSQL.class);
	
	public static String genUpdateSQL(SqlModel model){
		StringBuffer sber=new StringBuffer();
		String result=null;
		String[] columnNames=model.getColumnNames();
		
		//获取主键对象模型
		ModelField primaryField=model.getModelField(model.getPrimaryFieldName());
		//主键列名称
		String primaryColumnName = primaryField.getColumnName();
		if(null!=model){
			sber.append("UPDATE ");
			sber.append(model.getTableName());
			sber.append(" SET ");
			ModelField field=null;
			for(String fieldName:columnNames){
				if("id".equalsIgnoreCase(fieldName)){
					continue;
				}
				if(primaryColumnName.equalsIgnoreCase(fieldName)){
					continue;
				}
				field=model.getModelField(fieldName);
				if(null==field){
					throw new NullPointerException("得到的字段为空！ ");
				}
				
				if(0==field.getFieldType()&&StringUtils.isNotEmpty(field.getFieldValue())&&!"null".equalsIgnoreCase(field.getFieldValue())){
					sber.append(field.getColumnName());
					sber.append("=");
					sber.append("'");
					sber.append(field.getFieldValue());
					sber.append("'");
					sber.append(",");
				}else if(1==field.getFieldType()&&StringUtils.isNotEmpty(field.getFieldValue())&&!"null".equalsIgnoreCase(field.getFieldValue())){
					sber.append(field.getColumnName());
					sber.append("=");
					sber.append(field.getFieldValue());
					sber.append(",");
				}else{}
			}
			if(null!=sber&&sber.length()>0&&sber.toString().endsWith(",")){
				sber=sber.delete(sber.length()-1,sber.length());
			}
			
			sber.append(" WHERE ");
			sber.append(primaryColumnName);
			sber.append("=");
			
			if((null==primaryField||StringUtils.isEmpty(primaryField.getFieldValue()))&&(null==model.getPrimaryFieldValue()||"".equals(model.getPrimaryFieldValue()))){
				throw new NullPointerException("ID字段值为空！");
			}
			if(0==primaryField.getFieldType()){
				sber.append("'");
				sber.append(primaryField.getFieldValue()==null?model.getPrimaryFieldValue():primaryField.getFieldValue());
				sber.append("'");
			}else if(1==primaryField.getFieldType()){
				sber.append(primaryField.getFieldValue()==null?model.getPrimaryFieldValue():primaryField.getFieldValue());
			}
		}
		result=sber.toString();
		LOGGER.info("生成的SQL语句为："+result);
		return result;
	}
}
