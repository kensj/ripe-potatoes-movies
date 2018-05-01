$(document).ready(function() {
	hideAll();
	showReportQueue();
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
}

function showManageUsers() {
	hideAll();
	$(".manageUsersContainer").show();
}

function showManagePages() {
	hideAll();
	$(".managePagesContainer").show();
}

function showManageReviews() {
	hideAll();
	$(".manageReviewsContainer").show();
}

function showManageRatings() {
	hideAll();
	$(".manageRatingsContainer").show();
}
