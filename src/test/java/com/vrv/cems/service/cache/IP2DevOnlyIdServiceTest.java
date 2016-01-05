package com.vrv.cems.service.cache;



import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.vrv.cems.service.base.bean.cache.DeviceCache;
import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.base.interfaces.ICacheService;
import com.vrv.cems.service.cache.service.IP2DevOnlyIdService;
import com.vrv.cems.service.cache.service.impl.DeviceServiceImpl;
import com.vrv.cems.service.cache.service.impl.IP2DevOnlyIdServiceImpl;
import com.vrv.cems.service.cache.util.RedisUtil;

public class IP2DevOnlyIdServiceTest {
	private Logger log = Logger.getLogger(IP2DevOnlyIdServiceTest.class);
	private IP2DevOnlyIdService ip2DevOnlyIdService;
	private Result result;
	private String ip;
	private String  devOnlyId;
	private List<Result> resultList = new ArrayList<Result>();
/*	*//**
	 * 初始化一些该单元测试类需要的数据
	 *//*
	@Before
	public void init(){
		
		ip2DevOnlyIdService = new IP2DevOnlyIdServiceImpl();
		result = new Result();
		ip="192.168.99.112";
		devOnlyId="22222222222222222222222222222222";
		
	}
	
	*//**
	 * 测试IP与devOnlyId对应关系保存方法
	 *//*
	@Test
	public void testSaveIP2DevOnlyId()
	{
		result= ip2DevOnlyIdService.saveIP2DevOnlyId(ICacheService.SERVICE_CODE,ICacheService.SAVEIP2DEVONLYID,ip,devOnlyId);
		log.info(result.getCode()+":"+result.getInfo());
		
	}
	
	*//**
	 * 测试IP与devOnlyId对应关系更新方法
	 *//*
	@Test
	public void testUpdateIP2DevOnlyId()
	{
		//先查询
		String olddevOnlyId=ip2DevOnlyIdService.queryIP2DevOnlyId(ICacheService.SERVICE_CODE,ICacheService.SAVEIP2DEVONLYID,ip);
		log.info("olddevOnlyId老的为"+olddevOnlyId);
		String newdevOnlyId="132456781324567123456789";
		//更新
		result= ip2DevOnlyIdService.updateIP2DevOnlyId(ICacheService.SERVICE_CODE,ICacheService.SAVEIP2DEVONLYID,ip,newdevOnlyId);
		
		log.info("olddevOnlyId新的为"+newdevOnlyId);

	}
	
	*//**
	 * 测试IP与devOnlyId对应关系更新方法
	 *//*
	@Test
	public void testDeleteIP2DevOnlyId()
	{
	
		 result=ip2DevOnlyIdService.deleteIP2DevOnlyId(ICacheService.SERVICE_CODE,ICacheService.SAVEIP2DEVONLYID,ip);

		log.info(result.getCode()+":"+result.getInfo());

	}*/
	
	
}
