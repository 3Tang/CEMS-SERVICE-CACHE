package com.vrv.cems.service.cache.service; 

import java.util.Map;

import org.apache.log4j.Logger;

import com.vrv.cems.service.base.bean.cache.PolicyCache;
import com.vrv.cems.service.base.bean.cache.Result;

/** 
 *   <B>说       明</B>:策略信息Service接口
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午9:58:44 
 */
public interface PolicyService {

	public Logger log = Logger.getLogger(PolicyService.class);

	/**
	 * 保存策略信息
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1101
	 * @param policyCache	需要保存的策略信息
	 * @return
	 *		Result(0,保存策略信息到缓存成功.);;
	 *		Result(1,保存策略信息到缓存失败[策略信息为空]!);;
	 *		Result(2,保存策略信息到缓存失败[策略信息policyId为空]!);;
	 */
	public Result savePolicy(String maxCode,String minCode,PolicyCache policyCache);
	
	/**
	 * 更新策略信息
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1102
	 * @param policyCache	需要保存的策略信息
	 * @return
	 *		Result(0,更新策略信息到缓存成功.);;
	 *		Result(1,更新策略信息到缓存失败[策略信息为空]!);;
	 *		Result(2,更新策略信息到缓存失败[策略信息policyId为空]!);;
	 */
	public Result updatePolicy(String maxCode,String minCode,PolicyCache policyCache);
	
	/**
	 * 更新策略信息，更新指定的属性信息
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1103
	 * @param policyCache	需要更新的策略信息
	 * @return
	 *		Result(0,更新策略信息到缓存成功.);;
	 *		Result(1,更新策略信息到缓存失败[策略信息policyId为空]!);;
	 *		Result(2,更新策略信息到缓存失败[指定更新的策略属性信息Key和Value为空]!);;
	 */
	public Result updatePolicyByField(String maxCode,String minCode,String policyId,Map<String,String> fieldValueMap );
	
	/**
	 * 查询策略信息，根据策略ID 
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1104
	 * @param policyId		需要查询的策略ID
	 * @return
	 *		PolicyCache	需要查询的策略信息
	 */
	public PolicyCache queryPolicyByPolicyId(String maxCode,String minCode,String policyId);
	
	/**
	 * 删除策略信息，根据策略ID 
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1105
	 * @param policyId		需要删除的策略ID
	 * @return
	 *		Result	需要查询的策略信息
	 */
	public Result deletePolicyByPolicyId(String maxCode,String minCode,String policyId);
	
}
 