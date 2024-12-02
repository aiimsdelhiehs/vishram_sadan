package aiims.vishram_sadan.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import aiims.vishram_sadan.dto.ReferreData;
import aiims.vishram_sadan.entities.BookingRequest;
import aiims.vishram_sadan.entities.Category;
import aiims.vishram_sadan.entities.Patient;
import aiims.vishram_sadan.entities.User;
import aiims.vishram_sadan.entities.VishramSadan;
import aiims.vishram_sadan.exceptions.ApiException;
import aiims.vishram_sadan.payloads.ApiResponse;
import aiims.vishram_sadan.repositories.BookingRequestRepository;
import aiims.vishram_sadan.repositories.CategoryRepository;
import aiims.vishram_sadan.repositories.PatientRepository;
import aiims.vishram_sadan.repositories.UserRepository;
import aiims.vishram_sadan.repositories.VishramSadanRepository;
import aiims.vishram_sadan.util.ApplicationConstant;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReferrerServices {

	private static final Logger logger = LogManager.getLogger(ReferrerServices.class);
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private VishramSadanRepository vishramSadanRepository;
	
	@Autowired
	private BookingRequestRepository bookingRequestRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommonServices commonServices;
	
	@Autowired TextMessageServices textMessageServices;
	
	@Value("${application.image.url}")
	private String apiUrl;
	
	@Value("${application.imageService.active}")
	private boolean isimageserviceeActive;
	
	@Value("${application.file.path}")
	private String imageFilePath;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public BookingRequest addPriority(long bookingRequestId, int requiredPriority, String username) throws Exception {
		User loggedInUser = userRepository.findByUsername(username).orElseThrow(()->new Exception("User not found"));
		//of assigned vishram sadan
		List<BookingRequest> allPendingRequest = commonServices.getAllPendingRequest(new HashSet<>(vishramSadanRepository.findAll()));
		BookingRequest existingBookingRequest = bookingRequestRepository.findByStatusInAndRequestId(new String[] {ApplicationConstant.bookingPending,ApplicationConstant.bookingReminder1,ApplicationConstant.bookingReminder2},  bookingRequestId).orElseThrow(()->new ApiException("There is No PENDING Booking request with id "+bookingRequestId));
	 	if(requiredPriority <= 0 || requiredPriority > allPendingRequest.size()-1) {
			throw new ApiException("Invalid requested priority as per current waiting list");
		}
		else {
			
			int currentIndex = IntStream.range(0, allPendingRequest.size())
						            .filter(i -> (allPendingRequest.get(i).getRequestId() == bookingRequestId))
						            .findFirst().orElse(-1);
			System.out.println("curr i "+currentIndex);
			if(currentIndex == -1) {
				throw new ApiException("Booking request "+bookingRequestId+" NOT FOUND as Pending");
			}
			else if(requiredPriority >= currentIndex+1) {
            	throw new ApiException("Current priority is already SET minimum as compare to requested priority");
            }
            else {
            	float newPriority = 0;
            	if(requiredPriority == 1) {
            		newPriority = allPendingRequest.get(requiredPriority-1).getPriority()/2;
            	}else {
            		float startPriority = allPendingRequest.get(requiredPriority-1).getPriority();
                	float endPriority = allPendingRequest.get(requiredPriority-2).getPriority();
                	newPriority = (startPriority+endPriority)/2;
            	}
            	System.out.println(newPriority);
			 	existingBookingRequest.setPriority(newPriority);
			 	existingBookingRequest.setPrioritySetBy(loggedInUser);
			 	existingBookingRequest.setPrioritySetOn(new Date());
			 	return bookingRequestRepository.save(existingBookingRequest);
            }
			
		}
		
		
	 	
	}
	
	public BookingRequest addBookingRequest(BookingRequest request,String username)throws Exception {
		User loggedInUser = userRepository.findByUsername(username).orElseThrow(()->new Exception("User not found"));
		
		if(!validPatient(request.getPatient())) {
			throw new IllegalStateException("Patient details are incomplete");
		}else {
			if(request.getSadans() == null || request.getSadans().size() == 0) {
				throw new IllegalStateException("Please select at least one vishram sadan for booking request");
			}
			else if(request.getAttendants() == null || request.getAttendants().size() == 0) {
				throw new IllegalStateException("Please add at least one attendant for booking request");
			}
			else {
				Set<VishramSadan> requiredSadans = new HashSet<>();
				Set<Category> requiredCategories = new HashSet<>();
				for(Category category:request.getPreferences()) {
					if(category == null) {
						throw new IllegalStateException("Selected invalid category as preference");
					}else {
						requiredCategories.add(categoryRepository.findById(category.getId()).orElseThrow(()->new IllegalStateException("Selected category preference not found")));
					}
				}
				for(VishramSadan sadan:request.getSadans()) {
					if(sadan == null) {
						throw new IllegalStateException("Selected vishram sadan is invalid");
					}else {
						requiredSadans.add(vishramSadanRepository.findById(sadan.getId()).orElseThrow(()->new IllegalStateException("Selected vishram sadan not found")));
					}
				}
				List<BookingRequest> existingRequest = bookingRequestRepository.findByPatientAndStatusInOrderByPriorityAsc(request.getPatient(), new String[] {ApplicationConstant.bookingPending,ApplicationConstant.bookingReminder1,ApplicationConstant.bookingReminder2,ApplicationConstant.bookingConfirm});
				if(existingRequest != null && existingRequest.size() > 0) {
					    throw new IllegalStateException("This patient have already an ACTIVE booking request");
				}
				else {
					long requestId = System.currentTimeMillis()/1000;
					request.setSadans(requiredSadans);
					request.setPreferences(requiredCategories);
					request.setPatient(patientRepository.save(request.getPatient()));
					request.setRefferedBy(loggedInUser);
					request.setRequestedOn(new Date());
					request.setRequestId(requestId);
					request.setPriority(requestId);
					request.setLastUpdate(new Date());
					request.setStatus(ApplicationConstant.bookingPending);
					request.setBookings(new HashSet<>());
					request.setReminders(new HashSet<>());
					
					writeImage(request.getPatientImage(),"patient_"+request.getRequestId()+".png",imageFilePath);
					bookingRequestRepository.save(request);
					try {
						String contact = Long.toString(request.getPatient().getContactNo());
					textMessageServices.sendMessage(contact,"Your booking request with Ref No: "+ request.getRequestId()+" at AIIMS Vishram Sadan is successfully RAISED.");
				} catch (Exception e) {
					System.err.println("Textmessage services is not working");
				}
					return request;
				}
			}
		}
	}

	private boolean validPatient(Patient patient) {
		if(patient == null || patient.getUhid() == 0 || patient.getFullname() == null || patient.getContactNo() == 0 || patient.getAddress() == null ||
		   patient.getFullname().contentEquals("") || patient.getAddress().contentEquals("")) {
		   return false;
        }else {
        	return true;
        }
	}

	public ReferreData getAllBookingRequest() {
		   return new ReferreData(commonServices.getAllClosedRequest(new HashSet<>(vishramSadanRepository.findAll())),commonServices.getAllConfirmedRequest(new HashSet<>(vishramSadanRepository.findAll())),commonServices.getAllPendingRequest(new HashSet<>(vishramSadanRepository.findAll()))) ;
	}
	
	public ApiResponse addImage(String data) throws HttpClientErrorException,Exception  {
		        
		        Map<String, Object> requestBody = new HashMap<>();
		        requestBody.put("image", data);
                HttpEntity<Map<String, Object>> authRequestEntity = new HttpEntity<>(requestBody, new HttpHeaders());
		        ResponseEntity<Map> authResponseEntity1 = restTemplate.exchange(apiUrl, HttpMethod.POST, authRequestEntity, Map.class);

		        Map<String, Object> apiResponseData = authResponseEntity1.getBody();
		        if (apiResponseData != null) {
		            if (apiResponseData.containsKey("message")) {
		                String message1 = (String) apiResponseData.get("message");
		                return new ApiResponse(message1,true);
		            } else if (apiResponseData.containsKey("error")) {
		                String error = (String) apiResponseData.get("error");
		                throw new ApiException(error);
		            }
		        }
                throw new ApiException("there is no message");
	}
	
	private void writeImage(String base64String,String outputFileName, String directoryPath) throws FileNotFoundException, IOException {
		
		byte[] imageBytes = Base64.getDecoder().decode(base64String.split(",")[1]);
		File directory = new File(directoryPath);
		if (!directory.exists()) {
			 directory.mkdirs(); 
        }
        try (FileOutputStream fos = new FileOutputStream(new File(directoryPath+"/"+outputFileName))) {
            fos.write(imageBytes);
        }
    
	}
	
	public List <BookingRequest> setCloseToDiscard() throws Exception{
		LocalDateTime cutoffDate = LocalDateTime.now().minusHours(72);
	     List <BookingRequest> request= bookingRequestRepository.findByStatusPendingAndOver72Hours(cutoffDate);
	     System.out.println("check"+request.size());
			
			  for (BookingRequest requestList : request) {
			  requestList.setStatus(ApplicationConstant.bookingDiscard);
			  requestList.setLastUpdate(new Date());// Update thestatus to whatever new status you want
			  bookingRequestRepository.save(requestList);
			  try {
					String contact = Long.toString(requestList.getPatient().getContactNo());
				textMessageServices.sendMessage(contact,"Your booking request with id: "+ requestList.getRequestId()+"at AIIMS Vishram Sadan has been EXPIRED due to our policy. ");
			} catch (Exception e) {
				System.err.println("Textmessage services is not working");
			}
			
			  }
		return request;
	}
	
	public List <BookingRequest> setReminderToDiscard() throws Exception{
		LocalDateTime cutoffDate = LocalDateTime.now().minusHours(48);
	     List <BookingRequest> request= bookingRequestRepository.findBookingRequestsByReminderStatusAndOver48Hours(cutoffDate);
	     System.out.println("check"+request.size());
			
			  for (BookingRequest requestList : request) {
			  requestList.setStatus(ApplicationConstant.bookingDiscard); 
			  requestList.setLastUpdate(new Date());
			  bookingRequestRepository.save(requestList); // Save each updated request backto the database }
			  try {
					String contact = Long.toString(requestList.getPatient().getContactNo());
				textMessageServices.sendMessage(contact,"Your booking request with id: "+ requestList.getRequestId()+" at AIIMS Vishram Sadan has been EXPIRED due to our policy. ");
			} catch (Exception e) {
				System.err.println("Textmessage services is not working");
			}
			
			  }
		return request;
	}

}
