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
	$('#adminEditFilmPane').hide();
	$('#adminEditTVPane').hide();
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

$(document).on('click', "[name='addSeasonButton']", function() {
	$("#tvSeasons").append('<li class="list-group-item"><div class="form-group"><input placeholder="Season Number"><textarea maxlength="255" rows="3" cols="40">Synopsis</textarea><button class="btn-info loginButton" type="button" name="deleteSeasonButton"><span class="glyphicon glyphicon-remove"></span></button></div></li>');
});

$(document).on('click', "[name='deleteCastButton']", function() {
	$(this).parent().remove();
});

$(document).on('click', "[name='deleteSeasonButton']", function() {
	$(this).parent().parent().remove();
});

function resetEditContent() {
	$('#tvEpisodes').empty();
	$('#tvSeasons').empty();
	$('#mediaCast').empty();
	$('#adminEditFilmPane').hide();
	$('#adminEditTVPane').hide();
}

$(document).on('click', "[name='getPageInfoButton']", function() {
	var toFetch = $('.adminEditGetId').val();
	console.log(toFetch);
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajax({
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: "GET",
		url: "/pageInfo?id=" + toFetch,
		cache: true,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(data) {
			console.log(data);
			resetEditContent();
			if (data["success"]) {
				if (data["reason"] === "permission") {
					$('#adminEditMessage').text("You do not have permission.");
				}
				else if (data["reason"] === "login") {
					$('#adminEditMessage').text("You are not logged in.");
				}
				else if (data["reason"] === "exist") {
					$('#adminEditMessage').text("There is no content with that ID");
				}
				else {
					$('#adminEditMessage').text("Unknown error");
				}
			}
			else {
				if (data["budget"]) {
					$('#adminEditFilmPane').show();
					$('#filmRevenue').val(data["revenue"]);
					$('#filmBudget').val(data["budget"]);
				}
				else if (data["network"]) {
					$('#adminEditTVPane').show();
					$('#tvNetwork').val(data["network"]);
					for(var i = 0; i < data["seasons"].length; i++) {
						$("#tvSeasons").append('<li class="list-group-item"><div class="form-group"><input placeholder="Season Number" value="' + i + '"><textarea maxlength="255" rows="3" cols="40">' + data["seasons"][i]["synopsis"] + '</textarea><button class="btn-info loginButton" type="button" name="deleteSeasonButton"><span class="glyphicon glyphicon-remove"></span></button></div></li>');
					}
				}
				$('#mediaName').val(data["name"]);
				$('#mediaSynopsis').val(data["synopsis"]);
				$('#mediaReleaseDate').val(data["releaseDate"]);
				for(var i = 0; i < data["cast"].length; i++) {
					$("#mediaCast").append('<li class="list-group-item"><input value="' + data["cast"][i] + '"><button class="btn-info loginButton" type="button" name="deleteCastButton"><span class="glyphicon glyphicon-remove"></span></button></li>');
				}
				$('[name="changePageButton"]').attr("value", toFetch);
			}
		},
		error: function(e) {
			alert(e.responseText);
		}
	});
});

$(document).on('click', "[name='changePageButton']", function() {
	var toChange = $(this).attr("value");
	if (!toChange) {
		return;
	}
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	
	var cast = [];
	$('#mediaCast > li > input').each(function() {
		cast.push($(this).val());
	});
	var name = $('#mediaName').val();
	var synopsis = $('#mediaSynopsis').val();
	var releaseDate = $('#mediaReleaseDate').val();
	var revenue = $('#filmRevenue').val();
	var budget = $('#filmBudget').val();
	var network = $('#tvNetwork').val();
	var seasons = [];
	$('#tvSeasons > li > div > textarea').each(function() {
		seasons.push($(this).val());
	});
	console.log(toChange);
	console.log(name);
	console.log(synopsis);
	console.log(releaseDate);
	console.log(cast);
	console.log(revenue);
	console.log(budget);
	console.log(network);
	console.log(seasons);
	$.ajax({
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/x-www-form-urlencoded',
		},
		type: "POST",
		url: "/changePage",
		data: {
			id : toChange,
			name : name,
			synopsis : synopsis,
			cast : cast,
			releaseDate : releaseDate,
			revenue : revenue,
			budget : budget,
			network : network,
			seasons : seasons
		},
		cache: true,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(data) {
			console.log(data);
			if (data["success"] === "true") {
				//if server returns true, then remove the option and div
				$('#adminEditMessage').text("Success!");
			}
			else {
				if (data["reason"] === "login") {
					$('#adminEditMessage').text("You are not logged in. Please login first.");
				}
				else if (data["reason"] === "permission") {
					$('#adminEditMessage').text("You do not have permission to do that.");
				}
				else if (data["reason"] === "exist") {
					$('#adminEditMessage').text("The report has already been resolved.");
				}
				else if (data["reason"] === "number") {
					$('#adminEditMessage').text("Invalid number entered.");
				}
				else if (data["reason"] === "date") {
					$('#adminEditMessage').text("Invalid date entered. Format: YYYY-MM-DD");
				}
			}
		},
		error: function(e) {
			alert(e.responseText);
		}
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
