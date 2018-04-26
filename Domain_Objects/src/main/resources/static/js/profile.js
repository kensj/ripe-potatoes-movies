jQuery.fn.extend({
    live: function (event, callback) {
       if (this.selector) {
            jQuery(document).on(event, this.selector, callback);
        }
    }
});

$(document).ready(function() {
	$("#deleteWarning").hide();
	$(".rrContainer").show();
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

//The rel attribute is the userID you would want to follow

$('button.followButton').live('click', function(e){
//$('body').on('click','button.followbutton',function(e){
    e.preventDefault();
    $button = $(this);
    if($button.hasClass('following')){       
        unfollow();     
        $button.removeClass('following');
        $button.removeClass('unfollow');
        $button.text('Follow');
    } else {        
        follow();        
        $button.addClass('following');
        $button.text('Following');
    }
});

$('button.followButton').hover(function(){
     $button = $(this);
    if($button.hasClass('following')){
        $button.addClass('unfollow');
        $button.text('Unfollow');
    }
}, function(){
    if($button.hasClass('following')){
        $button.removeClass('unfollow');
        $button.text('Following');
    }
});

function follow() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
    	headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: 'POST',
        url: '/follow/' + $("#userID").val(),
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

function unfollow() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
    	headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: 'POST',
        url: '/unfollow/' + $("#userID").val(),
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




