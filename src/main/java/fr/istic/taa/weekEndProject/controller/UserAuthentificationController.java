package fr.istic.taa.weekEndProject.controller;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.istic.taa.weekEndProject.jpa.JpaTest;
import fr.istic.taa.weekEndProject.model.User;
import fr.istic.taa.weekEndProject.service.SecurityService;
import fr.istic.taa.weekEndProject.service.UserService;
import fr.istic.taa.weekEndProject.service.exception.UserNotFound;

@RestController
@RequestMapping(value = "/auth/")
public class UserAuthentificationController {
	@Autowired
	UserService serviceP;

	@Autowired
	private SecurityService securityService;
	
	private static final Logger logger = LogManager.getLogger(JpaTest.class);
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	ResponseEntity<User> registration(@RequestBody User userForm, BindingResult bindingResult, Model model) {
		try {
			serviceP.findByPseudo(userForm.getPseudo());
		} catch (UserNotFound e) {
			User u = serviceP.createUser(userForm);

			securityService.autologin(userForm.getPseudo(), userForm.getPassword());
			logger.info("user registration id: "+u.getId()+" Pseudo "+u.getPseudo());
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.CONFLICT);

	}
	
	@RequestMapping(value = "/jonshnownestpasmort", method = RequestMethod.POST)
	ResponseEntity<User> registrationGerant(@RequestBody User userForm, BindingResult bindingResult, Model model) {
		
		try {
			serviceP.findByPseudo(userForm.getPseudo());
		} catch (UserNotFound e) {
			User u = serviceP.createGerant(userForm);

			securityService.autologin(userForm.getPseudo(), userForm.getPassword());
			logger.info("gerant registration id: "+u.getId()+" Pseudo "+u.getPseudo());
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.CONFLICT);

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	ResponseEntity<User> login(@RequestBody User userForm, HttpSession session) {
		try {
			User u = serviceP.findByPseudo(userForm.getPseudo());
			securityService.autologin(userForm.getPseudo(), userForm.getPassword());
			logger.info("login id: "+u.getId()+" Pseudo "+u.getPseudo());
			return new ResponseEntity<User>(u, HttpStatus.OK);
		} catch (UserNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	ResponseEntity<?> logoutPage(HttpSession session) {
		session.invalidate();
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
