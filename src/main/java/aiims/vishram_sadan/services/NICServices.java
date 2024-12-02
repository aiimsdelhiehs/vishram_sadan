package aiims.vishram_sadan.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import aiims.vishram_sadan.dto.DemographicDto;
import jakarta.transaction.Transactional;



@Service

@Transactional
public class NICServices {

	private static final Logger logger = LogManager.getLogger(NICServices.class);
	
	@Value("${spring.datasource.nic.url}")
	private String nicApiUrl;
	

	
	@Value("${spring.datasource.nic.username}")
	private String nicApiUsername;
	
	@Value("${spring.datasource.nic.password}")
	private String nicApiPassword;
	
	@Value("${spring.datasource.nic.driver-class-name}")
	private String nicApiDriverClassName;
	
	@Value("${spring.datasource.nic.active}")
	private boolean nicApiActive;
	
	@Value("${application.test.uhid}")
	private String testUHIDs;
	
	
		

	public DemographicDto fetchDemographicDataFromUHID(String uhid) throws Exception {
		DemographicDto data=new DemographicDto();
		     if(uhid == null || uhid.contentEquals("") || uhid.length() != 9) { 
			    throw new Exception("Invalid UHID no.");
		     }
	         else if(nicApiActive) {
			    Connection connection = null;
		        PreparedStatement preparedStatement = null;
		        ResultSet resultSet = null;
		        try {
		    		connection = DriverManager.getConnection(nicApiUrl, nicApiUsername, nicApiPassword);
				    String sqlQuery = "SELECT p_fname, p_mname, p_lname, p_mobile_no,p_address FROM patient_detail WHERE reg_no = ?";
		            preparedStatement = connection.prepareStatement(sqlQuery);
		            preparedStatement.setString(1, uhid);
		            resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){ 
                    	String fullname = resultSet.getString("p_fname")+" "+resultSet.getString("p_mname")+" "+resultSet.getString("p_lname");
                    	String uhidType = (resultSet.getInt("p_cat_id") == 4 || resultSet.getInt("p_cat_id") == 5 || resultSet.getInt("p_cat_id") == 6)?"IPD":"OPD";
                    	return new DemographicDto(fullname,resultSet.getString("p_mobile_no"),resultSet.getString ("p_address"),uhid);
	                }
                    else {
                       throw new Exception("No user found with provided uhid no: "+uhid);	
                    }
		         }catch(Exception e) {
		        	 logger.error("NIC API error: {}",e.getMessage());
		        	 throw e;
		       	 }
	 	      	 finally {
	 	  			    try {
	 	  	                if (resultSet != null) resultSet.close();
	 	  	                if (preparedStatement != null) preparedStatement.close();
	 	  	                if (connection != null) connection.close();
	 	  	            } catch (SQLException e) {
	 	  	                logger.error("NIC API error2: {}",e.getMessage());
	 	  	                
	 	  	            }
	 	  		 }
		
	         }
			 else if(uhid.contentEquals("987654321")) {
				   return new DemographicDto("Sahil", "7065643718", " Delhi ", uhid);
			 }
		     else
		    	 return new DemographicDto("Sahil", "7065643718", " Delhi ", uhid);
		       //throw new Exception("NIC API SERVER IS DOWN");
  }
}