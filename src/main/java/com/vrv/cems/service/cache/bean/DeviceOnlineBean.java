package com.vrv.cems.service.cache.bean;

public class DeviceOnlineBean {
	/**
	 * 设备唯一ID
	 */
	private String devOnlyId;
	/**
	 * 设备登陆时间
	 */
	private String loginTime;
	/**
	 * 设备最后活跃时间(即最后心跳时间)
	 */
	private String activeTime;
	/**
	 * 设备登陆会话ID
	 */
	private String sessionId;
	/**
	 * 设备udp上一次心跳时间
	 */
	private String udpActiveTime;
	/**
	 * 设备udp心跳包路由时间
	 */
	private String routeIp;
	/**
	 * 设备upd心跳端口
	 */
	private String udpPort;
	
	public DeviceOnlineBean() {
		super();
	}

	public DeviceOnlineBean(String devOnlyId, String loginTime,
			String activeTime, String sessionId, String udpActiveTime,
			String routeIp, String udpPort) {
		super();
		this.devOnlyId = devOnlyId;
		this.loginTime = loginTime;
		this.activeTime = activeTime;
		this.sessionId = sessionId;
		this.udpActiveTime = udpActiveTime;
		this.routeIp = routeIp;
		this.udpPort = udpPort;
	}

	public String getDevOnlyId() {
		return devOnlyId;
	}

	public void setDevOnlyId(String devOnlyId) {
		this.devOnlyId = devOnlyId;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUdpActiveTime() {
		return udpActiveTime;
	}

	public void setUdpActiveTime(String udpActiveTime) {
		this.udpActiveTime = udpActiveTime;
	}

	public String getRouteIp() {
		return routeIp;
	}

	public void setRouteIp(String routeIp) {
		this.routeIp = routeIp;
	}

	public String getUdpPort() {
		return udpPort;
	}

	public void setUdpPort(String udpPort) {
		this.udpPort = udpPort;
	}
	
	
	
	
}
