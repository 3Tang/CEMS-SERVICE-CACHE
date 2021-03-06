package com.vrv.cems.service.cache.service;

import java.util.ArrayList;
import java.util.List;



import org.junit.Test;

import com.vrv.cems.service.cache.bean.CInstallPackBean;
import com.vrv.cems.service.cache.bean.CUpgradePackBean;
import com.vrv.cems.service.cache.bean.DBDeviceProdBean;
import com.vrv.cems.service.cache.bean.DeviceBean;
import com.vrv.cems.service.cache.bean.PolicyBean;
import com.vrv.cems.service.cache.bean.UserBean;
import com.vrv.cems.service.cache.core.Services;



/** 
 *   <B>说       明</B>:JobServiceJOB服务类
 *
 * @author  作  者  名 ：tangtieqiao<br/>
 *		    E-mail ：tangtieqiao@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年5月12日上午8:57:31 
 */
public class JobService {

	
	//private  List<CInstallPackBean> cInstallPackBeanList=new ArrayList<CInstallPackBean>();
	private static final JobService jobService = new JobService();
	//private JobService(){}
	
	public static JobService getInstance(){
		return jobService;
	}
	
	
	public void policyCacheRefresh()
	{
		List<PolicyBean> policyBeanList=Services.jobDataFromDBService.getPolicyBeanByAll(); 
		List<PolicyBean> policyBeanDelList=Services.jobDataFromDBService.getPolicyBeanClean();
		if(policyBeanList!=null&&policyBeanList.size()>0){
		Services.dbJobData2RedisService.putData2PolicyCache(policyBeanList);
		Services.dbJobData2RedisService.delData2PolicyCache(policyBeanDelList);
		}
	}
	

	
	public void deviceCacheRefresh()
	{
		
		List<DeviceBean>  deviceBeanList=Services.jobDataFromDBService.getDeviceBeanByAll(); 
		if(deviceBeanList!=null&&deviceBeanList.size()>0){
			Services.dbJobData2RedisService.putData2DeviceCache(deviceBeanList);
		}
	}
	
	@Test
	public void deviceCacheRefreshTest()
	{
		
		List<DeviceBean>  deviceBeanList=Services.jobDataFromDBService.getDeviceBeanByAll(); 
		
		Services.dbJobData2RedisService.putData2DeviceCache(deviceBeanList);
		
	}
	
	
	public void userCacheRefresh()
	{
		List<UserBean> userBeanList=Services.jobDataFromDBService.getUserBeanByAll(); 
		if(userBeanList!=null&&userBeanList.size()>0){
			Services.dbJobData2RedisService.putData2UserCache(userBeanList);
		}
	}
	

	/** 
	* @Title: productCacheRefresh 
	* @Description: 从数据里取出产品信息,把DB里的产品信息放入Redis缓存
	* @param     设定文件 
	* @return void    返回类型 
	* @throws  
	*/ 
	
	public void productCacheRefresh()
	{
		//安装包
		 List<CInstallPackBean> cInstallPackBeanList=Services.jobDataFromDBService.getCInstallPackBeanByAll(); 
		 if(cInstallPackBeanList!=null&&cInstallPackBeanList.size()>0){
			 Services.dbJobData2RedisService.putData2CInstallPackCache(cInstallPackBeanList);
		 }
		 
		 //已安装
		 List<DBDeviceProdBean> newProdList=Services.jobDataFromDBService.getNewProdByAll();
		 if(newProdList!=null&&newProdList.size()>0){
			 Services.dbJobData2RedisService.putData2DevInsProOld(newProdList);
		 }
		 
		 //升级包
		  List<CUpgradePackBean> cUpgradeBeanList= Services.jobDataFromDBService.getCUpgradeBeanByAll();
		  if(cUpgradeBeanList!=null&cUpgradeBeanList.size()>0){
			  Services.dbJobData2RedisService.putData2ProductCUpgradePack(cUpgradeBeanList);
		  }
	}
	

	
	
	public void PolicyXmlJob()
	{
		
	}
}
