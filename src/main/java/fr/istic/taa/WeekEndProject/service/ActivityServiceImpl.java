package fr.istic.taa.WeekEndProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	

	@Transactional
	public AbstractActivity delete(Long id) throws ActivityNotFound {
		AbstractActivity deletedActivity = activityRepository.findById(id);

		if (deletedActivity == null)
			throw new ActivityNotFound();

		deletedActivity.getMeteos().clear();
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

	public AbstractActivity findById(Long id) {
		// TODO Auto-generated method stub
		return activityRepository.findById(id);
	}

	public List<AbstractActivity> findByName(String name) {
		// TODO Auto-generated method stub
		return activityRepository.findByName(name);
	}

}
