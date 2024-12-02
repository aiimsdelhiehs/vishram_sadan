package aiims.vishram_sadan.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import aiims.vishram_sadan.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	   
	   public Optional<User> findByUsername(String username);
	   
	   @Query("SELECT u FROM User u JOIN u.authorities a WHERE a = :authority")
	   public List<User> findByAuthority(@Param("authority") String authority);
	   
	   @Query("SELECT u FROM User u JOIN u.authorities a WHERE u.username = :username AND a = :authority")
	   public Optional<User> findByUsernameAndAuthority(@Param("username") String username, @Param("authority") String authority);

}
