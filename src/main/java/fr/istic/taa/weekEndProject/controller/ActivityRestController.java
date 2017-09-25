package fr.istic.taa.weekEndProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.istic.taa.weekEndProject.model.Meteo;
import fr.istic.taa.weekEndProject.model.activity.AbstractActivity;
import fr.istic.taa.weekEndProject.model.activity.Leisure;
import fr.istic.taa.weekEndProject.model.activity.Sport;
import fr.istic.taa.weekEndProject.service.ActivityService;
import fr.istic.taa.weekEndProject.service.exception.ActivityNotFound;

/**
 * 
 * @author aferey
 *
 */
@RestController
@RequestMapping(value = "/activities/")
public class ActivityRestController {

	@Autowired
	ActivityService serviceA;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
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

	@RequestMapping(value = "leisures/", method = RequestMethod.GET)
	ResponseEntity<List<AbstractActivity>> getLeisure() {
		List<AbstractActivity> l1;

		l1 = this.serviceA.findAllLeisure();
		return new ResponseEntity<List<AbstractActivity>>(l1, HttpStatus.OK);

	}

	@RequestMapping(value = "sports/", method = RequestMethod.GET)
	ResponseEntity<List<AbstractActivity>> getSport() {
		List<AbstractActivity> l1;

		l1 = this.serviceA.findAllSport();
		return new ResponseEntity<List<AbstractActivity>>(l1, HttpStatus.OK);

	}

	@RequestMapping(value = "name/{name}", method = RequestMethod.GET)
	ResponseEntity<List<AbstractActivity>> getActivityByName(@PathVariable("name") String name) {
		List<AbstractActivity> listp = this.serviceA.findByName(name);

		return new ResponseEntity<List<AbstractActivity>>(listp, HttpStatus.OK);
	}

	@RequestMapping(value = "leisures/", method = RequestMethod.POST)
	ResponseEntity<Leisure> createLeisure(@RequestBody Leisure activity) {
		Leisure l1 = (Leisure) this.serviceA.create(activity);
		return new ResponseEntity<Leisure>(l1, HttpStatus.OK);
	}

	@RequestMapping(value = "sports/", method = RequestMethod.POST)
	ResponseEntity<Sport> createSport(@RequestBody Sport activity) {
		Sport l1 = (Sport) this.serviceA.create(activity);
		return new ResponseEntity<Sport>(l1, HttpStatus.OK);
	}

	@RequestMapping(value = "leisures/", method = RequestMethod.PUT)
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

	@RequestMapping(value = "sports/", method = RequestMethod.PUT)
	ResponseEntity<AbstractActivity> updateActivity(@RequestBody Leisure activity) {
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
		if (idM <= 0 || idM > Meteo.values().length) {
			return new ResponseEntity<AbstractActivity>(HttpStatus.NOT_FOUND);
		}
		// todo verif idM
		try {
			p1 = this.serviceA.updateLocation(idA, Meteo.values()[(int) (idM - 1)]);
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
