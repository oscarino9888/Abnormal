package controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Student;

public class StudentDatabase {

public StudentDatabase() {
	
}


public ArrayList<Student> getStudents(){
	

	 Integer result = 0;
   String error = "";
    String content = "";
  String redirect = "";
    PreparedStatement stmt = null;
    ArrayList<Student> students=new ArrayList<Student>();

 
    Connection conn = new DbConnection().getInstance().getConn();
    
    String sql = "";

    if (conn != null) {

     
       
        try {
          sql =
              " SELECT * FROM user "
              + "WHERE user_type = 0";
          stmt = conn.prepareStatement(sql);
       
          ResultSet r = stmt.executeQuery();
          if (r.wasNull()) {
            error = "Errore nell'esecuzione della Query";
          } else {
        	  while(r.next()){
              String email= r.getString("email");
              String name = r.getString("name");
              String surname = r.getString("surname");
              char sex = r.getString("sex").charAt(0);
              Date ban= r.getDate("ban");
              Student s=new Student();
              s.setEmail(email);
              s.setName(name);
              s.setSurname(surname);
              s.setSex(sex);
              s.setBan(ban);
              students.add(s);
}
          }
         
              conn.commit();
            
             
          } catch (Exception e) {
            error += e.getMessage();
          }
        }
	return students;
    }
}



