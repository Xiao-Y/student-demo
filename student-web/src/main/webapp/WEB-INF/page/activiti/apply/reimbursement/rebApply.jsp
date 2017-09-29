<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<%@ include file="/pub/pubTips.jsp" %>
<html>
<head>
    <title>报销申请</title>
    <jsp:include page="/pub/pubFormCss.jsp"/>
    <jsp:include page="/pub/pubFormJs.jsp"/>
    <script type="text/javascript" src="${ctx }/static/page/activiti/apply/reimbursement/rebApply.js"></script>
</head>

<body>
<fieldset class="layui-elem-field">
    <legend>报销申请信息</legend>
    <form class="layui-form layui-form-pane1" style="margin: 15px;" data-type="ajax"
          action="${ctx }/applyLeave/saveLeave">
        <div class="layui-form-item">
            <label class="layui-form-label">报销类型：</label>
            <div class="layui-input-block">
                <input type="hidden" id="processDefinitionKey" name="processDefinitionKey"
                       value="${rebDto.processDefinitionKey }">
                <input type="hidden" id="applyTypeTemp" value="${rebDto.applyType}">
                <select id="applyType" name="applyType" lay-verify="required" lay-search>
                    <option value=""></option>
                    <option value="1">1-差旅费</option>
                    <option value="2">2-招待费</option>
                    <option value="3">3-车辆费用</option>
                    <option value="4">4-培训费</option>
                    <option value="5">5-电话费</option>
                    <option value="6">6-网络费</option>
                    <option value="7">7-低值易耗品及备品备件</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">报销金额：</label>
            <div class="layui-input-block">
                <input type="text" name="amount" class="layui-input" value="${rebDto.amount }"
                       placeholder="￥" autocomplete="off">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">报销备注：</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入内容" lay-verify="required" class="layui-textarea"
                          name="reason">${rebDto.reason }</textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="*">申请</button>
                <button type="reset" id="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</fieldset>
<fieldset class="layui-elem-field">
    <legend>说明：</legend>
    &nbsp;&nbsp;&nbsp;1、差旅费：火车票、飞机票、长途汽车票、订票费发票；<br/>
    &nbsp;&nbsp;&nbsp;2、招待费：餐饮业发票、住宿费发票、打车票、礼品；<br/>
    &nbsp;&nbsp;&nbsp;3、车辆费用：加油费发票、过路过桥费发票、停车费；<br/>
    &nbsp;&nbsp;&nbsp;4、培训费：培训费发票、培训费收据；<br/>
    &nbsp;&nbsp;&nbsp;5、电话费：电话费发票、手机费发票；<br/>
    &nbsp;&nbsp;&nbsp;6、网络费：网络费发票、网络服务发票；<br/>
    &nbsp;&nbsp;&nbsp;7、低值易耗品及备品备件：办公耗材发票<br/>
</fieldset>
</body>
</html>
