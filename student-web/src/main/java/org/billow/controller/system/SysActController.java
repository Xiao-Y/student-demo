package org.billow.controller.system;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.Model;
import org.apache.log4j.Logger;
import org.billow.api.system.ActRepositoryService;
import org.billow.model.custom.DiagramDto;
import org.billow.model.custom.JsonResult;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("/sysAct")
public class SysActController {

	private static final Logger logger = Logger.getLogger(SysActController.class);

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
		av.setViewName(PagePathCst.BASEPATH_SYSTEM + "/actModel");
		return av;
	}

	@RequestMapping("/createModel")
	public String createModel() {
		return PagePathCst.BASEPATH_SYSTEM + "/actAddModel";
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
	@ResponseBody
	@RequestMapping("/diagram")
	public JsonResult create(DiagramDto diagram, HttpServletRequest request, HttpServletResponse response) {
		JsonResult json = new JsonResult();
		try {
			System.out.println(diagram);
			Model modelData = actRepositoryService.createModel(diagram);
			String url = request.getContextPath() + "/process-editor/modeler.html?modelId=" + modelData.getId();
			// response.sendRedirect(request.getContextPath() +
			// "/process-editor/modeler.html?modelId=" + modelData.getId());
			json.setMessage(MessageTipsCst.SAVE_SUCCESS);
			json.setSuccess(true);
			json.setRoot(url);
		} catch (Exception e) {
			json.setMessage("创建模型失败!");
			json.setSuccess(false);
			logger.error("创建模型失败：", e);
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 查看模板流程图
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param modelId
	 *            模板id
	 * @param request
	 * @param response
	 * 
	 * @date 2017年5月5日 上午9:36:53
	 */
	@RequestMapping("/viewPic/{modelId}")
	public void viewPic(@PathVariable("modelId") String modelId, HttpServletRequest request, HttpServletResponse response) {
		byte[] data = actRepositoryService.viewPic(modelId);
		try {
			if (data == null) {
				response.setContentType("text/html;charset=UTF-8");
				response.getOutputStream().write("暂时没有图片".getBytes("UTF-8"));
			} else {
				response.getOutputStream().write(data);
			}
			response.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	@ResponseBody
	@RequestMapping("/deleteModel/{modelId}")
	public JsonResult deleteModel(@PathVariable("modelId") String modelId) {
		JsonResult json = new JsonResult();
		try {
			actRepositoryService.deleteModel(modelId);
			json.setSuccess(true);
			json.setMessage(MessageTipsCst.DELETE_SUCCESS);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMessage(MessageTipsCst.DELETE_FAILURE);
			e.printStackTrace();
			logger.error(e);
		}
		return json;
	}
}
