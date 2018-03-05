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
