package aiims.vishram_sadan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aiims.vishram_sadan.entities.Patient;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
