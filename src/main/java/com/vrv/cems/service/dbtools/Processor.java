package com.vrv.cems.service.dbtools; 

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.RowProcessor;

/** 
 *   <B>说       明</B>:
 *
 * @author  作  者  名：陈 明<br/>
 *		    E-mail ：chenming@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月19日 下午4:59:08 
 */
public class Processor implements RowProcessor{

	@Override
	public Object[] toArray(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T toBean(ResultSet rs, Class<T> type) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> toBeanList(ResultSet rs, Class<T> type)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> toMap(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
 