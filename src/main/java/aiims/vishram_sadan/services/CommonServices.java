package aiims.vishram_sadan.services;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import aiims.vishram_sadan.entities.BookingRequest;
import aiims.vishram_sadan.entities.VishramSadan;
import aiims.vishram_sadan.repositories.BookingRequestRepository;
import aiims.vishram_sadan.util.ApplicationConstant;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CommonServices {

	private static final Logger logger = LogManager.getLogger(CommonServices.class);
	
	@Autowired
	private BookingRequestRepository bookingRequestRepository;
	
	public List<BookingRequest> getAllPendingRequest(Set<VishramSadan> sadans){
	       return bookingRequestRepository.findDistinctBySadansInAndStatusInOrderByPriorityAsc(sadans,new String[] {ApplicationConstant.bookingPending,ApplicationConstant.bookingReminder1,ApplicationConstant.bookingReminder2});
	}
	public List<BookingRequest> getAllConfirmedRequest(Set<VishramSadan> sadans){
	       return bookingRequestRepository.findDistinctBySadansInAndStatusInOrderByPriorityAsc(sadans,new String[] {ApplicationConstant.bookingConfirm});
	}
	public List<BookingRequest> getAllClosedRequest(Set<VishramSadan> sadans){
		 Date firstDayOfMonth = getFirstDayOfCurrentMonth();
	        Date lastDayOfMonth = getLastDayOfCurrentMonth();
	       return bookingRequestRepository.findDistinctBySadansInAndStatusInAndLastUpdateBetweenOrderByPriorityAsc(sadans,new String[] {ApplicationConstant.bookingClose,ApplicationConstant.bookingNotInterested,ApplicationConstant.bookingDiscard},firstDayOfMonth,lastDayOfMonth);
	}
	
	private Date getFirstDayOfCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);  // Set to the first day
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    // Utility method to get the last day of the current month
    private Date getLastDayOfCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  // Last day
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
    
    public List<BookingRequest> getAllClosedRequestByDate(Set<VishramSadan> sadans,Date fromDate,Date toDate){
    	return bookingRequestRepository.findDistinctBySadansInAndStatusInAndLastUpdateBetweenOrderByPriorityAsc(sadans,new String[] {ApplicationConstant.bookingClose,ApplicationConstant.bookingNotInterested,ApplicationConstant.bookingDiscard},fromDate,toDate);

    }
 
}
