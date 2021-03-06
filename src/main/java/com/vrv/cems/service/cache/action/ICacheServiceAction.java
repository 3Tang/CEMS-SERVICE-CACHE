package com.vrv.cems.service.cache.action;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.vrv.cems.service.base.bean.cache.CInstallPackCache;
import com.vrv.cems.service.base.bean.cache.CUpgradePackCache;
import com.vrv.cems.service.base.bean.cache.DeviceCache;
import com.vrv.cems.service.base.bean.cache.DeviceKeyCache;
import com.vrv.cems.service.base.bean.cache.DeviceOnlineCache;
import com.vrv.cems.service.base.bean.cache.DevicePolicyCache;
import com.vrv.cems.service.base.bean.cache.DeviceProduct;
import com.vrv.cems.service.base.bean.cache.PolicyCache;
import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.base.bean.cache.SensitiveCache;
import com.vrv.cems.service.cache.service.SensitiveService;
import com.vrv.cems.service.base.bean.cache.UserCache;
import com.vrv.cems.service.base.bean.cache.UserOnlineCache;
import com.vrv.cems.service.base.bean.cache.UserPolicyCache;
import com.vrv.cems.service.base.interfaces.CacheService.Iface;
import com.vrv.cems.service.base.interfaces.ICacheService;
import com.vrv.cems.service.cache.service.CommonService;
import com.vrv.cems.service.cache.service.DeviceInsProNewService;
import com.vrv.cems.service.cache.service.DeviceInsProOldService;
import com.vrv.cems.service.cache.service.DeviceKeyService;
import com.vrv.cems.service.cache.service.DeviceOnlineService;
import com.vrv.cems.service.cache.service.DevicePolicyService;
import com.vrv.cems.service.cache.service.DeviceService;
import com.vrv.cems.service.cache.service.IP2DevOnlyIdService;
import com.vrv.cems.service.cache.service.IPMAC2DevOnlyIdService;
import com.vrv.cems.service.cache.service.PolicyService;
import com.vrv.cems.service.cache.service.ProductCInstallPackService;
import com.vrv.cems.service.cache.service.ProductCUpgradePackService;
import com.vrv.cems.service.cache.service.PtpService;

import com.vrv.cems.service.cache.service.UserOnlineService;
import com.vrv.cems.service.cache.service.UserPolicyService;
import com.vrv.cems.service.cache.service.UserService;
import com.vrv.cems.service.cache.service.impl.CommonServiceImpl;
import com.vrv.cems.service.cache.service.impl.DeviceInsProNewServiceImpl;
import com.vrv.cems.service.cache.service.impl.DeviceInsProOldServiceImpl;
import com.vrv.cems.service.cache.service.impl.DeviceKeyServiceImpl;
import com.vrv.cems.service.cache.service.impl.DeviceOnlineServiceImpl;
import com.vrv.cems.service.cache.service.impl.DevicePolicyServiceImpl;
import com.vrv.cems.service.cache.service.impl.DeviceServiceImpl;
import com.vrv.cems.service.cache.service.impl.IP2DevOnlyIdServiceImpl;
import com.vrv.cems.service.cache.service.impl.IPMAC2DevOnlyIdServiceImpl;
import com.vrv.cems.service.cache.service.impl.PolicyServiceImpl;
import com.vrv.cems.service.cache.service.impl.ProductCInstallPackServiceImpl;
import com.vrv.cems.service.cache.service.impl.ProductCUpgradePackServiceImpl;
import com.vrv.cems.service.cache.service.impl.PtpServiceImpl;
import com.vrv.cems.service.cache.service.impl.SensitiveServiceImpl;
import com.vrv.cems.service.cache.service.impl.UserOnlineServiceImpl;
import com.vrv.cems.service.cache.service.impl.UserPolicyServiceImpl;
import com.vrv.cems.service.cache.service.impl.UserServiceImpl;

/**
 * <B>说 明</B>:缓存服务主入口
 * 
 * @author 作 者 名：glw<br/>
 *         E-mail ：linwu_gao@163.com
 * 
 * @version 版 本 号：V1.0.<br/>
 *          创建时间：2015年4月21日 下午6:33:49
 */
public class ICacheServiceAction implements Iface {
	private Logger log = Logger.getLogger(this.getClass());
	private Result result = null;
	private DeviceService deviceService = new DeviceServiceImpl();
	private DeviceOnlineService deviceOnlineService = new DeviceOnlineServiceImpl();
	private UserService userService = new UserServiceImpl();
	private DeviceKeyService deviceKeyService = new DeviceKeyServiceImpl();
	private DeviceInsProOldService deviceInsProOldService = new DeviceInsProOldServiceImpl();
	private DeviceInsProNewService deviceInsProNewService = new DeviceInsProNewServiceImpl();
	private DevicePolicyService devicePolicyService = new DevicePolicyServiceImpl();
	private UserOnlineService userOnlineService = new UserOnlineServiceImpl();
	private UserPolicyService userPolicyService = new UserPolicyServiceImpl();
	private ProductCInstallPackService productCInstallPackService = new ProductCInstallPackServiceImpl();
	private PolicyService policyService = new PolicyServiceImpl();
	private IPMAC2DevOnlyIdService ipMAC2DevOnlyIdService = new IPMAC2DevOnlyIdServiceImpl();
	private IP2DevOnlyIdService ip2DevOnlyIdService = new IP2DevOnlyIdServiceImpl();
	private ProductCUpgradePackService productCUpgradePackService = new ProductCUpgradePackServiceImpl();
	private CommonService commonService = new CommonServiceImpl();
	private PtpService ptpService = new PtpServiceImpl();
	private SensitiveService sensitiveService = new SensitiveServiceImpl();
	/**
	 * 检验输入参数maxCode和minCode的非空性
	 * 
	 * @param maxCode
	 * @param minCode
	 * @return
	 */
	private Result checkCode(String maxCode, String minCode) {
		result = new Result();
		if (StringUtils.isBlank(maxCode)) {
			result.setCode(ICacheService.ERROR_21);
			result.setInfo("传入参数maxCode为空!");
		} else if (StringUtils.isBlank(minCode)) {
			result.setCode(ICacheService.ERROR_22);
			result.setInfo("传入参数minCode为空!");
		} else if (!maxCode.equals(ICacheService.SERVICE_CODE)) {
			result.setCode(ICacheService.ERROR_23);
			result.setInfo("你要调用的不是缓存服务！");
		} else if (Integer.parseInt(minCode)<0&&Integer.parseInt(minCode) > 2000) {
			result.setCode(ICacheService.ERROR_24);
			result.setInfo("缓存服务暂时还未提供该类服务接口！");
		} else {

		}
		return result;
	}

