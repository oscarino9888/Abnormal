package Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Year;

import javax.servlet.ServletException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.DbConnection;
import controller.ServletStudent;
import interfacce.UserInterface;
import model.Student;
import controller.ServletCommon;

public class ServletStudentCoverage {

	public class MockObject {
		public MockObject (String nome, String cognome,String _email, String sesso, String password,String verifica) {
			name = nome;
			surname = cognome;
			sex = sesso;
			email= _email;
			pass = password;
			verifyPass = verifica;
		}

		public void setRequest (MockHttpServletRequest request) {
			request.addParameter("flag", "1");
			request.addParameter("surname", surname);
	        request.addParameter("name", name);
	        request.addParameter("email", email);
	        request.addParameter("sex", sex);
	        request.addParameter("password", pass);
	        request.addParameter("verifyPassword", verifyPass);
		} 

		private String name;
		private String surname;
		private String email;
		private String sex;
		private String pass;
		private String verifyPass;
	}

	public class MockObjectTwo {

		public MockObjectTwo (String id, String c_serial, String level, String release_data, String expire_data, String year, String r_cfu, String serial, String v_cfu, String user, String certifier, String state) {
			this.id = id;
			this.c_serial = c_serial;
			this.level = level;
			this.release_data = release_data;
			this.expire_data = expire_data;
			this.year = year;
			this.r_cfu = r_cfu;
			this.serial = serial;
			this.v_cfu = v_cfu;
			this.user = user;
			this.certifier = certifier;
			this.state = state;
		}

		public void setRequest (MockHttpServletRequest request) {
			request.addParameter("flag", "2");
			request.addParameter("year", year);
			request.addParameter("serial", serial);
	        request.addParameter("idEnte", state);
	        request.addParameter("expiryDate", expire_data);
	        request.addParameter("releaseDate", release_data);
	        request.addParameter("certificateSerial", c_serial);
	        request.addParameter("requestedCfu", r_cfu);
	        request.addParameter("level", level);
		} 

		private String id;
		private String c_serial;
		private String level;
		private String release_data;
		private String expire_data;
		private String year;
		private String r_cfu;
		private String serial;
		private String v_cfu;
		private String user;
		private String certifier;
		private String state;

	}

    private ServletStudent servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;


    Connection conn = DbConnection.getInstance().getConn();
    String sql = "";
    PreparedStatement stmt = null;

    @Before
    public void setUp() throws SQLException {
        servlet = new ServletStudent();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();

        sql = "DELETE FROM attached WHERE FK_USER = 'p.prova@studenti.unisa.it';";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        sql = "DELETE FROM request WHERE ID_REQUEST = 1;";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        sql = "DELETE FROM request WHERE FK_USER = 'p.prova@studenti.unisa.it';";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    }

    @Test
    public void coverage1() throws Exception {

    	sql = "DELETE FROM user WHERE EMAIL = 'p.prova@studenti.unisa.it';";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

    	MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("Thomas", "Amendola", "p.prova@studenti.unisa.it", "M", "Gi230198", "Gi230198");

        x.setRequest(request);

    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
    }

    @Test
	public void coverage2() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("Thomas", "", "p.prova@studenti.unisa.it", "M", "Gi230198", "Gi230198");

