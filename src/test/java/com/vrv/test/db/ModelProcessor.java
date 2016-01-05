package com.vrv.test.db; 

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.RowProcessor;


/** 
 *   <B>说       明</B>:对象模型处理器类。
 *
 * @author  作  者  名：陈 明<br/>
 *		    E-mail ：chenming@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月19日 下午4:52:38 
 */
public class ModelProcessor implements RowProcessor{

	private static final ModelProcessor processor=new ModelProcessor();
	 /**
    * 所使用的特殊的数组标识值 <code>mapColumnsToProperties</code>
    * 表示没有从Bean属性找到相匹配的列
    * <code>ResultSet</code>.
    */
   protected static final int PROPERTY_NOT_FOUND = -1;

   /**
    * 设置Bean的原始属性值。
    */
   private static final Map<Class<?>, Object> primitiveDefaults = new HashMap<Class<?>, Object>();

   /**
    * 结果集列用Bean的属性名覆盖。
    */
   private final Map<String, String> columnToPropertyOverrides;

   static {
       primitiveDefaults.put(Integer.TYPE, Integer.valueOf(0));
       primitiveDefaults.put(Short.TYPE, Short.valueOf((short) 0));
       primitiveDefaults.put(Byte.TYPE, Byte.valueOf((byte) 0));
       primitiveDefaults.put(Float.TYPE, Float.valueOf(0f));
       primitiveDefaults.put(Double.TYPE, Double.valueOf(0d));
       primitiveDefaults.put(Long.TYPE, Long.valueOf(0L));
       primitiveDefaults.put(Boolean.TYPE, Boolean.FALSE);
       primitiveDefaults.put(Character.TYPE, Character.valueOf((char) 0));
   }

   private ModelProcessor() {
       this(new HashMap<String, String>());
   }
   
   public static ModelProcessor getInstance(){
   	return processor;
   }

/**
 * 初始化带列名称的Bean处理器
 * @param columnToPropertyOverrides 列属性名称集合。
 */
public ModelProcessor(Map<String, String> columnToPropertyOverrides) {
   	super();
       if (columnToPropertyOverrides == null) {
           throw new IllegalArgumentException("columnToPropertyOverrides 集合不能为null!");
       }
       this.columnToPropertyOverrides = columnToPropertyOverrides;
   }

   /**
    * Convert a <code>ResultSet</code> row into a JavaBean.  This
    * implementation uses reflection and <code>BeanInfo</code> classes to
    * match column names to bean property names.  Properties are matched to
    * columns based on several factors:
    * <br/>
    * <ol>
    *     <li>
    *     The class has a writable property with the same name as a column.
    *     The name comparison is case insensitive.
    *     </li>
    *
    *     <li>
    *     The column type can be converted to the property's set method
    *     parameter type with a ResultSet.get* method.  If the conversion fails
    *     (ie. the property was an int and the column was a Timestamp) an
    *     SQLException is thrown.
    *     </li>
    * </ol>
    *
    * <p>
    * Primitive bean properties are set to their defaults when SQL NULL is
    * returned from the <code>ResultSet</code>.  Numeric fields are set to 0
    * and booleans are set to false.  Object bean properties are set to
    * <code>null</code> when SQL NULL is returned.  This is the same behavior
    * as the <code>ResultSet</code> get* methods.
    * </p>
    * @param <T> The type of bean to create
    * @param rs ResultSet that supplies the bean data
    * @param type Class from which to create the bean instance
    * @throws SQLException if a database access error occurs
    * @return the newly created bean
    */
   public <T> T toBean(ResultSet rs, Class<T> type) throws SQLException {

       PropertyDescriptor[] props = this.propertyDescriptors(type);
       ResultSetMetaData rsmd = rs.getMetaData();
       int[] columnToProperty = this.mapColumnsToProperties(rsmd, props,type);

       return this.createBean(rs, type, props, columnToProperty);
   }

