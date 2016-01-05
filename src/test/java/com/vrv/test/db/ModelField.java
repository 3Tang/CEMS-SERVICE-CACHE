package com.vrv.test.db; 
/** 
 *   <B>说       明</B>:数据模型字段属性类。
 *
 * @author  作  者  名：陈 明<br/>
 *		    E-mail ：chenming@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月19日 下午2:06:01 
 */
public class ModelField {
	/**
	 * 字段名称
	 */
	private String fieldName=null;
	/**
	 * 数据列名称
	 */
	private String columnName=null;
	/**
	 * 字段类型(0=代表字符串类型,1=代表数字类型)
	 * 
	 */
	private Integer fieldType=0;
	/**
	 * 字段值
	 */
	private String fieldValue;
	/**
	 * 关联对象
	 */
	private Class<?> foreignKey;
	/**
	 * 是否排序
	 */
	private boolean orderBy = false;
	public ModelField(String fieldName, String columnName, Integer fieldType,
			String fieldValue) {
		super();
		this.fieldName = fieldName;
		this.columnName = columnName;
		this.fieldType = fieldType;
		this.fieldValue = fieldValue;
	}
	public ModelField(String fieldName, String columnName, Integer fieldType,
			String fieldValue, Class<?> foreignKey) {
		super();
		this.fieldName = fieldName;
		this.columnName = columnName;
		this.fieldType = fieldType;
		this.fieldValue = fieldValue;
		this.foreignKey = foreignKey;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public Integer getFieldType() {
		return fieldType;
	}
	public void setFieldType(Integer fieldType) {
		this.fieldType = fieldType;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	public Class<?> getForeignKey() {
		return foreignKey;
	}
	public void setForeignKey(Class<?> foreignKey) {
		this.foreignKey = foreignKey;
	}
	public boolean isOrderBy() {
		return orderBy;
	}
	public void setOrderBy(boolean orderBy) {
		this.orderBy = orderBy;
	}
	
}
 