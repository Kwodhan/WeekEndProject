package fr.istic.taa.WeekEndProject.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.istic.taa.WeekEndProject.model.Meteo;
import fr.istic.taa.WeekEndProject.model.Person;
import fr.istic.taa.WeekEndProject.model.Activity.AbstractActivity;
import fr.istic.taa.WeekEndProject.repository.ActivityRepository;
import fr.istic.taa.WeekEndProject.service.exception.ActivityNotFound;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository activityRepository;

	@Transactional
	public AbstractActivity create(AbstractActivity activity) {

		return activityRepository.save(activity);
	}

	public ActivityServiceImpl() {

	}

	@Transactional
	public AbstractActivity delete(Long id) throws ActivityNotFound {
		AbstractActivity deletedActivity = activityRepository.findById(id);

		if (deletedActivity == null)
			throw new ActivityNotFound();

		deletedActivity.getMeteos().clear();

		for (Person p : deletedActivity.getPersons()) {
			p.getActivities().clear();
		}
		deletedActivity.getPersons().clear();

		activityRepository.delete(deletedActivity);

		return deletedActivity;
	}

	public List<AbstractActivity> findAll() {
		// TODO Auto-generated method stub
		return activityRepository.findAll();
	}

	@Transactional
	public AbstractActivity update(AbstractActivity activity) throws ActivityNotFound {
		// TODO Auto-generated method stub
		AbstractActivity updatedActivity = activityRepository.findById(activity.getId());

		if (updatedActivity == null) {
			throw new ActivityNotFound();
		}
		updatedActivity.setName(activity.getName());

		return updatedActivity;
	}

	public AbstractActivity findById(Long id) throws ActivityNotFound {
		// TODO Auto-generated method stub
		AbstractActivity getActivity = activityRepository.findById(id);

		if (getActivity == null) {
			throw new ActivityNotFound();
		}
		return getActivity;
	}

	public List<AbstractActivity> findByName(String name) {
		// TODO Auto-generated method stub
		return activityRepository.findByName(name);
	}

	@Transactional
	public AbstractActivity updateLocation(Long idActivity, Meteo meteo) throws ActivityNotFound {
		AbstractActivity updatedActivity = activityRepository.findById(idActivity);

		if (updatedActivity == null)
			throw new ActivityNotFound();

		if (!updatedActivity.getMeteos().contains(meteo)) {
			updatedActivity.getMeteos().add(meteo);
		}

		return updatedActivity;
	}

	public List<AbstractActivity> findAllSport() {
		// TODO Auto-generated method stub
		return activityRepository.findAllSport();
	}

	public List<AbstractActivity> findAllLoisir() {
		// TODO Auto-generated method stub
		return activityRepository.findAllLoisir();
	}

}
