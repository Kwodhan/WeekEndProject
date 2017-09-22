package fr.istic.taa.WeekEndProject.service;

import java.util.List;

import fr.istic.taa.WeekEndProject.model.Person;
import fr.istic.taa.WeekEndProject.service.exception.LocationNotFound;
import fr.istic.taa.WeekEndProject.service.exception.PersonNotFound;

public interface PersonService {

	public Person create(Person person);

	public Person delete(Long id) throws PersonNotFound;

	public List<Person> findAll();

	public Person update(Person person) throws PersonNotFound;
	
	public Person updateLocation(Long idPerson,Long idLocation) throws LocationNotFound,PersonNotFound;

	public Person findById(Long id) throws PersonNotFound;

	public List<Person> findByName(String name);

	public List<Person> findByNameWithLocation(String name);

	public List<Person> findByNameWithActivities(String name);

	public List<Person> findByNameWithAll(String name);

}
