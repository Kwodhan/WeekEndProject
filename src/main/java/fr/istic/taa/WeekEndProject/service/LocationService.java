package fr.istic.taa.WeekEndProject.service;

import java.util.List;

import fr.istic.taa.WeekEndProject.model.Location;
import fr.istic.taa.WeekEndProject.service.exception.LocationNotFound;

public interface LocationService {
	public Location create(Location location);

	public Location delete(Long id) throws LocationNotFound;

	public List<Location> findAll();

	public Location update(Location location) throws LocationNotFound;

	public Location findById(Long id);
	
	public List<Location> findByName(String name);
}
