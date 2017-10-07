package fr.istic.taa.weekEndProject.service;

import java.util.List;

import fr.istic.taa.weekEndProject.model.Activity;
import fr.istic.taa.weekEndProject.model.Meteo;
import fr.istic.taa.weekEndProject.service.exception.ActivityNotFound;

public interface ActivityService {
	public Activity create(Activity activity);

	public Activity delete(Long id) throws ActivityNotFound;

	public List<Activity> findAll();
	
	public List<Activity> findAllSport();
	
	public List<Activity> findAllLeisure();

	public Activity update(Activity activity) throws ActivityNotFound;
	
	public Activity updateLocation(Long idActivity,Meteo meteo) throws ActivityNotFound;

	public Activity findById(Long id) throws ActivityNotFound;

	public List<Activity> findByName(String name);
}
