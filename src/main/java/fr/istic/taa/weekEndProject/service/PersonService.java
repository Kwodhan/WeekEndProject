package fr.istic.taa.weekEndProject.service;

import java.util.List;
import java.util.Set;

import fr.istic.taa.weekEndProject.model.Location;
import fr.istic.taa.weekEndProject.model.Person;
import fr.istic.taa.weekEndProject.model.activity.AbstractActivity;
import fr.istic.taa.weekEndProject.service.exception.ActivityNotFound;
import fr.istic.taa.weekEndProject.service.exception.LocationNotFound;
import fr.istic.taa.weekEndProject.service.exception.PersonNotFound;

public interface PersonService {

	public Person create(Person person);

	public Person delete(Long id) throws PersonNotFound;

	public List<Person> findAll();

	public Person update(Person person) throws PersonNotFound;

	public Person updateLocation(Long idPerson, Set<Location> location) throws LocationNotFound, PersonNotFound;

	public Person updateActivities(Long id, Set<AbstractActivity> activities) throws ActivityNotFound, PersonNotFound;

	public Person updateLocation(Long idPerson, Long idLoc) throws LocationNotFound, PersonNotFound;

	public Person updateActivities(Long id, Long idAct) throws ActivityNotFound, PersonNotFound;

	public Person deleteLocation(Long idPerson, Long idLoc) throws LocationNotFound, PersonNotFound;

	public Person deleteActivities(Long id, Long idAct) throws ActivityNotFound, PersonNotFound;

	public Person findById(Long id) throws PersonNotFound;

	public List<Person> findByName(String name);

	public List<Person> findByNameWithAll(String name);

}
