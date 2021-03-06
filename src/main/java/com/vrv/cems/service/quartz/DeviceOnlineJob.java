package com.vrv.cems.service.quartz;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.sys.common.util.Assert;
import com.sys.common.util.DateUtils;
import com.sys.common.util.StringUtils;
import com.sys.common.util.UUIDUtils;
import com.sys.common.util.security.AESUtils;
import com.vrv.cems.service.base.bean.cache.DeviceCache;
import com.vrv.cems.service.base.bean.cache.DeviceOnlineCache;
import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.base.bean.cache.UserCache;
import com.vrv.cems.service.base.bean.cache.UserOnlineCache;
import com.vrv.cems.service.base.interfaces.ICacheService;
import com.vrv.cems.service.cache.core.Services;
import com.vrv.cems.service.cache.util.RedisUtil;
import com.vrv.cems.service.cache.util.XmlUtil;
import com.vrv.cems.service.dbtools.DBUtil;
import com.vrv.cems.service.init.SystemConstants;
import com.vrv.cems.service.quartz.ServiceParamBean.ParamBean;

/**
 * 清理设备在线信息策略JOB
 * 
 * @author 作 者 名：唐铁桥; E-mail ：tangtieqiao@vrvmail.com.cn
 * 
 * @version 版 本 号：V1.0; 创建时间：2015年5月9日 上午10:09:41
 */
public class DeviceOnlineJob implements Job {
	private static final Logger log = Logger.getLogger(DeviceOnlineJob.class);
	private static final String CEMS_DEVICE_TABLE_NAME = "cems_device";
	Long limitTime;
	private ServiceParamBean ServParamBean;
	// true超时false不超时
	boolean isOvertime;
	Set<String> devOnlyIdSet = new HashSet<String>();
	private static Result devOnlineResult=new Result();
	private static  Result devKeyResult=new Result();
	Date clientDate = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String clientTime = sdf.format(clientDate);
    String reportTime=clientTime;
	
