package com.vrv.cems.service.quartz;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.thrift.TException;
import org.dom4j.DocumentException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import com.sys.common.util.IPAddressUtils;
import com.sys.common.util.security.CRCUtils;
import com.vrv.cems.service.base.bean.BaseEnDecryptResultBean;
import com.vrv.cems.service.base.bean.ResultMsgBean;
import com.vrv.cems.service.base.interfaces.ICEMSService;
import com.vrv.cems.service.base.interfaces.ICacheService;
import com.vrv.cems.service.base.interfaces.IOnlineService;
import com.vrv.cems.service.base.interfaces.IUDPService;
import com.vrv.cems.service.base.interfaces.impl.CEMSServiceImpl;
import com.vrv.cems.service.base.util.BaseEncryptDecryptUtils;
import com.vrv.cems.service.base.util.ByteBufferUtils;
import com.vrv.cems.service.cache.bean.DBServParamBean;
import com.vrv.cems.service.cache.util.PolicyConfigService;
import com.vrv.cems.service.configure.utils.ReadConfigPropertiesUtils;
import com.vrv.cems.service.dbtools.DBUtil;
import com.vrv.cems.service.init.SystemConstants;


public class PolicyXmlJob implements Job{
	 private static Log logger = LogFactory.getLog(PolicyXmlJob.class);
	 private ServiceParamBean newServBean;
	 
	@Override
	public void execute(JobExecutionContext paramJobExecutionContext) {
		String xmlPath = SystemConstants.PATH_POLICY_XML;
		@SuppressWarnings("unused")
		// DBServParamBean
		// servParam=DBUtil.queryByServiceCode(IBlockService.SERVICE_CODE);
		String serviceIp = ReadConfigPropertiesUtils.getValue(
				SystemConstants.PATH_CONFIG_PROPERTIES, "service.ip");
		String crcValue = null;
		try {
			crcValue = CRCUtils.getCRC32StringValue(new FileInputStream(
					new File(SystemConstants.PATH_POLICY_XML)));
		} catch (FileNotFoundException e) {
			logger.error("未能加载policy.xml!", e);
		} catch (IOException e) {
			logger.error("加载policy.xml报错!", e);
		}
		if (StringUtils.isNotBlank(serviceIp)) {
			JSONObject json = new JSONObject();
			json.accumulate("serverId", IPAddressUtils.ip2UUID(serviceIp));
			json.accumulate("serviceCode", ICacheService.SERVICE_CODE);
			json.accumulate("crc", crcValue);
			String dataString = json.toString();
			ICEMSService cemsService = new CEMSServiceImpl();
			BaseEnDecryptResultBean encrypt = BaseEncryptDecryptUtils.encrypt(
					SystemConstants.ISENCRYPT, SystemConstants.ISZIP,
					ByteBufferUtils.string2ByteBuffer(dataString));
			ByteBuffer resultByteBuffer;
			try {
				resultByteBuffer = cemsService.getDataTS(
						ICEMSService.SERVICE_CODE, ICEMSService.POLICY_XML,
						encrypt.getCrc(), SystemConstants.ISZIP,
						encrypt.getData(), SystemConstants.ISENCRYPT,
						encrypt.getKey(), encrypt.getFlag());
				ResultMsgBean decrypt = BaseEncryptDecryptUtils.decrypt(
						SystemConstants.ISENCRYPT, SystemConstants.ISZIP,
						resultByteBuffer, encrypt.getKey(), encrypt.getFlag());
				JSONObject jsonObject = (JSONObject) decrypt.getJdata().get(0);
				Integer isChange = Integer.valueOf(jsonObject.get("isChange")
						.toString());
				if (isChange == 1) {
					String policyXmlContent = jsonObject.getString("policyXml");
					// 依次取修改各种配置
					newServBean = PolicyConfigService.getInstance()
							.modifyLocalPolicyParam(policyXmlContent, xmlPath);
					// 回写
					PolicyConfigService.getInstance()
							.localServicePolicyFileWrite(newServBean);
				} else {
					logger.info("服务策略没有变化,不需要更新");
				}
			} catch (TException e) {
				logger.error("访问CEMS平台报错!", e);
			} catch (FileNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		} else {
			logger.info("更新服务策略执行结束");
		}
	}
	
	
}
