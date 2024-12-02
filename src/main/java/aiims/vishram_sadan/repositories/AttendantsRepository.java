package aiims.vishram_sadan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aiims.vishram_sadan.entities.Attendants;
@Repository
public interface AttendantsRepository extends JpaRepository<Attendants, Long>{

}
