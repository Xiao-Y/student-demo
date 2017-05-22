package org.billow.service;

import java.util.List;

import org.billow.model.expand.ScheduleJobDto;

/**
 * 任务管理
 * 
 * @author liuyongtao
 * 
 * @date 2017年5月12日 下午5:29:33
 */
public interface TaskManagerService {

	/**
	 * 启用、禁用自动任务
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param dto
	 * @throws Exception
	 * 
	 * @date 2017年5月12日 下午5:36:01
	 */
	public void updateJobStatus(ScheduleJobDto dto) throws Exception;

	/**
	 * 删除自动任务
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param jobId
	 * @throws Exception
	 * 
	 * @date 2017年5月12日 下午7:17:41
	 */
	public void deleteAutoTask(int jobId) throws Exception;

	/**
	 * 保存/更新自动任务
	 * 
	 * @param scheduleJobDto
	 * @return 返回错误信息
	 * @throws Exception
	 * @author XiaoY
	 * @date: 2017年5月22日 上午10:08:48
	 */
	public List<String> saveAutoTask(ScheduleJobDto scheduleJobDto) throws Exception;
}
