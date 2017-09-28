<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<%@ include file="/pub/pubTips.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>请假待办任务列表</title>
    <jsp:include page="/pub/pubTableCss.jsp"/>
    <jsp:include page="/pub/pubJs.jsp"/>
    <script type="text/javascript" src="${ctx }/static/page/activiti/approval/leave/form-key/leaveTaskList.js"></script>
</head>
<body>
<div class="admin-main">
    <blockquote class="layui-elem-quote">
        <a href="javascript:;" class="layui-btn layui-btn-small" id="add">
            <i class="layui-icon">&#xe608;</i>
            添加信息
        </a>
        <a href="javascript:;" class="layui-btn layui-btn-small" id="search">
            <i class="layui-icon">&#xe615;</i>
            搜索
        </a>
    </blockquote>
    <fieldset class="layui-elem-field">
        <legend>数据列表</legend>
        <div class="layui-field-box">
            <table class="site-table table-hover">
                <thead>
                <tr>
                    <th>假种</th>
                    <th>申请人</th>
                    <th>申请时间</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>当前节点</th>
                    <th>任务创建时间</th>
                    <th>流程状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="leave" items="${page.list}">
                    <c:set var="task" value="${leave.task }"/>
                    <c:set var="pi" value="${leave.historicProcessInstance }"/>
                    <tr id="${leave.id }" tid="${task.id }">
                        <td>${leave.leaveType }</td>
                        <td>${leave.userName }</td>
                        <td>
                            <fmt:formatDate value="${leave.applyTime  }" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${leave.startTime  }" pattern="yyyy-MM-dd"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${leave.endTime  }" pattern="yyyy-MM-dd"/>
                        </td>
                        <td>
                            <a target="_blank" title="点击查看流程图"
                               href='${ctx }/sysAct/openActivitiProccessImagePage/leaveComment/${leave.processInstanceId }'>
                                <c:if test="${empty task.name }">已完成</c:if>
                                <c:if test="${not empty task.name }">${task.name }</c:if>
                            </a>
                        </td>
                        <td>
                            <fmt:formatDate value="${task.createTime  }" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <b title='流程版本号'>V: ${leave.processDefinition.version }</b>
                        </td>
                        <td>
                            <c:if test="${leave.status == '7' }">
                                <a href="${ctx }/formkey/applyLeave/editLeave?id=${leave.id }&processInstanceId=${leave.processInstanceId }">修改</a>
                            </c:if>
                            <c:if test="${leave.status != '7' }">
                                <c:if test="${empty task.assignee }">
                                    <a class="claim" id="${leave.id}" name="leaveClaim" taskId="${task.id}"
                                       href="javascript:;">签收</a>
                                </c:if>
                                <c:if test="${not empty task.assignee }">
                                    <c:if test="${leave.userName == task.assignee}">
                                        <c:if test="${task.name == '销假'}">
                                            <a href="${ctx }/formkey/approvalLeave/leaveApplyApp?id=${leave.id }&processInstanceId=${leave.processInstanceId }&taskId=${task.id}&flag=reportBack">销假</a>
                                        </c:if>
                                        <c:if test="${task.name != '销假'}">
                                            <a href="${ctx }/formkey/approvalLeave/leaveApplyApp?id=${leave.id }&processInstanceId=${leave.processInstanceId }&taskId=${task.id}">办理</a>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${leave.userName != task.assignee }">
                                        <a href="${ctx }/formkey/approvalLeave/leaveApplyApp?id=${leave.id }&processInstanceId=${leave.processInstanceId }&taskId=${task.id}">办理</a>
                                    </c:if>
                                </c:if>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </fieldset>
    <!-- 分页 -->
    <jsp:include page="/pub/pubPage.jsp"/>
</div>
</body>

</html>