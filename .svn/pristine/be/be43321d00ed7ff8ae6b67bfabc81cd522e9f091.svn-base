package com.vrv.cems.service.cache.bean;

import com.vrv.cems.service.dbtools.Column;
import com.vrv.cems.service.dbtools.PrimaryKey;
import com.vrv.cems.service.dbtools.Table;

@Table(name="cems_user")
public class UserBean {
	/**
	 * ID
	 */
	 @PrimaryKey
	 @Column(value="id")
	private String id;
	/**
	 * 用户唯一ID
	 */
	@Column(value="userOnlyId")
	private String userOnlyId;
	/**
	 * 帐号
	 */
	@Column(value="account")
	private String account;
	/**
	 * 密码
	 */
	@Column(value="password")
	private String password;
	/**
	 * 账号类型
	 */
	@Column(value="accountTypeId")
	private String accountTypeId;
	/**
	 * 组织机构ID
	 */
	@Column(value="organizationId")
	private String organizationId;
	/**
	 * 注册时间
	 */
	@Column(value="registeTime")
	private String registeTime;
	/**
	 * 最后登录时间
	 */
	@Column(value="lastLoginTime")
	private String lastLoginTime;
	
	public UserBean() {
		super();
	}
	public UserBean(String id, String userOnlyId, String account,
			String password, String accountTypeId, String organizationId,
			String registeTime, String lastLoginTime) {
		super();
		this.id = id;
		this.userOnlyId = userOnlyId;
		this.account = account;
		this.password = password;
		this.accountTypeId = accountTypeId;
		this.organizationId = organizationId;
		this.registeTime = registeTime;
		this.lastLoginTime = lastLoginTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserOnlyId() {
		return userOnlyId;
	}
	public void setUserOnlyId(String userOnlyId) {
		this.userOnlyId = userOnlyId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccountTypeId() {
		return accountTypeId;
	}
	public void setAccountTypeId(String accountTypeId) {
		this.accountTypeId = accountTypeId;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getRegisteTime() {
		return registeTime;
	}
	public void setRegisteTime(String registeTime) {
		this.registeTime = registeTime;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	
	
}
