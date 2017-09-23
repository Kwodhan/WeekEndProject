package fr.istic.taa.WeekEndProject.service;

import java.util.List;

import fr.istic.taa.WeekEndProject.model.SiteActivity;
import fr.istic.taa.WeekEndProject.service.exception.ActivityNotFound;
import fr.istic.taa.WeekEndProject.service.exception.LocationNotFound;
import fr.istic.taa.WeekEndProject.service.exception.SiteActivityNotFound;

public interface SiteActivityService {
	public SiteActivity create(SiteActivity site);

	public SiteActivity delete(Long id) throws SiteActivityNotFound;

	public List<SiteActivity> findAll();

	public SiteActivity update(SiteActivity activity) throws SiteActivityNotFound;
	
	public SiteActivity updateLocation(Long idSite,Long idActivity) throws SiteActivityNotFound,LocationNotFound;
	
	public SiteActivity updateActivity(Long idSite,Long idActivity) throws SiteActivityNotFound,ActivityNotFound;

	public SiteActivity findById(Long id) throws SiteActivityNotFound;

	public List<SiteActivity> findByName(String name);
}
