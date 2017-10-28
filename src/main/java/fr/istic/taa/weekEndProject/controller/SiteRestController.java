package fr.istic.taa.weekEndProject.controller;

import java.util.List;
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
import fr.istic.taa.weekEndProject.model.ResponseJson;
import fr.istic.taa.weekEndProject.model.SiteActivity;
import fr.istic.taa.weekEndProject.service.SiteActivityService;
import fr.istic.taa.weekEndProject.service.exception.ActivityNotFound;
import fr.istic.taa.weekEndProject.service.exception.SiteActivityNotFound;

/**
 * 
 * @author aferey
 *
 */
@RestController
@RequestMapping(value = "/sites")
public class SiteRestController {

	@Autowired
	SiteActivityService serviceS;
	private static final Logger logger = LogManager.getLogger(Application.class);
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<ResponseJson> getSiteActivityById(@PathVariable("id") long id) {
		SiteActivity p1 = null;
		try {
			p1 = this.serviceS.findById(new Long(id));
			ResponseJson json = new ResponseJson(p1);
			return new ResponseEntity<ResponseJson>(json, HttpStatus.OK);
		} catch (SiteActivityNotFound e) {

			return new ResponseEntity<ResponseJson>(HttpStatus.NOT_FOUND);
		}

	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	ResponseEntity<ResponseJson> getActivities() {
		List<SiteActivity> l1;

		l1 = this.serviceS.findAll();
		ResponseJson json = new ResponseJson(l1);

		return new ResponseEntity<ResponseJson>(json, HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	ResponseEntity<SiteActivity> createSiteActivity(@RequestBody SiteActivity SiteActivity) {
		SiteActivity p1 = this.serviceS.create(SiteActivity);
		logger.info("create Site "+p1.getId());
		return new ResponseEntity<SiteActivity>(p1, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	ResponseEntity<SiteActivity> updateSiteActivity(@RequestBody SiteActivity SiteActivity) {
		SiteActivity p1;
		try {
			p1 = this.serviceS.update(SiteActivity);
			logger.info("update Site. id: "+p1.getId());
			return new ResponseEntity<SiteActivity>(p1, HttpStatus.OK);
		} catch (SiteActivityNotFound e) {
			logger.info("Not Found Site for update. id: "+SiteActivity.getId());
			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	ResponseEntity<SiteActivity> updateSiteActivity(@PathVariable("id") long id,
			@RequestBody SiteActivity SiteActivity) {
		SiteActivity.setId(id);
		SiteActivity p1;
		try {
			p1 = this.serviceS.update(SiteActivity);
			logger.info("update Site. id: "+p1.getId());
			return new ResponseEntity<SiteActivity>(p1, HttpStatus.OK);
		} catch (SiteActivityNotFound e) {
			logger.info("Not Found Site "+SiteActivity.getId());
			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	ResponseEntity<SiteActivity> deleteSiteActivity(@RequestBody SiteActivity SiteActivity) {

		try {
			this.serviceS.delete(SiteActivity.getId());
			logger.info("delete Site. id: "+SiteActivity.getId());
			return new ResponseEntity<SiteActivity>(HttpStatus.OK);
		} catch (SiteActivityNotFound e) {
			logger.info("Not Found Site for delete. id: "+SiteActivity.getId());
			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<SiteActivity> deleteSiteActivity(@PathVariable("id") long id) {

		try {
			this.serviceS.delete(id);
			logger.info("delete Site. id: "+id);
			return new ResponseEntity<SiteActivity>(HttpStatus.OK);
		} catch (SiteActivityNotFound e) {
			logger.info("Not Found Site for delete. id: "+id);
			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/{id}/activities", method = RequestMethod.POST)
	ResponseEntity<SiteActivity> updateSiteActivity(@PathVariable("id") long id,
			@RequestBody Set<Activity> activities) {
		SiteActivity p1;
		try {
			p1 = this.serviceS.updateActivities(id, activities);
			logger.info("update activities of Site. id: "+id);
			return new ResponseEntity<SiteActivity>(p1, HttpStatus.OK);
		} catch (SiteActivityNotFound e) {
			logger.info("Not Found Site for update activities of Site. id: "+id);
			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		} catch (ActivityNotFound e) {
			logger.info("Not Found Activity for update activities of Site. id: "+id);
			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/{id}/activities/{idA}", method = RequestMethod.PUT)
	ResponseEntity<SiteActivity> updateSiteActivity(@PathVariable("id") long id, @PathVariable("idA") long idA) {
		SiteActivity p1;
		try {
			p1 = this.serviceS.updateActivities(id, idA);
			logger.info("add activity "+idA+" on Site"+id);
			return new ResponseEntity<SiteActivity>(p1, HttpStatus.OK);
		} catch (SiteActivityNotFound e) {
			logger.info("Not Found Site "+ id +"for add activity"+idA);
			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		} catch (ActivityNotFound e) {
			logger.info("Not Found activity "+ idA +"for add on Site"+id);
			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/{id}/activities/{idA}", method = RequestMethod.DELETE)
	ResponseEntity<SiteActivity> deleteSiteActivity(@PathVariable("id") long id, @PathVariable("idA") long idA) {
		SiteActivity p1;
		try {
			p1 = this.serviceS.deleteActivities(id, idA);
			logger.info("remove activity "+idA+" on Site"+id);
			return new ResponseEntity<SiteActivity>(p1, HttpStatus.OK);
		} catch (SiteActivityNotFound e) {
			logger.info("Not Found Site "+ id +"for remove activity"+idA);
			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		} catch (ActivityNotFound e) {
			logger.info("Not Found activity "+ idA +"for remove on Site"+id);
			return new ResponseEntity<SiteActivity>(HttpStatus.NOT_FOUND);
		}

	}

}
