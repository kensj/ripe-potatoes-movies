$(document). ready(function() {
	$("#submitReview").submit(function(event) {
		event.preventDefault();
		submitReview();
	});
});

function deleteReview() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var movieId = $("meta[name='content_id']").attr("content");
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
	var movieId = $("meta[name='content_id']").attr("content");
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

$(document).on('click', "[name='submitRButton']", function() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var movieId = $("meta[name='content_id']").attr("content");
	var msgIdentifier = $(this).val();
	var test = $(this).siblings("textarea").val();
	console.log(test);
	$.ajax({
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: "POST",
		url: "/content/" + movieId + "/report?reviewID=" + $(this).val() + "&" + "description=" + $(this).siblings("textarea").val(),
		cache: true,
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
					$("#report" + msgIdentifier).text("You are not logged in. Please login to report a review.");
				}
				else if (data["reason"] === "repeat") {
					$("#report" + msgIdentifier).text("You have already reported this review.");
				}
				else if (data["reason"] === "unknown") {
					$("#report" + msgIdentifier).text("An unknown error occurred.");
				}
			}
		},
		error: function(e) {
			alert(e.responseText);
		}
	});
});

$(document).on('click', "[name='adminDeleteReview']", function() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var movieId = $("meta[name='content_id']").attr("content");
	var toDelete = $(this).val();
	console.log(toDelete);
	$.ajax({
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: "DELETE",
		url: "/delete/review?reviewID=" + toDelete,
		cache: true,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(data) {
			console.log(data);
			if (data["success"] === "true") {
				$('#review' + toDelete).remove();
			}
			else {
				if (data["reason"] === "login") {
					$("#adminMessage" + toDelete).text("You are not logged in. Please login to report a review.");
				}
				else if (data["reason"] === "repeat") {
					$("#adminMessage" + toDelete).text("This review has already been deleted");
				}
				else if (data["reason"] === "unknown") {
					$("#adminMessage" + toDelete).text("An unknown error occurred.");
				}
			}
		},
		error: function(e) {
			alert(e.responseText);
		}
	});
});

$("#removeRating").click(function() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var movieId = $("meta[name='content_id']").attr("content");
	$.ajax({
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: "DELETE",
		url: "/content/" + movieId + "/delete-rating",
		cache: false,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(data) {
			console.log(data);
			if (data["success"] === "true") {
				$("#ownRating").rating('rate',0);
			}
			else {
				if (data["reason"] === "login") {
					$("#ratingError").text("You are not logged in. Please login to submit a rating.");
				}
				else if (data["reason"] === "invalid") {
					$("#ratingError").text("Invalid rating entered");
				}
				else {
					$("#ratingError").text("An error occurred");
				}
			}
		},
		error: function(e) {
			alert(e.responseText);
		}
	});	
});

$("#ownRating").on('change', function() {
//	alert('Rating: ' + $(this).val());
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var movieId = $("meta[name='content_id']").attr("content");
	$.ajax({
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: "POST",
		url: "/content/" + movieId + "/add-rating?rating=" + $(this).val(),
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
					$("#ratingError").text("You are not logged in. Please login to submit a rating.");
				}
				if (data["reason"] === "invalid") {
					$("#ratingError").text("Invalid rating entered");
				}
			}
		},
		error: function(e) {
			alert(e.responseText);
		}
	});
});