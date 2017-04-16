<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">

<title>Upload程序</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="http://blueimp.github.io/Gallery/css/blueimp-gallery.min.css">
<link rel="stylesheet" href="<%=basePath%>static/jQuery-File-Upload/css/style.css">
<link rel="stylesheet" href="<%=basePath%>static/jQuery-File-Upload/css/jquery.fileupload.css">
<link rel="stylesheet" href="<%=basePath%>static/jQuery-File-Upload/css/jquery.fileupload-ui.css">
<script type="text/javascript" src="<%=basePath%>static/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="<%=basePath%>static/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=basePath%>static/jQuery-File-Upload/js/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="<%=basePath%>static/jQuery-File-Upload/js/jquery.fileupload.js"></script>
</head>
<body>
	<div class="container">
		<h1>jQuery File Upload Demo</h1>
		<form id="fileupload" action="uploadTest/upload" method="POST" enctype="multipart/form-data">
			<noscript>
				<input type="hidden" name="redirect" value="https://blueimp.github.io/jQuery-File-Upload/">
			</noscript>
			<div class="row fileupload-buttonbar">
				<div class="col-lg-7">
					<span class="btn btn-success fileinput-button"> <i class="glyphicon glyphicon-plus"></i> <span>Add
							files...</span> <input type="file" name="files" multiple>
					</span>
					<button type="submit" class="btn btn-primary start">
						<i class="glyphicon glyphicon-upload"></i>
						<span>Start upload</span>
					</button>
					<button type="reset" class="btn btn-warning cancel">
						<i class="glyphicon glyphicon-ban-circle"></i>
						<span>Cancel upload</span>
					</button>
					<button type="button" class="btn btn-danger delete">
						<i class="glyphicon glyphicon-trash"></i>
						<span>Delete</span>
					</button>
					<input type="checkbox" class="toggle">
					<span class="fileupload-process"></span>
				</div>
				<div class="col-lg-5 fileupload-progress fade">
					<div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
						<div class="progress-bar progress-bar-success" style="width: 0%;"></div>
					</div>
					<div class="progress-extended">&nbsp;</div>
				</div>
			</div>
			<table role="presentation" class="table table-striped">
				<tbody class="files"></tbody>
			</table>
		</form>
	</div>
</body>
<script>
	$(function() {
		$('#fileupload').fileupload({
			dataType : 'json',
			done : function(e, data) {
				$.each(data.files, function(index, file) {
					$('<p/>').text(file.name).appendTo(document.body);
				});
			},
			progressall : function(e, data) {
				var progress = parseInt(data.loaded / data.total * 100, 10);
				$('#progress .bar').css('width', progress + '%');
			}
		});
	});
</script>
</html>