package fr.istic.taa.WeekEndProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.istic.taa.WeekEndProject.model.Location;
import fr.istic.taa.WeekEndProject.model.Person;
import fr.istic.taa.WeekEndProject.service.LocationService;
import fr.istic.taa.WeekEndProject.service.PersonService;
import fr.istic.taa.WeekEndProject.service.exception.PersonNotFound;

@RestController
public class PersonRestController {

	@Autowired
	PersonService serviceP;

	@Autowired
	LocationService serviceL;

	@RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
	ResponseEntity<Person> readBookmarks(@PathVariable("id") long id) {
		Person p1 = this.serviceP.findById(new Long(id));
		Location l1 = this.serviceL.findById(new Long(id));
		p1.getHomes().add(l1);

		return new ResponseEntity<Person>(this.serviceP.findById(new Long(id)), HttpStatus.OK);
	}


}
