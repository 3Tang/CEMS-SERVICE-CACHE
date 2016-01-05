package com.vrv.cems.service.cache.util;

import static com.vrv.cems.service.base.SystemConstants.PATH_LOG4J_PROPERTIES;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.TriggerKey;

import com.sys.common.util.CommentedProperties;
import com.sys.common.util.DateUtils;
import com.vrv.cems.service.base.SystemConstants;
import com.vrv.cems.service.base.bean.TimerBean;
import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.quartz.QuartzConfig;
import com.vrv.cems.service.quartz.ServiceParamBean;
import com.vrv.cems.service.quartz.ServiceParamBean.LogBean;
import com.vrv.cems.service.quartz.ServiceParamBean.ParamBean;
import com.vrv.cems.service.quartz.ServiceParamBean.ThriftBean;








/** 
 *   <B>说       明</B>:PolicyConfigService 策略配置服务类
 *
 * @author  作  者  名 ：tangtieqiao<br/>
 *		    E-mail ：tangtieqiao@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2015年5月12日上午11:39:55 
 */
public class PolicyConfigService {
	 private static Log logger = LogFactory.getLog(PolicyConfigService.class);

	 private static final  PolicyConfigService policyConfigService =new PolicyConfigService();

	 private PolicyConfigService()
	 {}
	 
	 public static PolicyConfigService getInstance()
	 {
		 return policyConfigService;
	 }
	 

