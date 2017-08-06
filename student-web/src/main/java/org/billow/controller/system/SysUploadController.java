package org.billow.controller.system;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.billow.utils.constant.PagePathCst;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传、下载
 * 
 * @author XiaoY
 * @date: 2017年8月6日 下午3:22:26
 */
@Controller
@RequestMapping("/sysUploadController")
public class SysUploadController {

	@RequestMapping("/uploadIndex")
	public String uploadIndex() {
		return PagePathCst.BASEPATH_SYSTEM + "uploadIndex";
	}

	@RequestMapping(value = "/upload")
	public void upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println("开始");
		// 保存
		try {
			String path = request.getSession().getServletContext().getRealPath("upload");
			String fileName = file.getOriginalFilename();
			System.out.println(path);
			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			file.transferTo(targetFile);
			String fileSize = "0B";
			BigDecimal temp = new BigDecimal(1024);
			BigDecimal size = new BigDecimal(file.getSize());
			if (size.compareTo(temp) >= 0) {
				size = size.divide(temp, 2, BigDecimal.ROUND_HALF_UP);
				fileSize = size + "KB";
			} else {
				fileSize = size + "B";
			}
			if (size.compareTo(temp) >= 0) {
				size = size.divide(temp, 2, BigDecimal.ROUND_HALF_UP);
				fileSize = size + "M";
			}
			if (size.compareTo(temp) >= 0) {
				size = size.divide(temp, 2, BigDecimal.ROUND_HALF_UP);
				fileSize = size + "G";
			}
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			String newFilePath = "333";
			String attachmentId = "4444";
			response.getWriter()
					.write("success:" + "," + newFilePath + "," + attachmentId + "," + fileName + "," + fileSize);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("error:上传文件失败!");
		}
	}
}
