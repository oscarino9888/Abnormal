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
	
	public ArrayList<Segnalazione> getSegnalazioniFromStudent(){ //array di segnalazioni s
		 PreparedStatement stmt = null; //statement di tes
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
	public boolean checkSegnalazioneUser(String email){
		 PreparedStatement stmt = null; //statement di tes
		 ArrayList<Segnalazione> segnalazioni = new ArrayList<Segnalazione>();
		 Connection conn = new DbConnection().getInstance().getConn();
		 String sql = "";
		 if( conn != null ) {
			 try { 
				 sql = "select R.Serial From Report as R INNER JOIN SEND_R AS S ON R.ID_Report = S.ID_Report INNER JOIN USER AS U ON S.EMAIL = U.EMAIL WHERE U.EMAIL = ?";
				 stmt = conn.prepareStatement(sql);
				 stmt.setString(1,email);
				 ResultSet r = stmt.executeQuery();
				 if(r.next()) {
					return true;
				 }else {
					 return false;
				 }
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			 	
		 }
		 return false;
	}
	public ArrayList<Segnalazione> getSegnalazioneListFromSerial(String serial){
		PreparedStatement stmt = null; //statement di tes
		 ArrayList<Segnalazione> segnalazioni = new ArrayList<Segnalazione>();
		 Connection conn = new DbConnection().getInstance().getConn();
		 String sql = "";
		 try {
			 
			 sql = "SELECT BODY, EMAIL FROM REPORT AS R INNER JOIN SEND_R AS S ON R.ID_REPORT=S.ID_REPORT WHERE SERIAL=?";
			 stmt = conn.prepareStatement(sql);
			 stmt.setString(1, serial); //IL SERIAL E' UNIVICO PER OGNI REPORT DI UNA COVERSAZIONE STUDENTE-SEGRETERIA
			 ResultSet r = stmt.executeQuery();
			 while(r.next()) {
				Segnalazione s = new Segnalazione ();
				s.setBody(r.getString(1));
				s.setEmail(r.getString(2));
				segnalazioni.add(s);
			 }
			 conn.commit();
			 
		
		 }catch (Exception e) {
			System.out.println(e.getMessage());
		 }
		return segnalazioni;
	}
}
