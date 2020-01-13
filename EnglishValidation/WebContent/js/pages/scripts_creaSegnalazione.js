
$(document).ready(function() {
  $("#bottone-invia").click(function(){
    var testo_msg = $("#testo").val();
    var email = $("#email").val();
    $.ajax({
      type: "POST",
	  dataType: 'JSON',
	  async: false,
      url:  absolutePath + "/ServletSegnalazione",
      data : {
				"testo" : testo_msg,
				"email" : email,
				"flag" : 1												
			},
      success: function(msg)
      {
        $("#risultato").html(msg);
      },
      error: function()
      {
        alert("Chiamata fallita, si prega di riprovare...");
      }
    });
  });
});


$(document).ready(function() {
	  $("#bottone-risposta").click(function(){
	    var testo_msg = $("#testo").val();
	    var email = $("#email").val();
	    $.ajax({
	      type: "POST",
		  dataType: 'JSON',
		  async: false,
	      url:  absolutePath + "/ServletSegnalazione",
	      data : {
					"testo" : testo,
					"email" : email,
					"flag" : 1
																
				},
	      success: function(msg)
	      {
	        $("#risultato").html(msg);
	      },
	      error: function()
	      {
	        alert("Chiamata fallita, si prega di riprovare...");
	      }
	    });
	  });
	});


