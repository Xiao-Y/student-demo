package org.billow.utils;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;

public class PageHelper extends com.github.pagehelper.PageHelper {

	private static PageHelper pageHelper = new PageHelper();

	private PageHelper() {
	}

	/**
	 * 获取PageHelper实例
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月28日 下午10:46:40
	 */
	public static PageHelper getInstance() {
		return pageHelper;
	}

	/**
	 * 分页方法
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @date 2017年4月28日 下午12:46:31
	 */
	@SuppressWarnings("deprecation")
	public static void startPage() {
		HttpServletRequest request = RequestUtils.getRequest();
		Integer pageSize = RequestUtils.getPageSize(request);
		Integer targetPage = RequestUtils.getTargetPage(request);
		startPage(targetPage, pageSize);
	}

	/**
	 * 分页方法
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @date 2017年4月28日 下午12:46:31
	 */
	@SuppressWarnings("deprecation")
	public static void startPage(HttpServletRequest request) {
		Integer pageSize = RequestUtils.getPageSize(request);
		Integer targetPage = RequestUtils.getTargetPage(request);
		startPage(targetPage, pageSize);
	}

	/**
	 * 获取分页对象
	 * 
	 * @param count
	 *            总数量
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月28日 下午10:50:05
	 */
	public static <T> PageInfo<T> getPageInfo(long count) {
		int pageSize = RequestUtils.getPageSize();
		int pageNum = RequestUtils.getTargetPage();
		int firstResult = startResult(pageNum, pageSize);
		int pages = pages(count, pageSize);
		PageInfo<T> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(pageNum);
		pageInfo.setPages(pages);
		pageInfo.setFirstPage(firstResult);
		pageInfo.setTotal(count);
		pageInfo.setPageSize(pageSize);
		return pageInfo;
	}

	/**
	 * 获取总页数
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param count
	 *            总数量
	 * @param pageSize
	 *            页面大小
	 * @return
	 * 
	 * @date 2017年4月29日 下午5:36:31
	 */
	private static int pages(long count, int pageSize) {
		return (int) ((count % pageSize) == 0 ? (count / pageSize) : ((count / pageSize) + 1));
	}

	/**
	 * 数据起始位置
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param targetPage
	 *            起始页码
	 * @param pageSize
	 *            页面大小
	 * @return
	 * 
	 * @date 2017年4月29日 下午5:45:08
	 */
	private static int startResult(int targetPage, int pageSize) {
		return (targetPage - 1) * pageSize;
	}
}