		 /**
		 * 拉取自身服务JOB
		 */
		private static final String POLICY_XML_JOB_NAME = "PolicyXmlJob";

	
	 public synchronized  ServiceParamBean modifyLocalPolicyParam(String xmlString,String policyXmlStrPath) throws FileNotFoundException, IOException
	 {
		 InputStream policyIn=new ByteArrayInputStream(xmlString.getBytes());
     	 ServiceParamBean newServBean=XmlUtil.xmlToObject(policyIn, ServiceParamBean.class);
     	 ServiceParamBean localServBean =new ServiceParamBean();
 		String	policyXmlStr = PolicyConfigService.getInstance().policyXML2String(policyXmlStrPath);
     	InputStream policyLocal=new ByteArrayInputStream(policyXmlStr.getBytes());
		localServBean = XmlUtil.xmlToObject(policyLocal, ServiceParamBean.class);
     	
     	
     	LogBean  localLogBean=localServBean.getLogBean();
     	LogBean  newLogBean=newServBean.getLogBean();
     	String locallogLevel=localLogBean.getLogLevel();
     	String newlogLevel=newLogBean.getLogLevel();
     	
     	String locallogPath=localLogBean.getLogPath();
     	String newlogPath = newLogBean.getLogPath();
     	Result logLevelResult =new Result();
     	Result logPathResult =new Result();
     	if(StringUtils.isNotBlank(newlogLevel)/*&&!newlogLevel.equals(locallogLevel)*/)
     	{
     		
     		locallogLevel=newlogLevel;
     		localLogBean.setLogLevel(locallogLevel);
     		logLevelResult.setCode(1);
     		logLevelResult.setInfo("logLevel不同!");
     	}
     	 
     	if(StringUtils.isNotBlank(newlogPath)/*&&!newlogPath.equals(locallogPath)*/)
     	{
     		locallogPath=newlogPath;
     		localLogBean.setLogPath(locallogPath);
     		logPathResult.setCode(1);
     		logLevelResult.setInfo("logPath不同!");
     	}

     	localServBean.setLogBean(localLogBean);
     	//调用日志处理过程
     	if(logPathResult.getCode()!=0&&logLevelResult.getCode()!=0){
     		logger.info("日志配置不同 需要修改!");
     		logBusinessProcess(localServBean);
     	}
     	else
     	{
     		logger.info("日志配置相同!");
     	}
     	
     	
     	ThriftBean  localTrift=localServBean.getThriftBean();
     	ThriftBean  newTrift=newServBean.getThriftBean();
     	 String newSelectorThreads = newTrift.getSelectorThreads();
     	String  localSelectorThreads = localTrift.getSelectorThreads();
     	
     	String newWorkerThreads = newTrift.getWorkerThreads();
     	String localWorkerThreads = localTrift.getWorkerThreads();
     	
     	
     	
     	
     	//调用thrift处理过程
     	if(StringUtils.isNotBlank(newSelectorThreads)&&!newSelectorThreads.equals(localSelectorThreads))
     	{
     		localSelectorThreads=newSelectorThreads;
     		localTrift.setSelectorThreads(localSelectorThreads);
     	}
     	if(StringUtils.isNotBlank(newWorkerThreads)&&!newWorkerThreads.equals(localWorkerThreads))
     	{
     		localWorkerThreads=newWorkerThreads;
     		localTrift.setWorkerThreads(localWorkerThreads);
     	}
     	thriftBusinessProcess(localServBean);
     	
     	TimerBean timerBean = new TimerBean();
     	List<TimerBean> localTimerList=localServBean.getTimers();
     	List<TimerBean> newTimerList=newServBean.getTimers();
     	List<TimerBean> modifyTimerList=new ArrayList<TimerBean>();
     	//调用定时器处理过程
     	for(int i=0;i<localTimerList.size();i++)
     	{
     		for(int j=0;j<newTimerList.size();j++)
     		{
     			if(StringUtils.isNotBlank(newTimerList.get(j).getName())&&newTimerList.get(j).getName().equals(localTimerList.get(i).getName()))
     	     	{
     				if(StringUtils.isNotBlank(newTimerList.get(j).getCycle())&&!newTimerList.get(j).getCycle().equals(localTimerList.get(i).getCycle()))
         	     	{   
     					TimerBean tb=new TimerBean();
     					localTimerList.get(i).setCycle(newTimerList.get(j).getCycle());
     					tb=localTimerList.get(i);
     					modifyTimerList.add(tb);
         	     	}
     	     	}
     		}
     	}
     	
     	
     	
     	if(modifyTimerList.size()>0){
     		logger.info("不同定时器个数为:"+modifyTimerList.size());
     		
     			timerBusinessProcess(modifyTimerList);
     		
     			
     		
     	}
     	
     // 处理 params的逻辑
     		List<ParamBean> localParams = localServBean.getParams();
     		List<ParamBean> newParams = newServBean.getParams();
     		List<ParamBean> modifyParams = new ArrayList<ParamBean>();
     		for (int i = 0; i < localParams.size(); i++) {
     			for (int j = 0; j < newParams.size(); j++) {
     				if (/*StringUtils.isNotBlank(newParams.get(j).getValue())
     						&& */newParams.get(j).getKey()
     								.equals(localParams.get(i).getKey())) {
     					ParamBean param = new ParamBean();
     					if (StringUtils.isNotBlank(newParams.get(j).getValue()))
     					{
     						
     						param.setKey(newParams.get(j).getKey());
     						param.setValue(newParams.get(j).getValue());
     						
     					}
     					if(StringUtils.isBlank(newParams.get(j).getValue()))
     					{
     						param.setKey(localParams.get(i).getKey());
     						param.setValue(localParams.get(i).getValue());
     					}
     					
     					modifyParams.add(param);
     				}
     			}
     		}
     	
		
     	ServiceParamBean.StaticParams=modifyParams;
		localServBean.setTimers(localTimerList);
     	localServBean.setThriftBean(localTrift);
		localServBean.setParams(ServiceParamBean.StaticParams);
     	return localServBean;
	 }

	public  ServiceParamBean getLocalPolicyParam(String pathPolicyXml) throws FileNotFoundException {
		
			FileInputStream policyIn = new FileInputStream(pathPolicyXml);
			ServiceParamBean serviceBean=XmlUtil.xmlToObject(policyIn, ServiceParamBean.class);
			return serviceBean;
		
	}
	
