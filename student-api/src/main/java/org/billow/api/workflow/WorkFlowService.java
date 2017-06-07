package org.billow.api.workflow;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.ProcessDefinition;

/**
 * 工作流程统一正理类
 * 
 * @author liuyongtao
 * 
 * @date 2017年6月7日 上午8:58:12
 */
public interface WorkFlowService {

	/**
	 * 获取流程图
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param pProcessInstanceId
	 *            流程实例id
	 * @param response
	 * @throws Exception
	 * 
	 * @date 2017年6月7日 上午9:00:26
	 */
	public void getActivitiProccessImage(String pProcessInstanceId, HttpServletResponse response) throws Exception;

	/**
	 * 查询个人任务
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param list
	 *            业务对象
	 * @param processDefinitionKey
	 *            流程定义Key
	 * @param assignee
	 *            办理人
	 * @return
	 * @throws Exception
	 * 
	 * @date 2017年6月7日 上午9:42:56
	 */
	public <T> List<T> findMyTask(List<T> list, String processDefinitionKey, String assignee) throws Exception;

	/**
	 * 查询流程定义对象
	 * 
	 * @param processDefinitionId
	 *            流程定义ID
	 * @return
	 */
	public ProcessDefinition getProcessDefinition(String processDefinitionId);
}
