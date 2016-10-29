<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/flat-ui.css">
<script src="js/sockjs-0.3.min.js"></script>
<script src="js/vendor/jquery.min.js"></script>
<script src="js/flat-ui.min.js"></script>
<title>登录</title>
<script>
	function login() {
		$.ajax({
			url : "<%=basePath%>user/login.do",
			dataType : "json",
			type : "post",
			data : {
				"username" : $("#login-name").val(),
				"password" : $("#login-pass").val()
			},
			success : function(data) {
				if (data.state == 0)
					location.href = "main.jsp";
			},
			error : function(data) {
				console.log(data);
			}
		});
	}
</script>
</head>

<body>
	<div class="container" style="margin-top: 20px;">
		<div class="login">
			<div class="login-screen">
				<div class="login-icon">
					<img src="img/icons/mail.png" alt="Welcome to Mail App">
					<h4>
						欢迎来到 <small>聊天室</small>
					</h4>
				</div>
				<div class="login-form">
					<div class="control-group">
						<input name="username" type="text" class="login-field"
							style="width: 312px;" value="" placeholder="用户名" id="login-name">
					</div>
					<div class="control-group">
						<input name="password" type="password" class="login-field"
							style="width: 312px;" value="" placeholder="密码" id="login-pass">
					</div>
					<a class="btn btn-primary btn-large btn-block"
						href="javascript:login();">登录</a> <a class="login-link" href="#">忘记密码?</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
