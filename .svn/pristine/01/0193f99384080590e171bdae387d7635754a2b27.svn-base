package com.vrv.cems.service.cache.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Set;
import java.util.zip.CRC32;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sys.common.util.UUIDUtils;
import com.sys.common.util.compress.CompressFormat;
import com.sys.common.util.compress.Compressor;
import com.sys.common.util.json.JsonUtils;
import com.vrv.cems.service.base.util.BaseEncryptDecryptUtils;
import com.vrv.cems.service.cache.bean.OutParam;



/** 
 *   <B>说       明</B>:常用工具类
 *
 * @author  作  者  名：代成<br/>
 *		    E-mail ：daicheng@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.20140815<br/>
 *          创建时间：2014年8月15日 17:01:41 
 */
public class CommonUtil {
	private static Log logger = LogFactory.getLog(CommonUtil.class);
	
	public final static int MAX_BUF = 1024 * 8;

	static String key = UUIDUtils.get32UUID();
	static int flag = BaseEncryptDecryptUtils.rondom();
	/**
	*  set类型转换成String[]
	* @param Set<String> set 
	* @return String[]  result
	* @date 2014-08-15 17:02:58
	*/ 
	public static String[] SetToStringArray(Set<String> set) {
		String[] result = set.toArray(new String[set.size()]);
		return result;
	}
	
	   /**
     * byteBufferToUTF8 byteBuffer转换成UTF8
     * @param name
     * @param @return 设定文件
     * @return String DOM对象
     * @Exception 异常对象
     * @since CodingExample　Ver(编码范例查看) 1.1
     */
    //把bytebuffer里的格式转为utf-8?
    public static ByteBuffer byteBufferToUTF8(ByteBuffer buffer) {
        CharBuffer decode = null;
        try {
            Charset charset = Charset.forName("UTF-8");
            CharsetDecoder charsetDecoder = charset.newDecoder();
            decode = charsetDecoder.decode(buffer.asReadOnlyBuffer());
        } catch (CharacterCodingException e) {
            logger.error("ByteBuffer转换为字符串时出错！", e);
            e.printStackTrace();
            return null;
        }
        return ByteBuffer.wrap(decode.toString().getBytes());
    }
    
    /**
     * 常规输出对象转换成为ByteBuffer
     * @param o
     * @return
     * @throws UnsupportedEncodingException
     */
    public static ByteBuffer outParam2Json(OutParam o) throws UnsupportedEncodingException {
        JsonConfig config = new JsonConfig();
        config.setJsonPropertyFilter(new PropertyFilter() {
            
            public boolean apply(Object source, String name, Object value) {
                return value == null;
            }
        });
        String object2Json = JsonUtils.object2Json(o, config);
        return ByteBuffer.wrap(object2Json.getBytes("UTF-8"));
    }

    
	public static String tsParams2String(String checkCode, boolean isZip,
			ByteBuffer data, boolean isEncrypt, String key, int flag) {
		// 先进行crc校验 checkCode data
				//解压缩 isZip
				//解密 isEncrypt key flag
		String dataStr="";
		logger.info("开始校验输入信息！");
		try {
			 if (!byteCrc(data.array()).equals(checkCode)) {
				 logger.info("CRC校验失败！");
			 return null;
			 }
			 logger.info("CRC校验成功！");
			 
			 if (isZip) 
			 {
				 logger.info("数据解压！");
				 data = Compressor.getInstance(CompressFormat.ZIP).decompress(data);	 	
			 } 
			  if(isEncrypt) 
			   {
				 logger.info("开始服务之前DES解密！");
				 logger.info("在线服务获取的key为"+key);
				 logger.info("在线服务获取的flag为"+flag);
				 //msgArray = ProcessUtil.decryptMsg(SystemConfig.SERVER_COMMUNICATE_ARITHMETIC_TYPE,key, upZipData);
				 data = BaseEncryptDecryptUtils.decrypt(key, flag, data);
				 
				 }
			  dataStr = byteBufferToString(data);
			logger.info("输入的data为：" + dataStr);
			return dataStr;			
			 //return false;
		} catch (Exception e) {
			logger.error("校验输入信息出错！" + e.getMessage());
			return null;
		}
		
	}
	
	
	/**
	 * ByteBuffer算crc
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static String byteBufferCrc(ByteBuffer data) throws IOException {
		return byteCrc(data.array());
	}
	
	/**
	 * byteCrc 算byte的crc
	 * 
	 * @param name
	 * @param @return 设定文件
	 * @return String DOM对象
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public static String byteCrc(byte[] b) throws IOException {
		InputStream is = new ByteArrayInputStream(b);
		byte by[] = new byte[MAX_BUF];
		CRC32 c = new CRC32();
		int len = 0;
		while ((len = is.read(by, 0, MAX_BUF)) != -1)
			c.update(by, 0, len);
		long result = c.getValue();
		is.close();
		return Long.toHexString(result);
	}
	
	
	public static String byteBufferToString(ByteBuffer buffer){
		CharBuffer decode = null;
		Charset charset = Charset.forName("UTF-8");
		CharsetDecoder charsetDecoder = charset.newDecoder();
		try {
			decode = charsetDecoder.decode(buffer.asReadOnlyBuffer());
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
		return decode.toString();
	}
	

}
