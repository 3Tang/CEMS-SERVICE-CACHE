package com.vrv.cems.service.cache.service;

import org.apache.log4j.Logger;

import com.vrv.cems.service.base.bean.cache.Result;
import com.vrv.cems.service.base.bean.cache.SensitiveCache;

/**
 * <B>说 明</B>:敏感规则库信息Service接口
 * 
 * @author 作 者 名：glw<br/>
 *         E-mail ：linwu_gao@163.com
 * 
 * @version 版 本 号：V1.0.<br/>
 *          创建时间：2015年10月28日 下午8:33:29
 */
public interface SensitiveService {

	public Logger log = Logger.getLogger(SensitiveService.class);
	
	/**
	 * 保存敏感规则库信息缓存数据
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1701
	 * @param sensitiveCache敏感规则库信息缓存数据
	 * @return
	 *		Result(0,保存敏感规则库信息缓存数据成功.);
	 *		Result(1,保存敏感规则库信息数据失败[name为空]!);
	 */
	Result saveSensitive(String maxCode, String minCode,
			SensitiveCache sensitiveCache);
	
	/**
	 * 更新敏感规则库信息缓存数据
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1702
	 * @param sensitiveCache敏感规则库信息缓存数据
	 * @return
	 *		Result(0,更新敏感规则库信息缓存数据成功.);
	 *		Result(1,更新敏感规则库信息数据失败[name为空]!);
	 */
	Result updateSensitive(String maxCode, String minCode, SensitiveCache sensitiveCache);
	
	/**
	 * 查询敏感规则库信息缓存数据
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1703
	 * @param fileName		敏感规则库文件名
	 * @return
	 *		SensitiveCache	敏感规则库信息缓存数据
	 */
	SensitiveCache querySensitive(String maxCode, String minCode, String fileName);
	
	/**
	 * 查询敏感规则库信息缓存数据，根据name查询crc
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1704
	 * @param fileName		敏感规则库文件名
	 * @param fieldKey		需要查询的字段键值(eg. crc,path...)
	 * @return
	 *		fieldValue 		需要查询的字段对应的值
	 */
	String querySensitiveByField(String maxCode, String minCode, String fileName,
			String fieldKey);
	
	/**
	 * 删除敏感规则库信息缓存数据
	 * @param maxCode		缓存服务号：00FF0700
	 * @param minCode		缓存服务功能号：1705
	 * @param fileName		敏感规则库文件名
	 * @return
	 *		Result(0,删除敏感规则库信息缓存数据成功.);
	 *		Result(1,删除敏感规则库信息数据失败[name为空]!);
	 */
	Result deleteSensitive(String maxCode, String minCode, String fileName);
}
