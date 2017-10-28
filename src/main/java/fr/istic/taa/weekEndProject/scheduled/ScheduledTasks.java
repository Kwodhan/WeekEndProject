package fr.istic.taa.weekEndProject.scheduled;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import fr.istic.taa.weekEndProject.model.Role;
import fr.istic.taa.weekEndProject.model.User;
import fr.istic.taa.weekEndProject.service.EmailService;
import fr.istic.taa.weekEndProject.service.UserService;

@Component
public class ScheduledTasks {

	@Autowired
	EmailService emailservice;

	@Autowired
	UserService userservice;

	// tous les jeudi a 12h
	@Scheduled(cron ="0 0 12 ? * THU")
	//@Scheduled(fixedRate = 60000)
	public void sendMailToUsers() {
		List<User> users = userservice.findAll();
		for (User user : users) {
			if(user.getRoles().contains(Role.ROLE_USER)) {
			try {
				emailservice.sendEmailNotification(user);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
	}

}