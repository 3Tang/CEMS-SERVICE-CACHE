package com.vrv.cems.service.dbtools;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


/** 
 *   <B>说       明</B>:插入语句拼装类。
 *
 * @author  作  者  名：陈 明<br/>
 *		    E-mail ：chenming@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年9月11日 下午14:53:43 
 */
public class InsertSQL{
	private static final Logger LOGGER = Logger.getLogger(InsertSQL.class);
	
	public static String genInsertSQL(SqlModel model){
		StringBuffer sber=new StringBuffer();
		String[] columnNames=model.getColumnNames();
		if(null!=model){
			sber.append("INSERT INTO ");
			sber.append(model.getTableName());
			sber.append("(");
			sber.append(StringUtils.join(columnNames,","));
			sber.append(") VALUES(");
			ModelField field=null;
			int i=0;
			for(String fieldName:columnNames){
				if(0<i){
					sber.append(",");
				}
				field=model.getModelField(fieldName);
				if(null==field){
					throw new NullPointerException("得到的字段为空！ ");
				}
				if(0==field.getFieldType()){
					sber.append("'");
					sber.append(field.getFieldValue());
					sber.append("'");
				}else if(1==field.getFieldType()){
					sber.append(field.getFieldValue());
				}else{}
				i++;
			}
			sber.append(")");
		}
		LOGGER.info("生成的插入语句为："+sber.toString());
		return sber.toString();
	}
	
}
