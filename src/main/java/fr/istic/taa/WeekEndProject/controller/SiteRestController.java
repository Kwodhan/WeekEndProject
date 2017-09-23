package fr.istic.taa.WeekEndProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.istic.taa.WeekEndProject.model.SiteActivity;
import fr.istic.taa.WeekEndProject.service.SiteActivityService;
import fr.istic.taa.WeekEndProject.service.exception.ActivityNotFound;
import fr.istic.taa.WeekEndProject.service.exception.LocationNotFound;
import fr.istic.taa.WeekEndProject.service.exception.SiteActivityNotFound;

/**
 * 
 * @author aferey
 *
 */
@RestController
@RequestMapping(value = "/site/")
public class SiteRestController {
	/*
	 * Par defaut : Nb requete == Nb association
	 */
	@Autowired
	SiteActivityService serviceS;

	@RequestMapping(value = "id/{id}", method = RequestMethod.GET)
	ResponseEntity<SiteActivity> getSiteActivityById(@PathVariable("id") long id) {
		SiteActivity p1 = null;
		try {
			p1 = this.serviceS.findById(new Long(id));
			return new ResponseEntity<SiteActivity>(p1, HttpStatus.OK);
		} catch (SiteActivityNotFound e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "{idP}/addLocation/{idL}", method = RequestMethod.PUT)
	ResponseEntity<SiteActivity> updateSiteActivityLocation(@PathVariable("idP") long idP,
			@PathVariable("idL") long idL) {
		SiteActivity p1;
		try {
			p1 = this.serviceS.updateLocation(idP, idL);
			return new ResponseEntity<SiteActivity>(p1, HttpStatus.OK);
		} catch (SiteActivityNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		} catch (LocationNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "{idP}/addActivity/{idA}", method = RequestMethod.PUT)
	ResponseEntity<SiteActivity> updateSiteActivity(@PathVariable("idP") long idP, @PathVariable("idA") long idA) {
		SiteActivity p1;
		try {
			p1 = this.serviceS.updateActivity(idP, idA);
			return new ResponseEntity<SiteActivity>(p1, HttpStatus.OK);
		} catch (SiteActivityNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		} catch (ActivityNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	ResponseEntity<SiteActivity> deleteSiteActivity(@PathVariable("id") long id) {

		try {
			this.serviceS.delete(id);
			return new ResponseEntity<SiteActivity>(HttpStatus.OK);
		} catch (SiteActivityNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		}

	}

}
