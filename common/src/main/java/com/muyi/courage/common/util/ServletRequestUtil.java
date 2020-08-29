package com.muyi.courage.common.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ServletRequestUtil {

    public static HttpServletRequest getHttpRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

	public static String getUsername(){
		return getHttpRequest().getHeader("username");
	}

    public static HttpSession getSession() {
        return getHttpRequest().getSession();
    }

    public static ServletContext getServletContext() {
        return getHttpRequest().getSession().getServletContext();
    }

    public static String getIp(){
		String ip = getHttpRequest().getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			int index = ip.indexOf(",");
			return index != -1 ? ip.substring(0, index) : ip;
		} else {
			ip = getHttpRequest().getHeader("X-Real-IP");
			return StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip) ? ip : getHttpRequest().getRemoteAddr();
		}

	}
}
