$( document ).ready(function() {
	var url = '/trailers/'+ $("meta[name='content_id']").get(0).content.toString() + '.mp4';
	$.get(url).done(function() { 
		$('#trailerElement').get(0).style.display = "block";
		$('#loadingTrailer').get(0).style.display = "none";
    }).fail(function() { 
    	$('#trailerNotFoundElement').get(0).style.display = "block";
    	$('#loadingTrailer').get(0).style.display = "none";
    })
});

$('.progress-bar').each(function() {
  var iterations = parseInt($(this).text());
  var actualBar = $(this);
  var interval = setInterval(loading,1);
  var percentVal = 0;
  function loading() {
    percentVal += 1;
    actualBar.css("width",percentVal+'%').attr("aria-valuenow",percentVal+'%').text(percentVal+'%');
    if (percentVal == iterations)
      clearInterval(interval);
  }
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
});
$(document).on('click', function (e) {
    $('[data-toggle="popover"],[data-original-title]').each(function () {
        //the 'is' for buttons that trigger popups
        //the 'has' for icons within a button that triggers a popup
        if (!$(this).is(e.target) && $(this).has(e.target).length === 0 && $('.popover').has(e.target).length === 0) {                
            (($(this).popover('hide').data('bs.popover')||{}).inState||{}).click = false  // fix for BS 3.3.6
        }

    });
});
