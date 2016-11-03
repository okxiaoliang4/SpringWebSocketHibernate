$(function() {
	var websocket;
	if ('WebSocket' in window) {
		websocket = new WebSocket("ws://localhost:8080/SH/ws.do");
	} else if ('MozWebSocket' in window) {
		websocket = new MozWebSocket("ws://echo");
	} else {
		websocket = new SockJS("http://localhost:8080/SH/sockjs/ws.do");
	}
	websocket.onopen = function(event) {
		$("#tou").html("连接服务器成功!")
	};
	websocket.onmessage = function(event) {
		dealWithMessage(event);
	};
	websocket.onerror = function(event) {

	};
	websocket.onclose = function(event) {
		$("#tou").html("与服务器断开了链接!")
	};

	$('#send').bind('click', function() {
		send();
	});

	$('#message').keydown(function(e) {
		if (e.keyCode == 13) {
			send();
		}
	});
	$('#message').focus();
	function send() {
		if ($('#message').val().trim() == "") {
			return;
		}
		if (websocket != null) {
			var data = JSON.stringify({
				"toUserId" : "91e8319d-2bef-48f9-b857-2bd139379399",
				"message" : $('#message').val().trim()
			});
			$('#message').val("");
			websocket.send(data);
		} else {
			alert('未与服务器链接.');
		}
	}
	function dealWithMessage(event) {
		var data = jQuery.parseJSON(event.data);
		var message = data.message.replace("&", "&amp;").replace("<", "&lt;");
		var date = new Date(data.date).Format("hh:mm:ss");
		console.log(message);
		if (userId == data.userId) {
			// 发送者
			$("#chatBoxUl")
					.append(
							"<li><div class='sender'><div><input class='messageDataInput'data-messageid='cce8d038-e969-4a95-9e52-f1a9d17cb032'"
									+ "type='hidden' data-time='"
									+ date
									+ "'><img src='img/headImage/b8cc5ae5-323d-4b22-a63c-ebab8041ae0d.png'></div><div>"
									+ "<div class='left_triangle'></div><span>"
									+ message + "</span></div></div></li>");
		} else {
			$("#chatBoxUl")
					.append(
							"<li><div class='receiver'><div><input class='messageDataInput'data-messageid='cce8d038-e969-4a95-9e52-f1a9d17cb032'"
									+ "type='hidden' data-time='"
									+ date
									+ "'><img src='img/headImage/b8cc5ae5-323d-4b22-a63c-ebab8041ae0d.png'></div><div>"
									+ "<div class='right_triangle'></div><span>"
									+ message + "</span></div></div></li>");
		}
		$("#chatBoxUl").stop().animate({
			scrollTop : $('#chatBoxUl')[0].scrollHeight
		}, 500);
	}
});