	@Override
	public Result isExist(String maxCode, String minCode, String prefix,
			String key) throws TException {
		log.info("进入调用缓存服务-验证Key在缓存中是否已存在...");
		log.info("输入数据：prefix: " + prefix);
		log.info("输入数据：key: " + key);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return commonService.isExist(maxCode, minCode, prefix, key);
	}

	@Override
	public Result saveDevice(String maxCode, String minCode,
			DeviceCache deviceCache) throws TException {

		log.info("进入调用缓存服务-保存设备信息接口...");
		log.info("输入数据：" + deviceCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return deviceService.saveDevice(maxCode, minCode, deviceCache);
	}

	@Override
	public Result updateDevice(String maxCode, String minCode,
			DeviceCache deviceCache) throws TException {
		log.info("进入调用缓存服务-更新设备信息接口...");
		log.info("输入数据：" + deviceCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return deviceService.updateDevice(maxCode, minCode, deviceCache);
	}

	@Override
	public Result updateDeviceByField(String maxCode, String minCode,
			String devOnlyId, Map<String, String> fieldValueMap)
			throws TException {
		log.info("进入调用缓存服务-根据fieldValueMap更新设备信息接口...");
		log.info("输入数据：" + fieldValueMap.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return deviceService.updateDeviceByField(maxCode, minCode, devOnlyId,
				fieldValueMap);
	}

	@Override
	public DeviceCache queryDevice(String maxCode, String minCode,
			DeviceCache deviceCache) throws TException {
		log.info("进入调用缓存服务-查询设备信息接口...");
		log.info("输入数据：" + deviceCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}

		return deviceService.queryDevice(maxCode, minCode, deviceCache);
	}

	@Override
	public DeviceCache queryDeviceByDevOnlyId(String maxCode, String minCode,
			String devOnlyId) throws TException {
		log.info("进入调用缓存服务-根据devOnlyId查询设备信息接口...");
		log.info("输入数据：" + devOnlyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return deviceService
				.queryDeviceByDevOnlyId(maxCode, minCode, devOnlyId);
	}

	@Override
	public DeviceCache queryDeviceByIp(String maxCode, String minCode, String ip)
			throws TException {
		log.info("进入调用缓存服务-根据Ip查询设备信息接口...");
		log.info("输入数据：" + ip);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return deviceService.queryDeviceByIp(maxCode, minCode, ip);
	}

	@Override
	public Result deleteDevice(String maxCode, String minCode,
			DeviceCache deviceCache) throws TException {
		log.info("进入调用缓存服务-删除设备信息接口...");
		log.info("输入数据：" + deviceCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return deviceService.deleteDevice(maxCode, minCode, deviceCache);
	}

	@Override
	public Result deleteDeviceByDevOnlyId(String maxCode, String minCode,
			String devOnlyId) throws TException {

		log.info("进入调用缓存服务-根据devOnlyId删除设备信息接口...");
		log.info("输入数据：" + devOnlyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}
		return deviceService.deleteDeviceByDevOnlyId(maxCode, minCode,
				devOnlyId);
	}

	@Override
	public Result deleteDeviceByIp(String maxCode, String minCode, String ip)
			throws TException {
		log.info("进入调用缓存服务-根据ip删除设备信息接口...");
		log.info("输入数据：" + ip);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}
		return deviceService.deleteDeviceByIp(maxCode, minCode, ip);
	}

	@Override
	public List<Result> batchDeleteDeviceByDevOnlyId(String maxCode,
			String minCode, List<String> devOnlyIdList) throws TException {
		log.info("进入调用缓存服务-根据devOnlyId批量删除设备信息接口...");
		log.info("输入数据：" + devOnlyIdList.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return deviceService.batchDeleteDeviceByDevOnlyId(maxCode, minCode,
				devOnlyIdList);
	}

	@Override
	public List<DeviceCache> batchQueryDevice(String maxCode, String minCode,
			List<DeviceCache> deviceCacheList) throws TException {
		log.info("进入调用缓存服务-根据deviceCacheList批量查询设备信息接口...");
		log.info("输入数据：" + deviceCacheList.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return deviceService
				.batchQueryDevice(maxCode, minCode, deviceCacheList);
	}

	@Override
	public List<DeviceCache> batchQueryDeviceByDevOnlyId(String maxCode,
			String minCode, List<String> devOnlyIdList) throws TException {
		log.info("进入调用缓存服务-根据devOnlyId批量查询设备信息接口...");
		log.info("输入数据：" + devOnlyIdList.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return deviceService.batchQueryDeviceByDevOnlyId(maxCode, minCode,
				devOnlyIdList);
	}

	@Override
	public List<Result> batchSaveDevice(String maxCode, String minCode,
			List<DeviceCache> deviceCacheList) throws TException {
		log.info("进入调用缓存服务-根据deviceCacheList批量保存设备信息接口...");
		log.info("输入数据：" + deviceCacheList.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return deviceService.batchSaveDevice(maxCode, minCode, deviceCacheList);
	}

	@Override
	public List<Result> batchUpdateDevice(String maxCode, String minCode,
			List<DeviceCache> deviceCacheList) throws TException {
		log.info("进入调用缓存服务-根据deviceCacheList批量更新设备信息接口...");
		log.info("输入数据：" + deviceCacheList.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return deviceService.batchUpdateDevice(maxCode, minCode,
				deviceCacheList);
	}

	@Override
	public List<Result> batchDeleteDevice(String maxCode, String minCode,
			List<DeviceCache> deviceCacheList) throws TException {
		log.info("进入调用缓存服务-根据deviceCacheList批量更新设备信息接口...");
		log.info("输入数据：" + deviceCacheList.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return deviceService.batchDeleteDevice(maxCode, minCode,
				deviceCacheList);
	}

	@Override
	public Result saveDeviceOnline(String maxCode, String minCode,
			DeviceOnlineCache deviceOnlineCache) throws TException {
		log.info("进入调用缓存服务-保存设备在线信息接口...");
		log.info("输入数据：" + deviceOnlineCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return deviceOnlineService.saveDeviceOnline(maxCode, minCode,
				deviceOnlineCache);
	}

	@Override
	public Result updateDeviceOnline(String maxCode, String minCode,
			DeviceOnlineCache deviceOnlineCache) throws TException {
		log.info("进入调用缓存服务-更新设备在线信息接口...");
		log.info("输入数据：" + deviceOnlineCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return deviceOnlineService.updateDeviceOnline(maxCode, minCode,
				deviceOnlineCache);
	}

	@Override
	public Result updateDeviceOnlineByField(String maxCode, String minCode,
			String devOnlyId, Map<String, String> fieldValueMap)
			throws TException {
		log.info("进入调用缓存服务-根据fieldValueMap更新设备在线信息接口...");
		log.info("输入数据：" + fieldValueMap.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return deviceOnlineService.updateDeviceOnlineByField(maxCode, minCode,
				devOnlyId, fieldValueMap);
	}

	@Override
	public DeviceOnlineCache queryDeviceOnline(String maxCode, String minCode,
			DeviceOnlineCache deviceOnlineCache) throws TException {
		log.info("进入调用缓存服务-查询设备在线信息接口...");
		log.info("输入数据：" + deviceOnlineCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}

		return deviceOnlineService.queryDeviceOnline(maxCode, minCode,
				deviceOnlineCache);
	}

	@Override
	public DeviceOnlineCache queryDeviceOnlineByDevOnlyId(String maxCode,
			String minCode, String devOnlyId) throws TException {
		log.info("进入调用缓存服务-根据devOnlyId查询设备在线信息接口...");
		log.info("输入数据：" + devOnlyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return deviceOnlineService.queryDeviceOnlineByDevOnlyId(maxCode,
				minCode, devOnlyId);
	}

	@Override
	public DeviceOnlineCache queryDeviceOnlineByIp(String maxCode,
			String minCode, String ip) throws TException {
		log.info("进入调用缓存服务-根据Ip查询设备在线信息接口...");
		log.info("输入数据：" + ip);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return deviceOnlineService.queryDeviceOnlineByIp(maxCode, minCode, ip);
	}

	@Override
	public Result deleteDeviceOnline(String maxCode, String minCode,
			DeviceOnlineCache deviceOnlineCache) throws TException {
		log.info("进入调用缓存服务-删除设备信息接口...");
		log.info("输入数据：" + deviceOnlineCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return deviceOnlineService.deleteDeviceOnline(maxCode, minCode,
				deviceOnlineCache);
	}

	@Override
	public Result deleteDeviceOnlineByDevOnlyId(String maxCode, String minCode,
			String devOnlyId) throws TException {
		log.info("进入调用缓存服务-根据devOnlyId删除设备在线信息接口...");
		log.info("输入数据：" + devOnlyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}
		return deviceOnlineService.deleteDeviceOnlineByDevOnlyId(maxCode,
				minCode, devOnlyId);

	}

	@Override
	public Result deleteDeviceOnlineByIp(String maxCode, String minCode,
			String ip) throws TException {
		log.info("进入调用缓存服务-根据ip删除设备在线信息接口...");
		log.info("输入数据：" + ip);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}
		return deviceOnlineService.deleteDeviceOnlineByIp(maxCode, minCode, ip);
	}

	@Override
	public Result saveDeviceKey(String maxCode, String minCode,
			DeviceKeyCache deviceKeyCache) throws TException {
		log.info("进入调用缓存服务-保存设备会话密钥信息接口...");
		log.info("输入数据：" + deviceKeyCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return deviceKeyService.saveDeviceKey(maxCode, minCode, deviceKeyCache);
	}

	@Override
	public Result updateDeviceKey(String maxCode, String minCode,
			DeviceKeyCache deviceKeyCache) throws TException {
		log.info("进入调用缓存服务-更新设备会话密钥信息接口...");
		log.info("输入数据：" + deviceKeyCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return deviceKeyService.updateDeviceKey(maxCode, minCode,
				deviceKeyCache);
	}

	@Override
	public Result updateDeviceKeyByField(String maxCode, String minCode,
			String sessionId, Map<String, String> fieldValueMap)
			throws TException {
		log.info("进入调用缓存服务-根据fieldValueMap更新设备会话密钥信息接口...");
		log.info("输入数据：" + fieldValueMap.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return deviceKeyService.updateDeviceKeyByField(maxCode, minCode,
				sessionId, fieldValueMap);
	}

	@Override
	public DeviceKeyCache queryDeviceKey(String maxCode, String minCode,
			DeviceKeyCache deviceKeyCache) throws TException {
		log.info("进入调用缓存服务-查询设备会话密钥信息接口...");
		log.info("输入数据：" + deviceKeyCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}

		return deviceKeyService
				.queryDeviceKey(maxCode, minCode, deviceKeyCache);
	}

	@Override
	public DeviceKeyCache queryDeviceKeyBySessionId(String maxCode,
			String minCode, String sessionId) throws TException {
		log.info("进入调用缓存服务-根据sessionId查询设备会话密钥信息接口...");
		log.info("输入数据：" + sessionId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}

		return deviceKeyService.queryDeviceKeyBySessionId(maxCode, minCode,
				sessionId);
	}

	@Override
	public DeviceKeyCache queryDeviceKeyByDevOnlyId(String maxCode,
			String minCode, String devOnlyId) throws TException {
		log.info("进入调用缓存服务-根据devOnlyId查询设备会话密钥信息接口...");
		log.info("输入数据：" + devOnlyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}

		return deviceKeyService.queryDeviceKeyByDevOnlyId(maxCode, minCode,
				devOnlyId);
	}

	@Override
	public Result deleteDeviceKey(String maxCode, String minCode,
			DeviceKeyCache deviceKeyCache) throws TException {
		log.info("进入调用缓存服务-删除设备会话密钥信息接口...");
		log.info("输入数据：" + deviceKeyCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return deviceKeyService.deleteDeviceKey(maxCode, minCode,
				deviceKeyCache);
	}

	@Override
	public Result deleteDeviceKeyBySessionId(String maxCode, String minCode,
			String sessionId) throws TException {
		log.info("进入调用缓存服务-根据sessionId删除设备会话密钥信息接口...");
		log.info("输入数据：" + sessionId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return deviceKeyService.deleteDeviceKeyBySessionId(maxCode, minCode,
				sessionId);
	}

	@Override
	public Result deleteDeviceKeyByDevOnlyId(String maxCode, String minCode,
			String devOnlyId) throws TException {
		log.info("进入调用缓存服务-根据devOnlyId删除设备会话密钥信息接口...");
		log.info("输入数据：" + devOnlyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return deviceKeyService.deleteDeviceKeyByDevOnlyId(maxCode, minCode,
				devOnlyId);
	}

	@Override
	public Result isExistSessionIdInDeviceKeyCache(String maxCode,
			String minCode, String sessionId) throws TException {
		log.info("进入调用缓存服务-验证sessionId 在DeviceKeyCache中是否存在...");
		log.info("输入数据：" + sessionId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return deviceKeyService.isExistSessionIdInDeviceKeyCache(maxCode,
				minCode, sessionId);
	}

	@Override
	public Result saveDeviceInsProOld(String maxCode, String minCode,
			String devOnlyId, List<DeviceProduct> deviceProductList)
			throws TException {
		log.info("进入调用缓存服务-保存设备已安装产品信息接口...");
		log.info("输入数据：" + devOnlyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return deviceInsProOldService.saveDeviceInsProOld(maxCode, minCode,
				devOnlyId, deviceProductList);
	}

	@Override
	public Result updateDeviceInsProOld(String maxCode, String minCode,
			String devOnlyId, List<DeviceProduct> deviceProductList)
			throws TException {
		log.info("进入调用缓存服务-更新设备已安装产品信息接口...");
		log.info("输入数据：" + devOnlyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return deviceInsProOldService.updateDeviceInsProOld(maxCode, minCode,
				devOnlyId, deviceProductList);
	}

	@Override
	public List<DeviceProduct> queryDeviceInsProOldByDevOnlyId(String maxCode,
			String minCode, String devOnlyId) throws TException {
		log.info("进入调用缓存服务-查询设备已安装产品信息接口...");
		log.info("输入数据：" + devOnlyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}

		return deviceInsProOldService.queryDeviceInsProOldByDevOnlyId(maxCode,
				minCode, devOnlyId);
	}

	@Override
	public Result deleteDeviceInsProOldByDevOnlyId(String maxCode,
			String minCode, String devOnlyId) throws TException {
		log.info("进入调用缓存服务-根据devOnlyId删除设备已安装产品信息接口...");
		log.info("输入数据：" + devOnlyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return deviceInsProOldService.deleteDeviceInsProOldByDevOnlyId(maxCode,
				minCode, devOnlyId);
	}

	@Override
	public Result saveDeviceInsProNew(String maxCode, String minCode,
			String devOnlyId, List<DeviceProduct> deviceProductList)
			throws TException {
		log.info("进入调用缓存服务-保存设备应安装产品信息接口...");
		log.info("输入数据：" + devOnlyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return deviceInsProNewService.saveDeviceInsProNew(maxCode, minCode,
				devOnlyId, deviceProductList);
	}

	@Override
	public Result updateDeviceInsProNew(String maxCode, String minCode,
			String devOnlyId, List<DeviceProduct> deviceProductList)
			throws TException {
		log.info("进入调用缓存服务-更新设备应安装产品信息接口...");
		log.info("输入数据：" + devOnlyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return deviceInsProNewService.updateDeviceInsProNew(maxCode, minCode,
				devOnlyId, deviceProductList);
	}

	@Override
	public List<DeviceProduct> queryDeviceInsProNewByDevOnlyId(String maxCode,
			String minCode, String devOnlyId) throws TException {

		log.info("进入调用缓存服务-根据devOnlyId查询设备应安装产品信息接口...");
		log.info("输入数据：" + devOnlyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}

		return deviceInsProNewService.queryDeviceInsProNewByDevOnlyId(maxCode,
				minCode, devOnlyId);
	}

	@Override
	public Result deleteDeviceInsProNewByDevOnlyId(String maxCode,
			String minCode, String devOnlyId) throws TException {
		log.info("进入调用缓存服务-根据devOnlyId删除设备应安装产品信息接口...");
		log.info("输入数据：" + devOnlyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return deviceInsProNewService.deleteDeviceInsProNewByDevOnlyId(maxCode,
				minCode, devOnlyId);
	}

	@Override
	public Result saveDevicePolicy(String maxCode, String minCode,
			DevicePolicyCache devicePolicyCache) throws TException {
		log.info("进入调用缓存服务-保存设备执行策略信息接口...");
		log.info("输入数据：" + devicePolicyCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return devicePolicyService.saveDevicePolicy(maxCode, minCode,
				devicePolicyCache);
	}

	@Override
	public Result updateDevicePolicy(String maxCode, String minCode,
			DevicePolicyCache devicePolicyCache) throws TException {
		log.info("进入调用缓存服务-更新设备执行策略信息接口...");
		log.info("输入数据：" + devicePolicyCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return devicePolicyService.updateDevicePolicy(maxCode, minCode,
				devicePolicyCache);
	}

	@Override
	public DevicePolicyCache queryDevicePolicyByDevOnlyId(String maxCode,
			String minCode, String devOnlyId) throws TException {
		log.info("进入调用缓存服务-根据devOnlyId查询设备执行策略信息接口...");
		log.info("输入数据：" + devOnlyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}

		return devicePolicyService.queryDevicePolicyByDevOnlyId(maxCode,
				minCode, devOnlyId);
	}

	@Override
	public DevicePolicyCache queryDevicePolicyByIp(String maxCode,
			String minCode, String ip) throws TException {
		log.info("进入调用缓存服务-根据ip查询设备执行策略信息接口...");
		log.info("输入数据：" + ip);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}

		return devicePolicyService.queryDevicePolicyByIp(maxCode, minCode, ip);
	}

	@Override
	public Result deleteDevicePolicyByDevOnlyId(String maxCode, String minCode,
			String devOnlyId) throws TException {
		log.info("进入调用缓存服务-根据devOnlyId删除设备执行策略信息接口...");
		log.info("输入数据：" + devOnlyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return devicePolicyService.deleteDevicePolicyByDevOnlyId(maxCode,
				minCode, devOnlyId);
	}

	@Override
	public Result deleteDevicePolicyByIp(String maxCode, String minCode,
			String ip) throws TException {
		log.info("进入调用缓存服务-根据ip删除设备执行策略信息接口...");
		log.info("输入数据：" + ip);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return devicePolicyService.deleteDevicePolicyByIp(maxCode, minCode, ip);
	}

	@Override
	public Result saveUser(String maxCode, String minCode, UserCache userCache)
			throws TException {
		log.info("进入调用缓存服务-保存用户信息接口...");
		log.info("输入数据：" + userCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return userService.saveUser(maxCode, minCode, userCache);
	}

	@Override
	public Result updateUser(String maxCode, String minCode, UserCache userCache)
			throws TException {
		log.info("进入调用缓存服务-更新用户信息接口...");
		log.info("输入数据：" + userCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return userService.updateUser(maxCode, minCode, userCache);
	}

	@Override
	public Result updateUserByField(String maxCode, String minCode,
			String userOnlyId, Map<String, String> fieldValueMap)
			throws TException {
		log.info("进入调用缓存服务-根据fieldValueMap更新用户信息接口...");
		log.info("输入数据：" + fieldValueMap.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return userService.updateUserByField(maxCode, minCode, userOnlyId,
				fieldValueMap);
	}

	@Override
	public UserCache queryUserByUserOnlyId(String maxCode, String minCode,
			String userOnlyId) throws TException {
		log.info("进入调用缓存服务-根据userOnlyId查询用户信息接口...");
		log.info("输入数据：" + userOnlyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}

		return userService.queryUserByUserOnlyId(maxCode, minCode, userOnlyId);
	}

	@Override
	public Result deleteUserByUserOnlyId(String maxCode, String minCode,
			String userOnlyId) throws TException {
		log.info("进入调用缓存服务-根据userOnlyId删除用户信息接口...");
		log.info("输入数据：" + userOnlyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return userService.deleteUserByUserOnlyId(maxCode, minCode, userOnlyId);
	}

	@Override
	public Result saveUserOnline(String maxCode, String minCode,
			UserOnlineCache userOnlineCache) throws TException {
		log.info("进入调用缓存服务-保存用户在线信息接口...");
		log.info("输入数据：" + userOnlineCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return userOnlineService.saveUserOnline(maxCode, minCode,
				userOnlineCache);
	}

	@Override
	public Result updateUserOnline(String maxCode, String minCode,
			UserOnlineCache userOnlineCache) throws TException {
		log.info("进入调用缓存服务-更新用户在线信息接口...");
		log.info("输入数据：" + userOnlineCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return userOnlineService.updateUserOnline(maxCode, minCode,
				userOnlineCache);
	}

	@Override
	public Result updateUserOnlineByField(String maxCode, String minCode,
			String userOnlyId, Map<String, String> fieldValueMap)
			throws TException {
		log.info("进入调用缓存服务-根据fieldValueMap更新用户在线信息接口...");
		log.info("输入数据：" + fieldValueMap.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return userOnlineService.updateUserOnlineByField(maxCode, minCode,
				userOnlyId, fieldValueMap);
	}

	@Override
	public UserOnlineCache queryUserOnlineByUserOnlyId(String maxCode,
			String minCode, String userOnlyId) throws TException {
		log.info("进入调用缓存服务-根据userOnlyId查询用户在线信息接口...");
		log.info("输入数据：" + userOnlyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}

		return userOnlineService.queryUserOnlineByUserOnlyId(maxCode, minCode,
				userOnlyId);
	}

	@Override
	public Result deleteUserOnlineByUserOnlyId(String maxCode, String minCode,
			String userOnlyId) throws TException {
		log.info("进入调用缓存服务-根据userOnlyId删除用户在线信息接口...");
		log.info("输入数据：" + userOnlyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return userOnlineService.deleteUserOnlineByUserOnlyId(maxCode, minCode,
				userOnlyId);
	}

	// userPolicyService
	@Override
	public Result saveUserPolicy(String maxCode, String minCode,
			UserPolicyCache userPolicyCache) throws TException {
		log.info("进入调用缓存服务-保存用户执行策略信息接口...");
		log.info("输入数据：" + userPolicyCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return userPolicyService.saveUserPolicy(maxCode, minCode,
				userPolicyCache);
	}

	@Override
	public Result updateUserPolicy(String maxCode, String minCode,
			UserPolicyCache userPolicyCache) throws TException {
		log.info("进入调用缓存服务-更新用户执行策略信息接口...");
		log.info("输入数据：" + userPolicyCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return userPolicyService.updateUserPolicy(maxCode, minCode,
				userPolicyCache);
	}

	@Override
	public UserPolicyCache queryUserPolicyByUserOnlyId(String maxCode,
			String minCode, String userOnlyId) throws TException {
		log.info("进入调用缓存服务-根据userOnlyId查询用户执行策略信息接口...");
		log.info("输入数据：" + userOnlyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}

		return userPolicyService.queryUserPolicyByUserOnlyId(maxCode, minCode,
				userOnlyId);
	}

	@Override
	public Result deleteUserPolicyByUserOnlyId(String maxCode, String minCode,
			String userOnlyId) throws TException {
		log.info("进入调用缓存服务-根据userOnlyId删除用户执行策略信息接口...");
		log.info("输入数据：" + userOnlyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return userPolicyService.deleteUserPolicyByUserOnlyId(maxCode, minCode,
				userOnlyId);
	}

	// productCInstallPackService
	@Override
	public Result saveProductCInstallPack(String maxCode, String minCode,
			CInstallPackCache cInstallPackCache) throws TException {
		log.info("进入调用缓存服务-保存产品安装包信息接口...");
		log.info("输入数据：" + cInstallPackCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return productCInstallPackService.saveProductCInstallPack(maxCode,
				minCode, cInstallPackCache);
	}

	@Override
	public Result updateProductCInstallPack(String maxCode, String minCode,
			CInstallPackCache cInstallPackCache) throws TException {
		log.info("进入调用缓存服务-更新产品安装包信息接口...");
		log.info("输入数据：" + cInstallPackCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}

		return productCInstallPackService.updateProductCInstallPack(maxCode,
				minCode, cInstallPackCache);
	}

	@Override
	public Result updateProductCInstallPackByField(String maxCode,
			String minCode, String cInstallPackId,
			Map<String, String> fieldValueMap) throws TException {
		log.info("进入调用缓存服务-根据fieldValueMap更新产品安装包信息接口...");
		log.info("输入数据：" + fieldValueMap.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}
		return productCInstallPackService.updateProductCInstallPackByField(
				maxCode, minCode, cInstallPackId, fieldValueMap);
	}

	@Override
	public CInstallPackCache queryProductCInstallPackById(String maxCode,
			String minCode, String cInstallPackId) throws TException {
		log.info("进入调用缓存服务-根据cInstallPackId查询产品安装包信息接口...");
		log.info("输入数据：" + cInstallPackId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return productCInstallPackService.queryProductCInstallPackById(maxCode,
				minCode, cInstallPackId);
	}

	@Override
	public Result deleteProductCInstallPackById(String maxCode, String minCode,
			String cInstallPackId) throws TException {
		log.info("进入调用缓存服务-根据cInstallPackId删除产品安装包信息接口...");
		log.info("输入数据：" + cInstallPackId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}
		return productCInstallPackService.deleteProductCInstallPackById(
				maxCode, minCode, cInstallPackId);
	}

	// policyService
	@Override
	public Result savePolicy(String maxCode, String minCode,
			PolicyCache policyCache) throws TException {
		log.info("进入调用缓存服务-保存策略信息接口...");
		log.info("输入数据：" + policyCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}
		return policyService.savePolicy(maxCode, minCode, policyCache);
	}

	@Override
	public Result updatePolicy(String maxCode, String minCode,
			PolicyCache policyCache) throws TException {
		log.info("进入调用缓存服务-更新策略信息接口...");
		log.info("输入数据：" + policyCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}
		return policyService.updatePolicy(maxCode, minCode, policyCache);
	}

	@Override
	public Result updatePolicyByField(String maxCode, String minCode,
			String policyId, Map<String, String> fieldValueMap)
			throws TException {
		log.info("进入调用缓存服务-根据fieldValueMap更新策略信息接口...");
		log.info("输入数据：" + fieldValueMap.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}
		return policyService.updatePolicyByField(maxCode, minCode, policyId,
				fieldValueMap);
	}

	@Override
	public PolicyCache queryPolicyByPolicyId(String maxCode, String minCode,
			String policyId) throws TException {
		log.info("进入调用缓存服务-根据policyId查询策略信息接口...");
		log.info("输入数据：" + policyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return policyService.queryPolicyByPolicyId(maxCode, minCode, policyId);
	}

	@Override
	public Result deletePolicyByPolicyId(String maxCode, String minCode,
			String policyId) throws TException {
		log.info("进入调用缓存服务-根据policyId删除策略信息接口...");
		log.info("输入数据：" + policyId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}
		return policyService.deletePolicyByPolicyId(maxCode, minCode, policyId);
	}

	@Override
	public Result saveIPMAC2DevOnlyId(String maxCode, String minCode,
			String ip, String mac, String devOnlyId) throws TException {
		log.info("进入调用缓存服务-保存ipmac和devOnlyId的关系接口...");
		log.info("输入数据：" + ip + mac);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}
		return ipMAC2DevOnlyIdService.saveIPMAC2DevOnlyId(maxCode, minCode, ip,
				mac, devOnlyId);
	}

	@Override
	public Result updateIPMAC2DevOnlyId(String maxCode, String minCode,
			String ip, String mac, String devOnlyId) throws TException {
		log.info("进入调用缓存服务-更新ipmac和devOnlyId的关系接口...");
		log.info("输入数据：" + ip + mac);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}
		return ipMAC2DevOnlyIdService.updateIPMAC2DevOnlyId(maxCode, minCode,
				ip, mac, devOnlyId);
	}

	@Override
	public String queryIPMAC2DevOnlyId(String maxCode, String minCode,
			String ip, String mac) throws TException {
		log.info("进入调用缓存服务-查询ipmac和devOnlyId的关系接口...");
		log.info("输入数据：" + ip + mac);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return ipMAC2DevOnlyIdService.queryIPMAC2DevOnlyId(maxCode, minCode,
				ip, mac);
	}

	@Override
	public Result deleteIPMAC2DevOnlyId(String maxCode, String minCode,
			String ip, String mac) throws TException {
		log.info("进入调用缓存服务-删除ipmac和devOnlyId的关系接口...");
		log.info("输入数据：" + ip + mac);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}
		return ipMAC2DevOnlyIdService.deleteIPMAC2DevOnlyId(maxCode, minCode,
				ip, mac);
	}

	@Override
	public Result saveIP2DevOnlyId(String maxCode, String minCode, String ip,
			String devOnlyId) throws TException {
		log.info("进入调用缓存服务-保存ip和devOnlyId的关系接口...");
		log.info("输入数据：" + ip);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}
		return ip2DevOnlyIdService.saveIP2DevOnlyId(maxCode, minCode, ip,
				devOnlyId);
	}

	@Override
	public Result updateIP2DevOnlyId(String maxCode, String minCode, String ip,
			String devOnlyId) throws TException {
		log.info("进入调用缓存服务-更新ip和devOnlyId的关系接口...");
		log.info("输入数据：" + ip);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}
		return ip2DevOnlyIdService.updateIP2DevOnlyId(maxCode, minCode, ip,
				devOnlyId);
	}

	@Override
	public String queryIP2DevOnlyId(String maxCode, String minCode, String ip)
			throws TException {
		log.info("进入调用缓存服务-查询ip和devOnlyId的关系接口...");
		log.info("输入数据：" + ip);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return ip2DevOnlyIdService.queryIP2DevOnlyId(maxCode, minCode, ip);
	}

	@Override
	public Result deleteIP2DevOnlyId(String maxCode, String minCode, String ip)
			throws TException {
		log.info("进入调用缓存服务-删除ip和devOnlyId的关系接口...");
		log.info("输入数据：" + ip);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}
		return ip2DevOnlyIdService.deleteIP2DevOnlyId(maxCode, minCode, ip);
	}

	// productCUpgradePackService
	@Override
	public Result saveProductCUpgradePack(String maxCode, String minCode,
			CUpgradePackCache cUpgradePackCache) throws TException {
		log.info("进入调用缓存服务-保存产品升级包信息接口...");
		log.info("输入数据：" + cUpgradePackCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}
		return productCUpgradePackService.saveProductCUpgradePack(maxCode,
				minCode, cUpgradePackCache);
	}

	@Override
	public Result updateProductCUpgradePack(String maxCode, String minCode,
			CUpgradePackCache cUpgradePackCache) throws TException {
		log.info("进入调用缓存服务-更新产品升级包信息接口...");
		log.info("输入数据：" + cUpgradePackCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}
		return productCUpgradePackService.updateProductCUpgradePack(maxCode,
				minCode, cUpgradePackCache);
	}

	@Override
	public Result updateProductCUpgradePackByField(String maxCode,
			String minCode, String cUpgradePackId,
			Map<String, String> fieldValueMap) throws TException {
		log.info("进入调用缓存服务-根据fieldValueMap更新产品升级包信息接口...");
		log.info("输入数据：" + fieldValueMap.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}
		return productCUpgradePackService.updateProductCUpgradePackByField(
				maxCode, minCode, cUpgradePackId, fieldValueMap);
	}

	@Override
	public CUpgradePackCache queryProductCUpgradePackById(String maxCode,
			String minCode, String cUpgradePackId) throws TException {
		log.info("进入调用缓存服务-根据cUpgradePackId查询产品升级包信息接口...");
		log.info("输入数据：" + cUpgradePackId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return productCUpgradePackService.queryProductCUpgradePackById(maxCode,
				minCode, cUpgradePackId);
	}

	@Override
	public Result deleteProductCUpgradePackById(String maxCode, String minCode,
			String cUpgradePackId) throws TException {
		log.info("进入调用缓存服务-根据cUpgradePackId删除产品升级包信息接口...");
		log.info("输入数据：" + cUpgradePackId);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}
		return productCUpgradePackService.deleteProductCUpgradePackById(
				maxCode, minCode, cUpgradePackId);
	}

	@Override
	public ByteBuffer getDataTS(String maxCode, String minCode,
			String checkCode, boolean isZip, ByteBuffer data,
			boolean isEncrypt, String key, int flag) throws TException {
		log.info("进入调用缓存服务-更新服务策略接口...");
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		


		return commonService.getDataTS(maxCode, minCode, checkCode, isZip,
				data, isEncrypt, key, flag);

	}

	@Override
	public Result deleteAccount2UserOnlyId(String maxCode, String minCode, String account)
			throws TException {
		log.info("进入调用缓存服务-删除account与userOnlyId对应关系接口...");
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return commonService.deleteAccount2UserOnlyId(maxCode, minCode,account);
	}

	@Override
	public String queryAccount2UserOnlyId(String maxCode, String minCode, String account)
			throws TException {
		log.info("进入调用缓存服务-查询account与userOnlyId对应关系接口...");
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return commonService.queryAccount2UserOnlyId(maxCode, minCode,account);
	}

	@Override
	public Result saveAccount2UserOnlyId(String maxCode, String minCode, String account,
			String userOnlyId) throws TException {
		log.info("进入调用缓存服务-增加account与userOnlyId对应关系接口...");
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return commonService.saveAccount2UserOnlyId(maxCode,minCode,account,userOnlyId);
	}

	@Override
	public Result updateAccount2UserOnlyId(String maxCode, String minCode,
			String account, String userOnlyId) throws TException {
		log.info("进入调用缓存服务-更新account与userOnlyId对应关系接口...");
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return commonService.updateAccount2UserOnlyId(maxCode, minCode,account,userOnlyId);
	}

	@Override
	public Result deleteData(String arg0, String arg1, String arg2, String arg3)
			throws TException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Result deletePtp(String maxCode, String minCode, String uuid)
			throws TException {
		log.info("进入调用缓存服务-删除点对点缓存数据接口...");
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return ptpService.deletePtp(maxCode,minCode,uuid);
	}

	@Override
	public String queryData(String arg0, String arg1, String arg2, String arg3)
			throws TException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String queryPtp(String maxCode, String minCode, String uuid)
			throws TException {
		log.info("进入调用缓存服务-查询点对点缓存数据接口...");
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return ptpService.queryPtp(maxCode,minCode,uuid);
	}

	@Override
	public Result saveData(String arg0, String arg1, String arg2, String arg3,
			String arg4) throws TException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Result saveExpiryData(String arg0, String arg1, String arg2,
			String arg3, String arg4, int arg5) throws TException {
		// TODO 自动生成的方法存根
		return null;
	}

/*	@Override
	public Result savePtp(String maxCode, String minCode, String uuid, String ptpResult,
			int expiredTime) throws TException {
		log.info("进入调用缓存服务-保存点对点缓存数据接口...");
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return ptpService.savePtp(maxCode,minCode,uuid,ptpResult,expiredTime);
	}
*/
	@Override
	public Result updateData(String arg0, String arg1, String arg2,
			String arg3, String arg4) throws TException {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Result updatePtp(String maxCode, String minCode, String uuid, String ptpResult)
			throws TException {
		log.info("进入调用缓存服务-更新点对点缓存数据接口...");
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return ptpService.updatePtp(maxCode,minCode,uuid,ptpResult);
	}
/*
	@Override
	public Result savePtp(String maxCode, String minCode, String uuid, String ptpResult,
			int arg4) throws TException {
		log.info("进入调用缓存服务-保存点对点缓存数据接口...");
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return ptpService.savePtp(maxCode,minCode,uuid,ptpResult,expiredTime);
	}*/

	@Override
	public Result savePtp(String maxCode, String minCode, String uuid, String ptpResult)
			throws TException {
		log.info("进入调用缓存服务-保存点对点缓存数据接口...");
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return ptpService.savePtp(maxCode,minCode,uuid,ptpResult);
	}

	@Override
	public Result deleteSensitive(String maxCode, String minCode, String fileName)
			throws TException {
		log.info("进入调用缓存服务-删除设备信息接口...");
		log.info("输入数据：" + fileName.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}
		return sensitiveService.deleteSensitive(maxCode, minCode, fileName);
	}

	@Override
	public SensitiveCache querySensitive(String maxCode, String minCode, String fileName)
			throws TException {
		log.info("进入调用缓存服务-根据文件名(fileName)查询敏感规则库信息接口...");
		log.info("输入数据：" + fileName.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return sensitiveService.querySensitive(maxCode, minCode, fileName);
	}

	@Override
	public String querySensitiveByField(String maxCode, String minCode, String fileName,
			String fieldKey) throws TException {
		log.info("进入调用缓存服务-根据文件名(fileName)查询敏感规则库信息接口...");
		log.info("输入数据：" + fileName);
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return null;
		}
		return sensitiveService.querySensitiveByField(maxCode, minCode, fileName, fieldKey);
	}

	@Override
	public Result saveSensitive(String maxCode, String minCode, SensitiveCache sensitiveCache)
			throws TException {
		// TODO Auto-generated method stub
		log.info("进入调用缓存服务-保存敏感规则库信息接口...");
		log.info("输入数据：" + sensitiveCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}
		return sensitiveService.saveSensitive(maxCode, minCode, sensitiveCache);
	}

	@Override
	public Result updateSensitive(String maxCode, String minCode, SensitiveCache sensitiveCache)
			throws TException {
		log.info("进入调用缓存服务-更新敏感规则库信息接口...");
		log.info("输入数据：" + sensitiveCache.toString());
		if (checkCode(maxCode, minCode).getCode() != 0) {
			return result;
		}
		return sensitiveService.updateSensitive(maxCode, minCode, sensitiveCache);
	}

}
