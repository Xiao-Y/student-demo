package org.billow.controller.activiti;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.Model;
import org.activiti.engine.task.Comment;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.billow.api.workflow.WorkFlowService;
import org.billow.model.custom.DiagramDto;
import org.billow.model.custom.JsonResult;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

	@Autowired(required = false)
	private RepositoryService repositoryService;

	@Autowired
	private WorkFlowService workFlowService;

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
		PageInfo<Model> pages = workFlowService.getModel();
		av.addObject("page", pages);
		av.setViewName(PagePathCst.BASEPATH_ACTIVITI_MODEL + "/actModel");
		return av;
	}

	@RequestMapping("/createModel")
	public String createModel() {
		return PagePathCst.BASEPATH_ACTIVITI_MODEL + "/actAddModel";
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
			Model modelData = workFlowService.createModel(diagram);
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
	public void viewPic(@PathVariable String modelId, HttpServletRequest request, HttpServletResponse response) {
		byte[] data = workFlowService.viewPic(modelId);
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

	/**
	 * 查看模板流程图
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param deploymentId
	 *            部署id
	 * @param resourceType
	 *            资源类型：image/xml
	 * @param request
	 * @param response
	 * @throws IOException
	 * 
	 * @date 2017年5月5日 上午9:36:53
	 */
	@RequestMapping("/viewPicDepId/{resourceType}/{deploymentId}")
	public void viewPicDepId(@PathVariable("resourceType") String resourceType, @PathVariable("deploymentId") String deploymentId,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		// byte[] data = workFlowService.viewPic(modelId);
		byte[] data = workFlowService.viewPicDepId(deploymentId, resourceType);
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

	/**
	 * 删除模板
	 * 
	 * @param modelId
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月25日 下午10:40:16
	 */
	@ResponseBody
	@RequestMapping("/deleteModel/{modelId}")
	public JsonResult deleteModel(@PathVariable String modelId) {
		JsonResult json = new JsonResult();
		try {
			workFlowService.deleteModel(modelId);
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

	/**
	 * 部署流程定义
	 * 
	 * @param modelId
	 *            模板id
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月25日 下午10:40:46
	 */
	@ResponseBody
	@RequestMapping("/deploy/{modelName}/{modelId}")
	public JsonResult deploy(@PathVariable String modelName, @PathVariable String modelId) {
		logger.info("==============" + modelId + "------>" + modelName);
		JsonResult json = new JsonResult();
		// 流程xml文件的名称
		String processName = modelName + ".bpmn20.xml";
		byte[] source = repositoryService.getModelEditorSource(modelId);
		try {
			ObjectNode objectNode = (ObjectNode) new ObjectMapper().readTree(source);
			BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(objectNode);
			byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(bpmnModel);
			DeploymentBuilder createDeployment = repositoryService.createDeployment();
			createDeployment.name(modelName);
			createDeployment.addString(processName, new String(bpmnBytes, "UTF-8"));
			createDeployment.deploy();
			json.setSuccess(true);
			json.setMessage(MessageTipsCst.DEPLOY_SUCCESS);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMessage(MessageTipsCst.DEPLOY_FAILURE);
			e.printStackTrace();
			logger.error(e);
		}
		return json;
	}

	/**
	 * 打开文件部署流程上传页面
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年6月6日 下午9:50:37
	 */
	@RequestMapping("/fileDeploy")
	public String fileDeploy() {
		return PagePathCst.BASEPATH_ACTIVITI_MODEL + "/actFileDeploy";
	}

	@ResponseBody
	@RequestMapping("/saveFileDeploy")
	public JsonResult saveFiledeploy(@RequestParam("zipFile") MultipartFile zipFile, @RequestParam("deployName") String deployName) {
		JsonResult json = new JsonResult();
		try {
			InputStream in = zipFile.getInputStream();
			ZipInputStream zipInputStream = new ZipInputStream(in);
			// 创建发布配置对象
			DeploymentBuilder builder = repositoryService.createDeployment();
			// 设置发布信息
			builder.name(deployName)// 添加部署规则的显示别名
					.addZipInputStream(zipInputStream);
			json.setSuccess(true);
			json.setMessage(MessageTipsCst.DEPLOY_SUCCESS);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMessage(MessageTipsCst.DEPLOY_FAILURE);
			e.printStackTrace();
			logger.error(e);
		}
		return json;
	}

	@ResponseBody
	@RequestMapping("/deployTest")
	public JsonResult deployTest() {
		JsonResult json = new JsonResult();
		try {
			// 从classpath路径下读取资源文件
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("diagrams/leave-formkey.zip");
			ZipInputStream zipInputStream = new ZipInputStream(in);
			// // 创建发布配置对象
			DeploymentBuilder builder = repositoryService.createDeployment();
			// 设置发布信息
			builder.name("请假流程-外置22")// 添加部署规则的显示别名
					// // 添加规则文件
					// .addClasspathResource("diagrams/QingJiaModel.bpmn20.xml")
					// // 添加规则图片 不添加会自动产生一个图片不推荐
					// .addClasspathResource("diagrams/QingJiaModel.png");
					// // 添加规则文件
					// .addClasspathResource("diagrams/leave/leave.bpmn")
					// // 添加规则图片
					// .addClasspathResource("diagrams/leave/leave.png");
					.addZipInputStream(zipInputStream);
			// 完成发布
			builder.deploy();
			json.setSuccess(true);
			json.setMessage(MessageTipsCst.DEPLOY_SUCCESS);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMessage(MessageTipsCst.DEPLOY_FAILURE);
			e.printStackTrace();
			logger.error(e);
		}
		return json;
	}

	/**
	 * 打开流程图显示页面
	 **/
	@RequestMapping("/openActivitiProccessImagePage/{commentType}/{processInstanceId}")
	public ModelAndView openActivitiProccessImagePage(@PathVariable String commentType, @PathVariable String processInstanceId) throws Exception {
		logger.info("[开始]-打开流程图显示页面");
		// 查询批注信息
		List<Comment> comments = workFlowService.findCommentByProcessInstanceId(processInstanceId, commentType);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("processInstanceId", processInstanceId);
		modelAndView.addObject("comments", comments);
		modelAndView.setViewName(PagePathCst.BASEPATH_ACTIVITI + "activitiProccessImagePage");
		logger.info("[完成]-打开流程图显示页面");
		return modelAndView;
	}

	/**
	 * 获取流程图像，已执行节点和流程线高亮显示
	 */
	@RequestMapping("/getActivitiProccessImage/{processInstanceId}")
	public void getActivitiProccessImage(@PathVariable String processInstanceId, HttpServletResponse response) throws Exception {
		workFlowService.getActivitiProccessImage(processInstanceId, response);
	}

	/**
	 * 导出model的xml文件
	 */
	@RequestMapping(value = "/export/{modelId}")
	public void export(@PathVariable("modelId") String modelId, HttpServletResponse response) throws Exception {
		try {
			Model modelData = repositoryService.getModel(modelId);
			BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
			JsonNode editorNode = new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
			BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
			BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
			byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);

			ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
			IOUtils.copy(in, response.getOutputStream());
			String filename = bpmnModel.getMainProcess().getId() + ".bpmn20.xml";
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
			response.flushBuffer();
		} catch (Exception e) {
			response.setContentType("text/html;charset=UTF-8");
			response.getOutputStream().write("暂时没有XML".getBytes("UTF-8"));
			e.printStackTrace();
			logger.error("导出model的xml文件失败：modelId=" + modelId, e);
		}
	}
}
