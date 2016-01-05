package com.vrv.test.db; 

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


/** 
 *   <B>说       明</B>:条件语句类。
 *
 * @author  作  者  名：陈 明<br/>
 *		    E-mail ：chenming@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月19日 下午2:16:58 
 */
public class Condition {
	private static final Logger LOGGER = Logger.getLogger(Condition.class);
	private Class<?> modelCls;
	
	private StringBuilder sber;
	
	private StringBuilder cdtion;
	
	private boolean hasCondition=false;
	
	private SqlModel model=null;
	
	//private String orderBy;
	
	private String order="DESC";

	public Condition(SqlModel model) {
		super();
		this.modelCls = model.getModelClass();
		this.cdtion = new StringBuilder();
		this.model = model;
	}

	/**
	 * 核心添加条件方法
	 * @param fieldName
	 * @param link
	 * @param fieldValue
	 */
	private void addCondition(String fieldName,Link link,String fieldValue){
		try{
			
			if(!hasCondition){
				hasCondition=true;
			}else{
				if(!cdtion.toString().trim().endsWith("AND")&&!cdtion.toString().trim().endsWith("OR")){
					and();
				}
			}
			
		    cdtion.append(" (");
		    cdtion.append(ModelParse.getColumnName(modelCls.getDeclaredField(fieldName)));
		    cdtion.append(link.value());
                    
                    if("IN".equals(link.value().trim())){
                          cdtion.append("(");
                          cdtion.append(fieldValue);
                          cdtion.append(")");
                    }else if("NOT IN".equals(link.value().trim())){
                          cdtion.append("(");
                          cdtion.append(fieldValue);
                          cdtion.append(")");
                    }else if("LIKE".equals(link.value().trim())){
                          cdtion.append("'%");
                          cdtion.append(fieldValue);
                          cdtion.append("%'");
                    }else{
                    
				        if(ModelParse.isNumber(modelCls.getDeclaredField(fieldName))){
					       cdtion.append(fieldValue);	
				        }else{
					    cdtion.append("'");
					    cdtion.append(fieldValue);
					    cdtion.append("'");
				        }
                    }
		    cdtion.append(") ");
	    }catch(Exception e){
	    	LOGGER.error("拼接条件语句时出错!", e);
		    e.printStackTrace();
	    }
	}
	
	
	/**
	 * 添加等于条件
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	public Condition eq(String fieldName, Object fieldValue) {
	    if(fieldValue.getClass() == this.modelCls){
              addCondition(fieldName,Link.EQUAL,String.valueOf(ModelParse.getProperty(fieldValue,fieldName)));
	    }else{
		      addCondition(fieldName,Link.EQUAL,String.valueOf(fieldValue));
	    }
		return this;
	}

	/**
	 * 添加不等于条件
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	public Condition ne(String fieldName, Object fieldValue) {
          if(fieldValue.getClass() == this.modelCls){
              addCondition(fieldName,Link.NOT_EQUAL,String.valueOf(ModelParse.getProperty(fieldValue,fieldName)));
	     }else{
		     addCondition(fieldName,Link.NOT_EQUAL,String.valueOf(fieldValue));
         }
		return this;
	}

	/**
	 * 添加小于等于条件
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	public Condition le(String fieldName, Object fieldValue) {
             if(fieldValue.getClass() == this.modelCls){
                  addCondition(fieldName,Link.LESS_EQUAL,String.valueOf(ModelParse.getProperty(fieldValue,fieldName)));
	     }else{
		addCondition(fieldName,Link.LESS_EQUAL,String.valueOf(fieldValue));
             }
		return this;
	}

	/**
	 * 添加大于等于条件
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	public Condition ge(String fieldName, Object fieldValue) {
            if(fieldValue.getClass() == this.modelCls){
                  addCondition(fieldName,Link.GRATE_EQUAL,String.valueOf(ModelParse.getProperty(fieldValue,fieldName)));
	     }else{
		addCondition(fieldName,Link.GRATE_EQUAL,String.valueOf(fieldValue));
            }
		return this;
	}

	/**
	 * 添加大于条件
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	public Condition gt(String fieldName, Object fieldValue) {
            if(fieldValue.getClass() == this.modelCls){
                  addCondition(fieldName,Link.GRATE_THAN,String.valueOf(ModelParse.getProperty(fieldValue,fieldName)));
	     }else{
		addCondition(fieldName,Link.GRATE_THAN,String.valueOf(fieldValue));
            }
		return this;
	}

	/**
	 * 添加小于条件
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	public Condition lt(String fieldName, Object fieldValue) {
         if(fieldValue.getClass() == this.modelCls){
             addCondition(fieldName,Link.LESS_THAN,String.valueOf(ModelParse.getProperty(fieldValue,fieldName)));
	     }else{
		     addCondition(fieldName,Link.LESS_THAN,String.valueOf(fieldValue));
          }
		return this;
	}

	/**
	 * 添加Like条件
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	public Condition like(String fieldName, Object fieldValue) {
         if(fieldValue.getClass() == this.modelCls){
             addCondition(fieldName,Link.LIKE,String.valueOf(ModelParse.getProperty(fieldValue,fieldName)));
	     }else{
		     addCondition(fieldName,Link.LIKE,String.valueOf(fieldValue));
         }
		return this;
	}

	/**
	 * 添加In条件
	 * @param fieldName
	 * @param fieldValues
	 * @return
	 */
	public Condition in(String fieldName, Object[] fieldValues) {
            try{
                String[] values=StringUtils.join(fieldValues,",").split(",");
		addCondition(fieldName,Link.IN,join(values,ModelParse.isNumber(modelCls.getDeclaredField(fieldName))));
            }catch(Exception e){
                e.printStackTrace();
            }
		return this;
	}
	
