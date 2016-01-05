package com.vrv.cems.service.cache.service.impl; 

import org.apache.commons.lang3.StringUtils;

import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.base.interfaces.ICacheService;
import com.vrv.cems.service.cache.service.IP2DevOnlyIdService;
import com.vrv.cems.service.cache.util.RedisUtil;

/** 
 *   <B>说       明</B>:IP与DevOnlyId关系Service实现类(默认)
 *
 * @author  作  者  名：glw<br/>
 *		    E-mail ：linwu_gao@163.com
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年4月22日 上午10:03:00 
 */
public class IP2DevOnlyIdServiceImpl implements IP2DevOnlyIdService {

	@Override
	public Result saveIP2DevOnlyId(String maxCode, String minCode, String ip,
			String devOnlyId) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.SAVEIP2DEVONLYID)){
			log.info("保存IP与DevOnlyId关系参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("保存IP与DevOnlyId关系参数不正确[功能号minCode不正确]!");

		}else if (StringUtils.isBlank(ip)) {
			log.info("保存IP与DevOnlyId关系到缓存失败[IP为空]!");
			result.setCode(1);
			result.setInfo("保存IP与DevOnlyId关系到缓存失败[IP为空]!");
		} else if (StringUtils.isBlank(devOnlyId)) {
			log.info("保存IP与DevOnlyId关系到缓存失败[devOnlyId为空]!");
			result.setCode(2);
			result.setInfo("保存IP与DevOnlyId关系到缓存失败[mac为空]!");
		}else {
			try{
			RedisUtil.getInstance().set(
					ICacheService.PREFIX_IPCACHE+ip, devOnlyId);
			log.info("保存IP与DevOnlyId关系到缓存成功!");
			result.setCode(0);
			result.setInfo("保存IP与DevOnlyId关系到缓存成功!");
			}
			catch(Exception e)
			{ 
				
				log.info("缓存saveIP2DevOnlyId异常!"+e.getMessage());
				result.setCode(3);
				result.setInfo("缓存saveIP2DevOnlyId异常![设备devOnlyId"+devOnlyId+"异常]!");
				return result;
			}
		
		}
		return result;
	}

	@Override
	public Result updateIP2DevOnlyId(String maxCode, String minCode, String ip,
			String devOnlyId) {
		Result result = new Result();
		if(!minCode.equals(ICacheService.UPDATEIP2DEVONLYID)){
			log.info("更新IP与DevOnlyId关系参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("保存IP与DevOnlyId关系参数不正确[功能号minCode不正确]!");

		}else if (StringUtils.isBlank(ip)) {
			log.info("更新IP与DevOnlyId关系到缓存失败[IP为空]!");
			result.setCode(1);
			result.setInfo("更新IP与DevOnlyId关系到缓存失败[IP为空]!");
		} else if (StringUtils.isBlank(devOnlyId)) {
			log.info("更新IP与DevOnlyId关系到缓存失败[devOnlyId为空]!");
			result.setCode(2);
			result.setInfo("保存IP与DevOnlyId关系到缓存失败[mac为空]!");
		} else {
			try{
			RedisUtil.getInstance().set(
					ICacheService.PREFIX_IPCACHE +ip, devOnlyId);
			log.info("更新IP与DevOnlyId关系到缓存成功!");
			result.setCode(0);
			result.setInfo("更新IP与DevOnlyId关系到缓存成功!");
			}
			catch(Exception e)
			{ 
				
				log.info("缓存updateIP2DevOnlyId异常!"+e.getMessage());
				result.setCode(3);
				result.setInfo("缓存updateIP2DevOnlyId异常![设备devOnlyId"+devOnlyId+"异常]!");
				return result;
			}
			
		}
		return result;
	}

	@Override
	public String queryIP2DevOnlyId(String maxCode, String minCode, String ip) {
		Result result = new Result();
		String devOnlyId = "";
		if(!minCode.equals(ICacheService.QUERYIP2DEVONLYID)){
			log.info("查询IP与DevOnlyId关系参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("查询IP与DevOnlyId关系参数不正确[功能号minCode不正确]!");

		}else if (StringUtils.isBlank(ip)) {
			log.info("查询IP与DevOnlyId关系到缓存失败[IP为空]!");
			result.setCode(1);
			result.setInfo("查询IP与DevOnlyId关系到缓存失败[IP为空]!");
		}  else {
			try{
			devOnlyId=RedisUtil.getInstance().get(
					ICacheService.PREFIX_IPCACHE +ip);
			if(StringUtils.isNotBlank(devOnlyId)){
			log.info("查询IP与DevOnlyId关系到缓存成功!");
			result.setCode(0);
			result.setInfo("查询IP与DevOnlyId关系到缓存成功!");
			}
			}
			catch(Exception e)
			 {	 
				 log.info("从缓存中查询IP与DevOnlyId关系失败[devOnlyId=" + devOnlyId
							+ " 对应的值在缓存中未找到]!"+e.getLocalizedMessage());
				    result.setCode(3);
					result.setInfo("从缓存中查询IP与DevOnlyId关系失败[devOnlyId=" + devOnlyId
							+ " 对应的值在缓存中未找到]!");
					devOnlyId=new String("\0");
					return devOnlyId;
			 }
			
		}
		return devOnlyId;
	}

	@Override
	public Result deleteIP2DevOnlyId(String maxCode, String minCode, String ip) {
		Result result = new Result();
		Long tempResult;
		if(!minCode.equals(ICacheService.QUERYIP2DEVONLYID)){
			log.info("查询IP与DevOnlyId关系参数不正确[功能号minCode不正确]!");
			result.setCode(4);
			result.setInfo("查询IP与DevOnlyId关系参数不正确[功能号minCode不正确]!");

		}else if (StringUtils.isBlank(ip)) {
			
			log.info("删除IP与DevOnlyId关系到缓存失败[IP为空]!");
			result.setCode(1);
			result.setInfo("保存IP与DevOnlyId关系到缓存失败[IP为空]!");
		}  else {
			
			try{
			 tempResult = RedisUtil.getInstance().delete(ICacheService.PREFIX_IPCACHE +ip);
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
				result.setInfo("从缓存中删除IP与DevOnlyId关系成功");
			}

			log.info("删除返回为" + tempResult);
		}
		return result;
	}

}
 