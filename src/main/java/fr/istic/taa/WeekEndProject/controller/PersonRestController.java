package fr.istic.taa.WeekEndProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.istic.taa.WeekEndProject.model.Person;
import fr.istic.taa.WeekEndProject.service.PersonService;
import fr.istic.taa.WeekEndProject.service.exception.LocationNotFound;
import fr.istic.taa.WeekEndProject.service.exception.PersonNotFound;

/**
 * 
 * @author aferey
 *
 */
@RestController
@RequestMapping(value = "/person/")
public class PersonRestController {
	/*
	 * Par defaut : Nb requete == Nb association
	 */
	@Autowired
	PersonService serviceP;

	@RequestMapping(value = "id/{id}", method = RequestMethod.GET)
	ResponseEntity<Person> getPersonById(@PathVariable("id") long id) {
		Person p1 = null;
		try {
			p1 = this.serviceP.findById(new Long(id));
			return new ResponseEntity<Person>(p1, HttpStatus.OK);
		} catch (PersonNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}

		
	}

	@RequestMapping(value = "name/{name}", method = RequestMethod.GET)
	ResponseEntity<List<Person>> getPersonByName(@PathVariable("name") String name) {
		List<Person> listp = this.serviceP.findByNameWithAll(name);

		return new ResponseEntity<List<Person>>(listp, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	ResponseEntity<Person> createPerson(@RequestBody Person person) {
		Person p1 = this.serviceP.create(person);
		return new ResponseEntity<Person>(p1, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	ResponseEntity<Person> updatePerson(@RequestBody Person person) {
		Person p1;
		try {
			p1 = this.serviceP.update(person);
			return new ResponseEntity<Person>(p1, HttpStatus.OK);
		} catch (PersonNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "{idP}/addLocation/{idL}", method = RequestMethod.PUT)
	ResponseEntity<Person> updatePersonLocation(@PathVariable("idP") long idP, @PathVariable("idL") long idL) {
		Person p1;
		try {
			p1 = this.serviceP.updateLocation(idP, idL);
			return new ResponseEntity<Person>(p1, HttpStatus.OK);
		} catch (PersonNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		} catch (LocationNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	ResponseEntity<Person> deletePerson(@PathVariable("id") long id) {

		try {
			this.serviceP.delete(id);
			return new ResponseEntity<Person>(HttpStatus.OK);
		} catch (PersonNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}

	}

}
