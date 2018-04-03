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
