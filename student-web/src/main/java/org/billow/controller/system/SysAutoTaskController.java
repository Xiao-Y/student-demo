package org.billow.controller.system;

import java.util.List;

import org.billow.api.system.ScheduleJobService;
import org.billow.common.constant.PagePathCst;
import org.billow.model.expand.ScheduleJobDto;
import org.billow.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@RequestMapping("/editAutoTask")
	public ModelAndView editAutoTask(ScheduleJobDto scheduleJobDto) {
		ModelAndView av = new ModelAndView();
		av.setViewName(PagePathCst.BASEPATH_SYSTEM + "autoTaskEdit");
		return av;
	}
}
