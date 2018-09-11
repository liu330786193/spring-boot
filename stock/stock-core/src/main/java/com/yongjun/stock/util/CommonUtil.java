package com.yongjun.stock.util;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

public class CommonUtil {
	
	/**
	 * 判断变量是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int getIntValue(Object o){
		if (o == null)
			return 0;
		try{
			return Integer.parseInt(o.toString());
		}catch(Exception e){
			return 0;
		}
	}
	
	public static int getIntValue(BigInteger o){
		if (o == null)
			return 0;
		try{
			return Integer.parseInt(o.toString());
		}catch(Exception e){
			return 0;
		}
	}
	
	public static int getIntValue(String o){
		if (o == null)
			return 0;
		try{
			if (o.indexOf(".") >= 0)
				return (int) (Double.parseDouble(o));
			else
				return Integer.parseInt(o);
		}catch(Exception e){
			return 0;
		}
	}
	
	public static double getDoubleValue(String o){
		if (o == null)
			return 0;
		try{
			return Double.parseDouble(o);
		}catch(Exception e){
			return 0;
		}
	}
	
	public static double getDoubleValue(Object o){
		if (o == null)
			return 0;
		try{
			return Double.parseDouble(o.toString());
		}catch(Exception e){
			return 0;
		}
	}
	
	public static long getLongValue(String o){
		if (o == null)
			return 0;
		try{
			return Long.parseLong(o);
		}catch(Exception e){
			return 0;
		}
	}
	
	public static long getLongValue(Object o){
		if (o == null)
			return 0;
		try{
			return Long.parseLong(o.toString());
		}catch(Exception e){
			return 0;
		}
	}
	
	/**
	 * 返回用户的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String toIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
