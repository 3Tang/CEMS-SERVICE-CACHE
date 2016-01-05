package com.vrv.cems.service.cache.bean;

public class ProdInfoNewBean {
	/**
	 * <B>说 明</B>:设备产品信息(应安装)结构体
	 * 
	 * @author 作 者 名：高林武<br/>
	 *         E-mail ：linwu_gao@163.com
	 * 
	 * @version 版 本 号：V1.0.<br/>
	 *          创建时间：2015年4月20日 上午10:09:58
	 */
	
		/**
		 * 产品升级包类型(0-基础升级包;1-定制升级包)
		 */
		private int type;
		/**
		 * 产品名称(升级包名称)
		 */
		private String productName;
		/**
		 * 产品版本(升级包版本)
		 */
		private String version;
		/**
		 * 产品安装包(升级包)签名信息
		 */
		private String productSign;
		/**
		 * 产品升级包ID(对应缓存结构14中cUpgradePackId)
		 */
		private String cUpgradePackId;
		public ProdInfoNewBean() {
			super();
		}
		public ProdInfoNewBean(int type, String productName, String version,
				String productSign, String cUpgradePackId) {
			super();
			this.type = type;
			this.productName = productName;
			this.version = version;
			this.productSign = productSign;
			this.cUpgradePackId = cUpgradePackId;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		public String getProductSign() {
			return productSign;
		}
		public void setProductSign(String productSign) {
			this.productSign = productSign;
		}
		public String getcUpgradePackId() {
			return cUpgradePackId;
		}
		public void setcUpgradePackId(String cUpgradePackId) {
			this.cUpgradePackId = cUpgradePackId;
		}
		
		
		
}
