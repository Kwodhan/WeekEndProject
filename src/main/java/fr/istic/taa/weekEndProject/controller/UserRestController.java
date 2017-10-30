package fr.istic.taa.weekEndProject.controller;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.istic.taa.weekEndProject.jpa.Application;
import fr.istic.taa.weekEndProject.model.Activity;
import fr.istic.taa.weekEndProject.model.Location;
import fr.istic.taa.weekEndProject.model.User;
import fr.istic.taa.weekEndProject.service.UserService;
import fr.istic.taa.weekEndProject.service.exception.ActivityNotFound;
import fr.istic.taa.weekEndProject.service.exception.LocationNotFound;
import fr.istic.taa.weekEndProject.service.exception.UserNotFound;

/**
 * 
 * @author aferey
 *
 */
@RestController
@RequestMapping(value = "/users")
public class UserRestController {

	@Autowired
	UserService serviceP;

	private static final Logger logger = LogManager.getLogger(Application.class);
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	ResponseEntity<ResponseJson> getPersonById(@PathVariable("id") long id) {
//		User p1 = null;
//		try {
//			p1 = this.serviceP.findById(new Long(id));
//			ResponseJson json = new ResponseJson(p1);
//			return new ResponseEntity<ResponseJson>(json, HttpStatus.OK);
//		} catch (UserNotFound e) {
//			
//			return new ResponseEntity<ResponseJson>(HttpStatus.NOT_FOUND);
//		}
//
//	}


//	@RequestMapping(value = "", method = RequestMethod.PUT)
//	ResponseEntity<User> updatePerson(@RequestBody User person) {
//		User p1;
//		try {
//			p1 = this.serviceP.update(person);
//			return new ResponseEntity<User>(p1, HttpStatus.OK);
//		} catch (UserNotFound e) {
//			
//			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//		}
//
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//	ResponseEntity<User> updatePerson(@PathVariable("id") long id, @RequestBody User person) {
//		person.setId(id);
//		User p1;
//		try {
//			p1 = this.serviceP.update(person);
//			return new ResponseEntity<User>(p1, HttpStatus.OK);
//		} catch (UserNotFound e) {
//			
//			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//		}
//
//	}
	
	@RequestMapping(value = "/{id}/homes", method = RequestMethod.POST)
	ResponseEntity<User> updatePersonLocation(@PathVariable("id") long id, @RequestBody Set<Location> locations) {
		User p1;
		try {
			p1 = this.serviceP.updateLocation(id, locations);
			logger.info("update homes of user. id: "+id);
			return new ResponseEntity<User>(p1, HttpStatus.OK);
		} catch (UserNotFound e) {
			logger.info("Not Found User for update homes of User. id: "+id);
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		} catch (LocationNotFound e) {
			logger.info("Not Found homes for update homes of User. id: "+id);
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/{id}/activities", method = RequestMethod.POST)
	ResponseEntity<User> updatePersonActivity(@PathVariable("id") long id,
			@RequestBody Set<Activity> activities) {
		User p1;
		try {
			p1 = this.serviceP.updateActivities(id, activities);
			logger.info("update activities of user. id: "+id);
			return new ResponseEntity<User>(p1, HttpStatus.OK);
		} catch (UserNotFound e) {
			logger.info("Not Found User for update activities of User. id: "+id);
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		} catch (ActivityNotFound e) {
			logger.info("Not Found activities for update activities of User. id: "+id);
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

	}
	
	@RequestMapping(value = "/{id}/homes/{idL}", method = RequestMethod.PUT)
	ResponseEntity<User> updatePersonLocation(@PathVariable("id") long id, @PathVariable("idL") long idL) {
		User p1;
		try {
			p1 = this.serviceP.updateLocation(id, idL);
			logger.info("add home "+idL+" on User"+id);
			return new ResponseEntity<User>(p1, HttpStatus.OK);
		} catch (UserNotFound e) {
			logger.info("Not Found User "+ id +"for add home"+idL);
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		} catch (LocationNotFound e) {
			logger.info("Not Found home "+ idL +"for add on User"+id);
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/{id}/activities/{idA}", method = RequestMethod.PUT)
	ResponseEntity<User> updatePersonActivity(@PathVariable("id") long id, @PathVariable("idA") long idA) {
		User p1;
		try {
			p1 = this.serviceP.updateActivities(id, idA);
			logger.info("add activity "+idA+" on User"+id);
			return new ResponseEntity<User>(p1, HttpStatus.OK);
		} catch (UserNotFound e) {
			logger.info("Not Found User "+ id +"for add activity"+idA);
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		} catch (ActivityNotFound e) {
			logger.info("Not Found activity "+ idA +"for add on User"+id);
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/{id}/homes/{idL}", method = RequestMethod.DELETE)
	ResponseEntity<User> deletePersonLocation(@PathVariable("id") long id, @PathVariable("idL") long idL) {
		User p1;
		try {
			p1 = this.serviceP.deleteLocation(id, idL);
			logger.info("remove home "+idL+" on User"+id);
			return new ResponseEntity<User>(p1, HttpStatus.OK);
		} catch (UserNotFound e) {
			logger.info("Not Found User "+ id +"for remove home"+idL);
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		} catch (LocationNotFound e) {
			logger.info("Not Found home "+ idL +"for remove on User"+id);
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/{id}/activities/{idA}", method = RequestMethod.DELETE)
	ResponseEntity<User> deletePersonActivity(@PathVariable("id") long id, @PathVariable("idA") long idA) {
		User p1;
		try {
			p1 = this.serviceP.deleteActivities(id, idA);
			logger.info("remove activity "+idA+" on User"+id);
			return new ResponseEntity<User>(p1, HttpStatus.OK);
		} catch (UserNotFound e) {
			logger.info("Not Found User "+ id +"for remove activity"+idA);
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		} catch (ActivityNotFound e) {
			logger.info("Not Found activity "+ idA +"for remove on User"+id);
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

	}

//	@RequestMapping(value = "", method = RequestMethod.DELETE)
//	ResponseEntity<User> deletePerson(@RequestBody User person) {
//
//		try {
//			this.serviceP.delete(person.getId());
//			return new ResponseEntity<User>(HttpStatus.OK);
//		} catch (UserNotFound e) {
//			
//			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//		}
//
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	ResponseEntity<User> deletePerson(@PathVariable("id") long id) {
//
//		try {
//			this.serviceP.delete(id);
//			return new ResponseEntity<User>(HttpStatus.OK);
//		} catch (UserNotFound e) {
//			
//			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//		}
//
//	}



}
