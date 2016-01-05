package com.vrv.test.db; 
/** 
 *   <B>说       明</B>:连接关系枚举类。
 *
 * @author  作  者  名：陈 明<br/>
 *		    E-mail ：chenming@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年8月19日 下午3:04:44 
 */
public enum Link {
	EQUAL("="),
	NOT_EQUAL("<>"),
	LESS_THAN("<"),
	GRATE_THAN(">"),
	LESS_EQUAL("<="),
	GRATE_EQUAL(">="),
	IN(" IN "),
	LIKE(" LIKE "),
	NOT_IN(" NOT IN ");
	
	private String value="=";
	
	private Link(String value){
		this.value=value;
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
	
	public String value(){
		return this.value;
	}
}
 