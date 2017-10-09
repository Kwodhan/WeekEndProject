package fr.istic.taa.weekEndProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.istic.taa.weekEndProject.model.Location;
import fr.istic.taa.weekEndProject.model.User;
import fr.istic.taa.weekEndProject.repository.LocationRepository;
import fr.istic.taa.weekEndProject.service.exception.LocationNotFound;

@Service
public class LocationServiceImpl implements LocationService {

	public LocationServiceImpl() {
	}

	@Autowired
	private LocationRepository locationRepository;

	@Transactional
	public Location create(Location Location) {
		List<Location> listLocation = locationRepository.findByName(Location.getCity(), Location.getRegion());
		if (listLocation.size() != 0) {
			return listLocation.get(0);
		}

		return locationRepository.save(Location);
	}

	@Transactional
	public Location delete(Long id) throws LocationNotFound {
		Location deletedLocation = locationRepository.findById(id);

		if (deletedLocation == null)
			throw new LocationNotFound();

		for (User p : deletedLocation.getUsers()) {
			p.getHomes().remove(deletedLocation);
		}
		deletedLocation.getUsers().clear();
		locationRepository.delete(deletedLocation);
		return deletedLocation;
	}

	public List<Location> findAll() {
		// TODO Auto-generated method stub
		return locationRepository.findAll();
	}

	@Transactional
	public Location update(Location Location) throws LocationNotFound {
		Location updatedLocation = locationRepository.findById(Location.getId());

		if (updatedLocation == null) {
			throw new LocationNotFound();
		}
		updatedLocation.setCity(Location.getCity());
		return updatedLocation;
	}

	public Location findById(Long id) throws LocationNotFound {

		Location createdLocation = locationRepository.findById(id);

		if (createdLocation == null) {
			throw new LocationNotFound();
		}

		return createdLocation;

	}

	public List<Location> findByName(String name,String city) {
		// TODO Auto-generated method stub
		return locationRepository.findByName(name,city);
	}

	public List<Location> findByNameWithPerson(String name) {
		// TODO Auto-generated method stub
		return locationRepository.findByNameWithPerson(name);
	}

	@Override
	public List<Location> findByRegion(String region) {
		// TODO Auto-generated method stub
		return locationRepository.findByRegion(region);
	}

}
