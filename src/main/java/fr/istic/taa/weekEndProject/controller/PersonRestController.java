package fr.istic.taa.weekEndProject.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.istic.taa.weekEndProject.model.Location;
import fr.istic.taa.weekEndProject.model.Person;
import fr.istic.taa.weekEndProject.model.activity.AbstractActivity;
import fr.istic.taa.weekEndProject.service.PersonService;
import fr.istic.taa.weekEndProject.service.exception.ActivityNotFound;
import fr.istic.taa.weekEndProject.service.exception.LocationNotFound;
import fr.istic.taa.weekEndProject.service.exception.PersonNotFound;

/**
 * 
 * @author aferey
 *
 */
@RestController
@RequestMapping(value = "/persons/")
public class PersonRestController {
	/*
	 * Par defaut : Nb requete == Nb association
	 */
	@Autowired
	PersonService serviceP;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
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

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	ResponseEntity<Person> updatePerson(@PathVariable("id") long id, @RequestBody Person person) {
		person.setId(id);
		Person p1;
		try {
			p1 = this.serviceP.update(person);
			return new ResponseEntity<Person>(p1, HttpStatus.OK);
		} catch (PersonNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "{id}/homes/", method = RequestMethod.POST)
	ResponseEntity<Person> updatePersonLocation(@PathVariable("id") long id, @RequestBody Set<Location> locations) {
		Person p1;
		try {
			p1 = this.serviceP.updateLocation(id, locations);
			return new ResponseEntity<Person>(p1, HttpStatus.OK);
		} catch (PersonNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		} catch (LocationNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "{id}/activities/", method = RequestMethod.POST)
	ResponseEntity<Person> updatePersonActivity(@PathVariable("id") long id,
			@RequestBody Set<AbstractActivity> activities) {
		Person p1;
		try {
			p1 = this.serviceP.updateActivities(id, activities);
			return new ResponseEntity<Person>(p1, HttpStatus.OK);
		} catch (PersonNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		} catch (ActivityNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "{id}/homes/{idL}", method = RequestMethod.PUT)
	ResponseEntity<Person> updatePersonLocation(@PathVariable("id") long id, @PathVariable("idL") long idL) {
		Person p1;
		try {
			p1 = this.serviceP.updateLocation(id, idL);
			return new ResponseEntity<Person>(p1, HttpStatus.OK);
		} catch (PersonNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		} catch (LocationNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "{id}/activities/{idA}", method = RequestMethod.PUT)
	ResponseEntity<Person> updatePersonActivity(@PathVariable("id") long id, @PathVariable("idA") long idA) {
		Person p1;
		try {
			p1 = this.serviceP.updateActivities(id, idA);
			return new ResponseEntity<Person>(p1, HttpStatus.OK);
		} catch (PersonNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		} catch (ActivityNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}

	}
	
	@RequestMapping(value = "{id}/homes/{idL}", method = RequestMethod.DELETE)
	ResponseEntity<Person> deletePersonLocation(@PathVariable("id") long id, @PathVariable("idL") long idL) {
		Person p1;
		try {
			p1 = this.serviceP.deleteLocation(id, idL);
			return new ResponseEntity<Person>(p1, HttpStatus.OK);
		} catch (PersonNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		} catch (LocationNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "{id}/activities/{idA}", method = RequestMethod.DELETE)
	ResponseEntity<Person> deletePersonActivity(@PathVariable("id") long id, @PathVariable("idA") long idA) {
		Person p1;
		try {
			p1 = this.serviceP.deleteActivities(id, idA);
			return new ResponseEntity<Person>(p1, HttpStatus.OK);
		} catch (PersonNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		} catch (ActivityNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	ResponseEntity<Person> deletePerson(@RequestBody Person person) {

		try {
			this.serviceP.delete(person.getId());
			return new ResponseEntity<Person>(HttpStatus.OK);
		} catch (PersonNotFound e) {
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
