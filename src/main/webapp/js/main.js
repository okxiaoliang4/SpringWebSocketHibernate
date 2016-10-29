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
				"toUserId" : "test",
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
		if (userId == data.userId) {
			// 发送者
			$("#msg").append(
					"<li class='floatR'><div class='arrow-right'></div><div class='chatBubbles bg2'><span>"
					+ message + "</span></div></li>");
		}else{
			$("#msg").append(
					"<li><div class='arrow-left'></div><div class='chatBubbles bg1'><span>"
					+ message + "</span></div></li>");
		}
		$("#msg").stop().animate({
			scrollTop : $('#msg')[0].scrollHeight
		}, 500);
	}
});