//$(".sortFilter3").click(function() {
//	var $wrapper = $('.resultsHolder');
//	$wrapper.find('.searchResult').sort(function(a,b) {
//		return $(a).find("h4").find("a").text() > $(b).find("h4").find("a").text();
//	}).appendTo($wrapper);
//});

var sortNameState = 0;
var sortRatingState = 0;

function nestedSort(parent, childSelector, keySelector) {
	var items = parent.children(childSelector).sort(function(a,b) {
		var vA = $(keySelector, a).text();
		var vB = $(keySelector, b).text();
		return (vA < vB) ? -1 : (vA > vB) ? 1 : 0;
	});
	parent.append(items);
}

function nestedSortReverse(parent, childSelector, keySelector) {
	var items = parent.children(childSelector).sort(function(a,b) {
		var vA = $(keySelector, a).text();
		var vB = $(keySelector, b).text();
		return (vA > vB) ? -1 : (vA < vB) ? 1 : 0;
	});
	parent.append(items);
}
$(".sortFilterTV").click(function() {
	var parent = $('.resultsHolder');
	var resultChildren = parent.children("div").children("p")
	for(var i = 0; i < resultChildren.length; i++) {
		if(resultChildren.get(i).className.toString() != "resultTV") {
			resultChildren.parent("div").get(i).style.display = "none";
		}
		else {
			resultChildren.parent("div").get(i).style.display = "block";
		}
	}
});
$(".sortFilterMovie").click(function() {
	var parent = $('.resultsHolder');
	var resultChildren = parent.children("div").children("p")
	for(var i = 0; i < resultChildren.length; i++) {
		if(resultChildren.get(i).className.toString() != "resultFilm") {
			resultChildren.parent("div").get(i).style.display = "none";
		}
		else {
			resultChildren.parent("div").get(i).style.display = "block";
		}
	}
});
$(".sortFilterCeleb").click(function() {
	var parent = $('.resultsHolder');
	var resultChildren = parent.children("div").children("p")
	for(var i = 0; i < resultChildren.length; i++) {
		if(resultChildren.get(i).className.toString() != "resultCeleb") {
			resultChildren.parent("div").get(i).style.display = "none";
		}
		else {
			resultChildren.parent("div").get(i).style.display = "block";
		}
	}
});
$(".sortFilterAll").click(function() {
	var parent = $('.resultsHolder');
	var resultChildren = parent.children("div").children("p")
	for(var i = 0; i < resultChildren.length; i++) {
		resultChildren.parent("div").get(i).style.display = "block";
	}
});
$(".sortRating5").click(function() {
	var parent = $('.resultsHolder');
	var resultChildren = parent.children("div").children("h5")
	for(var i = 0; i < resultChildren.length; i++) {
		if(!resultChildren.get(i).innerText.startsWith("5")) {
			resultChildren.parent("div").get(i).style.display = "none";
		}
		else {
			resultChildren.parent("div").get(i).style.display = "block";
		}
	}
});
$(".sortRating4").click(function() {
	var parent = $('.resultsHolder');
	var resultChildren = parent.children("div").children("h5")
	for(var i = 0; i < resultChildren.length; i++) {
		if(!resultChildren.get(i).innerText.startsWith("4")) {
			resultChildren.parent("div").get(i).style.display = "none";
		}
		else {
			resultChildren.parent("div").get(i).style.display = "block";
		}
	}
});
$(".sortRating3").click(function() {
	var parent = $('.resultsHolder');
	var resultChildren = parent.children("div").children("h5")
	for(var i = 0; i < resultChildren.length; i++) {
		if(!resultChildren.get(i).innerText.startsWith("3")) {
			resultChildren.parent("div").get(i).style.display = "none";
		}
		else {
			resultChildren.parent("div").get(i).style.display = "block";
		}
	}
});
$(".sortRating2").click(function() {
	var parent = $('.resultsHolder');
	var resultChildren = parent.children("div").children("h5")
	for(var i = 0; i < resultChildren.length; i++) {
		if(!resultChildren.get(i).innerText.startsWith("2")) {
			resultChildren.parent("div").get(i).style.display = "none";
		}
		else {
			resultChildren.parent("div").get(i).style.display = "block";
		}
	}
});
$(".sortRating1").click(function() {
	var parent = $('.resultsHolder');
	var resultChildren = parent.children("div").children("h5")
	for(var i = 0; i < resultChildren.length; i++) {
		if(!resultChildren.get(i).innerText.startsWith("1")) {
			resultChildren.parent("div").get(i).style.display = "none";
		}
		else {
			resultChildren.parent("div").get(i).style.display = "block";
		}
	}
});
$(".sortRatingUnr").click(function() {
	var parent = $('.resultsHolder');
	var resultChildren = parent.children("div").children("h5")
	for(var i = 0; i < resultChildren.length; i++) {
		if(!resultChildren.get(i).innerText.startsWith("Not")) {
			resultChildren.parent("div").get(i).style.display = "none";
		}
		else {
			resultChildren.parent("div").get(i).style.display = "block";
		}
	}
});
$(".sortRatingAll").click(function() {
	var parent = $('.resultsHolder');
	var resultChildren = parent.children("div").children("h5")
	for(var i = 0; i < resultChildren.length; i++) {
		resultChildren.parent("div").get(i).style.display = "block";
	}
});
$(".sortFilterName").click(function() {
	var parent = $('.resultsHolder');
	if (sortNameState == 0) {
		nestedSort(parent, "div", "h4");
		sortNameState = 1;
	}
	else {
		nestedSortReverse(parent, "div", "h4");
		sortNameState = 0;
	}
});
$(".sortFilterRating").click(function() {
	var parent = $('.resultsHolder');
	if (sortRatingState == 0) {
		nestedSort(parent, "div", "h5");
		sortRatingState = 1;
	}
	else {
		nestedSortReverse(parent, "div", "h5");
		sortRatingState = 0;
	}
});
$('#searchbox').keyup(function(e){
    if(e.keyCode == 13) {
    	console.log(window.location)
    	window.location = "search?searchBar=" + $('#searchbox').val()
    }
});
