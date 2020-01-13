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
import controller.ServletCommon;

public class ServletCommonCoverage {

	public class MockObject {
		public MockObject (String email, String password) {
			this.email = email;
			this.password = password;
		}

		public void setRequest (MockHttpServletRequest request) {
			request.addParameter("email", email);
	        request.addParameter("password", password);
		} 

		private String email;
		private String password;
	}

    private ServletCommon servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;


    Connection conn = DbConnection.getInstance().getConn();
    String sql = "";
    PreparedStatement stmt = null;

    @Before
    public void setUp() throws SQLException {
        servlet = new ServletCommon();
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
    }

    @Test
    public void coverage4() throws Exception {

    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("", "");

        x.setRequest(request);

        request.setParameter("flag", "1");

    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }

    @Test
    public void coverage1() throws Exception {

    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("p.prova@studenti.unisa.it", "Gi230198");

        x.setRequest(request);

        request.setParameter("flag", "1");

    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }

    @Test
    public void coverage2() throws Exception {

    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("segreteria@unisa.it", "password");

        x.setRequest(request);

        request.setParameter("flag", "1");

    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }

    @Test
    public void coverage3() throws Exception {

    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("fferrucci@unisa.it", "password");

        x.setRequest(request);

        request.setParameter("flag", "1");

    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }

    @Test
    public void coverage5() throws Exception {

    	sql = "DELETE FROM user WHERE EMAIL = 'p.prova2@studenti.unisa.it';";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

    	sql = "INSERT INTO user VALUES ('p.prova2@studenti.unisa.it', 'Thomas', 'Amendola', 'M', 'dfab23abcd54ff99d6e668a9fb9c977d352228b8', 0, '2100-09-01');";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("p.prova2@studenti.unisa.it", "Gi230198");

        x.setRequest(request);

        request.setParameter("flag", "1");

    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());

    }

    @Test
    public void coverage6() throws Exception {

    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  


        request.setParameter("flag", "2");
        request.setParameter("idUser", "p.prova@studenti.unisa.it");
        request.setParameter("newName", "Thomas");
    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }

    @Test
    public void coverage7() throws Exception {

    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  


        request.setParameter("flag", "2");
        request.setParameter("idUser", "p.prova3@studenti.unisa.it");
        request.setParameter("newName", "Thomas");
    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }

    @Test
    public void coverage8() throws Exception {

    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  


        request.setParameter("flag", "3");
        request.setParameter("idUser", "p.prova@studenti.unisa.it");
        request.setParameter("newSurname", "Amendola");
    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }

    @Test
    public void coverage9() throws Exception {

    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  


        request.setParameter("flag", "3");
        request.setParameter("idUser", "p.prova3@studenti.unisa.it");
        request.setParameter("newSurname", "Amendola");
    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }
}