package fr.istic.taa.weekEndProject.service;

import java.util.List;

import fr.istic.taa.weekEndProject.model.Meteo;
import fr.istic.taa.weekEndProject.model.activity.AbstractActivity;
import fr.istic.taa.weekEndProject.service.exception.ActivityNotFound;

public interface ActivityService {
	public AbstractActivity create(AbstractActivity activity);

	public AbstractActivity delete(Long id) throws ActivityNotFound;

	public List<AbstractActivity> findAll();
	
	public List<AbstractActivity> findAllSport();
	
	public List<AbstractActivity> findAllLeisure();

	public AbstractActivity update(AbstractActivity activity) throws ActivityNotFound;
	
	public AbstractActivity updateLocation(Long idActivity,Meteo meteo) throws ActivityNotFound;

	public AbstractActivity findById(Long id) throws ActivityNotFound;

	public List<AbstractActivity> findByName(String name);
}
