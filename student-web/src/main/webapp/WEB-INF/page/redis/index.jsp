<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<%@ include file="/pub/pubTips.jsp" %>
<html>
<head>
    <title>Dubbo</title>
    <jsp:include page="/pub/pubFormCss.jsp"/>
    <jsp:include page="/pub/pubFormJs.jsp"/>
    <script type="text/javascript" src="${ctx }/static/page/redis/index.js"></script>
</head>

<body>
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
    <ul class="layui-tab-title">
        <li class="layui-this">Redis插入测试</li>
        <li>Redis获取测试</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <div style="margin: 15px;">
                <form class="layui-form layui-form-pane1" data-type="ajax" action="${ctx }/redisController/addValue">
                    <div class="layui-form-item">
                        <label class="layui-form-label">KEY</label>
                        <div class="layui-input-block">
                            <input type="text" name="key" lay-verify="required" required placeholder="请输入KEY"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">VALUE</label>
                        <div class="layui-input-block">
                            <textarea class="layui-textarea" placeholder="请输入VALUE" name="value"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <input type="hidden" class="pathUrl" value="${ctx }/redisController/addValue">
                            <button class="layui-btn" lay-submit lay-filter="*" name="readMsgBt">插入</button>
                            <button class="layui-btn layui-btn-primary" type="reset">重置</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="layui-tab-item">
            <div style="margin: 15px;">
                <div class="layui-form layui-form-pane1">
                    <div class="layui-form-item">
                        <label class="layui-form-label">KEY</label>
                        <div class="layui-input-block">
                            <input type="text" name="key" lay-verify="required" required placeholder="请输入KEY"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">VALUE</label>
                        <div class="layui-input-block">
                            <textarea readonly="readonly" class="layui-textarea" name="value"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <input type="hidden" class="pathUrl" value="${ctx }/redisController/getValue">
                            <button class="layui-btn" name="readMsgBt" type="button">获取</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
