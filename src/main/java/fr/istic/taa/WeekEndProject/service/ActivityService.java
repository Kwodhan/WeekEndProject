package fr.istic.taa.WeekEndProject.service;

import java.util.List;

import fr.istic.taa.WeekEndProject.model.Activity.AbstractActivity;
import fr.istic.taa.WeekEndProject.service.exception.ActivityNotFound;
import fr.istic.taa.WeekEndProject.service.exception.LocationNotFound;

public interface ActivityService {
	public AbstractActivity create(AbstractActivity activity);

	public AbstractActivity delete(Long id) throws ActivityNotFound;

	public List<AbstractActivity> findAll();

	public AbstractActivity update(AbstractActivity activity) throws ActivityNotFound;
	
	public AbstractActivity findById(Long id);
	
	public List<AbstractActivity> findByName(String name);
}
