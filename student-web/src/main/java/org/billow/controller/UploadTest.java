package org.billow.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/uploadTest")
public class UploadTest {

	@RequestMapping("/uploadIndex")
	public String uploadIndex() {
		return "fileUpload/upload";
	}

	@ResponseBody
	@RequestMapping("/upload")
	public Map<String, Object> upload(@RequestParam(value = "files", required = false) MultipartFile[] files, HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("upload");
		Map<String, Object> data = new HashMap<>();
		for (MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			data.put("files", fileName);
			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 保存
			try {
				file.transferTo(targetFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		data.put("loaded", 23);
		data.put("total", 100);
		return data;
	}
}
