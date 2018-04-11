//$(".sortFilter3").click(function() {
//	var $wrapper = $('.resultsHolder');
//	$wrapper.find('.searchResult').sort(function(a,b) {
//		return $(a).find("h4").find("a").text() > $(b).find("h4").find("a").text();
//	}).appendTo($wrapper);
//});

var sortNameState = 0;
var sortRatingState = 0;
var sortContentState = 'All';
var sortRatingState = 'All';

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
	var resultRating = parent.children("div").children("h5")
	sortContentState = 'TV'
	$(".contentB").get(0).firstChild.nodeValue = 'Content - TV'
	for(var i = 0; i < resultChildren.length; i++) {
		if(resultChildren.get(i).className.toString() != "resultTV") {
			resultChildren.parent("div").get(i).style.display = "none";
		}
		else {
			if(sortRatingState == '5') {
				if(resultRating.get(i).innerText.startsWith("5")) {
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortRatingState == '4') { 
				if(resultRating.get(i).innerText.startsWith("4")) { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortRatingState == '3') { 
				if(resultRating.get(i).innerText.startsWith("3")) { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortRatingState == '2') { 
				if(resultRating.get(i).innerText.startsWith("2")) { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortRatingState == '1') { 
				if(resultRating.get(i).innerText.startsWith("1")) { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortRatingState == 'Unr') {
				if(resultRating.get(i).innerText.startsWith("N")) { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortRatingState == 'All') { 
				resultChildren.parent("div").get(i).style.display = "block";
			}
		}
	}	
});
$(".sortFilterMovie").click(function() {
	var parent = $('.resultsHolder');
	var resultChildren = parent.children("div").children("p")
	var resultRating = parent.children("div").children("h5")
	sortContentState = 'Movie'
	$(".contentB").get(0).firstChild.nodeValue = 'Content - Movies'
	for(var i = 0; i < resultChildren.length; i++) {
		if(resultChildren.get(i).className.toString() != "resultFilm") {
			resultChildren.parent("div").get(i).style.display = "none";
		}
		else {
			if(sortRatingState == '5') {
				if(resultRating.get(i).innerText.startsWith("5")) {
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortRatingState == '4') { 
				if(resultRating.get(i).innerText.startsWith("4")) { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortRatingState == '3') { 
				if(resultRating.get(i).innerText.startsWith("3")) { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortRatingState == '2') { 
				if(resultRating.get(i).innerText.startsWith("2")) { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortRatingState == '1') { 
				if(resultRating.get(i).innerText.startsWith("1")) { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortRatingState == 'Unr') {
				if(resultRating.get(i).innerText.startsWith("N")) { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortRatingState == 'All') { 
				resultChildren.parent("div").get(i).style.display = "block";
			}
		}
	}
});
$(".sortFilterCeleb").click(function() {
	var parent = $('.resultsHolder');
	var resultChildren = parent.children("div").children("p")
	var resultRating = parent.children("div").children("h5")
	sortContentState = 'Celeb'
	$(".contentB").get(0).firstChild.nodeValue = 'Content - Celebrities'
	for(var i = 0; i < resultChildren.length; i++) {
		if(resultChildren.get(i).className.toString() != "resultCeleb") {
			resultChildren.parent("div").get(i).style.display = "none";
		}
		else {
			if(sortRatingState == '5') {
				if(resultRating.get(i).innerText.startsWith("5")) {
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortRatingState == '4') { 
				if(resultRating.get(i).innerText.startsWith("4")) { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortRatingState == '3') { 
				if(resultRating.get(i).innerText.startsWith("3")) { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortRatingState == '2') { 
				if(resultRating.get(i).innerText.startsWith("2")) { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortRatingState == '1') { 
				if(resultRating.get(i).innerText.startsWith("1")) { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortRatingState == 'Unr') {
				if(resultRating.get(i).innerText.startsWith("N")) { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortRatingState == 'All') { 
				resultChildren.parent("div").get(i).style.display = "block";
			}
		}
	}
});
$(".sortFilterAll").click(function() {
	var parent = $('.resultsHolder');
	var resultChildren = parent.children("div").children("p")
	var resultRating = parent.children("div").children("h5")
	sortContentState = 'All'
	$(".contentB").get(0).firstChild.nodeValue = 'Content'
	for(var i = 0; i < resultChildren.length; i++) {
		if(sortRatingState == '5') {
			if(resultRating.get(i).innerText.startsWith("5")) {
				resultChildren.parent("div").get(i).style.display = "block";
			}
			else { 
				resultChildren.parent("div").get(i).style.display = "none";
			}
		}
		if(sortRatingState == '4') { 
			if(resultRating.get(i).innerText.startsWith("4")) { 
				resultChildren.parent("div").get(i).style.display = "block";
			}
			else { 
				resultChildren.parent("div").get(i).style.display = "none";
			}
		}
		if(sortRatingState == '3') { 
			if(resultRating.get(i).innerText.startsWith("3")) { 
				resultChildren.parent("div").get(i).style.display = "block";
			}
			else { 
				resultChildren.parent("div").get(i).style.display = "none";
			}
		}
		if(sortRatingState == '2') { 
			if(resultRating.get(i).innerText.startsWith("2")) { 
				resultChildren.parent("div").get(i).style.display = "block";
			}
			else { 
				resultChildren.parent("div").get(i).style.display = "none";
			}
		}
		if(sortRatingState == '1') { 
			if(resultRating.get(i).innerText.startsWith("1")) { 
				resultChildren.parent("div").get(i).style.display = "block";
			}
			else { 
				resultChildren.parent("div").get(i).style.display = "none";
			}
		}
		if(sortRatingState == 'Unr') {
			if(resultRating.get(i).innerText.startsWith("N")) { 
				resultChildren.parent("div").get(i).style.display = "block";
			}
			else { 
				resultChildren.parent("div").get(i).style.display = "none";
			}
		}
		if(sortRatingState == 'All') { 
			resultChildren.parent("div").get(i).style.display = "block";
		}
	}	
});
$(".sortRating5").click(function() {
	var parent = $('.resultsHolder');
	var resultChildren = parent.children("div").children("h5")
	var resultContent = parent.children("div").children("p")
	sortRatingState = '5'
	$(".ratingB").get(0).firstChild.nodeValue = 'Rating - 5'
	for(var i = 0; i < resultChildren.length; i++) {
		if(!resultChildren.get(i).innerText.startsWith("5")) {
			resultChildren.parent("div").get(i).style.display = "none";
		}
		else {
			if(sortContentState == 'TV') {
				if(resultContent.get(i).className.toString() == "resultTV") { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortContentState == 'Movie') { 
				if(resultContent.get(i).className.toString() == "resultFilm") { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortContentState == 'Celeb') { 
				if(resultContent.get(i).className.toString() == "resultCeleb") { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortContentState == 'All') { 
				resultChildren.parent("div").get(i).style.display = "block";
			}
		}
	}
});
$(".sortRating4").click(function() {
	var parent = $('.resultsHolder');
	var resultChildren = parent.children("div").children("h5")
	var resultContent = parent.children("div").children("p")
	sortRatingState = '4'
	$(".ratingB").get(0).firstChild.nodeValue = 'Rating - 4'
	for(var i = 0; i < resultChildren.length; i++) {
		if(!resultChildren.get(i).innerText.startsWith("4")) {
			resultChildren.parent("div").get(i).style.display = "none";
		}
		else {
			if(sortContentState == 'TV') {
				if(resultContent.get(i).className.toString() == "resultTV") { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortContentState == 'Movie') { 
				if(resultContent.get(i).className.toString() == "resultFilm") { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortContentState == 'Celeb') { 
				if(resultContent.get(i).className.toString() == "resultCeleb") { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortContentState == 'All') { 
				resultChildren.parent("div").get(i).style.display = "block";
			}
		}
	}
});
$(".sortRating3").click(function() {
	var parent = $('.resultsHolder');
	var resultChildren = parent.children("div").children("h5")
	var resultContent = parent.children("div").children("p")
	sortRatingState = '3'
	$(".ratingB").get(0).firstChild.nodeValue = 'Rating - 3'
	for(var i = 0; i < resultChildren.length; i++) {
		if(!resultChildren.get(i).innerText.startsWith("3")) {
			resultChildren.parent("div").get(i).style.display = "none";
		}
		else {
			if(sortContentState == 'TV') {
				if(resultContent.get(i).className.toString() == "resultTV") { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortContentState == 'Movie') { 
				if(resultContent.get(i).className.toString() == "resultFilm") { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortContentState == 'Celeb') { 
				if(resultContent.get(i).className.toString() == "resultCeleb") { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortContentState == 'All') { 
				resultChildren.parent("div").get(i).style.display = "block";
			}
		}
	}
});
$(".sortRating2").click(function() {
	var parent = $('.resultsHolder');
	var resultChildren = parent.children("div").children("h5")
	var resultContent = parent.children("div").children("p")
	sortRatingState = '2'
		$(".ratingB").get(0).firstChild.nodeValue = 'Rating - 2'
	for(var i = 0; i < resultChildren.length; i++) {
		if(!resultChildren.get(i).innerText.startsWith("2")) {
			resultChildren.parent("div").get(i).style.display = "none";
		}
		else {
			if(sortContentState == 'TV') {
				if(resultContent.get(i).className.toString() == "resultTV") { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortContentState == 'Movie') { 
				if(resultContent.get(i).className.toString() == "resultFilm") { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortContentState == 'Celeb') { 
				if(resultContent.get(i).className.toString() == "resultCeleb") { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortContentState == 'All') { 
				resultChildren.parent("div").get(i).style.display = "block";
			}
		}
	}
});
$(".sortRating1").click(function() {
	var parent = $('.resultsHolder');
	var resultChildren = parent.children("div").children("h5")
	var resultContent = parent.children("div").children("p")
	sortRatingState = '1'
	$(".ratingB").get(0).firstChild.nodeValue = 'Rating - 1'
	for(var i = 0; i < resultChildren.length; i++) {
		if(!resultChildren.get(i).innerText.startsWith("1")) {
			resultChildren.parent("div").get(i).style.display = "none";
		}
		else {
			if(sortContentState == 'TV') {
				if(resultContent.get(i).className.toString() == "resultTV") { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortContentState == 'Movie') { 
				if(resultContent.get(i).className.toString() == "resultFilm") { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortContentState == 'Celeb') { 
				if(resultContent.get(i).className.toString() == "resultCeleb") { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortContentState == 'All') { 
				resultChildren.parent("div").get(i).style.display = "block";
			}
		}
	}
});
$(".sortRatingUnr").click(function() {
	var parent = $('.resultsHolder');
	var resultChildren = parent.children("div").children("h5")
	var resultContent = parent.children("div").children("p")
	sortRatingState = 'Unr'
		$(".ratingB").get(0).firstChild.nodeValue = 'Rating - Unrated'
	for(var i = 0; i < resultChildren.length; i++) {
		if(!resultChildren.get(i).innerText.startsWith("Not")) {
			resultChildren.parent("div").get(i).style.display = "none";
		}
		else {
			if(sortContentState == 'TV') {
				if(resultContent.get(i).className.toString() == "resultTV") { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortContentState == 'Movie') { 
				if(resultContent.get(i).className.toString() == "resultFilm") { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortContentState == 'Celeb') { 
				if(resultContent.get(i).className.toString() == "resultCeleb") { 
					resultChildren.parent("div").get(i).style.display = "block";
				}
				else { 
					resultChildren.parent("div").get(i).style.display = "none";
				}
			}
			if(sortContentState == 'All') { 
				resultChildren.parent("div").get(i).style.display = "block";
			}
		}
	}
});
$(".sortRatingAll").click(function() {
	var parent = $('.resultsHolder');
	var resultChildren = parent.children("div").children("h5")
	var resultContent = parent.children("div").children("p")
	sortRatingState = 'All'
	$(".ratingB").get(0).firstChild.nodeValue = 'Rating'
	for(var i = 0; i < resultChildren.length; i++) {
		if(sortContentState == 'TV') {
			if(resultContent.get(i).className.toString() == "resultTV") { 
				resultChildren.parent("div").get(i).style.display = "block";
			}
			else { 
				resultChildren.parent("div").get(i).style.display = "none";
			}
		}
		if(sortContentState == 'Movie') { 
			if(resultContent.get(i).className.toString() == "resultFilm") { 
				resultChildren.parent("div").get(i).style.display = "block";
			}
			else { 
				resultChildren.parent("div").get(i).style.display = "none";
			}
		}
		if(sortContentState == 'Celeb') { 
			if(resultContent.get(i).className.toString() == "resultCeleb") { 
				resultChildren.parent("div").get(i).style.display = "block";
			}
			else { 
				resultChildren.parent("div").get(i).style.display = "none";
			}
		}
		if(sortContentState == 'All') { 
			resultChildren.parent("div").get(i).style.display = "block";
		}
	}
});
$(".sortFilterName").click(function() {
	var parent = $('.resultsHolder');
	if (sortNameState == 0) {
		$(".sortB").get(0).firstChild.nodeValue = 'Sort - Name (A-Z)'
		nestedSort(parent, "div", "h4");
		sortNameState = 1;
	}
	else {
		$(".sortB").get(0).firstChild.nodeValue = 'Sort - Name (Z-A)'
		nestedSortReverse(parent, "div", "h4");
		sortNameState = 0;
	}
});
$(".sortFilterRating").click(function() {
	var parent = $('.resultsHolder');
	if (sortRatingState == 0) {
		$(".sortB").get(0).firstChild.nodeValue = 'Sort - Rating (Highest)'
		nestedSort(parent, "div", "h5");
		sortRatingState = 1;
	}
	else {
		$(".sortB").get(0).firstChild.nodeValue = 'Sort - Rating (Lowest)'
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
