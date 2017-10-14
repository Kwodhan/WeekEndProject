package fr.istic.taa.weekEndProject.service;

import java.util.List;
import java.util.Set;

import fr.istic.taa.weekEndProject.model.Activity;
import fr.istic.taa.weekEndProject.model.Location;
import fr.istic.taa.weekEndProject.model.User;
import fr.istic.taa.weekEndProject.service.exception.ActivityNotFound;
import fr.istic.taa.weekEndProject.service.exception.LocationNotFound;
import fr.istic.taa.weekEndProject.service.exception.UserNotFound;

public interface UserService {

	
	public User createAdmin(User person);
	
	public User createUser(User person);
	
	public User createGerant(User person);

	public User delete(Long id) throws UserNotFound;

	public List<User> findAll();

	public User update(User person) throws UserNotFound;

	public User updateLocation(Long idPerson, Set<Location> location) throws LocationNotFound, UserNotFound;

	public User updateActivities(Long id, Set<Activity> activities) throws ActivityNotFound, UserNotFound;

	public User updateLocation(Long idPerson, Long idLoc) throws LocationNotFound, UserNotFound;

	public User updateActivities(Long id, Long idAct) throws ActivityNotFound, UserNotFound;

	public User deleteLocation(Long idPerson, Long idLoc) throws LocationNotFound, UserNotFound;

	public User deleteActivities(Long id, Long idAct) throws ActivityNotFound, UserNotFound;

	public User findById(Long id) throws UserNotFound;

	public User findByPseudo(String pseudo) throws UserNotFound;


	
	

}
