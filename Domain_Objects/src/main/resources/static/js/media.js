jQuery.fn.extend({
    live: function (event, callback) {
       if (this.selector) {
            jQuery(document).on(event, this.selector, callback);
        }
    }
});

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

$('button.wishlistButton').live('click', function(e){
    e.preventDefault();
    $button = $(this);
    if($button.hasClass('wishlisting')){       
        unwishlist();     
        $button.removeClass('wishlisting');
        $button.removeClass('unwishlist');
        $button.text('Wishlist');
    } else {       
        wishlist();
        $button.addClass('wishlisting');
        $button.text('Wishlisted');
    }
});

$('button.wishlistButton').hover(function(){
     $button = $(this);
    if($button.hasClass('wishlisting')){
        $button.addClass('unwishlist');
        $button.text('Remove Wishlist');
    }
}, function(){
    if($button.hasClass('wishlisting')){
        $button.removeClass('unwishlist');
        $button.text('Wishlisted');
    }
});

function wishlist() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
    	headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: 'POST',
        url: '/wishlist/' + $("#contentID").val(),
        cache: false,
        beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
        success: function (response) {
        	$button = $('button.notinterestedButton');
        	if($button.hasClass('notinteresteding')){       
        		unnotinterested();     
        		$button.removeClass('notinteresteding');
        		$button.removeClass('unnotinterested');
        		$button.text("I'm Not Interested");
        	}
        },
        error: function (jQXHR, textStatus, errorThrown) {
            console.log("An error occurred whilst trying to contact the server: " + jQXHR.status + " " + textStatus + " " + errorThrown);
        }
    });
}

function unwishlist() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
    	headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: 'POST',
        url: '/unwishlist/' + $("#contentID").val(),
        cache: false,
        beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
        success: function (response) {},
        error: function (jQXHR, textStatus, errorThrown) {
            console.log("An error occurred whilst trying to contact the server: " + jQXHR.status + " " + textStatus + " " + errorThrown);
        }
    });
}

$('button.notinterestedButton').live('click', function(e){
    e.preventDefault();
    $button = $(this);
    if($button.hasClass('notinteresteding')){       
        unnotinterested();     
        $button.removeClass('notinteresteding');
        $button.removeClass('unnotinterested');
        $button.text("I'm Not Interested");
    } else {       
        notinterested();
        $button.addClass('notinteresteding');
        $button.text('Not Interested');
    }
});

$('button.notinterestedButton').hover(function(){
     $button = $(this);
    if($button.hasClass('notinteresteding')){
        $button.addClass('unnotinterested');
        $button.text('Remove Not Interested');
    }
}, function(){
    if($button.hasClass('notinteresteding')){
        $button.removeClass('unnotinterested');
        $button.text('Not Interested');
    }
});

function notinterested() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
    	headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: 'POST',
        url: '/notinterested/' + $("#contentID").val(),
        cache: false,
        beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
        success: function (response) {
        	$button = $('button.wishlistButton')
        	if($button.hasClass('wishlisting')){
        		unwishlist();     
        		$button.removeClass('wishlisting');
        		$button.removeClass('unwishlist');
        		$button.text('Wishlist');
        	}
        },
        error: function (jQXHR, textStatus, errorThrown) {
            console.log("An error occurred whilst trying to contact the server: " + jQXHR.status + " " + textStatus + " " + errorThrown);
        }
    });
}

function unnotinterested() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
    	headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: 'POST',
        url: '/unnotinterested/' + $("#contentID").val(),
        cache: false,
        beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
        success: function (response) {},
        error: function (jQXHR, textStatus, errorThrown) {
            console.log("An error occurred whilst trying to contact the server: " + jQXHR.status + " " + textStatus + " " + errorThrown);
        }
    });
}