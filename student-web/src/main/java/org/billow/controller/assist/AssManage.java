package org.billow.controller.assist;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 辅助管理
 * 
 * @author liuyongtao
 * 
 * @date 2017年5月12日 上午11:33:33
 */
@Controller
@RequestMapping("/assManage")
public class AssManage {

	private static final Logger logger = Logger.getLogger(AssManage.class);

	/**
	 * 查看依赖图
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @date 2017年5月12日 上午11:34:42
	 */
	@RequestMapping("/viewDependence")
	public void viewDependence(HttpServletRequest request, HttpServletResponse response) {
		ServletContext servletContext = request.getSession().getServletContext();
		String realPath = servletContext.getRealPath("/");
		File file = new File(realPath + "/MoKuaiYiLai.png");
		FileInputStream fis = null;
		OutputStream os = null;
		try {
			fis = new FileInputStream(file);
			os = response.getOutputStream();
			int count = 0;
			byte[] buffer = new byte[1024 * 8];
			while ((count = fis.read(buffer)) != -1) {
				os.write(buffer, 0, count);
				os.flush();
			}
			fis.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
}