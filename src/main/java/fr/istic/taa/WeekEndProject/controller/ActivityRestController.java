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

import fr.istic.taa.WeekEndProject.model.Meteo;
import fr.istic.taa.WeekEndProject.model.Person;
import fr.istic.taa.WeekEndProject.model.Activity.AbstractActivity;
import fr.istic.taa.WeekEndProject.model.Activity.Loisir;
import fr.istic.taa.WeekEndProject.model.Activity.Sport;
import fr.istic.taa.WeekEndProject.service.ActivityService;
import fr.istic.taa.WeekEndProject.service.exception.ActivityNotFound;
import fr.istic.taa.WeekEndProject.service.exception.LocationNotFound;
import fr.istic.taa.WeekEndProject.service.exception.PersonNotFound;

/**
 * 
 * @author aferey
 *
 */
@RestController
@RequestMapping(value = "/activity/")
public class ActivityRestController {
	/*
	 * Par defaut : Nb requete == Nb association
	 */
	@Autowired
	ActivityService serviceA;

	@RequestMapping(value = "id/{id}", method = RequestMethod.GET)
	ResponseEntity<AbstractActivity> getActivityById(@PathVariable("id") long id) {
		AbstractActivity l1;
		try {
			l1 = this.serviceA.findById(new Long(id));
			return new ResponseEntity<AbstractActivity>(l1, HttpStatus.OK);
		} catch (ActivityNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<AbstractActivity>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "name/{name}", method = RequestMethod.GET)
	ResponseEntity<List<AbstractActivity>> getActivityByName(@PathVariable("name") String name) {
		List<AbstractActivity> listp = this.serviceA.findByName(name);

		return new ResponseEntity<List<AbstractActivity>>(listp, HttpStatus.OK);
	}

	@RequestMapping(value = "loisir/", method = RequestMethod.POST)
	ResponseEntity<Loisir> createLoisir(@RequestBody Loisir activity) {
		Loisir l1 = (Loisir) this.serviceA.create(activity);
		return new ResponseEntity<Loisir>(l1, HttpStatus.OK);
	}

	@RequestMapping(value = "sport/", method = RequestMethod.POST)
	ResponseEntity<Sport> createSport(@RequestBody Sport activity) {
		Sport l1 = (Sport) this.serviceA.create(activity);
		return new ResponseEntity<Sport>(l1, HttpStatus.OK);
	}

	@RequestMapping(value = "loisir/", method = RequestMethod.PUT)
	ResponseEntity<AbstractActivity> updateActivity(@RequestBody Sport activity) {
		AbstractActivity l1;
		try {
			l1 = this.serviceA.update(activity);
			return new ResponseEntity<AbstractActivity>(l1, HttpStatus.OK);
		} catch (ActivityNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<AbstractActivity>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "sport/", method = RequestMethod.PUT)
	ResponseEntity<AbstractActivity> updateActivity(@RequestBody Loisir activity) {
		AbstractActivity l1;
		try {
			l1 = this.serviceA.update(activity);
			return new ResponseEntity<AbstractActivity>(l1, HttpStatus.OK);
		} catch (ActivityNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<AbstractActivity>(HttpStatus.NOT_FOUND);
		}

	}
	
	@RequestMapping(value = "{idA}/addMeteo/{idM}", method = RequestMethod.PUT)
	ResponseEntity<AbstractActivity> updateActivityMeteo(@PathVariable("idA") long idA, @PathVariable("idM") long idM) {
		AbstractActivity p1;
		//todo verif idM
		try {
			p1 = this.serviceA.updateLocation(idA, Meteo.values()[(int) (idM-1)]);
			return new ResponseEntity<AbstractActivity>(p1, HttpStatus.OK);
		} catch (ActivityNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<AbstractActivity>(HttpStatus.NOT_FOUND);
		}
		
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	ResponseEntity<AbstractActivity> deleteActivity(@PathVariable("id") long id) {

		try {
			this.serviceA.delete(id);
			return new ResponseEntity<AbstractActivity>(HttpStatus.OK);
		} catch (ActivityNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<AbstractActivity>(HttpStatus.NOT_FOUND);
		}

	}

}
