package aiims.vishram_sadan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import aiims.vishram_sadan.services.SuperAdminServices;

@EnableScheduling
@SpringBootApplication
public class VishramSadanApplication implements ApplicationRunner {

	@Autowired
	private SuperAdminServices superAdminServices;
	
	public static void main(String[] args) {
		SpringApplication.run(VishramSadanApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		superAdminServices.intializeSuperAdmin();
	}

}
