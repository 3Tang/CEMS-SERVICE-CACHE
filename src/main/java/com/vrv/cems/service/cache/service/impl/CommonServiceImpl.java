package com.vrv.cems.service.cache.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.base.interfaces.ICacheService;
import com.vrv.cems.service.cache.bean.OutParam;
import com.vrv.cems.service.cache.service.CommonService;
import com.vrv.cems.service.cache.util.CommonUtil;
import com.vrv.cems.service.cache.util.PolicyConfigService;
import com.vrv.cems.service.cache.util.RedisUtil;
import com.vrv.cems.service.quartz.ServiceParamBean;
import com.vrv.cems.service.init.SystemConstants;
/**
 * 
 * 
 * @author 作 者 名：唐铁桥; E-mail ：tangtieqiao@vrvmail.com.cn
 * 
 * @version 版 本 号：V1.0; 创建时间：2015年5月7日 下午2:09:42
 */
public class CommonServiceImpl implements CommonService {

	public static final List NULLjdata = new ArrayList();

	@Override
	public Result isExist(String maxCode, String minCode, String prefix,
			String key) {
		boolean isExist = false;
		Result result = new Result();
		if(!minCode.equals(ICacheService.ISEXIST))
		{
			log.info("验证信息参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("验证信息参数不正确[功能号minCode不正确]!");
			
		}
		else if (StringUtils.isBlank(prefix)) {
			log.info("验证信息参数不正确[前缀prifix为空]!");
			result.setCode(2);
			result.setInfo("验证信息参数不正确[前缀prifix为空]!");
		} else if (StringUtils.isBlank(key)) {
			log.info("验证信息参数不正确[key为空]!");
			result.setCode(3);
			result.setInfo("验证信息参数不正确[key为空]!");
		} else {
			isExist = RedisUtil.getInstance().exists(prefix + key);
			if (isExist) {
				log.info("缓存中验证该Key存在!");
				result.setCode(0);
				result.setInfo("缓存中验证该Key存在!");
			} else {
				log.info("缓存中验证该Key不存在!");
				result.setCode(1);
				result.setInfo("缓存中验证该Key不存在!");
			}

		}
		return result;
		
	}

	public ByteBuffer getDataTS(String maxCode, String minCode,
			String checkCode, boolean isZip, ByteBuffer data,
			boolean isEncrypt, String key,  int flag) {
		Result result = new Result();
		ByteBuffer fail = null;
		try {
			fail = CommonUtil.outParam2Json(new OutParam(maxCode, minCode,
					SystemConstants.F_FAIL, "更新在线服务策略失败", NULLjdata));
		} catch (UnsupportedEncodingException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		if(!minCode.equals(ICacheService.POLICY_XML))
		{
			log.info("更新在线服务策略参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("更新在线服务策略参数不正确[功能号minCode不正确]!");
			return fail;
		}else{
			
		log.info("更新在线服务策略");
		
		  //crc校验,解压缩,解密的逻辑 现在屏蔽
		//String dataStr= CommonUtil.tsParams2String(checkCode,isZip,data,isEncrypt,key,flag);
		String dataStr= CommonUtil.byteBufferToString(data);
		ByteBuffer buffer = null;
		log.info("输入的data为：" + data);
		if (StringUtils.isNotBlank(dataStr)) {
			try {
				 String xmlPath=SystemConstants.PATH_POLICY_XML;
				  log.info("缓存服务 策略接口 服务策略路径为"+xmlPath);
	     			String policyXmlStr = null;
	     			try {
	     				//服务本地策略文件
	     				policyXmlStr = PolicyConfigService.getInstance().policyXML2String(xmlPath);
	     			
	     			} catch (IOException e1) {
	     				// TODO 自动生成的 catch 块
	     				e1.printStackTrace();
	     			}
	     			
	     			
	     			if(StringUtils.isBlank(dataStr))
	     			{
	     				throw new RuntimeException("网页平台传入参数为空！");
	     			}
	     			
	     			if(StringUtils.isBlank(policyXmlStr))
	     			{
	     				throw new RuntimeException("本地策略文件为空！");
	     			}
	     			ServiceParamBean  servParamBean =new ServiceParamBean();
	         	    InputStream policyIn=new ByteArrayInputStream(dataStr.getBytes());
	         	    
	         	    //对比修改参数属性并启动配置
	         	    servParamBean=PolicyConfigService.getInstance().modifyLocalPolicyParam(dataStr, xmlPath);
	         	   
	             	//回写文件
	         	    PolicyConfigService.getInstance().localServicePolicyFileWrite(servParamBean);

				buffer=CommonUtil.outParam2Json(new OutParam(maxCode, minCode,
						SystemConstants.F_SUCCESS, "更新在线服务策略成功",
						NULLjdata));
				
				/*buffer =BaseEncryptDecryptUtils.encrypt(key, flag,buffer);*/
				
				return buffer;

			} catch (Exception e) {
				e.printStackTrace();
				/*return BaseEncryptDecryptUtils.encrypt(key,flag,fail);*/
				return fail;
			}
		}
		/*return  BaseEncryptDecryptUtils.encrypt(key,flag,fail);*/
		
		
		}
		return fail;
	}

	
	
	@Override
	public Result saveAccount2UserOnlyId(String maxCode, String minCode,
			String account, String userOnlyId) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.SAVEACCOUNT2USERONLYID)){
			log.info("保存account与userOnlyId对应关系参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("保存account与userOnlyId对应关系参数不正确[功能号minCode不正确]!");

		}else if (StringUtils.isBlank(account)) {
			log.info("保存account与userOnlyId对应关系到缓存失败[account为空]!");
			result.setCode(1);
			result.setInfo("保存保存account与userOnlyId对应关系到缓存失败[account为空]!");
		} else if (StringUtils.isBlank(userOnlyId)) {
			log.info("保存保存account与userOnlyId对应关系到缓存失败[userOnlyId为空]!");
			result.setCode(2);
			result.setInfo("保存保存account与userOnlyId对应关系到缓存失败[mac为空]!");
		}else {
			try{
			RedisUtil.getInstance().set(
					ICacheService.PREFIX_ACCOUNT+account, userOnlyId);
			log.info("保存保存account与userOnlyId对应关系到缓存成功!");
			result.setCode(0);
			result.setInfo("保存保存account与userOnlyId对应关系到缓存成功!");
			}
			catch(Exception e)
			{ 
				
				log.info("缓存saveaccount2userOnlyId异常!"+e.getMessage());
				result.setCode(3);
				result.setInfo("缓存saveaccount2userOnlyId异常![用户userOnlyId"+userOnlyId+"异常]!");
				return result;
			}
		
		}
		return result;
	}

	@Override
	public Result updateAccount2UserOnlyId(String maxCode, String minCode,
			String account, String userOnlyId) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.UPDATEACCOUNT2USERONLYID)){
			log.info("更新保存account与userOnlyId对应关系参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("保存保存account与userOnlyId对应关系参数不正确[功能号minCode不正确]!");

		}else if (StringUtils.isBlank(account)) {
			log.info("更新保存account与userOnlyId对应关系到缓存失败[account为空]!");
			result.setCode(1);
			result.setInfo("更新保存account与userOnlyId对应关系到缓存失败[account为空]!");
		} else if (StringUtils.isBlank(userOnlyId)) {
			log.info("更新保存account与userOnlyId对应关系到缓存失败[userOnlyId为空]!");
			result.setCode(2);
			result.setInfo("保存保存account与userOnlyId对应关系到缓存失败[mac为空]!");
		} else {
			try{
			RedisUtil.getInstance().set(
					ICacheService.PREFIX_ACCOUNT +account, userOnlyId);
			log.info("更新保存account与userOnlyId对应关系到缓存成功!");
			result.setCode(0);
			result.setInfo("更新保存account与userOnlyId对应关系到缓存成功!");
			}
			catch(Exception e)
			{ 
				
				log.info("缓存updateaccount2userOnlyId异常!"+e.getMessage());
				result.setCode(3);
				result.setInfo("缓存updateaccount2userOnlyId异常![用户userOnlyId"+userOnlyId+"异常]!");
				return result;
			}
			
		}
		return result;
	}

	@Override
	public String queryAccount2UserOnlyId(String maxCode, String minCode,
			String account) {
		Result result = new Result();
		String userOnlyId = "";
		if(!minCode.equals(ICacheService.QUERYACCOUNT2USERONLYID)){
			log.info("查询保存account与userOnlyId对应关系参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("查询保存account与userOnlyId对应关系参数不正确[功能号minCode不正确]!");

		}else if (StringUtils.isBlank(account)) {
			log.info("查询保存account与userOnlyId对应关系到缓存失败[account为空]!");
			result.setCode(1);
			result.setInfo("查询保存account与userOnlyId对应关系到缓存失败[account为空]!");
		}  else {
			try{
			userOnlyId=RedisUtil.getInstance().get(
					ICacheService.PREFIX_ACCOUNT +account);
			if(StringUtils.isNotBlank(userOnlyId)){
			log.info("查询保存account与userOnlyId对应关系到缓存成功!");
			result.setCode(0);
			result.setInfo("查询保存account与userOnlyId对应关系到缓存成功!");
			}
			}
			catch(Exception e)
			 {	 
				 log.info("从缓存中查询保存account与userOnlyId对应关系失败[userOnlyId=" + userOnlyId
							+ " 对应的值在缓存中未找到]!"+e.getLocalizedMessage());
				    result.setCode(3);
					result.setInfo("从缓存中查询保存account与userOnlyId对应关系失败[userOnlyId=" + userOnlyId
							+ " 对应的值在缓存中未找到]!");
					return userOnlyId;
			 }
			
		}
		return userOnlyId;
	}

	@Override
	public Result deleteAccount2UserOnlyId(String maxCode, String minCode,
			String account) {
		Result result = new Result();
		Long tempResult;
		if(!minCode.equals(ICacheService.DELETEACCOUNT2USERONLYID)){
			log.info("删除保存account与userOnlyId对应关系参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("删除保存account与userOnlyId对应关系参数不正确[功能号minCode不正确]!");

		}else if (StringUtils.isBlank(account)) {
			
			log.info("删除保存account与userOnlyId对应关系到缓存失败[account为空]!");
			result.setCode(1);
			result.setInfo("删除保存account与userOnlyId对应关系到缓存失败[account为空]!");
		}  else {
			
			try{
			 tempResult = RedisUtil.getInstance().delete(ICacheService.PREFIX_ACCOUNT +account);
			}
			catch(Exception e)
			{
				log.info("从缓存中删除设备信息失败!"+e.getMessage());
				result.setCode(3);
				result.setInfo("从缓存中删除设备信息失败!"+e.getMessage());
				tempResult=-1l;
				return result;
			}
			if (tempResult >= 0) {
				result.setCode(0);
				result.setInfo("从缓存中删除保存account与userOnlyId对应关系成功");
			}

			log.info("删除返回为" + tempResult);
		}
		return result;
	}
	}
	



