$(document).ready(function() {
  $("#forgotFrame").hide();
  $("#registerFrame").hide();
});

$(document).keyup(function(e) {
  if (e.which == 27)
    closeLogin();
});

function openF() {
  $("#forgotFrame").show();
  $("#registerFrame").hide();
  $("#loginFrame").hide();
  $("#manageAccount").hide();
}
function openL() {
  $("#forgotFrame").hide();
  $("#registerFrame").hide();
  $("#loginFrame").show();
  $("#manageAccount").hide();
}
function openR() {
  $("#forgotFrame").hide();
  $("#manageAccount").hide();
  $("#registerFrame").show();
  $("#loginFrame").hide();
}

function login() {
  openL();
  document.getElementById('id01').style.display='block';
  if ($(".ll").text() === "Logout") {
	  sendLogoutRequest();
	  window.location.replace("/");
  }
}
function closeLogin() {
  document.getElementById('id01').style.display='none';
}
var modal = document.getElementById('id01');
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

 $('#autocomplete').autocomplete({
	serviceUrl: '/getContentList',
	paramName: "search",
	delimiter: ",",
    onSelect: function (suggestion) {
        window.location = "content/" + suggestion.data
        //console.log(suggestion.data);
    }
});
 
 $('#searchbox').autocomplete({
		serviceUrl: '/getSearchList',
		paramName: "search",
		delimiter: ",",
	    onSelect: function (suggestion) {
	        //window.location = "content/" + suggestion.data
	        //console.log(suggestion.data);
	    }
});
 
