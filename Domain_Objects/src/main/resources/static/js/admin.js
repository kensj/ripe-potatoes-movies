$(document).ready(function() {
	hideAll();
	showReportQueue();
	$('.reportDescription').hide();
	$('option').click(function() {
		var clicked = $(this).attr('id');
		console.log(clicked);
		if (clicked.indexOf("Page") >= 0) {
			$('.adminCreatePage').hide();
			$('.adminDeletePage').hide();
			$('.adminEditPage').hide();
			$('div.' + clicked).show();
		}
		else if (clicked.indexOf("report") >= 0) {
			$('.reportDescription').hide();
			$('div#' + clicked).show();
		}
//		console.log($('div#' + clicked).text());
		
	});
});

$(document).on('click', "div.reportDescription > div > a", function() {
	var toResolve = $(this).attr('id');
	console.log(toResolve);
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajax({
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: "POST",
		url: "/resolve/" + toResolve,
		cache: true,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(data) {
			console.log(data);
			if (data["success"] === "true") {
				//if server returns true, then remove the option and div
				$('option#report' + toResolve).remove();
				$('div#report' + toResolve).remove();
			}
			else {
				if (data["reason"] === "login") {
					$('h4#report' + toResolve).text("You are not logged in. Please login first.");
				}
				else if (data["reason"] === "permission") {
					$('h4#report' + toResolve).text("You do not have permission to do that.");
				}
				else if (data["reason"] === "exist") {
					$('h4#report' + toResolve).text("The report has already been resolved.");
					$('option#report' + toResolve).remove();
					$('div#report' + toResolve).remove();
				}
			}
		},
		error: function(e) {
			alert(e.responseText);
		}
	});
});

function hideAll() {
	$(".reportQueueContainer").hide();
	$(".manageUsersContainer").hide();
	$(".managePagesContainer").hide();
	$(".manageRatingsContainer").hide();
	$(".manageReviewsContainer").hide();
	$('.adminDeletePage').hide();
	$('.adminEditPage').hide();
}


function showReportQueue() {
	hideAll();
	$(".reportQueueContainer").show();
	$(".adminHeader").text("Report Queue");
}

function showManageUsers() {
	hideAll();
	$(".manageUsersContainer").show();
	$(".adminHeader").text("Manage Users");
}

function showManagePages() {
	hideAll();
	$(".managePagesContainer").show();
	$(".adminHeader").text("Manage Pages");
}

function showManageReviews() {
	hideAll();
	$(".manageReviewsContainer").show();
	$(".adminHeader").text("Manage Reviews");
}

function showManageRatings() {
	hideAll();
	$(".manageRatingsContainer").show();
	$(".adminHeader").text("Manage Ratings");
}
