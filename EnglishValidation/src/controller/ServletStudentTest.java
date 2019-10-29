package controller;

import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.fail;

import org.junit.Assert;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.ServletStudent;

public class ServletStudentTest{

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
	
	@Test
	public void tc_01_1() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("Thomas", "", "g.Colonia13@studenti.unisa.it", "M", "Gi230198", "Gi230198");
        
        x.setRequest(request);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));
    
        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}
	
	@Test
	public void tc_01_2() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("Thomas", "Carifragilisticospiralitosibankspiccionigrassi", "g.Colonia13@studenti.unisa.it", "M", "Gi230198", "Gi230198");
        
        x.setRequest(request);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));
    
        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}
	
	@Test
	public void tc_01_3() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("Thomas", "Amendola2132121231321231321321321321321321313", "g.Colonia13@studenti.unisa.it", "M", "Gi230198", "Gi230198");
        
        x.setRequest(request);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));
    
        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}
	
	@Test
	public void tc_01_4() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("", "Amendola", "g.Colonia13@studenti.unisa.it", "M", "Gi230198", "Gi230198");
        
        x.setRequest(request);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));
    
        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}
	
	@Test
	public void tc_01_5() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("MariaTorinelliLongobardiAntonioFerruccio", "Amendola", "g.Colonia13@studenti.unisa.it", "M", "Gi230198", "Gi230198");
        
        x.setRequest(request);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));
    
        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}
	
	@Test
	public void tc_01_6() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("Thomas.*\\\\d+.*§°ç°§ç°§ççé*çé**éç*éçé*çé*", "Amendola", "g.Colonia13@studenti.unisa.it", "M", "Gi230198", "Gi230198");
        
        x.setRequest(request);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));
    
        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}
	
	@Test
	public void tc_01_7() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("Thomas", "Amendola", "g@studenti.unisa.it", "M", "Gi230198", "Gi230198");
        
        x.setRequest(request);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));
    
        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}
	
	@Test
	public void tc_01_8() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("Thomas", "Amendola", "g.Amendola@studenti.it", "M", "Gi230198", "Gi230198");
        
        x.setRequest(request);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));
    
        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}
	
	@Test
	public void tc_01_9() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("Thomas", "Amendola", "g.Amendola@studenti.unisa.it", "M", "Gi230198", "Gi230198");
        
        x.setRequest(request);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));
    
        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}
	
	@Test
	public void tc_01_10() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("Thomas", "Amendola", "g.Amendola@studenti.unisa.it", "G", "Gi230198", "Gi230198");
        
        x.setRequest(request);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));
    
        if(e == null) fail (); else Assert.assertEquals("Valore non corretto", e.getMessage());
	}
	
	@Test
	public void tc_01_11() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("Thomas", "Amendola", "g.Amendola@studenti.unisa.it", "M", "Gi2", "Gi2");
        
        x.setRequest(request);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));
    
        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}
	
	@Test
	public void tc_01_12() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("Thomas", "Amendola", "g.Amendola@studenti.unisa.it", "M", ".*\\\\d+.*", ".*\\\\d+.*");
        
        x.setRequest(request);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));
    
        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}
	
	@Test
	public void tc_01_13() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("Thomas", "Amendola", "g.Amendola@studenti.unisa.it", "M", "Gi230198", "Gi2");
        
        x.setRequest(request);
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> new ServletStudent().doPost(request, response));
    
        if(e == null) fail (); else Assert.assertEquals("Formato non corretto", e.getMessage());
	}
	
	@Test
	public void tc_01_14() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("Thomas","Amendola","g.Amendola@studenti.unisa.it","M","Gi230198","Gi230198");
        
        x.setRequest(request);
        
        new ServletStudent().doPost(request, response);
        
         
	}
	
}