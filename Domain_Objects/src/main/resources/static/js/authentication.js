$(document). ready(function() {
	$("#registerFrame").submit(function(event) {
		event.preventDefault();
		sendRegisterRequest();
	});
	$("#loginFrame").submit(function(event) {
		event.preventDefault();
		sendLoginRequest();
	});
});

function sendLoginRequest() {
	var User = new Object();
	User.name = $("input[name=loginName]").val();
	User.password = $("input[name=loginPW]").val();
	User.email = "";
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajax({
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: "POST",
		url: "/login",
		data: JSON.stringify(User),
		dataType: 'json',
		cache: false,
		timeout: 600000,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(data) {
			console.log(data);
			if (data["success"] === "true") {
				window.location.reload();
			}
			else {
				if (data["reason"] === "username") {
					$("#errorMsg2").text("Incorrect user or password");
				}
				else if (data["reason"] === "password") {
					$("#errorMsg2").text("Incorrect user or password");
				}
			}
		},
		error: function(e) {
			alert(e.responseText);
		}
	});
}

function sendRegisterRequest() {
	var User = new Object();
	User.name = $("input[name=registerName]").val();
	User.email = $("input[name=registerEmail]").val();
	User.password = $("input[name=registerPW]").val();
	User.confirmPassword = $("input[name=retypePW]").val();
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajax({
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: "POST",
		url: "/register",
		data: JSON.stringify(User),
		dataType: 'json',
		cache: false,
		timeout: 600000,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(data) {
			console.log(data);
//			var result = JSON.parse(data);
			if (data["success"] === "true") {
				window.location.reload();
			}
			else {
				if (data["reason"] === "username") {
					$("#errorMsg3").text("Username is taken");
				}
				else if (data["reason"] === "email") {
					$("#errorMsg3").text("Email is taken");
				}
				else if (data["reason"] === "password") {
					$("#errorMsg3").text("Password and confirm password don't match");
				}
			}
		},
		error: function(e) {
			alert(e.responseText);
		}
	});
}

function sendLogoutRequest() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajax({
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: "GET",
		url: "/logout",
		cache: false,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(data) {
			console.log(data);
		},
		error: function(e) {
			alert(e.responseText);
		}
	});
}

$( ".reverifySubmit" ).click(function() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
    	headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: 'POST',
        url: '/reverify?email=' + $("#emailInput").val().trim(),
        cache: false,
        beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
        success: function (data) {
        	if(data.success == "true") {
        		alert("Email sent")
        	}
        	else {
        		alert("Email failed to send\nReason: " + data.reason);
        	}
        	
        },
        error: function (response) {
        	alert("Email failed to send");
        }
    });
});

function requestNewPass() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajax({
    	headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: 'POST',
        url: '/requestnewpass?email=' + $('#forgotPasswordInput').val(),
        cache: false,
        beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
        success: function (response) {
        	if(response.success == "true") {
        		alert("Password Requested");
        	}
        	else {
        		alert("Error")
        	}
        },
        error: function (jQXHR, textStatus, errorThrown) {
        	console.log("An error occurred whilst trying to contact the server: " + jQXHR.status + " " + textStatus + " " + errorThrown);
        }
    });
}
