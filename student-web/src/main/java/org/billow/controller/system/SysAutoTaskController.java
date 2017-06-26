package org.billow.controller.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.billow.api.system.ScheduleJobService;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.ScheduleJobDto;
import org.billow.service.TaskManagerService;
import org.billow.utils.PageHelper;
import org.billow.utils.ToolsUtils;
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
 * 自动任务管理
 * 
 * @author XiaoY
 * @date: 2017年5月8日 下午8:11:46
 */
@Controller
@RequestMapping("/sysAutoTask")
public class SysAutoTaskController {

	private static final Logger logger = Logger.getLogger(SysAutoTaskController.class);

	@Autowired
	private ScheduleJobService scheduleJobService;

	@Autowired
	private TaskManagerService taskManagerService;

	/**
	 * 显示自动任务列表
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param scheduleJobDto
	 * @return
	 * 
	 * @date 2017年5月11日 下午2:59:15
	 */
	@RequestMapping("/findAutoTask")
	public ModelAndView findAutoTask(ScheduleJobDto scheduleJobDto) {
		ModelAndView av = new ModelAndView();
		PageHelper.startPage();
		List<ScheduleJobDto> jods = scheduleJobService.selectAll(scheduleJobDto);
		PageInfo<ScheduleJobDto> page = new PageInfo<>(jods);
		av.addObject("page", page);
		av.setViewName(PagePathCst.BASEPATH_SYSTEM + "autoTaskManage");
		return av;
	}

	/**
	 * 自动任务修改页面
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param scheduleJobDto
	 *            jobId为-1表示是添加，不为-1表示修改
	 * @return
	 * 
	 * @date 2017年5月11日 下午2:59:31
	 */
	@RequestMapping("/editAutoTask/{jobId}")
	public ModelAndView editAutoTask(@PathVariable("jobId") Integer jobId) {
		ModelAndView av = new ModelAndView();
		if (jobId.compareTo(-1) != 0) {// 表示编辑
			ScheduleJobDto dto = new ScheduleJobDto();
			dto.setJobId(jobId);
			dto = scheduleJobService.selectByPrimaryKey(dto);
			av.addObject("task", dto);
		}
		av.setViewName(PagePathCst.BASEPATH_SYSTEM + "autoTaskEdit");
		return av;
	}

	/**
	 * 启用、禁用自动任务
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param jobId
	 *            自动任务id
	 * @param jobStatus
	 *            任务状态，1-启用，0-禁用
	 * @return
	 * 
	 * @date 2017年5月11日 下午2:58:16
	 */
	@ResponseBody
	@RequestMapping("/updateJobStatus/{jobId}")
	public JsonResult updateJobStatus(@PathVariable("jobId") Integer jobId, String jobStatus) {
		JsonResult json = new JsonResult();
		ScheduleJobDto dto = new ScheduleJobDto();
		dto.setJobId(jobId);
		dto.setJobStatus(jobStatus);
		dto.setUpdateTime(new Date());
		try {
			taskManagerService.updateJobStatus(dto);
			json.setSuccess(true);
			json.setMessage(MessageTipsCst.UPDATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage(MessageTipsCst.UPDATE_FAILURE);
		}
		return json;
	}

	/**
	 * 删除自动任务
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param jobId
	 * @return
	 * 
	 * @date 2017年5月12日 上午8:42:28
	 */
	@ResponseBody
	@RequestMapping("/deleteAutoTask/{jobId}")
	public JsonResult deleteAutoTask(@PathVariable("jobId") int jobId) {
		JsonResult json = new JsonResult();
		try {
			taskManagerService.deleteAutoTask(jobId);
			json.setSuccess(true);
			json.setMessage(MessageTipsCst.UPDATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage(MessageTipsCst.UPDATE_FAILURE);
		}
		return json;
	}

	/**
	 * 保存自动任务
	 * 
	 * @param scheduleJobDto
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月22日 上午10:07:54
	 */
	@ResponseBody
	@RequestMapping("/saveAutoTask")
	public JsonResult saveAutoTask(ScheduleJobDto scheduleJobDto) {
		JsonResult json = new JsonResult();
		List<String> list = new ArrayList<>();
		try {
			list = taskManagerService.saveAutoTask(scheduleJobDto);
			if (ToolsUtils.isNotEmpty(list)) {
				json.setSuccess(false);
				String returnStr = StringUtils.join(list, "&");
				json.setMessage(returnStr);
				json.setRoot("exceptionFlag");
				logger.info("==================" + returnStr + "====================");
				return json;
			}
			json.setSuccess(true);
			json.setMessage(MessageTipsCst.UPDATE_SUCCESS);
			json.setRoot("/sysAutoTask/findAutoTask");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage(MessageTipsCst.UPDATE_FAILURE);
		}
		return json;
	}
}
