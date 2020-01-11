package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class ServletStudent.
 */
@WebServlet("/ServletSegnalazione")
public class ServletSegnalazione extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 
	 public ServletSegnalazione() {
		 super();
	 }
	 
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    doPost(request, response);
	 }
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Integer result = 0;
		    String error = "";
		    String content = "";
		    String redirect = "";
		    PreparedStatement stmt = null;
		    Statement stmtSelect = null;
		    Statement stmtSelectTwo = null;

		    int flag = Integer.parseInt(request.getParameter("flag"));
		    Connection conn = new DbConnection().getInstance().getConn();
		    String sql = "";
		    
		    if (conn != null) {
		    	if (flag == 1) { //CREAZIONE PIMA SEGNALAZIONE DA PARTE DELLO STUDENTE
		    		String testo = request.getParameter("testo");
		    		String email = request.getParameter("email");
		    		try {
		    			//INSERISCO LA PRIMA SEGNALAZIONE NELLA TAB REPORT DEL DB
		    			sql = "INSERT INTO REPORT VALUES (?,?,?)";
		    			stmt = conn.prepareStatement(sql);
		    			stmt.setString(1, "key:" + email);
		    			stmt.setString(2, "");
		    			stmt.setString(3, testo);
		    			 stmt.executeUpdate();
		    			//PRENDO L'ID AUTO_INCREMENT DEL REPORT APPEAN CREATO 
		    			 sql = "SELECT ID_REPORT FROM REPORT WHERE SERIAL = ?";
		    			 String var = "key:" + email;
		    			 stmt = conn.prepareStatement(sql);
		    			 stmt.setString(1, var);
		    			 ResultSet r = stmt.executeQuery();
		    			 if (r.next()) {
		    				  int id = r.getInt(1);
		    				//CREO IL RECORD IN SEND_R PE COLLEGARE LA SEGNALAZIONE ALL'UTENTE CHE L'HA CREATA
		    				  sql = "INSERT INTO SEND_R VALUES (?,?)";
				    			stmt = conn.prepareStatement(sql);
				    			stmt.setString(1, email);
				    			stmt.setInt(2, id);
				    			stmt.executeUpdate();
				    		 }
		    		}catch(Exception e) {
		    			System.out.println(e.getMessage());
		    		}
		    	} 
		    }

	 }
}
	 