	/**
	 * 本地服务策略文件(policy.xml)写入
	 * @param document xml文件文档树对象
	 * @return 
	 * @throws IOException 
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 * @throws DocumentException 
	 */
	public  synchronized void localServicePolicyFileWrite(ServiceParamBean localServBean)
			throws IOException, FileNotFoundException,
			UnsupportedEncodingException, DocumentException {
		
		FileOutputStream os = null;
		XMLWriter writer = null;
		String xmlStr=XmlUtil.Object2Xml(localServBean);
		Document document = DocumentHelper.parseText(xmlStr);
		try{
			os = new FileOutputStream(SystemConstants.PATH_POLICY_XML);
			OutputFormat outputFormat = OutputFormat.createPrettyPrint();  
			outputFormat.setEncoding("UTF-8");  
			writer = new XMLWriter(os, outputFormat);  
			writer.write(document);  
			writer.flush();
			logger.info("策略文件回写成功!");
		}finally{
			if(writer!=null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public void thriftBusinessProcess(ServiceParamBean localServBean)
	{
		
	}
	
	public  void timerBusinessProcess(List<TimerBean> modifyTimers) {
		//获取timers节点
		String jobName="";
		if(modifyTimers!=null){
			//List<TimerBean> timerBeanList=localServBean.getTimers();
			for(TimerBean timerBean:modifyTimers){
				try{
				 jobName = timerBean.getName();
				String cycle = timerBean.getCycle();
				String group = timerBean.getGroup();
				String trigger = timerBean.getTrigger();
				TriggerKey triggerKey = new TriggerKey(trigger, group);
				CronTrigger oldTrigger = QuartzConfig.getInstance().getTrigger(triggerKey);
				if(oldTrigger == null){
					logger.error("jobName=["+jobName+"];group=["+group+"]无原始触发器！");
					continue;
				}
				String oldCycle = oldTrigger.getCronExpression();
				
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
						.cronSchedule(cycle);
				CronTrigger newTrigger = oldTrigger.getTriggerBuilder()
						.withSchedule(scheduleBuilder).build();
				Date date = QuartzConfig.getInstance().rescheduleJob(triggerKey, newTrigger);
				if(date==null){
					logger.error("jobName=["+jobName+"];group=["+group+"];cycle=["+cycle+"]定时器restart失败！");
				}else{
					logger.info("jobName=["+jobName+"];group=["+group+"];dateTime=["+DateUtils.format(date)+"]定时器restart成功！");
				}
				
			}			
				catch(Exception e)
				{
					logger.error("配置"+jobName+"定时器失败!");
				}
			 
			}
	  
	}
	}
	/**
	 * 日志配置业务处理方法
	 * @param rootElement 服务策略xml文件根节点元素对象。
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static synchronized void logBusinessProcess(ServiceParamBean localServBean)
			throws FileNotFoundException, IOException {
		InputStream is = null;
		try{
			//获取logBean节点
			if(localServBean!=null){
				//日志级别
				String logLevel = localServBean.getLogBean().getLogLevel()+",FILE";
				//日志文件路径
				String logPath = localServBean.getLogBean().getLogPath();
				//获取log4j配置文件
				CommentedProperties cp = new CommentedProperties();
				is = new FileInputStream(SystemConstants.PATH_LOG4J_PROPERTIES);
				cp.load(is,"UTF-8");
				//默认日志级别
				String defaultLogLevel = cp.getProperty("log4j.rootLogger");
				String defaultLogPath = cp.getProperty("log4j.appender.FILE.File");
				if(!defaultLogLevel.equals(logLevel) || !defaultLogPath.equals(logPath)){
					Writer pw = new PrintWriter(new File(PATH_LOG4J_PROPERTIES));
					//设置新的日志级别
					cp.setProperty("log4j.rootLogger", logLevel);
					cp.setProperty("log4j.appender.FILE.File", logPath);
					cp.store(pw,true);
					pw.flush();
					pw.close();
				}
				
				//重新加载日志文件
				PropertyConfigurator.configure(PATH_LOG4J_PROPERTIES);
				logger.info("日志修改成功!");
			}
			
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
	}
	
	
	public String policyXML2String(String pathPolicyXml) throws IOException
	{
		FileInputStream policyIn = null;
		try {
			policyIn= new FileInputStream(pathPolicyXml);
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	     StringBuffer   out   =   new   StringBuffer(); 
	        byte[]   b   =   new   byte[4096]; 
	        for   (int   n;   (n   =   policyIn.read(b))   !=   -1;)   { 
	                out.append(new   String(b,   0,   n)); 
	        } 
	        return   out.toString(); 	
	}
	
	

	
}