        x.setRequest(request);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));

        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}

	@Test
	public void coverage3() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("Thomas", "Carifragilisticospiralitosibankspiccionigrassi", "p.prova@studenti.unisa.it", "M", "Gi230198", "Gi230198");

        x.setRequest(request);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));

        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}

	@Test
	public void coverage4() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("Thomas", "Amendola2132121231321231321321321321321321313", "p.prova@studenti.unisa.it", "M", "Gi230198", "Gi230198");

        x.setRequest(request);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));

        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}

	@Test
	public void coverage5() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("", "Amendola", "p.prova@studenti.unisa.it", "M", "Gi230198", "Gi230198");

        x.setRequest(request);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));

        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}

	@Test
	public void coverage6() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("MariaTorinelliLongobardiAntonioFerruccio", "Amendola", "p.prova@studenti.unisa.it", "M", "Gi230198", "Gi230198");

        x.setRequest(request);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));

        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}

	@Test
	public void coverage7() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("Thomas.*\\\\d+.*§°ç°§ç°§ççé*çé**éç*éçé*çé*", "Amendola", "p.prova@studenti.unisa.it", "M", "Gi230198", "Gi230198");

        x.setRequest(request);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));

        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}

	@Test
	public void coverage8() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("Thomas", "Amendola", "p@studenti.unisa.it", "M", "Gi230198", "Gi230198");

        x.setRequest(request);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));

        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}

	@Test
	public void coverage9() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("Thomas", "Amendola", "p.prova@studenti.unisa.it", "G", "Gi230198", "Gi230198");

        x.setRequest(request);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));

        if(e == null) fail (); else Assert.assertEquals("Valore non corretto", e.getMessage());
	}

	@Test
	public void coverage10() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("Thomas", "Amendola", "p.prova@studenti.unisa.it", "M", "Gi2", "Gi2");

        x.setRequest(request);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));

        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}


	@Test
	public void coverage14() throws Exception {

    	MockHttpServletRequest request = new MockHttpServletRequest(); 
        MockHttpServletResponse response = new MockHttpServletResponse();

        MockObjectTwo x = new MockObjectTwo("1","111111111","B2","1990-09-01","1990-09-01","","10","111111111","0","p.prova@studenti.unisa.it","1","1");
        x.setRequest(request);

        UserInterface user = null;
        user = new Student("p.prova@studenti.unisa.it", "Thomas", "Amendola", 'M', "Gi230198", 1);
        request.getSession().setAttribute("user", user);

        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));

        if(e == null) fail (); else Assert.assertEquals("Valore non corretto", e.getMessage());

    }

	@Test
	public void coverage15() throws Exception {

    	MockHttpServletRequest request = new MockHttpServletRequest(); 
        MockHttpServletResponse response = new MockHttpServletResponse();

        MockObjectTwo x = new MockObjectTwo("1","111111111","B2","1990-09-01","1990-09-01","1990","10","1","0","p.prova@studenti.unisa.it","1","1");
        x.setRequest(request);

        UserInterface user = null;
        user = new Student("p.prova@studenti.unisa.it", "Thomas", "Amendola", 'M', "Gi230198", 1);
        request.getSession().setAttribute("user", user);

        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));

        if(e == null) fail (); else Assert.assertEquals("Valore non corretto", e.getMessage());

    }

	@Test
	public void coverage16() throws Exception {

    	MockHttpServletRequest request = new MockHttpServletRequest(); 
        MockHttpServletResponse response = new MockHttpServletResponse();

        MockObjectTwo x = new MockObjectTwo("1","111111111","B2","1990-09-01","1990-09-01","1990","10","111111111","0","p.prova@studenti.unisa.it","1","0");
        x.setRequest(request);

        UserInterface user = null;
        user = new Student("p.prova@studenti.unisa.it", "Thomas", "Amendola", 'M', "Gi230198", 1);
        request.getSession().setAttribute("user", user);

        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));

        if(e == null) fail (); else Assert.assertEquals("Valore non corretto", e.getMessage());

    }

	@Test
	public void coverage17() throws Exception {

    	MockHttpServletRequest request = new MockHttpServletRequest(); 
        MockHttpServletResponse response = new MockHttpServletResponse();

        MockObjectTwo x = new MockObjectTwo("1","111111111","B2","","1990-09-01","1990","10","111111111","0","p.prova@studenti.unisa.it","1","1");
        x.setRequest(request);

        UserInterface user = null;
        user = new Student("p.prova@studenti.unisa.it", "Thomas", "Amendola", 'M', "Gi230198", 1);
        request.getSession().setAttribute("user", user);

        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));

        if(e == null) fail (); else Assert.assertEquals("Valore non corretto", e.getMessage());

    }

	@Test
	public void coverage18() throws Exception {

    	MockHttpServletRequest request = new MockHttpServletRequest(); 
        MockHttpServletResponse response = new MockHttpServletResponse();

        MockObjectTwo x = new MockObjectTwo("1","111111111","B2","1990-09-01","","1990","10","111111111","0","p.prova@studenti.unisa.it","1","1");
        x.setRequest(request);

        UserInterface user = null;
        user = new Student("p.prova@studenti.unisa.it", "Thomas", "Amendola", 'M', "Gi230198", 1);
        request.getSession().setAttribute("user", user);

        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));

        if(e == null) fail (); else Assert.assertEquals("Valore non corretto", e.getMessage());

    }

	@Test
	public void coverage19() throws Exception {

    	MockHttpServletRequest request = new MockHttpServletRequest(); 
        MockHttpServletResponse response = new MockHttpServletResponse();

        MockObjectTwo x = new MockObjectTwo("1","111111111","B2222222","1990-09-01","1990-09-01","1990","10","111111111","0","p.prova@studenti.unisa.it","1","1");
        x.setRequest(request);

        UserInterface user = null;
        user = new Student("p.prova@studenti.unisa.it", "Thomas", "Amendola", 'M', "Gi230198", 1);
        request.getSession().setAttribute("user", user);

        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));

        if(e == null) fail (); else Assert.assertEquals("Valore non corretto", e.getMessage());

    }

	@Test
	public void coverage20() throws Exception {

    	MockHttpServletRequest request = new MockHttpServletRequest(); 
        MockHttpServletResponse response = new MockHttpServletResponse();

        MockObjectTwo x = new MockObjectTwo("1","","B2","1990-09-01","1990-09-01","1990","10","111111111","0","p.prova@studenti.unisa.it","1","1");
        x.setRequest(request);

        UserInterface user = null;
        user = new Student("p.prova@studenti.unisa.it", "Thomas", "Amendola", 'M', "Gi230198", 1);
        request.getSession().setAttribute("user", user);

        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));

        if(e == null) fail (); else Assert.assertEquals("Valore non corretto", e.getMessage());

    }

	@Test
	public void coverage21() throws Exception {

    	MockHttpServletRequest request = new MockHttpServletRequest(); 
        MockHttpServletResponse response = new MockHttpServletResponse();

        MockObjectTwo x = new MockObjectTwo("1","111111111","","1990-09-01","1990-09-01","1990","10","111111111","0","p.prova@studenti.unisa.it","1","1");
        x.setRequest(request);

        UserInterface user = null;
        user = new Student("p.prova@studenti.unisa.it", "Thomas", "Amendola", 'M', "Gi230198", 1);
        request.getSession().setAttribute("user", user);

        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));

        if(e == null) fail (); else Assert.assertEquals("Valore non corretto", e.getMessage());

    }

	@Test
	public void coverage22() throws Exception {

    	MockHttpServletRequest request = new MockHttpServletRequest(); 
        MockHttpServletResponse response = new MockHttpServletResponse();

        MockObjectTwo x = new MockObjectTwo("1","111111111","B2","1990-09-01","1990-09-01","1990","999","111111111","0","p.prova@studenti.unisa.it","1","1");
        x.setRequest(request);

        UserInterface user = null;
        user = new Student("p.prova@studenti.unisa.it", "Thomas", "Amendola", 'M', "Gi230198", 1);
        request.getSession().setAttribute("user", user);

        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));

        if(e == null) fail (); else Assert.assertEquals("Valore non corretto", e.getMessage());

    }

	@Test
	public void coverage11() throws Exception {

    	MockHttpServletRequest request = new MockHttpServletRequest(); 
        MockHttpServletResponse response = new MockHttpServletResponse();

        MockObjectTwo x = new MockObjectTwo("1","111111111","B2","1990-09-01","1990-09-01","1990","10","111111111","0","p.prova@studenti.unisa.it","1","1");

        x.setRequest(request);
        UserInterface user = null;
        user = new Student("p.prova@studenti.unisa.it", "Thomas", "Amendola", 'M', "Gi230198", 1);
        request.getSession().setAttribute("user", user);

        /*
        sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'p.prova@studenti.unisa.it',1,2);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    	
        sql = "INSERT INTO attached VALUES (1,'str',1,'p.prova@studenti.unisa.it');";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        */

    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());

        /*
        sql = "INSERT INTO user VALUES ('p.prova@studenti.unisa.it', 'Thomas', 'Amendola', 'M', 'Gi230198', 0);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();*/

    }

	@Test
	public void coverage12() throws Exception {


    	MockHttpServletRequest request = new MockHttpServletRequest(); 
        MockHttpServletResponse response = new MockHttpServletResponse();

        MockObjectTwo x = new MockObjectTwo("1","111111111","B2","1990-09-01","1990-09-01","1990","10","111111111","0","p.prova@studenti.unisa.it","1","1");

        x.setRequest(request);
        UserInterface user = null;
        user = new Student("p.prova@studenti.unisa.it", "Thomas", "Amendola", 'M', "Gi230198", 1);
        request.getSession().setAttribute("user", user);

        sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'p.prova@studenti.unisa.it',1,2);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

    	servlet.doPost(request, response);

        assertEquals("json", response.getContentType());

    }

	@Test
	public void coverage23() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest(); 
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.addParameter("filenames[]", "file.aaa");
        request.addParameter("filenames[]", "file2.aaa");
        request.setParameter("flag", "3");

        request.getSession().setAttribute("idRequest", 1);

        UserInterface user = null;
        user = new Student("p.prova@studenti.unisa.it", "Thomas", "Amendola", 'M', "Gi230198", 1);
        request.getSession().setAttribute("user", user);

        sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'p.prova@studenti.unisa.it',1,1);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));

        if(e == null) fail (); else Assert.assertEquals("Valore non corretto", e.getMessage());
	}

	@Test
	public void coverage24() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest(); 
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.addParameter("filenames[]", "file.pdf");
        request.addParameter("filenames[]", "file2.aaa");
        request.setParameter("flag", "3");

        request.getSession().setAttribute("idRequest", 1);

        UserInterface user = null;
        user = new Student("p.prova@studenti.unisa.it", "Thomas", "Amendola", 'M', "Gi230198", 1);
        request.getSession().setAttribute("user", user);

        sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'p.prova@studenti.unisa.it',1,1);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));

        if(e == null) fail (); else Assert.assertEquals("Valore non corretto", e.getMessage());
	}

	@Test
	public void coverage25() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest(); 
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.addParameter("filenames[]", "file.pdf");
        request.setParameter("flag", "3");

        request.getSession().setAttribute("idRequest", 1);

        UserInterface user = null;
        user = new Student("p.prova@studenti.unisa.it", "Thomas", "Amendola", 'M', "Gi230198", 1);
        request.getSession().setAttribute("user", user);

        sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'p.prova@studenti.unisa.it',1,1);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));

        if(e == null) fail (); else Assert.assertEquals("Valore non corretto", e.getMessage());
	}

	@Test
	public void coverage13() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest(); 
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.addParameter("filenames[]", "file.pdf");
        request.addParameter("filenames[]", "file2.pdf");
        request.setParameter("flag", "3");

        request.getSession().setAttribute("idRequest", 1);

        UserInterface user = null;
        user = new Student("p.prova@studenti.unisa.it", "Thomas", "Amendola", 'M', "Gi230198", 1);
        request.getSession().setAttribute("user", user);

        sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'p.prova@studenti.unisa.it',1,1);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
	}

	@Test
	public void coverage27() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest(); 
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setParameter("flag", "4");

        request.getSession().setAttribute("idRequest", 1);

        UserInterface user = null;
        user = new Student("p.prova@studenti2.unisa.it", "Thomas", "Amendola", 'M', "Gi230198", 1);
        request.getSession().setAttribute("user", user);

        sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'p.prova@studenti.unisa.it',1,1);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        sql = "INSERT INTO attached VALUES (1,'str',1,'p.prova@studenti.unisa.it');";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
	}

	@Test
	public void coverage26() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest(); 
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setParameter("flag", "4");

        request.getSession().setAttribute("idRequest", 1);

        UserInterface user = null;
        user = new Student("p.prova@studenti.unisa.it", "Thomas", "Amendola", 'M', "Gi230198", 1);
        request.getSession().setAttribute("user", user);

        sql = "INSERT INTO request VALUES (1,'str','1','1990-09-01','1990-09-01',1990,1,1111,1,'p.prova@studenti.unisa.it',1,1);";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        sql = "INSERT INTO attached VALUES (1,'str',1,'p.prova@studenti.unisa.it');";
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        servlet.doPost(request, response);

        assertEquals("json", response.getContentType());
	}

	@After
	public void onEnd() throws Exception{


	}
}