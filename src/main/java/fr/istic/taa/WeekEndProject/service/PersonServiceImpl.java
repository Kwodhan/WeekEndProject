package fr.istic.taa.WeekEndProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.istic.taa.WeekEndProject.model.Location;
import fr.istic.taa.WeekEndProject.model.Person;
import fr.istic.taa.WeekEndProject.repository.LocationRepository;
import fr.istic.taa.WeekEndProject.repository.PersonRepository;
import fr.istic.taa.WeekEndProject.service.exception.LocationNotFound;
import fr.istic.taa.WeekEndProject.service.exception.PersonNotFound;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private LocationRepository locationRepository;

	public PersonServiceImpl() {

	}

	@Transactional
	public Person create(Person person) {
		Person createdPerson = person;
		return personRepository.save(createdPerson);
	}

	@Transactional
	public Person delete(Long id) throws PersonNotFound {
		Person deletedPerson = personRepository.findById(id);

		if (deletedPerson == null) {
			throw new PersonNotFound();
		}
		deletedPerson.getHomes().clear();
		deletedPerson.getActivities().clear();
		personRepository.delete(deletedPerson);
		return deletedPerson;
	}

	public List<Person> findAll() {
		// TODO Auto-generated method stub
		return personRepository.findAll();
	}

	@Transactional
	public Person update(Person person) throws PersonNotFound {

		Person updatedPerson = personRepository.findById(person.getId());

		if (updatedPerson == null)
			throw new PersonNotFound();

		updatedPerson.setName(person.getName());
		return updatedPerson;
	}

	/**
	 * fetch = eager
	 * @throws PersonNotFound 
	 */
	public Person findById(Long id) throws PersonNotFound {
		// TODO Auto-generated method stub

		Person createPerson = personRepository.findById(id);
		
		if (createPerson == null)
			throw new PersonNotFound();
		
		return createPerson;

	}

	public List<Person> findByName(String name) {
		// TODO Auto-generated method stub
		return personRepository.findByName(name);
	}

	public List<Person> findByNameWithLocation(String name) {
		// TODO Auto-generated method stub
		return personRepository.findByNameWithLocation(name);
	}

	public List<Person> findByNameWithActivities(String name) {
		// TODO Auto-generated method stub
		return personRepository.findByNameWithActivities(name);
	}

	public List<Person> findByNameWithAll(String name) {
		// TODO Auto-generated method stub
		return personRepository.findByNameWithAll(name);
	}

	@Transactional
	public Person updateLocation(Long idPerson, Long idLocation) throws PersonNotFound, LocationNotFound {

		Person updatedPerson = personRepository.findById(idPerson);

		if (updatedPerson == null)
			throw new PersonNotFound();

		Location location = locationRepository.findOne(idLocation);

		if (location == null)
			throw new LocationNotFound();

		if (!updatedPerson.getHomes().contains(location)) {
			updatedPerson.getHomes().add(location);
		}

		return updatedPerson;
	}

}
