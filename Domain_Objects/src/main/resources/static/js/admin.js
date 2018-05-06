$(document).ready(function() {
	hideAll();
	showReportQueue();
	$('.reportDescription').hide();
	$('option').click(function() {
		var clicked = $(this).attr('id');
//		console.log(clicked);
		$('.reportDescription').hide();
//		console.log($('div#' + clicked).text());
		$('div#' + clicked).show();
	});
});

function hideAll() {
	$(".reportQueueContainer").hide();
	$(".manageUsersContainer").hide();
	$(".managePagesContainer").hide();
	$(".manageRatingsContainer").hide();
	$(".manageReviewsContainer").hide();
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
