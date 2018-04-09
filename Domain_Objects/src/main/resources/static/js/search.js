$('#searchbox').keyup(function(e){
    if(e.keyCode == 13)
    {
    	window.location = "search?searchBar=" + $('#searchbox').val()
    }
});