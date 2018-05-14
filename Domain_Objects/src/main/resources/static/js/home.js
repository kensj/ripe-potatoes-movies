$(document).ready(function() {
	$(".recommendedContainer").hide();
	$(".calendarContainer").hide();
	populateCalendar();
	fillCalendar(showTooltips);
});

$('.carousel[data-type="multi"] .item').each(function() {
	var next = $(this).next();
	if (!next.length) {
		next = $(this).siblings(':first');
	}
	next.children(':first-child').clone().appendTo($(this));

	for (var i = 0; i < 2; i++) {
		next = next.next();
		if (!next.length) {
			next = $(this).siblings(':first');
		}

		next.children(':first-child').clone().appendTo($(this));
	}
});

function showTooltips() {
	$('[data-toggle="tooltip"]').tooltip({
		trigger: 'hover click'
	});
}

function showCalendar() {
	$(".statsContainer").hide();
	$(".recommendedContainer").hide();
	$(".calendarContainer").show();
}

function showStats() {
	$(".recommendedContainer").hide();
	$(".calendarContainer").hide();
	$(".statsContainer").show();
}

function showRecommended() {
	$(".calendarContainer").hide();
	$(".statsContainer").hide();
	$(".recommendedContainer").show();
}

//generates the current month
function populateCalendar() {
	var date = new Date(),
	locale = "en-us",
	month = date.toLocaleString(locale, {month: "long"});
	//fill current month and year
	$(".calendarContainer > div > div > ul > li").html(month + "<br><span>" + date.getFullYear() + "</span>");
	
	var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
	var lastDay = new Date(date.getFullYear(), date.getMonth()+1, 0);
	var numDays = lastDay.getDate();
	var startDay = firstDay.getDay();
	var currentDay = date.getDate();
	
	for (var i = 0; i < startDay; i++) {
		$(".days").append("<li></li>");
	}
	
	for (var i = 0; i < numDays; i++) {
		$(".days").append("<li><span data-toggle='tooltip' data-html='true' title='' id='" + (i+1) + "'>" + (i+1) + "</span></li>");
		if (i == (currentDay - 1)) {
			$("#" + (i+1)).addClass('currentDay');
		} 
	}
	
}

function fillCalendar(callback) {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajax({
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: "GET",
		url: "/releaseCalendar",
		cache: false,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(data) {
			for (var p in data) {
				if (!data.hasOwnProperty(p)) {
					continue;
				}
//				console.log(data[p][0]);
//				console.log(data[p][1]);
//				console.log(p);
//				console.log($("#"+data[p][1]));
				var movie = $("#" + data[p][1]).attr('title');
				$("#" + data[p][1]).attr('title', movie + '<br>' + p);
				$("#" + data[p][1]).addClass('active');
			}
			callback();
		},
		error: function(e) {
			alert(e.responseText);
		}
	});
}

function requestNewPass() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajax({
    	headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: 'POST',
        url: '/requestnewpass?email=' + $('#forgotPasswordInput').val(),
        cache: false,
        beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
        success: function (response) {
        	if(response.success == "true") {
        		alert("Password Requested");
        	}
        	else {
        		alert("Error")
        	}
        },
        error: function (jQXHR, textStatus, errorThrown) {
        	console.log("An error occurred whilst trying to contact the server: " + jQXHR.status + " " + textStatus + " " + errorThrown);
        }
    });
}