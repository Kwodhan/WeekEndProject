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

import fr.istic.taa.WeekEndProject.model.Location;
import fr.istic.taa.WeekEndProject.service.LocationService;
import fr.istic.taa.WeekEndProject.service.exception.LocationNotFound;

/**
 * 
 * @author aferey
 *
 */
@RestController
@RequestMapping(value = "/locations/")
public class LocationRestController {
	/*
	 * Par defaut : Nb requete == Nb association
	 */
	@Autowired
	LocationService serviceL;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	ResponseEntity<Location> getLocationById(@PathVariable("id") long id) {
		Location l1;
		try {
			l1 = this.serviceL.findById(new Long(id));
			return new ResponseEntity<Location>(l1, HttpStatus.OK);
		} catch (LocationNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
		}

		
	}

	@RequestMapping(value = "name/{name}", method = RequestMethod.GET)
	ResponseEntity<List<Location>> getLocationByName(@PathVariable("name") String name) {
		List<Location> listp = this.serviceL.findByNameWithPerson(name);

		return new ResponseEntity<List<Location>>(listp, HttpStatus.OK);
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
			// TODO Auto-generated catch block
			return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	ResponseEntity<Location> deleteLocation(@PathVariable("id") long id) {

		try {
			this.serviceL.delete(id);
			return new ResponseEntity<Location>(HttpStatus.OK);
		} catch (LocationNotFound e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
		}

	}

}
