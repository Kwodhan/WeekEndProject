package fr.istic.taa.WeekEndProject.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.istic.taa.WeekEndProject.model.Location;
import fr.istic.taa.WeekEndProject.repository.LocationRepository;
import fr.istic.taa.WeekEndProject.service.exception.LocationNotFound;

@Service
public class LocationServiceImpl implements LocationService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private LocationRepository LocationRepository;

	

	@Transactional
	public Location create(Location Location) {
		Location createdLocation = Location;
		return LocationRepository.save(createdLocation);
	}

	@Transactional
	public Location delete(Long id) throws LocationNotFound {
		Location deletedLocation = LocationRepository.findOne(id);

		if (deletedLocation == null)
			throw new LocationNotFound();

		LocationRepository.delete(deletedLocation);
		return deletedLocation;
	}

	public List<Location> findAll() {
		// TODO Auto-generated method stub
		return LocationRepository.findAll();
	}

	@Transactional
	public Location update(Location Location) throws LocationNotFound {
		Location updatedLocation = LocationRepository.findOne(Location.getId());

		if (updatedLocation == null)
			throw new LocationNotFound();

		updatedLocation.setName(Location.getName());
		/*
		 * TODO : others attribut
		 */
		return updatedLocation;
	}

	public Location findById(Long id) {
		// TODO Auto-generated method stub
		return LocationRepository.findOne(id);

	}

	public List<Location> findByName(String name) {
		// TODO Auto-generated method stub
		return LocationRepository.findByName(name);
	}

}
