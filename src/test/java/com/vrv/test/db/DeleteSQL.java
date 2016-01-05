package com.vrv.test.db;

import org.apache.log4j.Logger;

/** 
 *   <B>说       明</B>:删除语句拼装类。
 *
 * @author  作  者  名：陈 明<br/>
 *		    E-mail ：chenming@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年9月11日 下午14:43:43 
 */
public class DeleteSQL {
	private static final Logger LOGGER = Logger.getLogger(DeleteSQL.class);
	
	public static String genDeleteSQL(Class<?> cls,String id){
		StringBuffer sber=new StringBuffer();
		String result=null;
		SqlModel model=new SqlModel(cls,id);
		
		sber.append("DELETE FROM ");
		sber.append(model.getTableName());
		sber.append(" WHERE "+model.getPrimaryColumnName()+"='");
		sber.append(id);
		sber.append("'");
		
		result=sber.toString();
		LOGGER.info("生成的SQL语句为："+result);
		return result;
	}
	
    public static String genDeleteSQL(Class<?> cls,Number id){
    	StringBuffer sber=new StringBuffer();
    	String result=null;
    	SqlModel model = null;
		if(id instanceof Long){
			model = new SqlModel(cls,id.longValue());
		}else if(id instanceof Integer){
			model = new SqlModel(cls,id.intValue());
		}
		sber.append("DELETE FROM ");
		sber.append(model.getTableName());
		sber.append(" WHERE "+model.getPrimaryColumnName()+"=");
		sber.append(id);
		
		result=sber.toString();
		LOGGER.info("生成的SQL语句为："+result);
		return result;
	}
    
    public static String genDeleteSQL(SqlModel model){
    	StringBuffer sber=new StringBuffer();
    	String result=null;
    	sber.append("DELETE FROM ");
		sber.append(model.getTableName());
		sber.append(" where ");
		sber.append(model.getCondition());
		
		result=sber.toString();
		LOGGER.info("生成的SQL语句为："+result);
    	return result;
    }
    
    public static String genDeleteSQL(Object obj){
    	StringBuffer sber=new StringBuffer();
    	String result=null;
    	SqlModel model=new SqlModel(obj);
		
		sber.append(" DELETE FROM ");
		sber.append(model.getTableName());
		sber.append(" WHERE ");
		sber.append(model.getPrimaryFieldName());
		sber.append("=");
		ModelField primaryField = model.getModelField(model.getPrimaryFieldName());
		//字符串
		if(0==primaryField .getFieldType()){
			sber.append("'");
			sber.append(primaryField .getFieldValue()==null?model.getPrimaryFieldValue():primaryField .getFieldValue());
			sber.append("'");
		//数字
		}else if(1==primaryField .getFieldType()){
			sber.append(primaryField .getFieldValue()==null?model.getPrimaryFieldValue():primaryField .getFieldValue());
		}
		result=sber.toString();
		LOGGER.info("生成的SQL语句为："+result);
		return result;
	}
}
