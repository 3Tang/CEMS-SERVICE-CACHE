package com.vrv.cems.service.cache.bean;

import com.vrv.cems.service.dbtools.Column;
import com.vrv.cems.service.dbtools.PrimaryKey;
import com.vrv.cems.service.dbtools.Table;

/**
 * <B>说 明</B>:
 * 
 * @author 作 者 名：shanfeng<br/>
 *         E-mail ：baishanfeng@vrvmail.com.cn
 * 
 * @version 版 本 号：V1.0.<br/>
 *          创建时间：2015年10月29日 下午4:36:38
 */
@Table(name = "cems_sensitive")
public class SensitiveBean{
	/**
	 * ID
	 */
	@PrimaryKey
	@Column(value = "id")
	private String id; // required

	/**
	 * name
	 */
	@Column(value = "name")
	private String name;

	/**
	 * path
	 */
	@Column(value = "path")
	private String path;

	/**
	 * crc
	 */
	@Column(value = "crc")
	private String crc;

	/**
	 * createTime
	 */
	@Column(value = "createTime")
	private String createTime;

	/**
	 * lastUpdateTime
	 */
	@Column(value = "lastUpdateTime")
	private String lastUpdateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCrc() {
		return crc;
	}

	public void setCrc(String crc) {
		this.crc = crc;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public SensitiveBean(String id, String name, String path, String crc,
			String createTime, String lastUpdateTime) {
		super();
		this.id = id;
		this.name = name;
		this.path = path;
		this.crc = crc;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
	}

	public SensitiveBean() {
		super();
		// TODO Auto-generated constructor stub
	}

}
