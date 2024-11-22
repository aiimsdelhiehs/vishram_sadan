package aiims.vishram_sadan.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import aiims.vishram_sadan.config.AppConstants;
import aiims.vishram_sadan.dto.BookingDetails;
import aiims.vishram_sadan.dto.FaceMatch;
import aiims.vishram_sadan.dto.PaymentReceipt;
import aiims.vishram_sadan.dto.ReceptionMasterData;
import aiims.vishram_sadan.dto.ReferreData;
import aiims.vishram_sadan.entities.Attendants;
import aiims.vishram_sadan.entities.Booking;
import aiims.vishram_sadan.entities.BookingRequest;
import aiims.vishram_sadan.entities.Contact;
import aiims.vishram_sadan.entities.Patient;
import aiims.vishram_sadan.entities.Payment;
import aiims.vishram_sadan.entities.Reminder;
import aiims.vishram_sadan.entities.Room;
import aiims.vishram_sadan.entities.User;
import aiims.vishram_sadan.entities.VishramSadan;
import aiims.vishram_sadan.exceptions.ApiException;
import aiims.vishram_sadan.exceptions.ResourceNotFound;
import aiims.vishram_sadan.payloads.ApiResponse;
import aiims.vishram_sadan.repositories.AttendantsRepository;
import aiims.vishram_sadan.repositories.BookingRepository;
import aiims.vishram_sadan.repositories.BookingRequestRepository;
import aiims.vishram_sadan.repositories.CategoryRepository;
import aiims.vishram_sadan.repositories.ContactRepository;
import aiims.vishram_sadan.repositories.PaymentRepository;
import aiims.vishram_sadan.repositories.ReminderRepository;
import aiims.vishram_sadan.repositories.RoomRepository;
import aiims.vishram_sadan.repositories.UserRepository;
import aiims.vishram_sadan.repositories.VishramSadanRepository;
import aiims.vishram_sadan.util.ApplicationConstant;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReceptionistServices {
 
	private static final Logger logger = LogManager.getLogger(ReceptionistServices.class);
	
	@Autowired
	private BookingRequestRepository bookingRequestRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ContactRepository contactrepo;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ReminderRepository reminderRepository;
	
	@Autowired
	private AttendantsRepository attendantsRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ReferrerServices referrerServices;
	
	@Autowired
	private CommonServices commonServices;
	
	@Autowired
	private VishramSadanRepository vishramSadanRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${application.image.match.url}")
	private String apiUrl;
	
	@Value("${application.file.path}")
	private String imageFilePath;
	
	public List<BookingRequest> getBookingRequest(String username) {
		User receptionist = userRepository.findByUsernameAndAuthority(username,ApplicationConstant.roleReceptionist).orElseThrow(()->new ResourceNotFound("User not found"));
		List<BookingRequest> requiredList = new ArrayList<>();
		List<BookingRequest> allPendingNewRequest = bookingRequestRepository.findDistinctBySadansInAndStatusInOrderByPriorityAsc(receptionist.getSadans(), new String[] {ApplicationConstant.bookingPending});
		BookingRequest firstPendingRequest = allPendingNewRequest.stream().min((a1, a2) -> Float.compare(a1.getPriority(), a2.getPriority())).orElse(null);
		if( firstPendingRequest != null) {
			firstPendingRequest.setPatientImage(readImage(imageFilePath,"patient_"+firstPendingRequest.getRequestId()+".png"));
			requiredList.add(firstPendingRequest);
		}
		List<BookingRequest> allRemidersList = bookingRequestRepository.findDistinctBySadansInAndStatusInOrderByPriorityAsc(receptionist.getSadans(),new String[] {ApplicationConstant.bookingReminder1,ApplicationConstant.bookingReminder2});
		for(BookingRequest remiderRequest:allRemidersList) {
			remiderRequest.setPatientImage(readImage(imageFilePath,"patient_"+remiderRequest.getRequestId()+".png"));
			requiredList.add(remiderRequest);
		}
	    return requiredList;
	}
	
	public List<BookingRequest> getAllBookingRequest(String username) {
		User receptionist = userRepository.findByUsernameAndAuthority(username,ApplicationConstant.roleReceptionist).orElseThrow(()->new ResourceNotFound("User not found"));
		List<BookingRequest> allPendingRequest = bookingRequestRepository.findDistinctBySadansInAndStatusInOrderByPriorityAsc(receptionist.getSadans(), new String[] {ApplicationConstant.bookingPending,ApplicationConstant.bookingReminder1,ApplicationConstant.bookingReminder2});
		List<BookingRequest> allPendingSortedRequest = allPendingRequest.stream().sorted(Comparator.comparingDouble(BookingRequest::getPriority))
                .collect(Collectors.toList());
		return allPendingSortedRequest;
	}
	
	public BookingRequest sendWaitingReminder(String username,long bookingRequestId, Reminder reminder) { 
		User updateBy = userRepository.findByUsernameAndAuthority(username, ApplicationConstant.roleReceptionist).orElseThrow(()->new ResourceNotFound("Not authorized person to confirm booking"));
    	BookingRequest existingBookingRequest = bookingRequestRepository.findDistinctBySadansInAndRequestId(updateBy.getSadans(),bookingRequestId).orElseThrow(()->new ResourceNotFound("Requested booking not found"));
        if(existingBookingRequest == null) {
        	throw new IllegalStateException("Either you are Not authorized person update Booking status or the provided booking id not found");
        }
        else if(ApplicationConstant.bookingPending.equals(existingBookingRequest.getStatus()) || ApplicationConstant.bookingReminder1.equals(existingBookingRequest.getStatus())) {
        	 Set<Reminder> allReminders = new HashSet<>(existingBookingRequest.getReminders());
             if(allReminders.size()  > 1) {
            	throw new IllegalStateException("You can only send maximum 2 reminders for a Booking request");
             }
             else {
            	 Date currentDate = new Date();
          	     Calendar calendar = Calendar.getInstance();
                 calendar.setTime(currentDate);
                 calendar.add(Calendar.HOUR_OF_DAY, reminder.getWaitingHour());
          	     reminder.setSentOn(currentDate);
          	     reminder.setWaitingtill(calendar.getTime());
            	 allReminders.add(reminder);
          	     if(existingBookingRequest.getReminders().size() == 0) {
              	    existingBookingRequest.setStatus(ApplicationConstant.bookingReminder1);
              	    existingBookingRequest.setReminders(new HashSet<>(reminderRepository.saveAll(allReminders)));
              	    return bookingRequestRepository.save(existingBookingRequest);
              	 }
          	     else if((new ArrayList<>(existingBookingRequest.getReminders())).get(0).getWaitingtill().compareTo(currentDate) > 0) {
          	    	throw new IllegalStateException("You can't send reminder 2 as reminder 1 is Active now"); 
          	     }
              	 else{
              	   existingBookingRequest.setStatus(ApplicationConstant.bookingReminder2);
              	   existingBookingRequest.setReminders(new HashSet<>(reminderRepository.saveAll(allReminders)));
              	   return bookingRequestRepository.save(existingBookingRequest);
              	 } 
             }
        }
        else {
        	throw new IllegalStateException("This booking request can't be modify for Now.");
        }
	}
	
    public BookingRequest updateBookingStatusToNotInterested(String username,long bookingRequestId) {
    	User updateBy = userRepository.findByUsernameAndAuthority(username, ApplicationConstant.roleReceptionist).orElseThrow(()->new ResourceNotFound("Not authorized person to confirm booking"));
    	BookingRequest existingBookingRequest = bookingRequestRepository.findDistinctBySadansInAndRequestId(updateBy.getSadans(),bookingRequestId).orElseThrow(()->new ResourceNotFound("Requested booking not found"));
        if(existingBookingRequest == null) {
        	throw new IllegalStateException("Either you are Not authorized person update Booking status or the provided booking id not found");
        }
        else if(ApplicationConstant.bookingPending.equals(existingBookingRequest.getStatus()) ||
        		ApplicationConstant.bookingReminder1.equals(existingBookingRequest.getStatus()) ||
        		ApplicationConstant.bookingReminder2.equals(existingBookingRequest.getStatus())) {
        	existingBookingRequest.setRemarks("Not Interested");
        	existingBookingRequest.setStatus(ApplicationConstant.bookingNotInterested);
        	existingBookingRequest.setLastUpdate(new Date());
        	return bookingRequestRepository.save(existingBookingRequest);
        }
        else {
        	throw new IllegalStateException("This booking request can't be modify for Now.");
        }
    }
    
	public BookingRequest proceedBookingConfirmation(String username, BookingRequest bookingRequest) {
		
		User checkInBy = userRepository.findByUsernameAndAuthority(username, ApplicationConstant.roleReceptionist).orElseThrow(()->new ResourceNotFound("Not authorized person to confirm booking"));
		BookingRequest existingBookingRequest = bookingRequestRepository.findById(bookingRequest.getRequestId()).orElseThrow(()->new ResourceNotFound("Requested booking not found"));
		
		existingBookingRequest.setCheckInBy(checkInBy);
		existingBookingRequest.setStatus(ApplicationConstant.bookingConfirm);
		existingBookingRequest.setIssuedOn(new Date());
		Set<Booking> updatedBooking = new HashSet<>();
		
		List<Attendants> totalAttendants = new ArrayList<>(existingBookingRequest.getAttendants());
		int attendantsEntered = 0;
		
		long txnId = Payment.generateTxnId();
		Date txnDate = new Date();
		for(Booking booking:bookingRequest.getBookings()) {
			Room room = roomRepository.findById(booking.getRoom().getId()).orElseThrow(()->new ResourceNotFound("Room with Id NOT FOUND"));
			if(room.getStatus() == null || room.getStatus().contentEquals(ApplicationConstant.roomNotAvailable)) {
			   throw new IllegalStateException("Room: "+room.getName()+" can't book with status: "+room.getStatus());	
			}
			room.setStatus(ApplicationConstant.roomNotAvailable);
			System.out.println(booking.getFromDate());
			booking.setRoom(roomRepository.save(room));
			booking.setStatus(ApplicationConstant.bookingConfirm);
			booking.setRequest(existingBookingRequest);
			booking.setCheckIn(new Date());
			Set<Attendants> bookingAttendants = booking.getAttendants();
			for (Attendants attendant : bookingAttendants) {
				 try {
					  if(attendantsEntered < totalAttendants.size()) {
					     attendant.setId(totalAttendants.get(attendantsEntered).getId());
					  }
					  attendantsEntered++;
				 } catch (IndexOutOfBoundsException e) {
					  attendantsEntered++;
				 }
	        }
			booking.setAttendants(new HashSet<>(attendantsRepository.saveAll(new ArrayList<>(bookingAttendants))));
			
			Set<Payment> paymentsForBooking = new HashSet<>();
			Set<Payment> paymentHistory = booking.getPaymentHistory();
			for(Payment payment:paymentHistory) {
				payment.setBooking(booking);
				payment.setRate(booking.getRoom().getCategory().getPrice());
				payment.setTxnDate(txnDate);
				payment.setTxnId(txnId);
				payment.setReceiptType(AppConstants.paymentPurposeNewBooking);
				payment.setTxnType(AppConstants.paymentCredit);
				Payment updatedPayment = paymentRepository.save(payment);
				paymentsForBooking.add(updatedPayment);
			}
			booking.setRequest(existingBookingRequest);
			booking.setPaymentHistory(new HashSet<>(paymentRepository.saveAll(paymentsForBooking)));
			
			booking = bookingRepository.save(booking);
			updatedBooking.add(booking);
		}
		
		existingBookingRequest.setBookings(updatedBooking);
		existingBookingRequest.setLastUpdate(new Date());
		//existingBookingRequest.setAttendants(new HashSet<>(attendantsRepository.saveAll(bookingRequest.getAttendants())));
		existingBookingRequest = bookingRequestRepository.save(existingBookingRequest);
		return existingBookingRequest;
	}
	
	public BookingRequest proceedCheckOut(String username, List<Booking> requestedBookings, long bookingRequestId) throws ParseException { 
		   User checkOutBy = userRepository.findByUsernameAndAuthority(username, ApplicationConstant.roleReceptionist).orElseThrow(()->new ResourceNotFound("Not authorized person to confirm booking"));
		   BookingRequest existingBookingRequest = bookingRequestRepository.findById(bookingRequestId).orElseThrow(()->new ResourceNotFound("Requested booking not found"));
		   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		   if(existingBookingRequest.getStatus().equals(ApplicationConstant.bookingClose)) {
			   throw new IllegalStateException("This booking is already cLosed");
		   }
		   Set<Booking> allBookings = existingBookingRequest.getBookings();
		   long txnId = Payment.generateTxnId();
		   Date txnDate = new Date();
		   for(Booking existingBooking:allBookings) {
			   Booking requestedBooking = requestedBookings.stream().filter(requestBooking -> requestBooking.getId() == existingBooking.getId()).findFirst().orElse(null);
			   if(requestedBooking != null) {
				  if(existingBooking.getRoom().getStatus() == null || ApplicationConstant.roomAvailable.contentEquals(existingBooking.getRoom().getStatus())) {
					  throw new IllegalStateException("Room: "+existingBooking.getRoom().getName()+" can't check out with status: "+existingBooking.getRoom().getStatus()); 
				  }
				  else if(existingBooking.getStatus().contentEquals(ApplicationConstant.bookingClose)) {
					  throw new IllegalStateException("Booking for the room: "+existingBooking.getRoom().getName()+" already CLOSED"); 
				  }
				  else {
					    Room room = existingBooking.getRoom();
						room.setStatus(ApplicationConstant.roomAvailable);
						existingBooking.setRoom(roomRepository.save(room));
						existingBooking.setStatus(ApplicationConstant.bookingClose);
						existingBooking.setCheckOut(new Date());
						Set<Payment> paymentHistory = existingBooking.getPaymentHistory();
						System.out.println(requestedBooking.getBalanceReceipt());
						if(requestedBooking.getBalanceReceipt() != null) {
						   Payment balancePayment = requestedBooking.getBalanceReceipt();
						   balancePayment.setBooking(existingBooking);
						   balancePayment.setRate(existingBooking.getRoom().getCategory().getPrice());
						   balancePayment.setTxnType((balancePayment.getAmount()>=0)?AppConstants.paymentDebit:AppConstants.paymentCredit);
						   balancePayment.setReceiptType(AppConstants.paymentPurposeBalance);
						   balancePayment.setTxnId(txnId);
						   balancePayment.setFromDate(dateFormat.parse(requestedBooking.getFromDate()));
						   balancePayment.setToDate(dateFormat.parse(requestedBooking.getToDate()));
						   balancePayment.setTxnDate(txnDate);
						   balancePayment = paymentRepository.save(balancePayment);
						   paymentHistory.add(balancePayment);
						   existingBooking.setPaymentHistory(paymentHistory);
						}
						existingBooking.setRequest(existingBookingRequest);
				  } 
			   }
		   }
		   HashSet<Booking> updatedBookings = new HashSet<>(bookingRepository.saveAll(allBookings));
		   if(updatedBookings.stream().allMatch(booking -> ApplicationConstant.bookingClose.contentEquals(booking.getStatus())) ) {
				existingBookingRequest.setCheckoutBy(checkOutBy);
				existingBookingRequest.setStatus(ApplicationConstant.bookingClose);
				existingBookingRequest.setClosedOn(new Date());
				existingBookingRequest.setLastUpdate(new Date());
			}
			existingBookingRequest.setBookings(updatedBookings);
			return bookingRequestRepository.save(existingBookingRequest);
	}
	
	public BookingRequest extendBooking(String username, List<Booking> requestedBookings, long bookingRequestId) throws ParseException {
		   BookingRequest existingBookingRequest = bookingRequestRepository.findById(bookingRequestId).orElseThrow(()->new ResourceNotFound("Requested booking not found"));
		   if(existingBookingRequest.getStatus().equals(ApplicationConstant.bookingClose)) {
			   throw new IllegalStateException("This booking is already cLosed");
		   }
		   Set<Booking> allBookings = existingBookingRequest.getBookings();
		   long txnId = Payment.generateTxnId();
		   Date txnDate = new Date();
		   for(Booking existingBooking:allBookings) {
			   Booking requestedBooking = requestedBookings.stream().filter(requestBooking -> requestBooking.getId() == existingBooking.getId()).findFirst().orElse(null);
			   if(requestedBooking != null) {
				  if(existingBooking.getRoom().getStatus() == null || ApplicationConstant.roomAvailable.contentEquals(existingBooking.getRoom().getStatus())) {
					  throw new IllegalStateException("Booking of room: "+existingBooking.getRoom().getName()+" can't be extends with status: "+existingBooking.getRoom().getStatus()); 
				  }
				  else if(existingBooking.getStatus().contentEquals(ApplicationConstant.bookingClose)) {
					  throw new IllegalStateException("Booking for the room: "+existingBooking.getRoom().getName()+" already CLOSED"); 
				  }
				  else {
					    Set<Payment> paymentHistory = existingBooking.getPaymentHistory();
					    existingBooking.setToDate(requestedBooking.getToDate());
						if(requestedBooking.getBalanceReceipt() != null) {
						   Payment balancePayment = requestedBooking.getBalanceReceipt();
						   balancePayment.setBooking(existingBooking);
						   balancePayment.setRate(existingBooking.getRoom().getCategory().getMaxPrice());
						   balancePayment.setFromDate(balancePayment.getFromDate());
						   balancePayment.setToDate(balancePayment.getToDate());
						   balancePayment.setTxnType(AppConstants.paymentCredit);
						   balancePayment.setReceiptType(AppConstants.paymentPurposeReNewBooking);
						   balancePayment.setTxnId(txnId);
						   balancePayment.setTxnDate(txnDate);
						   balancePayment = paymentRepository.save(balancePayment);
						   paymentHistory.add(balancePayment);
						   existingBooking.setPaymentHistory(paymentHistory);
						}
						existingBooking.setRequest(existingBookingRequest);
				  } 
			   }
		   }
		   HashSet<Booking> updatedBookings = new HashSet<>(bookingRepository.saveAll(allBookings));
		   existingBookingRequest.setBookings(updatedBookings);
		   existingBookingRequest.setLastUpdate(new Date());
		   return bookingRequestRepository.save(existingBookingRequest);
	}
	
	public List<BookingRequest> getBookingRequestHistory(String username){
		User receptionist = userRepository.findByUsernameAndAuthority(username, ApplicationConstant.roleReceptionist).orElseThrow(()->new ResourceNotFound("Not authorized person to confirm booking"));
		return bookingRequestRepository.findDistinctBySadansInAndStatusInOrderByPriorityAsc(receptionist.getSadans(), new String[] {ApplicationConstant.bookingClose});
	}
	public ReceptionMasterData getMasterData(String username) {
		User receptionist = userRepository.findByUsernameAndAuthority(username, ApplicationConstant.roleReceptionist).orElseThrow(()->new ResourceNotFound("Not authorized person to confirm booking"));
		List<Room> allRooms = new ArrayList<>();
		for(VishramSadan sadan :receptionist.getSadans()) {
			allRooms.addAll(sadan.getRooms());
		}
		List<Booking> bookings = bookingRepository.findByStatusInAndRoomIn(new String[] {ApplicationConstant.bookingConfirm}, allRooms);
		List<BookingDetails> bookingDetails = new ArrayList<BookingDetails>();
		for(Booking booking :bookings) {
			System.out.println(booking.getStatus()+" "+booking.getId());
			bookingDetails.add(new BookingDetails(booking.getRoom(),(booking.getRequest()!=null)?booking.getRequest().getPatient():new Patient(),(booking.getRequest()!=null)?booking.getRequest().getRequestId():0,booking.getPaymentHistory(),booking.getStatus()+""+booking.getRequest().getStatus()));
		}
		return new ReceptionMasterData(new ArrayList<>(receptionist.getSadans()),categoryRepository.findAll(),ApplicationConstant.floorMap,ApplicationConstant.floorIndex,ApplicationConstant.selectedFloors,bookingDetails);
	}
	
	public BookingRequest getBookingRecord(String username, String searchBy, Long value) {
		
		User receptionist = userRepository.findByUsernameAndAuthority(username, ApplicationConstant.roleReceptionist).orElseThrow(()->new ResourceNotFound("Not authorized person to confirm booking"));
		if(searchBy == null || value == 0) {
		   throw new IllegalStateException("Invalid request");
		}
		else {
			if(searchBy.equals(ApplicationConstant.searchByUHID)) {
				BookingRequest bookingRequest = bookingRequestRepository.findDistinctBySadansInAndPatientUhidAndStatusIn(receptionist.getSadans(), value, new String[] {ApplicationConstant.bookingConfirm}).orElseThrow(()->new ResourceNotFound("No Active Request with provided UHID"));
				List<Booking> existingBookings = bookingRequest.getBookings().stream().filter((booking)->!(ApplicationConstant.bookingClose.contentEquals(booking.getStatus()))).collect(Collectors.toList());
                bookingRequest.setBookings(new HashSet<>(existingBookings));
				return bookingRequest;
			}
			else if(searchBy.equals(ApplicationConstant.searchByContactNo)) {
				BookingRequest bookingRequest = bookingRequestRepository.findDistinctBySadansInAndPatientContactNoAndStatusIn(receptionist.getSadans(), value, new String[] {ApplicationConstant.bookingConfirm}).orElseThrow(()->new ResourceNotFound("No Active Request with provided Contact No"));
				List<Booking> existingBookings = bookingRequest.getBookings().stream().filter((booking)->!(ApplicationConstant.bookingClose.contentEquals(booking.getStatus()))).collect(Collectors.toList());
                bookingRequest.setBookings(new HashSet<>(existingBookings));
				return bookingRequest;
			}
			else if(searchBy.equals(ApplicationConstant.searchByRequestId)) {
				BookingRequest bookingRequest = bookingRequestRepository.findDistinctBySadansInAndRequestIdAndStatusIn(receptionist.getSadans(), value, new String[] {ApplicationConstant.bookingConfirm}).orElseThrow(()->new ResourceNotFound("No Active Request with provided Request Id"));
				List<Booking> existingBookings = bookingRequest.getBookings().stream().filter((booking)->!(ApplicationConstant.bookingClose.contentEquals(booking.getStatus()))).collect(Collectors.toList());
                bookingRequest.setBookings(new HashSet<>(existingBookings));
				return bookingRequest;
			}
			else {
				throw new IllegalStateException("Invalid search option");
			}
		}
	}
	
	public ApiResponse matchImages(FaceMatch faceMatch) throws HttpClientErrorException, Exception {
		    ApiResponse toVerifyImage = referrerServices.addImage(faceMatch.getImage1());
		    if(toVerifyImage.isSuccess()) {
		    	Map<String, Object> requestBody = new HashMap<>();
		        requestBody.put("image1", faceMatch.getImage1());
		        requestBody.put("image2", faceMatch.getImage2());
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
		    }else {
		    	throw new ApiException("Invalid captured image");
		    }
	}
	
	public  String readImage(String path,String image) {
        try {
        	Path imagePath = Paths.get(path, image);
        	if (Files.exists(imagePath)) {
             byte[] imageBytes = Files.readAllBytes(Paths.get(path, image));
             String base64String = Base64.getEncoder().encodeToString(imageBytes);
             return base64String;
        	}else {
        		return "";
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
		return image;
	
    }
	public Contact submitContact(Contact contact, String name) {
	    Contact form=new Contact();
	    form.setName(name);
	    form.setType(contact.getType());
	    form.setDescription(contact.getDescription());
	    form.setValue(contact.getValue());
	    form.setConnnectUs(new Date());
	    return contactrepo.save(form);
	    
		
	}



	public List<PaymentReceipt> getReceiptsBetweenDates(long txnId, String fromDate, String toDate)throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long[] txnIds = paymentRepository.findDistinctTxnIdByDateBetween(dateFormat.parse(fromDate+" 00:00:00"), dateFormat.parse(toDate+" 23:59:59"));
	    List<PaymentReceipt> receipts = new ArrayList<>();
		for(long id:txnIds) {
			List<Payment> payments = paymentRepository.findByTxnId(id);
			receipts.add(new PaymentReceipt(payments));
		}
		return receipts;
	}
	
	public ReferreData getAllBookingRequest() {
		 
		return new ReferreData(commonServices.getAllClosedRequest(new HashSet<>(vishramSadanRepository.findAll())),commonServices.getAllConfirmedRequest(new HashSet<>(vishramSadanRepository.findAll())),commonServices.getAllPendingRequest(new HashSet<>(vishramSadanRepository.findAll()))) ;
	}

	public List<BookingRequest> getCloseRequestBetweenDates(String fromDate, String toDate) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// TODO Auto-generated method stub
		return commonServices.getAllClosedRequestByDate(new HashSet<>(vishramSadanRepository.findAll()),dateFormat.parse(fromDate), dateFormat.parse(toDate));
	}
	
}
