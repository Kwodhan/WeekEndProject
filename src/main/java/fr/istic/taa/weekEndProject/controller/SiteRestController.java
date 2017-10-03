package fr.istic.taa.weekEndProject.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.istic.taa.weekEndProject.model.Person;
import fr.istic.taa.weekEndProject.model.SiteActivity;
import fr.istic.taa.weekEndProject.model.activity.AbstractActivity;
import fr.istic.taa.weekEndProject.service.SiteActivityService;
import fr.istic.taa.weekEndProject.service.exception.ActivityNotFound;
import fr.istic.taa.weekEndProject.service.exception.LocationNotFound;
import fr.istic.taa.weekEndProject.service.exception.PersonNotFound;
import fr.istic.taa.weekEndProject.service.exception.SiteActivityNotFound;

/**
 * 
 * @author aferey
 *
 */
@RestController
@RequestMapping(value = "/sites/")
public class SiteRestController {
	/*
	 * Par defaut : Nb requete == Nb association
	 */
	@Autowired
	SiteActivityService serviceS;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	ResponseEntity<SiteActivity> getSiteActivityById(@PathVariable("id") long id) {
		SiteActivity p1 = null;
		try {
			p1 = this.serviceS.findById(new Long(id));
			return new ResponseEntity<SiteActivity>(p1, HttpStatus.OK);
		} catch (SiteActivityNotFound e) {

			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	ResponseEntity<SiteActivity> createSiteActivity(@RequestBody SiteActivity SiteActivity) {
		SiteActivity p1 = this.serviceS.create(SiteActivity);
		return new ResponseEntity<SiteActivity>(p1, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	ResponseEntity<SiteActivity> updateSiteActivity(@RequestBody SiteActivity SiteActivity) {
		SiteActivity p1;
		try {
			p1 = this.serviceS.update(SiteActivity);
			return new ResponseEntity<SiteActivity>(p1, HttpStatus.OK);
		} catch (SiteActivityNotFound e) {

			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	ResponseEntity<SiteActivity> updateSiteActivity(@PathVariable("id") long id,
			@RequestBody SiteActivity SiteActivity) {
		SiteActivity.setId(id);
		SiteActivity p1;
		try {
			p1 = this.serviceS.update(SiteActivity);
			return new ResponseEntity<SiteActivity>(p1, HttpStatus.OK);
		} catch (SiteActivityNotFound e) {

			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	ResponseEntity<SiteActivity> deleteSiteActivity(@RequestBody SiteActivity SiteActivity) {

		try {
			this.serviceS.delete(SiteActivity.getId());
			return new ResponseEntity<SiteActivity>(HttpStatus.OK);
		} catch (SiteActivityNotFound e) {

			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	ResponseEntity<SiteActivity> deleteSiteActivity(@PathVariable("id") long id) {

		try {
			this.serviceS.delete(id);
			return new ResponseEntity<SiteActivity>(HttpStatus.OK);
		} catch (SiteActivityNotFound e) {

			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "{id}/activities/", method = RequestMethod.POST)
	ResponseEntity<SiteActivity> updateSiteActivity(@PathVariable("id") long id,
			@RequestBody Set<AbstractActivity> activities) {
		SiteActivity p1;
		try {
			p1 = this.serviceS.updateActivities(id, activities);
			return new ResponseEntity<SiteActivity>(p1, HttpStatus.OK);
		} catch (SiteActivityNotFound e) {

			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		} catch (ActivityNotFound e) {

			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "{id}/activities/{idA}", method = RequestMethod.PUT)
	ResponseEntity<SiteActivity> updateSiteActivity(@PathVariable("id") long id, @PathVariable("idA") long idA) {
		SiteActivity p1;
		try {
			p1 = this.serviceS.updateActivities(id, idA);
			return new ResponseEntity<SiteActivity>(p1, HttpStatus.OK);
		} catch (SiteActivityNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		} catch (ActivityNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "{id}/activities/{idA}", method = RequestMethod.DELETE)
	ResponseEntity<SiteActivity> deleteSiteActivity(@PathVariable("id") long id, @PathVariable("idA") long idA) {
		SiteActivity p1;
		try {
			p1 = this.serviceS.deleteActivities(id, idA);
			return new ResponseEntity<SiteActivity>(p1, HttpStatus.OK);
		} catch (SiteActivityNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		} catch (ActivityNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		}

	}

}
