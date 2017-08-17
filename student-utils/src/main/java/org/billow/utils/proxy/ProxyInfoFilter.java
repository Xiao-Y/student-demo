package org.billow.utils.proxy;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * 获取服务器的真实IP
 * 
 * @author liuyongtao
 * 
 * @date 2017年8月17日 下午5:38:11
 */
public class ProxyInfoFilter implements Filter {

	@Override
	public void doFilter(ServletRequest sRequest, ServletResponse sRespose, FilterChain chain) {
		HttpServletRequest request = (HttpServletRequest) sRequest;
		String localAddr = request.getLocalAddr();
		int port = request.getLocalPort();
		String host = request.getHeader("Host");
		String proxyAddr = "";
		if (StringUtils.isEmpty(host)) {
			proxyAddr = localAddr;
		} else if (host.startsWith("localhost")) {
			proxyAddr = host.split(":")[0];
		} else {
			proxyAddr = localAddr;
		}
		ProxyInfoContext proxyInfoContext = new ProxyInfoContext(localAddr, port, proxyAddr);
		try {
			chain.doFilter(sRequest, sRespose);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		} finally {
			proxyInfoContext.destroy();
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}
}
