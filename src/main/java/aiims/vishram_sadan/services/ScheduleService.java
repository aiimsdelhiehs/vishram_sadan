package aiims.vishram_sadan.services;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ScheduleService {
	private static final Logger logger = LogManager.getLogger(ScheduleService.class);
	
	@Autowired
	private ReferrerServices referrerServices;
	
	
	@Scheduled(cron = "0 30 13 * * *")
    public void myScheduledTask() {
		try {
			   SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
			   System.out.println("Scheduled Job Run At:"+sdf.format(new Date()));
			   referrerServices.setCloseToDiscard();
			   referrerServices.setReminderToDiscard();
		 } catch (Exception e) {
			 System.out.println("Scheduled Job Exception:"+e.getMessage());
		}
    }
}
