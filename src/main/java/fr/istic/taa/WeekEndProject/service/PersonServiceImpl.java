package fr.istic.taa.WeekEndProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.istic.taa.WeekEndProject.model.Person;
import fr.istic.taa.WeekEndProject.repository.PersonRepository;
import fr.istic.taa.WeekEndProject.service.exception.PersonNotFound;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository PersonRepository;

	public PersonServiceImpl() {

	}

	@Transactional
	public Person create(Person person) {
		Person createdPerson = person;
		return PersonRepository.save(createdPerson);
	}

	@Transactional
	public Person delete(Long id) throws PersonNotFound {
		Person deletedPerson = PersonRepository.findById(id);

		if (deletedPerson == null) {
			throw new PersonNotFound();
		}
		deletedPerson.getHomes().clear();
		deletedPerson.getActivities().clear();
		PersonRepository.delete(deletedPerson);
		return deletedPerson;
	}

	public List<Person> findAll() {
		// TODO Auto-generated method stub
		return PersonRepository.findAll();
	}

	@Transactional
	public Person update(Person person) throws PersonNotFound {
		Person updatedPerson = PersonRepository.findById(person.getId());

		if (updatedPerson == null)
			throw new PersonNotFound();

		updatedPerson.setName(person.getName());
		updatedPerson.setHomes(person.getHomes());
		updatedPerson.setActivities(person.getActivities());
		return updatedPerson;
	}

	/**
	 * fetch = eager
	 */
	public Person findById(Long id) {
		// TODO Auto-generated method stub

		return PersonRepository.findById(id);

	}

	public List<Person> findByName(String name) {
		// TODO Auto-generated method stub
		return PersonRepository.findByName(name);
	}

	public List<Person> findByNameWithLocation(String name) {
		// TODO Auto-generated method stub
		return PersonRepository.findByNameWithLocation(name);
	}

	public List<Person> findByNameWithActivities(String name) {
		// TODO Auto-generated method stub
		return PersonRepository.findByNameWithActivities(name);
	}

	public List<Person> findByNameWithAll(String name) {
		// TODO Auto-generated method stub
		return PersonRepository.findByNameWithAll(name);
	}

}
