package com.vrv.cems.service.cache.bean;

import com.vrv.cems.service.dbtools.Column;
import com.vrv.cems.service.dbtools.PrimaryKey;
import com.vrv.cems.service.dbtools.Table;

@Table(name="cems_cinstallpack")
public class CInstallPackBean {
	/**
	 * 安装包ID
	 */
	 @PrimaryKey
	 @Column(value="id")
	private String cInstallPackId;
	/**
	 * 安装包名称
	 */
	 @Column(value="name")
	private String name;
	/**
	 * 安装包版本
	 */
	 @Column(value="version")
	private String version;
	/**
	 * 安装包大小
	 */
	 @Column(value="size")
	int	size;
	/**
	 * 安装包在FASTDFS上的路径
	 */
	 @Column(value="path")
	private String path;
	/**
	 * 安装包操作系统类型
	 */
	 @Column(value="osType")
	private String osType;
	/**
	 * 安装包产品类型
	 */
	 @Column(value="productType")
	private String productType;
	/**
	 * 安装包发布时间
	 */
	 @Column(value="pubTime")
	private String pubTime;
	/**
	 * 安装包CRC
	 */
	 @Column(value="crc")
	private String crc;
	 
	 
	public CInstallPackBean() {
		super();
	}
	public CInstallPackBean(String cInstallPackId, String name,
			String version, int size, String path, String osType,
			String productType, String pubTime, String crc) {
		super();
		this.cInstallPackId = cInstallPackId;
		this.name = name;
		this.version = version;
		this.size = size;
		this.path = path;
		this.osType = osType;
		this.productType = productType;
		this.pubTime = pubTime;
		this.crc = crc;
	}
	public String getcInstallPackId() {
		return cInstallPackId;
	}
	public void setcInstallPackId(String cInstallPackId) {
		this.cInstallPackId = cInstallPackId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getPubTime() {
		return pubTime;
	}
	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}
	public String getCrc() {
		return crc;
	}
	public void setCrc(String crc) {
		this.crc = crc;
	} 
	
	
	
}
