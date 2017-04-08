<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>jQuery UI 按钮（Button） - 默认功能</title>
  <jsp:include page="/static/jqueryui.jsp"/>
  <script>
  $(function() {
    $( "input[type=submit], a, button" )
      .button()
      .click(function( event ) {
        event.preventDefault();
      });
  });
  </script>
</head>
<body>
 
<button>一个 button 元素</button>
 
<input type="submit" value="一个提交按钮">
 
<a href="#">一个锚</a>
 
 
</body>
</html>