package com.vrv.cems.service.cache.bean;

import java.nio.ByteBuffer;

/**
 * <B>说 明</B>:输入参数bean
 * 
 * @author 作 者 名：tom<br/>
 *         E-mail ：zhuxudong@vrvmail.com.cn
 * 
 * @version 版 本 号：V1.0.<br/>
 *          创建时间：2014年9月10日 上午11:20:39
 */
public class InParam {
	/**
	 * 数据通讯标准接口
	 * 
	 * @param maxCode
	 *            主功能号
	 * @param minCode
	 *            子功能号
	 * @param checkCode
	 *            data数据crc校验值
	 * @param isZip
	 *            data数据是否压缩：0表示不压缩；1表示压缩
	 * @param data
	 *            data通讯的业务数据
	 * @param sessionId
	 *            客户端登录sessionId
	 * @param msgCode
	 *            用于防止数据包伪造，每次会累加1
	 */
	private String maxCode;
	private String minCode;
	private String checkCode;
	private boolean isZip;
	private ByteBuffer data;
	private String sessionOrkey;
	private Integer msgCodeOrflag;
	private boolean isEncrypt;
	
	
	
	
	public InParam(String maxCode, String minCode, String checkCode,
			boolean isZip, ByteBuffer data, boolean isEncrypt, String sessionOrkey,
			Integer msgCodeOrflag) {
		super();
		this.maxCode = maxCode;
		this.minCode = minCode;
		this.checkCode = checkCode;
		this.isZip = isZip;
		this.data = data;
		this.sessionOrkey = sessionOrkey;
		this.msgCodeOrflag = msgCodeOrflag;
		this.isEncrypt = isEncrypt;
	}
	
	
	public InParam() {
		// TODO 自动生成的构造函数存根
	}


	@Override
	public String toString() {
		return "InParam [maxCode=" + maxCode + ", minCode=" + minCode
				+ ", checkCode=" + checkCode + ", isZip=" + isZip + ", data="
				+ data + ", sessionOrkey=" + sessionOrkey + ", msgCodeOrflag="
				+ msgCodeOrflag + ", isEncrypt=" + isEncrypt + "]";
	}


	public String getMaxCode() {
		return maxCode;
	}
	public void setMaxCode(String maxCode) {
		this.maxCode = maxCode;
	}
	public String getMinCode() {
		return minCode;
	}
	public void setMinCode(String minCode) {
		this.minCode = minCode;
	}
	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	public boolean isZip() {
		return isZip;
	}
	public void setZip(boolean isZip) {
		this.isZip = isZip;
	}
	public ByteBuffer getData() {
		return data;
	}
	public void setData(ByteBuffer data) {
		this.data = data;
	}
	public String getSessionOrkey() {
		return sessionOrkey;
	}
	public void setSessionOrkey(String sessionOrkey) {
		this.sessionOrkey = sessionOrkey;
	}
	public Integer getMsgCodeOrflag() {
		return msgCodeOrflag;
	}
	public void setMsgCodeOrflag(Integer msgCodeOrflag) {
		this.msgCodeOrflag = msgCodeOrflag;
	}
	public boolean isEncrypt() {
		return isEncrypt;
	}
	public void setEncrypt(boolean isEncrypt) {
		this.isEncrypt = isEncrypt;
	}

	
	
}
