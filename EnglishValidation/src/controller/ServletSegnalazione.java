package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
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
		    		String head = request.getParameter("head");
		    		try {
		    			//INSERISCO LA PRIMA SEGNALAZIONE NELLA TAB REPORT DEL DB
		    			sql = "INSERT INTO REPORT(Body, Head, Serial) VALUES "+ "(?,?,?)";
		    			stmt = conn.prepareStatement(sql,stmt.RETURN_GENERATED_KEYS);
		    			stmt.setString(1, testo);
		    			stmt.setString(2, head);
		    			stmt.setString(3, "key:" + email);

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
				    			result=1;
				    		 }
		    			 conn.commit();
		    			 
		    		}catch(Exception e) {
		    			System.out.println(e.getMessage());
		    		}
		    	}else if(flag == 2) { //FLAG DI RISPOSTA
		    		String testo = request.getParameter("testo");
		    		String email = request.getParameter("email");
		    		String serial = request.getParameter("keyserial");
		    		try {
		    			//INSERISCO LA PRIMA SEGNALAZIONE NELLA TAB REPORT DEL DB
		    			sql = "INSERT INTO REPORT(Body, Head, Serial) VALUES "+ "(?,?,?)";
		    			stmt = conn.prepareStatement(sql,stmt.RETURN_GENERATED_KEYS);
		    			System.out.println("body: " + testo);
		    			stmt.setString(1, testo);
		    			stmt.setString(2, "");
		    			stmt.setString(3, serial);
		    			 stmt.executeUpdate();
		    			//PRENDO L'ID AUTO_INCREMENT DEL REPORT APPEAN CREATO 
		    			 sql = "SELECT ID_REPORT FROM REPORT";
		    			 stmt = conn.prepareStatement(sql);
		    			 ResultSet r = stmt.executeQuery();
		    			 r.last();
		    				  int id = r.getInt(1);
		    				//CREO IL RECORD IN SEND_R PE COLLEGARE LA SEGNALAZIONE ALL'UTENTE CHE L'HA CREATA
		    				  sql = "INSERT INTO SEND_R VALUES (?,?)";
				    			stmt = conn.prepareStatement(sql);
				    			stmt.setString(1, email);
				    			stmt.setInt(2, id);
				    			stmt.executeUpdate();
				    			result=1;
				    		 conn.commit();
		    			 
		    		}catch(Exception e) {
		    			System.out.println(e.getMessage());
		    		}
		    		
		    	}else if(flag == 3) {
		    		String keyserial = request.getParameter("keyserial");
		    		try {
		    			// TROVO TUTTE LE SEGNALAZIONI CON IL KEYSERIAL DA ELIMINARE
		    			sql = "SELECT ID_REPORT FROM REPORT WHERE SERIAL = ?";
		    			stmt = conn.prepareStatement(sql);
		    			stmt.setString(1,keyserial);
		    			ResultSet r = stmt.executeQuery();
		    			while(r.next()) { //PER TUTTI I RECORD AVENTI KEYSERIAL DA ELIMINARE
		    				int id = r.getInt(1);
		    				//ELIMINO PRIMA IL RECORD DA SEND_R 
		    				sql = "DELETE FROM SEND_R WHERE ID_REPORT = ?"; 
		    				stmt  = conn.prepareStatement(sql);
		    				stmt.setInt(1, id);
		    				stmt.executeUpdate();
		    				sql = "DELETE FROM REPORT WHERE ID_REPORT = ?";
		    				stmt = conn.prepareStatement(sql);
		    				stmt.setInt(1,id);
		    				stmt.executeUpdate();
		    			}
		    		}catch (Exception e) {
		    			System.out.println(e.getMessage());

		    		}
		    	}
		    }
		    content="Aggiunta una segnalazione";
		    redirect= request.getContextPath() + "/creaSegnalazioneToSecretary.jsp";
		    JSONObject res = new JSONObject();
		    res.put("result", result);
		    res.put("error", error);
		    res.put("content", content);
		    res.put("redirect", redirect);
		    PrintWriter out = response.getWriter();
		    out.println(res);
		    response.setContentType("json");
	 }
}
	 
