package aiims.vishram_sadan.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aiims.vishram_sadan.entities.VishramSadan;

@Repository
public interface VishramSadanRepository extends JpaRepository<VishramSadan, Long> {
       public List<VishramSadan> findByNameOrFullname(String name,String fullname);
}
