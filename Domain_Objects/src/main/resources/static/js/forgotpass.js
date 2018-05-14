function changePass() {
	if($('#passwordInput1').val() != $('#passwordInput2').val()) {
		alert("Passwords don't match!");
	}
	else {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$.ajax({
	    	headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json',
			},
			type: 'POST',
	        url: '/changepass?userID=' + $('#userID').val() + '&password=' + $('#passwordInput1').val() + '&token=' + $('#token').val(),
	        cache: false,
	        beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
	        success: function (response) {
	        	console.log("Here")
	        	if(response.success == "true") {
	        		alert("changed")
	        		$('#passwordInput1').val("");
	        		$('#passwordInput2').val("");
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
}