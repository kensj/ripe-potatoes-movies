jQuery.fn.extend({
    live: function (event, callback) {
       if (this.selector) {
            jQuery(document).on(event, this.selector, callback);
        }
    }
});

$(document).ready(function() {
	$("#deleteWarning").hide();
	hideAll();
	showRR();
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

	$(".emailChangedText").hide();
	$(".passwordChangedText").hide();
	
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

function hideAll() {
	$(".rrContainer").hide();
	$(".wishlistContainer").hide();
	$(".niContainer").hide();
	$(".followingContainer").hide();
	$(".followedContainer").hide();
	$(".blockedContainer").hide();
}


function showRR() {
	hideAll();
	$(".rrContainer").show();
}

function showWishlist() {
	hideAll();
	$(".wishlistContainer").show();
}

function showFollowing() {
	hideAll();
	$(".followingContainer").show();
}

function showFollowed() {
	hideAll();
	$(".followedContainer").show();
}

function showBlocked() {
	hideAll();
	$(".blockedContainer").show();
}

function showNI() {
	hideAll();
	$(".niContainer").show();
}

// ---------------------------- FOLLOW -------------------------------- //

$('button.followButton').live('click', function(e){
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
		type: 'DELETE',
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

// ---------------------------- Block -------------------------------- //

$('button.blockButton').live('click', function(e){
	    e.preventDefault();
	    $button = $(this);
	    if($button.hasClass('blocking')){       
	        unblock();     
	        $button.removeClass('blocking');
	        $button.removeClass('unblock');
	        $button.text('Block');
	    } else {   
	    	unfollow();
	        block();
	        $button.addClass('blocking');
	        $button.text('Blocking');
	    }
	});

$('button.blockButton').hover(function(){
    $button = $(this);
   if($button.hasClass('blocking')){
       $button.addClass('unblock');
       $button.text('Unblock');
   }
}, function(){
   if($button.hasClass('blocking')){
       $button.removeClass('unblock');
       $button.text('Blocking');
   }
});

function block() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
    	headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: 'POST',
        url: '/block/' + $("#userID").val(),
        cache: false,
        beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
        success: function (response) {
        	location.reload();
        },
        error: function (jQXHR, textStatus, errorThrown) {
            console.log("An error occurred whilst trying to contact the server: " + jQXHR.status + " " + textStatus + " " + errorThrown);
        }
    });
}

function unblock() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
    	headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: 'DELETE',
        url: '/unblock/' + $("#userID").val(),
        cache: false,
        beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
        success: function (response) {
        	location.reload();
        },
        error: function (jQXHR, textStatus, errorThrown) {
            console.log("An error occurred whilst trying to contact the server: " + jQXHR.status + " " + textStatus + " " + errorThrown);
        }
    });
}

$(".mbtn").click(function() {
    $("#messageModal").modal('show');
});

$(".cibtn").click(function() {
    $("#changeIconModal").modal('show');
});

function messageUser() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
    	headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: 'POST',
        url: '/message/' + $("#userID").val() + "?body="+$('.messageBody').val(),
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

function deleteIcon() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
    $.ajax({
    	headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		},
		type: 'POST',
        url: '/deleteicon/',
        cache: false,
        beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
        success: function (response) {
        	$(".profileIcon").attr("src","/icons/default.png");
        },
        error: function (jQXHR, textStatus, errorThrown) {
            console.log("An error occurred whilst trying to contact the server: " + jQXHR.status + " " + textStatus + " " + errorThrown);
        }
    });
}

function manageAccount() {
	
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	
	var email = $('#manageEmail').val().trim();
	var cpw = $('#manageCPW').val().trim();
	var pw1 = $('#managePW1').val().trim();
	var pw2 = $('#managePW2').val().trim();
	
	if(!!email){
		$.ajax({
	    	headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json',
			},
			type: 'POST',
	        url: '/update-email?password=' + cpw + '&newEmail=' + email,
	        cache: false,
	        beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
	        success: function (response, succ, value) {	        	
	        	if(response.success == "true") {
		        	$("#manageAccount").hide();
		        	document.getElementById('id01').style.display='none';
		        	$(".emailChangedText").show();
		        	$("#manageEmail").attr("placeholder",email);
	        	}
	        	else {
	        		alert("Error changing email")
	        	}
	        },
	        error: function (jQXHR, textStatus, errorThrown) {
	            alert("Unknown error")
	        }
	    });
	}
	
	if(!!pw1 && pw1==pw2){
		$.ajax({
	    	headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json',
			},
			type: 'POST',
	        url: '/update-password?oldPass=' + cpw + '&newPass=' + pw1,
	        cache: false,
	        beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
	        success: function (response, succ, value) {
	        	if(response.success == "true") {
		        	$("#manageAccount").hide();
		        	document.getElementById('id01').style.display='none';
		        	$(".passwordChangedText").show();
	        	}
	        	else {
	        		alert("Error changing password")
	        	}
	        },
	        error: function (jQXHR, textStatus, errorThrown) {
	            alert("Unknown error")
	        }
	    });
	}
	else if(pw1!=pw2) {
		alert("Passwords don't match")
	}
	
	$('#manageEmail').val("");
	$('#manageCPW').val("");
	$('#managePW1').val("");
	$('#managePW2').val("");
}
