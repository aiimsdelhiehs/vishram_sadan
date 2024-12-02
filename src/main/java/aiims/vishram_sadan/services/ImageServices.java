package aiims.vishram_sadan.services;


import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.web.client.RestTemplate;

public class ImageServices {
	

	private static final Logger logger = LogManager.getLogger(ImageServices.class);
	
	@Value("${application.image.url}")
	private String apiUrl;
	
	@Value("${application.imageService.active}")
	private boolean isimageserviceeActive;

	
	@Autowired
	private RestTemplate restTemplate;
	
	public Object addImage(String data) throws Exception {
		
		 ResponseEntity<Map> authResponseEntity = null;
		    ResponseEntity<Map> responseEntity = null;
		try {
			Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("image", data);
            
            
            HttpEntity<Map<String, Object>> authRequestEntity = new HttpEntity<>(requestBody, new HttpHeaders());
            authResponseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, authRequestEntity, Map.class);
            
            
          
		} catch (Exception e) {
			throw new Exception("Error: " + e.getMessage());
		}
		
		
		return authResponseEntity;
		
		
	}

}
