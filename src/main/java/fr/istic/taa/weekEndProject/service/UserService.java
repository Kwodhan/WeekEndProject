package fr.istic.taa.weekEndProject.service;

import java.util.List;
import java.util.Set;

import fr.istic.taa.weekEndProject.model.Location;
import fr.istic.taa.weekEndProject.model.User;
import fr.istic.taa.weekEndProject.model.activity.AbstractActivity;
import fr.istic.taa.weekEndProject.service.exception.ActivityNotFound;
import fr.istic.taa.weekEndProject.service.exception.LocationNotFound;
import fr.istic.taa.weekEndProject.service.exception.PersonNotFound;

public interface UserService {

	public User create(User person);

	public User delete(Long id) throws PersonNotFound;

	public List<User> findAll();

	public User update(User person) throws PersonNotFound;

	public User updateLocation(Long idPerson, Set<Location> location) throws LocationNotFound, PersonNotFound;

	public User updateActivities(Long id, Set<AbstractActivity> activities) throws ActivityNotFound, PersonNotFound;

	public User updateLocation(Long idPerson, Long idLoc) throws LocationNotFound, PersonNotFound;

	public User updateActivities(Long id, Long idAct) throws ActivityNotFound, PersonNotFound;

	public User deleteLocation(Long idPerson, Long idLoc) throws LocationNotFound, PersonNotFound;

	public User deleteActivities(Long id, Long idAct) throws ActivityNotFound, PersonNotFound;

	public User findById(Long id) throws PersonNotFound;

	public List<User> findByName(String name);

	public List<User> findByNameWithAll(String name);


	
	

}