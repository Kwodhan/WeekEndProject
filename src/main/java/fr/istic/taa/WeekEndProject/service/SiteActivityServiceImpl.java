package fr.istic.taa.WeekEndProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.istic.taa.WeekEndProject.model.Location;
import fr.istic.taa.WeekEndProject.model.SiteActivity;
import fr.istic.taa.WeekEndProject.model.Activity.AbstractActivity;
import fr.istic.taa.WeekEndProject.repository.ActivityRepository;
import fr.istic.taa.WeekEndProject.repository.LocationRepository;
import fr.istic.taa.WeekEndProject.repository.SiteActivityRepository;
import fr.istic.taa.WeekEndProject.service.exception.ActivityNotFound;
import fr.istic.taa.WeekEndProject.service.exception.LocationNotFound;
import fr.istic.taa.WeekEndProject.service.exception.SiteActivityNotFound;

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
		deletedSiteActivity.setActivity(null);
		siteActivityRepository.delete(deletedSiteActivity);

		return deletedSiteActivity;
	}

	public List<SiteActivity> findAll() {
		// TODO Auto-generated method stub
		return siteActivityRepository.findAll();
	}

	@Transactional
	public SiteActivity update(SiteActivity siteActivity) throws SiteActivityNotFound {
		// TODO Auto-generated method stub
		SiteActivity updatedSiteActivity = siteActivityRepository.findById(siteActivity.getId());

		if (updatedSiteActivity == null) {
			throw new SiteActivityNotFound();
		}
		updatedSiteActivity.setName(siteActivity.getName());
		updatedSiteActivity.setLocation(siteActivity.getLocation());
		updatedSiteActivity.setActivity(siteActivity.getActivity());

		return updatedSiteActivity;
	}

	public SiteActivity findById(Long id) throws SiteActivityNotFound {
		// TODO Auto-generated method stub
		SiteActivity getSiteActivity = siteActivityRepository.findById(id);

		if (getSiteActivity == null) {
			throw new SiteActivityNotFound();
		}
		return getSiteActivity;
	}

	public List<SiteActivity> findByName(String name) {
		// TODO Auto-generated method stub
		return siteActivityRepository.findByName(name);
	}

	@Transactional
	public SiteActivity updateLocation(Long idSite, Long idLocation) throws SiteActivityNotFound, LocationNotFound {
		SiteActivity updatedSiteActivity = siteActivityRepository.findById(idSite);

		if (updatedSiteActivity == null)
			throw new SiteActivityNotFound();

		Location location = locationRepository.findById(idLocation);

		if (location == null)
			throw new LocationNotFound();

		updatedSiteActivity.setLocation(location);

		return updatedSiteActivity;
	}

	@Transactional
	public SiteActivity updateActivity(Long idSite, Long idActivity) throws SiteActivityNotFound, ActivityNotFound {
		SiteActivity updatedSiteActivity = siteActivityRepository.findById(idSite);

		if (updatedSiteActivity == null)
			throw new SiteActivityNotFound();

		AbstractActivity activity = activityRepository.findById(idActivity);

		if (activity == null)
			throw new ActivityNotFound();

		updatedSiteActivity.setActivity(activity);

		return updatedSiteActivity;
	}

}
