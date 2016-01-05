package com.vrv.cems.service.dbtools;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.log4j.Logger;

import com.sys.common.util.Assert;
import com.sys.common.util.db.dbutils.JdbcC3P0DBUtils;
import com.vrv.cems.service.base.SystemConstants;
import com.vrv.cems.service.base.interfaces.ICacheService;
import com.vrv.cems.service.cache.bean.DBServParamBean;


/** 
 *   <B>说       明</B>:连接数据库工具类。
 *
 * @author  作  者  名：陈 明<br/>
 *		    E-mail ：chenming@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月19日 下午2:16:58 
 */
public class DBUtil {
	private static Logger log = Logger.getLogger(DBUtil.class);
	@SuppressWarnings("rawtypes")
	private static BeanHandler beanHandler = null;
	public static QueryRunner queryRunner = null;
	private static final String SERVICEPARAM_TABLE_NAME = "cems_serviceParam";
	private static ConnectionPool pool = null;
	static {
		initTheardPool();
	}

	
	public static void initTheardPool() {
		DbUtils.loadDriver("com.mysql.jdbc.Driver");
		try {
			pool = ConnectionPool.getInstance(SystemConstants.PATH_JDBC_PROPERTIES);
		} catch (Exception e) {
			log.error("建立数据库连接池失败", e);
		}
		queryRunner = new QueryRunner(pool.mDataSource);
	}


	public static Map<String, Object> getColById(String sql, Object... params) {
		MapHandler mapHandler = new MapHandler();
		try {
			return queryRunner.query(sql, mapHandler, params);
		} catch (SQLException e) {
			log.error("查询报错", e);
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object SQLParser(Class<?> type, String sql, Object... params) {
		beanHandler = new BeanHandler(type);
		try {
			return queryRunner.query(sql, beanHandler, params);
		} catch (SQLException e) {
			log.error("查询报错", e);
			return null;
		}
	}
	

	
	
	/**
	 * 根据对象类型查询数据集,
	 * 默认根据主键进行降序排列。
	 * @param clazz 对象类型。
	 * @return 对象类型数据集。
	 */
	public static <T> List<T> queryByList(Class<T> clazz){
		 List<T> result=null;
		 try{
		   	result =(List<T>) queryRunner.query(SelectSQL.genSelectSQL(clazz),new BeanListHandler<T>(clazz,ModelProcessor.getInstance()));
		 }catch(Exception e){
			 log.error("查询数据集时出错!", e);
			 e.printStackTrace();
		 }
		 return result;
	}
	
	
	/**
	 * 根据服务ID查询服务参数信息。
	 * @param serviceId 服务ID。
	 * @return 服务参数对象。
	 */
	public static DBServParamBean queryByServiceId(String serviceId) {
		Assert.isNotBlank(serviceId, "服务ID不能为空！");
		DBServParamBean serviceParam = null;
		String sql = "select * from " + SERVICEPARAM_TABLE_NAME + " where serviceId = '"
				+ serviceId + "'";
		try {
			serviceParam = queryRunner.query(sql, new BeanHandler<DBServParamBean>(DBServParamBean.class));
		} catch (SQLException e) {
			log.error("根据服务ID查询服务默认配置参数时出错!", e);
		}
		return serviceParam;
	}
	/**
	 * 根据服务号查询服务参数信息。
	 * @param serviceCode 服务号。
	 * @return 服务参数对象。
	 */
	public  static DBServParamBean queryByServiceCode(String serviceCode) {
		Assert.isNotBlank(serviceCode, "服务号不能为空！");
		DBServParamBean serviceParam = null;
		
		String sql = "select * from " + SERVICEPARAM_TABLE_NAME + " where serviceCode = '"
				+ serviceCode + "'";
		try {
			serviceParam = queryRunner.query(sql, new BeanHandler<DBServParamBean>(DBServParamBean.class));
		} catch (SQLException e) {
			log.error("根据服务号查询服务默认配置参数时出错!", e);
		}
		return serviceParam;
	}



	
	/**
	 * 根据对象模型及条件语句查询多对象数据集。
	 * @param clazz 对象类型。
	 * @param conditions 添加语句集合
	 * @return 数据集。
	 */
	public static <T> List<T> queryConditionsByList(Class<T> clazz,Map<String, String> conditions){
		 List<T> result=null;
		 try{
			SqlModel model = new SqlModel(clazz);
			for(Map.Entry<String, String> conEntry : conditions.entrySet()){
				String fieldName = conEntry.getKey();
				String fieldValue = conEntry.getValue();
				model.getCondition().eq(fieldName,fieldValue);
			}
		   	result = (List<T>)queryRunner.query(SelectSQL.genSelectSQL(model),new BeanListHandler<T>(clazz,ModelProcessor.getInstance()));
		 }catch(Exception e){
			 log.error("多条件查询数据集时出错!", e);
			 e.printStackTrace();
		 }
		 return result;
	}

	/** 
	* @Title: getUserOnlyIdByDevOnlyId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devOnlyId
	* @param @return  参数说明 
	* @return List<String>    返回类型 
	* @throws 
	*/
	public static List<String> getUserOnlyIdByDevOnlyId(String devOnlyId) {
		List<String> userOnlyIds=new ArrayList<String>();
		MapListHandler mapListHandler=new MapListHandler();
		String sql = "select devOnlyId,userOnlyId,activeTime from  cems_user_device where devOnlyId = ?";
		Object[] params = { devOnlyId };
		 List<Map<String, Object>> mapList = null;
		try {
			mapList = queryRunner.query(sql, mapListHandler, params);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if (mapList != null) {
			for(Map<String,Object> map:mapList){
				String userOnlyId = map.get("userOnlyId").toString();
				userOnlyIds.add(userOnlyId);
			}
			return userOnlyIds;
		}
		return null;
		
	}
	
	
}
