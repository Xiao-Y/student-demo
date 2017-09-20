package org.billow.api.workflow;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.billow.model.custom.DiagramDto;

import com.github.pagehelper.PageInfo;

/**
 * 工作流程统一正理类
 *
 * @author liuyongtao
 * @date 2017年6月7日 上午8:58:12
 */
public interface WorkFlowService {

    /**
     * 获取流程图
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @param pProcessInstanceId 流程实例id
     * @param response
     * @throws Exception
     * @date 2017年6月7日 上午9:00:26
     */
    public void getActivitiProccessImage(String pProcessInstanceId, HttpServletResponse response) throws Exception;

    // /**
    // * 查询个人任务列表
    // *
    // * <br>
    // * added by liuyongtao<br>
    // *
    // * @param list
    // * 业务对象
    // * @param processDefinitionKey
    // * 流程定义Key
    // * @param assignee
    // * 办理人
    // * @return
    // * @throws Exception
    // *
    // * @date 2017年6月7日 上午9:42:56
    // */
    // public <T> List<T> findMyTaskList(List<T> list, String
    // processDefinitionKey, String assignee) throws Exception;
    //
    // /**
    // * 查询个人任务
    // *
    // * <br>
    // * added by liuyongtao<br>
    // *
    // * @param list
    // * 业务对象
    // * @param processDefinitionKey
    // * 流程定义Key
    // * @param assignee
    // * 办理人
    // * @return
    // * @throws Exception
    // *
    // * @date 2017年6月7日 上午9:42:56
    // */
    // public <T> T findMyTask(T t, String processDefinitionKey, String
    // assignee) throws Exception;

    /**
     * 查询流程定义对象
     *
     * @param processDefinitionId 流程定义ID
     * @return
     */
    public ProcessDefinition getProcessDefinition(String processDefinitionId);

    /**
     * 通过流程定义key启动流程实例
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @param processDefinitionKey 流程定义key
     * @param businessKey          业务主键
     * @return 流程实例
     * @date 2017年6月7日 下午12:17:34
     */
    public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey);

    /**
     * 通过流程实例id查询任务
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @param processInstanceId 流程实例id
     * @return 任务
     * @date 2017年6月7日 下午12:20:23
     */
    public Task findTaskByProcessInstanceId(String processInstanceId);

    /**
     * 保存批注信息
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @param taskId            任务Id
     * @param processInstanceId 流程实例id
     * @param type              关键字
     * @param message           信息
     * @date 2017年6月7日 下午12:24:38
     */
    public void addComment(String taskId, String processInstanceId, String type, String message);

    /**
     * 保存批注信息
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @param taskId            任务Id
     * @param processInstanceId 流程实例id
     * @param type              关键字
     * @param message           信息
     * @param userName          操作人
     * @date 2017年7月3日 下午5:42:08
     */
    public void addComment(String taskId, String processInstanceId, String type, String message, String userName);

    /**
     * 根据流程实例id和type查询批注信息
     *
     * @param processInstanceId 流程实例id
     * @param type
     * @return
     * @author XiaoY
     * @date: 2017年6月7日 下午10:36:19
     */
    public List<Comment> findCommentByProcessInstanceId(String processInstanceId, String type);

    /**
     * 完成个人任务，并添加批注信息
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @param t
     * @param processDefinitionKey 流程定义key
     * @param assignee             任务人
     * @throws Exception
     * @date 2017年6月8日 下午12:54:15
     */
    public <T> void complete(T t, String processDefinitionKey, String assignee) throws Exception;

    /**
     * 完成个人任务，并添加批注信息
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @param t
     * @param processDefinitionKey 流程定义key
     * @param assignee             任务人
     * @param variables            流程变量
     * @throws Exception
     * @date 2017年6月22日 上午9:58:11
     */
    public <T> void complete(T t, String processDefinitionKey, String assignee, Map<String, Object> variables) throws Exception;

    /**
     * 根据任务Id，获取所有出口的名称，用于生成按钮
     *
     * @param taskId 任务Id
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

    /**
     * 查询待办任务
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @param userId 用户Id
     * @return
     * @throws Exception
     * @date 2017年6月13日 上午8:51:48
     */
    public <T, C> PageInfo<T> findTodoTaskList(String userId, C s) throws Exception;

    /**
     * 任务签收
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @param taskId   任务Id
     * @param userName 签收人
     * @date 2017年6月13日 下午2:56:21
     */
    public void claim(String taskId, String userName);

    /**
     * 根据流程定义KEY读取外置表单(初始化启动流程，读取启动流程的表单内容来渲染start)
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @param processDefinitionKey
     * @return
     * @date 2017年6月29日 下午4:48:34
     */
    public Object getRenderedStartForm(String processDefinitionKey);

    /**
     * 通过流程定义的key查询最新的流程定义
     *
     * @param processDefinitionKey 流程定义的key
     * @return 最新的流程定义
     * @author XiaoY
     * @date: 2017年7月1日 下午9:09:42
     */
    public ProcessDefinition getProcessDefinitionLatestVersion(String processDefinitionKey);

    /**
     * 提交开始表单数据启动流程实例（最新的流程定义）
     *
     * @param processDefinitionKey 流程定义key
     * @param businessKey          业务key
     * @param properties           表单参数
     * @param userName             操作人
     * @return
     * @author XiaoY
     * @date: 2017年7月1日 下午9:12:46
     */
    public ProcessInstance submitStartFormData(String processDefinitionKey, String businessKey, Map<String, String> properties, String userName);

    /**
     * 根据流程实例id查询任务表单
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @param processInstanceId 流程实例id
     * @return
     * @date 2017年7月3日 上午10:01:06
     */
    public Object getRenderedTaskForm(String processInstanceId);

    /**
     * 保存任务表单
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @param taskId     任务id
     * @param properties 页面参数
     * @param userName   操作人
     * @date 2017年7月3日 下午3:25:53
     */
    public void submitTaskFormData(String taskId, Map<String, String> properties, String userName);

    /**
     * 分页查询出模板列表
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @return
     * @date 2017年5月5日 上午9:37:58
     */
    public PageInfo<Model> getModel();

    /**
     * 创建模板
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @param diagram
     * @return
     * @throws Exception
     * @date 2017年5月5日 上午9:38:29
     */
    public Model createModel(DiagramDto diagram) throws Exception;

    /**
     * 查看模板流程图
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @param modelId 模板id
     * @return
     * @date 2017年5月5日 上午9:37:40
     */
    public byte[] viewPic(String modelId);

    /**
     * 通过部署id查看流程图
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @param deploymentId 部署id
     * @param resourceType 资源类型：image/xml
     * @return
     * @date 2017年9月9日 上午11:30:50
     */
    public byte[] viewPicDepId(String deploymentId, String resourceType) throws IOException;

    /**
     * 删除模板
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @param modelId 模板id
     * @throws Exception
     * @date 2017年5月5日 上午10:09:25
     */
    public void deleteModel(String modelId) throws Exception;

    /**
     * 查询流程定义列表
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @return
     * @date 2017年9月9日 上午10:42:18
     */
    public PageInfo<ProcessDefinition> queryProcDefList();

    /**
     * 查询流程部署列表
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @return
     * @date 2017年9月9日 下午12:38:53
     */
    public PageInfo<Deployment> queryDeployList();

    /**
     * 删除流程定义
     *
     * @param deployId 部署Id
     * @param b        是否级联删除
     * @throws Exception
     */
    void deleteDeploy(String deployId, boolean b) throws Exception;
}
