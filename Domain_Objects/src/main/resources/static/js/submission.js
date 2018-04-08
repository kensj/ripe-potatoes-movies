$(document). ready(function() {
	$("#submitReview").submit(function(event) {
		event.preventDefault();
		submitReview();
	});
});

function deleteReview() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var movieId = $("meta[name='movie_id']").attr("content");
	$.ajax({
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: "DELETE",
		url: "/content/" + movieId + "/delete-review",
		cache: false,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(data) {
			console.log(data);
			window.location.reload();
		},
		error: function(e) {
			alert(e.responseText);
		}
	});
}

function submitReview() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var movieId = $("meta[name='movie_id']").attr("content");
	$.ajax({
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: "POST",
		url: "/content/" + movieId + "/add-review?justificationText=" + $('#reviewSubmit').val(),
		cache: false,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(data) {
			console.log(data);
			if (data["success"] === "true") {
				window.location.reload();
			}
			else {
				if (data["reason"] === "login") {
					$("#reviewError").text("You are not logged in. Please login to submit a review.");
				}
			}
		},
		error: function(e) {
			alert(e.responseText);
		}
	});
}