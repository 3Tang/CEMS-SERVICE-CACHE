package com.vrv.cems.service.dbtools; 

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;





/** 
 *   <B>说       明</B>:sql数据模型类。
 *
 * @author  作  者  名：陈 明<br/>
 *		    E-mail ：chenming@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月19日 下午2:04:55 
 */
public class SqlModel extends ArrayList<ModelField>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6177835396812746151L;
	
	private Integer size=0;
	private String tableName=null;
	private String primaryFieldName=null;
	private String primaryColumnName = null;
	private String primaryFieldValue=null;
	private Long   parmaryFieldValueL = null;
	private Integer parmaryFieldValueI = null;
	private String[] fieldNames=null;
	private String[] columnNames=null;
	private StringBuffer sber=new StringBuffer();
	
	private Long pageNum=0l;//总页数
	private Long pageNow=1l;//当前页数
	private Long pageSize=15l;//每页多少条数据
	private Long total=0l;//总条数
	
	public boolean toPage=true;//进行分页
	
	private Object modelObj;
	private Class<?> modelClass;
	private Condition condition;
	public SqlModel(Class<?> clazz) throws InstantiationException, IllegalAccessException{
		modelClass=clazz;
		loadModel(clazz);
		condition= new Condition(this);
		condition.setModel(this);
	}
	
	public SqlModel(Class<?> clazz,Long id) throws InstantiationException, IllegalAccessException{
		setParmaryFieldValueL(id);//设置主键值
		modelClass=clazz;
		loadModel(clazz);
		condition=new Condition(this);
		condition.setModel(this);
	}
	public SqlModel(Class<?> clazz,Integer id) throws InstantiationException, IllegalAccessException{
		setParmaryFieldValueI(id);//设置主键值
		modelClass=clazz;
		loadModel(clazz);
		condition=new Condition(this);
		condition.setModel(this);
	}

	public SqlModel(Class<?> cls,String id) throws InstantiationException, IllegalAccessException{
		setPrimaryFieldValue(id);//设置主键值
		modelClass=cls;
		loadModel(cls);
		condition=new Condition(this);
		condition.setModel(this);
	}
	
	public SqlModel(Object obj){
		modelObj=obj;
		modelClass=obj.getClass();
		load(obj);
		condition=new Condition(this);
		condition.setModel(this);
	}
	/**
	 * 加载字段信息。
	 * @param obj
	 */
	public void load(Object obj){
		clear();
		//设置表名称
		setTableName(ModelParse.getTableName(obj.getClass()));
		//获取类所声明的所有字段
		Field[] fields=obj.getClass().getDeclaredFields();
		//获取主键字段属性
		Field primaryField=ModelParse.getPrimaryKey(obj.getClass());
		
		Column primaryColunm = primaryField.getAnnotation(Column.class);
		
		primaryFieldName=primaryField.getName();
		if(primaryColunm!=null){
			primaryColumnName = primaryColunm.value();
		}else{
			primaryColumnName = primaryFieldName;
		}
		
		for(Field field : fields){
			if(!field.isAnnotationPresent(IgnoreColumn.class)){
				if(field.isAnnotationPresent(Column.class)){
				int type= ModelParse.isNumber(field)?1:0;
				if(primaryFieldName.equalsIgnoreCase(field.getName())){
					primaryFieldValue=String.valueOf(ModelParse.getProperty(obj,primaryFieldName));
				}
				add(new ModelField(field.getName(),ModelParse.getColumnName(field),type,String.valueOf(ModelParse.getProperty(obj,field.getName()))));
			}
			}
			
		}
		size = size();
	}
	/**
	 * 加载字段信息。
	 * @param clazz 实体类型。
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void loadModel(Class<?> clazz) throws InstantiationException, IllegalAccessException{
		//设置表名称
		setTableName(ModelParse.getTableName(clazz));
		//获取类所声明的所有字段
		Object obj=clazz.newInstance();
		Field[] fields=clazz.getDeclaredFields();
		//获取主键字段属性
		Field primaryField=ModelParse.getPrimaryKey(clazz);
		
		primaryFieldName=primaryField.getName();
		Column primaryColunm = primaryField.getAnnotation(Column.class);
		
		primaryFieldName=primaryField.getName();
		if(primaryColunm!=null){
			primaryColumnName = primaryColunm.value();
		}else{
			primaryColumnName = primaryFieldName;
		}
		
		for(Field field : fields){
			if(!field.isAnnotationPresent(IgnoreColumn.class)){
				if(field.isAnnotationPresent(Column.class)){
				int type= ModelParse.isNumber(field)?1:0;
				if(primaryFieldName.equalsIgnoreCase(field.getName())){
					primaryFieldValue=String.valueOf(ModelParse.getProperty(obj,primaryFieldName));
				}
				add(new ModelField(field.getName(),ModelParse.getColumnName(field),type,String.valueOf(ModelParse.getProperty(obj,field.getName()))));
				}
			}
		}
		size=size();
	}
	/**
	 * 更新字段值。
	 * @param bean
	 */
	public void update(Object bean){
		Iterator<ModelField> it=iterator();
		ModelField mf=null;
		while(it.hasNext()){
			mf=it.next();
			mf.setFieldValue(String.valueOf(ModelParse.getProperty(bean, mf.getFieldName())));
		}
	}
	
	/**
	 * 得到指定字段对象。
	 * @param name
	 * @return
	 */
	public ModelField getModelField(String name){
		Iterator<ModelField> it=iterator();
		ModelField mf=null;
		while(it.hasNext()){
			mf=it.next();
			if(name.equalsIgnoreCase(mf.getFieldName())||name.equalsIgnoreCase(mf.getColumnName())){
				return mf;
			}
		}
		return null;
	}
	

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public StringBuffer getSber() {
		return sber;
	}

	public int getSize() {
		return size;
	}
	

	public void setPrimaryFieldValue(String primaryFieldValue) {
		this.primaryFieldValue = primaryFieldValue;
	}

	/**
	 * 得到主键字段名
	 * @return
	 */
	public String getPrimaryFieldName(){
		return this.primaryFieldName;
	}
	
	/**获取主键的列名称
	 * @return 字段的列名称。
	 */
	public String getPrimaryColumnName() {
		return primaryColumnName;
	}

	/**
	 * 得到主键字段值
	 * @return
	 */
	public String getPrimaryFieldValue(){
		return this.primaryFieldValue;
	}

	public String[] getFieldNames() {
		if(null==fieldNames){
			fieldNames=new String[size];
			Iterator<ModelField> mfs=iterator();
			ModelField mf=null;
			int i=0;
			while(mfs.hasNext()){
				mf=mfs.next();
				fieldNames[i++]=mf.getFieldName();
			}
		}
		return fieldNames;
	}
	
	public String[] getColumnNames(){
		if(null==columnNames){
			columnNames = new String[size];
			Iterator<ModelField> mfs=iterator();
			ModelField mf=null;
			int i=0;
			while(mfs.hasNext()){
				mf=mfs.next();
				
				columnNames[i++]=mf.getColumnName();
				
			}
		}
		
		
		return columnNames;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public Class<?> getModelClass() {
		return modelClass;
	}

	public Object getModelObj() {
		return modelObj;
	}

/*	public void toPage(){
            if("mysql".equalsIgnoreCase(DBUtils.database)){
                getCondition().getCdtion().append(" LIMIT ");
                getCondition().getCdtion().append(getPageNow()<=1?0:(getPageNow()-1)*getPageSize());
                getCondition().getCdtion().append(",");
                getCondition().getCdtion().append(getPageNow()<=1?getPageSize():getPageNow()*getPageSize());
            }else if("oracle".equalsIgnoreCase(DBUtils.database)){
                
            }else if("sqlserver".equalsIgnoreCase(DBUtils.database)){
            
            }
	}
*/
	public boolean isToPage() {
		return toPage;
	}

	public void setToPage(boolean toPage) {
		this.toPage = toPage;
	}
	public void setTotal(long total){
		if(0!=total){
		    this.total=total;
		    pageNum=(total/pageSize)+(total%pageSize==0?0:1);
		}
	}

	public long getPageNow() {
		if(pageNow>=pageNum){
			pageNow=pageNum;
		}
		return pageNow;
	}
	public void setPageNow(long pageNow) {
		if(pageNow<1){
			pageNow=1;
		}
		this.pageNow = pageNow;
	}
	public long getPageNum() {
		return pageNum;
	}
	public long getPageSize() {
		return pageSize;
	}
	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}

	public Long getParmaryFieldValueL() {
		return parmaryFieldValueL;
	}

	public void setParmaryFieldValueL(Long parmaryFieldValueL) {
		this.parmaryFieldValueL = parmaryFieldValueL;
	}

	public Integer getParmaryFieldValueI() {
		return parmaryFieldValueI;
	}

	public void setParmaryFieldValueI(Integer parmaryFieldValueI) {
		this.parmaryFieldValueI = parmaryFieldValueI;
	}
	
}
 