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
import controller.ServletAdmin;
import interfacce.UserInterface;
import model.Student;

public class ServletAdminCoverage {

    private ServletAdmin servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;


    Connection conn = DbConnection.getInstance().getConn();
    String sql = "";
    PreparedStatement stmt = null;

    @Before
    public void setUp() throws SQLException {
        servlet = new ServletAdmin();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();

        sql = "SELECT * FROM user  WHERE EMAIL = 'p.prova@studenti.unisa.it';";
        stmt = conn.prepareStatement(sql);
        ResultSet resultSet = stmt.executeQuery();
        if(!resultSet.isBeforeFirst()) 
        {
        	sql = "INSERT INTO user VALUES ('p.prova@studenti.unisa.it', 'Thomas', 'Amendola', 'M', 'Gi230198', 0);";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        }
        
        sql = "DELETE FROM attached WHERE FK_USER = 'p.prova@studenti.unisa.it';";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        sql = "DELETE FROM request WHERE FK_USER = 'p.prova@studenti.unisa.it';";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    }

    @Test
    public void coverage1() throws Exception {
    	
    	sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'p.prova@studenti.unisa.it',1,3);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        sql = "INSERT INTO attached VALUES (1,'str',1,'p.prova@studenti.unisa.it');";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    	
    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  

        request.setParameter("flag", "1");
        
    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }
    
    @Test
    public void coverage2() throws Exception {
    	
    	sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'p.prova@studenti.unisa.it',1,4);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        sql = "INSERT INTO attached VALUES (1,'str',1,'p.prova@studenti.unisa.it');";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    	
    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  

        request.setParameter("flag", "1");
        
    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }
    
    @Test
    public void coverage3() throws Exception {
    	
    	sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'p.prova@studenti.unisa.it',1,5);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        sql = "INSERT INTO attached VALUES (1,'str',1,'p.prova@studenti.unisa.it');";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    	
    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  

        request.setParameter("flag", "1");
        
    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }
    
    @Test
    public void coverage4() throws Exception {
    	
    	sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,0,'p.prova@studenti.unisa.it',1,6);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        sql = "INSERT INTO attached VALUES (1,'str',1,'p.prova@studenti.unisa.it');";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    	
    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  

        request.setParameter("flag", "1");
        
    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }
    
    @Test
    public void coverage5() throws Exception {
    	
    	sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'p.prova@studenti.unisa.it',1,7);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        sql = "INSERT INTO attached VALUES (1,'str',1,'p.prova@studenti.unisa.it');";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    	
    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  

        request.setParameter("flag", "1");
        
    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }
    
    @Test
    public void coverage6() throws Exception {
    	
    	sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'p.prova@studenti.unisa.it',1,7);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        sql = "INSERT INTO attached VALUES (1,'str',1,'p.prova@studenti.unisa.it');";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    	
    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  

        request.setParameter("flag", "2");
        request.setParameter("idRequest", "1");
        request.setParameter("type", "6");
        
    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }
    
    @Test
    public void coverage7() throws Exception {
    	
    	sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'p.prova@studenti.unisa.it',1,7);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        sql = "INSERT INTO attached VALUES (1,'str',1,'p.prova@studenti.unisa.it');";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    	
    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  

        request.setParameter("flag", "2");
        request.setParameter("idRequest", "-1");
        request.setParameter("type", "-2048");
        
    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }
    
    @Test
    public void coverage8() throws Exception {
    	
    	sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'p.prova@studenti.unisa.it',1,7);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        sql = "INSERT INTO attached VALUES (1,'str',1,'p.prova@studenti.unisa.it');";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    	
    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  

        request.setParameter("flag", "3");
        request.setParameter("idRequest", "1");
        
    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }
    
    @Test
    public void coverage9() throws Exception {
    	
    	sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'p.prova@studenti.unisa.it',1,7);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        sql = "INSERT INTO attached VALUES (1,'str',1,'p.prova@studenti.unisa.it');";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    	
    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  

        request.setParameter("flag", "3");
        request.setParameter("idRequest", "-1");
        
    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }
    
    @Test
    public void coverage10() throws Exception {
    	
    	sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'p.prova@studenti.unisa.it',1,7);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        sql = "INSERT INTO attached VALUES (1,'str',1,'p.prova@studenti.unisa.it');";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    	
    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  

        request.setParameter("flag", "4");
        request.setParameter("idRequest", "1");
        
    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }
    
    @Test
    public void coverage11() throws Exception {
    	
    	sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'p.prova@studenti.unisa.it',1,7);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        sql = "INSERT INTO attached VALUES (1,'str',1,'p.prova@studenti.unisa.it');";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    	
    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  

        request.setParameter("flag", "4");
        request.setParameter("idRequest", "-1");
        
    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }
    
    @Test
    public void coverage12() throws Exception {
    	
    	sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'p.prova@studenti.unisa.it',1,7);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        sql = "INSERT INTO attached VALUES (1,'str',1,'p.prova@studenti.unisa.it');";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    	
    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        
        UserInterface user = null;
        user = new Student("p.prova@studenti.unisa.it", "Thomas", "Amendola", 'M', "Gi230198", 2);
        request.getSession().setAttribute("user", user);
        
        request.setParameter("flag", "5");
        
    	servlet.doGet(request, response);

        assertEquals("application/vnd.ms-excel", response.getContentType());
    }
    
    @Test
    public void coverage13() throws Exception {
    	
    	sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'p.prova@studenti.unisa.it',1,7);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        sql = "INSERT INTO attached VALUES (1,'str',1,'p.prova@studenti.unisa.it');";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    	
    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        
        UserInterface user = null;
        user = new Student("p.prova@studenti.unisa.it", "Thomas", "Amendola", 'M', "Gi230198", 2);
        request.getSession().setAttribute("user", user);
        
        request.setParameter("flag", "6");
        
    	servlet.doGet(request, response);

        assertEquals("application/vnd.ms-excel", response.getContentType());
    }
    
    @Test
    public void coverage14() throws Exception {
    	
    	sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'p.prova@studenti.unisa.it',1,5);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        sql = "INSERT INTO attached VALUES (1,'str',1,'p.prova@studenti.unisa.it');";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    	
    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        
        UserInterface user = null;
        user = new Student("p.prova@studenti.unisa.it", "Thomas", "Amendola", 'M', "Gi230198", 2);
        request.getSession().setAttribute("user", user);
        
        request.setParameter("flag", "6");
        
    	servlet.doGet(request, response);

        assertEquals("application/vnd.ms-excel", response.getContentType());
    }
    
    @Test
    public void coverage15() throws Exception {
    	
    	sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'p.prova@studenti.unisa.it',1,4);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        sql = "INSERT INTO attached VALUES (1,'str',1,'p.prova@studenti.unisa.it');";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    	
    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        
        UserInterface user = null;
        user = new Student("p.prova@studenti.unisa.it", "Thomas", "Amendola", 'M', "Gi230198", 2);
        request.getSession().setAttribute("user", user);
        
        request.setParameter("flag", "5");
        
    	servlet.doGet(request, response);

        assertEquals("application/vnd.ms-excel", response.getContentType());
    }
}