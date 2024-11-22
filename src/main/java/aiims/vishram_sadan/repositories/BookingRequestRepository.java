package aiims.vishram_sadan.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import aiims.vishram_sadan.entities.BookingRequest;
import aiims.vishram_sadan.entities.Patient;
import aiims.vishram_sadan.entities.VishramSadan;
@Repository
public interface BookingRequestRepository extends JpaRepository<BookingRequest, Long>{
       public List<BookingRequest> findByPatientAndStatusInOrderByPriorityAsc(Patient patient, String[] status);
       public List<BookingRequest> findDistinctBySadansInAndStatusInOrderByPriorityAsc(Set<VishramSadan> vishramSadanList,String[] status);
       @Query("SELECT br FROM BookingRequest br WHERE br.status = 'PENDING' AND br.requestedOn <= :cutoffDate ")
       List<BookingRequest> findByStatusPendingAndOver72Hours(@Param("cutoffDate") LocalDateTime cutoffDate);
       @Query("SELECT b FROM BookingRequest b JOIN b.reminders r WHERE (b.status = 'REMINDER1' OR b.status= 'REMINDER2') AND r.sentOn <= :cutoffTime")
       List<BookingRequest> findBookingRequestsByReminderStatusAndOver48Hours(@Param("cutoffTime") LocalDateTime cutoffTime);
       
       public Optional<BookingRequest> findByPriority(float priority);
       public Optional<BookingRequest> findByStatusInAndRequestId(String[] status, long requestId);
       public Optional<BookingRequest> findDistinctBySadansInAndRequestId(Set<VishramSadan> vishramSadanList,Long requestId);
       public Optional<BookingRequest> findDistinctBySadansInAndRequestIdAndStatusIn(Set<VishramSadan> vishramSadanList,Long requestId, String[] status);
       public Optional<BookingRequest> findDistinctBySadansInAndPatientUhidAndStatusIn(Set<VishramSadan> vishramSadanList,Long uhid, String[] status);
       public Optional<BookingRequest> findDistinctBySadansInAndPatientContactNoAndStatusIn(Set<VishramSadan> vishramSadanList,Long contactNo, String[] status);
       
       List<BookingRequest> findDistinctBySadansInAndStatusInAndLastUpdateBetweenOrderByPriorityAsc( Set<VishramSadan> vishramSadanList, String[] status,Date startDate, Date endDate);
       //List<BookingRequest> findDistinctBySadansInAndStatusInAndLastUpdateBetweenOrderByPriority( Set<VishramSadan> vishramSadanList, String[] status,String startDate, String endDate);
}
