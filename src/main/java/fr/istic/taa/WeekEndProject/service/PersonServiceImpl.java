package fr.istic.taa.WeekEndProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.istic.taa.WeekEndProject.model.Location;
import fr.istic.taa.WeekEndProject.model.Person;
import fr.istic.taa.WeekEndProject.repository.PersonRepository;
import fr.istic.taa.WeekEndProject.service.exception.PersonNotFound;

@Service
public class PersonServiceImpl implements PersonService {


	@Autowired
	private PersonRepository PersonRepository;

	public PersonServiceImpl(){
	
	}
	@Transactional
	public Person create(Person person) {
		Person createdPerson = person;
		return PersonRepository.save(createdPerson);
	}

	@Transactional
	public Person delete(Long id) throws PersonNotFound {
		Person deletedPerson = PersonRepository.findOne(id);

		if (deletedPerson == null)
			throw new PersonNotFound();
		
		
		PersonRepository.delete(deletedPerson);
		return deletedPerson;
	}

	public List<Person> findAll() {
		// TODO Auto-generated method stub
		return PersonRepository.findAll();
	}

	@Transactional
	public Person update(Person person) throws PersonNotFound {
		Person updatedPerson = PersonRepository.findOne(person.getId());

		if (updatedPerson == null)
			throw new PersonNotFound();

		updatedPerson.setName(person.getName());
		for(Location p : person.getHomes()) {
			updatedPerson.getHomes().add(p);
		}
		
		/*
		 * TODO : others attribut
		 */
		return updatedPerson;
	}
	@Transactional
	public Person findById(Long id) {
		// TODO Auto-generated method stub
		return PersonRepository.findOne(id);

	}

	public List<Person> findByName(String name) {
		// TODO Auto-generated method stub
		return PersonRepository.findByName(name);
	}
	
	public List<Person> findByNameWithLocation(String name) {
		// TODO Auto-generated method stub
		return PersonRepository.findByNameWithLocation(name);
	}

}
