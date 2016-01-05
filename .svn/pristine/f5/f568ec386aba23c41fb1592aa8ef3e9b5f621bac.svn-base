package com.vrv.cems.service.cache.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.base.interfaces.ICacheService;
import com.vrv.cems.service.cache.bean.CInstallPackBean;
import com.vrv.cems.service.cache.service.IPMAC2DevOnlyIdService;
import com.vrv.cems.service.cache.util.RedisUtil;
import com.vrv.cems.service.cache.util.ReflectUtils;

/**
 * <B>说 明</B>:IPMAC与DevOnlyId关系Service实现类(默认)
 * 
 * @author 作 者 名：glw<br/>
 *         E-mail ：linwu_gao@163.com
 * 
 * @version 版 本 号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午10:01:23
 */
public class IPMAC2DevOnlyIdServiceImpl implements IPMAC2DevOnlyIdService {

	@Override
	public Result saveIPMAC2DevOnlyId(String maxCode, String minCode,
			String ip, String mac, String devOnlyId) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.SAVEIPMAC2DEVONLYID)){
			log.info("保存IPMAC与DevOnlyId关系参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("保存IPMAC与DevOnlyId关系参数不正确[功能号minCode不正确]!");

		}else if (StringUtils.isBlank(ip)) {
			log.info("保存IPMAC与DevOnlyId关系到缓存失败[IP为空]!");
			result.setCode(1);
			result.setInfo("保存IPMAC与DevOnlyId关系到缓存失败[IP为空]!");
		} else if (StringUtils.isBlank(mac)) {
			log.info("保存IPMAC与DevOnlyId关系到缓存失败[mac为空]!");
			result.setCode(2);
			result.setInfo("保存IPMAC与DevOnlyId关系到缓存失败[mac为空]!");
		} else if (StringUtils.isBlank(devOnlyId)) {
			log.info("保存IPMAC与DevOnlyId关系到缓存失败[devOnlyId为空]!");
			result.setCode(3);
			result.setInfo("保存IPMAC与DevOnlyId关系到缓存失败[devOnlyId为空]!");
		} else {
			try{
			RedisUtil.getInstance().set(
					ICacheService.PREFIX_IPMACCACHE + ip + mac, devOnlyId);
			log.info("保存IPMAC与DevOnlyId关系到缓存成功!");
			result.setCode(0);
			result.setInfo("保存IPMAC与DevOnlyId关系到缓存成功!");
			}
			catch(Exception e)
			{ 
				
				log.info("缓存saveIPMAC2DevOnlyId异常!"+e.getMessage());
				result.setCode(3);
				result.setInfo("缓存saveIPMAC2DevOnlyId异常![设备devOnlyId"+devOnlyId+"异常]!");
				return result;
			}
			
		}
		return result;
	}

	@Override
	public Result updateIPMAC2DevOnlyId(String maxCode, String minCode,
			String ip, String mac, String devOnlyId) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.UPDATEIPMAC2DEVONLYID)){
			log.info("更新IPMAC与DevOnlyId关系参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("更新IPMAC与DevOnlyId关系参数不正确[功能号minCode不正确]!");
		}else if (StringUtils.isBlank(ip)) {
			log.info("更新IPMAC与DevOnlyId关系到缓存失败[IP为空]!");
			result.setCode(1);
			result.setInfo("保存IPMAC与DevOnlyId关系到缓存失败[IP为空]!");
		} else if (StringUtils.isBlank(mac)) {
			log.info("更新IPMAC与DevOnlyId关系到缓存失败[mac为空]!");
			result.setCode(2);
			result.setInfo("保存IPMAC与DevOnlyId关系到缓存失败[mac为空]!");
		} else if (StringUtils.isBlank(devOnlyId)) {
			log.info("更新IPMAC与DevOnlyId关系到缓存失败[devOnlyId为空]!");
			result.setCode(3);
			result.setInfo("更新IPMAC与DevOnlyId关系到缓存失败[devOnlyId为空]!");
		} else {
			try{
			RedisUtil.getInstance().set(
					ICacheService.PREFIX_IPMACCACHE + ip + mac, devOnlyId);
			log.info("更新IPMAC与DevOnlyId关系到缓存成功!");
			result.setCode(0);
			result.setInfo("更新IPMAC与DevOnlyId关系到缓存成功!");
			}
			catch(Exception e)
			{ 
				
				log.info("缓存updateIPMAC2DevOnlyId异常!"+e.getMessage());
				result.setCode(3);
				result.setInfo("缓存updateIPMAC2DevOnlyId异常![设备devOnlyId"+devOnlyId+"异常]!");
				return result;
			}
		
		}
		return result;
	}

	@Override
	public String queryIPMAC2DevOnlyId(String maxCode, String minCode,
			String ip, String mac) {
		Result result = new Result();
		String devOnlyId = "";
		if(!minCode.equals(ICacheService.QUERYIPMAC2DEVONLYID)){
			log.info("查询IPMAC与DevOnlyId关系参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("查询IPMAC与DevOnlyId关系参数不正确[功能号minCode不正确]!");

		}else if (StringUtils.isBlank(ip)) {
			log.info("查询IPMAC与DevOnlyId关系到缓存失败[IP为空]!");
			result.setCode(1);
			result.setInfo("保存IPMAC与DevOnlyId关系到缓存失败[IP为空]!");
		} else if (StringUtils.isBlank(mac)) {
			log.info("查询IPMAC与DevOnlyId关系到缓存失败[mac为空]!");
			result.setCode(2);
			result.setInfo("保存IPMAC与DevOnlyId关系到缓存失败[mac为空]!");
		} else {
			try{
			devOnlyId=RedisUtil.getInstance().get(
					ICacheService.PREFIX_IPMACCACHE + ip + mac);
			if(StringUtils.isNotBlank(devOnlyId)){
			log.info("查询IPMAC与DevOnlyId关系到缓存成功!");
			result.setCode(0);
			result.setInfo("查询IPMAC与DevOnlyId关系到缓存成功!");
			}
			}
			 catch(Exception e)
			 {	 
				 log.info("从缓存中查询IPMAC与DevOnlyId关系失败[输入 devOnlyId=" + devOnlyId
							+ " 对应的值在缓存中未找到]!"+e.getLocalizedMessage());
				    result.setCode(3);
					result.setInfo("从缓存中查询IPMAC与DevOnlyId关系失败[输入 devOnlyId=" + devOnlyId
							+ " 对应的值在缓存中未找到]!");
					devOnlyId=new String("\0");
					return devOnlyId;
			 }
			
		}
		return  devOnlyId;
	}

	@Override
	public Result deleteIPMAC2DevOnlyId(String maxCode, String minCode,
			String ip, String mac) {
		Result result = new Result();
		Long tempResult ;
		if(!minCode.equals(ICacheService.DELETEIPMAC2DEVONLYID)){
			log.info("删除IPMAC与DevOnlyId关系参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("删除IPMAC与DevOnlyId关系参数不正确[功能号minCode不正确]!");

		}else if (StringUtils.isBlank(ip)) {
			log.info("删除IPMAC与DevOnlyId关系到缓存失败[IP为空]!");
			result.setCode(1);
			result.setInfo("删除IPMAC与DevOnlyId关系到缓存失败[IP为空]!");
		} else if (StringUtils.isBlank(mac)) {
			log.info("删除IPMAC与DevOnlyId关系到缓存失败[mac为空]!");
			result.setCode(2);
			result.setInfo("删除IPMAC与DevOnlyId关系到缓存失败[mac为空]!");
		}  else {
			try{
				tempResult= RedisUtil.getInstance().delete(ICacheService.PREFIX_IPMACCACHE +  ip + mac);
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
				result.setInfo("从缓存中删除IPMAC与DevOnlyId关系成功");
			}

			log.info("删除返回为" + tempResult);
		}
		return result;
	}

}
