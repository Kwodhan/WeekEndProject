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

import fr.istic.taa.weekEndProject.model.Activity;
import fr.istic.taa.weekEndProject.model.Location;
import fr.istic.taa.weekEndProject.model.ResponseJson;
import fr.istic.taa.weekEndProject.service.LocationService;
import fr.istic.taa.weekEndProject.service.exception.LocationNotFound;

/**
 * 
 * @author aferey
 *
 */
@RestController
@RequestMapping(value = "/locations")
public class LocationRestController {
	/*
	 * Par defaut : Nb requete == Nb association
	 */
	@Autowired
	LocationService serviceL;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<ResponseJson> getLocationById(@PathVariable("id") long id) {
		Location l1;
		try {
			l1 = this.serviceL.findById(new Long(id));
			ResponseJson json = new ResponseJson(l1);
			return new ResponseEntity<ResponseJson>(json, HttpStatus.OK);
		} catch (LocationNotFound e) {
			
			return new ResponseEntity<ResponseJson>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/region/{region}", method = RequestMethod.GET)
	ResponseEntity<ResponseJson> getLocationsByRegion(@PathVariable("region") String region) {
		List<Location> listp = this.serviceL.findByRegion( region);
		ResponseJson json = new ResponseJson(listp);
		return new ResponseEntity<ResponseJson>(json, HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	ResponseEntity<ResponseJson> getLocations() {
		List<Location> l1;

		l1 = this.serviceL.findAll();
		ResponseJson json = new ResponseJson(l1);
		return new ResponseEntity<ResponseJson>(json, HttpStatus.OK);

	}
	
	

	@RequestMapping(value = "", method = RequestMethod.POST)
	ResponseEntity<Location> createPerson(@RequestBody Location location) {
		Location l1 = this.serviceL.create(location);
		return new ResponseEntity<Location>(l1, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	ResponseEntity<Location> updateLocation(@RequestBody Location location) {
		Location l1;
		try {
			l1 = this.serviceL.update(location);
			return new ResponseEntity<Location>(l1, HttpStatus.OK);
		} catch (LocationNotFound e) {
			
			return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	ResponseEntity<Location> updateLocation(@PathVariable("id") long id, @RequestBody Location location) {
		Location l1;
		location.setId(id);
		try {
			l1 = this.serviceL.update(location);
			return new ResponseEntity<Location>(l1, HttpStatus.OK);
		} catch (LocationNotFound e) {
			
			return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	ResponseEntity<Location> deleteLocation(@RequestBody Location location) {

		try {
			this.serviceL.delete(location.getId());
			return new ResponseEntity<Location>(HttpStatus.OK);
		} catch (LocationNotFound e) {
			
			return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<Location> deleteLocation(@PathVariable("id") long id) {

		try {
			this.serviceL.delete(id);
			return new ResponseEntity<Location>(HttpStatus.OK);
		} catch (LocationNotFound e) {
			
			return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
		}

	}

}
