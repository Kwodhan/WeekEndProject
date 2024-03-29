package fr.istic.taa.weekEndProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.istic.taa.weekEndProject.model.Activity;
import fr.istic.taa.weekEndProject.model.ResponseJson;
import fr.istic.taa.weekEndProject.service.ActivityService;
import fr.istic.taa.weekEndProject.service.exception.ActivityNotFound;

/**
 * 
 * @author aferey
 *
 */
@RestController
@RequestMapping(value = "/activities")
public class ActivityRestController {

	@Autowired
	ActivityService serviceA;


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<ResponseJson> getActivityById(@PathVariable("id") long id) {

		Activity l1;
		try {
			l1 = this.serviceA.findById(new Long(id));
			ResponseJson json = new ResponseJson((Activity)l1);
			return new ResponseEntity<ResponseJson>(json, HttpStatus.OK);
		} catch (ActivityNotFound e) {

			return new ResponseEntity<ResponseJson>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/leisures", method = RequestMethod.GET)
	ResponseEntity<ResponseJson> getLeisure() {
		List<Activity> l1;

		l1 = this.serviceA.findAllLeisure();
		ResponseJson json = new ResponseJson(l1);
		return new ResponseEntity<ResponseJson>(json, HttpStatus.OK);

	}

	@RequestMapping(value = "/sports", method = RequestMethod.GET)
	ResponseEntity<ResponseJson> getSport() {
		List<Activity> l1;

		l1 = this.serviceA.findAllSport();
		ResponseJson json = new ResponseJson(l1);
		return new ResponseEntity<ResponseJson>(json, HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	ResponseEntity<ResponseJson> getActivities() {
		List<Activity> l1;

		l1 = this.serviceA.findAll();
		ResponseJson json = new ResponseJson(l1);
		return new ResponseEntity<ResponseJson>(json, HttpStatus.OK);

	}
	



//	@RequestMapping(value = "", method = RequestMethod.POST)
//	ResponseEntity<Activity> create(@RequestBody Activity activity) {
//		Activity l1 = this.serviceA.create(activity);
//
//		return new ResponseEntity<Activity>(l1, HttpStatus.OK);
//	}
//
//	@RequestMapping(value = "", method = RequestMethod.PUT)
//	ResponseEntity<Activity> updateActivity(@RequestBody Activity activity) {
//		Activity l1;
//		try {
//			l1 = this.serviceA.update(activity);
//
//			return new ResponseEntity<Activity>(l1, HttpStatus.OK);
//		} catch (ActivityNotFound e) {
//			// TODO Auto-generated catch block
//			return new ResponseEntity<Activity>(HttpStatus.NOT_FOUND);
//		}
//
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//	ResponseEntity<Activity> updateActivity(@PathVariable("id") long id,
//			@RequestBody Activity activity) {
//		activity.setId(id);
//		Activity l1;
//		try {
//			l1 = this.serviceA.update(activity);
//
//			return new ResponseEntity<Activity>(l1, HttpStatus.OK);
//		} catch (ActivityNotFound e) {
//			// TODO Auto-generated catch block
//			return new ResponseEntity<Activity>(HttpStatus.NOT_FOUND);
//		}
//
//	}
//
//	@RequestMapping(value = "", method = RequestMethod.DELETE)
//	ResponseEntity<Activity> deleteActivity(@RequestBody Activity activity) {
//
//		try {
//			this.serviceA.delete(activity.getId());
//			return new ResponseEntity<Activity>(HttpStatus.OK);
//		} catch (ActivityNotFound e) {
//			// TODO Auto-generated catch block
//			return new ResponseEntity<Activity>(HttpStatus.NOT_FOUND);
//		}
//
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	ResponseEntity<Activity> deleteActivity(@PathVariable("id") long id) {
//
//		try {
//			this.serviceA.delete(id);
//			return new ResponseEntity<Activity>(HttpStatus.OK);
//		} catch (ActivityNotFound e) {
//			// TODO Auto-generated catch block
//			return new ResponseEntity<Activity>(HttpStatus.NOT_FOUND);
//		}
//
//	}

}
