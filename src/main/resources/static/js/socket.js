/**
 * Created by IOriens on 23/10/2016.
 */

var Socket = function () {
	this.stompClient = null
	this.socketURI = "http://localhost:8080/ws-chess"
}

Socket.prototype.setConnected = function (connected) {
	$('#connect').prop("disabled", connected)
	$('#disconnect').prop("disabled", !connected)
	if (connected) {
		$("#conversation").hide()
	} else {
		$("#conversation").hide()
	}
	$("#greetings").html("")
}

Socket.prototype.connect = function (cb) {
	var socket = new SockJS(this.socketURI)
	this.stompClient = Stomp.over(socket)
	var that = this
	this.stompClient.connect({}, function (frame) {
		that.setConnected(true)
		console.log('Connected: ' + frame)

		for (let i in cb) {
			// console.log(i)
			that.stompClient.subscribe('/topic/' + i , function (data) {
				cb[i](JSON.parse(data.body).content)
			})
		}
		// that.stompClient.subscribe('/topic/playing', function (data) {
		// 	cb["aiPlay"](JSON.parse(data.body).content)
		// })

		// that.stompClient.subscribe('/topic/init', function (data) {
		// 	cb["init"](JSON.parse(data.body).content)
		// })

		// that.stompClient.subscribe('/topic/init', function (data) {
		// 	cb["init"](JSON.parse(data.body).content)
		// })
	})
}

Socket.prototype.disconnect = function () {
	if (stompClient !== null) {
		this.stompClient.disconnect()
	}

	this.setConnected(false)
	console.log("Disconnected")
}

Socket.prototype.sendMessage = function (des, msg) {
	this.stompClient.send(des, {}, JSON.stringify(msg))
}

// Socket.prototype.showResponse = function(message) {
// $("#greetings").append("<tr><td>" + message + "</td></tr>");
// console.log(message)
// }

//
// $(function () {
//
// $("form").on('submit', function (e) {
// e.preventDefault()
// })
//
// $("#connect").click(function () {
// connect()
// })
//
// $("#disconnect").click(function () {
// disconnect()
// })
//
// $("#send").click(function() {
// sendMessage()
// })
// })