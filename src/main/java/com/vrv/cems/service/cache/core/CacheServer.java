package com.vrv.cems.service.cache.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TServerSocket;

import com.sys.common.util.CommentedProperties;
import com.vrv.cems.service.base.interfaces.CacheService;
import com.vrv.cems.service.cache.action.ICacheServiceAction;
/** 
 *   <B>说       明</B>: 缓存服务端CacheServer
 *
 * @author  作  者  名：代成<br/>
 *		    E-mail ：daicheng@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.20140815<br/>
 *          创建时间：2014年8月15日 17:01:41 
 */
public class CacheServer implements Runnable {
	protected Log log = LogFactory.getLog(this.getClass().getName());
	/**
	 * 策略分发服务端口
	 */
	private int port;
	/**
	 * Thrift selector线程数  
	 */
	private String selectorThreads;
	
	/**
	 * Thrift 工作线程数
	 */
	private String  workerThreads;
	/**
	 * 任务执行线程池服务
	 */
	private final ExecutorService exec;
	
	public CacheServer(ExecutorService exec,int port,String selectorThreads,String  workerThreads) {
		this.exec = exec;
		this.port = port;
		this.selectorThreads = selectorThreads;
		this.workerThreads = workerThreads;
	}
/*	private void getServerPort() {
		InputStream in = null;
		try {
			//读取配置文件中集群IP和端口
			pros = new Properties();
			//in = this.getClass().getResourceAsStream("/redis.properties");
			File file = new File(prop.getPropertyFromCemsDat("path.config.properties"));
			in = new FileInputStream(file);
			pros.load(in);
			server_port = Integer.valueOf(pros.getProperty("service.port"));
			log.info("CacheService port="+server_port+"....");
		} catch (Exception ex) {
			log.error("没有找到redis配置文件", ex);
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}*/
	public void run() {
		try {
			log.info("Cache Server start ....");
			TProcessor tprocessor = new CacheService.Processor<CacheService.Iface>(new ICacheServiceAction());
			 TServerSocket serverTransport = new TServerSocket(port);
			 TThreadPoolServer.Args ttpsArgs = new TThreadPoolServer.Args(
			 serverTransport);
			 ttpsArgs.processor(tprocessor);
			 ttpsArgs.protocolFactory(new TBinaryProtocol.Factory());
			 ttpsArgs.transportFactory(new TFramedTransport.Factory());

			// 线程池服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求
			 TServer server = new TThreadPoolServer(ttpsArgs);
			 server.serve();
		} catch (Exception e) {
			log.info("Cache Server start error!!!");
			e.printStackTrace();
		}
	}

}