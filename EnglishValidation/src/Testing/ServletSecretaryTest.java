package Testing;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.DbConnection;
import controller.ServletSecretary;


public class ServletSecretaryTest {
    private ServletSecretary servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    
    Connection conn = DbConnection.getInstance().getConn();
    String sql = "";
    PreparedStatement stmt = null;
    
    @Before
    public void setUp() throws SQLException {
        servlet = new ServletSecretary();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        
        sql = "DELETE FROM attached WHERE ID_ATTACHED = 1;";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        
        sql = "DELETE FROM attached WHERE ID_ATTACHED = 2;";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        
        sql = "DELETE FROM request WHERE ID_REQUEST = 1;";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    }
    
    @Test
    public void coverage1() throws ServletException, IOException {
    	request.addParameter("flag", "1");
    	
    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }
    
    @Test
    public void coverage6() throws ServletException, IOException, SQLException{
    	request.addParameter("flag", "1");
    	
    	sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'segreteria@unisa.it',1,2);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    	
        sql = "INSERT INTO attached VALUES (1,'str',1,'segreteria@unisa.it');";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        
    	servlet.doPost(request, response);

    	sql = "DELETE FROM attached WHERE ID_ATTACHED = 1;";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        
        sql = "DELETE FROM request WHERE ID_REQUEST = 1;";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        
        assertEquals("json", response.getContentType());
    }
    
    @Test
    public void coverage7() throws ServletException, IOException, SQLException{
    	request.addParameter("flag", "1");
    	
    	sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'segreteria@unisa.it',1,2);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    	
        sql = "INSERT INTO attached VALUES (1,'str',1,'segreteria@unisa.it'), (2,'str2',1,'segreteria@unisa.it');";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        
    	servlet.doPost(request, response);

    	sql = "DELETE FROM attached WHERE ID_ATTACHED = 1;";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        
        sql = "DELETE FROM attached WHERE ID_ATTACHED = 2;";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        
        sql = "DELETE FROM request WHERE ID_REQUEST = 1;";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        
        assertEquals("json", response.getContentType());
    }
    
    
    @Test
    public void coverage2() throws ServletException, IOException {
    	request.addParameter("flag", "2");
    	request.addParameter("idRequest", "1");
    	request.addParameter("cfu", "1");
    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }
    
    @Test
    public void coverage3() throws ServletException, IOException, SQLException{
    	request.addParameter("flag", "2");
    	request.addParameter("idRequest", "1");
    	request.addParameter("cfu", "1");
    	
    	sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'segreteria@unisa.it',1,1);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    	
    	servlet.doPost(request, response);
        
        sql = "DELETE FROM request WHERE ID_REQUEST = 1;";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        
        assertEquals("json", response.getContentType());
    }
    
    @Test
    public void coverage4() throws ServletException, IOException {
    	request.addParameter("flag", "3");
    	request.addParameter("idRequest", "1");
    	
    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }
    
    @Test
    public void coverage5() throws ServletException, IOException, SQLException{
    	request.addParameter("flag", "3");
    	request.addParameter("idRequest", "1");
    	
    	sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'segreteria@unisa.it',1,1);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    	
    	servlet.doPost(request, response);

    	sql = "DELETE FROM request WHERE ID_REQUEST = 1;";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        assertEquals("json", response.getContentType());
    }
}