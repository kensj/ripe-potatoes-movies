$('#searchbox').keyup(function(e){
    if(e.keyCode == 13) {
    	console.log(window.location)
    	window.location = "usersearch?searchBar=" + $('#searchbox').val()
    }
});
