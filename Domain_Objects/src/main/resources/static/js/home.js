$(document).ready(function() {
	$(".recommendedContainer").hide();
	$(".calendarContainer").hide();
	populateCalendar();
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
	
	for (var i = 0; i < startDay; i++) {
		$(".days").append("<li></li>");
	}
	
	for (var i = 0; i < numDays; i++) {
		$(".days").append("<li>" + (i + 1) + "</li>");
	}
	
}

