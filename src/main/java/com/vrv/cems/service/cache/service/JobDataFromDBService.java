package com.vrv.cems.service.cache.service;
import java.sql.SQLException;
import java.util.List;

import com.vrv.cems.service.cache.bean.CInstallPackBean;
import com.vrv.cems.service.cache.bean.CUpgradePackBean;
import com.vrv.cems.service.cache.bean.DBDeviceProdBean;
import com.vrv.cems.service.cache.bean.DeviceBean;
import com.vrv.cems.service.cache.bean.PolicyBean;
import com.vrv.cems.service.cache.bean.UserBean;


/** 
 *   <B>说       明</B>:JobDataFromDBService 从DB中取出数据
 *
 * @author  作  者  名 ：tangtieqiao<br/>
 *		    E-mail ：tangtieqiao@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年5月12日上午9:03:48 
 */
public interface JobDataFromDBService {

	 
	public List<PolicyBean> getPolicyBeanByAll();
	
	public List<PolicyBean> getPolicyBeanClean();
	
	
	public List<UserBean> getUserBeanByAll();
	
	
	public List<DeviceBean> getDeviceBeanByAll();
	
	
	public List<CInstallPackBean> getCInstallPackBeanByAll();
	
	
	public List<DBDeviceProdBean> getNewProdByAll();
	
	
	public List<CUpgradePackBean> getCUpgradeBeanByAll();
	
	
}
