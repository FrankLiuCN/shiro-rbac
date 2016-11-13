<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>login</title>
<link rel="icon" href="" />
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport" />
<link href="<%=basePath%>/libs/bootstrap-3.3.0/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="<%=basePath%>/css/AdminLTE.min.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>/libs/iCheck/square/blue.css">
<link rel="stylesheet" href="<%=basePath%>/libs/jBox/jBox.css">
<link href="<%=basePath%>/css/login.css" rel="stylesheet" />
<link href="<%=basePath%>/css/cBox.css" rel="stylesheet" />

<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="<%=basePath%>/libs/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/libs/iCheck/icheck.min.js"></script>
<script src="<%=basePath%>/libs/jBox/jBox.js"></script>
<script src="<%=basePath%>/js/cBox.js"></script>

<script type="text/javascript">
	var basePath="<%=basePath%>";
	$(function() {
		$('input').iCheck({
			checkboxClass : 'icheckbox_square-blue',
			radioClass : 'iradio_square-blue',
			increaseArea : '20%' // optional
		});

		$("#txtSubmit").click(function() {
			if($("#txtUserName").val().trim()===""){
				ccNotice("登录名不能为空。");
				return;
			}
			if($("#txtPassword").val().trim()===""){
				ccNotice("密码不能为空。");
				return;
			}
			login();
		});
	});

	function login() {
		$.post(basePath + "/login", {
			loginName : $("#txtUserName").val(),
			password : $("#txtPassword").val(),
			rememberMe:false
		}, function(result) {
			if (result.code == 0) {
				window.location.href =basePath+ result.data;
				ccNotice(result.data);
			} else {
				ccNotice(result.msg);
			}
		});
	}
</script>
</head>
<body class="login-page">
	<div class="login-box">
		<div class="login-box-body">
			<div class="login-logo">
				<b>权限管理系统</b>
			</div>
				<div class="form-group has-feedback">
					<input type="text" id="txtUserName" class="form-control" placeholder="登录名">
					<span class="glyphicon glyphicon-user form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" id="txtPassword" class="form-control" placeholder="密码">
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label> <input type="checkbox"> 记住我
							</label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
					<button type="button" id="txtSubmit" class="btn btn-primary btn-block btn-flat">登录</button>
				</div>
				</div>
		</div>
	</div>
</body>
</html>