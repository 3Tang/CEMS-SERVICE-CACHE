package com.vrv.cems.service.cache.bean;

import com.vrv.cems.service.dbtools.Column;
import com.vrv.cems.service.dbtools.PrimaryKey;
import com.vrv.cems.service.dbtools.Table;

@Table(name="cems_policy")
public class PolicyBean {
	/**
	 * ID
	 */
	 @PrimaryKey
	 @Column(value="id")
	private String id;
	/**
	 * 策略执行对象类型
	 */
	 @Column(value="objType")
	private String objType;
	/**
	 * 名称
	 */
	 @Column(value="name")
	private String name;
	/**
	 * 内容
	 */
	 @Column(value="content")
	private String content;
	/**
	 * CRC值
	 */
	 @Column(value="crc")
	private String crc;
	/**
	 * 策略content xml内容中policy表中的crc的属性值
	 */
	 @Column(value="contentCRC")
	private String contentCRC;
	/**
	 * 组织机构ID
	 */
	 @Column(value="organizationId")
	private String organizationId;
	/**
	 * 下发时间
	 */
	 @Column(value="publishTime")
	private String publishTime;
	/**
	 * 策略模板ID
	 */
	 @Column(value="policyTemplateId")
	private String policyTemplateId;
	/**
	 * 策略对象
	 */
	private String obj;
	/**
	 * 策略对象CRC
	 */
	private String objCRC;
	
	public PolicyBean() {
		super();
	}
	public PolicyBean(String id, String objType, String name, String content,
			String crc, String contentCRC, String organizationId,
			String publishTime, String policyTemplateId, String obj,
			String objCRC) {
		super();
		this.id = id;
		this.objType = objType;
		this.name = name;
		this.content = content;
		this.crc = crc;
		this.contentCRC = contentCRC;
		this.organizationId = organizationId;
		this.publishTime = publishTime;
		this.policyTemplateId = policyTemplateId;
		this.obj = obj;
		this.objCRC = objCRC;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getObjType() {
		return objType;
	}
	public void setObjType(String objType) {
		this.objType = objType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCrc() {
		return crc;
	}
	public void setCrc(String crc) {
		this.crc = crc;
	}
	public String getContentCRC() {
		return contentCRC;
	}
	public void setContentCRC(String contentCRC) {
		this.contentCRC = contentCRC;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getPolicyTemplateId() {
		return policyTemplateId;
	}
	public void setPolicyTemplateId(String policyTemplateId) {
		this.policyTemplateId = policyTemplateId;
	}
	public String getObj() {
		return obj;
	}
	public void setObj(String obj) {
		this.obj = obj;
	}
	public String getObjCRC() {
		return objCRC;
	}
	public void setObjCRC(String objCRC) {
		this.objCRC = objCRC;
	}
	
	
	
	
}