   /**
    * Convert a <code>ResultSet</code> into a <code>List</code> of JavaBeans.
    * This implementation uses reflection and <code>BeanInfo</code> classes to
    * match column names to bean property names. Properties are matched to
    * columns based on several factors:
    * <br/>
    * <ol>
    *     <li>
    *     The class has a writable property with the same name as a column.
    *     The name comparison is case insensitive.
    *     </li>
    *
    *     <li>
    *     The column type can be converted to the property's set method
    *     parameter type with a ResultSet.get* method.  If the conversion fails
    *     (ie. the property was an int and the column was a Timestamp) an
    *     SQLException is thrown.
    *     </li>
    * </ol>
    *
    * <p>
    * Primitive bean properties are set to their defaults when SQL NULL is
    * returned from the <code>ResultSet</code>.  Numeric fields are set to 0
    * and booleans are set to false.  Object bean properties are set to
    * <code>null</code> when SQL NULL is returned.  This is the same behavior
    * as the <code>ResultSet</code> get* methods.
    * </p>
    * @param <T> The type of bean to create
    * @param rs ResultSet that supplies the bean data
    * @param type Class from which to create the bean instance
    * @throws SQLException if a database access error occurs
    * @return the newly created List of beans
    */
   public <T> List<T> toBeanList(ResultSet rs, Class<T> type) throws SQLException {
       List<T> results = new ArrayList<T>();

       if (!rs.next()) {
           return results;
       }

       PropertyDescriptor[] props = this.propertyDescriptors(type);
       ResultSetMetaData rsmd = rs.getMetaData();
       int[] columnToProperty = this.mapColumnsToProperties(rsmd, props,type);

       do {
           results.add(this.createBean(rs, type, props, columnToProperty));
       } while (rs.next());

       return results;
   }

   /**
    * Creates a new object and initializes its fields from the ResultSet.
    * @param <T> The type of bean to create
    * @param rs The result set.
    * @param type The bean type (the return type of the object).
    * @param props The property descriptors.
    * @param columnToProperty The column indices in the result set.
    * @return An initialized object.
    * @throws SQLException if a database error occurs.
    */
   private <T> T createBean(ResultSet rs, Class<T> type,PropertyDescriptor[] props, int[] columnToProperty)throws SQLException {

       T bean = this.newInstance(type);

       for (int i = 1; i < columnToProperty.length; i++) {

           if (columnToProperty[i] == PROPERTY_NOT_FOUND) {
               continue;
           }

           PropertyDescriptor prop = props[columnToProperty[i]];
           Class<?> propType = prop.getPropertyType();

           Object value = this.processColumn(rs, i, propType);

           if (propType != null && value == null && propType.isPrimitive()) {
               value = primitiveDefaults.get(propType);
           }

           this.callSetter(bean, prop, value);
       }

       return bean;
   }

   /**
    * Calls the setter method on the target object for the given property.
    * If no setter method exists for the property, this method does nothing.
    * @param target The object to set the property on.
    * @param prop The property to set.
    * @param value The value to pass into the setter.
    * @throws SQLException if an error occurs setting the property.
    */
   private void callSetter(Object target, PropertyDescriptor prop, Object value)throws SQLException {

       Method setter = prop.getWriteMethod();

       if (setter == null) {
           return;
       }

       Class<?>[] params = setter.getParameterTypes();
       try {
           // convert types for some popular ones
           if (value instanceof java.util.Date) {
               final String targetType = params[0].getName();
               if ("java.sql.Date".equals(targetType)) {
                   value = new java.sql.Date(((java.util.Date) value).getTime());
               } else
               if ("java.sql.Time".equals(targetType)) {
                   value = new java.sql.Time(((java.util.Date) value).getTime());
               } else
               if ("java.sql.Timestamp".equals(targetType)) {
                   value = new java.sql.Timestamp(((java.util.Date) value).getTime());
               }
           }

           // Don't call setter if the value object isn't the right type
           if (this.isCompatibleType(value, params[0])) {
               setter.invoke(target, new Object[]{value});
           } else {
             throw new SQLException(
                 "Cannot set " + prop.getName() + ": incompatible types, cannot convert "
                 + value.getClass().getName() + " to " + params[0].getName());
                 // value cannot be null here because isCompatibleType allows null
           }

       } catch (IllegalArgumentException e) {
           throw new SQLException(
               "Cannot set " + prop.getName() + ": " + e.getMessage());

       } catch (IllegalAccessException e) {
           throw new SQLException(
               "Cannot set " + prop.getName() + ": " + e.getMessage());

       } catch (InvocationTargetException e) {
           throw new SQLException(
               "Cannot set " + prop.getName() + ": " + e.getMessage());
       }
   }

