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
		else if (clicked.indexOf("User") >= 0) {
			$('.adminDeleteUser').hide();
			$('div.' + clicked).show();
		}
//		console.log($('div#' + clicked).text());
		
	});
});

$(document).on('click', "[name='deleteUserButton']", function() {
	var idToDelete = $('.adminDeleteUserForm').val()
	console.log(idToDelete);
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajax({
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: "DELETE",
		url: "/deleteUser?id=" + idToDelete,
		cache: true,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(data) {
			console.log(data);
			if (data["success"] === "true") {
				//if server returns true, then remove the option and div
				$('#adminDeleteUserMessage').text("Successfully deleted user " + data["name"]);
			}
			else {
				if (data["reason"] === "login") {
					$('#adminDeleteUserMessage').text("You are not logged in. Please login first.");
				}
				else if (data["reason"] === "permission") {
					$('#adminDeleteUserMessage').text("You do not have permission to do that.");
				}
				else if (data["reason"] === "exist") {
					$('#adminDeleteUserMessage').text("There is no user with that ID");
				}
			}
		},
		error: function(e) {
			alert(e.responseText);
		}
	});
})

$(document).on('click', "[name='createPageButton']", function() {
	var typeToMake = $("input[name='createType']:checked").val();
	var titleToGive = $('.adminCreateForm').val();
	var idToGive = $('.adminCreateForm2').val();
	console.log(titleToGive);
	console.log(typeToMake);
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajax({
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: "POST",
		url: "/createPage?type=" + typeToMake + "&title=" + titleToGive + "&id=" + idToGive,
		cache: true,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(data) {
			console.log(data);
			if (data["success"] === "true") {
				//if server returns true, then remove the option and div
				$('#adminCreateMessage').text("Success!");
			}
			else {
				if (data["reason"] === "login") {
					$('#adminCreateMessage').text("You are not logged in. Please login first.");
				}
				else if (data["reason"] === "permission") {
					$('#adminCreateMessage').text("You do not have permission to do that.");
				}
				else if (data["reason"] === "exist") {
					$('#adminCreateMessage').text("A page with that ID already exists.");
				}
				else if (data["reason"] === "type") {
					$('#adminCreateMessage').text("The type of content was not selected.");
				}
			}
		},
		error: function(e) {
			alert(e.responseText);
		}
	});
});

$(document).on('click', "[name='deletePageButton']", function() {
	var pageToDelete = $('.adminDeletePageForm').val();
	console.log(pageToDelete);
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajax({
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: "DELETE",
		url: "/deletePage?id=" + pageToDelete,
		cache: true,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(data) {
			console.log(data);
			if (data["success"] === "true") {
				//if server returns true, then remove the option and div
				$('#adminDeleteMessage').text("Successfully deleted page " + data["title"]);
			}
			else {
				if (data["reason"] === "login") {
					$('#adminDeleteMessage').text("You are not logged in. Please login first.");
				}
				else if (data["reason"] === "permission") {
					$('#adminDeleteMessage').text("You do not have permission to do that.");
				}
				else if (data["reason"] === "exist") {
					$('#adminDeleteMessage').text("There is no page with that ID");
				}
			}
		},
		error: function(e) {
			alert(e.responseText);
		}
	});
});

$(document).on('click', "[name='addCastButton']", function() {
	$("#mediaCast").append('<li class="list-group-item"><input><button class="btn-info loginButton" type="button" name="deleteCastButton"><span class="glyphicon glyphicon-remove"></span></button></li>');
});

$(document).on('click', "[name='addEpisodeButton']", function() {
	$("#tvEpisodes").append('<li class="list-group-item"><input placeholder="Episode #"><br><input placeholder="Season #"><br><textarea maxlength="255" rows="3" cols="35">Episode Synopsis</textarea><br><button class="btn-info loginButton" type="button" name="deleteEpisodeButton"><span class="glyphicon glyphicon-remove"></span></button></li>');
});

$(document).on('click', "[name='addSeasonButton']", function() {
	$("#tvSeasons").append('<li class="list-group-item"><div class="form-group"><input placeholder="Season Number"><textarea maxlength="255" rows="3" cols="40">Synopsis</textarea><button class="btn-info loginButton" type="button" name="deleteSeasonButton"><span class="glyphicon glyphicon-remove"></span></button></div></li>');
});

$(document).on('click', "[name='deleteCastButton']", function() {
	$(this).parent().remove();
});

$(document).on('click', "[name='deleteEpisodeButton']", function() {
	$(this).parent().remove();
});

$(document).on('click', "[name='deleteSeasonButton']", function() {
	$(this).parent().parent().remove();
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
