package com.vrv.cems.service.cache.bean;

public class UserPolicyBean {
	/**
	 * 用户唯一ID
	 */
	private String userOnlyId;
	/**
	 * 策略总版本(具体计算格式详见《VRV-VDP-064-缓存数据结构设计-CEMS.doc》文档中“9.设备执行策略信息”)
	 */
	private String policyVersion;
	/**
	 * 策略ID，多个以","分开
	 */
	private String policyIds;
	public UserPolicyBean() {
		super();
	}
	public UserPolicyBean(String userOnlyId, String policyVersion,
			String policyIds) {
		super();
		this.userOnlyId = userOnlyId;
		this.policyVersion = policyVersion;
		this.policyIds = policyIds;
	}
	public String getUserOnlyId() {
		return userOnlyId;
	}
	public void setUserOnlyId(String userOnlyId) {
		this.userOnlyId = userOnlyId;
	}
	public String getPolicyVersion() {
		return policyVersion;
	}
	public void setPolicyVersion(String policyVersion) {
		this.policyVersion = policyVersion;
	}
	public String getPolicyIds() {
		return policyIds;
	}
	public void setPolicyIds(String policyIds) {
		this.policyIds = policyIds;
	}
	
	
	
}