	/**
	 * 添加In条件
	 * @param fieldName
	 * @param fieldValues
	 * @return
	 */
	public Condition in(String fieldName, List<String> fieldValues) {
        in(fieldName, fieldValues.toArray());
		return this;
	}
	
	public Condition and(){
		cdtion.append(" AND ");
		return this;
	}
	
	public Condition or(){
		cdtion.append(" OR ");
		return this;
	}

	public Condition notIn(String fieldName, Object[] fieldValues) {
            try{
                String[] values=StringUtils.join(fieldValues,",").split(",");
		        addCondition(fieldName,Link.NOT_IN,join(values,ModelParse.isNumber(modelCls.getDeclaredField(fieldName))));
            }catch(Exception e){
                e.printStackTrace();
            }
            return this;
	}
	
	private String join(String[] values,boolean isNumber){
		int i=0;
		if(null==sber){
			sber=new StringBuilder();			
		}else{
			sber.delete(0, sber.length());
		}
		for(String value:values){
			if(0!=i){
			    sber.append(",");				
			}
			if(isNumber){
				sber.append(value);	
			}else{
				sber.append("'");
				sber.append(value);
				sber.append("'");
			}
			
			i++;
		}
		return sber.toString();
	}

	public StringBuilder getCdtion() {
		
		if(cdtion.toString().trim().length()<=0){
			cdtion.append(" 1=1 ");
		}
		
		cdtion.append(" \n  ORDER BY ");
		cdtion.append(getOrderBy());
		cdtion.append(" ");
		cdtion.append(getOrder());
		cdtion.append(" ");
		
		return cdtion;
	}

	private String getOrderBy() {
		//默认按主键进行排序
		Field idField=ModelParse.getPrimaryKey(modelCls);
		String primaryFieldName = ModelParse.getColumnName(idField);
		return primaryFieldName;
	}
	
/*	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}*/

	public void setOrder(String order) {
		this.order = order;
	}

	/*public Condition orderBy(String orderBy) {
		this.orderBy = ModelParse.addUnderline(orderBy);
		return this;
	}*/
	private String getOrder() {
		return order;
	}
	public Condition order(String order) {
		this.order = ModelParse.addUnderline(order);
		return this;
	}

	public SqlModel getModel() {
		return model;
	}

	public Class<?> getModelCls() {
		return modelCls;
	}


	public void setModel(SqlModel model) {
		this.model = model;
	}
	
	@Override
    public String toString() {
        return getCdtion().toString();
    }
	
	
}
 