package aiims.vishram_sadan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import aiims.vishram_sadan.entities.BookingRequest;
import aiims.vishram_sadan.entities.Contact;

public interface ContactRepository  extends JpaRepository<Contact, Long>{

}
