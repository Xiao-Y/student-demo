package org.billow.api.workflow;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;

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
	 * 查询个人任务列表
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
	public <T> List<T> findMyTaskList(List<T> list, String processDefinitionKey, String assignee) throws Exception;

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
	public <T> T findMyTask(T t, String processDefinitionKey, String assignee) throws Exception;

	/**
	 * 查询流程定义对象
	 * 
	 * @param processDefinitionId
	 *            流程定义ID
	 * @return
	 */
	public ProcessDefinition getProcessDefinition(String processDefinitionId);

	/**
	 * 通过流程定义key启动流程实例
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param processDefinitionKey
	 *            流程定义key
	 * @param businessKey
	 *            业务主键
	 * @return 流程实例
	 * 
	 * @date 2017年6月7日 下午12:17:34
	 */
	public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey);

	/**
	 * 通过流程实例id查询任务
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param processInstanceId
	 *            流程实例id
	 * @return 任务
	 * 
	 * @date 2017年6月7日 下午12:20:23
	 */
	public Task findTaskByProcessInstanceId(String processInstanceId);

	/**
	 * 保存批注信息
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param taskId
	 *            任务Id
	 * @param processInstanceId
	 *            流程实例id
	 * @param type
	 *            关键字
	 * @param message
	 *            信息
	 * 
	 * @date 2017年6月7日 下午12:24:38
	 */
	public void addComment(String taskId, String processInstanceId, String type, String message);

	/**
	 * 根据流程实例id和type查询批注信息
	 * 
	 * @param processInstanceId
	 *            流程实例id
	 * @param type
	 * @return
	 * @author XiaoY
	 * @date: 2017年6月7日 下午10:36:19
	 */
	public List<Comment> findCommentByProcessInstanceId(String processInstanceId, String type);

	/**
	 * 完成个人任务，并添加批注信息
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param t
	 * @param processDefinitionKey
	 *            流程定义key
	 * @param assignee
	 *            任务人
	 * @throws Exception
	 * 
	 * @date 2017年6月8日 下午12:54:15
	 */
	public <T> void complete(T t, String processDefinitionKey, String assignee) throws Exception;

	/**
	 * 根据任务Id，获取所有出口的名称，用于生成按钮
	 * 
	 * @param taskId
	 *            任务Id
	 * @return
	 * @throws Exception
	 * @author XiaoY
	 * @date: 2017年6月10日 下午12:38:53
	 */
	public List<String> getOutGoingTransNames(String taskId) throws Exception;

	/**
	 * 查询任务节点：通过businessKey在act_hi_procinst表中找到流程实例Id，然后找到当前节点
	 * 
	 * @param T
	 * @return
	 * @author XiaoY
	 * @date: 2017年6月12日 下午10:07:52
	 */
	public <T> List<T> findTaskNodeList(List<T> leavList) throws Exception;

	/**
	 * 查询任务节点：通过businessKey在act_hi_procinst表中找到流程实例Id，然后找到当前节点
	 * 
	 * @param T
	 * @return
	 * @author XiaoY
	 * @date: 2017年6月12日 下午10:07:52
	 */
	public <T> void findTaskNode(T t) throws Exception;
}
