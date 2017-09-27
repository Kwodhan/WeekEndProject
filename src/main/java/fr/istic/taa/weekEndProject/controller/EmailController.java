package fr.istic.taa.weekEndProject.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.istic.taa.weekEndProject.model.Person;
import fr.istic.taa.weekEndProject.service.PersonService;
import fr.istic.taa.weekEndProject.service.exception.PersonNotFound;

@Controller
public class EmailController {

	@Autowired
	private JavaMailSender sender;

	@Autowired
	private PersonService service;

	@RequestMapping(value = "/registeremail/{id}", method = RequestMethod.POST)
	@ResponseBody
	String register(@PathVariable("id") long id) {

		Person person;
		try {
			person = service.findById(id);
			sendEmailRegister(person);
		} catch (PersonNotFound e) {
			// TODO Auto-generated catch block
			return "PersonNotFound";
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			return "Fail send Email";
		}
		return "Email Sent!";

	}

	@RequestMapping(value = "/notifyemail/{id}", method = RequestMethod.POST)
	@ResponseBody
	String notify(@PathVariable("id") long id) {

		Person person;
		try {
			person = service.findById(id);
			sendEmailNotification(person);
		} catch (PersonNotFound e) {
			// TODO Auto-generated catch block
			return "PersonNotFound";
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			return "Fail send Email";
		}
		return "Email Sent!";

	}

	private void sendEmailRegister(Person person) throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setTo(person.getEmailAddress());
		helper.setText("Bienvenue " + person.getFirstName() + " dans WeekEndProject");

		helper.setSubject("Bienvenue " + person.getFirstName() + " dans WeekEndProject");

		sender.send(message);
	}

	private void sendEmailNotification(Person person) throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setTo(person.getEmailAddress());
		helper.setText("Bienvenue " + person.getFirstName() + " dans WeekEndProject");

		helper.setSubject("Bienvenue " + person.getFirstName() + " dans WeekEndProject");

		sender.send(message);
	}

}