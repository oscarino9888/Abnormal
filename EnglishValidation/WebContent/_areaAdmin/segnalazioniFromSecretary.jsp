<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" import="controller.CheckSession"%>
<%@ page
	import="model.Student,controller.SegnalazioneDatabase,model.Segnalazione,java.util.*,model.Request,controller.DbConnection,controller.ServletAdmin,java.sql.ResultSet,java.sql.Statement"%>

<%
	String pageName = "segnalazioniFromSecretary.jsp";
	String pageFolder = "_areaSecretary";
	
	
	CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());
	if(!ck.isAllowed()){
	  response.sendRedirect(request.getContextPath()+ck.getUrlRedirect());  
	}
	SegnalazioneDatabase datb = new SegnalazioneDatabase();
	Student stud = (Student) request.getSession().getAttribute("user");
	String keySerial = "key:fferrucci@unisa.it";
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp" />
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
  max-width: 60px;
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

		<div class="page-wrapper">

		<!-- Preloader -->
		<!--  <div class="preloader"></div> -->


		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%= pageName %>" />
			<jsp:param name="pageFolder" value="<%= pageFolder %>" />
		</jsp:include>

<div class="sidebar-page-container basePage uploadAttachedPage">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content">
							<div class="news-block-seven">
								<div class="form-group">

		<!-- Start Body  -->
		<%  ArrayList<Segnalazione> segnalazioni = datb.getSegnalazioneListFromSerial(keySerial);
			if(segnalazioni.isEmpty()){ %>
					<br><form id="form-risposta">
			<textarea id="testo-risposta" rows="20" cols="20"></textarea>
	   	<input type="hidden" id="email-risposta" value= <%=stud.getEmail()%>>
	   	<input type="hidden" id="keyserial" value="<%=keySerial %>"> 
			<br> <input type="button" id="bottone-risposta" value="invia"></form>
			<% }else{
			for(int i = 0; i < segnalazioni.size(); i++){
				if(segnalazioni.get(i).getEmail().equals("segreteria@unisa.it")){
					%>
					<!-- LA SEGNALAZIONE IN QUESTIONE E' DELLA SEGRETERIA. CARICO IL LAYOUT PER LA SEGRETERIA -->
						<div class="container darker">
			  				<img src="" alt="segreteria" class="right">
			  				<p><%=segnalazioni.get(i).getBody()%></p>
						</div>
				<% } else { %>
				<!-- LA SEGNALAZIONE IN QUESTIONE E' DELL'ADMIN. CARICO IL LAYOUT DELLO STUDENTE -->
					<div class="container ">
			  			<img src="" alt="admin">
			  		<p><%=segnalazioni.get(i).getBody()%></p>
						</div>
				<% } 
				}   %>
		<!--  FINE CARICAMENTO SEGNALAZIONI TRA STUDENTE E SEGRETERIA 
			  CARICO IL FORM DI RISPOSTA DA PARTE DELL'UTENTE -->
			  
		<br><form id="form-risposta">
		<textarea id="testo-risposta" rows="20" cols="20"></textarea>
	   <input type="hidden" id="email-risposta" value= <%=stud.getEmail()%>>
	   <input type="hidden" id="keyserial" value="<%=keySerial %>"> 
		<br> <input type="button" id="bottone-risposta" value="invia"></form>
			<% } %>

		
	</div>
						</div>
					</div>
				</div>
			</div>
			</div>
			</div>
			</div>
					<jsp:include page="/partials/footer.jsp" />
		<!--  END BODY -->
<jsp:include page="/partials/includes.jsp" />
	<script src="<%= request.getContextPath() %>/js/pages/scripts_creaSegnalazione.js"></script>
	


	<!--End pagewrapper-->

	
	

</body>
</html>