   /**
    * ResultSet.getObject() returns an Integer object for an INT column.  The
    * setter method for the property might take an Integer or a primitive int.
    * This method returns true if the value can be successfully passed into
    * the setter method.  Remember, Method.invoke() handles the unwrapping
    * of Integer into an int.
    *
    * @param value The value to be passed into the setter method.
    * @param type The setter's parameter type (non-null)
    * @return boolean True if the value is compatible (null => true)
    */
   private boolean isCompatibleType(Object value, Class<?> type) {
       // Do object check first, then primitives
       if (value == null || type.isInstance(value)) {
           return true;

       } else if (type.equals(Integer.TYPE) && Integer.class.isInstance(value)) {
           return true;

       } else if (type.equals(Long.TYPE) && Long.class.isInstance(value)) {
           return true;

       } else if (type.equals(Double.TYPE) && Double.class.isInstance(value)) {
           return true;

       } else if (type.equals(Float.TYPE) && Float.class.isInstance(value)) {
           return true;

       } else if (type.equals(Short.TYPE) && Short.class.isInstance(value)) {
           return true;

       } else if (type.equals(Byte.TYPE) && Byte.class.isInstance(value)) {
           return true;

       } else if (type.equals(Character.TYPE) && Character.class.isInstance(value)) {
           return true;

       } else if (type.equals(Boolean.TYPE) && Boolean.class.isInstance(value)) {
           return true;

       }
       return false;

   }

   /**
    * Factory method that returns a new instance of the given Class.  This
    * is called at the start of the bean creation process and may be
    * overridden to provide custom behavior like returning a cached bean
    * instance.
    * @param <T> The type of object to create
    * @param c The Class to create an object from.
    * @return A newly created object of the Class.
    * @throws SQLException if creation failed.
    */
   protected <T> T newInstance(Class<T> c) throws SQLException {
       try {
           return c.newInstance();
       } catch (InstantiationException e) {
           throw new SQLException("Cannot create " + c.getName() + ": " + e.getMessage());

       } catch (IllegalAccessException e) {
           throw new SQLException("Cannot create " + c.getName() + ": " + e.getMessage());
       }
   }

   /**
    * Returns a PropertyDescriptor[] for the given Class.
    *
    * @param c The Class to retrieve PropertyDescriptors for.
    * @return A PropertyDescriptor[] describing the Class.
    * @throws SQLException if introspection failed.
    */
   private PropertyDescriptor[] propertyDescriptors(Class<?> c)
       throws SQLException {
       // Introspector caches BeanInfo classes for better performance
       BeanInfo beanInfo = null;
       try {
           beanInfo = Introspector.getBeanInfo(c);

       } catch (IntrospectionException e) {
           throw new SQLException(
               "Bean introspection failed: " + e.getMessage());
       }

       return beanInfo.getPropertyDescriptors();
   }

