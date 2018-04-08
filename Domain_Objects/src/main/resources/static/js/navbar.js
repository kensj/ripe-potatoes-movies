$(document).ready(function() {
  $("#forgotFrame").hide();
  $("#registerFrame").hide();
  
  
  /*$(function() {
	  $("#autocomplete").autocomplete({
		  source: function( request, response ) {
				$.ajax({
					url: "/getContentList",
					transformResult: function(response) {				  
						return {      	
						  suggestions: $.map($.parseJSON(response), function(item) {
						      return { value: item.name, data: item.id };
						   })
						            
						};
					        
					}
				});
			}
	  });
  });*/

  /*$('#autocomplete').autocomplete({
		serviceUrl: '${pageContext.request.contextPath}/getContentList',
		paramName: "search",
		delimiter: ",",
		transformResult: function(response) {	
			return {      	
			  resultList: $.map($.parseJSON(response), function(item) {
				  return { name: item.name };
			  })       
			};        
		}
	});*/
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

var results = [
    { value: 'Moana', data: 'Moana' },
    { value: 'Big Hero 6', data: 'Big Hero 6' },
    { value: 'Logan', data: 'Logan' },
    { value: 'Fantastic Beasts', data: 'Fantastic Beasts' },
    { value: 'Zootopia', data: 'Zootopia' },
    { value: 'Robocop', data: 'Robocop' },
    { value: 'Early Man', data: 'Early Man' },
    { value: 'Baywatch', data: 'Baywatch' },
    { value: 'Suicide Squad', data: 'Suicide Squad' }
];

 $('#autocomplete').autocomplete({
	serviceUrl: '/getContentList',
	paramName: "search",
	delimiter: ",",
    onSelect: function (suggestion) {
        window.location = "content/" + suggestion.data
        //console.log(suggestion.data);
    }
});
