package com.hk.common.util;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.hk.auth.domain.User;
import com.hk.common.domain.SysConst;

/**
 * 客户端工具类，主要记录一些客户端web方面有关的通用方法
 *
 * @author zhenglian
 * @data 2015年10月18日 上午11:20:00
 */
public class ClientUtil {

	public static String getLinuxIP() throws SocketException {
		// 根据网卡取本机配置的IP
		Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
		InetAddress ipAddress = null;
		String ip = "";
		while (netInterfaces.hasMoreElements()) {
			NetworkInterface ni = (NetworkInterface) netInterfaces
					.nextElement();
			if (ni.getName().equals("eth0")) {
				continue;
			} else {
				Enumeration<?> e2 = ni.getInetAddresses();
				while (e2.hasMoreElements()) {
					ipAddress = (InetAddress) e2.nextElement();
					if (ipAddress instanceof Inet6Address)
						continue;
					ip = ipAddress.getHostAddress();
//					System.out.println("getLinuxIp:" + ip);
				}
				break;
			}
		}
		return ip;
	}
	
	public static String getWindowsIP() throws Exception {
		String ip = null;
		InetAddress address = InetAddress.getLocalHost();
		ip = address.getHostAddress(); // 获取ip地址
		
		return ip;
	}
	
	public static boolean isWindowsOS() {
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") > -1) {
			isWindowsOS = true;
		}
		return isWindowsOS;
	}
	
	public static String getLocalip() {
		String localIP = null;
		try {
			boolean isWindows = isWindowsOS(); // 判断是否是windows系统
			if (isWindows) {
				localIP = getWindowsIP();
			} else { // 如果是Linux系统
				localIP = getLinuxIP();
			}
		} catch (Exception e) {
		}
		return localIP;
	}

	
	/**
	 * 获取来自客户端的请求Ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientIp() {
		HttpServletRequest request = getRequest();
		if (request == null) {
			return "";
		}
		
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
		// 如果用localhost访问，得到的IP是0:0:0:0:0:0:0:1
		if (ip != null
				&& (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1") || ip
						.equalsIgnoreCase("127.0.0.1"))) {
			ip = getLocalip();
		}
		return ip;
	}

	/**
	 * 获取HttpServletRequest对象
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
			return request;
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * 获取登录的用户
	 * @return
	 */
	public static User getLoginUser() {
		return (User) getRequest().getSession().getAttribute(SysConst.LOGIN_USER);
	}

}
