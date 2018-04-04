$(document). ready(function() {
	$("#registerFrame").submit(function(event) {
		event.preventDefault();
		sendRegisterRequest();
	});
});

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
		},
		error: function(e) {
			alert(e.responseText);
		}
	});
}