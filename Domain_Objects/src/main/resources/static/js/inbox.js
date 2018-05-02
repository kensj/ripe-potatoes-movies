$( document ).ready(function() {
	$(".messagelist").height($(".messageselect").height());
});

$( ".messageselect" ).change(function() {
	$(".messagelist").hide();
	$("#"+$(".messageselect").find(":selected").attr("id")+".messagelist").show();
	$(".inboxHeader").text("Inbox - "+$(".messageselect").find(":selected").text());
	$(".inboxLink").text("Visit Profile");
	$(".inboxLink").attr("href","/users/"+$(".messageselect").find(":selected").attr("id"));
	$(".chatshow").text($(".messageselect").find(":selected").text());
	$(".chatinput").width($("#"+$(".messageselect").find(":selected").attr("id")+".messagelist").width());
	$(".chatinput").show();
});

$('.chatinput').keyup(function(e){
    if(e.keyCode == 13) {
    	var token = $("meta[name='_csrf']").attr("content");
    	var header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
        	headers: {
    			'Accept': 'application/json',
    			'Content-Type': 'application/json',
    		},
    		type: 'POST',
            url: '/message/' + $(".messageselect").find(":selected").attr("id")+"?body="+$('.chatinput').val(),
            cache: false,
            beforeSend: function(xhr) {
    			xhr.setRequestHeader(header, token);
    		},
            success: function (response) {
            	$(".messagelist").append("<span class='chat right'>"+$('.chatinput').val()+"</span>");
            	$(".messagelist").append("<div class='clear'></div>");
            },
            error: function (jQXHR, textStatus, errorThrown) {
                console.log("An error occurred whilst trying to contact the server: " + jQXHR.status + " " + textStatus + " " + errorThrown);
            }
        });
    }
});
