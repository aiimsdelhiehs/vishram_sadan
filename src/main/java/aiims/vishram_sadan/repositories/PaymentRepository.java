package aiims.vishram_sadan.repositories;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import aiims.vishram_sadan.entities.Payment;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
       List<Payment> findByTxnId(long txnId);
       @Query("SELECT DISTINCT p.txnId FROM Payment p")
       long[] findDistinctTxnIdBy();
       
       @Query("SELECT DISTINCT p.txnId FROM Payment p WHERE p.txnDate BETWEEN :d1 AND :d2")
       long[] findDistinctTxnIdByDateBetween(@Param("d1") Date fromDate, @Param("d2")Date toDate);
       
}
