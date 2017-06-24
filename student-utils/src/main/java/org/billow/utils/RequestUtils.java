package org.billow.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * request请求的工具类
 * 
 * @author XiaoY
 * @date: 2015年11月18日 下午10:14:35
 */
public class RequestUtils {

	/**
	 * 获取当前页
	 * 
	 * @param request
	 * @return
	 * @date 2015年11月18日 下午10:11:23
	 */
	@Deprecated
	public static Integer getTargetPage(HttpServletRequest request) {
		String targetPageStr = request.getParameter("pageNo");
		Integer targetPage;
		try {
			targetPage = Integer.parseInt(targetPageStr);
		} catch (Exception e) {
			targetPage = 1;
		}
		return targetPage;
	}

	/**
	 * 获取当前页大小
	 * 
	 * @param request
	 * @return
	 * @date 2015年11月18日 下午10:11:23
	 */
	@Deprecated
	public static Integer getPageSize(HttpServletRequest request) {
		String sizeStr = request.getParameter("pageSize");
		Integer size;
		try {
			size = Integer.parseInt(sizeStr);
		} catch (Exception e) {
			size = 8;
		}
		return size;
	}

	/**
	 * 获取当前页
	 * 
	 * @param request
	 * @return
	 * @date 2015年11月18日 下午10:11:23
	 */
	public static Integer getTargetPage() {
		String targetPageStr = getRequest().getParameter("pageNo");
		Integer targetPage;
		try {
			targetPage = Integer.parseInt(targetPageStr);
		} catch (Exception e) {
			targetPage = 1;
		}
		return targetPage;
	}

	/**
	 * 获取当前页大小
	 * 
	 * @param request
	 * @return
	 * @date 2015年11月18日 下午10:11:23
	 */
	public static Integer getPageSize() {
		String sizeStr = getRequest().getParameter("pageSize");
		Integer size;
		try {
			size = Integer.parseInt(sizeStr);
		} catch (Exception e) {
			size = 8;
		}
		return size;
	}

	/**
	 * 获取request
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年1月22日 下午3:13:54
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;
	}

	/**
	 * 获取HttpSession
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年1月22日 下午3:13:54
	 */
	public static HttpSession getSession(HttpServletRequest request) {
		return request.getSession();
	}

	/**
	 * 用于从request中获取参数值
	 * 
	 * @param request
	 *            当前reuqest
	 * @param name
	 *            参数的名字
	 * @param defaultValue
	 * @return
	 * 
	 * @date 2015年8月11日下午5:30:39
	 */
	public static String getStringParameter(HttpServletRequest request, String name, String defaultValue) {
		String s = request.getParameter(name);
		try {
			if (StringUtils.isEmpty(s)) {
				return defaultValue;
			}
			return s.trim();
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 用于从request中获取参数值
	 * 
	 * @param request
	 *            当前reuqest
	 * @param name
	 *            参数的名字
	 * @return
	 * 
	 * @date 2015年8月11日下午5:30:39
	 */
	public static String getStringParameter(HttpServletRequest request, String name) {
		String s = request.getParameter(name);
		try {
			if (StringUtils.isEmpty(s)) {
				return "";
			}
			return s.trim();
		} catch (Exception e) {
			return "";
		}
	}
}
