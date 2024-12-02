package aiims.vishram_sadan.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import aiims.vishram_sadan.entities.Booking;
import aiims.vishram_sadan.entities.Room;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
       public List<Booking> findByStatusInAndRoomIn(String[] status,List<Room> room);
       public List<Booking> findByRoomIn(List<Room> room);
       public List<Booking> findByIdIn(long[] ids);
}
