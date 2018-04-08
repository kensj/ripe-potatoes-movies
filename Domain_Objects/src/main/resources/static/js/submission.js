$(document). ready(function() {
	$("#submitReview").submit(function(event) {
		event.preventDefault();
		submitReview();
	});
});

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
		},
		error: function(e) {
			alert(e.responseText);
		}
	});
}