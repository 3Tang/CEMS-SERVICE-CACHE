package com.vrv.cems.service.dbtools; 

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 *   <B>说       明</B>:忽略列标识类。
 *
 * @author  作  者  名：陈 明<br/>
 *		    E-mail ：chenming@vrvmail.com.cn
 
 * @version 版   本  号：V1.0.<br/>
 *          创建时间：2014年11月3日 下午12:04:55 
 */
@Target({TYPE, FIELD, METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreColumn {

}
 