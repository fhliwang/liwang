/** 
 * <pre>项目名称:shop-admin3 
 * 文件名称:WebContext.java 
 * 包名:com.fh.shop.admin.util 
 * 创建日期:2018年10月17日下午1:54:43 
 * Copyright (c) 2018, lw951119@163.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.api.util;

import javax.servlet.http.HttpServletRequest;

/** 
 * <pre>项目名称：shop-admin3    
 * 类名称：WebContext    
 * 类描述：    
 * 创建人：李旺   lw951119@163.com 
 * 创建时间：2018年10月17日 下午1:54:43    
 * 修改人：李旺    lw951119@163.com
 * 修改时间：2018年10月17日 下午1:54:43    
 * 修改备注
 * @version </pre>    
 */
public class WebContext {
	private static ThreadLocal<HttpServletRequest> threadLocal = new ThreadLocal<HttpServletRequest>();
	
	
	/**
	 * set方法
	 */
	public static void setRequest(HttpServletRequest request){
		threadLocal.set(request);
	}
	/**
	 * get方法
	 */
	
	public static HttpServletRequest getRequest(){
		return threadLocal.get();
	}
	/**
	 * remove方法
	 */
	public static void removeRequest(){
		threadLocal.remove();
	}
}
