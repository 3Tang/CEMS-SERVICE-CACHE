package com.vrv.cems.service.cache.bean;

import java.io.Serializable;

import com.vrv.cems.service.dbtools.Column;
import com.vrv.cems.service.dbtools.IgnoreColumn;
import com.vrv.cems.service.dbtools.PrimaryKey;
import com.vrv.cems.service.dbtools.Table;

/**
 * <B>说 明</B>:
 * 
 * @author 作 者 名：glw<br/>
 *         E-mail ：linwu_gao@163.com
 * 
 * @version 版 本 号：V1.0.<br/>
 *          创建时间：2015年4月21日 下午9:38:30
 */
@Table(name = "cems_device")
public class DeviceBean implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	@IgnoreColumn
	private static final long serialVersionUID = -576163171339049622L;
	/**
	 * ID
	 */
	@PrimaryKey
	@Column(value = "id")
	private String id; // required
	/**
	 * 设备唯一ID
	 */
	@Column(value = "devOnlyId")
	private String devOnlyId; // required
	/**
	 * IP地址
	 */
	@Column(value = "ip")
	private String ip; // required
	/**
	 * IP类型
	 */
	@Column(value = "ipType")
	private String ipType; // required
	/**
	 * IP数值
	 */
	@Column(value = "ipNumber")
	private String ipNumber; // required
	/**
	 * MAC地址
	 */
	@Column(value = "mac")
	private String mac; // required
	/**
	 * CEMS客户端安装时间
	 */

	private String registerTime; // required
	/**
	 * CEMS客户端唯一ID
	 */
	@Column(value = "clientId")
	private String clientId; // required
	/**
	 * CEMS客户端名称
	 */
	@Column(value = "clientName")
	private String clientName; // required
	/**
	 * CEMS客户端签名
	 */
	@Column(value = "clientSign")
	private String clientSign; // required
	/**
	 * CEMS客户端注册程序版本号
	 */
	@Column(value = "clientVersion")
	private String clientVersion; // required
	/**
	 * CEMS客户端核心模块版本
	 */
	@Column(value = "softVersion")
	private String softVersion; // required
	/**
	 * 所属交换机IP
	 */
	private String switchIP; // required
	/**
	 * 所属交换机端口
	 */
	@Column(value = "switchPort")
	private String switchPort; // required
	/**
	 * 是否是多操作系统(1-单操作系统,>=2-多操作系统)
	 */
	@Column(value = "isMutiOs")
	private int isMutiOs; // required
	/**
	 * 是否是虚拟机(0-不是,1-是)
	 */
	@Column(value = "isVm")
	private int isVm; // required
	/**
	 * 客户端通讯ip
	 */
	@Column(value = "communicateIP")
	private String communicateIP; // required
	/**
	 * 路由IP
	 */
	private String routeIp; // required
	
	/**
	 * 注册人Id(userOnlyId)
	 */
	@Column(value = "userOnlyId")
	private String userOnlyId; // required
	/**
	 * 所属组织机构ID
	 */
	@Column(value = "organizationId")
	private String organizationId; // required
	
	/**
	 * 操作系统ID
	 */
	@Column(value = "osId")
	private int osId;

	/**
	 * 设备类型ID
	 */
	@Column(value = "deviceTypeId")
	private int deviceTypeId;

	/**
	 * 设备注册状态
	 */
	@Column(value = "regState")
	private int regState;

	
	/**
	 * 设备保护状态
	 */
	@Column(value = "protectState")
	private int protectState;
	
	/**
	 * 设备漫游状态
	 */
	@Column(value = "roamState")
	private int roamState;
	
	
	/**
	 * 设备删除状态
	 */
	@Column(value = "deleteState")
	private int deleteState;
	
	/**
	 * 子网掩码
	 */
	private String mask; // required

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDevOnlyId() {
		return devOnlyId;
	}

	public void setDevOnlyId(String devOnlyId) {
		this.devOnlyId = devOnlyId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIpType() {
		return ipType;
	}

	public void setIpType(String ipType) {
		this.ipType = ipType;
	}

	public String getIpNumber() {
		return ipNumber;
	}

	public void setIpNumber(String ipNumber) {
		this.ipNumber = ipNumber;
	}

	public String getMac() {
		return mac;
	}
	
	

	/** 
	 * @return osId 
	 */
	public int getOsId() {
		return osId;
	}

	/** 
	 * @param osId 要设置的 osId 
	 */
	public void setOsId(int osId) {
		this.osId = osId;
	}

	/** 
	 * @return deviceTypeId 
	 */
	public int getDeviceTypeId() {
		return deviceTypeId;
	}

	/** 
	 * @param deviceTypeId 要设置的 deviceTypeId 
	 */
	public void setDeviceTypeId(int deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	/** 
	 * @return regState 
	 */
	public int getRegState() {
		return regState;
	}

	/** 
	 * @param regState 要设置的 regState 
	 */
	public void setRegState(int regState) {
		this.regState = regState;
	}

	/** 
	 * @return protectState 
	 */
	public int getProtectState() {
		return protectState;
	}

	/** 
	 * @param protectState 要设置的 protectState 
	 */
	public void setProtectState(int protectState) {
		this.protectState = protectState;
	}

	/** 
	 * @return roamState 
	 */
	public int getRoamState() {
		return roamState;
	}

	/** 
	 * @param roamState 要设置的 roamState 
	 */
	public void setRoamState(int roamState) {
		this.roamState = roamState;
	}

	/** 
	 * @return deleteState 
	 */
	public int getDeleteState() {
		return deleteState;
	}

	/** 
	 * @param deleteState 要设置的 deleteState 
	 */
	public void setDeleteState(int deleteState) {
		this.deleteState = deleteState;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientSign() {
		return clientSign;
	}

	public void setClientSign(String clientSign) {
		this.clientSign = clientSign;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getSoftVersion() {
		return softVersion;
	}

	public void setSoftVersion(String softVersion) {
		this.softVersion = softVersion;
	}

	public String getSwitchIP() {
		return switchIP;
	}

	public void setSwitchIP(String switchIP) {
		this.switchIP = switchIP;
	}

	public String getSwitchPort() {
		return switchPort;
	}

	public void setSwitchPort(String switchPort) {
		this.switchPort = switchPort;
	}

	public int getIsMutiOs() {
		return isMutiOs;
	}

	public void setIsMutiOs(int isMutiOs) {
		this.isMutiOs = isMutiOs;
	}

	public int getIsVm() {
		return isVm;
	}

	public void setIsVm(int isVm) {
		this.isVm = isVm;
	}

	public String getCommunicateIP() {
		return communicateIP;
	}

	public void setCommunicateIP(String communicateIP) {
		this.communicateIP = communicateIP;
	}

	public String getRouteIp() {
		return routeIp;
	}

	public void setRouteIp(String routeIp) {
		this.routeIp = routeIp;
	}



	public String getUserOnlyId() {
		return userOnlyId;
	}

	public void setUserOnlyId(String userOnlyId) {
		this.userOnlyId = userOnlyId;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public DeviceBean() {
		super();
	}

}
