package Testing;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.DbConnection;
import controller.ServletSegnalazione;
import interfacce.UserInterface;
import model.Student;

public class ServletSegnalazioneCoverage {

    private ServletSegnalazione servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;


    Connection conn = DbConnection.getInstance().getConn();
    String sql = "";
    PreparedStatement stmt = null;

    @Before
    public void setUp() throws SQLException {
        servlet = new ServletSegnalazione();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        
        sql = "SELECT * FROM user  WHERE EMAIL = 'p.prova@studenti.unisa.it';";
        stmt = conn.prepareStatement(sql);
        ResultSet resultSet = stmt.executeQuery();
        if(!resultSet.isBeforeFirst()) 
        {
        	sql = "INSERT INTO user VALUES ('p.prova@studenti.unisa.it', 'Thomas', 'Amendola', 'M', 'Gi230198', 0, null);";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        }
    }

    @Test
    public void coverage1() throws Exception {
    	
    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  

        request.setParameter("flag", "1");
        request.setParameter("testo", "test");
		request.setParameter("email", "p.prova@studenti.unisa.it");
		request.setParameter("head", "test");
        
    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }
    
    @Test
    public void coverage2() throws Exception {
    	
    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  

        request.setParameter("flag", "2");
        request.setParameter("testo", "test");
		request.setParameter("email", "p.prova@studenti.unisa.it");
		request.setParameter("keyserial", "key:p.prova@studenti.unisa.it");
        
    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }
    
    @Test
    public void coverage3() throws Exception {
    	
    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  

        request.setParameter("flag", "3");
		request.setParameter("keyserial", "key:p.prova@studenti.unisa.it");
        
    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }
}