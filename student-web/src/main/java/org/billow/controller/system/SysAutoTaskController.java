package org.billow.controller.system;

import java.util.Date;
import java.util.List;

import org.billow.api.system.ScheduleJobService;
import org.billow.common.constant.MessageTipsCst;
import org.billow.common.constant.PagePathCst;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.ScheduleJobDto;
import org.billow.utils.PageHelper;
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

	@Autowired
	private ScheduleJobService scheduleJobService;

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
	 * @return
	 * 
	 * @date 2017年5月11日 下午2:59:31
	 */
	@RequestMapping("/editAutoTask")
	public ModelAndView editAutoTask(ScheduleJobDto scheduleJobDto) {
		ModelAndView av = new ModelAndView();
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
	public JsonResult updateJobStatus(@PathVariable("jobId") Long jobId, String jobStatus) {
		JsonResult json = new JsonResult();
		ScheduleJobDto dto = new ScheduleJobDto();
		dto.setJobId(jobId);
		dto.setJobStatus(jobStatus);
		dto.setUpdateTime(new Date());
		try {
			scheduleJobService.updateByPrimaryKeySelective(dto);
			json.setSuccess(true);
			json.setMessage(MessageTipsCst.UPDATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage(MessageTipsCst.UPDATE_FAILURE);
		}
		return json;
	}

	@ResponseBody
	@RequestMapping("/deleteAutoTask/{jobId}")
	public JsonResult deleteAutoTask(@PathVariable("jobId") int jobId){
		JsonResult json = new JsonResult();
		try {
			scheduleJobService.deleteByPrimaryKey(jobId);
			json.setSuccess(true);
			json.setMessage(MessageTipsCst.UPDATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMessage(MessageTipsCst.UPDATE_FAILURE);
		}
		return json;
	}
	
}
