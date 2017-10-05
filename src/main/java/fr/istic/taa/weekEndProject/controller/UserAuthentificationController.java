package fr.istic.taa.weekEndProject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.istic.taa.weekEndProject.model.User;
import fr.istic.taa.weekEndProject.service.SecurityService;
import fr.istic.taa.weekEndProject.service.UserService;

@RestController
@RequestMapping(value = "/auth/")
public class UserAuthentificationController {
	@Autowired
	UserService serviceP;

	@Autowired
	private SecurityService securityService;

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	ResponseEntity<User> registration(@RequestBody User userForm, BindingResult bindingResult, Model model) {
		if (serviceP.findByName(userForm.getPseudo()).size() == 1) {
			new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		User u = serviceP.create(userForm);
		securityService.autologin(userForm.getPseudo(), userForm.getPassword());

		return new ResponseEntity<User>(u,HttpStatus.OK);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	ResponseEntity<?> login(@RequestBody User userForm, HttpSession session) {

		securityService.autologin(userForm.getPseudo(), userForm.getPassword());

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	ResponseEntity<?> logoutPage(HttpSession session) {
		session.invalidate();
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
