<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>报销申请列表</title>
    <jsp:include page="/pub/pubTableCss.jsp"/>
    <jsp:include page="/pub/pubJs.jsp"/>
    <script type="text/javascript"
            src="${ctx }/static/page/activiti/apply/reb/reimbursement/rebApplyList.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/extend/pubPopForm.js"></script>

</head>
<body>
<div class="admin-main">
    <blockquote class="layui-elem-quote">
        <a href="${ctx }/applyReb/editReb" class="layui-btn layui-btn-small">
            <i class="layui-icon">&#xe608;</i> 报销申请
        </a>
        <a href="javascript:;" class="layui-btn layui-btn-small" id="search">
            <i class="layui-icon">&#xe615;</i> 搜索
        </a>
    </blockquote>
    <fieldset class="layui-elem-field">
        <legend>报销申请列表</legend>
        <div class="layui-field-box">
            <table class="site-table table-hover">
                <thead>
                <tr>
                    <th>报销类型</th>
                    <th>状态</th>
                    <th>申请金额</th>
                    <th>申请时间</th>
                    <th>创建时间</th>
                    <th>更新时间</th>
                    <th>当前节点</th>
                    <th>任务创建时间</th>
                    <th>流程状态</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="reb" items="${page.list}">
                    <c:set var="task" value="${reb.task }"/>
                    <c:set var="pi" value="${reb.historicProcessInstance }"/>
                    <tr>
                        <td>${reb.rebType }</td>
                        <td>${reb.status }</td>
                        <td>${reb.amount }</td>
                        <td>
                            <fmt:formatDate value="${reb.applyTime }" pattern="yyyy-MM-dd"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${reb.creatDate }" pattern="yyyy-MM-dd"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${reb.updateDate }" pattern="yyyy-MM-dd"/>
                        </td>
                        <td>
                            <a target="_blank" title="点击查看流程图"
                               href='${ctx }/sysAct/openActivitiProccessImagePage/rebComment/${reb.processInstanceId }'> ${reb.taskName } </a>
                        </td>
                        <td>
                            <fmt:formatDate value="${task.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <b title='流程版本号'>V: ${reb.processDefinition.version }</b>
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