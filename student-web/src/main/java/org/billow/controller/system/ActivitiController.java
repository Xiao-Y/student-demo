package org.billow.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.Model;
import org.apache.log4j.Logger;
import org.billow.api.system.ActRepositoryService;
import org.billow.common.constant.PagePath;
import org.billow.model.custom.DiagramDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

/**
 * 流程管理
 * 
 * @author liuyongtao
 * 
 * @date 2017年4月29日 下午4:38:16
 */
@Controller
@RequestMapping("/act")
public class ActivitiController {

	private static final Logger logger = Logger.getLogger(ActivitiController.class);

	@Autowired
	private ActRepositoryService actRepositoryService;

	/**
	 * 查询流程模板
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param request
	 * @return
	 * 
	 * @date 2017年4月29日 下午4:43:38
	 */
	@RequestMapping("/findActModel")
	public ModelAndView findActModel(HttpServletRequest request) {
		ModelAndView av = new ModelAndView();
		PageInfo<Model> pages = actRepositoryService.getModel();
		av.addObject("pages", pages);
		av.setViewName(PagePath.BASEPATH_SYSTEM + "/actModel");
		return av;
	}

	@RequestMapping("/createModel")
	public String createModel() {
		return PagePath.BASEPATH_SYSTEM + "/actAddModel";
	}

	/**
	 * 进入流程设计器
	 * 
	 * @param name
	 *            model的名称
	 * @param key
	 *            model的key
	 * @param description
	 *            简介
	 * @param request
	 * @param response
	 * @author XiaoY
	 * @date: 2017年4月22日 上午11:16:49
	 */
	@RequestMapping("/diagram")
	public void create(DiagramDto diagram, HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println(diagram);
			//Model modelData = actRepositoryService.createModel(diagram);
			//response.sendRedirect(request.getContextPath() + "/process-editor/modeler.html?modelId=" + modelData.getId());
		} catch (Exception e) {
			logger.error("创建模型失败：", e);
			e.printStackTrace();
		}
	}
}
