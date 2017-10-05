package fr.istic.taa.weekEndProject.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.istic.taa.weekEndProject.model.Location;
import fr.istic.taa.weekEndProject.model.SiteActivity;
import fr.istic.taa.weekEndProject.model.activity.AbstractActivity;
import fr.istic.taa.weekEndProject.repository.ActivityRepository;
import fr.istic.taa.weekEndProject.repository.LocationRepository;
import fr.istic.taa.weekEndProject.repository.SiteActivityRepository;
import fr.istic.taa.weekEndProject.service.exception.ActivityNotFound;
import fr.istic.taa.weekEndProject.service.exception.SiteActivityNotFound;

@Service
public class SiteActivityServiceImpl implements SiteActivityService {

	@Autowired
	private SiteActivityRepository siteActivityRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private ActivityRepository activityRepository;

	public SiteActivityServiceImpl() {

	}

	@Transactional
	public SiteActivity create(SiteActivity site) {

		return siteActivityRepository.save(site);
	}

	@Transactional
	public SiteActivity delete(Long id) throws SiteActivityNotFound {
		SiteActivity deletedSiteActivity = siteActivityRepository.findById(id);

		if (deletedSiteActivity == null)
			throw new SiteActivityNotFound();

		deletedSiteActivity.setLocation(null);
		deletedSiteActivity.getActivities().clear();
		siteActivityRepository.delete(deletedSiteActivity);

		return deletedSiteActivity;
	}

	public List<SiteActivity> findAll() {

		return siteActivityRepository.findAll();
	}

	@Transactional
	public SiteActivity update(SiteActivity siteActivity) throws SiteActivityNotFound {

		SiteActivity updatedSiteActivity = siteActivityRepository.findById(siteActivity.getId());

		if (updatedSiteActivity == null) {
			throw new SiteActivityNotFound();
		}
		updatedSiteActivity.setName(siteActivity.getName());

		// update activities
		Set<AbstractActivity> updateActivities = new HashSet<AbstractActivity>();

		for (AbstractActivity l : siteActivity.getActivities()) {
			AbstractActivity up = activityRepository.findById(l.getId());
			if (up != null) {
				updateActivities.add(up);

			}
		}
		updatedSiteActivity.setActivities(updateActivities);
		// update location
		if (siteActivity.getLocation() == null) {
			updatedSiteActivity.setLocation(null);
		} else {
			Location l = locationRepository.findById(siteActivity.getLocation().getId());
			if (l != null) {
				updatedSiteActivity.setLocation(l);
			}
		}
		return updatedSiteActivity;
	}

	public SiteActivity findById(Long id) throws SiteActivityNotFound {

		SiteActivity getSiteActivity = siteActivityRepository.findById(id);

		if (getSiteActivity == null) {
			throw new SiteActivityNotFound();
		}
		return getSiteActivity;
	}

	public List<SiteActivity> findByName(String name) {

		return siteActivityRepository.findByName(name);
	}

	@Transactional
	public SiteActivity updateActivities(Long id, Set<AbstractActivity> activities)
			throws ActivityNotFound, SiteActivityNotFound {

		SiteActivity updatedSiteActivity = siteActivityRepository.findById(id);

		if (updatedSiteActivity == null) {
			throw new SiteActivityNotFound();
		}

		// update activities
		Set<AbstractActivity> updateActivities = new HashSet<AbstractActivity>();

		for (AbstractActivity l : activities) {
			AbstractActivity up = activityRepository.findById(l.getId());
			if (up != null) {
				updateActivities.add(up);

			}
		}
		updatedSiteActivity.setActivities(updateActivities);

		return updatedSiteActivity;
	}

	@Transactional
	public SiteActivity updateActivities(Long id, Long idAct) throws ActivityNotFound, SiteActivityNotFound {
		SiteActivity updatedActivity = siteActivityRepository.findById(id);

		if (updatedActivity == null)
			throw new SiteActivityNotFound();

		AbstractActivity addActivity = activityRepository.findById(idAct);

		if (addActivity == null)
			throw new ActivityNotFound();

		updatedActivity.getActivities().add(addActivity);

		return updatedActivity;
	}

	@Transactional
	public SiteActivity deleteActivities(Long idPerson, Long idAct) throws ActivityNotFound, SiteActivityNotFound {
		SiteActivity updatedActivity = siteActivityRepository.findById(idPerson);

		if (updatedActivity == null)
			throw new SiteActivityNotFound();

		AbstractActivity addActivity = activityRepository.findById(idAct);

		if (addActivity == null)
			throw new ActivityNotFound();

		updatedActivity.getActivities().remove(addActivity);

		return updatedActivity;
	}

}
