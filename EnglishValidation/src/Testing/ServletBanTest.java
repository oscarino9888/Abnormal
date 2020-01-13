package Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.DbConnection;
import controller.ServletBan;
import controller.ServletCommon;

public class ServletBanTest {

	public class MockObject {
		public MockObject (String email, String ban_user) {
			this.email = email;
			this.ban_user = ban_user;
		}

		public void setRequest (MockHttpServletRequest request) {
			request.addParameter("email", email);
	        request.addParameter("ban_user", ban_user);
		} 

		private String email;
		private String ban_user;
	}

    private ServletBan servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;


    Connection conn = DbConnection.getInstance().getConn();
    String sql = "";
    PreparedStatement stmt = null;

    @Before
    public void setUp() throws SQLException {
        servlet = new ServletBan();
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
    public void test1() throws Exception {

    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("p.prova@studenti.unisa.it", "2100-09-01");

        x.setRequest(request);

    	servlet.doPost(request, response);

        assertEquals("_areaAdmin/banUser.jsp", response.getForwardedUrl());
    }

    @Test
    public void test2() throws Exception {

    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("aaaaaaa", "2100-09-01");

        x.setRequest(request);

    	Exception e = Assertions.assertThrows(Exception.class, () -> new ServletBan().doPost(request, response));

        if(e == null) fail (); else Assert.assertEquals("Utente non registrato", e.getMessage());
    }

    @Test
    public void test3() throws Exception {

    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("p.prova@studenti.unisa.it", "aaaaaaaa");

        x.setRequest(request);

        Exception e = Assertions.assertThrows(Exception.class, () -> new ServletBan().doPost(request, response));

        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
    }
}