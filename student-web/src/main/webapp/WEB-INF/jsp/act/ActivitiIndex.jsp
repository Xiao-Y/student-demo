<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>Activeti Model</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<body>
	<h1>Activeti</h1>
	<a href="<%=basePath%>/activitiController/createModel">创建Model</a>
	<br>
	<a href="<%=basePath%>/activitiController/findModelList">Model列表</a>
	<br>
	<a href=""></a>
	<br>
	<a href=""></a>
	<br>
	<a href=""></a>
	<br>
</body>
</html>
