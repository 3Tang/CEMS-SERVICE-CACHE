package com.vrv.cems.service.cache.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.ColumnListHandler;


import com.vrv.cems.service.cache.bean.CInstallPackBean;
import com.vrv.cems.service.cache.bean.CUpgradePackBean;
import com.vrv.cems.service.cache.bean.DBDeviceProdBean;
import com.vrv.cems.service.cache.bean.DeviceBean;
import com.vrv.cems.service.cache.bean.PolicyBean;
import com.vrv.cems.service.cache.bean.UserBean;
import com.vrv.cems.service.cache.service.JobDataFromDBService;
import com.vrv.cems.service.dbtools.DBUtil;


public class JobDataFromDBServiceImpl implements JobDataFromDBService{

	@Override
	public List<PolicyBean> getPolicyBeanByAll() {
		List<PolicyBean> policyBeanList=new ArrayList<PolicyBean>();
		List<PolicyBean> newPolicyBeanList=new ArrayList<PolicyBean>();
		Map<String,String> conditions = new HashMap<String,String>();
		
		conditions.put("isPublish", "1");//approvalState
		conditions.put("approveState", "1");
		conditions.put("state", "0");
		
		policyBeanList=DBUtil.queryConditionsByList(PolicyBean.class, conditions);
		List<String> targetList=new ArrayList<String>();
		
		for(PolicyBean pb:policyBeanList)	
		{  
			StringBuffer policyTarget=new StringBuffer();
			String sql="select b.target from cems_policy_target_mapping a,cems_policy_target b where  a.targetId=b.id and a.policyId=?";
			try {
				targetList=DBUtil.queryRunner.query(sql, new ColumnListHandler<String>("target"),pb.getId());
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			for(int i=0;i<targetList.size();i++)
			{	
				if(i>=1&&i<=targetList.size()-1){
					 policyTarget.append("|");
				}
				policyTarget.append(targetList.get(i));
			}
			pb.setObj(policyTarget.toString());
			
			newPolicyBeanList.add(pb);
		}
		
		return newPolicyBeanList;
	}
	
	
	

	@Override
	public List<UserBean> getUserBeanByAll() {
		List<UserBean> userBeanList=new ArrayList<UserBean>();
		userBeanList=DBUtil.queryByList(UserBean.class);
		return userBeanList;
	}
	
	
	

	@Override
	public List<DeviceBean> getDeviceBeanByAll() {
		List<DeviceBean> deviceBeanList=new ArrayList<DeviceBean>();
		deviceBeanList=DBUtil.queryByList(DeviceBean.class);
		return deviceBeanList;
	}
	
	
	

	/* （非 Javadoc）
	 * @see com.vrv.cems.service.cache.service.JobDataFromDBService#getCInstallPackBeanByAll()
	 */
	@Override
	public List<CInstallPackBean> getCInstallPackBeanByAll() {
		List<CInstallPackBean> cInstallPackBeanList=new ArrayList<CInstallPackBean>();
		cInstallPackBeanList=DBUtil.queryByList(CInstallPackBean.class);
		return cInstallPackBeanList;
	}




	@Override
	public List<DBDeviceProdBean> getNewProdByAll() {
		List<DBDeviceProdBean> newProdList=new ArrayList<DBDeviceProdBean>();
		newProdList=DBUtil.queryByList(DBDeviceProdBean.class);
		return newProdList;
	}




	@Override
	public List<CUpgradePackBean> getCUpgradeBeanByAll() {
		List<CUpgradePackBean> cUpgradeBeanList=new ArrayList<CUpgradePackBean>();
		cUpgradeBeanList=DBUtil.queryByList(CUpgradePackBean.class);
		return cUpgradeBeanList;
	}




	/*
	* Title: getPolicyBeanClean
	*Description: 
	* @return 
	* @see com.vrv.cems.service.cache.service.JobDataFromDBService#getPolicyBeanClean() 
	*/
	@Override
	public List<PolicyBean> getPolicyBeanClean() {
		Map<String,String> conditions = new HashMap<String,String>();
		
		conditions.put("isPublish", "1");//approvalState
		conditions.put("approveState", "1");
		conditions.put("state", "2");
		
		return DBUtil.queryConditionsByList(PolicyBean.class, conditions);
		
	}
	


	
	
}
