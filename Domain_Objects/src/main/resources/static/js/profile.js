$(document).ready(function() {
	$("#deleteWarning").hide();
	$(".rrContainer").hide();
	$(".wishlistContainer").hide();
	$(".followingContainer").hide();
	$(".followedContainer").hide();
});

$('[rel="tooltip"]').tooltip({
	animated: 'fade',
	placement: 'top',
	html:true
});
$(function() {
	$('[rel="popover"]').popover({
		animated:'fade',
		placement: 'top',
		html:true
	});
})
function openM() {
	$("#forgotFrame").hide();
  $("#registerFrame").hide();
  $("#loginFrame").hide();
	$("#manageAccount").show();
	document.getElementById('id01').style.display='block';
}
$(".deleteAccount").click(function() {
	if ($("#deleteWarning").is(":visible")) {
		alert("delete here");
	}
	else {
		$("#deleteWarning").show();
	}
});

function showRR() {
	$(".rrContainer").show();
	$(".wishlistContainer").hide();
	$(".followingContainer").hide();
	$(".followedContainer").hide();
}

function showWishlist() {
	$(".rrContainer").hide();
	$(".wishlistContainer").show();
	$(".followingContainer").hide();
	$(".followedContainer").hide();
}

function showFollowing() {
	$(".rrContainer").hide();
	$(".wishlistContainer").hide();
	$(".followingContainer").show();
	$(".followedContainer").hide();
}

function showFollowed() {
	$(".rrContainer").hide();
	$(".wishlistContainer").hide();
	$(".followingContainer").hide();
	$(".followedContainer").show();
}
