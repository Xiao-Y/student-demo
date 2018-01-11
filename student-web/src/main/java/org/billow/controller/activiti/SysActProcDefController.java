package org.billow.controller.activiti;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.billow.api.workflow.WorkFlowService;
import org.billow.model.custom.JsonResult;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

/**
 * 流程定义相关
 * 
 * @author liuyongtao
 * 
 * @date 2017年9月9日 上午10:32:50
 */
@Controller
@RequestMapping("/sysActProcDef")
public class SysActProcDefController {

	@Autowired
	private WorkFlowService workFlowService;

	@Autowired(required = false)
	private RepositoryService repositoryService;

	/**
	 * 查询流程定义列表
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @date 2017年9月9日 上午10:34:58
	 */
	@RequestMapping("/queryProcDefList")
	public ModelAndView queryProcDefList(HttpServletRequest request) {
		PageInfo<ProcessDefinition> pages = workFlowService.queryProcDefList();
		ModelAndView av = new ModelAndView();
		av.addObject("page", pages);
		av.setViewName(PagePathCst.BASEPATH_ACTIVITI_PROC_DEF + "/actProcDef");
		return av;
	}

	/**
	 * 挂起流程定义
	 * @return
	 */
	@RequestMapping("/suspendProcDef")
	public JsonResult suspendProcDef(){
		//repositoryService.suspendProcessDefinitionById();

		return null;
	}
}
