package fr.istic.taa.weekEndProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.istic.taa.weekEndProject.model.Activity;
import fr.istic.taa.weekEndProject.model.Meteo;
import fr.istic.taa.weekEndProject.model.User;
import fr.istic.taa.weekEndProject.model.SiteActivity;
import fr.istic.taa.weekEndProject.repository.ActivityRepository;
import fr.istic.taa.weekEndProject.service.exception.ActivityNotFound;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository activityRepository;

	@Transactional
	public Activity create(Activity activity) {
		System.out.println(activity.getType());
		return activityRepository.save(activity);
	}

	public ActivityServiceImpl() {

	}

	@Transactional
	public Activity delete(Long id) throws ActivityNotFound {
		Activity deletedActivity = activityRepository.findById(id);

		if (deletedActivity == null)
			throw new ActivityNotFound();

		deletedActivity.getMeteos().clear();

		for (User p : deletedActivity.getUsers()) {
			p.getActivities().remove(deletedActivity);
		}
		deletedActivity.getUsers().clear();
		for (SiteActivity s : deletedActivity.getSites()) {
			s.getActivities().remove(deletedActivity);
		}
		deletedActivity.getSites().clear();
		activityRepository.delete(deletedActivity);

		return deletedActivity;
	}

	public List<Activity> findAll() {
		// TODO Auto-generated method stub
		return activityRepository.findAll();
	}

	@Transactional
	public Activity update(Activity activity) throws ActivityNotFound {
		// TODO Auto-generated method stub
		Activity updatedActivity = activityRepository.findById(activity.getId());

		if (updatedActivity == null) {
			throw new ActivityNotFound();
		}
		updatedActivity.setName(activity.getName());
		updatedActivity.setType(activity.getType());

		return updatedActivity;
	}

	public Activity findById(Long id) throws ActivityNotFound {
		// TODO Auto-generated method stub
		Activity getActivity = activityRepository.findById(id);

		if (getActivity == null) {
			throw new ActivityNotFound();
		}
		return getActivity;
	}

	public List<Activity> findByName(String name) {
		// TODO Auto-generated method stub
		return activityRepository.findByName(name);
	}

	@Transactional
	public Activity updateLocation(Long idActivity, Meteo meteo) throws ActivityNotFound {
		Activity updatedActivity = activityRepository.findById(idActivity);

		if (updatedActivity == null)
			throw new ActivityNotFound();

		if (!updatedActivity.getMeteos().contains(meteo)) {
			updatedActivity.getMeteos().add(meteo);
		}

		return updatedActivity;
	}

	public List<Activity> findAllSport() {
		// TODO Auto-generated method stub
		return activityRepository.findAllSport();
	}

	public List<Activity> findAllLeisure() {
		// TODO Auto-generated method stub
		return activityRepository.findAllLeisure();
	}

}
