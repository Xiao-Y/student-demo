package org.billow.controller.activiti;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.repository.Deployment;
import org.billow.api.workflow.WorkFlowService;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

/**
 * 流程部署
 * 
 * @author liuyongtao
 * 
 * @date 2017年9月9日 下午12:34:13
 */
@Controller
@RequestMapping("/sysActDeploy")
public class SysActDeployController {

	@Autowired
	private WorkFlowService workFlowService;

	/**
	 * 查询流程部署列表
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param request
	 * @return
	 * 
	 * @date 2017年9月9日 下午12:36:07
	 */
	@RequestMapping("/queryDeployList")
	public ModelAndView queryDeployList(HttpServletRequest request) {
		PageInfo<Deployment> pages = workFlowService.queryDeployList();
		ModelAndView av = new ModelAndView();
		av.addObject("page", pages);
		av.setViewName(PagePathCst.BASEPATH_ACTIVITI_DEPLOY + "/actDeploy");
		return av;
	}

}
