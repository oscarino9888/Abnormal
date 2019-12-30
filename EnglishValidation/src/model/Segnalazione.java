package model;

public class Segnalazione {
	private String body; 
	private String head; 
	private int serial; 
	private int idReport; 
	
	public Segnalazione() {
		
	}
	public Segnalazione(String body, String head, int serial, int idReport) {
		this.body = body; 
		this.head = head; 
		this.serial = serial; 
		this.idReport = idReport;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	public int getIdReport() {
		return idReport;
	}
	public void setIdReport(int idReport) {
		this.idReport = idReport;
	}
	
	
}
