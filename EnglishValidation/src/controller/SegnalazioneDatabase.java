package controller;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Segnalazione;;
public class SegnalazioneDatabase { 
	public SegnalazioneDatabase() {
		
	}
	
	public ArrayList<Segnalazione> getSegnalazioniFromStudent(){ //array di segnalazioni
		 PreparedStatement stmt = null; //statement di test
		 ArrayList<Segnalazione> segnalazioni = new ArrayList<Segnalazione>();
		 Connection conn = new DbConnection().getInstance().getConn();
		 String sql = "";
		 if( conn != null ) {
			 try {
				 sql = 
						 "SELECT Report.ID_REPORT, Report.Serial, Report.Body, Report.Head"
						 + " FROM Report "
						 + "INNER JOIN SEND_R AS S ON Report.ID_REPORT = S.ID_REPORT "
						 + "INNER JOIN USER AS U ON S.EMAIL = U.EMAIL "
						 + "WHERE U.USER_TYPE = '0'"
						 + "GROUP BY(Report.Serial)"; //USER-TYPE DELLO STUDENTE DA DEFINIRE
				 stmt = conn.prepareStatement(sql);
			       
		          ResultSet r = stmt.executeQuery();
		          if (r.wasNull()) {
		        	  System.out.println("Errore nell'esecuzione della Query");
		          } else {
		        	  while(r.next()) {
		        		  int id_report = r.getInt("ID_REPORT");
		        		  int serial = r.getInt("SERIAL");
		        		  String body = r.getString("BODY");
		        		  String head = r.getString("HEAD");
		        		  Segnalazione s = new Segnalazione();
		        		  s.setBody(body);
		        		  s.setHead(head);
		        		  s.setIdReport(id_report);
		        		  s.setSerial(serial);
		        		  segnalazioni.add(s);
		        		  }
		          }
		          conn.commit();
				 
			 }catch(Exception e) {
				 System.out.println(e.getMessage());
			 }
		 }

		 return segnalazioni;
		
	}
	public ArrayList<Segnalazione> getSegnalazioniFromSecretary(){
		 PreparedStatement stmt = null;
		 ArrayList<Segnalazione> segnalazioni = new ArrayList<Segnalazione>();
		 Connection conn = new DbConnection().getInstance().getConn();
		 String sql = "";
		 if( conn != null ) {
			 try {
				 sql = 
						 "SELECT Report.ID_REPORT, Report.Serial, Report.Body, Report.Head"
						 + " FROM Report "
						 + "INNER JOIN SEND_R AS S ON Report.ID_REPORT = S.ID_REPORT "
						 + "INNER JOIN USER AS U ON S.EMAIL = U.EMAIL "
						 + "WHERE U.USER_TYPE = '1'"; //USER-TYPE DELLA SEGRETERIA DA DEFINIRE
				 stmt = conn.prepareStatement(sql);
			       
		          ResultSet r = stmt.executeQuery();
		          if (r.wasNull()) {
		        	  System.out.println("Errore nell'esecuzione della Query");
		          } else {
		        	  while(r.next()) {
		        		  int id_report = r.getInt("ID_REPORT");
		        		  int serial = r.getInt("SERIAL");
		        		  String body = r.getString("BODY");
		        		  String head = r.getString("HEAD");
		        		  Segnalazione s = new Segnalazione();
		        		  s.setBody(body);
		        		  s.setHead(head);
		        		  s.setIdReport(id_report);
		        		  s.setSerial(serial);
		        		  segnalazioni.add(s);
		        		  }
		          }
		          conn.commit();
				 
			 }catch(Exception e) {
				 System.out.println(e.getMessage());
			 }
		 }

		 return segnalazioni;
		
	}
}
