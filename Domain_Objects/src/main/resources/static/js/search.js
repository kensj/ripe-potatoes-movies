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

$(".sortFilter3").click(function() {
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
$(".sortFilter4").click(function() {
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
    if(e.keyCode == 13)
    {
    	window.location = "search?searchBar=" + $('#searchbox').val()
    }
});