	@Override
	public void execute(JobExecutionContext paramJobExecutionContext)
			throws JobExecutionException {
		// 取缓存的值和配置文件中的值比较
		// 若两者之差大于配置文件设定的值 则 执行 设备登出操作
		// 设备登出操作 除了清理缓存 还要修改数据库的里的isOpened字段
		// 初始化 当前时间 缓存中的值
		log.info("心跳超时清理定时器开始执行...");
		try {
			init();
		} catch (Exception e1) {
			log.error("缓存服务 在线设备 清理 定时器初始化 失败!"+e1.getMessage());
			
		}
		// 比较操作返回结果
		if (devOnlyIdSet.size() > 0) {
			Iterator iterator = devOnlyIdSet.iterator();
			while (iterator.hasNext()) {
				boolean isOverTime=false;
				DeviceOnlineCache deviceOnlineCache = new DeviceOnlineCache();
				String devOnlineKey = (String) iterator.next();
				String devOnlyId = devOnlineKey.replace(
						ICacheService.PREFIX_DEVICEONLINECACHE, "");
				deviceOnlineCache = Services.deviceOnlineService
						.queryDeviceOnlineByDevOnlyId(
								ICacheService.SERVICE_CODE,
								ICacheService.QUERYDEVICEONLINEBYDEVONLYID,
								devOnlyId);

				String activeTimeStr = deviceOnlineCache.getActiveTime();
				try {
					if(StringUtils.isNotBlank(activeTimeStr)){
						isOverTime=isOverTime(activeTimeStr);
					}
				} catch (ParseException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				//若超时则执行清理操作
				if(isOverTime)
				{
					boolean operResult=operate(devOnlyId);
					if(operResult)
					{
						log.info("清理设备["+devOnlyId+"]在线成功!");
					}
					else
					{
						log.info("清理设备["+devOnlyId+"]在线失败!");
					}
				}
			
			}

		}
		log.info("心跳超时清理定时器执行结束...");
		
	}


	

	private boolean operate(String devOnlyId) {
		// TODO 自动生成的方法存根
		// 删除 设备在线缓存表 和 设备会话表
		 devOnlineResult= Services.deviceOnlineService
		   .deleteDeviceOnlineByDevOnlyId(
					ICacheService.SERVICE_CODE,
					ICacheService.DELETEDEVICEONLIEBYDEVONLYID,
					devOnlyId);
		 //System.out.println("devOnlineResult.getCode()"+devOnlineResult.getCode());
		if(devOnlineResult.getCode()!=0)
		{
			log.error("心跳超时清理定时器清理设备在线缓存["+devOnlyId+"]失败!");
		}else
		{
			log.info("心跳超时清理定时器清理设备在线缓存["+devOnlyId+"]成功!");
		}
		
		 devKeyResult= Services.deviceKeyService
				   .deleteDeviceKeyByDevOnlyId(
							ICacheService.SERVICE_CODE,
							ICacheService.DELETEDEVICEKEYBYDEVONLYID,
							devOnlyId);
		 //System.out.println("devKeyResult.getCode()"+devKeyResult.getCode());
				if(devKeyResult.getCode()!=0)
				{
					log.error("心跳超时清理定时器清理设备会话秘钥["+devOnlyId+"]失败!");
				}else
				{
					log.info("心跳超时清理定时器清理设备会话秘钥["+devOnlyId+"]成功!");
				}
				//先清理设备再清理设备关联用户
				dealDevOnlineDB(devOnlyId);
				//根据user_device的关系,删除用户在线的缓存数据
				List<String> userOnlyIds=DBUtil.getUserOnlyIdByDevOnlyId(devOnlyId);
				if(userOnlyIds!=null&&userOnlyIds.size()>1)
				{
					for(String userOnlyId:userOnlyIds){
						try {
							updateUserDevice(devOnlyId, userOnlyId);
							updateUserDeviceLoginLog(devOnlyId,userOnlyId);
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
						Services.userOnlineService.deleteUserOnlineByUserOnlyId(ICacheService.SERVICE_CODE, ICacheService.DELETEUSERONLINEBYUSERONLYID, userOnlyId);
					}
				}
				
				//更改数据库状态
			if(devOnlineResult.getCode()==0&&devKeyResult.getCode()==0)
			{
				return true;
			}	
			else
			{
				return false;
			}
		
			
	}


	//清理数据库的逻辑同在线服务 的 设备登出逻辑
	 void dealDevOnlineDB(String devOnlyId) { 
		 String activeTime=DateUtils.format(new Date());
		 updateDeviceOnline(devOnlyId);
		 updateDeviceInfo(devOnlyId,activeTime);
		 updateDevice(devOnlyId,SystemConstants.DEVICE_ISNOTOPENED);				
	}



	private void updateUserDeviceLoginLog(String devOnlyId,String userOnlyId) throws ParseException  {
				if (StringUtils.isNotBlank(userOnlyId)) {
					String id = UUIDUtils.get32UUID();
					String loginTime = DateUtils.format(new Date());
					DeviceCache  deviceCache=Services.deviceService.queryDeviceByDevOnlyId(ICacheService.SERVICE_CODE, ICacheService.QUERYDEVICEBYDEVONLYID, devOnlyId);
					UserOnlineCache userOnlineCache =Services.userOnlineService.queryUserOnlineByUserOnlyId(ICacheService.SERVICE_CODE, ICacheService.QUERYUSERONLINEBYUSERONLYID, userOnlyId);
					String userAccount =userOnlineCache.getAccount();
					String loginAccount = userOnlineCache.getLoginAccount();
					String lastLoginTime = userOnlineCache.getLoginTime();
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					Long lastLoginDateMill=0l;
					String ip=StringUtils.isBlank(deviceCache.getIp())?"null":deviceCache.getIp();
					String mac=StringUtils.isBlank(deviceCache.getMac())?"null":deviceCache.getMac();
					int type = SystemConstants.TYPE_LOGOUT;
					int fromType = SystemConstants.FROM_TYPE_DEVICE;
					Long currentTime = System.currentTimeMillis();
					String clientTime=loginTime;
					String reportTime=loginTime;
					String onlineTime="";
					if(StringUtils.isNotBlank(lastLoginTime)){
						lastLoginDateMill = sdf.parse(lastLoginTime).getTime();
						onlineTime = getDayHourMinuteSecond(currentTime-lastLoginDateMill);
					}
					
					insertUserLoginLog(userAccount, devOnlyId, ip, mac,
							loginAccount, type, clientTime, userOnlyId,
							reportTime, onlineTime, fromType);
	
				}
			
		
	}
	
	private static void insertDeviceLoginLog(String id, String userAccount,
			String devOnlyId, String ip, String mac, String osLoginAccount,
			int type, String clientTime, String userOnlyId, String reportTime,
			String onlineTime, int fromType) {
		String insertSql = "insert into cems_log_device_clientlog(id,regUserOnlyId,regUserAccount,devOnlyId,ip,mac,clientTime,reportTime,osLoginAccount,type,onlineTime) values(?,?,?,?,?,?,?,?,?,?,?)";
		Object[] insertParams = { id, userOnlyId, userAccount, devOnlyId, ip,
				mac, clientTime, reportTime, osLoginAccount, type, onlineTime};
		try {
			DBUtil.queryRunner.update(insertSql, insertParams);
			log.info("写入cems_log_device_loginlog成功!");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	private static synchronized void insertUserLoginLog(String userAccount,
			String devOnlyId, String ip, String mac, String osLoginAccount,
			int type, String clientTime, String userOnlyId, String reportTime,
			String onlineTime, int fromType) {
		String id = UUIDUtils.get32UUID();
		String insertSql = "insert into cems_log_user_loginlog(id,userOnlyId,userAccount,devOnlyId,ip,mac,clientTime,reportTime,osLoginAccount,type,onlineTime,fromType) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] insertParams = { id, userOnlyId, userAccount, devOnlyId, ip,
				mac, clientTime, reportTime, osLoginAccount, type, onlineTime,
				fromType };
		try {
			if(StringUtils.isNotBlank(userOnlyId)&&StringUtils.isNotBlank(userAccount)&&StringUtils.isNotBlank(devOnlyId)&&StringUtils.isNotBlank(ip)){
				DBUtil.queryRunner.update(insertSql, insertParams);
			}
			log.info("写入cems_log_user_loginlog成功!");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public static String getDayHourMinuteSecond(long timeMillis) {

		long totalSeconds = timeMillis / 1000;

		int second = (int) (totalSeconds % 60);// 秒

		long totalMinutes = totalSeconds / 60;

		int minute = (int) (totalMinutes % 60);// 分

		long totalHours = totalMinutes / 60;

		int hour = (int) (totalHours % 24);// 时

		int totalDays = (int) (totalHours / 24);

		return totalDays + "天" + hour + "小时" + minute + "分" + second + "秒";

	}


	private void updateDeviceOnline(String devOnlyId) {
		String delSql = "delete  from cems_device_online where devOnlyId=?";
		Object[] delParams = { devOnlyId };
		try {
			DBUtil.queryRunner.update(delSql, delParams);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void updateDevice(String devOnlyId, int deviceIsopened) {
		String updateSql = "update cems_device set isOpened=? where devOnlyId=?";
		Object[] updateParam = {deviceIsopened, devOnlyId };
		try {
			DBUtil.queryRunner.update(updateSql, updateParam);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("更新cems_device表成功!");
	}


	public static void updateUserDevice(String devOnlyId, String userOnlyId) {
		String deleteSql = "delete from cems_user_device where devOnlyId=? and userOnlyId=?";
		Object[] deleteParam = {devOnlyId,userOnlyId};
		try {
			DBUtil.queryRunner.update(deleteSql, deleteParam);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateDeviceInfo(String devOnlyId, String activeTime) {
		String updateSql = "update cems_deviceinfo set lastActiveTime=? where devOnlyId=?";
		Object[] updateParam = { activeTime, devOnlyId };
		try {
			DBUtil.queryRunner.update(updateSql, updateParam);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("更新cems_deviceinfo表成功!");
	}
	
	

	private boolean isOverTime(String activeTimeStr) throws ParseException {
		// TODO 自动生成的方法存根
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		long currentTime = System.currentTimeMillis();
		long activeTime = sdf.parse(activeTimeStr).getTime();
		if((currentTime-activeTime)/(60*1000)>limitTime)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	

	
	/**
	 * @throws Exception  
	* @Title: init 
	* @Description:  
	* @param @throws Exception  参数说明 
	* @return void    返回类型 
	* @throws 
	*/
	public void init() throws Exception {
		
		// 遍历设备在线中的deviceOnlyId
		devOnlyIdSet = RedisUtil.getInstance().keys(
				ICacheService.PREFIX_DEVICEONLINECACHE + "*");
		// 从xml文件中取
		// 获取路径
		String policyPath = SystemConstants.PATH_POLICY_XML;
		InputStream ins;
		try {
			ins = new FileInputStream(policyPath);
			ServParamBean = XmlUtil.xmlToObject(ins, ServiceParamBean.class);
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			log.error("缓存服务 设备在线清理定时器 policy.xml文件不存在!"+e.getMessage());
			throw new Exception(e);
		}
			
		// 取出来的结构是一个List
		if(ServiceParamBean.StaticParams==null)
		{
			ServiceParamBean.StaticParams=ServParamBean.getParams();
		}
		List<ParamBean> paramBeanList =ServiceParamBean.StaticParams;

		for (ParamBean paramBean : paramBeanList) {
			if (StringUtils.isNotBlank(paramBean.getKey())
					&& "deviceAliveTime".equals(paramBean.getKey())) {
					limitTime = Long.parseLong(paramBean.getValue());
					log.info("缓存服务 设备在线清理定时器  取得policy.xml的心跳deviceAliveTime为"+limitTime+"分!");
			}
			
		}

		if(limitTime==0||limitTime==null)
		{
			log.error("缓存服务 设备在线清理定时器  取得policy.xml的心跳 deviceAliveTime为空!");
			throw new Exception("deviceAliveTime为空!");
		}
		
	}
	
	
}
