<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page
	import="model.Student,controller.SegnalazioneDatabase,model.Segnalazione,java.util.*,model.Request,controller.DbConnection,controller.ServletAdmin,java.sql.ResultSet,java.sql.Statement"%>

<%
	String pageName = "rispostaSegnalazione.jsp";
	String pageFolder = "_areaSecretary";
	SegnalazioneDatabase datb = new SegnalazioneDatabase();
	String email = request.getParameter("keyserial");
	String keySerial = "key:" + email;
	%>

<html>
<head>
<title>FINESTRA DI CHAT</title>
<style>
body {
  margin: 0 auto;
  max-width: 800px;
  padding: 0 20px;
}




.container {
  border: 2px solid #dedede;
  background-color: #f1f1f1;
  border-radius: 5px;
  padding: 10px;
  margin: 10px 0;
}

.darker {
  border-color: #ccc;
  background-color: #ddd;
}

.container::after {
  content: "";
  clear: both;
  display: table;
}

.container img {
  float: left;
  max-width: 40px;
  width: 100%;
  margin-right: 20px;
  border-radius: 50%;
}

.container img.right {
  float: right;
  margin-left: 20px;
  margin-right:0;
}

.time-right {
  float: right;
  color: #aaa;
}

.time-left {
  float: left;
  color: #999;
}
</style>
</head>
<body>
<%
ArrayList<Segnalazione> listSegnalazioni = datb.getSegnalazioneListFromSerial(keySerial);
for(int i = 0; i < listSegnalazioni.size(); i++){
	if(listSegnalazioni.get(i).getEmail().equals("segreteria@unisa.it")){ %>
		<div class="container">
			  			<img src="" alt="Segreteria">
			  				<p><%=listSegnalazioni.get(i).getBody()%></p>
		</div>
	<% }else{ %>
		<div class="container darker">
			  				<img src="" alt="Studente" class="right">
			  				<p><%=listSegnalazioni.get(i).getBody()%></p>
		</div>
	<%}
}
%>
<br><form id="form-risposta">
		<textarea id="testo-risposta" rows="5" cols="10"></textarea>
	   <input type="hidden" id="email-risposta" value="segreteria@unisa.it">
	   <input type="hidden" id="keyserial" value="<%=keySerial %>">
		<br> <input type="button" id="bottone-risposta" value="invia"></form>
<jsp:include page="/partials/includes.jsp" />
<script>
$(document).ready(function() {
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

</script>
</body>
</html>