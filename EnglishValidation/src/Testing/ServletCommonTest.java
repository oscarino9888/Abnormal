package Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import Testing.ServletCommonCoverage.MockObject;
import controller.DbConnection;
import controller.ServletStudent;
import controller.ServletCommon;

public class ServletCommonTest {
	
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
        
    }
    
	@Test
	public void tc_Common_1() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("sbagliato", "Gi230198");
        
        x.setRequest(request);
    	
        request.setParameter("flag", "1");
        
        new ServletCommon().doPost(request, response);
        
        assertEquals(true, response.getContentAsString().contains("Username o Password errati."));
	}
	
	@Test
	public void tc_Common_2() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("p.prova@studenti.unisa.it", "sbagliato");
        
        x.setRequest(request);
    	
        request.setParameter("flag", "1");
        
        new ServletCommon().doPost(request, response);
        
        assertEquals(true, response.getContentAsString().contains("Username o Password errati."));
	}
	
	@Test
	public void tc_Common_3() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("p.prova@studenti.unisa.it", "Gi230198");
        
        x.setRequest(request);
    	
        request.setParameter("flag", "1");
        
        new ServletCommon().doPost(request, response);
        
        assertEquals("json", response.getContentType());
	}
	
	@Test
	public void tc_Common_4() throws Exception {
		
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
        
        new ServletCommon().doPost(request, response);
        
        assertEquals(true, response.getContentAsString().contains("/banned.jsp"));
	}
	
	@Test
	public void tc_Common_5() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
    	
        request.setParameter("flag", "2");
        request.setParameter("idUser", "");
        request.setParameter("newName", "Thomas");
        
        new ServletCommon().doPost(request, response);
        
        assertEquals(true, response.getContentAsString().contains("Errore aggiornamento Nome."));
	}
	
	@Test
	public void tc_Common_6() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
    	
        request.setParameter("flag", "2");
        request.setParameter("idUser", "p.prova@studenti.unisa.it");
        request.setParameter("newName", "");
        
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletCommon().doPost(request, response));
        
        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}
	
	@Test
	public void tc_Common_7() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
    	
        request.setParameter("flag", "2");
        request.setParameter("idUser", "p.prova@studenti.unisa.it");
        request.setParameter("newName", ".//&&%%?^");
        
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletCommon().doPost(request, response));
        
        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}
	
	@Test
	public void tc_Common_8() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
    	
        request.setParameter("flag", "2");
        request.setParameter("idUser", "p.prova@studenti.unisa.it");
        request.setParameter("newName", "Thomasssssssssssssssssssssssssssssssss");
        
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletCommon().doPost(request, response));
        
        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}
	
	@Test
	public void tc_Common_9() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
    	
        request.setParameter("flag", "2");
        request.setParameter("idUser", "p.prova@studenti.unisa.it");
        request.setParameter("newName", "Thomas");
        
        new ServletCommon().doPost(request, response);
        
        assertEquals("json", response.getContentType());
	}
	
	@Test
	public void tc_Common_10() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
    	
        request.setParameter("flag", "3");
        request.setParameter("idUser", "");
        request.setParameter("newSurname", "Amendola");
        
        new ServletCommon().doPost(request, response);
        
        assertEquals(true, response.getContentAsString().contains("Errore aggiornamento Cognome."));
	}
	
	@Test
	public void tc_Common_11() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
    	
        request.setParameter("flag", "3");
        request.setParameter("idUser", "p.prova@studenti.unisa.it");
        request.setParameter("newSurname", "");
        
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletCommon().doPost(request, response));
        
        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}
	
	@Test
	public void tc_Common_12() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
    	
        request.setParameter("flag", "3");
        request.setParameter("idUser", "p.prova@studenti.unisa.it");
        request.setParameter("newSurname", ".//&&%%?^");
        
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletCommon().doPost(request, response));
        
        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}
	
	@Test
	public void tc_Common_13() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
    	
        request.setParameter("flag", "3");
        request.setParameter("idUser", "p.prova@studenti.unisa.it");
        request.setParameter("newSurname", "Amendolaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletCommon().doPost(request, response));
        
        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}
	
	@Test
	public void tc_Common_14() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
    	
        request.setParameter("flag", "3");
        request.setParameter("idUser", "p.prova@studenti.unisa.it");
        request.setParameter("newSurname", "Amendola");
        
        new ServletCommon().doPost(request, response);
        
        assertEquals("json", response.getContentType());
	}

}
