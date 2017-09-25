package fr.istic.taa.weekEndProject.service;

import java.util.List;

import fr.istic.taa.weekEndProject.model.Location;
import fr.istic.taa.weekEndProject.service.exception.LocationNotFound;

public interface LocationService {
	public Location create(Location location);

	public Location delete(Long id) throws LocationNotFound;

	public List<Location> findAll();

	public Location update(Location location) throws LocationNotFound;
	
	public Location findById(Long id) throws LocationNotFound;
	
	public List<Location> findByName(String name);
	
	public List<Location> findByNameWithPerson(String name);
}