   /**
    * The positions in the returned array represent column numbers.  The
    * values stored at each position represent the index in the
    * <code>PropertyDescriptor[]</code> for the bean property that matches
    * the column name.  If no bean property was found for a column, the
    * position is set to <code>PROPERTY_NOT_FOUND</code>.
    *
    * @param rsmd The <code>ResultSetMetaData</code> containing column
    * information.
    *
    * @param props The bean property descriptors.
    *
    * @throws SQLException if a database access error occurs
    *
    * @return An int[] with column index to property index mappings.  The 0th
    * element is meaningless because JDBC column indexing starts at 1.
    */
   protected int[] mapColumnsToProperties(ResultSetMetaData rsmd,PropertyDescriptor[] props,Class<?> type) throws SQLException {

       int cols = rsmd.getColumnCount();
       int[] columnToProperty = new int[cols + 1];
       Arrays.fill(columnToProperty, PROPERTY_NOT_FOUND);

       for (int col = 1; col <= cols; col++) {
           String columnName = rsmd.getColumnLabel(col);
           if (null == columnName || 0 == columnName.length()) {
             columnName = rsmd.getColumnName(col);
           }
           String propertyName = columnToPropertyOverrides.get(columnName);
           if (propertyName == null) {
               propertyName=ModelParse.getFieldName(type,columnName);
               if(null==propertyName){
               	propertyName = columnName;
               }
           }
           for (int i = 0; i < props.length; i++) {

               if (propertyName.equalsIgnoreCase(props[i].getName())) {
                   columnToProperty[col] = i;
                   break;
               }
           }
       }

       return columnToProperty;
   }

   /**
    * Convert a <code>ResultSet</code> column into an object.  Simple
    * implementations could just call <code>rs.getObject(index)</code> while
    * more complex implementations could perform type manipulation to match
    * the column's type to the bean property type.
    *
    * <p>
    * This implementation calls the appropriate <code>ResultSet</code> getter
    * method for the given property type to perform the type conversion.  If
    * the property type doesn't match one of the supported
    * <code>ResultSet</code> types, <code>getObject</code> is called.
    * </p>
    *
    * @param rs The <code>ResultSet</code> currently being processed.  It is
    * positioned on a valid row before being passed into this method.
    *
    * @param index The current column index being processed.
    *
    * @param propType The bean property type that this column needs to be
    * converted into.
    *
    * @throws SQLException if a database access error occurs
    *
    * @return The object from the <code>ResultSet</code> at the given column
    * index after optional type processing or <code>null</code> if the column
    * value was SQL NULL.
    */
   protected Object processColumn(ResultSet rs, int index, Class<?> propType)throws SQLException {

       if ( !propType.isPrimitive() && rs.getObject(index) == null ) {
           return null;
       }

       if (propType.equals(String.class)) {
           return rs.getString(index);

       } else if (
           propType.equals(Integer.TYPE) || propType.equals(Integer.class)) {
           return Integer.valueOf(rs.getInt(index));

       } else if (
           propType.equals(Boolean.TYPE) || propType.equals(Boolean.class)) {
           return Boolean.valueOf(rs.getBoolean(index));

       } else if (propType.equals(Long.TYPE) || propType.equals(Long.class)) {
           return Long.valueOf(rs.getLong(index));

       } else if (
           propType.equals(Double.TYPE) || propType.equals(Double.class)) {
           return Double.valueOf(rs.getDouble(index));

       } else if (
           propType.equals(Float.TYPE) || propType.equals(Float.class)) {
           return Float.valueOf(rs.getFloat(index));

       } else if (
           propType.equals(Short.TYPE) || propType.equals(Short.class)) {
           return Short.valueOf(rs.getShort(index));

       } else if (propType.equals(Byte.TYPE) || propType.equals(Byte.class)) {
           return Byte.valueOf(rs.getByte(index));

       } else if (propType.equals(Timestamp.class)) {
           return rs.getTimestamp(index);

       } else if (propType.equals(SQLXML.class)) {
           return rs.getSQLXML(index);

       } else {
           return rs.getObject(index);
       }

   }

	@Override
	public Object[] toArray(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> toMap(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
 