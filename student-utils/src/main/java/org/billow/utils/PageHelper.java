package org.billow.utils;

import javax.servlet.http.HttpServletRequest;

public class PageHelper extends com.github.pagehelper.PageHelper {

	/**
	 * 分布方法
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @date 2017年4月28日 下午12:46:31
	 */
	public static void startPage() {
		HttpServletRequest request = RequestUtils.getRequest();
		Integer pageSize = RequestUtils.getPageSize(request);
		Integer targetPage = RequestUtils.getTargetPage(request);
		startPage(pageSize, targetPage);
	}

	/**
	 * 分布方法
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @date 2017年4月28日 下午12:46:31
	 */
	public static void startPage(HttpServletRequest request) {
		Integer pageSize = RequestUtils.getPageSize(request);
		Integer targetPage = RequestUtils.getTargetPage(request);
		startPage(pageSize, targetPage);
	}
}
