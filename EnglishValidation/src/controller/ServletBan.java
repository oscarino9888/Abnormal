package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.Locale;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Student;

/**
 * Servlet implementation class ServletBan
 */
@WebServlet("/ServletBan")
public class ServletBan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String date=request.getParameter("ban_user");
	   String email=request.getParameter("email");
	   Integer result = 0;
	   String error = "";
	    String content = "";
	  String redirect = "";
	  Date data = null;
	  PreparedStatement stmt=null;
	
	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parser = null;
		try {
			parser = format.parse(date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new IllegalArgumentException("Formato non corretto");
		}
		@SuppressWarnings("deprecation")
		java.sql.Date dataSql = new java.sql.Date(parser.getTime());
	System.out.println(parser);
		
	   Connection conn = new DbConnection().getInstance().getConn();
	    
	    String sql = "";

	    if (conn != null) {

	     
	       
	        try {
	        	System.out.println("fase update");
	          sql ="update user set ban=? where EMAIL = ? ";
	           stmt = conn.prepareStatement(sql);
	          stmt.setDate(1,dataSql);
	          stmt.setString(2, email);
	       
	          stmt.executeUpdate();
	         
	
	          if(stmt.executeUpdate() != 1)
	        	  throw new IllegalArgumentException("Utente non registrato");
	         
	              conn.commit();
	            
	             
	          } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	          }
	        }
	        
		
	    request.getRequestDispatcher("_areaAdmin/banUser.jsp").forward((ServletRequest)request, (ServletResponse)response);
}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
