package org.billow.common.constant;

/**
 * 自动任务静态变量
 * 
 * @author liuyongtao
 * 
 * @date 2017年5月8日 上午10:33:47
 */
public class QuartzCst {
	/**
	 * 是否在远行中-是
	 */
	public static final String STATUS_RUNNING = "1";
	/**
	 * 是否在远行中-否
	 */
	public static final String STATUS_NOT_RUNNING = "0";
	/**
	 * 有状态的（相当于单线程）
	 */
	public static final String CONCURRENT_IS = "1";
	/**
	 * 无状态的（相当于多线程）
	 */
	public static final String CONCURRENT_NOT = "0";
}
