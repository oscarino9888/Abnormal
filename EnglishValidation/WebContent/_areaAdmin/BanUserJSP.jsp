<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" import="controller.CheckSession"%>
<%@ page
	import="controller.StudentDatabase,model.Student,java.util.*,model.Request,controller.DbConnection,controller.ServletAdmin,java.sql.ResultSet,java.sql.Statement"%>

<%
	String pageName = "BanUserJSP.jsp";
	String pageFolder = "_areaAdmin";
	ArrayList<Student> student = new ArrayList<Student>();
	StudentDatabase datb=new StudentDatabase();
	student=datb.getStudents();
			
	
	
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
											<th class="text-center">Email</th>
											<th class="text-center">Nome</th>
											<th class="text-center">Cognome</th>
											<th class="text-center">Sesso</th>
											<th class="text-center">Stato di Ban</th>
											<th class="text-center">Sospensione Desiderata</th>
											<th class="text-center"></th>
											
										
										</tr>
									</thead>
									<tbody id="bodyAdminTable">
                                    <%
							//int [] itera;
								//itera = new int [100];

								for (int i = 0; i < student.size(); i++) {

									
						%>
						<form id="insertform" action="ServletBan" method="post">
						<tr>
							<td><%=student.get(i).getEmail()%></td>
							<td><%=student.get(i).getName()%></td>
							<td><%=student.get(i).getSurname()%></td>
							<td><%=student.get(i).getSex()%></td>
							<%if(student.get(i).getBan()==null){ %>
							<td> Account non sospeso </td>
							<%} else {%>
							<td><%=student.get(i).getBan()%></td>
							<% }%>
							<td><input name="ban_user" type="date" id="durataban" placeholder="Durata ban"></td>
							<input type="hidden" value="<%=student.get(i).getEmail() %>">
							<td><input type="submit"></td>
							</form>
							
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

	<jsp:include page="/partials/includes.jsp" />
	

</body>
</html>

