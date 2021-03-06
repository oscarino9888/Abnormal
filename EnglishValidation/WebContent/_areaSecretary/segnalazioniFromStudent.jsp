<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" import="controller.CheckSession"%>
<%@ page
	import="controller.SegnalazioneDatabase,model.Segnalazione,java.util.*,model.Request,controller.DbConnection,controller.ServletAdmin,java.sql.ResultSet,java.sql.Statement"%>

<%
	String pageName = "segnalazioniFromStudent.jsp";
	String pageFolder = "_areaSecretary";
	ArrayList<Segnalazione> segnalazioni = new ArrayList<Segnalazione>();
	SegnalazioneDatabase datb=new SegnalazioneDatabase();
	segnalazioni=datb.getSegnalazioniFromStudent();
			
	
	
	CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());
	if(!ck.isAllowed()){
	  response.sendRedirect(request.getContextPath()+ck.getUrlRedirect());  
	}
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp" />
</head>

<body onLoad="showData()">
	<div class="page-wrapper">

		<!-- Preloader -->
		<!--  <div class="preloader"></div> -->


		<jsp:include page="/partials/header.jsp">
			<jsp:param name="pageName" value="<%= pageName %>" />
			<jsp:param name="pageFolder" value="<%= pageFolder %>" />
		</jsp:include>


		<div class="sidebar-page-container basePage viewRequestAdmin">
			<div class="auto-container">
				<div class="row clearfix">
					<div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="content ">
							<div class="news-block-seven">
								<table id="adminTable" class="display data-results table table-striped table-hover table-bordered">
									<thead>
										<tr>
											<th class="text-center">EMAIL</th>
											<th class="text-center">HEADER</th>
											<th class="text-center">VISUALIZZA</th>
										
										</tr>
									</thead>
									<tbody id="bodyAdminTable">
                                    <%
							//int [] itera;
								//itera = new int [100];

								for (int i = 0; i < segnalazioni.size(); i++) {

									
						%>
						<tr>
							<td><%= segnalazioni.get(i).getEmail() %></td>
							<td><%= segnalazioni.get(i).getHead() %></td>
							<td><button onClick="openVisualizza('<%=segnalazioni.get(i).getEmail()%>')" > Visualizza </button></td>
						</tr>
						
						<%
							}
						%>
									</tbody>
								</table>
								
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<jsp:include page="/partials/footer.jsp" />
	</div>
	<!--End pagewrapper-->
	<script>
	function openVisualizza(email){
		window.open("rispostaSegnalazione.jsp?keyserial="+email,"Finestra di diaologo",'width=500,height=600');
	}
	</script>
	<jsp:include page="/partials/includes.jsp" />
	

</body>
</html>