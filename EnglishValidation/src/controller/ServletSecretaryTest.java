package controller;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.ServletStudentTest.MockObject;

class ServletSecretaryTest {

	public class MockObject {
		public MockObject (String idRequest, String cfu,String flag) {
			this.idRequest = idRequest;
			this.cfu = cfu;
			this.flag=flag;
			
		}
		
		public void setRequest (MockHttpServletRequest request) {
			request.addParameter("flag", flag);
			request.addParameter("idRequest", idRequest);
	        request.addParameter("cfu", cfu);
	        
		} 
		
		private String idRequest;
		private String cfu;
		private String flag;
	}
	
	@Test
	public void tc_01_1() throws Exception {
		Connection conn = new DbConnection().getInstance().getConn();
		 Statement stmt = conn.createStatement();
		String query="INSERT into request (ID_REQUEST, CERTIFICATE_SERIAL, LEVEL, RELEASE_DATE, EXPIRY_DATE, YEAR, REQUESTED_CFU, SERIAL, VALIDATED_CFU, FK_USER, FK_CERTIFIER, FK_STATE)"
		+		"VALUES ('2', 'B.6.56546', 'A1', '2017-05-25', '2018-05-25', 2018, '3', '512103579', '100', '04wmljf0wy.@studenti.unisa.it', '7', '2')";
		stmt.executeUpdate(query);
		 query="INSERT into attached (ID_ATTACHED, FILENAME, FK_REQUEST, FK_USER)"
				+		"VALUES ('3', 'certificato.pdf', '2', '04wmljf0wy.@studenti.unisa.it')";
				stmt.executeUpdate(query);
		
		
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("2","100","1");
        
        x.setRequest(request); 
        conn.commit();
       new ServletSecretary().doPost(request, response);
       
       stmt = conn.createStatement();
		query="DELETE from attached WHERE ID_ATTACHED = 3;";
		 stmt.executeUpdate(query);
       
       query="DELETE from request WHERE ID_REQUEST = 2;";
       stmt.executeUpdate(query);
		
		conn.commit();
     
	}

	@Test
	public void tc_01_2() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("1","12","2");
        
        x.setRequest(request);
        new ServletSecretary().doPost(request, response);
        
        
    
     
	}
	
	@Test
	public void tc_01_3() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("8577934","123","3");
        
        x.setRequest(request);
        new ServletSecretary().doPost(request, response);
        

    
     
	}
	
	@Test
	public void tc_01_4() throws Exception {
		
	
		
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("2","100","1");
        
        x.setRequest(request); 
       new ServletSecretary().doPost(request, response);
      
       
       
       
     
	}
	
	@Test
	public void tc_01_5() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();  
        MockHttpServletResponse response = new MockHttpServletResponse();  
        MockObject x = new MockObject("122","3212","2");
        
        x.setRequest(request);
        new ServletSecretary().doPost(request, response);
}
}

