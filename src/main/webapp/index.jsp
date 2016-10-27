<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	session.setAttribute("userId", "1");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>WebSocket</title>
</head>
<script type="text/javascript">
	var socket;
	if (!window.WebSocket) {
		window.WebSocket = window.MozWebSocket;
	}
	if (window.WebSocket) {
		socket = new WebSocket("ws://localhost:8888/websocket");

		socket.onmessage = function(event) {
			var ta = document.getElementById('responseText');
			ta.value += event.data + "\r\n";
		};

		socket.onopen = function(event) {
			var ta = document.getElementById('responseText');
			ta.value = "打开WebSoket 服务正常，浏览器支持WebSoket!" + "\r\n";
		};

		socket.onclose = function(event) {
			var ta = document.getElementById('responseText');
			ta.value = "";
			ta.value = "socket服务已关闭" + "\r\n";
		};
	} else {
		alert("您的浏览器不支持WebSocket协议！");
	}

	function send(message) {
		if (!window.WebSocket) {
			return;
		}
		if (socket.readyState == WebSocket.OPEN) {
			socket.send(message);
		} else {
			alert("WebSocket 连接没有建立成功！");
		}

	}
</script>
<body>
	<form onSubmit="return false;">
		<input type="text" name="message" value="Netty The Sinper" /> <br />
		<br /> <input type="button" value="发送 WebSocket 请求消息"
			onClick="send(this.form.message.value)" />
		<hr color="blue" />
		<h3>服务端返回的应答消息</h3>
		<textarea id="responseText" style="width: 1024px; height: 300px;"></textarea>
	</form>
</body>
</html>
