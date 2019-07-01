package com.zjzc.manage.utils.others;

import javax.servlet.http.HttpServletRequest;

/**
 * ua工具类
 * @author qinzihao
 */
public class UserAgentUtils {
	/** 
	* 获取用户真实IP地址，不使用request.getRemoteAddr();<br>
	* 因为有可能用户使用了代理软件方式避免真实IP地址, <br>
	* 如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值<br> 
	* 取X-Forwarded-For中第一个非unknown的有效IP字符串。 <br>
	* 
	* 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 192.168.1.100<br>
	* 用户真实IP为： 192.168.1.110<br> 
	* 
	* @param request 
	* @return 
	*/ 
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
	  }
}
