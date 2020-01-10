<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" import="controller.CheckSession"%>
<%@ page
	import="controller.SegnalazioneDatabase,model.Segnalazione,java.util.*,model.Request,controller.DbConnection,controller.ServletAdmin,java.sql.ResultSet,java.sql.Statement"%>

<%
	String pageName = "creaSegnalazioneToSecretary.jsp";
	String pageFolder = "_areaStudent";
	
	
	CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());
	if(!ck.isAllowed()){
	  response.sendRedirect(request.getContextPath()+ck.getUrlRedirect());  
	}
	SegnalazioneDatabase datb = new SegnalazioneDatabase();
	boolean checkSegnalazione = datb.checkSegnalazioneUser(request.getParameter("email"));
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp" />
</head>

<body>
	<div class="page-wrapper">

		<!-- Preloader -->
		<!--  <div class="preloader"></div> -->


		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%= pageName %>" />
			<jsp:param name="pageFolder" value="<%= pageFolder %>" />
		</jsp:include>


		<!-- Start Body  -->
		<% if(checkSegnalazione) { %>
		<!-- UTENTE HA GIA' SEGNALAZIONI CARICAMENTO DELLE SEGNALAZIONI -->
		<%} else { %>
		<!--  UTENTE NON HA ANCORA NESSUNA SEGNALAZIONE, CARICAMENTO DELLA CREAZIONE -->
		Contatta la segreteria tramite il box qui sotto, inserisci un messaggio e premi il tasto invia.
		<br>
		<form name="form-invia"></form><textarea name="text" rows="10" cols="10"></textarea><br>
		<input type="submit"> Invia </button></form>
		<% } %>
		
		
		<!--  END BODY -->

		<jsp:include page="/partials/footer.jsp" />
	</div>
	<!--End pagewrapper-->

	<jsp:include page="/partials/includes.jsp" />
	

</body>
</html>