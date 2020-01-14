
$(document).ready(function() {
  $("#bottone-invia").click(function(){
    var testo_msg = $("#testo").val();
    var email = $("#email").val();
    var head = $("#headsend").val();
    $.ajax({
      type: "POST",
	  dataType: 'JSON',
	  async: false,
      url:  absolutePath + "/ServletSegnalazione",
      data : {
				"testo" : testo_msg,
				"email" : email,
				"flag" : 1,
				"head" : head,
			},
      success: function(msg)
      {
        location.reload(true);
      },
      error: function()
      {
        alert("Chiamata fallita, si prega di riprovare...");
      }
    });
  });
  $("#bottone-risposta").click(function(){
	    var testo_msg = $("#testo-risposta").val();
	    var email = $("#email-risposta").val();
	    var keySerial = $("#keyserial").val();
	    $.ajax({
	      type: "POST",
		  dataType: 'JSON',
		  async: false,
	      url:  absolutePath + "/ServletSegnalazione",
	      data : {
					"testo" : testo_msg,
					"email" : email,
					"keyserial": keySerial,
					"flag" : 2
				},
	      success: function(msg)
	      {
	        location.reload(true);
	      },
	      error: function()
	      {
	        alert("Chiamata fallita, si prega di riprovare...");
	      }
	    });
	  });
});


