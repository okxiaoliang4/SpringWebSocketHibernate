<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String wspath = request.getServerName() + ":"
			+ request.getServerPort() + path;
	String userId = (String) session.getAttribute("userId");
	if (userId == null || userId == "") {
		response.sendRedirect(basePath);
	}
%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/flat-ui.css">
<link rel="stylesheet" href="css/css.css">
<script src="js/sockjs-0.3.min.js"></script>
<script src="js/vendor/jquery.min.js"></script>
<script src="js/flat-ui.min.js"></script>
<script src="js/util.js"></script>
<script src="js/main.js"></script>
<script>
var userId="<%=userId%>";
</script>
<title>聊天室</title>

</head>
<body>
	<div class="container">
		<div class="page-header" id="tou">webSocket及时聊天Demo程序</div>
		<div class="row">
			<div id="friendList" class="well col-xs-3"></div>
			<div id="talkWindow" class="well col-xs-8">
				<ul class="" id="msg">
					<li class="floatL">
						<div class="arrow-left"></div>
						<div class='chatBubbles bg1'>
							123123213213
						</div>
					</li>
					<li class="floatL">
						<div class="arrow-left"></div>
						<div class='chatBubbles bg1'>
							123123213213
						</div>
					</li>
					<li class="floatR">
						<div class="arrow-right"></div>
						<div class='chatBubbles bg2'>
							123123213213
						</div>
					</li>
				</ul>
				<div class="input-group"
					style="top: 458px; left: 0px; position: absolute;">
					<input type="text" class="form-control" placeholder="发送信息..."
						id="message" maxlength="150"> <span
						class="input-group-btn">
						<button class="btn btn-default" type="button" id="send">发送</button>
					</span>
				</div>
			</div>
		</div>
	</div>
</body>
</html>