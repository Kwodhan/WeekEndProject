package fr.istic.taa.weekEndProject.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.istic.taa.weekEndProject.model.Activity;
import fr.istic.taa.weekEndProject.model.Location;
import fr.istic.taa.weekEndProject.model.Role;
import fr.istic.taa.weekEndProject.model.User;
import fr.istic.taa.weekEndProject.repository.ActivityRepository;
import fr.istic.taa.weekEndProject.repository.LocationRepository;
import fr.istic.taa.weekEndProject.repository.UserRepository;
import fr.istic.taa.weekEndProject.service.exception.ActivityNotFound;
import fr.istic.taa.weekEndProject.service.exception.LocationNotFound;
import fr.istic.taa.weekEndProject.service.exception.PersonNotFound;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository personRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public UserServiceImpl() {

	}

	@Transactional
	public User createAdmin(User person) {
		User createdPerson = new User();
		createdPerson.setFirstName(person.getFirstName());
		createdPerson.setLastName(person.getLastName());
		createdPerson.setEmailAddress(person.getEmailAddress());
		createdPerson.setPassword(encoder.encode(person.getPassword()));
		createdPerson.setPseudo(person.getPseudo());
		Set<Role> roles = new HashSet<Role>();
		roles.add(Role.ROLE_ADMIN);
		createdPerson.setRoles(roles);
		return personRepository.save(createdPerson);
	}

	@Transactional
	public User createUser(User person) {
		User createdPerson = new User();
		createdPerson.setFirstName(person.getFirstName());
		createdPerson.setLastName(person.getLastName());
		createdPerson.setEmailAddress(person.getEmailAddress());
		createdPerson.setPassword(encoder.encode(person.getPassword()));
		createdPerson.setPseudo(person.getPseudo());
		Set<Role> roles = new HashSet<Role>();
		roles.add(Role.ROLE_USER);
		createdPerson.setRoles(roles);
		return personRepository.save(createdPerson);
	}
	
	@Transactional
	public User createGerant(User person) {
		User createdPerson = new User();
		createdPerson.setFirstName(person.getFirstName());
		createdPerson.setLastName(person.getLastName());
		createdPerson.setEmailAddress(person.getEmailAddress());
		createdPerson.setPassword(encoder.encode(person.getPassword()));
		createdPerson.setPseudo(person.getPseudo());
		Set<Role> roles = new HashSet<Role>();
		roles.add(Role.ROLE_GERANT);
		createdPerson.setRoles(roles);
		return personRepository.save(createdPerson);
	}


	@Transactional
	public User delete(Long id) throws PersonNotFound {
		User deletedPerson = personRepository.findById(id);

		if (deletedPerson == null) {
			throw new PersonNotFound();
		}
		deletedPerson.getHomes().clear();
		deletedPerson.getActivities().clear();

		personRepository.delete(deletedPerson);
		return deletedPerson;
	}

	public List<User> findAll() {

		return personRepository.findAll();
	}

	@Transactional
	public User update(User person) throws PersonNotFound {

		User updatedPerson = personRepository.findById(person.getId());

		if (updatedPerson == null)
			throw new PersonNotFound();

		updatedPerson.setFirstName(person.getFirstName());
		updatedPerson.setLastName(person.getLastName());
		updatedPerson.setEmailAddress(person.getEmailAddress());

		// update locations
		Set<Location> updatelocations = new HashSet<Location>();
		for (Location l : person.getHomes()) {
			Location up = locationRepository.findById(l.getId());
			if (up != null) {
				updatelocations.add(up);

			}
		}
		updatedPerson.setHomes(updatelocations);

		// update activities
		Set<Activity> updateActivities = new HashSet<Activity>();

		for (Activity l : person.getActivities()) {
			Activity up = activityRepository.findById(l.getId());
			if (up != null) {
				updateActivities.add(up);

			}
		}
		updatedPerson.setActivities(updateActivities);

		return updatedPerson;
	}

	/**
	 * fetch = eager
	 * 
	 * @throws PersonNotFound
	 */
	public User findById(Long id) throws PersonNotFound {

		User createPerson = personRepository.findById(id);

		if (createPerson == null)
			throw new PersonNotFound();

		return createPerson;

	}

	public List<User> findByName(String name) {

		return personRepository.findByPseudo(name);
	}


	@Transactional
	public User updateLocation(Long idPerson, Set<Location> locations) throws LocationNotFound, PersonNotFound {

		User updatedPerson = personRepository.findById(idPerson);

		if (updatedPerson == null)
			throw new PersonNotFound();
		Set<Location> updatelocations = new HashSet<Location>();

		for (Location l : locations) {
			Location up = locationRepository.findById(l.getId());
			if (up != null) {
				updatelocations.add(up);

			}
		}
		updatedPerson.setHomes(updatelocations);

		return updatedPerson;
	}

	@Transactional
	public User updateActivities(Long id, Set<Activity> activities) throws ActivityNotFound, PersonNotFound {

		User updatedPerson = personRepository.findById(id);

		if (updatedPerson == null)
			throw new PersonNotFound();
		Set<Activity> updateActivities = new HashSet<Activity>();

		for (Activity l : activities) {
			Activity up = activityRepository.findById(l.getId());
			if (up != null) {
				updateActivities.add(up);

			}
		}
		updatedPerson.setActivities(updateActivities);

		return updatedPerson;
	}

	@Transactional
	public User updateLocation(Long idPerson, Long idLoc) throws LocationNotFound, PersonNotFound {
		User updatedPerson = personRepository.findById(idPerson);

		if (updatedPerson == null)
			throw new PersonNotFound();

		Location addLocation = locationRepository.findById(idLoc);

		if (addLocation == null)
			throw new LocationNotFound();

		updatedPerson.getHomes().add(addLocation);

		return updatedPerson;

	}

	@Transactional
	public User updateActivities(Long id, Long idAct) throws ActivityNotFound, PersonNotFound {
		User updatedPerson = personRepository.findById(id);

		if (updatedPerson == null)
			throw new PersonNotFound();

		Activity addActivity = activityRepository.findById(idAct);

		if (addActivity == null)
			throw new ActivityNotFound();

		updatedPerson.getActivities().add(addActivity);

		return updatedPerson;
	}

	@Transactional
	public User deleteLocation(Long idPerson, Long idLoc) throws LocationNotFound, PersonNotFound {
		User updatedPerson = personRepository.findById(idPerson);

		if (updatedPerson == null)
			throw new PersonNotFound();

		Location addLocation = locationRepository.findById(idLoc);

		if (addLocation == null)
			throw new LocationNotFound();

		updatedPerson.getHomes().remove(addLocation);

		return updatedPerson;
	}

	@Transactional
	public User deleteActivities(Long id, Long idAct) throws ActivityNotFound, PersonNotFound {
		User updatedPerson = personRepository.findById(id);

		if (updatedPerson == null)
			throw new PersonNotFound();

		Activity addActivity = activityRepository.findById(idAct);

		if (addActivity == null)
			throw new ActivityNotFound();

		updatedPerson.getActivities().remove(addActivity);

		return updatedPerson;
	}

	@Override
	public User findByPseudo(String pseudo)  throws  PersonNotFound{
		List<User> createPerson = personRepository.findByPseudo(pseudo);

		if ( createPerson.size() == 0)
			throw new PersonNotFound();

		return createPerson.get(0);
	}

	
}
